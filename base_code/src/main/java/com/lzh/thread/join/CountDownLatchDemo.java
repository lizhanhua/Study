package com.lzh.thread.join;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {  
    final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    public static void main(String[] args) throws InterruptedException {  
        CountDownLatch latch=new CountDownLatch(3);//两个工人的协作  
        Worker worker1=new Worker("zhang san", 5000, latch);  
        Worker worker2=new Worker("li si", 8000, latch); 
        Worker worker3=new Worker("wang wu", 19000, latch);  
        ExecutorService es = Executors.newFixedThreadPool(5);
        es.submit(worker1);
        es.submit(worker2);
        es.submit(worker3);
        
//        worker1.start();//  
//        worker2.start();//  
//        worker3.start();
        latch.await();//等待所有工人完成工作  
        System.out.println("all work done at "+sdf.format(new Date()));  
    }  
      
      
    static class Worker extends Thread{  
        String workerName;   
        int workTime;  
        CountDownLatch latch;  
        public Worker(String workerName ,int workTime ,CountDownLatch latch){  
             this.workerName=workerName;  
             this.workTime=workTime;  
             this.latch=latch;  
        }  
        public void run(){  
            System.out.println("Worker "+workerName+" do work begin at "+sdf.format(new Date()));  
            doWork();//工作了  
            System.out.println("Worker "+workerName+" do work complete at "+sdf.format(new Date()));  
            latch.countDown();//工人完成工作，计数器减一  
  
        }  
          
        private void doWork(){  
            try {  
                Thread.sleep(workTime);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
       
}  