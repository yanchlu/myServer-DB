package com.c72;
import java.sql.Timestamp;
public class Task {
	private int tid;
	private int uid;
	private String title;
	private String detail;
	private int money;
	private String type;
	private int total_num;
	private int current_num;
	private Timestamp start_time;
	private Timestamp end_time;
	private String state;
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getTid() {
		return tid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getUid() {
		return uid;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDetail() {
		return detail;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getMoney() {
		return money;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}
	public int getTotal_num() {
		return total_num;
	}
	public void setCurrent_num(int current_num) {
		this.current_num = current_num;
	}
	public int getCurrent_num() {
		return current_num;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
}
