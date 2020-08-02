
package com.zixue.web.session;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 功能描述:(提高缓存api)
 * @author houdo
 *
 */
public class CacheManager {
	// 存放缓存数据
	private Map<String, Cache> cacheMap = new HashMap<String, Cache>();

	public void put(String key, Object value) {
		put(key, value, null);
	}

	public synchronized void put(String key, Object value, Long timeout) {
		Cache cache = new Cache();
		cache.setKey(key);
		cache.setValue(value);
		if (timeout != null) {
			// 保存的是 整个毫秒数
			cache.setTimeout(System.currentTimeMillis() + timeout);
		}

		cacheMap.put(key, cache);
	}

	
	/**
	 * 功能描述:(删除api)
	 * @param key
	 */
	public synchronized void del(String key) {
		cacheMap.remove(key);
	}

	
	/**
	 * 功能描述:(使用key 查询缓存)
	 * @param key
	 * @return
	 */
	public synchronized Object get(String key) {
		Cache cache = cacheMap.get(key);
		if (cache != null) {
			return cache.getValue();
		}
		return null;
	}

	
	/**
	 * 功能描述:(删除)
	 * @param key
	 */
	public synchronized void remove(String key) {
		System.out.println("key:"+key+"删除成功....");
		cacheMap.remove(key);
	}

	
	/**
	 *  功能描述:(定时检查删除有效期的值)
	 */
	public synchronized void checkValidityData() {
		for (String key : cacheMap.keySet()) {
			Cache cache = cacheMap.get(key);
			if (cache == null) {
				break;
			}
			Long timeout = cache.getTimeout();
			Long currentTime = System.currentTimeMillis();
			// 说明 已经过期。
			if ((currentTime - timeout) > 0) {
				remove(key);
			}
		}
	}

	public static void main(String[] args) {
		final CacheManager cacheManager = new CacheManager();
		cacheManager.put("userName", "123", 5000l);
		System.out.println("保存成功...");
		//定期检查刷新数据... 	 开启一个线程，检查有效期...
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable() {
			public void run() {
				cacheManager.checkValidityData();
			}
		}, 5000, TimeUnit.MILLISECONDS);
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String userName = (String) cacheManager.get("userName");
		System.out.println("userName:" + userName);

	}

}
