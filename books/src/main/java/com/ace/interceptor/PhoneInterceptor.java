package com.ace.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ace.util.Resource;
/**
 * 手机端请求拦截器，主要用于TOKEN验证
 * @author hack
 *
 */
public class PhoneInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		String tokenErrorUrl = "/phone/error.json";
		try{
			String uri = request.getRequestURI();
			int index = uri.indexOf(Resource.PHONE);
			if(index >= 0){
				index += Resource.PHONE.length();
				String turi = uri.substring(index+1);
				if(!turi.equals("")){
					if(turi.indexOf("/") >= 0){
						//TODO 验证TOKEN
						String token = request.getParameter("token");
						
						if(token == null){
							//TODO 重定向
							response.sendRedirect(request.getContextPath() + tokenErrorUrl);
						}else{
							if(token.equals(Resource.TOKEN)){
								//TODO TOKEN验证成功，跳过
							}else{
								//TODO 重定向
								response.sendRedirect(request.getContextPath() + tokenErrorUrl);
							}
						}
					}
				}
			}
		}catch(Exception e){}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		//View渲染前调用
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	}

}
