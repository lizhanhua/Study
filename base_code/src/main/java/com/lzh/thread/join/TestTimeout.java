/**
 * 
 */
package com.lzh.thread.join;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author lizh
 * 解决控制任务的超时时间控制。用两个线程池， 一个池里是任务执行的线程，一个池里是future获取的线程
 */
public class TestTimeout {
	private static ExecutorService exe = Executors.newFixedThreadPool(100);
	private static ExecutorService exe2 = Executors.newFixedThreadPool(500);
//	public static void submit(Callable<String> call, long timeout){
//		Future<String> f = exe.submit(call);
//		try {
//			String res = f.get(timeout, TimeUnit.SECONDS);
//			System.out.println(res);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("已经超时");
//		}
//		
//	}
//	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random r = new Random();
		for (int i = 0; i < 50; i++) {
			JobThread job = new JobThread("job" + i);	
			int timeout = r.nextInt(10) * 1;
			Future<String> f = exe.submit(job);
			exe2.submit(new FutureThread(f, timeout, "job" + i));
			
		}
		
		
	}

}

class JobThread implements Callable<String> {
	private String jobName;
	public JobThread(String jobName) {
		this.jobName = jobName;
	}
	
	public String call() throws Exception {
		int sleepSecond = (int) (Math.random() * 100);
		Thread.sleep(8000 + sleepSecond);
		return this.jobName + " is ok";
	}
	
}

class FutureThread implements Callable<String> {
	private Future<String> f;
	private long timeout;
	private String jobName;
	public FutureThread (Future<String> f, long timeout, String jobName) {
		this.f = f;
		this.timeout = timeout;
		this.jobName = jobName;
	}
	public String call() {
		System.out.println(this.jobName + "超时时间是：" + timeout + "s");
		String res = "";
		try {
			res = f.get(timeout, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			res = this.jobName + "已经超时了";
//			System.out.println("超时了");
		}
		System.out.println(res);
		return res;
	}
	
}


