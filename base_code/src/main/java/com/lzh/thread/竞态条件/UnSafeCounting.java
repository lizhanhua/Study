
package com.lzh.thread.竞态条件;

/**
 * @author zhanhua.li
 */
public class UnSafeCounting {

	private long count = 0;
	public long getCount() {
		return count;
	}
	
	public synchronized void doCount() {
		this.count++;
		System.out.println(count);	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UnSafeCounting usc = new UnSafeCounting();
		for (int i = 0; i < 1000; i++) {
			usc.doCount();
		}
		long count = usc.getCount();
		System.out.println(count);
	}

}
