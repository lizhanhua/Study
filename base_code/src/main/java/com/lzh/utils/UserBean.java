/**
 * 
 */
package com.lzh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhanhua.li
 *
 */
public class UserBean {

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getJobnumber() {
		return jobnumber;
	}
	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getVendor_code() {
		return vendor_code;
	}
	public void setVendor_code(int vendor_code) {
		this.vendor_code = vendor_code;
	}
	public int getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public String getShops() {
		return shops;
	}
	public void setShops(String shops) {
		this.shops = shops;
	}
	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAxdata() {
		return axdata;
	}
	public void setAxdata(String axdata) {
		this.axdata = axdata;
	}
	private String user;
    private String jobnumber;
    private int shop_id;
    private int vendor_code;
    private int vendor_id;
    private String permission;
    private int user_id;
    private int user_type;
    private String shops;
    private String codes;
    private String nickname;
    private int type;
    private String axdata;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("E:\\user_main.txt");
		try {
			InputStream is = new FileInputStream(file);
			
			StringBuffer sb = new StringBuffer();
//			int c;
//			while((c=is.read()) != -1) {
//				sb.append((char)c);
//			}
			byte b[] = new byte[1000];
			while(is.read(b)!= -1) {
				sb.append(new String(b));
			}
			Map<String, Object> map = JsonUtil.toHashMap(sb.toString());
			Set<String> set = map.keySet();
			StringBuffer param = new StringBuffer();
			for(String key: set) {
				param.append(key + "=" + map.get(key) + "&");
			}
			String paramStr = param.toString();
			String paramUrl = paramStr.substring(0, paramStr.length()-1);
			String hostUrl = "http://vis-api-jqq.vip.com/vendor/report/login.php?" + paramUrl;
			System.out.print(hostUrl);
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
