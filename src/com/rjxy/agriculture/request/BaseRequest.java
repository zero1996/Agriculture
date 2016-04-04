package com.rjxy.agriculture.request;

/**
 * 请求基类
 * */
public abstract class BaseRequest {

	
	public enum REQUEST_RESULT{
		SUCCESS,FILT,NOT_NET;
	}
	
	
	/**
	 * 服务器返回的数据
	 * */
	String responseStr;
	
	public void setResponseStr(String responseStr){
		this.responseStr = responseStr;
	}
	
	/**
	 * 回调接口
	 * */
	public interface OnResponseEventListener{
		void onResponse(BaseRequest request,REQUEST_RESULT result);
	}
	
	OnResponseEventListener onResponseEventListener;
	
	public void setOnResponseEventListener(OnResponseEventListener onResponseEventListener){
		this.onResponseEventListener = onResponseEventListener;
	}
	
	/**
	 * 返回活动名称，由子类实现
	 * */
	protected abstract String getActionName(); 
	
	/**
	 * 封装请求消息发送至服务器
	 * */
	protected String getBody(){
		return onGetJasonBody();
	}
	
	public String onGetJasonBody(){
		return "";
	}
	
	/**
	 * 解析来自服务器端的消息
	 * */
	public void parseJason(REQUEST_RESULT result){
		if(responseStr != null){
			onParseJasonBody(responseStr);
		}
		if(onResponseEventListener != null){
			onResponseEventListener.onResponse(this,result);
		}
	}
	
	
	public void onParseJasonBody(String responseStr){
		
	}
}
