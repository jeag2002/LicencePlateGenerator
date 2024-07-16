package com.test;

import java.io.FileWriter;


/**
 * LicencePlateGeneratorByIndexBetter
 */
public class LicencePlateGeneratorByIndex {
    private static final int ASCII_A = 65;
    private static final int[] RANGE_MULTIPLIERS = new int[]{1000000, 100000, 10000, 1000, 100, 10, 1};
    private static final int RANGE_AZ = 26;
    private static final int MAX = 6;
    private final long[] limits;
    
    /**
     * Builder. Defining the limits.
     */
    public LicencePlateGeneratorByIndex() {
        limits = new long[RANGE_MULTIPLIERS.length];
        limits[0] = RANGE_MULTIPLIERS[0];
        for (int i = 1; i < RANGE_MULTIPLIERS.length; i++) {
            limits[i] = limits[i - 1] + (long) Math.pow(RANGE_AZ, i) * RANGE_MULTIPLIERS[i];
        }
    }

    /**
     * Plate generator.
     * @param index
     * @return plate
     */
    public String calculatePlate(long index) {
    	
    	//limit boundaries evaluation.
        if (index < 0 || index >= limits[limits.length - 1]) {
            throw new RuntimeException("Index out of limits");
        }
        
        /**
         * plate generator for the following intervals
         * 
         * 000000-999999
         * 00000A-99999Z
         * 0000AA-9999ZZ
         * 000AAA-999ZZZ
         * 00AAAA-99ZZZZ
         * 0AAAAA-9ZZZZZ
         */
        for (int i = 0; i < limits.length; i++) {
            if (index < limits[i]) {
                return generatePlate(index, i);
            }
        }
        
        /**
         * plate generator for the following interval
         * 
         * AAAAAA-ZZZZZZ
         */
        return generatePlate(index, limits.length - 1); // for the last range
    }
    
    /**
     * Plate generator 
     * @param index index
     * @param level level
     * @return plate
     */
    private String generatePlate(long index, int level) {
        
        //remove previous levels.
        if (level >= 1) {
          index -= limits[level-1];
        }

        int numDigits = RANGE_MULTIPLIERS[level];
        
        //numeric padding
        int digitpad = MAX-level;
        StringBuilder plate = new StringBuilder();
        
        
        if (digitpad > 0) {
            plate = new StringBuilder(String.format("%0" + digitpad + "d", index % numDigits));
        }
        index /= numDigits;
        
        //letter
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < level; i++) {
        	letters.append((char) (ASCII_A + (index % RANGE_AZ)));
            index /= RANGE_AZ;
        }
        plate.append(letters.reverse().toString());
        
        return plate.toString();
    }
    
    
    
    //MAIN. BATCH GENERATOR OF ALL THE PLATES.
    public static void main(String[] args) throws Exception {
      
      // ALL POSSIBLE PLATES GENERATOR
      /*
      long startGlobalTime = System.currentTimeMillis();
      LicencePlateGeneratorByIndexBetter letterRegistration = new LicencePlateGeneratorByIndexBetter();
      
      FileWriter myWriter = null;
      
      long firstStartGlobalTime = System.currentTimeMillis();
      myWriter = new FileWriter("0-999999.txt");
      for (long i=0L; i < 1000000L; i++) {
           myWriter.write("plate (" + i + ") = " + letterRegistration.calculatePlate(i)+"\r\n");
      }
      myWriter.close();
      System.out.println("0-999999 time " + (System.currentTimeMillis() - firstStartGlobalTime) + " ms ");
      
      long secondStartGlobalTime = System.currentTimeMillis();
      myWriter = new FileWriter("1000000-3599999.txt");
      for (long i=1000000L; i < 3600000L; i++) {
      	myWriter.write("plate (" + i + ") = " + letterRegistration.calculatePlate(i)+"\r\n");
      }
      myWriter.close();
      System.out.println("1000000-3599999 time " + (System.currentTimeMillis() - secondStartGlobalTime) + " ms ");
      
      long thirdStartGlobalTime = System.currentTimeMillis();
      myWriter = new FileWriter("3600000-10359999.txt");
      for (long i=3600000L; i < 10360000L; i++) {
      	myWriter.write("plate (" + i + ") = " + letterRegistration.calculatePlate(i)+"\r\n");
      }
      myWriter.close();
      System.out.println("3600000-10359999 time " + (System.currentTimeMillis() - thirdStartGlobalTime) + " ms ");
      
      long fourthStartGlobalTime = System.currentTimeMillis();
      myWriter = new FileWriter("10360000-27935999.txt");
      for (long i=10360000L; i < 27936000L; i++) {
      	myWriter.write("plate (" + i + ") = " + letterRegistration.calculatePlate(i) + "\r\n");
      }
      myWriter.close();
      System.out.println("10360000-27935999 time " + (System.currentTimeMillis() - fourthStartGlobalTime) + " ms ");
      
      long fifthStartGlobalTime = System.currentTimeMillis();
      myWriter = new FileWriter("27936000-73633599.txt");
      for (long i=27936000L; i < 73633600L; i++) {
      	myWriter.write("plate (" + i + ") = " + letterRegistration.calculatePlate(i) + "\r\n");
      }
      myWriter.close();
      System.out.println("27936000-73633599 time " + (System.currentTimeMillis() - fifthStartGlobalTime) + " ms ");
      
      long sixthStartGlobalTime = System.currentTimeMillis();
      myWriter = new FileWriter("73633600-192447359.txt");
      for (long i=73633600L; i < 192447360L; i++) {
      	myWriter.write("plate (" + i + ") = " + letterRegistration.calculatePlate(i) + "\r\n");
      }
      myWriter.close();
      System.out.println("73633600-192447359 time " + (System.currentTimeMillis() - sixthStartGlobalTime) + " ms ");
      
      long seventhStartGlobalTime = System.currentTimeMillis();
      myWriter = new FileWriter("192447360-501363135.txt");
      for (long i=192447360L; i < 501363136L; i++) {
      	myWriter.write("plate (" + i + ") = " + letterRegistration.calculatePlate(i) + "\r\n");
      }
      myWriter.close();
      System.out.println("192447360-501363135 time " + (System.currentTimeMillis() - seventhStartGlobalTime) + " ms ");
      
      Long endGlobalTime = System.currentTimeMillis();
      
      System.out.println("GLOBAL TIME SPENT " + (endGlobalTime - startGlobalTime) + " ms ");
      */
    }
}


