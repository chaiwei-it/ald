package com.mood.user.service.impl;

import com.mood.entity.user.User;
import com.mood.user.service.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserCacheServiceImpl implements UserCacheService {

	@Autowired
	private RedisTemplate<String, User> redisTemplate;
	
	@Autowired
	private RedisTemplate<String, Integer> redisTemplateInt;
	
	@Autowired
	private RedisTemplate<String, Long> redisTemplateLong;
	
	@Value("${phonecodePre}")
	private String phonecodePre;//手机验证码前缀
	
	@Value("${phonecodeExpSeconds}")
	private String phonecodeExpSeconds;//验证码有效时间20分
	
	@Value("${phonenumPre}")
	private String phonenumPre;//手机获取验证码次数前缀
	
	@Value("${phonecodeTime}")
	private String phonecodeTime;//手机验证码获取时间前缀
	
	

//	@Override
//	public void set(final String key, final User member, final int exp) {
//		redisTemplate.execute(new RedisCallback<Object>() {
//			@SuppressWarnings("unchecked")
//			@Override
//			public Object doInRedis(RedisConnection connection) throws DataAccessException {
//				Jackson2JsonRedisSerializer<User> jackson2JsonRedisSerializer = (Jackson2JsonRedisSerializer<User>) redisTemplate
//						.getValueSerializer();
//				connection.set(redisTemplate.getStringSerializer().serialize(key),
//						jackson2JsonRedisSerializer.serialize(member));
//				if (exp!=0) {
//					connection.expire(redisTemplate.getStringSerializer().serialize(key), exp);
//				}
//				return null;
//			}
//		});
//	}
//
//	@Override
//	public User get(final String key) {
//		return redisTemplate.execute(new RedisCallback<User>() {
//			@SuppressWarnings("unchecked")
//			@Override
//			public User doInRedis(RedisConnection connection) throws DataAccessException {
//				Jackson2JsonRedisSerializer<User> jackson2JsonRedisSerializer = (Jackson2JsonRedisSerializer<User>) redisTemplate
//						.getValueSerializer();
//				return jackson2JsonRedisSerializer.deserialize(connection.get(redisTemplate.getStringSerializer().serialize(key)));
//			}
//		});
//	}
//
//	@Override
//	public boolean exists(final String key) {
//		return redisTemplate.execute(new RedisCallback<Boolean>() {
//			@Override
//			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
//				return connection.exists(redisTemplate.getStringSerializer().serialize(key));
//			}
//		});
//	}
//
//	@Override
//	public void del(final String key) {
//		redisTemplate.execute(new RedisCallback<Long>() {
//			@Override
//			public Long doInRedis(RedisConnection connection) throws DataAccessException {
//				return connection.del(redisTemplate.getStringSerializer().serialize(key));
//			}
//		});
//	}

	
	@Override
	public boolean existsPhonceCode(final String phone) {
		
		final String key=phonecodePre+phone;
		return redisTemplateInt.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(redisTemplateInt.getStringSerializer().serialize(key));
			}
		});
	}
	
	@Override
	public Integer getPhoneCode(String userId) {
		// TODO Auto-generated method stub
		final String key=phonecodePre+userId;
		
		return redisTemplateInt.execute(new RedisCallback<Integer>() {
			@Override
			public Integer doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				@SuppressWarnings("unchecked")
                Jackson2JsonRedisSerializer<Integer> jackson2JsonRedisSerializer = (Jackson2JsonRedisSerializer<Integer>) redisTemplateInt
						.getValueSerializer();
				return jackson2JsonRedisSerializer.deserialize(connection.get(redisTemplateInt.getStringSerializer().serialize(key)));
			}
		});
	}

	
	
	@Override
	public void savePhoneCode(final String userId,final Integer phonecode) {
		// TODO Auto-generated method stub
		final String codekey=phonecodePre+userId;
		final String numkey=phonenumPre+userId;
		final String timekey=phonecodeTime+userId;
		
		redisTemplateInt.execute(new RedisCallback<Object>() {
			@SuppressWarnings("unchecked")
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = (Jackson2JsonRedisSerializer<Object>) redisTemplateInt
						.getValueSerializer();
				
				//保存验证码到redis
				connection.set(redisTemplateInt.getStringSerializer().serialize(codekey),
						jackson2JsonRedisSerializer.serialize(phonecode));
				
				long codeExpSeconds=Long.parseLong(phonecodeExpSeconds);
				
				//设置验证码有效时间
				connection.expire(redisTemplateInt.getStringSerializer().serialize(codekey), codeExpSeconds);
				
				//设置验证码创建时间
				connection.set(redisTemplateInt.getStringSerializer().serialize(timekey), 
						jackson2JsonRedisSerializer.serialize(Calendar.getInstance().getTimeInMillis()));
				//设置验证码创建时间的有效时间
				connection.expire(redisTemplateInt.getStringSerializer().serialize(timekey), codeExpSeconds);
				
				//设置手机号码接收短信次数
				if(connection.exists(redisTemplateInt.getStringSerializer().serialize(numkey))){
					connection.incr(redisTemplateInt.getStringSerializer().serialize(numkey));
				}else{
					connection.set(redisTemplateInt.getStringSerializer().serialize(numkey), 
							jackson2JsonRedisSerializer.serialize(1));
				}
				
				//设置手机号码接收短信次数的有效时间
			    Calendar cal = Calendar.getInstance();    
			    cal.add(Calendar.DATE, 1);    
			    cal.set(Calendar.SECOND, 0);    
			    cal.set(Calendar.MINUTE, 0);    
			    cal.set(Calendar.HOUR, 0);    
			    //cal.getTime();    
			    long unixTime=cal.getTimeInMillis();
			    		
				connection.expireAt(redisTemplateInt.getStringSerializer().serialize(numkey), 
						unixTime);//设置有效期到明天0时0分0秒
				
				return null;
			}
		});
		
	}

	@Override
	public Integer getPhoneNum(String userId) {
		// TODO Auto-generated method stub
		final String key=phonenumPre+userId;
		
		return redisTemplateInt.execute(new RedisCallback<Integer>() {
			@Override
			public Integer doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				@SuppressWarnings("unchecked")
                Jackson2JsonRedisSerializer<Integer> jackson2JsonRedisSerializer = (Jackson2JsonRedisSerializer<Integer>) redisTemplateInt
						.getValueSerializer();
				return jackson2JsonRedisSerializer.deserialize(connection.get(redisTemplateInt.getStringSerializer().serialize(key)));
			}
		});
	}

	@Override
	public Long getPhoneTime(String phone) {
		// TODO Auto-generated method stub
		final String key=phonecodeTime+phone;
		
		return redisTemplateLong.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				@SuppressWarnings("unchecked")
                Jackson2JsonRedisSerializer<Long> jackson2JsonRedisSerializer = (Jackson2JsonRedisSerializer<Long>) redisTemplateLong
						.getValueSerializer();
				return jackson2JsonRedisSerializer.deserialize(connection.get(redisTemplateLong.getStringSerializer().serialize(key)));
			}
		});
	}

	@Override
	public boolean existsPhonceNum(String phone) {
		// TODO Auto-generated method stub
		final String key=phonenumPre+phone;
		return redisTemplateInt.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(redisTemplateInt.getStringSerializer().serialize(key));
			}
		});
	}

	@Override
	public boolean existsPhonceTime(String phone) {
		// TODO Auto-generated method stub
		final String key=phonecodeTime+phone;
		return redisTemplateLong.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(redisTemplateLong.getStringSerializer().serialize(key));
			}
		});
	}
	
//	public static void main(String[] args) {
//	    Calendar cal = Calendar.getInstance();    
//	    cal.add(Calendar.DATE, 1);    
//	    cal.set(Calendar.SECOND, 0);    
//	    cal.set(Calendar.MINUTE, 0);    
//	    cal.set(Calendar.HOUR, 0);    
//	    //cal.getTime();    
//	    Date date=cal.getTime();
//	    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
//	    System.out.println(simpleDateFormat.format(date));
//		Calendar cal = Calendar.getInstance();
//		long a=cal.getTimeInMillis();
//		
//		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd HHmmss");
//		Date date;
//		try {
//			date = simpleDateFormat.parse("20161220 191100");
//			System.out.println(date.getTime()-a);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//	}

}
