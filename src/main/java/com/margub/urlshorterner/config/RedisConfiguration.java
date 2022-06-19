package com.margub.urlshorterner.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.margub.urlshorterner.entities.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /*
    By default the auto configured RedisTemplate provided by Sprint Boot works well with a String based Key/Value pair.
     However, in this post we will store Key as a String and Value as a JSON object.

{
    "id": "5446d139",
    "url": "https://www.codeprimers.com/spring-boot-url-shortener",
    "created": "2019-02-18T13:29:14.449"
}

In order to do so, we need to configure our own version of RedisTemplate as shown below.
     */
    @Bean
    RedisTemplate<String, Url> redisTemplate() {
        final RedisTemplate<String, Url> redisTemplate = new RedisTemplate<>();
        Jackson2JsonRedisSerializer<Url> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Url.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // key is string; so StringRedisSerilizer
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // VAlue is Json; Jackson2JsonRedisSerializer
        return redisTemplate;
    }
}
