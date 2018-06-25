package com.ace.controller.admin;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ace.entity.Book;
import com.ace.entity.BookFile;
import com.ace.entity.BookType;
import com.ace.entity.User;
import com.ace.service.BookFileService;
import com.ace.service.BookService;
import com.ace.service.BookTypeService;
import com.ace.util.PageCode;
import com.ace.util.StringUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/book")
public class BookAdminController extends BaseController{
	@Autowired
	private BookService bookService;
	@Autowired
	private BookTypeService bookTypeService;
	@Autowired
	private BookFileService bookFileService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<Book> pu = bookService.queryPageListByWhere(null, page, PAGESIZE);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		return INDEX;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		List<BookType> parents = bookTypeService.getParentList();
		model.addAttribute("parents",parents);
		return INDEX;
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(Book book,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		
		book.setCreateTime(StringUtils.getDateToLong(new Date()));
		book.setReadCount(0l);
		String publicTimeStr = request.getParameter("publicTimeStr");
		book.setPublicTime(StringUtils.getLongByString(publicTimeStr,"MM/dd/yyyy"));
		
		int count = bookService.saveSelect(book);
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
		List<BookType> parents = bookTypeService.getParentList();
		Book book = bookService.queryById(id);
		BookType bt = bookTypeService.queryById(book.getTypeId());
		//TODO childs,child
		List<BookType> childs = bookTypeService.getChildList(bt.getParentId());
		
		model.addAttribute("parents",parents);
		model.addAttribute("childs",childs);
		model.addAttribute("parentId",bt.getParentId());
		model.addAttribute("book",book);
		
		return INDEX;
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(Book book,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		
		int count = bookService.updateSelective(book);
		if(count > 0){
			returnMap.put("msg", "成功！很好地完成了提交。");
			returnMap.put("code", 0);
		}else{
			returnMap.put("msg", "错误！请进行一些更改。");
			returnMap.put("code", 4);
		}
		
		return returnMap;
	}
	
	
	@RequestMapping("/upload")
	public @ResponseBody Map<String,Object> uptfile(HttpServletRequest request,@RequestParam MultipartFile file) throws Exception{
		//String filePath = request.getSession().getServletContext().getRealPath("/") + "upload_books/";
		String dir = System.getProperty("user.dir") + "/resources";
		String filePath = dir + "/upload_books/";
		File path = new File(filePath);
		if(!path.exists()){
			path.mkdirs();
		}
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		int code = 0;
		String msg = "成功！很好地完成了提交。";
		
		User user = (User)request.getAttribute("user");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book b = bookService.queryById(bookId);
		BookFile bf = bookFileService.queryBookFileByBookId(bookId);
		boolean updataFlag = true;
		if(bf == null){
			bf = new BookFile();
			updataFlag = false;
		}
		bf.setBookId(bookId);
		bf.setUserId(user.getId());
		bf.setCreateTime(StringUtils.getDateToLong(new Date()));
		
		String fileName = file.getOriginalFilename();
		if(fileName == null || "".equals(fileName)){
			code = 4;
			msg = "错误！文件名不存在！";
		}else{
			String fileEx = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
			if(fileEx.equals(".txt")){
				String fn = b.getName() + fileEx;
				try {
					//文件保存路径
					String fp = filePath + fn;
					//转存文件
					file.transferTo(new File(fp));
					//保存数据库记录
					bf.setName(fn);
					if(updataFlag){
						bookFileService.updateSelective(bf);
					}else{
						bookFileService.saveSelect(bf);
					}
				} catch (Exception e) {
					code = 4;
	                msg = "错误！文件上传错误。";
					e.printStackTrace();
				}
			}else{
				code = 4;
                msg = "错误！上传文件类型错误。";
			}
		}
		
		returnMap.put("bookfile", bf);
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		
		return returnMap;
	}
	
	
}
