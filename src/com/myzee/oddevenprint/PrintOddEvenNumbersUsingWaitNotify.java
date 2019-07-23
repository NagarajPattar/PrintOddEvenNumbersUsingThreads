package com.myzee.oddevenprint;

public class PrintOddEvenNumbersUsingWaitNotify {

	public static int count = 0;
	public static boolean switchFlag = true;
	private static Object obj = new Object();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EvenThread et = new EvenThread(obj);
		OddThread ot = new OddThread(obj);
		
		et.start();
		ot.start();
	}
}

class EvenThread extends Thread{
	
	private Object obj;
	public EvenThread(Object obj) {
		// TODO Auto-generated constructor stub
		this.obj = obj;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(PrintOddEvenNumbersUsingWaitNotify.count < 20) {
			synchronized(obj) {
			if(PrintOddEvenNumbersUsingWaitNotify.switchFlag == true) {
				System.out.println(" even thread - " + PrintOddEvenNumbersUsingWaitNotify.count++);
				PrintOddEvenNumbersUsingWaitNotify.switchFlag = false;
				obj.notify();
			}else {
				try {
					obj.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		}
	}
}

class OddThread extends Thread {
	private Object obj;
	
	public OddThread(Object obj) {
		// TODO Auto-generated constructor stub
		this.obj = obj;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(PrintOddEvenNumbersUsingWaitNotify.count < 20) {
			synchronized(obj) {
			
			if(PrintOddEvenNumbersUsingWaitNotify.switchFlag == false) {
				System.out.println(" odd thread  - " + PrintOddEvenNumbersUsingWaitNotify.count++);
				PrintOddEvenNumbersUsingWaitNotify.switchFlag = true;
				obj.notify();
			} else {
				try {
					obj.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}
		
	}
	
}
