package com.tl.live.service;

import com.tl.common.redis.IMCacheKeyBuilder;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@Service
public class IMTokenService {

    @Resource
    private IMCacheKeyBuilder imCacheKeyBuilder;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public String generateIMToken(String userId){
        String imToken = generateRandomString(8);
        String tokenKey = imCacheKeyBuilder.buildIMTokenCacheKey(imToken);
        redisTemplate.opsForValue().set(tokenKey,userId);
        //这个Token只在获取WS的URL和建立WS连接两个操作之间使用，过期时间不用很长
        redisTemplate.expire(tokenKey,10, TimeUnit.SECONDS);
        return imToken;
    }

    public boolean checkIMToken(String imToken){
        String tokenKey = imCacheKeyBuilder.buildIMTokenCacheKey(imToken);
        Object imTokenRecord = redisTemplate.opsForValue().get(tokenKey);
        if(null == imTokenRecord){
            return false;
        }
        return true;
    }

    private String generateRandomString(int length){
        String result = "";
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < length; i++) {
            result += letters.charAt(ThreadLocalRandom.current().nextInt(0,letters.length()));
        }

        return result;
    }
}
