package org.adventofcode.y2025.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.adventofcode.y2025.day1.Day1Part1;

public class Day2Part1 {
    long total = 0;
    public Day2Part1(){
        List<String> lines = null;
        try {
            //one line
            lines = Files.readAllLines(Path.of("src/main/resources/y2025/day2/input1.txt"));
            String input = lines.getFirst();
            List<String> inputRanges = List.of(input.split(","));
            inputRanges.forEach(this::processRange);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processRange(String range){
        String[] startEndRange;
        startEndRange = range.split("-");
        long start = Long.parseLong(startEndRange[0]);
        long end = Long.parseLong(startEndRange[1]);
        for(long i = start; i <= end; i++){
            String stringValue = Long.toString(i);
            if(stringValue.length() % 2 == 0){
                int half = stringValue.length() / 2;
                String firstHalf = stringValue.substring(0,half);
                String secondHalf = stringValue.substring(half);
                if(firstHalf.equals(secondHalf)){
                    total += i;
                }
            }
        }
    }


    public static void main(String[] args) {
        Day2Part1 day2Part1 = new Day2Part1();
        System.out.println(day2Part1.total);

    }
}
