/**
 * 
 */
package com.lzh.thread;

/**
 * @author zhanhua.li
 *
 */
class Demo implements Runnable {
	public void run() {
		for (int x = 1; x < 100; x++) {
			System.out.println(Thread.currentThread().getName() + "..." + x);
		}
	}
}

public class JoinDemo {
	public static void main(String[] args) throws Exception {
		
		Demo d = new Demo();
		Thread t1 = new Thread(d);
		Thread t2 = new Thread(d);
		
		t1.start();
		t1.join();
		t2.start();

		for (int x = 1; x < 100; x++) {
			System.out.println(Thread.currentThread().getName() + "..." + x);
		}
	}
}
