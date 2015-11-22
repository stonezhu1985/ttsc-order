package com.ttsc.data.entity.task;
/**
 * 指定好评
 * @author stone.zhu
 *
 */
public class TaskSpecifyDetail {
	//id
	private int id;
	//类型：0，关键字任务；1，指定评语；2，指定图文
	private int type;
	//第一个位置
	private String first;
	//第二个位置
	private String second;
	//第三个位置
	private String third;
	//第四个位置
	private String four;
	//第五个位置
	private String five;
	//好评
	private String praise;
	//任务id
	private int taskId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getThird() {
		return third;
	}
	public void setThird(String third) {
		this.third = third;
	}
	public String getFour() {
		return four;
	}
	public void setFour(String four) {
		this.four = four;
	}
	public String getFive() {
		return five;
	}
	public void setFive(String five) {
		this.five = five;
	}
	public String getPraise() {
		return praise;
	}
	public void setPraise(String praise) {
		this.praise = praise;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
}
