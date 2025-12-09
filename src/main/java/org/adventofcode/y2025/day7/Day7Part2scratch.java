package org.adventofcode.y2025.day7;

import java.util.ArrayList;
import java.util.List;

import org.adventofcode.y2025.ReadFile;

public class Day7Part2scratch extends ReadFile {
    long count = 0;


    public Day7Part2scratch() {
        super("src/main/resources/y2025/day7/input.txt");
        int indexStart;
        String firstLine = lines.getFirst();
        List<String> answer = new ArrayList<>();
        List<String> choice = new ArrayList<>();
        choice.add(firstLine);
        backTracking(answer, choice, 0);
        //manyWorldList.forEach(world -> world.forEach(System.out::println));
    }

    public void backTracking(List<String> backTracking, List<String> rowChoices, int currentRow){
        //backTracking.forEach(System.out::println);
        if(backTracking.size() == lines.size() -1){
            backTracking.forEach(System.out::println);
            System.out.println("-----------");
            count++;
            return;
        }


        for(String row : rowChoices) {
                backTracking.add(row);
                backTracking(backTracking, createChoices(backTracking, currentRow), currentRow + 1);
                backTracking.removeLast();
        }

    }

    private List<String> createChoices(List<String> backTracking, int currentRow){
        int nextRow = currentRow + 1;
        String row = backTracking.get(currentRow);
        String nextLine = lines.get(nextRow);
        StringBuilder nextLineSb = new StringBuilder(nextLine);
        List<String> newChoices = new ArrayList<>();
        for (int col = 0; col < row.length(); col++) {
            if (row.charAt(col) == 'S' || row.charAt(col) == '|') {
                if (nextLineSb.charAt(col) == '.') {
                    nextLineSb.replace(col, col + 1, "|");
                    newChoices.add(nextLineSb.toString());
                    return newChoices;
                }
                if (nextLineSb.charAt(col) == '^') {

                    StringBuilder sbLeft = new StringBuilder(nextLineSb);
                    StringBuilder sbRight = new StringBuilder(nextLineSb);
                    newChoices.add(sbLeft.replace(col - 1, col, "|").toString());
                    newChoices.add(sbRight.replace(col + 1, col + 2, "|").toString());
                    return newChoices;
                }
            }
        }
        newChoices.add(nextLineSb.toString());
        return newChoices;
    }




    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Day7Part2scratch day7Part2 = new Day7Part2scratch();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + " ms");
    }
}