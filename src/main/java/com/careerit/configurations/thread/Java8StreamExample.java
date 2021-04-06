package com.careerit.configurations.thread;

import java.util.Collection;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Java8StreamExample {

    public static long sum(long a,long b){
        return a + b;
    }
    public static long reduce(List<Long> list){
           long a = list.get(0);
           for(int i=1;i<list.size();i++){
               a = sum(a,list.get(i));
           }
           return a;
    }
    public static long reduce(long def,List<Long> list){
           long a = def;
           for(int i=0;i<list.size();i++){
               a = sum(a,list.get(i));
           }
           return a;

    }
    public static void main(String[] args) {
        long firstNum = 1;
        long lastNum = 4;

        List<Long> longList = LongStream.rangeClosed(firstNum, lastNum).boxed().collect(Collectors.toList());
        long stime = System.currentTimeMillis();
        Long res=longList.stream().reduce(0L,(a,b)->a+b);
        System.out.println(res);
        long etime = System.currentTimeMillis();
        System.out.println("Time taken in ms :"+(etime - stime));
        System.out.println(res);
    }
}
