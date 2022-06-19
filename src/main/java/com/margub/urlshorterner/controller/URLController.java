package com.margub.urlshorterner.controller;

import com.margub.urlshorterner.entities.Url;
import com.margub.urlshorterner.entities.UrlError;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/rest/url")
public class URLController {

    @Autowired
    private URLManager urlManager;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity shortenURL(@RequestBody @NotNull Url url) {

        UrlValidator validator = new UrlValidator(new String[] {"http", "https"});
        if(!validator.isValid(url.getUrl())) {
            UrlError error  = new UrlError("url", url.getUrl(), "Invalid URL");
            return ResponseEntity.badRequest().body(error);
        }

        Url shortUrlEntry = urlManager.shortenURL(url);
        return ResponseEntity.ok(shortUrlEntry);
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public ResponseEntity getUrl(@PathVariable String key) {

        String url = urlManager.getURLByKey(key);
        if(url == null) {
            UrlError urlError = new UrlError("key", key, "No Such key exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(urlError);
        }
        return ResponseEntity.ok(url);
    }

}
