package com.ttsc.data.action.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttsc.data.action.BaseController;
import com.ttsc.data.entity.notice.NoticeInfo;
import com.ttsc.data.po.NoticeQueryPo;
import com.ttsc.data.result.BasicResult;
import com.ttsc.data.service.notice.NoticeInfoService;
import com.ttsc.data.util.Constant;

@Controller
@RequestMapping("notice")
public class NoticeController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeInfoService noticeInfoService;
	
	@RequestMapping(value = "queryList", method = RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public BasicResult<List<NoticeInfo>> queryList(HttpServletRequest request,@RequestBody  NoticeQueryPo noticePo) throws Exception {
		BasicResult<List<NoticeInfo>> rs = new BasicResult<List<NoticeInfo>>();
		try {
			if (request.getSession().getAttribute(Constant.USER_LOGIN_INFO) == null) {
				rs.setMessage("请重新登录!");
				rs.setCode("1");
				return rs;
			}
			if(noticePo.getPageNum() <= 1){
				noticePo.setStart(0);
	        }else{
	        	noticePo.setStart((noticePo.getPageNum()-1)*noticePo.getPageSize());
	        }
			
			noticePo.setType(0);
			
			List<NoticeInfo>  list= noticeInfoService.queryList(noticePo);
			rs.setSingleResult(list);
		} catch (Exception e) {
			logger.info("系统异常，公告查询失败!" + e.getMessage());
			e.printStackTrace();
			rs.setMessage("系统异常，公告查询失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}
	
	@RequestMapping(value = "getNotice", method = RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public BasicResult<NoticeInfo> getNotice(HttpServletRequest request,@RequestBody String info) throws Exception {
		BasicResult<NoticeInfo> rs = new BasicResult<NoticeInfo>();
		try {
			if (request.getSession().getAttribute(Constant.USER_LOGIN_INFO) == null) {
				rs.setMessage("请重新登录!");
				rs.setCode("1");
				return rs;
			}
			JSONObject obj = JSONObject.fromObject(info);
			String noticeId = obj.getString("noticeId");
			
			NoticeInfo notice = noticeInfoService.getNoticeById(Integer.parseInt(noticeId));
			rs.setSingleResult(notice);
		} catch (Exception e) {
			logger.info("系统异常，公告详情获取失败!" + e.getMessage());
			e.printStackTrace();
			rs.setMessage("系统异常，公告详情获取失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	}
}
