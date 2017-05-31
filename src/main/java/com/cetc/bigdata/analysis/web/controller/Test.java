package com.cetc.bigdata.analysis.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;

public class Test {
	public static final Gson gson = new Gson();
	public static void main(String[] args) {
		
		Map map = new HashMap();
		map.put("1", "zhangsan");
		map.put("lisi", new Date());
		
		UserP p = new UserP();
		p.setAge(12);
		p.setName("zhangsssss"); 
		
		map.put("per", p);
		
		System.out.println(gson.toJson(map));
		System.out.println(JSONObject.toJSONString(map));
		
		
		System.out.println(Hashing.md5().newHasher().putString("testadmin", Charsets.UTF_8).hash().toString());
	}
	


}

class UserP{
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}