package com.labs.LAB1;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class ArrayStore {
    int count = 0;
    int[] arr = new int[10];
// to accept 10 nos.
    public void input() {
        Scanner sc = new Scanner(System.in);  //used when we need to take input from user
        System.out.println("Enter 10 integers:");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("10 integers : " + Arrays.toString(arr));
    }

    //display using while loop
    public void displayWhile() {
        int i = 0;
        System.out.println("Array using while loop: ");
        while (i < arr.length) {
            System.out.println(arr[i] + " ");
            i++;
        }
    }

    //display using for loop
    public void displayFor() {
        System.out.println("Array using for loop: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
    }

    //sort array
    public void sortArray() {
        Arrays.sort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }

    public int occurrenceCount (int number) {
        int count = 0;
        for (int num : arr) {
            if (num == number) {
                count++;
            }
        }
        return count;
    }

    //inserting number at a specific position

    public void insertAtPosition( int num , int position) {
        if (position < 0 || position > count || count >= arr.length) {
            System.out.println("Position is invalid");
            return;
        }

        for (int i = count ; i > position; i--) {
            arr[i] = arr[i - 1];
        }
        arr[position] = num;
        count++;
    }

    //returning array without duplicates

    public int [] removeDuplicates() {
        ArrayList<Integer> unique = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (!unique.contains(arr[i])) {
                unique.add(arr[i]);
            }
        }

        int[] result = new int [unique.size()];
        for (int i = 0; i < unique.size(); i++) {
            result[i] = unique.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayStore a = new ArrayStore();
        a.input();
        a.displayWhile();
        a.displayFor();
        a.sortArray();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number to count: ");
        int num = sc.nextInt();
        System.out.println("Count = " + a.occurrenceCount(num));

        System.out.println("Enter number to insert  and the position: ");
        int n = sc.nextInt();
        int position = sc.nextInt();
        a.insertAtPosition(n,position);
        a.displayFor();

        int[] noDuplicates = a.removeDuplicates();
        System.out.println("Array without duplicates: ");
        for (int x : noDuplicates) {
            System.out.println(x + " ");
        }
    }
}
