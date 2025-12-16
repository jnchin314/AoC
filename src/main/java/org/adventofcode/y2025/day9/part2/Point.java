package org.adventofcode.y2025.day9.part2;

import java.util.*;

public class Point {
    private List<Integer> coordinates;
    private static Map<Integer,Integer> X_MAP = new HashMap<>(),  Y_MAP = new HashMap<>();
    public Point(String s){
        coordinates = createCoordinates(s);
    }
    public Point(int x, int y){
        coordinates = new ArrayList<>();
        coordinates.add(x);
        coordinates.add(y);
    }
    public static void setCoordinateMaps(Map<Integer,Integer> xMap, Map<Integer,Integer> yMap){
        X_MAP = xMap;
        Y_MAP = yMap;
    }

    public int getConvertedX(){
        return X_MAP.get(coordinates.getFirst());
    }

    public int getConvertedY(){
        return Y_MAP.get(coordinates.getLast());
    }

    public int getX(){
            return coordinates.getFirst();
    }

    public int getY(){
        return coordinates.getLast();
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
