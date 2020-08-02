package com.zixue.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
/**
 * hdfs工具
 * @author houdo
 *
 */
public class HDFSUtil {
	/**
	 * 描述：获取连接 hdfs
	 * 
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static FileSystem getFileSystemCon() {
		// 获取配置信息
		Configuration configuration = new Configuration();
		// 获取文件系统
		FileSystem fs = null;
		try {
			fs = FileSystem.get(new URI(ConfigurationUtil.getString("hdfs.fs-defaultFS", "hdfs://hadoop108:8020")),
					configuration, ConfigurationUtil.getString("username", "hadoop"));
		} catch (IOException | InterruptedException | URISyntaxException e) {
			e.printStackTrace();
		}
		if (fs != null) {
			System.out.println("文件系统连接成功...");
		} else {
			System.out.println("文件系统连接失败...");
		}

		return fs;

	}
}
