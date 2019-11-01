package com.maple.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

	@RequestMapping("/first")
	@ResponseBody
	public String firstController() {
		return "the first controller";
	}
}
