/**
 * 
 */
package com.lzh.thread;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lizh
 *
 */
public class TransferMoney {


    public static boolean transferMoney(Account fromAcct, Account toAcct, BigDecimal amount, long timeout, TimeUnit unit)  
            throws InsufficientFundsException, InterruptedException {  
        long fixedDelay = 100;  
        long randMod = 100;  
        long waitTime = unit.toNanos(timeout); 
        long stopTime = System.nanoTime() 
        		+ waitTime;
      
        while (true) {  
            if (fromAcct.lock.tryLock()) {  
                try {  
                    if (toAcct.lock.tryLock()) {  
                        try {  
                            if (fromAcct.getBalance().compareTo(amount) < 0)  
                                throw new InsufficientFundsException();  
                            else {  
                                fromAcct.debit(amount);  
                                toAcct.credit(amount);  
                                System.out.println(Thread.currentThread().getName() +",to account:" + toAcct.getBalance().intValue());
                                return true;  
                            }  
                        } finally {  
                            toAcct.lock.unlock();  
                        }  
                    }  
                } finally {  
                    fromAcct.lock.unlock();  
                }  
            }   
            if (System.nanoTime() > stopTime)   {
            	System.out.println(Thread.currentThread().getName() + ",ת��ʧ��");
            	return false;
            }
                  
            Random rnd = new Random();
            TimeUnit.MILLISECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
        }  
    }  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Account fromAcct = new Account();
		fromAcct.setBalance(new BigDecimal(1000 * 1000));
		final Account toAcct = new Account();
		toAcct.setBalance(new BigDecimal(1000 * 1000));
		final BigDecimal amount = new BigDecimal(1000 * 10);
		final long timeout = 1000;
		for(int i = 0; i<22; i++) {	
			try {
				Thread.sleep(i * 100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new Thread(
				new Runnable() {
					public void run() {
						try {	
//							System.out.println("pre:::::from account:" + fromAcct.getBalance().intValue());
//							System.out.println("pre::::to account:" + toAcct.getBalance().intValue());
							transferMoney(fromAcct, toAcct,amount,timeout, TimeUnit.NANOSECONDS);
//							transferMoney(toAcct, fromAcct,amount,timeout, TimeUnit.SECONDS);
//							System.out.println("from account:" + fromAcct.getBalance().intValue());
							
						} catch (InsufficientFundsException
								| InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}).start();
		}	
		
	}
	
	

}

class Account {
	protected Lock lock = new ReentrantLock();
	private volatile BigDecimal balance;
	public synchronized BigDecimal getBalance() {
		return balance;
	}
	public synchronized void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public synchronized void debit(BigDecimal amount) {
		double afterDebit = this.balance.subtract(amount).doubleValue();
		this.balance = new BigDecimal(afterDebit);
	}
	public synchronized void credit(BigDecimal amount) {
		double afterCredit = this.balance.add(amount).doubleValue();
		this.balance = new BigDecimal(afterCredit);
	}
}

class InsufficientFundsException extends Exception {
	private static final long serialVersionUID = -3764946212039013382L;
}
