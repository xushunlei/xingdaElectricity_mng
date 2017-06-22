package com.xinda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinda.service.MeterService;

@Controller
@RequestMapping("/")
public class TestController
{
	@Autowired
	private MeterService meterService;
	@RequestMapping
	public String Hello(){
		return "index";
	}
}
