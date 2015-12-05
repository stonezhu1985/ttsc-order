package com.ttsc.data.action.area;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttsc.data.action.BaseController;
import com.ttsc.data.entity.area.AreaInfo;
import com.ttsc.data.entity.area.CityInfo;
import com.ttsc.data.entity.area.ProvinceInfo;
import com.ttsc.data.entity.area.TownInfo;
import com.ttsc.data.result.BasicResult;
import com.ttsc.data.service.area.AreaService;
import com.ttsc.data.service.area.CityService;
import com.ttsc.data.service.area.ProvinceService;
import com.ttsc.data.service.area.TownService;

/**
 * 地理区域信息交互
 * @author arno.jiang
 *
 */
@Controller
@RequestMapping("area")
public class AreaController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private TownService townServiced;
	
	@Autowired
	private AreaService areaService;
	
	/**
	 * 获取区域信息列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getAreaList", method=RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public BasicResult<List<AreaInfo>> getAreaList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<List<AreaInfo>> rs = new BasicResult<List<AreaInfo>>();
		try {
			List<AreaInfo> list = areaService.getAreaInfoList();
			AreaInfo areaInfo = null;
			for(int i=0;i<list.size();i++){
				areaInfo = list.get(i);
				List<ProvinceInfo> plist = provinceService.getProvinceListByAreaId(areaInfo.getAreaId());
				areaInfo.setProvinceList(plist);
			}
			rs.setSingleResult(list);
		} catch (Exception e) {
			logger.info("系统异常，获取省份信息列表失败!" + e.getMessage());
			e.printStackTrace();
			rs.setMessage("系统异常，获取省份信息列表失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}
	
	/**
	 * 获取省份信息列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getProvinceList", method=RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public BasicResult<List<ProvinceInfo>> getProvinceList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BasicResult<List<ProvinceInfo>> rs = new BasicResult<List<ProvinceInfo>>();
		try {
			List<ProvinceInfo> list = provinceService.getProvinceList();
			rs.setSingleResult(list);
		} catch (Exception e) {
			logger.info("系统异常，获取省份信息列表失败!" + e.getMessage());
			e.printStackTrace();
			rs.setMessage("系统异常，获取省份信息列表失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}
	
	/**
	 * 获取省份信息列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getCityList", method=RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public BasicResult<List<CityInfo>> getCityList(HttpServletRequest request,@RequestBody String info) throws Exception {
		BasicResult<List<CityInfo>> rs = new BasicResult<List<CityInfo>>();
		try {
			JSONObject obj = JSONObject.fromObject(info);
			String provinceId = obj.getString("provinceId");
			if(StringUtils.isEmpty(obj.getString("provinceId"))){
				rs.setMessage("省份信息不能为空!");
				rs.setCode("1");
				return rs;
			}
			List<CityInfo> list = cityService.getCityList(Integer.parseInt(provinceId));
			rs.setSingleResult(list);
		} catch (Exception e) {
			logger.info("系统异常，获取城市信息列表失败!" + e.getMessage());
			e.printStackTrace();
			rs.setMessage("系统异常，获取城市信息列表失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}
	
	/**
	 * 获取省份信息列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getTownList", method=RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public BasicResult<List<TownInfo>> getTownList(HttpServletRequest request,@RequestBody String info) throws Exception {
		BasicResult<List<TownInfo>> rs = new BasicResult<List<TownInfo>>();
		try {
			JSONObject obj = JSONObject.fromObject(info);
			String cityId = obj.getString("cityId");
			if(StringUtils.isEmpty(cityId)){
				rs.setMessage("城市信息不能为空!");
				rs.setCode("1");
				return rs;
			}
			List<TownInfo> list =townServiced.getTownList(Integer.parseInt(cityId));
			rs.setSingleResult(list);
		} catch (Exception e) {
			logger.info("系统异常，获取区县信息列表失败!" + e.getMessage());
			e.printStackTrace();
			rs.setMessage("系统异常，获取区县信息列表失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}
	
	

}
