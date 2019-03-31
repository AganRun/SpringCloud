package com.atguigu.springcloud.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.agan.springcloud.entities.Dept;

@RestController
public class DeptController_Consumer {

	//没加入eureka之前的访问方式
//	private static final String REST_URL_RREFIX = "http://localhost:8001";
	
	//微服务提供者的服务名称
	private static final String REST_URL_RREFIX = "http://MICROSERVICECLOUD-DEPT";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/consumer/dept/add")
	public boolean add(Dept dept) {
		return restTemplate.postForObject(REST_URL_RREFIX + "/dept/add" , dept, Boolean.class);
	}
	
	@RequestMapping("/consumer/dept/get/{id}")
	public Dept get(@PathVariable Long id) {
		return restTemplate.getForObject(REST_URL_RREFIX + "/dept/get/" + id, Dept.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/consumer/dept/list")
	public List<Dept> list(){
		return restTemplate.getForObject(REST_URL_RREFIX + "/dept/list", List.class);
	}
	
	
	
	
	
	
}
