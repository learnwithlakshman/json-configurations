package com.careerit.configurations.thread;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class Generator{
        int count = 1001;
        public String increment(){
            return "IND-KA-"+count++;
        }
}
class Service  implements Runnable{

    @Override
    public void run() {
        Generator obj = new Generator();
        for(int i = 1; i <= 100; i++){

            try {
                TimeUnit.MICROSECONDS.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String regNumber = obj.increment();
            System.out.println("Thread :"+Thread.currentThread().getName()+" : "+regNumber);

        }
    }
}


public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("The thread "+Thread.currentThread().getName()+" started");
        Service service = new Service();
        Thread t1 = new Thread(service,"Service");
        t1.start();
        System.out.println("The thread "+Thread.currentThread().getName()+" end");

        Runnable r = ()->{
            System.out.println("--------- Runnable lambda expression ----------------");
        };

        Thread t2 = new Thread(r);
        t2.start();
    }
}
