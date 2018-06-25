package com.ace.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ace.entity.BookFile;
import com.ace.service.BookFileService;

@Controller
@RequestMapping("/admin/bookfile")
public class BookFileAdminController extends BaseController{
	@Autowired
	private BookFileService bookFileService;
	
	@RequestMapping("/select")
	public @ResponseBody Map<String,Object> select(int bookId,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		int code = 0;
		String msg = "成功！很好地完成了提交。";
		BookFile bf = bookFileService.queryBookFileByBookId(bookId);
		if(bf == null){
			code = 4;
			msg = "错误！数据不存在。";
		}
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		returnMap.put("bookfile", bf);
		return returnMap;
	}
	
	
}
