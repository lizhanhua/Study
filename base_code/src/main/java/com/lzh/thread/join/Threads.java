package com.lzh.thread.join;

public class Threads {

	public static void main(String[] args) {
		SubThread thread = new SubThread();
		thread.start();
		// ���̴߳�����������,�����߳��첽ȥִ��.
//		mainThreadOtherWork();
		System.out.println("now waiting sub thread done.");
		// ���߳������������,�ȴ����̵߳Ľ���, ����joinϵ�еķ�������(�������ó�ʱʱ��)
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("now all done.");
	}

	private static void mainThreadOtherWork() {
		System.out.println("main thread work start");
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main thread work done.");
	}

	public static class SubThread extends Thread {
		@Override
		public void run() {
			working();
		}

		private void working() {
			System.out.println("sub thread start working.");
			busy();
			System.out.println("sub thread stop working.");
		}

		private void busy() {
			try {
				sleep(5000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
