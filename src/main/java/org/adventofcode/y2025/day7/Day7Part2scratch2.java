package org.adventofcode.y2025.day7;

import java.util.Arrays;

import org.adventofcode.y2025.ReadFile;

public class Day7Part2scratch2 extends ReadFile {
    long count = 0;


    public Day7Part2scratch2() {
        super("src/main/resources/y2025/day7/input.txt");
        int indexStart;
        lines.getFirst().indexOf("S");
        char startingChar = 'A';
        for (int row = 0;row < lines.size()-1;row++){
            String line = lines.get(row);
            int nextRow = row + 1;
            String nextLine = lines.get(nextRow);
            StringBuilder nextLineSb = new StringBuilder(nextLine);
            for(int col = 0;col < line.length();col++){
                char currentChar = line.charAt(col);
                char nextLineChar = nextLineSb.charAt(col);
                if(currentChar == 'S'){
                    if(nextLineSb.charAt(col) == '.'){
                        nextLineSb.setCharAt(col, (char) startingChar);
                    }
                }else if(currentChar != '.' && currentChar != '^'){
                    char beam = currentChar;
                    if(nextLineSb.charAt(col) == '.'){
                        nextLineSb.setCharAt(col,beam);
                    }else if(nextLineSb.charAt(col) == '^'){
                        count++;
                        if(nextLineSb.charAt(col-1) == '.'){
                            nextLineSb.setCharAt(col-1,beam);
                        }else{
                            nextLineSb.setCharAt(col-1,(char)(beam - startingChar + 1 + nextLineSb.charAt(col-1)));
                            if(beam - startingChar +1 > 35000 && nextLineSb.charAt(col-1) > 35000){
                                System.out.println(beam - startingChar + 1 + " + " +  (int)nextLineSb.charAt(col-1) + " = " + (int)(char)(beam - startingChar + 1 + nextLineSb.charAt(col-1)));

                            }
                        }
                        if(nextLineSb.charAt(col + 1) == '.'){
                            nextLineSb.setCharAt(col+1,beam);
                        }else{
                            nextLineSb.setCharAt(col+1, (char)(beam - startingChar + 1 + nextLineSb.charAt(col+1)));
                            if(beam - startingChar +1 > 35000 && nextLineSb.charAt(col+1) > 35000){
                                System.out.println("TOO BIG 2");
                            }
                        }
                    }else{
                        nextLineSb.setCharAt(col, (char)(beam - startingChar + 1 + nextLineChar));
                        if(beam - startingChar +1 > 35000 && nextLineChar > 35000){
                            System.out.println("TOO BIG 3");
                        }
                    }
                }
            }
            lines.set(nextRow, nextLineSb.toString());
        }
        lines.forEach(System.out::println);
        System.out.println(count);
        int newCount;
        String[] string  = lines.getLast().split("");
        Arrays.stream(string).forEach(
                s -> {
                    if(s.charAt(0) != '.'){
                        System.out.println(s.charAt(0) - startingChar + 1);
                    }else{
                        System.out.println(".");
                    }
                }
        );
        newCount = Arrays.stream(string).mapToInt(s -> {
            if(s.charAt(0) != '.'){
                return (s.charAt(0) - startingChar + 1);
            }
            else{
                return 0;
            }
        }).sum();
        System.out.println(newCount);

    }




    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Day7Part2scratch2 day7Part2 = new Day7Part2scratch2();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + " ms");
    }
}