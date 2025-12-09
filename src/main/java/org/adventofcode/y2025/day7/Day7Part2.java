package org.adventofcode.y2025.day7;

import java.util.Arrays;

import org.adventofcode.y2025.ReadFile;

public class Day7Part2 extends ReadFile {
    long count = 0;
    public  record GridSpot(char c, int value){};
    long[][] gridspots;
    public Day7Part2() {
        super("src/main/resources/y2025/day7/input.txt");
        gridspots = new long[lines.size()][lines.getFirst().length()];

        int indexStart;
        lines.getFirst().indexOf("S");
        char beam = '|';
        for (int row = 0;row < lines.size()-1;row++){
            String line = lines.get(row);
            int nextRow = row + 1;
            String nextLine = lines.get(nextRow);
            StringBuilder nextLineSb = new StringBuilder(nextLine);
            for(int col = 0;col < line.length();col++){
                char currentChar = line.charAt(col);
                char nextLineChar = nextLineSb.charAt(col);
                if(currentChar == 'S'){
                        nextLineSb.setCharAt(col, beam);
                        gridspots[nextRow][col] = 1;
                } else if(currentChar == beam){
                    if(nextLineSb.charAt(col) == '.'){
                        nextLineSb.setCharAt(col,beam);
                        gridspots[nextRow][col] = gridspots[row][col];
                    }else if(nextLineSb.charAt(col) == '^'){
                        count++;
                        nextLineSb.setCharAt(col - 1,beam);
                        nextLineSb.setCharAt(col + 1,beam);
                        gridspots[nextRow][col - 1] = gridspots[nextRow][col - 1] +  gridspots[row][col];
                        gridspots[nextRow][col + 1] = gridspots[nextRow][col + 1] +  gridspots[row][col];
                    } else{
                        nextLineSb.setCharAt(col, beam);
                        gridspots[nextRow][col] = gridspots[nextRow][col] +  gridspots[row][col];
                    }
                }
            }
            lines.set(nextRow, nextLineSb.toString());
        }
        lines.forEach(System.out::println);
        System.out.println(count);
        count = 0;

        for(long[] lastRowGridSpot : gridspots){
            for(long n : lastRowGridSpot){
                System.out.print(n);
            }
            System.out.println();
        }
        for(long lastRowGridSpot : gridspots[lines.size()-1]){
            count += lastRowGridSpot;
        }
        System.out.println(count);
    }





    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Day7Part2 day7Part2 = new Day7Part2();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + " ms");
    }
}