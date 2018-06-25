package com.ace.controller.phone;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ace.entity.Book;
import com.ace.entity.BookFile;
import com.ace.service.BookFileService;
import com.ace.service.BookService;
import com.ace.util.FileUtils;

@Controller
@RequestMapping("/phone/bookfile")
public class BookFilePhoneController extends BaseController{
	@Autowired
	private BookFileService bookFileService;
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/select.json")
	public @ResponseBody Map<String,Object> select(int bookId,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		int code = 0;
		String msg = "成功！数据查询成功。";
		BookFile bf = bookFileService.queryBookFileByBookId(bookId);
		String contents = null;
		if(bf == null){
			code = 4;
			msg = "失败！数据查询失败。";
		}else{
			String fileName = bf.getName();
			//String filePath = request.getSession().getServletContext().getRealPath("/") + "upload_books/";
			String dir = System.getProperty("user.dir") + "/resources";
			String filePath = dir + "/upload_books/";
			Book b = bookService.queryById(bf.getBookId());
			if(b.getPrice() > 0){
				contents = FileUtils.readTxtFile(filePath + fileName, TXTNUM);
				contents += "...";
			}else{
				//TODO 免费书籍取出全部
				contents = FileUtils.readTxtFile(filePath + fileName, TXTNUM);
				contents += "...";
			}
			
		}
		
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		returnMap.put("bookId", bookId);
		returnMap.put("contents", contents);
		
		return returnMap;
	}
	
	@RequestMapping("/download.json")
	public ResponseEntity<InputStreamResource> download(int bookId,int userId) throws IOException{
		//TODO 查询用户是否有下载权限
		Book b = bookService.queryById(bookId);
		boolean flag = false;
		if(b.getPrice() == 0){
			flag = true;
		}else{
			//TODO 收费部分
		}
		if(flag){
			BookFile bf = bookFileService.queryBookFileByBookId(bookId);
			String fileName = bf.getName();
			String dir = System.getProperty("user.dir") + "/resources";
			String filePath = dir + "/upload_books/";
			FileSystemResource file = new FileSystemResource(filePath + fileName);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");  
	        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));  
	        headers.add("Pragma", "no-cache");  
	        headers.add("Expires", "0"); 
	        return ResponseEntity  
	                .ok()  
	                .headers(headers)  
	                .contentLength(file.contentLength())  
	                .contentType(MediaType.parseMediaType("application/octet-stream"))  
	                .body(new InputStreamResource(file.getInputStream())); 
		}
		return null;
	}
	
	
	
}
