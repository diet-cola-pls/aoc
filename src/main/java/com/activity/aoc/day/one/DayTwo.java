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
        limitColor.put("red",12);
        limitColor.put("blue",14);
        limitColor.put("green",13);


        BufferedReader reader = null;
        try {
            File file = new File("inputs/daythree.txt");
            reader = new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {
                HashMap<String, Integer> eachColorCount = new HashMap<>();
                eachColorCount.put("red", 0);
                eachColorCount.put("blue", 0);
                eachColorCount.put("green", 0);

                System.out.println(line);
                String[] temp = line.split(":");
                String gameNum = temp[0]; // Gives which game it is
                for (String game : temp) {

                    String colorRound = temp[1];
                    String[] round = colorRound.split(";");
                    for (String turn : round) {
                        String[] color = turn.split(",");
                        for (String eachColor : color) {

                            int colorCounter = 0;
                            String[] colorAndCount = eachColor.split(" ");
                            String colorName = colorAndCount[2];
                            String colorCount = colorAndCount[1];
                            System.out.println("Color: " + colorName + " Count: " + eachColorCount.get(colorName));
                            colorCounter += eachColorCount.get(colorName) + Integer.parseInt(colorCount);

                            if (eachColorCount.get(colorName).compareTo(limitColor.get(colorName))>0){
                                System.out.println("Limit Reached");
                                System.out.println("Color: " + colorName + " Count: " + eachColorCount.get(colorName));
                                System.out.println("Limit: " + limitColor.get(colorName));
                                break;
                            }else{

                                eachColorCount.put(colorName, colorCounter);
                            }

                        }

                    }
                }
                for (Map.Entry<String, Integer> entry : eachColorCount.entrySet()) {

                    String key = entry.getKey();
                    System.out.println("Color: " + key);
                    int value = entry.getValue();
                    System.out.println("Value: " + value);


                }
            }

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