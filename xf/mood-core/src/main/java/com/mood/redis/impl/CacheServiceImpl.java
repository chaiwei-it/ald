package com.mood.redis.impl;

import com.mood.redis.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService<String, String> {

	@Autowired
	private RedisTemplate<String, String> simpleRedisTemplate;
	
	@Override
	public String get(final String key) {
		String value = simpleRedisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				@SuppressWarnings("unchecked")
                Jackson2JsonRedisSerializer<String> jackson2JsonRedisSerializer = (Jackson2JsonRedisSerializer<String>) simpleRedisTemplate
						.getValueSerializer();
				return jackson2JsonRedisSerializer.deserialize(connection.get(simpleRedisTemplate.getStringSerializer().serialize(key)));
			}
		});
		return value;
	}

	@Override
	public void set(final String key, final String value, final int exp) {
		simpleRedisTemplate.execute(new RedisCallback<Object>() {
			@SuppressWarnings("unchecked")
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				Jackson2JsonRedisSerializer<String> jackson2JsonRedisSerializer = (Jackson2JsonRedisSerializer<String>) simpleRedisTemplate
						.getValueSerializer();
				connection.set(simpleRedisTemplate.getStringSerializer().serialize(key),
						jackson2JsonRedisSerializer.serialize(value));
				if (exp!=0) {
					connection.expire(simpleRedisTemplate.getStringSerializer().serialize(key), exp);
				}
				return null;
			}
		});
	}

	@Override
	public boolean exists(final String key) {
		return simpleRedisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(simpleRedisTemplate.getStringSerializer().serialize(key));
			}
		});
	}

	@Override
	public void del(final String key) {
		simpleRedisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.del(simpleRedisTemplate.getStringSerializer().serialize(key));
			}
		});
	}

}
