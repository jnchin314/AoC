package org.adventofcode.y2025.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.adventofcode.y2025.ReadFile;

public class Day3Part1 extends ReadFile {
    int count = 0;
    public Day3Part1() {
        super("src/main/resources/y2025/day3/input.txt");
        lines.forEach(line -> {
            count += this.getLargestBattery(line);
        });
        System.out.println(count);
    }

    public int[] largestDigit(String line, int start, int fromEnd){
        int[] largest = new int[2];
        int  largestDigitValue = -1;
        int largestDigitPlace = -1;
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < line.length() - fromEnd;i++){
            int currentValue = Integer.valueOf(String.valueOf(line.charAt(i)));
            if( currentValue > largestDigitValue){
                largestDigitValue = currentValue;
                largestDigitPlace = i;
            }
        }
        largest[0] = largestDigitValue;
        largest[1] = largestDigitPlace;
        return largest;
    }

    public int getLargestBattery(String line){
        int digitStart = 0;
        System.out.println("LINE : " + line);
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i>=0;i--) {
            int[] largest = largestDigit(line, digitStart, i);
            sb.append(largest[0]);
            digitStart = largest[1] + 1;
            System.out.println("digit start " + digitStart);
        }
        System.out.println(sb);
        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) {
        Day3Part1 day3Part1 = new Day3Part1();
    }
}
