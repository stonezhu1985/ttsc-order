package com.ttsc.data.entity.area;

/**
 * 区县信息
 * 
 * @author arno.jiang
 * 
 */
public class TownInfo {
	private int id;
	private int cityId;
	private String townName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}
}
