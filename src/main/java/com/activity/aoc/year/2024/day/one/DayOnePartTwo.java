package com.activity.aoc.day.one;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayOnePartTwo {
    private static HashMap<String, String> charNum = new HashMap<>();
    private static BigDecimal total = BigDecimal.ZERO;


    public static void main(String[] args) {
        BufferedReader reader = null;
        String[] numbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

        charNum.put("one", "1");
        charNum.put("two", "2");
        charNum.put("three", "3");
        charNum.put("four", "4");
        charNum.put("five", "5");
        charNum.put("six", "6");
        charNum.put("seven", "7");
        charNum.put("eight", "8");
        charNum.put("nine", "9");

        // o = one          // s = six
        // t = two          // s = seven
        // t = three        // e = eight
        // f = four         // n = nine
        // five = one
        HashMap<String, List<String>> letterToNum = new HashMap<>();
        List<String> oString = new ArrayList<>(), tString = new ArrayList<>(), fString = new ArrayList<>(), sString = new ArrayList<>(), eString = new ArrayList<>(), nString = new ArrayList<>();
        oString.add("one");
        tString.add("two");
        tString.add("three");
        fString.add("four");
        fString.add("five");
        sString.add("six");
        sString.add("seven");
        eString.add("eight");
        nString.add("nine");

        letterToNum.put("o", oString);
        letterToNum.put("t", tString);
        letterToNum.put("f", fString);
        letterToNum.put("s", sString);
        letterToNum.put("e", eString);
        letterToNum.put("n", nString);

        HashMap<Integer, List<BigDecimal>> trackEachLineNums = new HashMap<>();


        try {
            File file = new File("inputs/dayone.txt");
            reader = new BufferedReader(new FileReader(file));

            String line;
            String[] temp;
            BigDecimal number = BigDecimal.ZERO;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                List<BigDecimal> eachLinesList = new ArrayList<>();
                temp = line.split("");
                List<String> potentialNum = new ArrayList<>();
                System.out.println("Line Character: " + line);
                int matchCount = 0;
                for (int i = 0; i < temp.length; i++) {
                    String s = temp[i];
                    System.out.println(temp[i]);
                    if (
                            s.startsWith("o") || s.startsWith("t") ||
                                    s.startsWith("f") || s.startsWith("s") ||
                                    s.startsWith("e") || s.startsWith("n")
                                    || s.startsWith("1") || s.startsWith("2") || s.startsWith("3") || s.startsWith("4") || s.startsWith("5")
                                    || s.startsWith("6") || s.startsWith("7") || s.startsWith("8") || s.startsWith("9")
                    ) {
                        String reg = "[0-9]+";
                        if (s.matches(reg)) {
                            System.out.println("MATCHED!!");
                            eachLinesList.add(new BigDecimal(s));
                        }
                        boolean moreThanOne = false;
                        if (letterToNum.containsKey(s)) {
                            potentialNum = letterToNum.get(s);
                            if (potentialNum.size() > 1) {
                                moreThanOne = true;
                            }
                        }
                        for (String letterToCheck : potentialNum) {
                            System.out.println("potential num: " + letterToCheck);

                            String[] count = letterToCheck.split("");
                            int tempCount = i;
                            String strToCheck = "";
                            for (int x = 0; x < count.length; x++) {
                                System.out.println("Count: " + count[x] + " tempCount: " + temp[tempCount]);
                                if (count[x].equalsIgnoreCase(temp[tempCount])) {
//                                    System.out.println("Matched! Array List Letter: " + count[x] + " with advent letter: " + temp[tempCount]);
                                    strToCheck += temp[tempCount];
                                } else {
                                    System.out.println("Not a match");
//                                    break;
                                }
                                if (tempCount != temp.length - 1) {
                                    tempCount++;
                                }
                                if (x == count.length - 1) {
//                                    System.out.println("All letters are matching!");
                                    if (checkIfTrueMatch(letterToCheck, strToCheck)) {
                                        number = number.add(convertToNum(strToCheck));
                                        eachLinesList.add(convertToNum(strToCheck));
                                        matchCount++;
//                                        if (i != temp.length - 1) {
//                                            i = i + count.length - 1;
//                                        }
                                    }

                                }
//                                System.out.println("matching so far for: " + letterToCheck);
                            }
                        }
                        System.out.println("Match Count: " + matchCount);

                    }

                }

                trackEachLineNums.put(lineNumber, eachLinesList);
            }
            System.out.println("lines: " + trackEachLineNums.size());
            eachLineCalc(trackEachLineNums);
            System.out.println("TOTAL: " + total);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        catch (ArrayIndexOutOfBoundsException e){
//            System.out.println("Ignoring for now exception");
//        }
    }

    private static Boolean checkIfTrueMatch(String stringInMap, String stringInAdv) {
        if (stringInMap.equalsIgnoreCase(stringInAdv)) {
            System.out.println("It's a true match! Map String: " + stringInMap + " Adv String: " + stringInAdv);
            return true;
        }
        return false;
    }

    private static BigDecimal convertToNum(String stringNum) {
        return new BigDecimal(charNum.get(stringNum));
    }

    private static BigDecimal eachLineCalc(HashMap<Integer, List<BigDecimal>> map) {
        System.out.println("Start of iterator===");
        for (Map.Entry<Integer, List<BigDecimal>> entry : map.entrySet()) {

            int key = entry.getKey();
            System.out.println("Line Number: " + key);
            List<BigDecimal> value = entry.getValue();
            String numValueString = "";
            numValueString = value.get(0).toString();
            numValueString += value.get(value.size() - 1).toString();
            System.out.println("Num Val String: " + numValueString);
            total = total.add(new BigDecimal(numValueString));

            System.out.println("::Values were");
            for (BigDecimal val : value) {
                System.out.println("::" + val);
            }

        }

        return BigDecimal.ZERO;
    }
}
