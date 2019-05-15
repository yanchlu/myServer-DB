package com.c72;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class myServer {
	private static String localIP;
	private myServer() {
		try {
			localIP=InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	// TODO Auto-generated constructor stub
	}
	
	public static myServer getInstance() {
		return Singleton.INSTANCE.getInstance();
	}
	
	private static enum Singleton{
		INSTANCE;
		private myServer mServer;
		private Singleton() {
			mServer=new myServer();
		}
		public myServer getInstance() {
			return mServer;
		}
	}
	
	public boolean connect2DB() {
		DBManager dbManager=new DBManager.Builder("root","Caijunqi111").build();
		dbManager.initManager();
		return true;
	}
	
	public static void main(String[] args) {
		myServer server=myServer.getInstance();
		server.connect2DB();
	}
}
