package com.sk.bookz_customer.exception;

import lombok.Getter;

@Getter
public class UrlNotActiveException extends RuntimeException{

    private String url;

    public UrlNotActiveException(String url){
        this(url,null);
    }

    public UrlNotActiveException(String url, Throwable cause) {
        super("Url: "+url+" is not active",cause);
        this.url = url;
    }
}
