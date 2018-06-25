package com.ace.controller.phone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ace.entity.BookType;
import com.ace.service.BookTypeService;

@Controller
@RequestMapping("/phone/booktype")
public class BookTypePhoneController extends BaseController{
	@Autowired
	private BookTypeService bookTypeService;
	
	@RequestMapping("/childs.json")
	public @ResponseBody Map<String,Object> childs(HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		int code = 0;
		String msg = "成功！";
		List<BookType> types = null;
		String _parentId = request.getParameter("parentId");
		if(_parentId == null){
			 types = bookTypeService.getAllChildList();
		}else{
			int parentId = Integer.parseInt(_parentId);
			types = bookTypeService.getChildList(parentId);
		}
		
		if(types == null){
			code = 4;
			msg = "失败！数据不存在。";
		}
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		returnMap.put("types", types);
		return returnMap;
	}
	
	@RequestMapping("/parents.json")
	public @ResponseBody Map<String,Object> all(HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		String msg = "成功！";
		int code = 0;
		List<BookType> parents = bookTypeService.getParentList();
		
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		returnMap.put("parents", parents);
		return returnMap;
	}
	
	
	@RequestMapping("/select.json")
	public @ResponseBody Map<String,Object> select(int id,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		int code = 0;
		String msg = "成功！";
		BookType type = bookTypeService.queryById(id);
		if(type == null){
			code = 4;
			msg = "失败！数据不存在。";
		}
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		returnMap.put("type", type);
		return returnMap;
	}
	
}
