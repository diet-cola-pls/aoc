package com.activity.aoc.day.three;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DayThree {
    private static String tempString = "";
//    private static String fileName = "inputs/test.txt";
    private static String fileName = "inputs/daythree.txt";
    private static BigDecimal globalSumCounter = new BigDecimal(0);

    public static void main(String[] args){
        //TODO
        // [x] 1. Identify digits in line
        //     .....[679].....
        // [x] 2. check after the digits and before digits if symbol encountered
        //     .....679[*]....
        //TODO
        // [] 3. Check in next line same position accounting for before and after char.
        //     ......[*]....
        //     ...679.....
        //      Break this down
        //      If not found in right or left
        //   [x]Look up
        //   [ ]If nothing up then black list

        BufferedReader reader = null;
        try {
            HashMap<Integer, String> previousLineString = new HashMap<>();
            ConcurrentHashMap<Integer, String> checkInNextPass = new ConcurrentHashMap<>();
            File file = new File(fileName);
            reader = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                System.out.println(lineNumber + " - " + line);
                String[] charac = line.split("");
                int counter = 0;
                checkForBlacklistedNums(checkInNextPass, charac);
                if(!checkInNextPass.isEmpty()){
                    checkInNextPass = null;
                    checkInNextPass = new ConcurrentHashMap<>();
                }
                for (int i = 0; i < charac.length; i++) {
                    char[] ct = charac[i].toCharArray();
                    tempString = "";
                    int currentIndex = 0;
                    if (Character.isDigit(ct[0])) {
                        if (i != charac.length - 1) {
                            currentIndex = i;
                            i = buildNumber(charac, i);
                        }
                    }
                    if(!tempString.equalsIgnoreCase("")){
                        if(!checkForSymbolLeftAndRightAndTop(charac, tempString, previousLineString, currentIndex, lineNumber)){
                            checkInNextPass.put(currentIndex, tempString);
                            System.out.println("Info>> " + tempString + " Not found at index: " + currentIndex+ " Storing for next pass");
                        }
                    }
                    counter++;
                }
                // Put this line in memory
                for (int j = 0; j < charac.length; j++) {
                    previousLineString.put(j, charac[j]);
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sum Value After: " + globalSumCounter);
    }

    private static void globalSumCount(int lineNumber, String tempString) {
        System.out.println("Temp String: " + tempString + " on line #: " + lineNumber);
        String num = getNumFromString(tempString);
        System.out.println("Info>> Added: " + num );
        globalSumCounter = globalSumCounter.add(new BigDecimal(num));
    }

    private static String getNumFromString(String tempString) {
        String[] arrTemp = tempString.split("");
        String num = "";
        int startOfArr = 1;
        int endOfArr = arrTemp.length - 1;
        if(arrTemp.length <= 4){
            startOfArr = 0;
            if(containsSpecialCharacterForCheck(arrTemp[0])){
                startOfArr = 1;
            }
            if(!containsSpecialCharacterForCheck(arrTemp[arrTemp.length - 1])){
                endOfArr = arrTemp.length;
            }
        }
        for(int z = startOfArr; z < endOfArr; z++){
            num += arrTemp[z];
        }
        return num;
    }

    static private boolean containsSpecialCharacter(String s) {
        return s != null && s.matches("[^A-Za-z0-9.]");
    }

    static private boolean containsSpecialCharacterForCheck(String s) {
        return s != null && s.matches("[^A-Za-z0-9]");
    }

    static private int buildNumber(String[] charac, int currentIndex) {
        StringBuilder temp = new StringBuilder();
        if (currentIndex != 0) {
            // append on character on left if not start of line
            temp.append(charac[currentIndex - 1]);
        }
        temp.append(charac[currentIndex]);
        currentIndex++;
        char[] ct = charac[currentIndex].toCharArray();
        while (Character.isDigit(ct[0])) {
//            System.out.println(temp);
            if (currentIndex != charac.length) {
                temp.append(charac[currentIndex]);
                ct = charac[currentIndex].toCharArray();
                currentIndex++;
            } else {
                break;
            }
        }
        System.out.println("Temp Length: " + temp.length());
        if(temp.length() == 2){
            temp.append(charac[currentIndex]);
        }
        tempString = temp.toString();
        return currentIndex - 1;
    }

    static private boolean checkForSymbolLeftAndRightAndTop(String[] charac, String number, HashMap<Integer, String> previousLineString, int currentIndex, int line) {
        String aboveString = "";
        int currentIndexCounter = currentIndex;
//        if (number.isBlank()) {
//            return false;
//        }
        String[] s = number.split("");
        //check to the right
        if (containsSpecialCharacter(s[s.length - 1])) {
            globalSumCount(line, number);
            return true;
        }
        //check to the left
        if (containsSpecialCharacter(s[0])) {
            globalSumCount(line, number);
            return true;
        }

        //check above if it's not the first line
        if( line != 0) {
//            System.out.println("Line Number: " + line + "Above String: " + previousLineString.toString());
            if (currentIndexCounter != 0) {
                currentIndexCounter = currentIndexCounter - 1;
            }
//        System.out.println("Current Index is at: " + currentIndexCounter);
//        System.out.println("Length of string to search: " + s.length);

            for (int i = currentIndexCounter; i < currentIndexCounter + s.length; i++) {
                aboveString = previousLineString.get(i);
                if (containsSpecialCharacter(aboveString)) {
                    globalSumCount(line, number);
                    return true;
                }
            }
        }

        return false;
    }

    private static void checkForBlacklistedNums(ConcurrentHashMap<Integer, String> blList, String[] charac) {
        System.out.println("Inside this method!!");
        for (Map.Entry<Integer, String> entry : blList.entrySet()) {
            int indexCounter = entry.getKey();
            int currentIndexCounter = indexCounter;
            String value = entry.getValue();
            String[] s = value.split("");
            String belowString = "";
            int limitCounter = 0;
            boolean found = false;

            if (currentIndexCounter != 0) {
                currentIndexCounter = currentIndexCounter - 1;
            }
            if (currentIndexCounter + s.length == 140) {
                limitCounter = currentIndexCounter + s.length - 1;
            }else {
                limitCounter = currentIndexCounter + s.length;
            }
            for (int i = currentIndexCounter; i < limitCounter; i++) {
                belowString = charac[i];
                if (containsSpecialCharacter(belowString)) {
                    found = true;
                }
            }
            if(found){
                String num = getNumFromString(value);
                System.out.println("Info>> Added: " + num );
                globalSumCounter = globalSumCounter.add(new BigDecimal(num));
            }

        }
    }


}