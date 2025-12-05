package org.adventofcode.y2025.day4;

import java.util.ArrayList;
import java.util.List;

import org.adventofcode.y2025.ReadFile;
import org.adventofcode.y2025.day3.Day3Part1;

public class Day4Part1 extends ReadFile {
    int count = 0;
    List<String> rollMap = new ArrayList<>();
    public Day4Part1() {
        super("src/main/resources/y2025/day4/input.txt");
        int lineLength = lines.getFirst().length();
        StringBuilder sb = new StringBuilder();
        sb.repeat(".", lineLength+2);
        rollMap.add(sb.toString());
        for (String line : lines) {
            sb = new StringBuilder();
            rollMap.add(sb.append(".").append(line).append(".").toString());
        }
        sb = new StringBuilder();
        sb.repeat(".", lineLength+2);
        rollMap.add(sb.toString());
        rollMap.forEach(System.out::println);
        for(int i = 1;i < lineLength + 1;i++){
            for(int j = 1;j < rollMap.size() -1;j++){
                if(checkRoll(i,j)){
                    count++;
                }
            }
        }
        System.out.println(count);
    }



    public boolean checkRoll(int row, int col){
        boolean isCheckableSpot = rollMap.get(row).charAt(col) == '@';
        int neighborRolls = 0;
        for(int i = -1; i <= 1;i++){
            for (int j = -1;j <= 1;j++){
                if(!(i == 0 && j == 0) && isCheckableSpot && rollMap.get(row + i).charAt(col+j) == '@'){
                    neighborRolls++;
                }
            }
        }
        System.out.println("row : " + row + " col : " + col + " spot : " + rollMap.get(row).charAt(col) + " : count " + neighborRolls);
        return neighborRolls < 4 && isCheckableSpot;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Day4Part1 day4Part1 = new Day4Part1();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + " ms");
    }
}
