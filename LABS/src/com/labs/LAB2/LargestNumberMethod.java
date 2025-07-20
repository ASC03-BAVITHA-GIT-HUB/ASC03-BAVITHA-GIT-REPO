package com.labs.LAB2;

public class LargestNumberMethod {
    public static int largest(int a, int b, int c) {
        return Math.max(a, Math.max(b,c));
    }

    public static void main (String[] args) {
        int result = largest (12,20,30);
        System.out.println("The largest number is :" + result);
    }
}
