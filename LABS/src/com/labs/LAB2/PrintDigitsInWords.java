package com.labs.LAB2;

public class PrintDigitsInWords {
    public static void printDigitsInWords(int number) {
        String[] words = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String numStr = String.valueOf(number);
        for (char digit : numStr.toCharArray()) {
            System.out.println(words[digit - '0'] + " ");
        }
    }

    public static void main (String[] args) {
        printDigitsInWords(213);

    }
}
