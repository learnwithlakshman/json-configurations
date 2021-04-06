package com.careerit.configurations.thread;

import java.util.concurrent.TimeUnit;

class Counter{
        private volatile  int count = 0;
        public  void increment(){
            System.out.println("Thread :"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                count++;
            }
        }
        public int getCount(){

            return  count;
        }

}

public class ThreadManager {
    public static void main(String[] args)throws  InterruptedException {
            Counter c = new Counter();
            Thread t1 = new Thread(()->{

                for(int i=1;i<=1000;i++){
                    try {
                        TimeUnit.MICROSECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    c.increment();
                }
            },"T1");
            Thread t2 = new Thread(()->{

                for(int i=1;i<=1000;i++){
                    try {
                        TimeUnit.MICROSECONDS.sleep(100);
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }

                    c.increment();
                 }
            },"T2");

            t1.start();
            t2.start();


            t1.join();
            t2.join();

            System.out.println("Count :"+c.getCount());
    }
}
