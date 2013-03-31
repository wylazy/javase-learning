package com.ipjmc.demo.http;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.free4lab.utils.hash.Md5Util;


public class SimpleHttpClient {

	private HttpClient httpClient;
	
	public SimpleHttpClient() {
		httpClient = new DefaultHttpClient();
	}
	
	public String get(String url, Map<String, String> map) {
		
		StringBuilder sb = new StringBuilder();

		try {
			if (map != null && !map.isEmpty()) {
				for (Map.Entry<String, String> entry : map.entrySet()) {
					sb.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), "utf-8")).append('&');
				}
				//baseUrl			
				url += "?" + sb.toString();
			}
			System.out.println("url = " + url);
			HttpGet httpGet = new HttpGet(url);

		
		    HttpResponse response = httpClient.execute(httpGet); //发起GET请求

		    System.out.println("resCode = " + response.getStatusLine().getStatusCode()); //获取响应码
		    return EntityUtils.toString(response.getEntity(), "utf-8");
		    
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		return null;
	}
	
	public static void first() {
		SimpleHttpClient http = new SimpleHttpClient();
		HashMap<String, String> map = new HashMap<String, String>();
		
		String url = "http://account.free4lab.com/pwdauth";
		
		String customId = "onlinevideo";
		String secretKey = "21a87cfc9ed51896bee556c35713e9c0";
		String email = "ipjmcp@gmail.com";
		String passwordMd5 = "72d69e18646816a3eae295e0afc4c44a";
		
		map.put("customId", customId);
		map.put("secretKey", secretKey);
		map.put("email", email);
		map.put("passwordMd5", passwordMd5);
		map.put("chksum", Md5Util.getMd5(customId+secretKey+email+passwordMd5));
		
		System.out.println(http.get(url, map));
		
		secretKey = "000000";
		map.put("secretKey", secretKey);
		map.put("chksum", Md5Util.getMd5(customId+secretKey+email+passwordMd5));
		System.out.println(http.get(url, map));
	}
	
	public static void second() {
		
		SimpleHttpClient http = new SimpleHttpClient();
		String url = "http://account.free4lab.com/api/checkPsw";
		HashMap<String, String> map = new HashMap<String, String>();
		String email = "ipjmcp@gmail.com";
		String passwordMd5 = "72d69e18646816a3eae295e0afc4c44a";
		
		map.put("email", email);
		map.put("passwordMd5", passwordMd5);
		System.out.println(http.get(url, map));
	}
	
	public static void main(String [] args) {
		first();
		second();
	}
}
