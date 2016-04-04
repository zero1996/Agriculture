package com.rjxy.agriculture.request;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class AgricultureRequest extends BaseRequest {

	public boolean isSuccess = false;

	public boolean getisSuccess(){
		return this.isSuccess;
	}
	
	@Override
	public void onParseJasonBody(String responseStr) {
		super.onParseJasonBody(responseStr);
		if(responseStr != null){
			try {
				JSONObject object = new JSONObject(responseStr);
				if(object.has("result")){
					String result = object.getString("result");
					if("ok".equalsIgnoreCase(result)){
						this.isSuccess = true;
					}
				}else{
					this.isSuccess = false;
				}
			} catch (JSONException e) {
				e.printStackTrace();
				this.isSuccess = false;
			}
		}
	}
}
