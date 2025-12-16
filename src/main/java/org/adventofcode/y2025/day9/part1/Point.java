package org.adventofcode.y2025.day9.part1;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Point {
    List<Integer> coordinates;
    public Point(String s){
        coordinates = createCoordinates(s);
    }

    public int getX(){
            return coordinates.get(0);
    }

    public int getY(){
        return coordinates.get(1);
    }

    private List<Integer> createCoordinates(String s){
        return Arrays.stream(s.split(",")).map(Integer::parseInt).toList();
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinates=" + coordinates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Point that = (Point) o;
        return Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coordinates);
    }
}
