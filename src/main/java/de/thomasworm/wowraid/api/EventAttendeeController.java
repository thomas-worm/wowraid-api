package de.thomasworm.wowraid.api;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.thomasworm.wowraid.api.model.dto.EventAttendee;
import de.thomasworm.wowraid.api.model.persistence.Character;
import de.thomasworm.wowraid.api.model.persistence.CharacterRole;
import de.thomasworm.wowraid.api.model.persistence.Event;
import de.thomasworm.wowraid.api.model.persistence.User;
import reactor.core.publisher.Mono;

@RestController()
public class EventAttendeeController {

    private EventService eventService;
    private CharacterService characterService;
    private CharacterRoleService characterRoleService;
    private RealmService realmService;
    private EventAttendeeService eventAttendeeService;
    private UserService userService;

    @Autowired()
    public EventAttendeeController(
        EventService eventService,
        CharacterService characterService,
        CharacterRoleService characterRoleService,
        RealmService realmService,
        EventAttendeeService eventAttendeeService,
        UserService userService
    ) {
        this.eventService = eventService;
        this.characterService = characterService;
        this.characterRoleService = characterRoleService;
        this.realmService = realmService;
        this.eventAttendeeService = eventAttendeeService;
        this.userService = userService;
    }

    @PostMapping("/event/{key}/attendee")
    public Mono<ResponseEntity<Object>> createEventAttendee(@RequestBody() EventAttendee attendee, @PathVariable("key") String key, @RequestParam(name = "recursive", required = false, defaultValue = "false") String recursive, OAuth2AuthenticationToken token) {
        User user = this.userService.getUserByToken(token);
        if (!user.getGroups().contains("admin")) {
            return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
        }
        Mono<CharacterRole[]> rolesMono = Mono.just(new CharacterRole[] {});
        if (attendee.getRoles() != null && attendee.getRoles().size() > 0) {
            if (attendee.getRoles().size() == 1) {
                rolesMono = this.characterRoleService.findByName((String)attendee.getRoles().toArray()[0]).map(role -> new CharacterRole[] { role });
            } else {
                Iterable<Mono<CharacterRole>> iterableMonos = attendee.getRoles().stream().map(role -> this.characterRoleService.findByName(role))::iterator;
                rolesMono = Mono.zip(iterableMonos, arr -> Arrays.stream(arr).toArray(CharacterRole[]::new));
            }
        }
        Mono<Event> eventMono = this.eventService.getByKey(key);
        Mono<Character> characterMono = 
            this.realmService.getByName(attendee.getCharacterRealm()).flatMap(realm -> (realm == null) ? Mono.justOrEmpty(null) :
                this.characterService.getByRealmAndName(realm, attendee.getCharacterName()));
        return Mono.zip(eventMono, characterMono, rolesMono).flatMap(tuple -> {
            if (tuple.get(0) == null || tuple.get(1) == null) {
                return Mono.just(ResponseEntity.notFound().build());
            } else {
                Event event = (Event) tuple.get(0);
                Character character = (Character) tuple.get(1);
                CharacterRole[] roles = (CharacterRole[]) tuple.get(2);
                LocalDateTime startDateTime = attendee.getStartDateTime();
                LocalDateTime finishDateTime = attendee.getFinishDateTime();
                this.eventAttendeeService.create(event, character, Arrays.asList(roles), startDateTime, finishDateTime, (recursive != null && recursive.toLowerCase().equals("true")));
                return Mono.just(ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header(HttpHeaders.LOCATION, "/event/" + event.getKey() + "/attendee/" + character.getRealm().getName() + "/" + character.getName())
                    .build());
            }
        });
    }

}