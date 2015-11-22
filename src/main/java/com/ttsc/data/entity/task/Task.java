package com.ttsc.data.entity.task;

import java.util.Date;

/**
 * 任务
 * @author stone.zhu
 *
 */
public class Task {
	//任务id
	private int id;
	//任务绑定店铺的id
	private int userShopBindId;
	//商品名称
	private String commodityName;
	//商品链接
	private String commodityHref;
	//商品主图
	private String commodityPhoto;
	//商品所在地
	private String commodityAddress;
	//商品最大金额
	private int maxPrice;
	//商品最小金额
	private int minPrice;
	//是否包邮
	private int freeShipping;
	//商品单价
	private int commodityPrice;
	//订单含有商品的个数:是否包邮：1，包邮；0，不包邮
	private int orderHaveCommodityNum;
	//是否是直通车任务：0，不是；1，是
	private int isStraightTrainTask;
	//订单总数
	private int orderNum;
	//关键字总数
	private int keyWordsCount;
	//留言
	private String message;
	//注意事项
	private String note;
	//特色留言
	private String characteristicMessage;
	//延长期限：月
	private int extendedPeriod;
	//隔两天的任务单数
	private int theNextTaskTwo;
	//隔5天任务单数
	private int theNextTaskFive;
	//保证钻石的比例,需要除以100
	private int guaranteeRatio;
	//回头客的比例,需要除以100
	private int repeatCustomersRatio;
	//保证钻石数据
	private int guaranteeNum;
	//保证回头客数据
	private int repeatCustomersNum;
	//性别
	private int sex;
	//年龄
	private int age;
	//是否花呗账号
	private int flower;
	//任务插队：0,不插队；1，插队
	private int taskQueue;
	//抢单买家
	private int strongSingleBuyer;
	//小费
	private int grabSingletip;
	//任务类型：0，垫付任务；1，浏览任务；2，隔天任务；3，定制回头客任务；4，全钻任务；5，直通车任务；6，垫付任务；7点击任务；
	private int taskType;
	//终端，0：pc端，1：移动端
	private int terminal;
	//创建时间
	private Date createTime;
	//状态
	private int status;
	//商铺id
	private int userShopId;
	
	public int getUserShopId() {
		return userShopId;
	}
	public void setUserShopId(int userShopId) {
		this.userShopId = userShopId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserShopBindId() {
		return userShopBindId;
	}
	public void setUserShopBindId(int userShopBindId) {
		this.userShopBindId = userShopBindId;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCommodityHref() {
		return commodityHref;
	}
	public void setCommodityHref(String commodityHref) {
		this.commodityHref = commodityHref;
	}
	public String getCommodityPhoto() {
		return commodityPhoto;
	}
	public void setCommodityPhoto(String commodityPhoto) {
		this.commodityPhoto = commodityPhoto;
	}
	public String getCommodityAddress() {
		return commodityAddress;
	}
	public void setCommodityAddress(String commodityAddress) {
		this.commodityAddress = commodityAddress;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getFreeShipping() {
		return freeShipping;
	}
	public void setFreeShipping(int freeShipping) {
		this.freeShipping = freeShipping;
	}
	public int getCommodityPrice() {
		return commodityPrice;
	}
	public void setCommodityPrice(int commodityPrice) {
		this.commodityPrice = commodityPrice;
	}
	public int getOrderHaveCommodityNum() {
		return orderHaveCommodityNum;
	}
	public void setOrderHaveCommodityNum(int orderHaveCommodityNum) {
		this.orderHaveCommodityNum = orderHaveCommodityNum;
	}
	public int getIsStraightTrainTask() {
		return isStraightTrainTask;
	}
	public void setIsStraightTrainTask(int isStraightTrainTask) {
		this.isStraightTrainTask = isStraightTrainTask;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getKeyWordsCount() {
		return keyWordsCount;
	}
	public void setKeyWordsCount(int keyWordsCount) {
		this.keyWordsCount = keyWordsCount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCharacteristicMessage() {
		return characteristicMessage;
	}
	public void setCharacteristicMessage(String characteristicMessage) {
		this.characteristicMessage = characteristicMessage;
	}
	public int getExtendedPeriod() {
		return extendedPeriod;
	}
	public void setExtendedPeriod(int extendedPeriod) {
		this.extendedPeriod = extendedPeriod;
	}
	public int getTheNextTaskTwo() {
		return theNextTaskTwo;
	}
	public void setTheNextTaskTwo(int theNextTaskTwo) {
		this.theNextTaskTwo = theNextTaskTwo;
	}
	public int getTheNextTaskFive() {
		return theNextTaskFive;
	}
	public void setTheNextTaskFive(int theNextTaskFive) {
		this.theNextTaskFive = theNextTaskFive;
	}
	public int getGuaranteeRatio() {
		return guaranteeRatio;
	}
	public void setGuaranteeRatio(int guaranteeRatio) {
		this.guaranteeRatio = guaranteeRatio;
	}
	public int getRepeatCustomersRatio() {
		return repeatCustomersRatio;
	}
	public void setRepeatCustomersRatio(int repeatCustomersRatio) {
		this.repeatCustomersRatio = repeatCustomersRatio;
	}
	public int getGuaranteeNum() {
		return guaranteeNum;
	}
	public void setGuaranteeNum(int guaranteeNum) {
		this.guaranteeNum = guaranteeNum;
	}
	public int getRepeatCustomersNum() {
		return repeatCustomersNum;
	}
	public void setRepeatCustomersNum(int repeatCustomersNum) {
		this.repeatCustomersNum = repeatCustomersNum;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getFlower() {
		return flower;
	}
	public void setFlower(int flower) {
		this.flower = flower;
	}
	public int getTaskQueue() {
		return taskQueue;
	}
	public void setTaskQueue(int taskQueue) {
		this.taskQueue = taskQueue;
	}
	public int getStrongSingleBuyer() {
		return strongSingleBuyer;
	}
	public void setStrongSingleBuyer(int strongSingleBuyer) {
		this.strongSingleBuyer = strongSingleBuyer;
	}
	public int getGrabSingletip() {
		return grabSingletip;
	}
	public void setGrabSingletip(int grabSingletip) {
		this.grabSingletip = grabSingletip;
	}
	public int getTaskType() {
		return taskType;
	}
	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}
	public int getTerminal() {
		return terminal;
	}
	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
