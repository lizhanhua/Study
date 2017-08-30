/**
 * 
 */
package com.lzh.thread;

import java.util.Random;

/**
 * @author zhanhua.li
 *
 */
public class ThreadLocalTest {

	public static final ThreadLocal<Integer> local = new ThreadLocal<Integer>();

	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[5];
		
		
		for (int j = 0; j < 5; j++) {
			threads[j] = new Thread(new Runnable() {
				public void run() {
					Random random = new Random();
			        int s = random.nextInt(100);
					local.set(s);
					System.out.println(Thread.currentThread().getName() + " : "
							+ local.get());

				}
			}, "Thread-" + j);
		}

		for (Thread thread : threads) {
			thread.start();
		}
	}
}