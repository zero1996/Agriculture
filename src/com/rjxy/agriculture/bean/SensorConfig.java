package com.rjxy.agriculture.bean;

/**
 * 传感器阈值类
 * */
public class SensorConfig {
	
	//Co2最大最小值
	private int maxCo2=0;
	private int minCo2=0;
	//灯光最大最小值
	private int maxLight=0;
	private int minLight=0;
	//土壤湿度最大最小值
	private int maxSoilHumidity = 0;
	private int minSoilHumidity = 0;
	//土壤温度最大最小值
	private int maxSoilTemperature=0;
	private int minSoilTemperature=0;
	//空气湿度最大最小值
	private int minAirHumidity = 0;
	private int maxAirHumidity = 0;
	//空气温度最大最小值
	private int maxAirTemperature = 0;
	private int minAirTemperature = 0;
	//
	private int controlAuto;
	public int getMaxCo2() {
		return maxCo2;
	}
	public void setMaxCo2(int maxCo2) {
		this.maxCo2 = maxCo2;
	}
	public int getMinCo2() {
		return minCo2;
	}
	public void setMinCo2(int minCo2) {
		this.minCo2 = minCo2;
	}
	public int getMaxLight() {
		return maxLight;
	}
	public void setMaxLight(int maxLight) {
		this.maxLight = maxLight;
	}
	public int getMinLight() {
		return minLight;
	}
	public void setMinLight(int minLight) {
		this.minLight = minLight;
	}
	public int getMaxSoilHumidity() {
		return maxSoilHumidity;
	}
	public void setMaxSoilHumidity(int maxSoilHumidity) {
		this.maxSoilHumidity = maxSoilHumidity;
	}
	public int getMinSoilHumidity() {
		return minSoilHumidity;
	}
	public void setMinSoilHumidity(int minSoilHumidity) {
		this.minSoilHumidity = minSoilHumidity;
	}
	public int getMaxSoilTemperature() {
		return maxSoilTemperature;
	}
	public void setMaxSoilTemperature(int maxSoilTemperature) {
		this.maxSoilTemperature = maxSoilTemperature;
	}
	public int getMinSoilTemperature() {
		return minSoilTemperature;
	}
	public void setMinSoilTemperature(int minSoilTemperature) {
		this.minSoilTemperature = minSoilTemperature;
	}
	public int getMinAirHumidity() {
		return minAirHumidity;
	}
	public void setMinAirHumidity(int minAirHumidity) {
		this.minAirHumidity = minAirHumidity;
	}
	public int getMaxAirHumidity() {
		return maxAirHumidity;
	}
	public void setMaxAirHumidity(int maxAirHumidity) {
		this.maxAirHumidity = maxAirHumidity;
	}
	public int getMaxAirTemperature() {
		return maxAirTemperature;
	}
	public void setMaxAirTemperature(int maxAirTemperature) {
		this.maxAirTemperature = maxAirTemperature;
	}
	public int getMinAirTemperature() {
		return minAirTemperature;
	}
	public void setMinAirTemperature(int minAirTemperature) {
		this.minAirTemperature = minAirTemperature;
	}
	public int getControlAuto() {
		return controlAuto;
	}
	public void setControlAuto(int controlAuto) {
		this.controlAuto = controlAuto;
	}
	
}
