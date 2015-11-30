package com.ttsc.data.action.task;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import com.ttsc.data.entity.account.Account;
import com.ttsc.data.entity.task.SpecialTask;
import com.ttsc.data.entity.task.Task;
import com.ttsc.data.entity.task.TaskAdditionalProductDetail;
import com.ttsc.data.entity.task.TaskEmptyPacketServiceDetail;
import com.ttsc.data.entity.task.TaskKeyWordDetail;
import com.ttsc.data.entity.task.TaskShowOrderTimeDetail;
import com.ttsc.data.entity.task.TaskSpecifyDetail;
import com.ttsc.data.po.TaskForm;
import com.ttsc.data.result.BasicResult;
import com.ttsc.data.service.task.SpecialTaskService;
import com.ttsc.data.service.task.TaskAdditionalProductDetailService;
import com.ttsc.data.service.task.TaskEmptyPacketServiceDetailService;
import com.ttsc.data.service.task.TaskKeyWordDetailService;
import com.ttsc.data.service.task.TaskService;
import com.ttsc.data.service.task.TaskShowOrderTimeDetailService;
import com.ttsc.data.service.task.TaskSpecifyDetailService;
import com.ttsc.data.util.Base64Util;
import com.ttsc.data.util.Constant;
import com.ttsc.data.util.DateUtil;
import com.ttsc.data.util.ReadPropertiesUtil;

/**
 * 任务管理
 * @author arno.jiang
 *
 */
@Controller
@RequestMapping("task")
public class TaskController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private SpecialTaskService specialTaskService;
	
	@Autowired
	private TaskAdditionalProductDetailService taskAdditionalProductDetailService;
	
	@Autowired
	private TaskEmptyPacketServiceDetailService taskEmptyPacketServiceDetailService;
	
	@Autowired
	private TaskKeyWordDetailService taskKeyWordDetailService;
	
	@Autowired
	private TaskSpecifyDetailService taskSpecifyDetailService;
	
	@Autowired
	private TaskShowOrderTimeDetailService taskShowOrderTimeDetailService;
	
	@RequestMapping(value = "saveTask", method=RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public BasicResult<String> saveTask(HttpServletRequest request,@RequestBody TaskForm form) throws Exception {
		BasicResult<String> rs = new BasicResult<String>();
		try {
			if (request.getSession().getAttribute(Constant.USER_LOGIN_INFO) == null) {
				rs.setMessage("请重新登录!");
				rs.setCode("1");
				return rs;
			}
			
			Account account = (Account)request.getSession().getAttribute(Constant.USER_LOGIN_INFO);
			int userId = account.getId();
			
			Task task = form.getTask();
			List<TaskAdditionalProductDetail> taskAdditionalProductDetailList = form.getTaskAdditionalProductDetailList(); // 附加商品
			TaskEmptyPacketServiceDetail taskEmptyPacketServiceDetail = form.getTaskEmptyPacketServiceDetail();// 空包服务
			List<TaskKeyWordDetail> taskKeyWordDetailList = form.getTaskKeyWordDetailList();// 关键字
			List<TaskSpecifyDetail> taskSpecifyDetailList = form.getTaskSpecifyDetailList();// 指定好评
			List<TaskShowOrderTimeDetail> taskShowOrderTimeDetailList = form.getTaskShowOrderTimeDetailList();//放单时间
			

			String photoName = this.changeImage(userId, task.getCommodityPhoto());
			task.setCommodityPhoto(photoName);

			//保存任务主表
			int taskId = taskService.saveTask(task);
			
			//保存附加商品信息
			if(taskAdditionalProductDetailList != null && taskAdditionalProductDetailList.size()>0){
				TaskAdditionalProductDetail taskAdditionalProductDetail = null;
				for(int i =0;i<taskAdditionalProductDetailList.size();i++){
					taskAdditionalProductDetail = taskAdditionalProductDetailList.get(i);
					taskAdditionalProductDetail.setTaskId(taskId);
					photoName = this.changeImage(userId, taskAdditionalProductDetail.getCommodityPhoto());
					taskAdditionalProductDetail.setCommodityPhoto(photoName);
					taskAdditionalProductDetailService.saveTaskAdditionalProductDetail(taskAdditionalProductDetail);
				}
			}
			
			//保存空包服务
			if(taskEmptyPacketServiceDetail != null){
				taskEmptyPacketServiceDetail.setTaskId(taskId);
				taskEmptyPacketServiceDetailService.saveTaskEmptyPacketServiceDetail(taskEmptyPacketServiceDetail);
			}
			
			//保存关键字信息
			if(taskKeyWordDetailList != null && taskKeyWordDetailList.size()>0){
				TaskKeyWordDetail taskKeyWordDetail = null;
				for(int i =0;i<taskKeyWordDetailList.size();i++){
					taskKeyWordDetail = taskKeyWordDetailList.get(i);
					taskKeyWordDetail.setTaskId(taskId);
					taskKeyWordDetailService.saveTaskKeyWordDetail(taskKeyWordDetail);
				}
			}

			//保存指定好评信息
			if(taskSpecifyDetailList != null && taskSpecifyDetailList.size()>0){
				TaskSpecifyDetail taskSpecifyDetail = null;
				for(int i =0;i<taskSpecifyDetailList.size();i++){
					taskSpecifyDetail = taskSpecifyDetailList.get(i);
					taskSpecifyDetail.setTaskId(taskId);
					if(taskSpecifyDetail.getType() == 2){
						if(StringUtils.isNotEmpty(taskSpecifyDetail.getFirst())){
							photoName = this.changeImage(userId, taskSpecifyDetail.getFirst());
							taskSpecifyDetail.setFirst(photoName);
						}
						if(StringUtils.isNotEmpty(taskSpecifyDetail.getSecond())){
							photoName = this.changeImage(userId, taskSpecifyDetail.getSecond());
							taskSpecifyDetail.setSecond(photoName);
						}
						if(StringUtils.isNotEmpty(taskSpecifyDetail.getThird())){
							photoName = this.changeImage(userId, taskSpecifyDetail.getThird());
							taskSpecifyDetail.setThird(photoName);
						}
						if(StringUtils.isNotEmpty(taskSpecifyDetail.getFour())){
							photoName = this.changeImage(userId, taskSpecifyDetail.getFour());
							taskSpecifyDetail.setFour(photoName);
						}
						if(StringUtils.isNotEmpty(taskSpecifyDetail.getFive())){
							photoName = this.changeImage(userId, taskSpecifyDetail.getFive());
							taskSpecifyDetail.setFive(photoName);
						}
					}
					taskSpecifyDetailService.save(taskSpecifyDetail);
				}
			}
			
			//放单时间
			if(taskShowOrderTimeDetailList != null && taskShowOrderTimeDetailList.size()>0){
				TaskShowOrderTimeDetail taskShowOrderTimeDetail = null;
				for(int i =0;i<taskShowOrderTimeDetailList.size();i++){
					taskShowOrderTimeDetail = taskShowOrderTimeDetailList.get(i);
					taskShowOrderTimeDetail.setTaskId(taskId);
					taskShowOrderTimeDetailService.save(taskShowOrderTimeDetail);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("系统异常，任务保存失败!" + e.getMessage());
			rs.setMessage("系统异常，任务保存失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	} 
	
	@RequestMapping(value = "saveSpecialTask", method=RequestMethod.POST, headers = {"content-type=application/json","content-type=application/xml"})
	@ResponseBody
	public BasicResult<String> saveSpecialTask(HttpServletRequest request,@RequestBody SpecialTask task) throws Exception {
		BasicResult<String> rs = new BasicResult<String>();
		try {
			if (request.getSession().getAttribute(Constant.USER_LOGIN_INFO) == null) {
				rs.setMessage("请重新登录!");
				rs.setCode("1");
				return rs;
			}
			
			specialTaskService.saveTask(task);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("系统异常，特殊任务保存失败!" + e.getMessage());
			rs.setMessage("系统异常，特殊任务保存失败!");
			rs.setCode("1");
			return rs;
		}
		return rs;
	} 
	
	private String changeImage(int userId, String photoBaseStr){
		String basePath = ReadPropertiesUtil.get("task.file.path");
		String[] photo = photoBaseStr.split(",");
		String fileName = UUID.randomUUID() + "." + photo[0];
		String saveFilePath = userId+"/"+DateUtil.getDateFormatter(new Date(), "yyyyMMdd")+"/";
		Base64Util.generateImage(basePath+saveFilePath,fileName,photo[1]);
		return saveFilePath+fileName;
	}
}
