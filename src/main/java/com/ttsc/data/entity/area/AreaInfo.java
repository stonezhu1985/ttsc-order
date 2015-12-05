package com.ttsc.data.entity.area;

import java.util.List;

/**
 * 区域信息
 * @author arno.jiang
 *
 */
public class AreaInfo {
	private int areaId;
	private String areaName;
	
	private List<ProvinceInfo> provinceList;

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<ProvinceInfo> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<ProvinceInfo> provinceList) {
		this.provinceList = provinceList;
	}
}
