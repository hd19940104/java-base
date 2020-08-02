
package com.zixue.ssm.project001.service;

import java.util.List;

import com.zixue.ssm.project001.bean.VideoType;



/**
 * 功能描述:(视频类型Service)
 * @author houdo
 *
 */
public interface VideoTypeService {
	
	/**
	 *  功能描述:(查询视频类型)
	 * @param record
	 * @return
	 */
	public List<VideoType> showVideoType(VideoType record);

}
