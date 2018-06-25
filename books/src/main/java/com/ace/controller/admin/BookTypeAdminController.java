package com.ace.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ace.entity.BookType;
import com.ace.service.BookTypeService;
import com.ace.util.PageCode;
import com.ace.util.StringUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/booktype")
public class BookTypeAdminController extends BaseController{
	@Autowired
	private BookTypeService bookTypeService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<BookType> pu = bookTypeService.queryPageListByWhere(null, page, PAGESIZE);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu", pu);
		model.addAttribute("pc", pc);
		return INDEX;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		List<BookType> parents = bookTypeService.getParentList();
		model.addAttribute("parents",parents);
		return INDEX;
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(BookType bookType,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		
		bookType.setCreateTime(StringUtils.getDateToLong(new Date()));
		int count = bookTypeService.saveSelect(bookType);
		if(count > 0){
			returnMap.put("msg", "成功！很好地完成了提交。");
			returnMap.put("code", 0);
		}else{
			returnMap.put("msg", "错误！请进行一些更改。");
			returnMap.put("code", 4);
		}
		
		return returnMap;
	}
	
	@RequestMapping("/upt/{id}")
	public String upt(@PathVariable int id,HttpServletRequest request,Model model){
		BookType bt = bookTypeService.queryById(id);
		List<BookType> parents = bookTypeService.getParentList();
		model.addAttribute("parents",parents);
		model.addAttribute("bt",bt);
		return INDEX;
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(BookType bookType,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		int count = bookTypeService.updateSelective(bookType);
		if(count > 0){
			returnMap.put("msg", "成功！很好地完成了提交。");
			returnMap.put("code", 0);
		}else{
			returnMap.put("msg", "错误！请进行一些更改。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	@RequestMapping("/child.json")
	public @ResponseBody Map<String,Object> child(int id,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		
		List<BookType> childs = bookTypeService.getChildList(id);
		returnMap.put("childs", childs);
		
		return returnMap;
	}
	
	
}
