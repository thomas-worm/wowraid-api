package de.thomasworm.wowraid.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

public class RemoveSameSiteFilter implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpResponse resp = exchange.getResponse();
        HttpHeaders headers = resp.getHeaders();

        System.out.println("");
        System.out.println("");
        System.out.println("*************************************");
        for (Object key : headers.keySet()) {
            for (String value : headers.get(key)) {
                System.out.println(key.toString() + " = " + value);
            }
        }

        if (headers.containsKey(HttpHeaders.SET_COOKIE)) {
            List<String> cookieHeaders = headers.get(HttpHeaders.SET_COOKIE);

            if (cookieHeaders != null) {
                for (int i = 0; i < cookieHeaders.size(); i++) {
                    String header = cookieHeaders.get(i);
                    if (header.contains("SESSION")) {
                        cookieHeaders.set(i, header.replaceAll("; SameSite=Lax", ""));
                        System.out.println(header.replaceAll("; SameSite=Lax", ""));
                    }
                }
            }

            headers.put(HttpHeaders.SET_COOKIE, cookieHeaders);
        }

        return chain.filter(exchange);
	}
}