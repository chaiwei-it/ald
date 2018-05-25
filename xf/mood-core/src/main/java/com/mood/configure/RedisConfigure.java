package com.mood.configure;

import com.mood.entity.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfigure {

	@Bean
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory) {
       RedisTemplate<String, User> redisTemplate = new RedisTemplate<String, User>();
       redisTemplate.setConnectionFactory(factory);
       redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
       return redisTemplate;
    }
	
	@Bean(name="redisTemplateInt")
    public RedisTemplate<String, Integer> redisTemplateInt(RedisConnectionFactory factory) {
       RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<String, Integer>();
       redisTemplate.setConnectionFactory(factory);
       redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Integer>(Integer.class));
       return redisTemplate;
    }
	
	@Bean(name="redisTemplateLong")
    public RedisTemplate<String, Long> redisTemplateLong(RedisConnectionFactory factory) {
       RedisTemplate<String, Long> redisTemplate = new RedisTemplate<String, Long>();
       redisTemplate.setConnectionFactory(factory);
       redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Long>(Long.class));
       return redisTemplate;
    }
	
	@Bean(name="simpleRedisTemplate")
    public RedisTemplate<String, String> simpleRedisTemplate(RedisConnectionFactory factory) {
       RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
       redisTemplate.setConnectionFactory(factory);
       redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<String>(String.class));
       return redisTemplate;
    }
	
	
	
}
