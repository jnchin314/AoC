package org.adventofcode.y2025.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2Part2 {
    long total = 0;
    public Day2Part2(){
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

    public void processRange(String range) {
        String[] startEndRange;
        startEndRange = range.split("-");
        long start = Long.parseLong(startEndRange[0]);
        long end = Long.parseLong(startEndRange[1]);
        for (long i = start; i <= end; i++) {
            String stringValue = Long.toString(i);
            for (int j = 1; j <= stringValue.length() / 2; j++) {
                String stringPart = stringValue.substring(0, j);
                StringBuilder sb = new StringBuilder();
                //System.out.println("i " + i + " and part j " + stringPart);
                //this if check to see if the part will evenly divide the number
                if(stringValue.length() % j == 0){
                    while(sb.toString().length() < stringValue.length()){
                        sb.append(stringPart);
                    }
                    if(sb.toString().equals(stringValue)){
                        total += i;
                        break;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        Day2Part2 day2Part1 = new Day2Part2();
        System.out.println(day2Part1.total);

    }
}
