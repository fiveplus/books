package com.ace.controller.phone;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ace.entity.BookFile;
import com.ace.entity.BookShelves;
import com.ace.service.BookFileService;
import com.ace.service.BookShelvesService;
import com.ace.util.StringUtils;

@Controller
@RequestMapping("/phone/bookshelves")
public class BookShelvesPhoneController {
	@Autowired
	private BookShelvesService bookShelvesService;
	@Autowired
	private BookFileService bookFileService;
	
	@RequestMapping("/save.json")
	public @ResponseBody Map<String,Object> save(BookShelves bookShelves,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		int code = 0;
		String message = "恭喜您，保存成功。";
		
		//TODO 检查书架是否存在
		BookShelves bs = bookShelvesService.getBookShelvesByUserIdAndBookId(bookShelves.getUserId(),bookShelves.getBookId());
		if(bs == null){
			BookFile bf = bookFileService.queryBookFileByBookId(bookShelves.getBookId());
			bookShelves.setBookfileId(bf.getId());
			bookShelves.setCreateTime(StringUtils.getDateToLong(new Date()));
			bookShelvesService.saveSelect(bookShelves);
		}else{
			code = 4;
			message = "错误！书架中已有书籍。";
		}
		
		returnMap.put("code", code);
		returnMap.put("message", message);
		return returnMap;
	}
	
	
}
