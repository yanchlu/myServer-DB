package com.c72;

import java.sql.Timestamp;

public class Message {
	private int tid;
	private int uid;
	private Timestamp time;
	private int floor;
	private String detail;
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
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getFloor() {
		return floor;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDetail() {
		return detail;
	}
}
