package com.zixue.sftp.config;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;


/**
 *  * SFTP工厂类，用于获取SFTP的连接  *
 */

@Component
public class SFTPConnectionFactory {

	private static Logger LOGGER = LoggerFactory.getLogger(SFTPConnectionFactory.class);

	private static final SFTPConnectionFactory factory = new SFTPConnectionFactory();
	private ChannelSftp client;
	private Session session;

	private SFTPConnectionFactory() {

	}

	public static SFTPConnectionFactory getInstance() {
		return factory;
	}

	@Autowired
	private SFTPProperties sftpProperties;
	
	private static String username;
	private static String privateKey;
	private static String password;
	private static String ip;
	private static int port;

	/**
	 * 初始化设置
	 * 
	 * @return
	 */
	@PostConstruct
	public void init() {
		username = sftpProperties.getUsername();
		privateKey = sftpProperties.getPrivateKey();
		password = sftpProperties.getPassword();
		ip = sftpProperties.getIp();
		port = sftpProperties.getPort();
	}

	synchronized public ChannelSftp makeConnection() {

		if (client == null || session == null || !client.isConnected() || !session.isConnected()) {
			try {
				LOGGER.info("username:" + username + "&ip:" + ip + "&port:" + port + "&password:" + password);
				JSch jsch = new JSch();
				if (privateKey != null&& !"".equals(privateKey)) {
					jsch.addIdentity(privateKey);// 设置私钥
				}
				session = jsch.getSession(username, ip, port);
				if (password != null) {
					session.setPassword(password);
				}
				Properties config = new Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig(config);
				session.connect();
				Channel channel = session.openChannel("sftp");
				channel.connect();
				client = (ChannelSftp) channel;
				LOGGER.info("sftp服务器连接成功");
			} catch (JSchException e) {
				LOGGER.error("sftp登录失败，检测登录ip，端口号，用户名密码是否正确，错误信息为" + e.getMessage());
			}
		}

		return client;
	}

	/**
	 *         * 关闭连接 server         
	 */

	public void logout() {
		if (client != null) {
			if (client.isConnected()) {
				client.disconnect();
			}
		}
		if (session != null) {
			if (session.isConnected()) {
				session.disconnect();
			}
		}
	}

}