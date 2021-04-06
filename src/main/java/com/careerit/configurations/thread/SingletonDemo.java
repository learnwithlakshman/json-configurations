package com.careerit.configurations.thread;


import javafx.concurrent.Task;

class TaskManager{

        private volatile static TaskManager obj = null;
        private TaskManager(){
        }
        public  static TaskManager getInstance(){

            if(obj == null){
                synchronized (TaskManager.class){
                    if(obj == null) {
                        obj = new TaskManager();
                    }
                }

            }
            return obj;
        }
}
public class SingletonDemo {

    public static void main(String[] args) {

            Thread t1 = new Thread(()->{
                System.out.println(TaskManager.getInstance());
            });
            Thread t2 = new Thread(()->{
                     System.out.println(TaskManager.getInstance());
            });
            Thread t3 = new Thread(()->{
                 System.out.println(TaskManager.getInstance());
            });
            Thread t4 = new Thread(()->{
                System.out.println(TaskManager.getInstance());
            });


            t1.start();
            t2.start();
            t3.start();
            t4.start();



    }
}
