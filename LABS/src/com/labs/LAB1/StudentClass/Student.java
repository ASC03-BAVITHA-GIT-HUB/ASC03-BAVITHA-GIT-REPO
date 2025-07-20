package com.labs.LAB1.StudentClass;

public class Student {
    private int studentid;
    private String studentName;
    private String city;
    private int marks1;
    private int marks2;
    private int marks3;

    float feePerMonth;
    boolean isEligibleForScholarship;

    public void setName(String studentName) {
        this.studentName = studentName;
    }
    public String getName() {
        return studentName;
    }

    public void setId(int studentid) {
        this.studentid = studentid;
    }

    public int getId() {
        return studentid;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setmarks1(int marks1) {
        this.marks1 = marks1;
    }

    public int getMarks1() {
        return marks1;
    }

    public void setMarks2(int marks2) {
        this.marks2 = marks2;
    }

    public int getMarks2() {
        return marks2;
    }

    public void setMarks3(int marks3) {
        this.marks3 = marks3;
    }

    public int getMarks3() {
        return marks3;
    }

    public void setFeePerMonth(int feePerMonth) {
        this.feePerMonth = feePerMonth;
    }

    public float getFeePerMonth() {
        return feePerMonth;
    }

    public void setIsEligibleForScholarship(boolean isEligibleForScholarship) {
        this.isEligibleForScholarship = isEligibleForScholarship;
    }

    public boolean getIsEligibleForScholarship() {
        return isEligibleForScholarship;
    }


    public void setAnnualFee(int feePerMonth) {
        this.feePerMonth = feePerMonth;
    }

    public float getAnnualFee() {
        return feePerMonth * 12;
    }

    public void setTotalMarks(int marks1, int marks2, int marks3) {
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
    }

    public int getTotalMarks() {
        return marks1 + marks2 + marks3;
    }

    public int getAverage() {
        return (marks1 +marks2 +marks3) / 3;
    }

    public void setResult(int marks1, int marks2, int marks3) {
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;

    }

    public String getResult() {
        if(marks1 >= 60 && marks2 >= 60 && marks3 >= 60) {
            return "pass";
        }
        return "fail";
    }


}


