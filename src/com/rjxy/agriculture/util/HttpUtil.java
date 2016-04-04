package com.rjxy.agriculture.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import android.content.Context;
import android.net.ConnectivityManager;

public class HttpUtil {
	public static String  getData(String url ,String data) {
		InputStream  is = null;
		OutputStream os = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		String result = "";
		try {
			URL uri = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			conn.setRequestMethod("POST");
			conn.setReadTimeout(1000*20);
			conn.setConnectTimeout(1000*20);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			os = conn.getOutputStream();
			bw = new BufferedWriter(new OutputStreamWriter(os));
			bw.write(data);
			bw.flush();
			is  = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while((line = br.readLine())!=null) {
				result = result + line;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(os!=null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(br !=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bw !=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static boolean  isNetWork(Context context) {
		boolean result = false;
		ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if( conn!=null && conn.getActiveNetworkInfo() != null) {
			result = true;
		}
		return result;
	}		
	
	public String  getSocketData(String ip,int port,String data){
		String result = " ";
		Socket socket = null ;
		InputStream is  = null;
		BufferedReader br = null;
		InetAddress address;
		try {
			address = InetAddress.getByName(ip);
			socket = new Socket(address, port);
			is = socket.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line=br.readLine())!=null) {
				result = result + line;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		return result;
			
	} 
}
