package com.lzh.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<String>> resultList = new ArrayList<Future<String>>();

		// ����10������ִ��
		for (int i = 0; i < 10; i++) {
			// ʹ��ExecutorServiceִ��Callable���͵����񣬲������������future������
			Future<String> future = executorService.submit(new TaskWithResult(i));
			// ������ִ�н���洢��List��
			resultList.add(future);
		}


		for (Future<String> fs : resultList) {
			try {
//				if(fs.isDone()) 
					System.out.println(fs.get()); // ��ӡ�����̣߳�����ִ�еĽ��
//				} 
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				// ����һ��˳��رգ�ִ����ǰ�ύ�����񣬵�����������������Ѿ��رգ������û���������á�
				executorService.shutdown();
			}
		}
		
		System.out.println("ͬ����ɣ�");
	}
}

class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	public String call() throws Exception {
		Random r = new Random();
		int waitTime = r.nextInt(100);
		Thread.sleep(waitTime);
		return "call()�������Զ����ã�����Ľ���ǣ�" + id + " "
				+ Thread.currentThread().getName() + ",sleep��" + waitTime ;
	}
}