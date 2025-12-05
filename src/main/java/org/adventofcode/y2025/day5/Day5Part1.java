package org.adventofcode.y2025.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import com.sun.source.tree.Tree;
import org.adventofcode.y2025.ReadFile;
import org.adventofcode.y2025.day4.Day4Part1;

public class Day5Part1 extends ReadFile {
    int count = 0;
    List<String> validIdRange;
    public Day5Part1() {
        super("src/main/resources/y2025/day5/input.txt");
        validIdRange = new ArrayList<>();
        lines.subList(0, lines.indexOf("")).forEach(line -> storeValidIds(line, validIdRange));

        lines.subList(lines.indexOf("") + 1, lines.size()).forEach(value -> {
            if(checkWithinRange(validIdRange, value)){
                count++;
            }
        });

        System.out.println(count);
    }

    Long[] splitRange(String idRange){
        return Arrays.stream(idRange.split("-")).map(Long::parseLong).toList().toArray(Long[]::new);
    }

    boolean checkWithinRange(List<String> validIdRange, String value){
        for (String idRange : validIdRange) {
            long longValue = Long.parseLong(value);
            Long[] range = splitRange(idRange);
            if(range[0] <= longValue && longValue <= range[1]){
                return true;
            }
        }
        return false;
    }

    List<String> storeValidIds(String idRange, List<String> validIds){
        validIds.add(idRange);
        return validIds;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Day5Part1 day5Part1 = new Day5Part1();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + " ms");
    }
}
