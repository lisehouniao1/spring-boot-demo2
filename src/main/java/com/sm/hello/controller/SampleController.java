package com.sm.hello.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sm.hello.common.cache.CacheService;
import com.sm.hello.model.User;
import com.sm.hello.service.UserService;

@Controller
@EnableAutoConfiguration
public class SampleController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	/*@Autowired
	private RedisUtil redisUtil;*/
	@Autowired
	private CacheService cacheService;
	
	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "hello world!!!!";
	}
	
	@RequestMapping("/hello/{name}")
	@ResponseBody
	String index(@PathVariable String name) {
		stringRedisTemplate.opsForValue().set("nsdjnd", name);
		stringRedisTemplate.expire("nsdjnd", 30, TimeUnit.SECONDS);
		User user = new User();
		user.setUsername(name);
		user.setAge(24);
		user.setPhoneNumber("18042022493");
		user.setEmail("thunder.shen@foxmail.com");
		cacheService.set("测试", user, 60l);
		return "Hello " + name + "!!!!!!!";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	String login(String name, String password) {
		return "登录成功";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	String test() {
		User res = (User) cacheService.get("测试");
		System.out.println("------------------------------------------------");
		System.out.println(res.toString());
		return res.toString(); 
	}
	
	@RequestMapping("/test/page")
	public String testPage(Model model) {
		
		return "/index.html";
	}
	
	@RequestMapping("/test/incr")
	@ResponseBody
	public String testIncr() {
		long value = cacheService.incr("testIncr");
		return String.valueOf(value);
	}
	
	@RequestMapping("/test/lpush")
	@ResponseBody
	public String testLeftPush() {
		cacheService.leftPush("testLeftPush", "12345");
		return "success";
	}
}
