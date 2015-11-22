package com.ttsc.data.entity.area;

/**
 * 城市信息
 * 
 * @author arno.jiang
 * 
 */
public class CityInfo {
	private int id;
	private int provinceId;
	private String cityName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
