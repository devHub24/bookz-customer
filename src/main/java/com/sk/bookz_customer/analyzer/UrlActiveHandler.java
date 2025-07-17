package com.sk.bookz_customer.analyzer;


import com.sk.bookz_customer.exception.UrlNotActiveException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UrlActiveHandler {

    @Value("${external.api:https://dog.ceo/dog-api/random}")
    private String url;
//
//    @EventListener(ContextRefreshedEvent.class)
//    public void onContextRefreshed(ContextRefreshedEvent event) {
//        throw new UrlNotActiveException(url);
//    }
}
