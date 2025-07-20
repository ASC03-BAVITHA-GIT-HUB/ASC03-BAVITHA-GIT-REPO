package com.labs.LAB1;

public class MultiplicationTable {

    //forLoop
    public static void forLoop(int num) {
        System.out.println("2 Table using forloop: ");
        for (int i = 1; i <= 10; i++)
        {
            System.out.println(num + "x" + i + "=" + (num * i));
        }
    }

    public static void whileLoop(int num) {
        System.out.println("Multiplication table using wile loop: ");
        int i = 1;
        while (i <= 10) {
            System.out.println(num + " x " + i + " = " + (num * i));
            i++;
        }
    }

    public static void doWhile(int num) {
        System.out.println("Multiplication table using do-while loop: ");
        int i = 1;
        do {
            System.out.println(num + " x " + i + " = " + (num * i));
            i++;
        } while (i <= 10);
    }

    public static void main(String[] args) {
        int num = 2;
        forLoop(num);
        whileLoop(num);
        doWhile(num);
    }
}








