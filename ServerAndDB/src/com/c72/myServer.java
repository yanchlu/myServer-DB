package com.c72;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class myServer {
	private enum ServerStatus{
		CLOSED,OPEN
	}
	private DBManager dbManager;
	private ServerStatus flag=ServerStatus.OPEN;
	private myServer() {
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
		dbManager=new DBManager.Builder("root","Caijunqi111").build();
		dbManager.initManager();
		return true;
	}
	
	public void waitForRequest() {
		System.out.println("Waiting for Request...");
		try {
			DatagramSocket socket=new DatagramSocket(6666);
			socket.setSoTimeout(5000);
			ExecutorService executorService=Executors.newCachedThreadPool();
			while(flag==ServerStatus.OPEN) {
				DatagramPacket rcv_pac=new DatagramPacket(new byte[1024], 1024);
				try {
					socket.receive(rcv_pac);
				}catch (SocketTimeoutException e) {
					continue;
					// TODO: handle exception
				}
				String result=(new String(rcv_pac.getData(),0,rcv_pac.getLength(),"ASCII"));
				executorService.execute(()-> treatRequest(result,rcv_pac.getAddress(),rcv_pac.getPort()));
			}
			System.out.println("Stop Server...");
			executorService.awaitTermination(10, TimeUnit.SECONDS);
			socket.close();
			dbManager.disConnect();
			dbManager=null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void treatRequest(String request,InetAddress address,int port) {
		byte[] response=null;
		DatagramSocket socket=null;
		try {
			socket=new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(request.contains("select")||request.contains("SELECT")) {
			response=dbManager.select(request).getBytes();
		}else if(request.contains("delete")||request.contains("DELETE")
			   ||request.contains("update")||request.contains("UPDATE")
			   ||request.contains("insert")||request.contains("INSERT")) {
			DBinsertStatus status=dbManager.update(request);
			switch (status) {
			case NOTHING_CHANGED:
				response="Fail".getBytes();
				break;
			case SOMETHING_CHANGED:
				response="Done".getBytes();
				break;
			case EXCEPTION_THROWN:
				response="Error".getBytes();
				break;
			default:
				break;
			}
		}
		DatagramPacket req_Packet=new DatagramPacket(response,response.length,address,port);
		try {
			socket.send(req_Packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void RenderGUI() {
		JFrame frame = new JFrame("Simple Server Window");
        // Setting the width and height of frame
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();    
        // 添加面板
        frame.add(panel);
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("Server: On");
        userLabel.setBounds(10,20,200,50);
        panel.add(userLabel);


        // 创建登录按钮
        JButton loginButton = new JButton("CLOSE SERVER");
        loginButton.setBounds(10, 80, 200, 50);
        panel.add(loginButton);
        loginButton.addActionListener((e)->{
        	switch (flag) {
			case OPEN:
				setFlag(ServerStatus.CLOSED);
				loginButton.setText("Closing...");
				userLabel.setText("Server:Closing");
				loginButton.setFocusable(false);
				break;
			case CLOSED:
				setFlag(ServerStatus.OPEN);
				loginButton.setText("CLOSE SERVER");
			default:
				break;
			}
        });

        // 设置界面可见
        frame.setVisible(true);
	}
	public void setFlag(ServerStatus flag) {
		this.flag=flag;
	}
	
	public static void main(String[] args) {
		myServer server=myServer.getInstance();
		server.RenderGUI();
		if(server.connect2DB()) {
			System.out.println("Connected to DataBase!");
			server.waitForRequest();
			System.out.println("Server closed");
		}else {
			System.out.println("Connect failed!");
		}
	}
}
