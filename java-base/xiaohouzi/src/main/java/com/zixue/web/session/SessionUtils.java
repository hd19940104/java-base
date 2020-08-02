package com.zixue.web.session;
/**
 *  Session 缓存
 * @author houdo
 *
 */
public class SessionUtils {
	private CacheManager cacheManager = new CacheManager();;
	/**
	 * 初始化cacheManager
	 */
	public void init() {
		if (cacheManager!=null) {
			cacheManager =new CacheManager();
		}
	}
	/**
	 * 新增一个session 返回sessionid
	 * @param key
	 * @param value
	 */
	public String setAttribute(Object value) {
		//init();防止线程安全问题
		String sessionId = TokenUtils.getToken();
		cacheManager.put(sessionId, value);
		return sessionId;
	}
	/**
	 * 获取 value
	 * @param key
	 * @return
	 */
	public Object getAttribute(String key) {
		
		return cacheManager.get(key);
	}
	
}
