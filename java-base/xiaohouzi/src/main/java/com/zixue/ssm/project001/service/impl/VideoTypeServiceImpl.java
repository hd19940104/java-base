
package com.zixue.ssm.project001.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zixue.ssm.project001.bean.VideoType;
import com.zixue.ssm.project001.mapper.VideoTypeMapper;
import com.zixue.ssm.project001.service.VideoTypeService;



@Service
public class VideoTypeServiceImpl implements VideoTypeService {
	@Autowired
	private VideoTypeMapper videoTypeMapper;

	public List<VideoType> showVideoType(VideoType record) {
		return videoTypeMapper.selectList(record);
	}
}
