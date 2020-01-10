package com.example.rpc.service.impl;

import org.springframework.stereotype.Service;

import com.example.rpc.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return "rpc";
	}

}
