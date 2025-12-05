package org.adventofcode.y2025.day4;

import java.util.ArrayList;
import java.util.List;

import org.adventofcode.y2025.ReadFile;

public class Day4Part2 extends ReadFile {
    int totalCount = 0;
    int lineLength;
    List<String> rollMap = new ArrayList<>();
    public Day4Part2() {
        super("src/main/resources/y2025/day4/input.txt");
        lineLength = lines.getFirst().length();
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
        int iterationCount = processMap();
        totalCount += iterationCount;
        while(iterationCount > 0){
            iterationCount = processMap();
            totalCount += iterationCount;
        }
        System.out.println("total count " + totalCount);
    }

    public int processMap(){
        int count = 0;
        rollMap.forEach(System.out::println);
        for(int i = 1;i < lineLength + 1;i++){
            for(int j = 1;j < rollMap.size() -1;j++){
                if(checkRoll(i,j)){
                    count++;
                    StringBuilder sb = new StringBuilder(rollMap.get(i));
                    sb.replace(j,j+1, ".");
                    rollMap.set(i, sb.toString());
                }
            }
        }
        System.out.println(count);
        return count;
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
        //System.out.println("row : " + row + " col : " + col + " spot : " + rollMap.get(row).charAt(col) + " : count " + neighborRolls);
        return neighborRolls < 4 && isCheckableSpot;
    }

    public static void main(String[] args) {
        Day4Part2 day4Part1 = new Day4Part2();
    }
}
