package com.labs.LAB1;

public class MethodOverloading {

    public void add(int a, int b) {
        System.out.println("sum :" + (a+b));
    }

    public void add(int a, int b, int c) {
        System.out.println("sum :" + (a+b+c));
    }

    public void add(double a, double b) {
        System.out.println("sum :" + (a+b));
    }

    public void add(String a, int b) {
        System.out.println("string and int combined :" + (a+" "+b));
    }

    public static void main (String[] args) {
        MethodOverloading obj = new MethodOverloading();

        obj.add(10,20);
        obj.add(10,20,30);
        obj.add(10.5,20.1);
        obj.add("Hello", 20);

    }
}



