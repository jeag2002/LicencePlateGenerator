package com.test;

import java.io.FileWriter;

public class LicencePlateGenerator {

	public static String generateLicensePlate(long n) {
        int numDigits = 6;
        int numLetters = 0;
        int baseDigit = 10;
        int baseLetter = 26;

        long totalCombinations = 0;
        long currentCombinations;

        // Determine the block where 'n' fits
        while (numDigits >= 0) {
            currentCombinations = (long) Math.pow(baseDigit, numDigits) * (long) Math.pow(baseLetter, numLetters);
            if (totalCombinations + currentCombinations > n) {
                break;
            }
            totalCombinations += currentCombinations;
            if (numDigits == 0 && numLetters == 6) {
                break; // When both loops are exhausted, stop.
            }
            numDigits--;
            numLetters++;
        }

        // Handle the case when 'n' is out of the last range
        if (numDigits < 0) {
            return "Index out of range";
        }

        // Compute the specific index within the current block
        long indexInBlock = n - totalCombinations;

        // Calculate the digit and letter parts
        long digitPart = indexInBlock / (long) Math.pow(baseLetter, numLetters);
        long letterIndex = indexInBlock % (long) Math.pow(baseLetter, numLetters);

        // Format the digit part
        String digits = numDigits > 0 ? String.format("%0" + numDigits + "d", digitPart) : "";

        // Construct the letter part
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < numLetters; i++) {
            letters.insert(0, (char) (65 + letterIndex % baseLetter));
            letterIndex /= baseLetter;
        }

        return digits + letters.toString();
    }
    public static void main(String[] args) throws Exception {

    	System.out.println(generateLicensePlate(501363135));
        
    }
}