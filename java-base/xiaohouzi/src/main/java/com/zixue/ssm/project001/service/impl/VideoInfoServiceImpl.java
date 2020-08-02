
package com.zixue.ssm.project001.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zixue.ssm.project001.bean.VideoInfo;
import com.zixue.ssm.project001.mapper.VideoInfoMapper;
import com.zixue.ssm.project001.service.VideoInfoService;
	

@Service
public class VideoInfoServiceImpl implements VideoInfoService {
	@Autowired
	private VideoInfoMapper videoInfoMapper;

	public List<VideoInfo> getVideoInfo() {
		return videoInfoMapper.selectAll();

	}

	public int addVideoInfo(VideoInfo record) {
		return videoInfoMapper.insert(record);

	}

	public VideoInfo getVideoInfo(int id) {
		return videoInfoMapper.selectByPrimaryKey(id);

	}

}
