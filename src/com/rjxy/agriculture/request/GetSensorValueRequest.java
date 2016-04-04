package com.rjxy.agriculture.request;

import org.json.JSONException;
import org.json.JSONObject;

import com.rjxy.agriculture.bean.SensorValue;

/**
 * 获取传感器当前值类
 * */
public class GetSensorValueRequest extends AgricultureRequest {

	public static final String ACTION = "getSensor";

	public SensorValue sensor;

	public void setSensorValue(SensorValue sensor){
		this.sensor = sensor;
	}

	@Override
	protected String getActionName() {
		return ACTION;
	}

	@Override
	public void onParseJasonBody(String responseStr) {
		super.onParseJasonBody(responseStr);
		if(responseStr != null){
			try {
				JSONObject object = new JSONObject(responseStr);
				if(object.has("result")){
					String result = object.getString("result");
					if(result.equalsIgnoreCase("ok")){
						isSuccess = true;

						if(object.has("co2")){
							sensor.setCo2(object.getInt("co2"));
						}
						if(object.has("light")){
							sensor.setLight(object.getInt("light"));
						}
						if(object.has("airHumidity")){
							sensor.setAirHumidity(object.getInt("airHumidity"));
						}
						if(object.has("airTemperature")){
							sensor.setAirTemperature(object.getInt("airTemperature"));
						}
						if(object.has("soilTemperature")){
							sensor.setSoilTemperature(object.getInt("soilTemperature"));
						}
						if(object.has("soilHumidity")){
							sensor.setSoilHumidity(object.getInt("soilHumidity"));
						}
					}
				}else{
					isSuccess = false;
				}
			}catch (JSONException e) {
				isSuccess = false;
				e.printStackTrace();
			}

		} 
	}
}

