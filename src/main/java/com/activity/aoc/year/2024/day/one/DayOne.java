package com.activity.aoc.day.one;

import java.io.*;
import java.math.BigDecimal;

public class DayOne {
    public static void main(String[] args) {
        BufferedReader reader = null;
        String[] numbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        try {
            File file = new File("inputs/dayone.txt");
            reader = new BufferedReader(new FileReader(file));

            String line;
            String[] temp;
            BigDecimal trackCount = BigDecimal.ZERO;

            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                temp = line.split("");
                String addLineTemp = "";
                String[] strArr = new String[2];
                boolean firstTime = true;
                for (String s : temp) {
                    BigDecimal addLine = BigDecimal.ZERO;

                    for (int i = 0; i < numbers.length; i++) {

                        if (s.equals(numbers[i])) {
                            if(firstTime){
                                strArr[0] = numbers[i];
                                firstTime = false;
                            }else{
                                strArr[1] = numbers[i];
                            }

                        }
                    }
                    if(strArr[1]!= null){
                        addLineTemp = strArr[0] + strArr[1];
                    }
                    else {
                        addLineTemp = strArr[0] +  strArr[0];
                    }

                }
//                System.out.println("Checking: " + addLineTemp);
                BigDecimal numberFound = new BigDecimal(addLineTemp);
//                System.out.println("Adding: " + trackCount+"+"+ numberFound);
                trackCount = trackCount.add(numberFound);
//                System.out.println("Rolling Count: " + trackCount);
            }
            System.out.println(trackCount);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
