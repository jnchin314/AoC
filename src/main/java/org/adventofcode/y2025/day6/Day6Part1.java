package org.adventofcode.y2025.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.adventofcode.y2025.ReadFile;

public class Day6Part1 extends ReadFile {
    long count = 0;

    @FunctionalInterface
    public interface Operation {
        long execute(long v1, long v2);
    }


    public Day6Part1() {
        super("src/main/resources/y2025/day6/input.txt");
        List<List<String>> problemSet = new ArrayList<>();
        lines.forEach(line -> problemSet.add(Arrays.stream(line.split("\\s+")).toList()));
        problemSet.forEach(System.out::println);

        for(int column = 0; column < problemSet.getFirst().size();column++) {
            String operator = problemSet.getLast().get(column);
            long localTotal = Long.parseLong(problemSet.getFirst().get(column));
            for (int row = 1; row < problemSet.size() - 1; row++) {
                localTotal = getOperation(operator).execute(localTotal, Long.parseLong(problemSet.get(row).get(column)));
            }
            count += localTotal;
        }

        System.out.println(count);
    }

    public Operation getOperation(String operator){
        Operation multiply = Math::multiplyExact;
        Operation add = Long::sum;
        if (operator.equals("*")) {
            return multiply;
        }
        return add;
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Day6Part1 day6Part1 = new Day6Part1();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + " ms");
    }
}