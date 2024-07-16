package com.test;

import java.io.FileWriter;

/**
 * LicencePlateGeneratorByIndex class.
 */
public class LicencePlateGeneratorByIndexOld {
	
public static final Long RANGE_1  = 1000000L;
public static final Long RANGE_2  = 100000L;
public static final Long RANGE_3  = 10000L;
public static final Long RANGE_4  = 1000L;
public static final Long RANGE_5  = 100L;
public static final Long RANGE_6  = 10L;
public static final Long RANGE_7  = 1L;

public static final Long RANGE_AZ = 26L;

public static final Integer ASCII_A = 65;


private Long limit_1;
private Long limit_2;
private Long limit_3;
private Long limit_4;
private Long limit_5;
private Long limit_6;
private Long limit_7;
private Long limit;

private Long limit_1_2;
private Long limit_1_2_3;
private Long limit_1_2_3_4;
private Long limit_1_2_3_4_5;
private Long limit_1_2_3_4_5_6;

/**
 * Builder.
 */
public LicencePlateGeneratorByIndexOld() {
    limit_1 = RANGE_1; 
    limit_2 = RANGE_2 * RANGE_AZ; 
    limit_3 = RANGE_3 * (RANGE_AZ * RANGE_AZ);
    limit_4 = RANGE_4 * (RANGE_AZ * RANGE_AZ * RANGE_AZ);
    limit_5 = RANGE_5 * (RANGE_AZ * RANGE_AZ * RANGE_AZ * RANGE_AZ);
    limit_6 = RANGE_6 * (RANGE_AZ * RANGE_AZ * RANGE_AZ * RANGE_AZ * RANGE_AZ);
    limit_7 = (RANGE_AZ * RANGE_AZ * RANGE_AZ * RANGE_AZ * RANGE_AZ * RANGE_AZ);
    
    limit = limit_1 + limit_2 + limit_3 + limit_4 + limit_5 + limit_6 + limit_7;
   
    limit_1_2 = limit_1 + limit_2;
    limit_1_2_3 = limit_1 + limit_2 + limit_3;
    limit_1_2_3_4 = limit_1 + limit_2 + limit_3 + limit_4;
    limit_1_2_3_4_5 = limit_1 + limit_2 + limit_3 + limit_4 + limit_5;
    limit_1_2_3_4_5_6 = limit_1 + limit_2 + limit_3 + limit_4 + limit_5 + limit_6;
}

/**
 * Plate generator from index.
 * @param index index plate generator
 * @return plate code.
 */

public String calculatePlate(long index) {
    String plate = "";
        
    //000000-999999
    if (index < 0 || index >= limit) {
    	throw new RuntimeException("Out of limits");
    }
    
    //00000A-99999Z
    if (index < limit_1) {
        plate = String.format("%06d", index);
        
    } else if ((limit_1 <= index) && (index < limit_1_2)) {
   	    long index_code = index - limit_1;   	    
   	    long div = index_code / RANGE_2;
   	    long res = index_code % RANGE_2;
   	    plate = String.format("%05d",res).concat(String.valueOf((char)(div + ASCII_A)));
   	    
   	//0000AA-9999ZZ
    } else if ((limit_1_2 <= index ) && (index < limit_1_2_3)) {
        long index_code = index - limit_1_2;
        
        long res = index_code%RANGE_3;
        long div = (index_code/RANGE_3) % RANGE_AZ;
        long div_p = (index_code/RANGE_3)/ RANGE_AZ;
        
        
        plate = String.format("%04d",res)
                      .concat(String.valueOf((char)(div_p + ASCII_A)))
                      .concat(String.valueOf((char)(div + ASCII_A)));
        
    //000AAA-999ZZZ
    } else if ((limit_1_2_3 <= index) && (index < limit_1_2_3_4)) {
        
        long index_code = index - limit_1_2_3;
        long res = index_code % RANGE_4;
        long div = (index_code / RANGE_4) % RANGE_AZ;
        long div_p = ((index_code / RANGE_4) / RANGE_AZ) % RANGE_AZ; 
        long div_p_p = ((index_code / RANGE_4) / RANGE_AZ) / RANGE_AZ;
        
        plate = String.format("%03d",res)
        		.concat(String.valueOf((char)(div_p_p + ASCII_A)))
                .concat(String.valueOf((char)(div_p + ASCII_A)))
                .concat(String.valueOf((char)(div + ASCII_A)));
        
    //00AAAA-99ZZZZ
    } else if ((limit_1_2_3_4 <= index) && (index < limit_1_2_3_4_5)) {
        
    	long index_code = index - limit_1_2_3_4;
        long res = index_code % RANGE_5;
        long div = (index_code / RANGE_5) % RANGE_AZ;
        long div_p = ((index_code / RANGE_5) / RANGE_AZ) % RANGE_AZ; 
        long div_p_p = (((index_code / RANGE_5) / RANGE_AZ) / RANGE_AZ) % RANGE_AZ;
        long div_p_p_p = (((index_code / RANGE_5) / RANGE_AZ) / RANGE_AZ) / RANGE_AZ;
        
        plate = String.format("%02d",res)
        		.concat(String.valueOf((char)(div_p_p_p + ASCII_A)))
        		.concat(String.valueOf((char)(div_p_p + ASCII_A)))
                .concat(String.valueOf((char)(div_p + ASCII_A)))
                .concat(String.valueOf((char)(div + ASCII_A)));
        
    //0AAAAA-9ZZZZZ
    } else if ((limit_1_2_3_4_5 <= index) && (index < limit_1_2_3_4_5_6)) {
    	
    	long index_code = index - limit_1_2_3_4_5;
        long res = index_code % RANGE_6;
        long div = (index_code / RANGE_6) % RANGE_AZ;
        long div_p = ((index_code / RANGE_6) / RANGE_AZ) % RANGE_AZ; 
        long div_p_p = (((index_code / RANGE_6) / RANGE_AZ) / RANGE_AZ) % RANGE_AZ;
        long div_p_p_p = ((((index_code / RANGE_6) / RANGE_AZ) / RANGE_AZ) / RANGE_AZ) % RANGE_AZ;
        long div_p_p_p_p = (((index_code / RANGE_6) / RANGE_AZ) / RANGE_AZ) / RANGE_AZ / RANGE_AZ;
        
        plate = String.format("%01d",res)
        		.concat(String.valueOf((char)(div_p_p_p_p + ASCII_A)))
        		.concat(String.valueOf((char)(div_p_p_p + ASCII_A)))
        		.concat(String.valueOf((char)(div_p_p + ASCII_A)))
                .concat(String.valueOf((char)(div_p + ASCII_A)))
                .concat(String.valueOf((char)(div + ASCII_A)));
    
    //AAAAAA-ZZZZZZ
    } else if ((limit_1_2_3_4_5_6 <= index) && (index < limit)) {
    	
    	long index_code = index - limit_1_2_3_4_5_6;
        long div = (index_code / RANGE_7) % RANGE_AZ;
        long div_p = ((index_code / RANGE_7) / RANGE_AZ) % RANGE_AZ; 
        long div_p_p = (((index_code / RANGE_7) / RANGE_AZ) / RANGE_AZ) % RANGE_AZ;
        long div_p_p_p = ((((index_code / RANGE_7) / RANGE_AZ) / RANGE_AZ) / RANGE_AZ) % RANGE_AZ;
        long div_p_p_p_p = ((((index_code / RANGE_7) / RANGE_AZ) / RANGE_AZ) / RANGE_AZ / RANGE_AZ) % RANGE_AZ;
        long div_p_p_p_p_p = ((((index_code / RANGE_7) / RANGE_AZ) / RANGE_AZ) / RANGE_AZ / RANGE_AZ) / RANGE_AZ;
    	
        
        plate = ""
        		.concat(String.valueOf((char)(div_p_p_p_p_p + ASCII_A)))
        		.concat(String.valueOf((char)(div_p_p_p_p + ASCII_A)))
        		.concat(String.valueOf((char)(div_p_p_p + ASCII_A)))
        		.concat(String.valueOf((char)(div_p_p + ASCII_A)))
                .concat(String.valueOf((char)(div_p + ASCII_A)))
                .concat(String.valueOf((char)(div + ASCII_A)));
        
    }
    
    
    return plate;
}


//MAIN. BATCH GENERATOR OF ALL THE PLATES.
public static void main(String[] args) throws Exception {
	

	
	// ALL POSSIBLE PLATES GENERATOR
	/*
    long startGlobalTime = System.currentTimeMillis();
   
    LicencePlateGeneratorByIndex letterRegistration = new LicencePlateGeneratorByIndex();
    
    FileWriter myWriter = null;
    
    long firstStartGlobalTime = System.currentTimeMillis();
    myWriter = new FileWriter("0-999999.txt");
    for (long i=0L; i < 999999L; i++) {
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
