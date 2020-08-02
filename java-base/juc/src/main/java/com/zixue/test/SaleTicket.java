package com.zixue.test;

public class SaleTicket
{
	
	static NotifyWaitDemo demo =new NotifyWaitDemo();
    final Ticket ticket = new Ticket();
    public static void main(String[] args)
    {
    	
    	
    	
    	
//        new Thread(() -> {for(int i=1 ;i<=40;i++) ticket.sale();} ,"A" ).start();
//        new Thread(() -> {for(int i=1 ;i<=40;i++) ticket.sale();} ,"B" ).start();
//        new Thread(() -> {for(int i=1 ;i<=40;i++) ticket.sale();} ,"C" ).start();
        new Thread(() -> {for(int i=1 ;i<=40;i++)
			try {
				demo.increment1();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}} ,"A" ).start();
        new Thread(() -> {for(int i=1 ;i<=40;i++)
			try {
				demo.decrement1();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}} ,"B" ).start();
        
     /*   new Thread(new Runnable()
        {
            
            public void run()
            {
                for (int i = 0; i <=40; i++)
                {
                    ticket.sale();
                }
            }
        }, "A").start();
        new Thread(new Runnable()
        {
            
            public void run()
            {
                for (int i = 0; i <=40; i++)
                {
                    ticket.sale();
                }
            }
        }, "B").start();
        new Thread(new Runnable()
        {
            
            public void run()
            {
                for (int i = 0; i <=40; i++)
                {
                    ticket.sale();
                }
            }
        }, "C").start();*/
        
    }
}
