import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.stereotype.Component;

@Component()
public class SameSiteInjector {

  private final ApplicationContext applicationContext;

  @Autowired()
  public SameSiteInjector(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @EventListener()
  public void onApplicationEvent(ContextRefreshedEvent event) {
    DefaultCookieSerializer cookieSerializer = applicationContext.getBean(DefaultCookieSerializer.class);
    cookieSerializer.setSameSite(null);
  }
}
