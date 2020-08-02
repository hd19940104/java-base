
package com.zixue.ssm.project001.handler.backstage;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zixue.ssm.project001.bean.VideoInfo;
import com.zixue.ssm.project001.bean.VideoType;
import com.zixue.ssm.project001.service.VideoInfoService;
import com.zixue.ssm.project001.service.VideoTypeService;
import com.zixue.util.config.ConfigurationUtil;
import com.zixue.util.img.Base64Img;



/**
 * 功能描述:(查询视频)
 * @author houdo
 *
 */
@Controller()
public class VideoInfoController {
	private static Logger log = Logger.getLogger(VideoInfoController.class);
	@Autowired
	private VideoInfoService videoInfoService;
	@Autowired
	private VideoTypeService videoTypeService;
	private static final String BASE = "backstage/";
	// 视频管理页面
	private static final String VIDEOMANAG = BASE + "videoManag";
	private static final String LOCAVIDEO = BASE + "addVideo";
	private static final String VIDEODETAILS = BASE + "videoDetails";

	
	/**
	 * 功能描述:(视频首页)
	 * @param request
	 * @return
	 */
	@RequestMapping("/videoManag")
	public String videoManag(HttpServletRequest request) {
		List<VideoInfo> listVideoInfo = videoInfoService.getVideoInfo();
		request.setAttribute("listVideoInfo", listVideoInfo);
		return VIDEOMANAG;
	}

	
	/**
	 * 功能描述:(添加视频)
	 * @param request
	 * @return
	 */
	@RequestMapping("/locaAddVideo")
	public String locaVideo(HttpServletRequest request) {
		List<VideoType> listVideoType = videoTypeService.showVideoType(null);
		request.setAttribute("listVideoType", listVideoType);
		return LOCAVIDEO;
	}


	/**
	 * 功能描述:(视频详情)
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/videoDetails")
	public String videoDetails(int id, HttpServletRequest request) {
		VideoInfo videoInfo = videoInfoService.getVideoInfo(id);
		request.setAttribute("videoInfo", videoInfo);
		return VIDEODETAILS;
	}


	/**
	 * 功能描述:(添加视频元素)
	 * @param file
	 * @param videoInfo
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/addVideo")
	public String addVideo(@RequestParam(value = "file", required = false) MultipartFile file, VideoInfo videoInfo,
			HttpServletRequest req, HttpServletResponse res) {
		try {
			String file_path = ConfigurationUtil.getString("video.file_path");
			System.out.println("----------------"+file_path);
			System.out.println(ConfigurationUtil.getString("system.username"));
			// 获取当前上下文
			//String path = req.getSession().getServletContext().getRealPath("/static/imgs");
			String path =file_path;
			// 文件名称
			String newName = System.currentTimeMillis() + ".png";
			File targetFile = new File(path, newName);
			// 文件夹不存在,则创建文件夹
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				log.error(e);
			}
			String getImageStr = Base64Img.GetImageStr(file_path+File.separator+newName);
			System.out.println(path);
			videoInfo.setcontent(getImageStr);
			videoInfo.setVideoUrl("static/imgs/"+newName);
			videoInfoService.addVideoInfo(videoInfo);
			req.setAttribute("result", "封面上传成功!");
			return "redirect:/videoManag";
		} catch (Exception e) {
			log.error(e);
			req.setAttribute("result", "上传失败!");
			return LOCAVIDEO;
		}

	}

	@ResponseBody
	@RequestMapping("/getVideoInfoList")
	public List<VideoInfo> getVideoInfoList() {
		return videoInfoService.getVideoInfo();
	}

	public Map<String, Object> setRuest(int code, String msg) {
		Map<String, Object> resutMap = new HashMap<String, Object>();
		resutMap.put("code", "200");
		resutMap.put("msg", "添加元素成功!");
		return resutMap;
	}
}
