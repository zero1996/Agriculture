package com.rjxy.agriculture.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rjxy.agriculture.ClientApp;
import com.rjxy.agriculture.R;
import com.rjxy.agriculture.bean.SensorConfig;
import com.rjxy.agriculture.bean.SensorValue;
import com.rjxy.agriculture.bean.SetSensorConfig;
import com.rjxy.agriculture.request.BaseRequest;
import com.rjxy.agriculture.request.BaseRequest.OnResponseEventListener;
import com.rjxy.agriculture.request.BaseRequest.REQUEST_RESULT;
import com.rjxy.agriculture.request.RequestThread;
import com.rjxy.agriculture.request.SetSensorConfigRequest;
import com.rjxy.agriculture.ui.activity.MainActivity;

public class SetAirDialog extends BaseDialog {
	private Button setBtn;
	private TextView closeTV;
	private Context context;
	private ClientApp mApp;
	
	private TextView airTvalueTV;
	private TextView airTstateTV;
	private EditText airTMinET;
	private EditText airTMaxET;
	
	private TextView airHvalueTV;
	private TextView airHstateTV;
	private EditText airHMinET;
	private EditText airHMaxET;

	public SetAirDialog(Context context) {
		super(context);
		this.context = context;
		mApp = (ClientApp) context.getApplicationContext();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_set_air);
	
		setBtn = (Button) findViewById(R.id.dialog_set_air_ok);
		closeTV = (TextView) findViewById(R.id.dialog_set_air_close);
		
		airTvalueTV = (TextView) findViewById(R.id.dialog_set_air_temperature_now);
		airTstateTV = (TextView) findViewById(R.id.dialog_set_air_temperature_state);
		airTMinET = (EditText) findViewById(R.id.dialog_set_air_temperature_min);
		airTMaxET = (EditText) findViewById(R.id.dialog_set_air_temperature_max);
		
		airHvalueTV = (TextView) findViewById(R.id.dialog_set_air_humidity_now);
		airHstateTV = (TextView) findViewById(R.id.dialog_set_air_humidity_state);
		airHMinET = (EditText) findViewById(R.id.dialog_set_air_humidity_min);
		airHMaxET = (EditText) findViewById(R.id.dialog_set_air_humidity_max);
		setAirDialogValue();
		setCommonBtn(setBtn, closeTV);
	}

	@Override
	protected void setConfirmBtnOnClick() {
		String atMin = airTMinET.getText().toString().trim();
		String atMax = airTMaxET.getText().toString().trim();
		String ahMin = airHMinET.getText().toString().trim();
		String ahMax = airHMaxET.getText().toString().trim();
		
		if(isConfigValueAvailable(true, atMin, atMax, ahMin, ahMax)){//阈值符合要求
			SetSensorConfig config = new SetSensorConfig();//请求类
			
			final int int_ahmax = Integer.parseInt(ahMax);
			final int int_ahmin = Integer.parseInt(ahMin);
			final int int_atmax = Integer.parseInt(atMax);
			final int int_atmin = Integer.parseInt(atMin);// 转换成整形数值
			
			config.setMaxAirHumidity(int_ahmax);
			config.setMinAirHumidity(int_ahmin);
			config.setMaxAirTemperature(int_atmax);
			config.setMinAirTemperature(int_atmin);
			//设置请求数值	
			
        	final SetSensorConfigRequest request1 = new SetSensorConfigRequest();
        	request1.setSensorConfig(config);
        	RequestThread thread = new RequestThread(context,  mApp.getHandler());
        	thread.setBaseRequest(request1);
        	
        	request1.setOnResponseEventListener(new  OnResponseEventListener() {
				
				@Override
				public void onResponse(BaseRequest request, REQUEST_RESULT result) {
					// TODO Auto-generated method stub
					if(request1.isSuccess){
						MainActivity main = (MainActivity) context;
						main.runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								SensorConfig config = mApp.getSensorConfig();
								config.setMaxAirHumidity(int_ahmax);
								config.setMinAirHumidity(int_ahmin);
								config.setMaxAirTemperature(int_atmax);
								config.setMinAirTemperature(int_atmin);//请求成功后设置值到CLientApp
								Toast.makeText(context, "设置成功", Toast.LENGTH_LONG).show();
							}
						});
						
					}
				}
			});
        	thread.start();//发起网络清秋
		}
		this.dismiss();
	}
	
	/***
	 * 设置空气温湿度提示内的textview值
	 * */
	public  void setAirDialogValue() {
        SensorValue value = mApp.getSensorValue();
        int ah_value = value.getAirHumidity();
        int at_value = value.getAirTemperature();
    	airHvalueTV.setText(ah_value+"");
		airTvalueTV.setText(at_value+"");//设置空气湿度和温度的当前值
		
	    SensorConfig config = mApp.getSensorConfig();
	    int ah_max = config.getMaxAirHumidity();
	    int ah_min = config.getMinAirHumidity();
	    int at_max = config.getMaxAirTemperature();
	    int at_min = config.getMinAirTemperature();//设置状态的textview;
	    
	    airHMaxET.setText(ah_max+"");
        airHMinET.setText(ah_min+"");
        
        airTMaxET.setText(at_max+"");
        airTMinET.setText(at_min+"");
        
	    if(ah_value < ah_min || ah_value > ah_max) {
	    	airHstateTV.setText("预警");
	    	airHstateTV.setTextColor(Color.RED);
	    }else{
	    	airHstateTV.setText("正常");
	    	airHstateTV.setTextColor(Color.BLACK);
	    }
	    if(at_value < at_min || at_value > at_max) {
	    	airTstateTV.setText("预警");
	    	airTstateTV.setTextColor(Color.RED);
	    }else{
	    	airTstateTV.setText("正常");
	    	airTstateTV.setTextColor(Color.BLACK);
	    }
	    
	}
}
