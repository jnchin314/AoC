package org.adventofcode.y2025.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

public class Day5Part2 extends ReadFile {
    long count = 0;
    List<List<Long>> validIdRange;
    List<List<Long>> mergedRange;
    public Day5Part2() {
        super("src/main/resources/y2025/day5/input.txt");
        validIdRange = new ArrayList<>();
        lines.subList(0, lines.indexOf("")).forEach(line -> storeValidIds(line, validIdRange));
        System.out.println(validIdRange);
        validIdRange.sort(Comparator.comparingLong(List::getFirst));
        System.out.println(validIdRange);
        mergedRange = mergeIds(validIdRange);
        for(List<Long> range : mergedRange){
            count += range.getLast() - range.getFirst() + 1;
        }
        System.out.println(mergedRange);
        System.out.println(count);
    }

    List<Long> splitRange(String idRange){
        return Arrays.stream(idRange.split("-")).map(Long::parseLong).toList();
    }
    boolean checkWithinRange(List<Long> validIdRange, Long value){
        if(validIdRange.getFirst() <= value && value <= validIdRange.getLast() + 1){
            return true;
        }
        return false;
    }

    public List<List<Long>> mergeIds(List<List<Long>> idList){
        List<List<Long>> mergedList = new ArrayList<>();
        List<Long> mergedItemRange = new ArrayList<>(idList.getFirst());

        for(int i = 1;i< idList.size();i++){
            List<Long> currentRange = idList.get(i);
            long start = currentRange.getFirst();
            long end = currentRange.getLast();
            if(checkWithinRange(mergedItemRange, start)){
                if(mergedItemRange.getLast() < end){
                    mergedItemRange.set(1, end);
                }
            }else{
                mergedList.add(mergedItemRange);
                mergedItemRange = new ArrayList<>(currentRange);
            }
        }
        mergedList.add(mergedItemRange);
        return mergedList;
    }


    List<List<Long>> storeValidIds(String idRange, List<List<Long>> validIds){
        validIds.add(splitRange(idRange));
        return validIds;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Day5Part2 day5Part2 = new Day5Part2();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + " ms");
    }
}
