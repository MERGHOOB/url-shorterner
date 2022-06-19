package com.margub.urlshorterner.controller;

import com.margub.urlshorterner.entities.Url;

import javax.validation.constraints.NotBlank;
import java.net.URL;

public interface URLManager {

    String getURLByKey(@NotBlank String key);

    Url shortenURL(@NotBlank Url url);
}
