package com.zixue.web.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author Administrator
 *
 */
public class HttpClientDemo {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		doGet();
	}

	/**
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * 
	 */
   static	public void doGet() throws ClientProtocolException, IOException {
		// 创建一个 默认httpclient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.baidu.com/");
		// 执行http请求
		CloseableHttpResponse response = httpClient.execute(httpGet);
		// 获取状态码
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode==200){
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity,"UTF-8"));
		}
		response.close();
		
	}

}
