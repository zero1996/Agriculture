package com.rjxy.agriculture.bean;

import java.util.LinkedList;

public class ChartPagerBean {

	public String majorValueName="";
	public int majorValueMax = 0;
	public int majorValueMin = 0;
	public LinkedList<Integer> majorValue = new LinkedList<Integer>();
	
	public ChartPagerBean(String mjorValueName){
		this.majorValueName = mjorValueName;
	}

	public int getMajorValueMax() {
		return majorValueMax;
	}

	public void setMajorValueMax(int majorValueMax) {
		this.majorValueMax = majorValueMax;
	}

	public int getMajorValueMin() {
		return majorValueMin;
	}

	public void setMajorValueMin(int majorValueMin) {
		this.majorValueMin = majorValueMin;
	}
	
	
}
