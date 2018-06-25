package com.ace;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.ace.entity.User;
import com.ace.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class,webEnvironment=WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private UserService userService;	
	@Test
	public void contextLoads() {
		List<User> users = userService.queryAll();
		System.out.println(users);
	}
	/*
	public static void main(String[] args) {
		String admin = "admin";
		String uri = "/admin/permission/list/1?parentId=parentId";
		int index = uri.indexOf(admin)+admin.length();
		String turi = uri.substring(index+1);
		String finder = turi.substring(0,turi.indexOf("/"));
		System.out.println("finder:"+finder);
		turi = turi.substring(turi.indexOf("/")+1);
		String mainName = turi.substring(0,turi.indexOf("/"));
		System.out.println("mainName:"+mainName);
	}*/

}
