/**
 * 
 */
package com.lzh.utils;

import java.util.HashMap;
import java.util.Iterator;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhanhua.li
 * alibaba的json对象JSONObject有很多的方法，可以根据key获取值
 */
public class JsonUtil {
	
	//json转成java bean
	public static <T> T get(String jsonStr, Class<T> clazz){
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		T t = JSONObject.toJavaObject(jsonObject, clazz);
		return t;
	}
	//json串转成Map
	public static HashMap<String, Object> toHashMap(String jsonStr) {
		HashMap<String, Object> data = new HashMap<String, Object>();	
		try {
			data = new HashMap<String, Object>();
			// 将json字符串转换成jsonObject
			JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			Iterator<?> it = jsonObject.keySet().iterator();
			// 遍历jsonObject数据，添加到Map对象
			while (it.hasNext()) {
				String key = String.valueOf(it.next());
				Object value = jsonObject.get(key);
				data.put(key, value);
			}
		} catch (Exception e) {
			data = null;
		}
		return data;
	}
}
