package com.activity.aoc.day.two;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayTwoPartTwo {


    public static void main(String[] args) {




        BufferedReader reader = null;
        try {
            File file = new File("inputs/daythree.txt");
            reader = new BufferedReader(new FileReader(file));
            BigDecimal finalNumber = BigDecimal.ZERO;

            String line;
            BigDecimal tempval = BigDecimal.ZERO;

            while ((line = reader.readLine()) != null) {
                HashMap<String, Integer> eachColorCount = new HashMap<>();
                eachColorCount.put("red", 0);
                eachColorCount.put("blue", 0);
                eachColorCount.put("green", 0);

                System.out.println(line);
                String[] temp = line.split(":");
                String gameNum = temp[0]; // Gives which game it is
                System.out.println("Starting " + gameNum);
                for (String game : temp) {

                    String colorRound = temp[1];
                    String[] round = colorRound.split(";");

                    for (String turn : round) {
                        System.out.println("\nNext round starting\n");
                        String[] color = turn.split(",");
                        for (String eachColor : color) {

                            int colorCounter = 0;
                            String[] colorAndCount = eachColor.split(" ");
                            String colorName = colorAndCount[2];
                            String colorCount = colorAndCount[1];

                            System.out.println("Color: " + colorName + " Count: " + colorCount);
//                            colorCounter += eachColorCount.get(colorName) + Integer.parseInt(colorCount);
                            // Game 1: 1 green, 6 red, 4 blue; 2 blue, 6 green, 7 red; 3 red, 4 blue, 6 green; 3 green; 3 blue, 2 green, 1 red
                            if(eachColorCount.get(colorName).compareTo(Integer.parseInt(colorCount)) < 0){
                                eachColorCount.put(colorName, Integer.parseInt(colorCount));
                            }
                        }
                    }

                }
                tempval = tempval.add(new BigDecimal(eachColorCount.get("blue")).multiply(new BigDecimal(eachColorCount.get("green")).multiply(new BigDecimal(eachColorCount.get("red")))));
            }
            System.out.println("Total Number " + tempval);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
