package com.zixue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zixue.sftp.util.SFTPClientUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpClientTest {

//	@Test
//	public void uploadFile() {
//		long start = System.currentTimeMillis();
//		boolean upload = SFTPClientUtils.upload("test/demo/win10/64/教育版",
//				"D:\\download\\app\\cn_windows_10_business_editions_version_1909_x64_dvd_0ca83907.iso");
//		long end = System.currentTimeMillis();
//		System.out.println("耗时：" + (end - start) + "ms");
//	}
//	@Test
//	public void delFile() {
//		long start = System.currentTimeMillis();
//		boolean upload = SFTPClientUtils.delete("test/demo/win10/64/教育版",
//				"cn_windows_10_business_editions_version_1909_x64_dvd_0ca83907.iso");
//		long end = System.currentTimeMillis();
//		System.out.println("耗时：" + (end - start) + "ms");
//	}
//	@Test
//	public void delDir() {
//		long start = System.currentTimeMillis();
//		boolean upload = SFTPClientUtils.deleteDir("test");
//		long end = System.currentTimeMillis();
//		System.out.println("耗时：" + (end - start) + "ms");
//	}

}
