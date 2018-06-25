package com.ace.controller.phone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ace.entity.Book;
import com.ace.service.BookService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/phone/book")
public class BookPhoneController extends BaseController{
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/top.json")
	public @ResponseBody Map<String,Object> top(String token,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		int code = 0;
		String msg = "成功！";
		List<Book> books = bookService.getBookList(NUMBER);
		if(books == null){
			code = 4;
			msg = "失败！数据不存在。";
		}
		
		returnMap.put("books", books);
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		
		return returnMap;
	}
	
	@RequestMapping("/main.json")
	public @ResponseBody Map<String,Object> main(int typeId,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		String msg = "成功！";
		int code = 0;
		//TODO 根据类别查询书籍5个
		PageInfo<Book> pu = bookService.getBookListByTypeId(typeId,1,NUMBER);
		if(pu.getList() == null){
			code = 4;
			msg = "失败！数据不存在。";
		}
		returnMap.put("books", pu.getList());
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		return returnMap;
	}
	
	@RequestMapping("/types.json")
	public @ResponseBody Map<String,Object> types(int typeId,int page,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		String msg = "成功！";
		int code = 0;
		PageInfo<Book> pu = bookService.getBookListByTypeId(typeId,page,PAGESIZE);
		if(pu.getList() == null){
			code = 4;
			msg = "失败！数据不存在。";
		}
		returnMap.put("books", pu.getList());
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		return returnMap;
	}
	
	@RequestMapping("/parents.json")
	public @ResponseBody Map<String,Object> parents(int typeId,int page,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		String msg = "成功！";
		int code = 0;
		PageInfo<Book> pu = bookService.getBookListByParentId(typeId, page, PAGESIZE);
		
		if(pu.getList() == null){
			code = 4;
			msg = "失败！数据不存在。";
		}
		returnMap.put("books", pu.getList());
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		
		return returnMap;
	}
	
	
	
	
	@RequestMapping("/search.json")
	public @ResponseBody Map<String,Object> search(String name,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		String msg = "成功！";
		int code = 0;
		List<Book> books = null;
		if(name == null || name.equals("")){
			code = 4;
			msg = "失败！参数错误。";
		}else{
			books = bookService.getBookListLikeName(name);
		}
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		returnMap.put("books", books);
		
		return returnMap;
	}
	
	
	
	@RequestMapping("/select.json")
	public @ResponseBody Map<String,Object> select(int id,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		int code = 0;
		String msg = "成功！";
		Book book = bookService.queryById(id);
		if(book == null){
			code = 4;
			msg = "失败！数据不存在。";
		}else{
			//阅读量++
			book.setReadCount(book.getReadCount()+1);
			bookService.updateSelective(book);
		}
		
		returnMap.put("book", book);
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		
		return returnMap;
	}
	
	/**
	 * 热门推荐
	 * @param page
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/recommend.json")
	public @ResponseBody Map<String,Object> recommend(int page,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		String msg = "成功！数据查询成功。";
		int code = 0;
		PageInfo<Book> pu = bookService.getBookListOrderByReadCount(page,PAGESIZE);
		
		returnMap.put("books", pu.getList());
		returnMap.put("msg", msg);
		returnMap.put("code", code);
		
		return returnMap;
	}
	
	@RequestMapping("/free.json")
	public @ResponseBody Map<String,Object> free(int page,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		String msg = "成功！数据查询成功。";
		int code = 0;
		PageInfo<Book> pu = bookService.getBookListByNoPrice(page,PAGESIZE);
		returnMap.put("books", pu.getList());
		returnMap.put("msg", msg);
		returnMap.put("code", code);
		
		return returnMap;
	}
	
	@RequestMapping("/newbooks.json")
	public @ResponseBody Map<String,Object> newbooks(int page,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		String msg = "成功！数据查询成功。";
		int code = 0;
		PageInfo<Book> pu = bookService.getBookListByCreateTime(page,PAGESIZE);
		returnMap.put("books", pu.getList());
		returnMap.put("msg", msg);
		returnMap.put("code", code);
		
		return returnMap;
	}
	
	
}
