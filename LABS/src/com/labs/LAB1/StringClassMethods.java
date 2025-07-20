package com.labs.LAB1;

public class StringClassMethods {
    public static void charAt() {
        String str = "Hello";
        System.out.println("charAt : " + str.charAt(1));
    }

    public static void contains() {
        String str = "Hello my name is Bavitha";
        System.out.println("contains : " + str.contains("Bavitha"));
    }

    public static void Length() {
        String str = "Bavitha";
        System.out.println("Length of string : " + str.length());
    }

    public static void indexOf() {
        String str = "Java programming";
        System.out.println("index : " + str.indexOf('m'));
    }

    public static void equals() {
        String string1 = "apple";
        String string2 = "banana";
        System.out.println("equals : " + string1.equals(string2));
    }

    public static void equalsIgnoreCase() {
        String string1 = "apple";
        String string2 = "APPLE";
        System.out.println("equalsIgnoreCase : " + string1.equalsIgnoreCase(string2));
    }

    public static void join() {
        String joined = String.join( "-","Java", "Python", "C++");
        System.out.println("join : " + joined);
    }

    public static void lastIndexOf() {
        String str = ("banana");
        System.out.println("last index  : " + ("n"));
    }

    public static void subString() {
        String str = "HelloGuys";
        System.out.println("substring : " + str.substring(5));  //begins from index 5 if we give (0,5) start index will be 0 and end index will be 5
    }

    public static void toLowerCase() {
        String str = "BAVITHA";
        System.out.println("toLowerCase : " + str.toLowerCase());
    }

    public static void toUpperCase() {
        String str = ("bavitha");
        System.out.println("toUpperCase : " + str.toUpperCase());
    }

    public static void trim() {
        String str = "    WorkDen ";
        System.out.println("trim : " + str.trim());
    }

    public static void main(String[] args) {
        charAt();
        contains();
        Length();
        indexOf();
        equals();
        equalsIgnoreCase();
        join();
        lastIndexOf();
        subString();
        toLowerCase();
        toUpperCase();
        trim();
    }
}
