
package com.zixue.web.session;


/**
 * 功能描述:(缓存实体类)
 * @author houdo
 *
 */
public class Cache {

	// key
	private String key;
	// 值
	private Object value;
	//有效期
	private Long timeout;
	public String getKey() {
	
		return key;
	}
	public void setKey(String key) {
	
		this.key = key;
	}
	public Object getValue() {
	
		return value;
	}
	public void setValue(Object value) {
	
		this.value = value;
	}
	public Long getTimeout() {
	
		return timeout;
	}
	public void setTimeout(Long timeout) {
	
		this.timeout = timeout;
	}
	
	
	
	
	
}
