package com.ace.controller.admin;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ace.entity.User;
import com.ace.service.PermissionService;
import com.ace.service.UserService;
import com.ace.util.ImageUtils;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request,Model model){
		User user = (User)request.getAttribute("user");
		if(user == null){
			return LOGIN;
		}else{
			return INDEX;
		}
	}
	
	@RequestMapping("/login")
	public String login(Model model){
		return LOGIN;
	}
	
	@RequestMapping("/image/upload")
	public @ResponseBody Map<String,Object> upload(HttpServletRequest request,@RequestParam MultipartFile file) throws Exception{
		//String filePath = request.getSession().getServletContext().getRealPath("/") + "book_images/";
		String dir = System.getProperty("user.dir") + "/resources";
		String filePath = dir + "/book_images/";
		File path = new File(filePath);
		if(!path.exists()){
			path.mkdirs();
		}
		//HttpSession session = request.getSession();
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		String _x = request.getParameter("x");
		String _y = request.getParameter("y");
		String _width = request.getParameter("width");
		String _height = request.getParameter("height");
		int x = _x == null ? 0 : Integer.parseInt(_x);
		int y = _y == null ? 0 : Integer.parseInt(_y);
		int width = _width == null ? 0 : Integer.parseInt(_width);
		int height = _height == null ? 0 : Integer.parseInt(_height);
		
		String fileName = file.getOriginalFilename();
		if (fileName == null || "".equals(fileName))  
        {  
			returnMap.put("code", 4);
            returnMap.put("msg", "错误！文件名不存在。");
        }else{
        	String fileEx = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        	if(fileEx.equals(".png")|| fileEx.equals(".jpg")||fileEx.equals(".gif")||fileEx.equals(".jpeg")){
        		//String fn = user.getLoginName() + fileEx;
        		String fn = fileName;
        		try {  
    				//文件保存路径  
                    String fp = filePath + fn;  
                    // 转存文件  
                    file.transferTo(new File(fp));
                    //裁剪图片
                    ImageUtils.cutImage(fp, x, y, width, height);
                    //修改数据库记录
                    String url = "book_images/"+fn;
                    int count = 1;
                    if(count > 0){
           			 	returnMap.put("msg", "成功！很好地完成了提交。");
           			 	returnMap.put("url", url);
           			 	returnMap.put("code", 0);
           		 	}else{
           		 		returnMap.put("msg", "错误！请进行一些更改。");
           		 		returnMap.put("code", 4);
           		 	}
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
        	}else{
        		returnMap.put("code", 4);
                returnMap.put("msg", "错误！上传图片类型错误。");
        	}
        }
		
		return returnMap;
	}
}
