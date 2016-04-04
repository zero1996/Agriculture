package com.rjxy.agriculture.request;

import org.json.JSONException;
import org.json.JSONObject;

import com.rjxy.agriculture.bean.SetSensorConfig;

public class SetSensorConfigRequest extends AgricultureRequest {
	public static final String ACTIONNAME = "setConfig";
	private SetSensorConfig config;
	public void setSensorConfig(SetSensorConfig config){
		this.config = config;
	}
	@Override
	protected String getActionName() {
		// TODO Auto-generated method stub
		return ACTIONNAME;
	}
	@Override
	public String onGetJasonBody() {
		JSONObject object = new JSONObject();
		try {
			if(config.isControlAuto()) {
				object.put("controlAuto", config.getControlAuto());
			}
			
			if(config.isMaxCo2()) {
				object.put("maxCo2", config.getMaxCo2());
			}
			if(config.isMinCo2()){
				object .put("minCo2", config.getMinCo2());
			}
			
			if(config.isMaxLight()) {
				object.put("maxLight", config.getMaxLight());
			}
			if(config.isMinLight()) {
				object.put("minLight", config.getMinLight());
			}
			
			if(config.isMaxSoilHumidity()) {
				object.put("maxSoilHumidity", config.getMaxSoilHumidity());
			}
			if(config.isMinSoilHumidity()) {
				object.put("minSoilHumidity", config.getMinSoilHumidity());
			}
			
			if(config.isMaxSoilTemperature()) {
				object.put("maxSoilTemperature", config.getMaxSoilTemperature());
			}
			if(config.isMinSoilTemperature()) {
				object.put("minSoilTemperature", config.getMinSoilTemperature());
			}
			
			if(config.isMaxAirHumidity()) {
				object.put("maxAirHumidity(", config.getMaxAirHumidity());
			}
			if(config.isMinAirHumidity()) {
				object.put("minAirHumidity", config.getMinAirHumidity());
			}
			if(config.isMaxAirTemperature()) {
				object.put("maxAirTemperature", config.getMaxAirTemperature());
			}
			if(config.isMinAirTemperature()) {
				object.put("minAirTemperature", config.getMinAirTemperature());
			}
			object.put("username", "");
			config.reset();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}
}
