package com.lzh.thread.exception;

import java.lang.Thread.UncaughtExceptionHandler;  

public class ExceptionHandler implements UncaughtExceptionHandler{  
  
    public void uncaughtException(Thread t, Throwable e) {  
  
        System.out.printf("An exception has been captured\n");  
        System.out.printf("Thread:%s\n",t.getId());  
        System.out.printf("Exception:%s :%s\n",e.getClass().getName(),e.getMessage());  
        System.out.printf("Stack Traace");  
        e.printStackTrace(System.out);  
        System.out.printf("Thread status:%s\n",t.getState());  
          
    }  
  
}  
