package com.maple.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

	@RequestMapping("/first")
	@ResponseBody
	public String firstController() {
		return "the first controller";
	}


	@GetMapping("/printName")
	public String printName(@RequestParam(name = "name") String name, Model model){
		model.addAttribute("name",name);

		return "printName";
	}
}
