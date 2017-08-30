package com.lzh.thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 *  �ػ��߳�
 */
public class Daemons {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Thread d = new Thread(new Daemon());
        d.setDaemon(true); //�����������߳�ǰ����
        d.start();
        Thread t = new Thread(new ThreadNormal());
        t.start();
    }
}

class ThreadNormal implements Runnable {
    
	public void run() {
//		for (int i = 0; i < 100; i++) {
//			System.out.println(i);
//		}
		int i = 0;
		while (true) {
			i++;
          System.out.println("ִ�е��߳�" + i);
      }
    }
}

class Daemon implements Runnable {
    private Thread[] t = new Thread[10];
    public void run() {
//        for (int i=0; i<t.length; i++) {
//            t[i] = new Thread(new DaemonSpawn());
//            t[i].start();
//            System.out.println("DaemonSpawn " + i + " started.");
//        }
//        for (int i=0; i<t.length; i++) {
//            System.out.println("t[" + i + "].isDaemon() = " +
//                    t[i].isDaemon() + ".");
//        }
//        while (true) {
//            Thread.yield();
//        }
    	int i=0;
    	while (true) {
    		i++;
          System.out.println("�ػ��߳�" + i);
      }
    }
}