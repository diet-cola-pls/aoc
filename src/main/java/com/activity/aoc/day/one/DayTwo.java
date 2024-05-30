package com.activity.aoc.day.one;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayTwo {


    public static void main(String[] args) {
        List<Integer> redCubes = new ArrayList<>();
        redCubes.add(0, 12);
        List<Integer> blueCubes = new ArrayList<>();
        blueCubes.add(0, 14);
        List<Integer> greenCubes = new ArrayList<>();
        greenCubes.add(0, 13);


        HashMap<String, Integer> limitColor = new HashMap<>();
        limitColor.put("red", 12);
        limitColor.put("blue", 14);
        limitColor.put("green", 13);


        BufferedReader reader = null;
        try {
            File file = new File("inputs/daythree.txt");
            reader = new BufferedReader(new FileReader(file));
            BigDecimal finalNumber = BigDecimal.ZERO;

            String line;

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
                    boolean thresholdReached = false;
                    for (String turn : round) {
                        System.out.println("\nNext round starting\n");
                        String[] color = turn.split(",");
                        for (String eachColor : color) {

                            int colorCounter = 0;
                            String[] colorAndCount = eachColor.split(" ");
                            String colorName = colorAndCount[2];
                            String colorCount = colorAndCount[1];

                            System.out.println("Color: " + colorName + " Count: " + eachColorCount.get(colorName));
                            colorCounter += eachColorCount.get(colorName) + Integer.parseInt(colorCount);
                            if (eachColorCount.get(colorName).compareTo(limitColor.get(colorName)) > 0) {
                                System.out.println("****Disqualified****");
                                System.out.println("Color: " + colorName + " Count: " + eachColorCount.get(colorName));
                                System.out.println("Limit: " + limitColor.get(colorName));
                                thresholdReached = true;
                            }
                            if (!thresholdReached) {
                                System.out.println("Adding " + colorName + " Counter: " + colorCounter);
                                eachColorCount.put(colorName, colorCounter);
                            }


                        }
                        if (!thresholdReached) {
                            System.out.println("Final Number Added because threshold not reached in this game");
                            String[] num = gameNum.split(" ");
                            finalNumber = finalNumber.add(new BigDecimal(num[1]));
                            System.out.println("Game: " + num[1] + " added");

                        }


                    }


                }
//                for (Map.Entry<String, Integer> entry : eachColorCount.entrySet()) {
//
//                    String key = entry.getKey();
//                    System.out.println("Color: " + key);
//                    int value = entry.getValue();
//                    System.out.println("Value: " + value);
//
//
//                }
            }
            System.out.println("Number of games not disqualified: " + finalNumber);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        catch (ArrayIndexOutOfBoundsException e){
//            System.out.println("Ignoring for now exception");
//        }
    }


}
