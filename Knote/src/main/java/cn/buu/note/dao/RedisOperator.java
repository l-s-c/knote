package cn.buu.note.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
/**
 * redis操作工具类
 * @author ABC
 *
 */
@Component
public class RedisOperator {
	@Autowired
	private StringRedisTemplate strRedis;
	
	public String get(String key) {
		String s = strRedis.opsForValue().get(key);
		return s;
	}
}
