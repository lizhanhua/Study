/**
 * 
 */
package com.lzh.controller;

/**
 * @author lizh
 *
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lzh.SpringDemo.TestBean;
@Controller
public class RestConstroller implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	public RestConstroller() {}
	@RequestMapping(value = "/sslogin_/{user}", method = RequestMethod.GET)
	public ModelAndView myMethod(HttpServletRequest request,HttpServletResponse response, 
			@PathVariable("user") String user, ModelMap modelMap) throws Exception {
		modelMap.put("loginUser", user);
		return new ModelAndView("/login/hello", modelMap);
	}
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String registPost() {
		return "/welcome";
	}
//	@RequestMapping(value="/login") 
//	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
//			@RequestParam("user_name") String userName, @RequestParam("user_pwd") String userPwd,
//			ModelMap modelMap) throws Exception {
//		if("lizh".equalsIgnoreCase(userName) && "123456".equalsIgnoreCase(userPwd)) {
//			modelMap.put("result", "��¼�ɹ�");
//			modelMap.put("userName", userName);
//		} else {
//			modelMap.put("result", "��¼ʧ��");
//			modelMap.put("userName", userName);
//		}
//		response.setCharacterEncoding("GBK");
//		return new ModelAndView("/sub/loginResult", modelMap);
//	}
	
	@RequestMapping(value="/login") 
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws Exception {
		
		//测试ApplicationContextAware
		TestBean bean =  applicationContext.getBean("test", TestBean.class);
		System.out.println(bean.getClass().getName());
		
		
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("user_name");
		String userPwd = request.getParameter("user_pwd");
		if("lizh".equalsIgnoreCase(userName) && "123456".equalsIgnoreCase(userPwd)) {
			modelMap.put("result", "登录成功");
			modelMap.put("userName", userName);
		} else {
			modelMap.put("result", "登录失败");
			modelMap.put("userName", userName);
		}
		response.setCharacterEncoding("GBK");
		return new ModelAndView("/sub/loginResult", modelMap);
	}
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}
}

