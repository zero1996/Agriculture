package com.rjxy.agriculture.bean;

public class SetSensorControl {
	private int WaterPump;
	private int Blower;
	private int Roadlamp;
	private int Buzzer;
	
	private boolean isWaterPump = false;
	private boolean isBlower = false;
	private boolean isRoadlamp = false;
	private boolean isBuzzer = false;
	
	
	public boolean isWaterPump() {
		return isWaterPump;
	}
	public boolean isBlower() {
		return isBlower;
	}
	public boolean isRoadlamp() {
		return isRoadlamp;
	}
	public boolean isBuzzer() {
		return isBuzzer;
	}
	
	public int getWaterPump() {
		return WaterPump;
	}
	public void setWaterPump(int waterPump) {
		WaterPump = waterPump;
		isWaterPump = true;
	}
	public int getBlower() {
		return Blower;
	}
	public void setBlower(int blower) {
		Blower = blower;
		isBlower = true;
	}
	public int getRoadlamp() {
		return Roadlamp;
	}
	public void setRoadlamp(int roadlamp) {
		Roadlamp = roadlamp;
		isRoadlamp = true;
	}
	public int getBuzzer() {
		return Buzzer;
	}
	public void setBuzzer(int buzzer) {
		Buzzer = buzzer;
		isBuzzer = true;
	}
	/**
	 * 重置
	 * */
	public void reset() {
		isBlower = false;
		isBuzzer = false;
		isRoadlamp = false;
		isWaterPump = false;
	}
	
}
