package com.zixue.hdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import com.zixue.util.CommUtil;
import com.zixue.util.HDFSUtil;

public class HdfsDemo {
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HdfsDemo.class);
	/**
	 * 查询目录
	 * 
	 * @param fileSystem
	 * @param src
	 * @param srcFile
	 * @param dist
	 * @param distFile
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public void getListFiles(FileSystem fileSystem, String src, String srcFile, String dist, String distFile)
			throws FileNotFoundException, IllegalArgumentException, IOException {
		// 查询目录
		RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path(distFile), true);
		while (listFiles.hasNext()) {
			logger.info("-----------------------------------");
			LocatedFileStatus next = listFiles.next();
//			logger.info("地址：" + next.getPath());
//			logger.info("拥有者：" + next.getOwner());
//			logger.info("块大小：" + next.getBlockSize() / (1024 * 1024) + "M");
//			logger.info("总大小：" + next.getLen() / (1024 * 1024) + "M");
//			logger.info("创建时间：" + CommUtil.timeStamp2Date(next.getModificationTime() + "", null));
			logger.info("地址：" + next.getPath());
			logger.info("拥有者：" + next.getOwner());
			logger.info("块大小：" + next.getBlockSize() / (1024 * 1024) + "M");
			logger.info("总大小：" + next.getLen() / (1024 * 1024) + "M");
			logger.info("创建时间：" + CommUtil.timeStamp2Date(next.getModificationTime() + "", null));
			// 文件块的具体信息
			BlockLocation[] blockLocations = next.getBlockLocations();
			for (BlockLocation blockLocation : blockLocations) {
				logger.info("块开始位置:" + blockLocation.getOffset() / (1024 * 1024) + "--->");
				String[] hosts = blockLocation.getHosts();
				for (String string : hosts) {
					logger.info("host:" + string + "|");
				}
				logger.info("");

			}
			logger.info("-----------------------------------");
		}

		FileStatus[] listStatus = fileSystem.listStatus(new Path(distFile));
		for (FileStatus fileStatus : listStatus) {
			logger.info(fileStatus.toString());
			if (fileStatus.isFile()) {
				logger.info(fileStatus.getPath());
			}
		}
	}

	/**
	 * 测试
	 */
	@Test
	public void Test() {
		// 获取hdfs客户端
		FileSystem fileSystem = HDFSUtil.getFileSystemCon();
		try {
			Configuration conf = new Configuration();
			// 创建目录
			String dist = File.separator + "user" + File.separator + "hadoop" + File.separator + "hadoop-hdfs-demo1001";
			String src = "C:" + File.separator + "Users" + File.separator + "houdo" + File.separator + "Desktop";
			String srcFile = "C:" + File.separator + "Users" + File.separator + "houdo" + File.separator + "Desktop"
					+ File.separator + "jdk-8u191-linux-x64.tar.gz";
			String distFile = dist + File.separator + srcFile.substring(srcFile.lastIndexOf(File.separator));

			if (fileSystem.exists(new Path(dist))) {
				logger.info("dist is exit");
			} else {
				fileSystem.mkdirs(new Path(dist));
			}
			if (new java.io.File(srcFile).exists()) {
				// 上传文件
				// fileSystem.copyFromLocalFile(true, new Path(srcFile), new Path(dist));
				putFileFromLocal(conf, fileSystem, src, srcFile, dist, distFile, false);
				boolean exists = fileSystem.exists(new Path(distFile));
				if (exists) {
					logger.info("文件上传成功");
					getListFiles(fileSystem, src, srcFile, dist, distFile);
				} else {
					logger.info("文件上传失败");
				}

			} else {
				logger.info("srcFile is not exit");

				if (fileSystem.exists(new Path(distFile))) {
					// fileSystem.copyToLocalFile(false, new Path(distFile), new Path(src), true);
					//getSeek(conf, fileSystem, src, srcFile, dist, distFile, false, 1);
					//getSeek(conf, fileSystem, src, srcFile, dist, distFile, false, 2);
					getFileFromLocal(conf, fileSystem, src, srcFile, dist, distFile, false, 0);

					// 查询目录
					getListFiles(fileSystem, src, srcFile, dist, distFile);
				}
				if (new File(srcFile).exists()) {
					logger.info("文件下载成功");
					// 删除hdfs上面文件
					fileSystem.deleteOnExit(new Path(distFile));
					if (fileSystem.exists(new Path(distFile))) {
						logger.info("文件删除成功");
					}
				} else {
					logger.info("文件下载失败");
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (fileSystem != null) {
				try {
					fileSystem.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			logger.info("文件系统关闭");

		}

	}

	/**
	 * 文件系统下载到本地
	 * 
	 * @param conf
	 * @param fileSystem
	 * @param src
	 * @param srcFile
	 * @param dist
	 * @param distFile
	 * @param isClose
	 */
	public void putFileFromLocal(Configuration conf, FileSystem fileSystem, String src, String srcFile, String dist,
			String distFile, boolean isClose) {

		// 创建输入流
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new java.io.FileInputStream(srcFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 创建输出流
		FSDataOutputStream fsDataOutputStream = null;
		try {
			fsDataOutputStream = fileSystem.create(new Path(distFile));
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		// 流的拷贝
		try {
			IOUtils.copyBytes(fileInputStream, fsDataOutputStream, conf);
			logger.info("上传成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (new java.io.File(srcFile).exists()) {
			new File(srcFile).deleteOnExit();
			logger.info("文件删除成功");
		}

		if (isClose) {
			try {
				if (fileSystem != null) {
					fileSystem.close();
				}
				logger.info("文件系统关闭");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 文件下载
	 * 
	 * @param conf
	 * @param fileSystem
	 * @param src
	 * @param srcFile
	 * @param dist
	 * @param distFile
	 * @param isClose
	 * @param num
	 */
	public void getFileFromLocal(Configuration conf, FileSystem fileSystem, String src, String srcFile, String dist,
			String distFile, boolean isClose, int num) {
		FSDataInputStream fsDataInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fsDataInputStream = fileSystem.open(new Path(distFile));
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		try {
			fileOutputStream = new java.io.FileOutputStream(new File(srcFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			IOUtils.copyBytes(fsDataInputStream, fileOutputStream, conf);
			logger.info("下载成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (isClose) {
			try {
				if (fileSystem != null) {
					fileSystem.close();

				}
				logger.info("文件系统关闭");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 下载块
	 * 
	 * @param conf
	 * @param fileSystem
	 * @param src
	 * @param srcFile
	 * @param dist
	 * @param distFile
	 * @param isClose
	 * @param num
	 */
	public void getSeek(Configuration conf, FileSystem fileSystem, String src, String srcFile, String dist,
			String distFile, boolean isClose, int num) {
		FSDataInputStream fsDataInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fsDataInputStream = fileSystem.open(new Path(distFile));
		} catch (IllegalArgumentException | IOException e) {

			e.printStackTrace();
		}
		try {
			fileOutputStream = new java.io.FileOutputStream(new File(srcFile + ".part" + num));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		try {
			/*
			 * byte[] buf = new byte[1024]; for (int i = 1024 * 128 * (num - 1); i < 1024 *
			 * 128 * (num); i++) { fsDataInputStream.read(buf); fileOutputStream.write(buf);
			 * }
			 */
			fsDataInputStream.seek(1024 * 1024 * 128 * (num - 1));
			IOUtils.copyBytes(fsDataInputStream, fileOutputStream, conf);
			logger.info("下载成功");
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (fsDataInputStream != null) {
			IOUtils.closeStream(fsDataInputStream);
		}
		if (fileOutputStream != null) {
			IOUtils.closeStream(fileOutputStream);
		}

		if (isClose) {
			try {
				if (fileSystem != null) {
					fileSystem.close();

				}
				logger.info("文件系统关闭");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

}
