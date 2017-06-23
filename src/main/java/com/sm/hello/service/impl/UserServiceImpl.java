package com.sm.hello.service.impl;

import org.springframework.stereotype.Service;

import com.sm.hello.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public String test() {
		return "xxxx";
	}

}
