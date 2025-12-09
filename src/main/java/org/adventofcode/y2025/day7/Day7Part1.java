package org.adventofcode.y2025.day7;

import org.adventofcode.y2025.ReadFile;

public class Day7Part1 extends ReadFile {
    long count = 0;


    public Day7Part1() {
        super("src/main/resources/y2025/day7/input.txt");
        int indexStart;
        lines.getFirst().indexOf("S");
        for (int row = 0;row < lines.size()-1;row++){
            String line = lines.get(row);
            int nextRow = row + 1;
            String nextLine = lines.get(nextRow);
            StringBuilder nextLineSb = new StringBuilder(nextLine);
            for(int col = 0;col < line.length();col++){
                if(line.charAt(col) == 'S' || line.charAt(col) == '|'){
                    if(nextLineSb.charAt(col) == '.'){
                        nextLineSb.setCharAt(col, '|');
                    }
                    if(nextLineSb.charAt(col) == '^'){
                        count++;
                        nextLineSb.setCharAt(col-1,'|');
                        nextLineSb.setCharAt(col+1, '|');
                    }
                }

            }
            lines.set(nextRow, nextLineSb.toString());
        }
        lines.forEach(System.out::println);
        System.out.println(count);
    }



    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Day7Part1 day7Part1 = new Day7Part1();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + " ms");
    }
}