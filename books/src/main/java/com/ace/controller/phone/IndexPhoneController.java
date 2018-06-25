package com.ace.controller.phone;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ace.config.ServerConfig;
import com.ace.entity.User;
import com.ace.service.UserService;
import com.ace.util.Resource;

@Controller
@RequestMapping("/phone")
public class IndexPhoneController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ServerConfig config;
	
	@RequestMapping("/token.json")
	public @ResponseBody Map<String,Object> token(String loginName,String password,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		//TODO 根据用户名+密码生成token
		User user = userService.getUserToLoginName(loginName);
		String msg = "成功！";
		String token = "";
		if(user != null){
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			password = md5.encodePassword(password, loginName);
			if(!password.equals(user.getPassword())){
				msg = "失败！密码错误。";
			}else{
				//TODO token_时间戳+用户名
				token = md5.encodePassword(config.getToken(), loginName);
				Resource.TOKEN = token;
			}
		}else{
			msg = "失败！用户名不存在。";
		}
		returnMap.put("msg", msg);
		returnMap.put("token", token);
		
		return returnMap;
	}
	
	@RequestMapping("/login.json")
	public @ResponseBody Map<String,Object> login(String loginName,String password,HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		Map<String,Object> returnMap = new HashMap<String, Object>();
		String msg = "成功！";
		int code = 0;
		User user = null;
		if(loginName == null || loginName.equals("")){
			msg = "失败！请输入用户名。";
			code = 4;
		}else{
			user = userService.getUserToLoginName(loginName);
			if(user != null){
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
				password = md5.encodePassword(password, loginName);
				if(!password.equals(user.getPassword())){
					msg = "失败！密码错误。";
					code = 4;
				}else{
					//成功
					session.setAttribute("user", user);
				}
			}else{
				msg = "失败！用户名不存在。";
				code = 4;
			}
		}
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		returnMap.put("user", user);
		return returnMap;
	}
	
	
	@RequestMapping("/error.json")
	public @ResponseBody Map<String,Object> error(HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		
		returnMap.put("code", 4);
		returnMap.put("msg", "失败！TOKEN验证失败。");
		
		return returnMap;
	}
	
}
