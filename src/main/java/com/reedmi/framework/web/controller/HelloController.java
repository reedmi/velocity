package com.reedmi.framework.web.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController extends AbstractController {

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String hello() {
		for (Map.Entry<String, String[]> entry : (Set<Map.Entry<String, String[]>>)request().getParameterMap().entrySet()){
			String key = entry.getKey();
			String value = entry.getValue()[0];
			System.out.println(key + "=" + value);
		}
		return "hello";
	}
}
