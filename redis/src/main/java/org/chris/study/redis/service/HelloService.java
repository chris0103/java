package org.chris.study.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void hello() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("k3", "v3");
        Object k1 = ops.get("k3");
        System.out.println(k1);
    }

    public void hello2() {
        ValueOperations ops = stringRedisTemplate.opsForValue();
        ops.set("k4", "v4");
        Object k1 = ops.get("k4");
        System.out.println(k1);
    }
}
