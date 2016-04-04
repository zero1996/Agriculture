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

public class SetSoilDialog extends BaseDialog {
	private Button setBtn;
	private TextView closeTV;
	private ClientApp mApp;
	private Context context;
	
	private TextView soilTvalueTV;
	private TextView soilTstateTV;
	private EditText soilTMinET;
	private EditText soilTMaxET;
	
	private TextView soilHvalueTV;
	private TextView soilHstateTV;
	private EditText soilHMinET;
	private EditText soilHMaxET;

	public SetSoilDialog(Context context) {
		super(context);
		mApp = (ClientApp) context.getApplicationContext();
		this.context = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_set_soil);
		setBtn = (Button) findViewById(R.id.dialog_set_soil_ok);
		closeTV = (TextView) findViewById(R.id.dialog_set_soil_close);
		
		soilTvalueTV = (TextView) findViewById(R.id.dialog_set_soil_temperature_now);
		soilTstateTV = (TextView) findViewById(R.id.dialog_set_soil_temperature_state);
		soilTMinET = (EditText) findViewById(R.id.dialog_set_soil_temperature_min);
		soilTMaxET = (EditText) findViewById(R.id.dialog_set_soil_temperature_max);
		
		soilHvalueTV = (TextView) findViewById(R.id.dialog_set_soil_humidity_now);
		soilHstateTV = (TextView) findViewById(R.id.dialog_set_soil_humidity_state);
		soilHMinET = (EditText) findViewById(R.id.dialog_set_soil_humidity_min);
		soilHMaxET = (EditText) findViewById(R.id.dialog_set_soil_humidity_max);
		setSoilDialogValue();
		setCommonBtn(setBtn, closeTV);
	}

	@Override
	protected void setConfirmBtnOnClick() {String stMin = soilTMinET.getText().toString().trim();
	String stMax = soilTMaxET.getText().toString().trim();
	String shMin = soilHMinET.getText().toString().trim();
	String shMax = soilHMaxET.getText().toString().trim();
	if(isConfigValueAvailable(true, stMin, stMax, shMin, shMax)){//阈值符合要求
		SetSensorConfig config = new SetSensorConfig();
		final int int_shmax = Integer.parseInt(shMax);
		final int int_shmin = Integer.parseInt(shMin);
		final int int_stmax = Integer.parseInt(stMax);
		final int int_stmin = Integer.parseInt(stMin);// 转换成整形数值
		config.setMaxSoilHumidity(int_shmax);
		config.setMinSoilHumidity(int_shmin);
		config.setMaxSoilTemperature(int_stmax);
		config.setMinSoilTemperature(int_stmin);
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
							config.setMaxSoilHumidity(int_shmax);
							config.setMinSoilHumidity(int_shmin);
							config.setMaxSoilTemperature(int_stmax);
							config.setMinSoilTemperature(int_stmin);//请求成功后设置值到CLientApp
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
	
	
	/***
	 * 设置空气温湿度提示内的textview值
	 * */
	public  void setSoilDialogValue() {
        SensorValue value = mApp.getSensorValue();
        int sh_value = value.getSoilHumidity();
        int st_value = value.getSoilTemperature();
    	soilHvalueTV.setText(sh_value+"");
		soilTvalueTV.setText(st_value+"");//设置土壤湿度和温度的当前值
		
	    SensorConfig config = mApp.getSensorConfig();
	    int sh_max = config.getMaxSoilHumidity();
	    int sh_min = config.getMinSoilHumidity();
	    int st_max = config.getMaxSoilTemperature();
	    int st_min = config.getMinSoilTemperature();//设置状态的textview;
	    
	    soilHMaxET.setText(sh_max+"");
	    soilHMinET.setText(sh_min+"");
        
	    soilTMaxET.setText(st_max+"");
        soilTMinET.setText(st_min+"");
        
	    if(sh_value < sh_min || sh_value > sh_max) {
	    	soilHstateTV.setText("预警");
	    	soilHstateTV.setTextColor(Color.RED);
	    }else{
	    	soilHstateTV.setText("正常");
	    	soilHstateTV.setTextColor(Color.BLACK);
	    }
	    if(st_value < st_min || st_value > st_max) {
	    	soilTstateTV.setText("预警");
	    	soilTstateTV.setTextColor(Color.RED);
	    }else{
	    	soilTstateTV.setText("正常");
	    	soilTstateTV.setTextColor(Color.BLACK);
	    }
	    
	}
	

}
