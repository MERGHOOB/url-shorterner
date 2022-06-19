package com.margub.urlshorterner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenApplication {


    /*
    long url --> short url

    // margub.com /longurl=?
    // minimum

    short-url=
    return longurl; clickable

    Map<>
    hashid -> shorturl

   hashMethod(longurl) -> hashid;
   


     */






    public static void main(String[] args) {
        SpringApplication.run(UrlShortenApplication.class, args);
    }

}
