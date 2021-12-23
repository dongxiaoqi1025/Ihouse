package edu.nuc.ihouse_01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

@SpringBootApplication
//开启缓存
@EnableCaching
public class Ihouse01Application {
    //redis的操作模板类
    @Autowired
    private RedisTemplate redisTemplate;
    //获取redis连接的工场
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    //初始化RedisTemplate 设置RedisTemplate序列化
    private void initRedisTemplate(){
        RedisSerializer redisSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
    }
    //spring初始化完成之后调用的方法
    @PostConstruct
    public void init(){
        initRedisTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Ihouse01Application.class, args);
    }

}
