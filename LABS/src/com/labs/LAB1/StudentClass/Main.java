package com.labs.LAB1.StudentClass;

public class Main {

    public static void main(String args[]) {
        Student s1 = new Student();
        s1.setName("Bavitha");
        s1.setId(1);
        s1.setCity("Bangalore");
        s1.setmarks1(100);
        s1.setMarks2(95);
        s1.setMarks3(93);
        s1.setFeePerMonth(1000);
        s1.setIsEligibleForScholarship(true);

        Student s2 = new Student();
        s2.setName("Praneesh");
        s2.setId(2);
        s2.setCity("Bangalore");
        s2.setmarks1(90);
        s2.setMarks2(85);
        s2.setMarks3(81);
        s2.setFeePerMonth(1000);
        s2.setIsEligibleForScholarship(true);

        System.out.println(s1.getAnnualFee());
        System.out.println(s1.getTotalMarks());
        System.out.println(s1.getAverage());
        System.out.println(s1.getResult());

        System.out.println(s2.getAnnualFee());
        System.out.println(s2.getTotalMarks());
        System.out.println(s2.getAverage());
        System.out.println(s2.getResult());



    }

}
