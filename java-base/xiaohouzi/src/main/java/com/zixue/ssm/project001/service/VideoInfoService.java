
package com.zixue.ssm.project001.service;

import java.util.List;
import java.util.Map;

import com.zixue.ssm.project001.bean.VideoInfo;




/**
 * 功能描述:(视频查询)
 * @author houdo
 *
 */
public interface VideoInfoService {

	public List<VideoInfo> getVideoInfo();

	public int addVideoInfo(VideoInfo record);
	
	public VideoInfo getVideoInfo(int id);
}
