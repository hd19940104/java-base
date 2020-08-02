package com.zixue.socket.base.udp;
/**
 * 
 * @ClassName  MessageCreate 
 * @Description 消息创建者
 * @author 一只会飞的小猴子
 * @date  2019年8月13日 上午10:00:20 
 *
 */
public class MessageCreate {
	
	private static final String SN_HEADER="收到暗号，我是(SN)";
	private static final String PORT_HEADER="收到暗号，请回电端口(Port)";
	/**
	 * 
	 * @Title  buildWithPost 
	 * @Description  创建端口
	 * @param port
	 * @return String
	 */
	public static String buildWithPost(int port) {
		return PORT_HEADER+port;
	}
	/**
	 * 
	 * @Title  parsePort 
	 * @Description  解析端口
	 * @param data
	 * @return int
	 */
	public static int parsePort(String data) {
		if (data.startsWith(PORT_HEADER)) {
			return Integer.parseInt(data.substring(PORT_HEADER.length()));
		}
		return  -1;
	}
	/**
	 * 
	 * @Title  buildWithSn 
	 * @Description  创建sn
	 * @param sn
	 * @return String
	 */
	public static String buildWithSn(String sn) {
		
		return SN_HEADER+sn;
	}
	/**
	 * 
	 * @Title  parseSn 
	 * @Description  解析sn
	 * @param data
	 * @return String
	 */
	public static String parseSn(String data) {
		if (data.startsWith(SN_HEADER)) {
			return data.substring(SN_HEADER.length());
		}
		return null;
	}
}
