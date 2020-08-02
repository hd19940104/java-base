package com.zixue.ssm.project001.mapper;

import java.util.List;

import com.zixue.ssm.project001.bean.VideoType;

public interface VideoTypeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(VideoType record);

	int insertSelective(VideoType record);

	VideoType selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(VideoType record);

	int updateByPrimaryKey(VideoType record);

	List<VideoType> selectList(VideoType record);
}