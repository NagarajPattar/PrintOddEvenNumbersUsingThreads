package com.myzee.oddevenprint;

public class PrintOddEvenNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumberCount obj = new NumberCount(0, true);
		EvenThreadPrint t1 = new EvenThreadPrint(obj);
		OddThreadPrint t2 = new OddThreadPrint(obj);
		
		t1.start();
		t2.start();
	}

}

class NumberCount {
	int count = 0;
	boolean flag = true;
	
	NumberCount(int count, boolean flag) {
		this.count = count;
		this.flag = flag;
	}
}

class EvenThreadPrint extends Thread {
	NumberCount obj;
	public EvenThreadPrint(NumberCount obj) {
		this.obj = obj;
	}
	public void run() {
		while(true) {
			synchronized(obj) {
			if(obj.flag == true) {
				System.out.println(Thread.currentThread().getName() + " - " + obj.count++);
				obj.flag = false;
				try {
					obj.wait();
					obj.notify();
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			}
		}
	}
}

class OddThreadPrint extends Thread {
	NumberCount obj;
	public OddThreadPrint(NumberCount obj) {
		this.obj = obj;
	}
	public void run() {
		while(true) {
			synchronized(obj) {
			if(obj.flag == false) {
				System.out.println(Thread.currentThread().getName() + " - " + obj.count++);
				obj.flag = true;
				try {
					obj.notify();
					obj.wait();
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			}
		}
	}
}

