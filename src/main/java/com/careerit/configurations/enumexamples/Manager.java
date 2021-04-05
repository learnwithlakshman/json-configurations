package com.careerit.configurations.enumexamples;




public class Manager {
    public static void main(String[] args) {

        Size[] arr = Size.values();
        for(Size size:arr){
            System.out.println(size.ordinal()+" "+size+"("+size.getLength()+")");
        }
    }
}
