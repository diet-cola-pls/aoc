package com.activity.aoc.day.three;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayThree {
    private static String tempString = "";

    public static void main(String[] args) {
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
        //      Look up
        //      If nothing up then black list

        BufferedReader reader = null;
        try {
            List<BigDecimal> finalNumber = new ArrayList<>();
            HashMap<Integer, String> previousLineString = new HashMap<>();
            HashMap<Integer, String> blackList = new HashMap<>();

            File file = new File("inputs/daythree.txt");
            reader = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {

                System.out.println(lineNumber + " - " + line);
                String[] charac = line.split("");
                int counter = 0;

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
                    if (containsSpecialCharacter(charac[i])) {
                        System.out.println("Special Character Found!: " + charac[i]);
                    }
                    if(counter != 0){
                        System.out.println("Combined String: " + tempString + " Contains symbol on right or left or above: " + checkForSymbolLeftAndRightAndTop(charac, tempString, previousLineString, currentIndex));
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
    }

    static private boolean containsSpecialCharacter(String s) {
        return s != null && s.matches("[^A-Za-z0-9.]");
    }

    static private int buildNumber(String[] charac, int currentIndex) {
        StringBuilder temp = new StringBuilder();
        if (currentIndex != 0) {
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
        tempString = temp.toString();
        return currentIndex - 1;
    }

    static private boolean checkForSymbolLeftAndRightAndTop(String[] charac, String number, HashMap<Integer, String> previousLineString, int currentIndex) {

        String aboveString = "";
        int currentIndexCounter = currentIndex;
        if (number.isBlank()) {
            return false;
        }
        String[] s = number.split("");
        //check to the right
        if (containsSpecialCharacter(s[s.length - 1])) {
            return true;
        }
        //check to the left
        if (containsSpecialCharacter(s[0])) {
            return true;
        }

        System.out.println("Above String: " + previousLineString.toString());

        // check above
        if(currentIndexCounter != 0){
            currentIndexCounter = currentIndexCounter -1;
        }
//        System.out.println("Current Index is at: " + currentIndexCounter);
//        System.out.println("Length of string to search: " + s.length);

        for (int i = currentIndexCounter; i < currentIndexCounter + s.length + 1; i++) {
            aboveString = previousLineString.get(i);
            System.out.println("Checking above at index: " +i +" value: "+ aboveString);
            if (containsSpecialCharacter(aboveString)) {
                System.out.println("Contains symbol above");
                return true;
            }
        }

        return false;
    }


}