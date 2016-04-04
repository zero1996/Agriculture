package com.rjxy.agriculture.bean;

/**
 * 传感器当前值和阈值实体类
 * @author Zero
 */
public class SensorBean {
	
	private String name ="";
	private int value = 0;
	private int maxValue = 0;
	private int minValue = 0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

}
