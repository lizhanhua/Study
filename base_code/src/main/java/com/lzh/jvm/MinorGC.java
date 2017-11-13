/**
 * 
 */
package com.lzh.jvm;

/**
 * @author lizh
 *
 */
public class MinorGC {

	private static final int _1MB = 1024 *  1024;
	public static void testAllocation() {
		byte [] b1, b2,b3,b4;
		b1 = new byte[2 * _1MB];
		b2 = new byte[2 * _1MB];
		b3 = new byte[2 * _1MB];
		b4 = new byte[2 * _1MB];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testAllocation();
	}

}
