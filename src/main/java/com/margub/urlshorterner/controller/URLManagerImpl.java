package com.margub.urlshorterner.controller;

import com.google.common.hash.Hashing;
import com.margub.urlshorterner.entities.Url;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class URLManagerImpl implements URLManager {

    @Autowired
    RedisTemplate<String, Url> redisTemplate;


    @Override
    public String getURLByKey(String key) {
        Url url = redisTemplate.opsForValue().get(key);
        return Objects.requireNonNull(url).getUrl();
    }

    @Override
    public Url shortenURL(Url url) {
        String key = Hashing.murmur3_32().hashString(url.getUrl(), Charset.defaultCharset()).toString();

        url.setKey(key);
        url.setCreatedAt(LocalDateTime.now());
        // store in redis;
        redisTemplate.opsForValue().set(key, url, 36000L, TimeUnit.SECONDS);
        return url;
    }
}
