# LICENCE PLATE GENERATOR APPLICATION

LICENCE PLATE GENERATOR.

## Statement:

You work for the DMV; you have a specific, sequential way of generating new license plate numbers:
 
Each license plate number has 6 alphanumeric characters. The numbers always come before the letters.
 
The first plate number is 000000, followed by 000001. Finally, when you arrive at 999999, the next entry would be 00000A, Followed by 00001A. When you arrive at 99999A, the next entry is 00000B. After following the pattern to 99999Z, the next in the sequence would be 0000AA.
 
When 9999AA is reached, the next in the series would be 0000AB...
 
The pattern overview looks a bit like the following:

```
 
000000
000001
...
999999
00000A
00001A
...
99999A
00000B
00001B
...
99999Z
0000AA
0001AA
...
9999AA
0000AB
0001AB
...
9999ZZ
000AAA
001AAA
...
ZZZZZZ
```

The goal is to write a function that takes some index n as a parameter and returns the nth element in this license plate sequence.


## Technologies used:

- Microsoft Windows 10 Professional Edition x64
- Java 17.


## Execution:


Solution of this exercice can be found [here](LicencePlateGenerator/src/main/java/com/test/LicencePlateGeneratorByIndex.java)

Function 

```
/**
 * Plate generator from index.
 * @param index index plate generator
 * @return plate code.
 */
public String calculatePlate(long index)
```

Also exists a commented main function, with a generator of all possible plates between 0 and 501363136 (out of these limits the application raise a RuntimeException "Out of Limits")

Times as follows:

(Executed in a laptop with i7 intell 8th generation, 16GBytes of RAM running Windows 10 Professional Edition)

|Interval|Plate|Time (ms)|
|---------|-----------|---------------|
|0-999999|0-999999|1273 ms|
|1000000-3599999|00000A-99999Z|1741 ms|
|3600000-10359999|0000AA-9999ZZ|9128 ms|
|10360000-27935999|000AAA-999ZZZ|12704 ms|
|27936000-73633599|00AAAA-99ZZZZ|32711 ms|
|73633600-192447359|0AAAAA-9ZZZZZ|83154 ms|
|192447360-501363135|AAAAAA-ZZZZZZ|95111 ms|

Global processing time 235829 ms

