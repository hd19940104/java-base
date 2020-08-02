package com.zixue.sftp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author 一只会飞的小猴子
 */
@Data
@Component
@ConfigurationProperties(prefix = "sftp")
public class SFTPProperties {

	private String protocol;

	private String ip;

	private Integer port;

	private String username;

	private String password;
	/**
	 * # 密钥文件路径
	 */
	private String root;
	/**
	 * 密钥文件路径
	 */
	private String privateKey;
	/**
	 * # 密钥的密码
	 */
	private String passphrase;
	private String sessionStrictHostKeyChecking;
	/**
	 * #文件下载失败下次超时重试时间
	 */
	private Integer downloadSleep;
	/**
	 * #文件下载失败重试次数
	 */
	private Integer downloadRetry;
	/**
	 * #文件上传失败下次超时重试时间
	 */
	private Integer uploadSleep;
	/**
	 * #文件上传失败重试次数
	 */
	private Integer uploadRettry;
}
