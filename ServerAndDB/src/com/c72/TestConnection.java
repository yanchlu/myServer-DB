package com.c72;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
public class TestConnection {
	public static HttpURLConnection httpURLConnection=null;
	public static void main(String args[]) {
		String UrlPath=new String("http://localhost:8080/ServerAndDB/bi");
			URL url;
			try {
				url = new URL(UrlPath);
				httpURLConnection=(HttpURLConnection)url.openConnection();
				httpURLConnection.setRequestMethod("GET");
				httpURLConnection.setConnectTimeout(5*1000);
	            httpURLConnection.setReadTimeout(60000);
				httpURLConnection.connect();
				InputStream inputStream=httpURLConnection.getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
				StringBuffer sb=new StringBuffer();
				String temp=null;
				while ((temp=br.readLine())!=null){
	                sb.append(temp+"\r\n");
	            }
				String message=sb.toString();
				System.out.println(message);
				JSONObject jsonResult=JSONObject.parseObject(message);
				inputStream.close();
				httpURLConnection.disconnect();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
