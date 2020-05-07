package com.example.sso.service.impl;

import org.springframework.stereotype.Service;

import com.example.sso.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {

    @Override
    public String info() {
        // TODO Auto-generated method stub
        return "sso";
    }

}