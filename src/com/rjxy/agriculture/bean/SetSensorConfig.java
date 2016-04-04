package com.rjxy.agriculture.bean;

public class SetSensorConfig {
	//Co2最大最小值
		private int maxCo2;
		private boolean isMaxCo2 = false;
		private int minCo2;
		private boolean isMinCo2 = false;
		//灯光最大最小值
		private int maxLight;
		private boolean isMaxLight = false;
		private int minLight;
		private boolean isMinLight = false;
		//土壤湿度最大最小值
		private int maxSoilHumidity;
		private boolean isMaxSoilHumidity = false;
		private int minSoilHumidity;
		private boolean isMinSoilHumidity = false;
		//土壤温度最大最小值
		private int maxSoilTemperature;
		private boolean isMaxSoilTemperature = false;
		private int minSoilTemperature;
		private boolean isMinSoilTemperature = false;
		//空气湿度最大最小值
		private int maxAirHumidity;
		private boolean isMaxAirHumidity = false;
		private int minAirHumidity;
		private boolean isMinAirHumidity = false;
		//空气温度最大最小值
		private int maxAirTemperature;
		private boolean isMaxAirTemperature = false;
		private int minAirTemperature;
		private boolean isMinAirTemperature = false;
		//
		private int controlAuto;
		private boolean isControlAuto = false;
		
		public int getMaxCo2() {
			return maxCo2;
		}
		public void setMaxCo2(int maxCo2) {
			this.maxCo2 = maxCo2;
			isMaxCo2 = true;
		}
		public int getMinCo2() {
			return minCo2;
		}
		public void setMinCo2(int minCo2) {
			this.minCo2 = minCo2;
			isMaxCo2 = true;
		}
		public int getMaxLight() {
			return maxLight;
		}
		public void setMaxLight(int maxLight) {
			this.maxLight = maxLight;
			isMaxLight =true;
		}
		public int getMinLight() {
			return minLight;
		}
		public void setMinLight(int minLight) {
			this.minLight = minLight;
			isMinLight =true;
		}
		public int getMaxSoilHumidity() {
			return maxSoilHumidity;
		}
		public void setMaxSoilHumidity(int maxSoilHumidity) {
			this.maxSoilHumidity = maxSoilHumidity;
			isMaxSoilHumidity =true;
		}
		public int getMinSoilHumidity() {
			return minSoilHumidity;
		}
		public void setMinSoilHumidity(int minSoilHumidity) {
			this.minSoilHumidity = minSoilHumidity;
			isMinSoilHumidity = true;
		}
		public int getMaxSoilTemperature() {
			return maxSoilTemperature;
		}
		public void setMaxSoilTemperature(int maxSoilTemperature) {
			this.maxSoilTemperature = maxSoilTemperature;
			isMaxSoilTemperature = true;
		}
		public int getMinSoilTemperature() {
			return minSoilTemperature;
		}
		public void setMinSoilTemperature(int minSoilTemperature) {
			this.minSoilTemperature = minSoilTemperature;
			isMinSoilTemperature = true;
		}
		public int getMinAirHumidity() {
			return minAirHumidity;
		}
		public void setMinAirHumidity(int minAirHumidity) {
			this.minAirHumidity = minAirHumidity;
			isMinAirHumidity = true;
		}
		public int getMaxAirHumidity() {
			return maxAirHumidity;
		}
		public void setMaxAirHumidity(int maxAirHumidity) {
			this.maxAirHumidity = maxAirHumidity;
			isMaxAirHumidity = true;
		}
		public int getMaxAirTemperature() {
			return maxAirTemperature;
		}
		public void setMaxAirTemperature(int maxAirTemperature) {
			this.maxAirTemperature = maxAirTemperature;
			isMaxAirTemperature = true;
		}
		public int getMinAirTemperature() {
			return minAirTemperature;
		}
		public void setMinAirTemperature(int minAirTemperature) {
			this.minAirTemperature = minAirTemperature;
			isMinAirTemperature = true;
		}
		public int getControlAuto() {
			return controlAuto;
		}
		public void setControlAuto(int controlAuto) {
			this.controlAuto = controlAuto;
			isControlAuto = true;
		}
		
		/**
		 * 重置
		 * */
		public void reset() {
			isControlAuto = false;
			
			isMaxCo2 = false;
			isMinCo2 = false;
			
			isMaxLight = false;
			isMinLight = false;
			
			isMaxAirHumidity = false;
			isMinAirHumidity  = false;
			
			isMaxAirTemperature = false;
			isMinAirHumidity = false;
			
			isMaxSoilHumidity = false;
			isMinSoilHumidity = false;
			
			isMaxSoilTemperature = false;
			isMinSoilTemperature = false;
		}
		public boolean isMaxCo2() {
			return isMaxCo2;
		}
		public boolean isMinCo2() {
			return isMinCo2;
		}
		public boolean isMaxLight() {
			return isMaxLight;
		}
		public boolean isMinLight() {
			return isMinLight;
		}
		public boolean isMaxSoilHumidity() {
			return isMaxSoilHumidity;
		}
		public boolean isMinSoilHumidity() {
			return isMinSoilHumidity;
		}
		public boolean isMaxSoilTemperature() {
			return isMaxSoilTemperature;
		}
		public boolean isMinSoilTemperature() {
			return isMinSoilTemperature;
		}
		public boolean isMaxAirHumidity() {
			return isMaxAirHumidity;
		}
		public boolean isMinAirHumidity() {
			return isMinAirHumidity;
		}
		public boolean isMaxAirTemperature() {
			return isMaxAirTemperature;
		}
		public boolean isMinAirTemperature() {
			return isMinAirTemperature;
		}
		public boolean isControlAuto() {
			return isControlAuto;
		}
		
		
}
