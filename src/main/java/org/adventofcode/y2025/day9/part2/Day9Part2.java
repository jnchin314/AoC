package org.adventofcode.y2025.day9.part2;

import org.adventofcode.y2025.ReadFile;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day9Part2 extends ReadFile {
    long count = 0;
    List<Point> pointList;
    List<Rectangle> rectangleList;
    CompressedGrid grid;
    public Day9Part2() {
        super("src/main/resources/y2025/day9/input.txt");
        fillPointList();

        grid = new CompressedGrid(100000, pointList);

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
                if(grid.isWithinArea(rectangle)) {
                    rectangleList.add(rectangle);
                }
            }
        }
        return rectangleList.stream().sorted(Comparator.comparingLong(Rectangle::getArea)).toList();
    }

    private void fillPointList() {
        pointList = new ArrayList<>();
        lines.forEach(line -> pointList.add(new Point(line)));
    }

    public void printGrid(){

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Day9Part2 day9Part2 = new Day9Part2();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + " ms");
    }
}