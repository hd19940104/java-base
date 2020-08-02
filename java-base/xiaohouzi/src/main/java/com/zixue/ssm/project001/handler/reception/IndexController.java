package com.zixue.ssm.project001.handler.reception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zixue.ssm.project001.bean.VideoInfo;
import com.zixue.ssm.project001.bean.VideoType;
import com.zixue.ssm.project001.service.VideoInfoService;
import com.zixue.ssm.project001.service.VideoTypeService;



/**
 *  功能描述:(前台展示数据)
 * @author houdo
 *
 */
@Controller("/")
public class IndexController {
	private static final String INDEX = "index";
	private static final String INDEXVIDEODETAILS = "indexVideoDetails";
	@Autowired
	private VideoTypeService videoTypeService;
	@Autowired
	private VideoInfoService videoInfoService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		//查询所有类型
		List<VideoType> listShowVideoType = videoTypeService.showVideoType(null);
		request.setAttribute("listShowVideoType", listShowVideoType);
		List<VideoInfo> listVideoInfo = videoInfoService.getVideoInfo();
		request.setAttribute("listVideoInfo", listVideoInfo);
		return INDEX;
	}
	
	@RequestMapping("/indexVideoDetails")
	public String indexVideoDetails(HttpServletRequest request,int id){
		VideoInfo videoInfo = videoInfoService.getVideoInfo(id);
		request.setAttribute("videoInfo", videoInfo);
		return INDEXVIDEODETAILS;
	}
}
