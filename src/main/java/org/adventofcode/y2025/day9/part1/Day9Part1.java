package org.adventofcode.y2025.day9.part1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.adventofcode.y2025.ReadFile;

public class Day9Part1 extends ReadFile {
    long count = 0;
    List<Point> pointList;
    List<Rectangle> rectangleList;

    public Day9Part1() {
        super("src/main/resources/y2025/day9/input.txt");
        pointList = new ArrayList<>();
        lines.forEach(line -> pointList.add(new Point(line)));

        rectangleList = fillRectangleList(pointList);
        System.out.println(rectangleList.getLast().getArea());
    }

    private List<Rectangle> fillRectangleList(List<Point> pointList){
        List<Rectangle> rectangleList = new ArrayList<>();
        Rectangle rectangle;
        Point corner1, corner2;
        for(int first = 0; first < pointList.size() - 1;first++){
            for(int second = first + 1; second < pointList.size();second++){
                corner1 = pointList.get(first);
                corner2 = pointList.get(second);
                rectangle = new Rectangle(corner1, corner2);

                rectangleList.add(rectangle);
            }
        }
        return rectangleList.stream().sorted(Comparator.comparingLong(Rectangle::getArea)).toList();
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Day9Part1 day9Part1 = new Day9Part1();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + " ms");
    }
}