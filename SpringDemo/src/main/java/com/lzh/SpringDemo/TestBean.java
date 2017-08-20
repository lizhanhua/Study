/**
 * 
 */
package com.lzh.SpringDemo;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.lzh.controller.RestConstroller;

/**
 * @author lizh
 *
 */
public class TestBean {

	private ApplicationContext applicationContext;
	
	@PostConstruct
	public void init () {
		RestConstroller bean = (RestConstroller) applicationContext.getBean("restConstroller");
		System.out.println(bean.getClass().getName());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
