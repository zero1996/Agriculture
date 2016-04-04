package com.rjxy.agriculture.request;

import org.json.JSONException;
import org.json.JSONObject;

import com.rjxy.agriculture.bean.SetSensorControl;

public class SetControlRequest extends AgricultureRequest {
	public static final String ACTIONNAME = "control";
	private SetSensorControl control;
	@Override
	protected String getActionName() {
		// TODO Auto-generated method stub
		return ACTIONNAME;
	}
	public void setControl(SetSensorControl control){
		this.control = control;
	}
	@Override
	public String onGetJasonBody() {
		JSONObject object = new JSONObject();
		try {
			if(control.isBlower()) {
				object.put("Blower", control.getBlower());
			}
			if(control.isBuzzer()) {
				object.put("Buzzer", control.getBuzzer());
			}
			if(control.isRoadlamp()) {
				object.put("Roadlamp", control.getRoadlamp());
			}
			if(control.isWaterPump()) {
				object.put("WaterPump", control.getWaterPump());
			}
			object.put("username", "");
			control.reset();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}
}
