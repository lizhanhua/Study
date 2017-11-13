/**
 * 
 */
package com.lzh.thread.join;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author lizh
 *
 */
class RunThread implements Runnable{
	private CyclicBarrier cb;
	private String name;
	public RunThread(CyclicBarrier cb, String name){
		this.cb = cb;
		this.name = name;
	}		
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println(this.name + ":准备好了");
			cb.await();
			System.out.println(this.name + ":完成了");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

public class CyclicBarrierTest2 {
	private static CyclicBarrier cb = new CyclicBarrier(6);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 6; i++) {
			new Thread(new RunThread(cb, "Runner" + i)).start();
		}
	}	
}
