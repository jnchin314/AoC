package org.adventofcode.y2025.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.adventofcode.y2025.ReadFile;

public class Day6Part2 extends ReadFile {
    long count = 0;

    @FunctionalInterface
    public interface Operation {
        long execute(long v1, long v2);
    }


    public Day6Part2() {
        super("src/main/resources/y2025/day6/input.txt");
        List<List<String>> problemSet = new ArrayList<>();
        lines.forEach(System.out::println);


        int linePosition = 0;
        while (linePosition < lines.getLast().length()){
            int columnWidth = columnSize(lines.getLast(), linePosition);
            int finalLinePosition = linePosition;
            int finalLineEnd = finalLinePosition + columnWidth;

            String operator = getOperatorString(lines.getLast(), finalLinePosition, finalLineEnd);
            Operation operation = getOperation(operator);
            List<Long> sbList = getTransformedNumbers(lines, columnWidth, finalLinePosition);
            long localTotal = sbList.getFirst();
            for(int i = 1;i < sbList.size();i++){
                localTotal = operation.execute(localTotal, sbList.get(i));
            }
            System.out.println(operator);
            System.out.println(localTotal);
            System.out.println("-------");
            count+= localTotal;
            linePosition += columnWidth;
        }
        System.out.println(count);
    }

    private String getOperatorString(String operatorLine, int finalLinePosition, int finalLineEnd) {
        return operatorLine.substring(finalLinePosition, finalLineEnd).trim();
    }

    private List<Long> getTransformedNumbers(List<String> lines, int columnWidth, int finalLinePosition) {
        List<StringBuffer> sbList = new ArrayList<>();
        int finalLineEnd = finalLinePosition + columnWidth;
        for(int i = 0; i < columnWidth; i++){
            sbList.add(new StringBuffer());
        }
        for(int i = 0; i < 4;i++){
            String extractedNumber = lines.get(i).substring(finalLinePosition, finalLineEnd);
            System.out.println(extractedNumber);
            for(int digitPlace = 0;digitPlace < extractedNumber.length();digitPlace++){
                sbList.get(digitPlace).append(extractedNumber.charAt(digitPlace));
            }
        }
        return sbList.stream()
                     .map(StringBuffer::toString)
                     .map(String::trim)
                     .filter(string -> !string.isBlank())
                     .map(Long::parseLong)
                     .toList();
    }

    public int columnSize(String operatorLine, int beginIndex){
        int width = 1;
        while(beginIndex + 1 < operatorLine.length() && operatorLine.charAt(beginIndex + 1) == ' '){
            beginIndex++;
            width++;
        }
        return width;
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

        Day6Part2 day6Part2 = new Day6Part2();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + " ms");
    }
}