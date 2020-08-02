package com.zixue.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * 
 * @ClassName  NetHttpUtil 
 * @Description 基于java net工具包实现的网络访问工具类
 * @author 一只会飞的小猴子
 * @date  2019年8月8日 下午6:11:56 
 *
 */
public class NetHttpUtil {

	/**
	 * 
	 * @Title  getHttpContent 
	 * @Description   发起GET请求 获取网络内容 并转成字符串进行返回（字符串：后面无论是解析dom或者结果是json字符串 都比较方便进行转换）
	 * @param url
	 * @return String
	 */
  public static String getHttpContent(String url){
      HttpURLConnection http = null;
      InputStream is = null;
      try {
          URL urlGet = new URL(url);
          http = (HttpURLConnection) urlGet.openConnection();
          http.setRequestMethod("GET");
          http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
          http.setDoOutput(true);
          http.setDoInput(true);
          System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
          System.setProperty("sun.net.client.defaultReadTimeout", "30000");
          http.connect();
          is =http.getInputStream();
          int size =is.available();
          byte[] jsonBytes =new byte[size];
          is.read(jsonBytes);
          String message=new String(jsonBytes,"UTF-8");
          return message;
      } catch (Exception e) {
          return null;
      }finally {
          if(null != http) http.disconnect();
          try {
              if (null != is) is.close();
          }catch (IOException e){
              e.printStackTrace();
          }
      }
  }

  /**
   * 
   * @Title  postHttpContent 
   * @Description  发起POST请求 获取网络内容 并转成字符串进行返回（字符串：后面无论是解析dom或者结果是json字符串 都比较方便进行转换）
   * @param url
   * @param data
   * @return String
   */
  public static String postHttpContent(String url,String data){
      HttpURLConnection http = null;
      PrintWriter out = null;
      BufferedReader reader = null;
      try {
          //创建连接
          URL urlPost = new URL(url);
          http = (HttpURLConnection) urlPost
                  .openConnection();
          http.setDoOutput(true);
          http.setDoInput(true);
          http.setRequestMethod("POST");
          http.setUseCaches(false);
          http.setInstanceFollowRedirects(true);
          http.setRequestProperty("Content-Type",
                  "application/x-www-form-urlencoded");
          http.connect();
          //POST请求
          OutputStreamWriter outWriter = new OutputStreamWriter(http.getOutputStream(), "utf-8");
          out = new PrintWriter(outWriter);
          out.print(data);
          out.flush();
          out.close();
          out = null;
          //读取响应
          reader = new BufferedReader(new InputStreamReader(
                  http.getInputStream()));
          String lines;
          StringBuffer sb = new StringBuffer("");
          while ((lines = reader.readLine()) != null) {
              lines = new String(lines.getBytes(), "utf-8");
              sb.append(lines);
          }
          reader.close();
          reader = null;
          System.out.println(sb.toString());
          return sb.toString();
      } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          return null;
      }finally {
          if(null != http) http.disconnect();
          if(null != out) out.close();
          try{
              if(null != reader) reader.close();
          }catch (IOException e){
              e.printStackTrace();
          }
      }
  }
}
