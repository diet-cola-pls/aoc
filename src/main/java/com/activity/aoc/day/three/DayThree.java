package com.activity.aoc.day.three;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;

public class DayThree {


    public static void main(String[] args) {


        // 1. Identify digits in line
        //     .....[679].....
        // 2. check after the digits and before digits if symbol encountered
        //     .....679[*]....
        // 3. Check in next line same position accounting for before and after char.
        //     ......[*]....
        //     ...679.....

        BufferedReader reader = null;
        try {
            File file = new File("inputs/daythree.txt");
            reader = new BufferedReader(new FileReader(file));
            BigDecimal finalNumber = BigDecimal.ZERO;

            String line;
            BigDecimal tempval = BigDecimal.ZERO;

            while ((line = reader.readLine()) != null) {

                System.out.println(line);
                String[] charac = line.split("");

                for (String c : charac) {
                    char[] ct = c.toCharArray();
                    if (Character.isDigit(ct[0])) {
                        System.out.println(c);
                    }
                }

            }
            System.out.println("Total Number " + tempval);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
