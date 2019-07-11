package com.doit.net.unicom.udp.server;

import java.util.Scanner;

public class TestThread extends Thread {

	
	public TestThread() {
		setName("Test-Thread");
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in); 
		while(true){
			String tag = sc.nextLine();
			try {
				testProcess(tag);
			} catch (Exception e) {
				System.out.println("参数错误");
			}
		}
	}

	private void testProcess(String tag){
		
	}
	
}
