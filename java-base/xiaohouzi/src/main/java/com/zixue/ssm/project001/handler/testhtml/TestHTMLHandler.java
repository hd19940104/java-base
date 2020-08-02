package com.zixue.ssm.project001.handler.testhtml;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("TestHTMLHandler")
public class TestHTMLHandler {
	@RequestMapping("/testDemo001")
	public String testDemo001() {
		return "testDemo001";
	}
}
