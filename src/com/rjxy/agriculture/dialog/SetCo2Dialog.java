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
import com.rjxy.agriculture.request.BaseRequest.OnResponseEventListener;
import com.rjxy.agriculture.request.BaseRequest.REQUEST_RESULT;
import com.rjxy.agriculture.request.SetSensorConfigRequest;
import com.rjxy.agriculture.ui.activity.MainActivity;

public class SetCo2Dialog extends BaseDialog {
	private Button setBtn;
	private TextView closeTV;
	
	private Context context;
	private ClientApp mApp;
	
	private TextView co2ValueTV;
	private TextView co2StateTV;
	private EditText co2MinET;
	private EditText co2MaxET;
	

	public SetCo2Dialog(Context context) {
		super(context);
		mApp = (ClientApp) context.getApplicationContext();
		this.context = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_set_co2);
		setBtn = (Button) findViewById(R.id.dialog_set_co2_ok);
		closeTV = (TextView) findViewById(R.id.dialog_set_co2_close);
		
		co2ValueTV = (TextView) findViewById(R.id.dialog_set_co2_now);
		co2StateTV = (TextView) findViewById(R.id.dialog_set_co2_state);
		co2MinET = (EditText) findViewById(R.id.dialog_set_co2_min);
		co2MaxET = (EditText) findViewById(R.id.dialog_set_co2_max);
		
		setCo2DialogValue();
		setCommonBtn(setBtn, closeTV);
	}

	@Override
	protected void setConfirmBtnOnClick() {String co2Min = co2MinET.getText().toString().trim();
	String co2Max = co2MaxET.getText().toString().trim();
	if(isConfigValueAvailable(false, co2Min, co2Max, "", "")){//阈值符合要求
		SetSensorConfig config = new SetSensorConfig();
		final int int_co2Max = Integer.parseInt(co2Max);
		final int int_co2Min = Integer.parseInt(co2Min);
		config.setMaxCo2(int_co2Max);
		config.setMinCo2(int_co2Min);
		
    	final SetSensorConfigRequest request1 = new SetSensorConfigRequest();
    	request1.setSensorConfig(config);
    	RequestThread thread = new RequestThread(context,mApp.getHandler());
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
							config.setMaxCo2(int_co2Max);
							config.setMinCo2(int_co2Min);
							Toast.makeText(context, "设置成功", Toast.LENGTH_LONG).show();
						}
					});
					
				}
			}
		});
    	thread.start();
    	this.dismiss();
	}}
	
	
	public  void setCo2DialogValue() {
        SensorValue value = mApp.getSensorValue();
        int co2 = value.getCo2();
    	co2ValueTV.setText(co2+""); //设置co2当前值
		
	    SensorConfig config = mApp.getSensorConfig();
	    int co2_max = config.getMaxCo2();
	    int co2_min = config.getMinCo2(); //设置状态的textview;
	    
	    co2MaxET.setText(co2_max+"");
        co2MinET.setText(co2_min+"");
        
	    if(co2 < co2_min || co2 > co2_max) {
	    	co2StateTV.setText("预警");
	    	co2StateTV.setTextColor(Color.RED);
	    }else{
	    	co2StateTV.setText("正常");
	    	co2StateTV.setTextColor(Color.BLACK);
	    }
	  
	    
	}
}
