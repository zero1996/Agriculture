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
import com.rjxy.agriculture.request.RequestThread;
import com.rjxy.agriculture.request.SetSensorConfigRequest;
import com.rjxy.agriculture.request.BaseRequest.OnResponseEventListener;
import com.rjxy.agriculture.request.BaseRequest.REQUEST_RESULT;
import com.rjxy.agriculture.ui.activity.MainActivity;

public class SetLightDialog extends BaseDialog {
	private Button setBtn;
	private TextView closeTV;
	
	private Context context;
	private ClientApp mApp;
	
	private TextView lightValueTV;
	private TextView lightStateTV;
	private EditText lightMinET;
	private EditText lightMaxET;
	

	public SetLightDialog(Context context) {
		super(context);
		this.mApp = (ClientApp) context.getApplicationContext();
		this.context = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_set_light);
		setBtn = (Button) findViewById(R.id.dialog_set_light_ok);
		closeTV = (TextView) findViewById(R.id.dialog_set_light_close);
		
		lightValueTV = (TextView) findViewById(R.id.dialog_set_light_now);
		lightStateTV = (TextView) findViewById(R.id.dialog_set_light_state);
		lightMinET = (EditText) findViewById(R.id.dialog_set_light_min);
		lightMaxET = (EditText) findViewById(R.id.dialog_set_light_max);
		
		setLightDialogValue();
		setCommonBtn(setBtn, closeTV);
	}

	@Override
	protected void setConfirmBtnOnClick() {String lightMin = lightMinET.getText().toString().trim();
	String lightMax = lightMaxET.getText().toString().trim();
	if(isConfigValueAvailable(false, lightMin, lightMax, "", "")){//阈值符合要求
		SetSensorConfig config = new SetSensorConfig();
		final int int_lightMax = Integer.parseInt(lightMax);
		final int int_lightMin = Integer.parseInt(lightMin);
		config.setMaxCo2(int_lightMax);
		config.setMinCo2(int_lightMin);
		config.setMaxLight(Integer.parseInt(lightMax));
		config.setMinLight(Integer.parseInt(lightMin));
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
							config.setMaxLight(int_lightMax);
							config.setMinLight(int_lightMin);
							Toast.makeText(context, "设置成功", Toast.LENGTH_LONG).show();
						}
					});
					
				}
			}
		});
    	thread.start();
    	this.dismiss();
	}
}
	
	public  void setLightDialogValue() {
        SensorValue value = mApp.getSensorValue();
        int light = value.getLight();
    	lightValueTV.setText(light+""); //设置co2当前值
		
	    SensorConfig config = mApp.getSensorConfig();
	    int light_max = config.getMaxLight();
	    int light_min = config.getMinLight(); //设置状态的textview;
	    
	    lightMaxET.setText(light_max+"");
        lightMinET.setText(light_min+"");
        
	    if(light < light_min || light  > light_max) {
	    	lightStateTV.setText("预警");
	    	lightStateTV.setTextColor(Color.RED);
	    }else{
	    	lightStateTV.setText("正常");
	    	lightStateTV.setTextColor(Color.BLACK);
	    }
	    
	}

}
