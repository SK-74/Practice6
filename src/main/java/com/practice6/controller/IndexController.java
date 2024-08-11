package com.practice6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.practice6.request.IndexRequest;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(@ModelAttribute("request") IndexRequest req, Model model) {
		//htmlファイルの名前を返却
		return "index";
	}

	@GetMapping("/judge")
	public String judge(
			@Validated @ModelAttribute("request") IndexRequest req, BindingResult validResult, Model model) {
		
		if(! validResult.hasErrors()) {
			int val = Integer.valueOf(req.getValue());
			String message = val + "は、";
			
			boolean result = over100(val);
			if(result) {
				message += "100より大きい数字でした";
			}else {
				message += "100以下の数字でした";
			}
			//Thymeleafに情報を渡す。htmlファイルに${message}が必要
			model.addAttribute("message", message);
		}
		//htmlファイルの名前を返却
		return "index";
	}
	
	private boolean over100(int val) {
		boolean result = false;
		if(val > 100) {
			result = true;
		}
		return result;
	}
}
