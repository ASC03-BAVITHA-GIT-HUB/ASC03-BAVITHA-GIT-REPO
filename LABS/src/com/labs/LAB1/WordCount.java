package com.labs.LAB1;

public class WordCount {

    public static int countWords(String sentence ) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return 0;
        }

        String[] parts = sentence.trim().split(" ");

        int count = 0;
        for (String word : parts) {
            if (!word.matches("\\d+")) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String input = "Sum of 12 and 20 is 32";
        int wordcounter = countWords(input);
        System.out.println("Number of words: " + wordcounter);
    }



}
