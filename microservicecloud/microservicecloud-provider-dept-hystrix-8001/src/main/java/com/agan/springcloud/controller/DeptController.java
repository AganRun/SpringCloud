package com.agan.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agan.springcloud.entities.Dept;
import com.agan.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeptController
{
	@Autowired
	private DeptService service;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept)
	{
		return service.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod="processHystrix_Get")
	public Dept get(@PathVariable("id") Long id)
	{
		Dept dept = service.get(id);
		if(dept == null) {
			throw new RuntimeException("该id" + id + "没有对应的信息");
		}
		return dept;
	}
	
	@SuppressWarnings("unused")
	private Dept processHystrix_Get(@PathVariable("id") Long id) {
		return new Dept().setDeptno(id)
				.setDname("该ID:" + id + "没有没有对应的信息,null--@HystrixCommand")
				.setDb_source("no this database in Mysql");
		
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list()
	{
		return service.list();
	}

}
