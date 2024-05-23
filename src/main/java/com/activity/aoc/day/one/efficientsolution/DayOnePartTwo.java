package com.activity.aoc.day.one.efficientsolution;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class DayOnePartTwo {
    private static final Map<String, String> CHAR_NUM = Map.of("one", "1", "two", "2", "three", "3", "four", "4", "five", "5", "six", "6", "seven", "7", "eight", "8", "nine", "9");
    private static final Map<Character, List<String>> LETTER_TO_NUM = Map.of('o', List.of("one"), 't', List.of("two", "three"), 'f', List.of("four", "five"), 's', List.of("six", "seven"), 'e', List.of("eight"), 'n', List.of("nine"));
    private static BigDecimal total = BigDecimal.ZERO;

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new FileReader("inputs/dayoneparttwo.txt"))) {
            for (String line; (line = reader.readLine()) != null;) {
                var nums = new ArrayList<BigDecimal>();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) nums.add(BigDecimal.valueOf(c - '0'));
                    else if (LETTER_TO_NUM.containsKey(c))
                        for (var word : LETTER_TO_NUM.get(c))
                            if (line.regionMatches(true, i, word, 0, word.length())) { nums.add(new BigDecimal(CHAR_NUM.get(word))); i += word.length() - 1; break; }
                }
                if (!nums.isEmpty()) total = total.add(new BigDecimal(nums.get(0) + "" + nums.get(nums.size() - 1)));
            }
        }
        System.out.println("TOTAL: " + total);
    }
}