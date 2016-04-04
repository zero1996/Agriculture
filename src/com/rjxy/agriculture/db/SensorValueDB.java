package com.rjxy.agriculture.db;

import com.rjxy.agriculture.bean.SensorValue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class SensorValueDB extends BaseDB{

	public SensorValueDB(Context context) {
		super(context);
	}

	
	/**
	 * 将bean实体转换成ContentValues
	 * 
	 * @param data
	 * 			bean实体
	 * **/
	@Override
	public ContentValues getContentByData(Object data){
		SensorValue sensor = (SensorValue) data;
		if(sensor == null){
			return null;
		}
		ContentValues values = new ContentValues();
		values.put("co2", sensor.getCo2());
		values.put("light", sensor.getLight());
		values.put("airHumidity", sensor.getAirHumidity());
		values.put("airTemperature", sensor.getAirTemperature());
		values.put("soilHumidity", sensor.getSoilHumidity());
		values.put("soilTemperature", sensor.getSoilTemperature());
		return values;
	}

	

	@Override
	protected String getTabName() {
		return "sensor";
	}


	@Override
	protected Object getDataByCursor(Cursor cursor) {
		if(cursor == null){
			return null;
		}
		SensorValue value = new SensorValue();
		value.setCo2(cursor.getInt(cursor.getColumnIndex("co2")));
		value.setLight(cursor.getInt(cursor.getColumnIndex("light")));
		value.setAirHumidity(cursor.getInt(cursor.getColumnIndex("airHumidity")));
		value.setAirTemperature(cursor.getInt(cursor.getColumnIndex("airTemperature")));
		value.setSoilHumidity(cursor.getInt(cursor.getColumnIndex("soilHumidity")));
		value.setSoilTemperature(cursor.getInt(cursor.getColumnIndex("soilTemperature")));
		return value;
	}
	
}
