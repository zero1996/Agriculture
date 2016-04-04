package com.rjxy.agriculture.request;




import com.rjxy.agriculture.ClientApp;
import com.rjxy.agriculture.request.BaseRequest.REQUEST_RESULT;
import com.rjxy.agriculture.util.HttpUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class RequestThread extends Thread {
	public Handler handler;
	public Context context;
	public BaseRequest baseRequest;
	public ClientApp mApp;
	public boolean isLoop = false;
	public boolean mcancle = false;
	public boolean  isPaues = false;
	public int mLoopperiod = 10000;
	
	public RequestThread(Context context,Handler handler) {
		this.context = context;
		this.mApp = (ClientApp) context.getApplicationContext();
		this.handler = handler;
	}
	public void setBaseRequest(BaseRequest baseRequest) {
		this.baseRequest = baseRequest;
	}
	/**
	 * run方法
	 * */
	@Override
	public void run() {
		if(!isPaues) {
			do{
				if(baseRequest !=  null ) {//并且mApp！=null  
					REQUEST_RESULT result = REQUEST_RESULT.FILT;//默认失败
					if(HttpUtil.isNetWork(context)) {  //网络联通判断
						if(mApp != null && baseRequest.getActionName() !=null) {
							String url = "http://"+mApp.getIp()+":8890/type/jason/action/"+
									baseRequest.getActionName();//链接地址
							String  response = HttpUtil.getData(url, baseRequest.getBody());//网络请求后结果
							baseRequest.setResponseStr(response);//
							if(result != null && !result.equals("")) {
								result = REQUEST_RESULT.SUCCESS;
							}
						}
					} else{
						result = REQUEST_RESULT.FILT;
					}
					Message msg = Message.obtain();
					msg.what = ClientApp.what;
					msg.arg1 = result.ordinal();
					msg.obj = this; 
					handler.sendMessage(msg);
					if(!isPaues) {
						try {
							sleep(mLoopperiod);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}while(isLoop);
		}else{
			try {
				sleep(mLoopperiod);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void hanlderResult(REQUEST_RESULT result) {
		baseRequest.parseJason(result);
	}
	
	
	public void stopThread() {
		isLoop = false;
		mcancle = false;
		isPaues = true;
	}
	
	/**
	 * 暂停线程
	 * */
	public void pauseThread(boolean isPause){
		isPaues = isPause;
	}
	
	/**
	 * 设置循环
	 * 
	 * */
	public void setLoop(boolean isLoop, int mLoopperiod) {
		this.isLoop = isLoop;
		this.mLoopperiod = mLoopperiod;
	}
	
}
