package com.c72;
import java.sql.Timestamp;
public class UserJoins {
	private int tid;
	private int uid;
	private Timestamp time;
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
}
