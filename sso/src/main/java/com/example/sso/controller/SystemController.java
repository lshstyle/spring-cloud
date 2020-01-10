package com.example.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sso.service.SystemService;

@RestController
@RequestMapping("/sys")
public class SystemController {

	@Autowired
	private SystemService systemService;
	
	@GetMapping("/info")
	public String info() {
		return systemService.info();
	}
}
