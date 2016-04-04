package com.rjxy.agriculture.ui.activity;


import com.rjxy.agriculture.ClientApp;
import com.rjxy.agriculture.request.BaseRequest;
import com.rjxy.agriculture.request.BaseRequest.REQUEST_RESULT;
import com.rjxy.agriculture.request.GetSensorValueRequest;
import com.rjxy.agriculture.request.RequestThread;


import android.app.Activity;
import android.os.Bundle;

/**
 * 
 */
public class AppBaseActivity extends Activity{
	
	protected ClientApp mApp;
	// 请求执行线程
	protected RequestThread mGetSensorThread;
	// 获取传感器实时值的请求类
	private GetSensorValueRequest mGetSensorRequest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mApp = (ClientApp) getApplication();
		
		mGetSensorThread = new RequestThread(getApplicationContext(), mApp.getHandler());
		mGetSensorThread.setLoop(true, 1000);
		
		mGetSensorRequest = new GetSensorValueRequest();
		mGetSensorRequest.setSensorValue(mApp.getSensorValue());
		mGetSensorRequest
				.setOnResponseEventListener(new BaseRequest.OnResponseEventListener() {
				
					@Override
					public void onResponse(BaseRequest request,
							REQUEST_RESULT result) {
						if (mGetSensorRequest.isSuccess) {
							onGetSensor();
						}
					}
				});
		
		mGetSensorThread.setBaseRequest(mGetSensorRequest);

	}

	protected void startGetSensorData() {
		mGetSensorThread.start();
	}

	// 停止线程
	protected void stopGetSensorData() {
		mGetSensorThread.stopThread();
	}

	protected void onGetSensor() {
	}

}
