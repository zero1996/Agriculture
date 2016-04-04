package com.rjxy.agriculture.bean;

/**
 * 传感器当前值
 * */
public class SensorValue {

	private int co2;
	
	private int light;
	
	private int soilHumidity;
	
	private int soilTemperature;
	
	private int airHumidity;
	
	private int airTemperature;
	
	public SensorValue(){}
	public SensorValue(int co2,int light,int soilHumidity,int soilTemperature,int airHumidity,int airTemperature){
		this.co2 = co2;
		this.light = light;
		this.soilHumidity = soilHumidity;
		this.soilTemperature=soilTemperature;
		this.airHumidity=airHumidity;
		this.airTemperature=airTemperature;
	}
	
	public int getCo2() {
		return co2;
	}

	public void setCo2(int co2) {
		this.co2 = co2;
	}

	public int getLight() {
		return light;
	}

	public void setLight(int light) {
		this.light = light;
	}

	public int getSoilHumidity() {
		return soilHumidity;
	}

	public void setSoilHumidity(int soilHumidity) {
		this.soilHumidity = soilHumidity;
	}

	public int getSoilTemperature() {
		return soilTemperature;
	}

	public void setSoilTemperature(int soilTemperature) {
		this.soilTemperature = soilTemperature;
	}

	public int getAirHumidity() {
		return airHumidity;
	}

	public void setAirHumidity(int airHumidity) {
		this.airHumidity = airHumidity;
	}

	public int getAirTemperature() {
		return airTemperature;
	}

	public void setAirTemperature(int airTemperature) {
		this.airTemperature = airTemperature;
	}
	
	
}
