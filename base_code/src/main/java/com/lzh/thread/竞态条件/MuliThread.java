/**
 * 
 */
package com.lzh.thread.竞态条件;

/**
 * @author zhanhua.li
 *
 */
public class MuliThread extends Thread {
	
	private UnSafeCounting unSafeCounting;
	
	public MuliThread (UnSafeCounting unSafeCounting) {
		this.unSafeCounting = unSafeCounting;
	}

	public void run() {
		for (int i = 0; i < 2; i++) {
			this.unSafeCounting.doCount();
		}
		
	}

	public static void main(String[] args) {
		UnSafeCounting unSafeCounting = new UnSafeCounting();
		for (int i = 0; i < 500; i++) {
			new MuliThread(unSafeCounting).start();
		}
//		System.out.println(unSafeCounting.getCount());
	}

}
