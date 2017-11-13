/**
 * 
 */
package com.lzh.thread.syn;

import java.util.concurrent.TimeUnit;

/**
 * @author lizh
 *
 */
public class Test {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Object synObj = new Object();       
		Thread t1 = new Thread(new Runnable() {  
		    public void run() {  
		        synchronized(synObj) {  
		            System.out.println("T1获取synObj的对象监视器，开始执行同步块");  
		            try {  
		                TimeUnit.MINUTES.sleep(1);  
		                System.out.println("T1在 wait()时挂起了");  
		                synObj.wait();  
		                System.out.println("T1被T2唤醒后并重新获得synObj的对象监视器，继续执行");                         
		            }catch(InterruptedException e) {  
		                e.printStackTrace();  
		            }  
		            System.out.println("T1获取synObj的对象监视器，结束同步块");  
		        }                 
		    };  
		});  
		t1.start();  
		  
		  
		Thread t2 = new Thread(new Runnable() {  
		    public void run() {  
		        System.out.println("T2启动，但是因为T1占用了synObj的对象监视器，则等待T1执行synObj.wait来释放它");  
		        synchronized(synObj) {  
		            try {  
		                System.out.println("在T1执行synObj.wait后，T2获取synObj的对象监视器，进入同步块");  
		                synObj.notify();  
		                System.out.println("T2执行synObj.notify()，T1被唤醒，但T2还在同步块中，没有释放synObj的对象监视器，T1等待synObj的对象监视器");  
		                TimeUnit.MINUTES.sleep(1);  
		                System.out.println("T2结束同步块，释放synObj的对象监视器，T1获取到synObj的对象监视器，并执行wait后面的操作");  
		            }catch(InterruptedException e) {  
		                e.printStackTrace();  
		            }  
		        }                 
		    };  
		});  
		t2.start(); 
	}

}
