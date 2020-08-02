
package com.zixue.ssm.project001.handler.backstage;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zixue.ssm.project001.bean.VideoType;
import com.zixue.ssm.project001.service.VideoTypeService;
import com.zixue.util.config.ConfigurationUtil;


@Controller
public class TestController {
	private static final String TEST = "test";
	@Autowired
	private VideoTypeService videoTypeService;
	private static Logger log = Logger.getLogger(TestController.class);

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		String file_path = ConfigurationUtil.getString("video.file_path");
		return "[file_path:"+file_path+"]";
	}

	@ResponseBody
	@RequestMapping("/getViideType")
	public List<VideoType> getViideType() {
		log.info("###getViideType() start()###");
		List<VideoType> listVideoType = videoTypeService.showVideoType(null);
		log.info("###getViideType() end()###");
		return listVideoType;
	}
}
