package org.adventofcode.y2025.day8;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class JunctionBox {
    List<Integer> coordinates;
    public JunctionBox(String s){
        coordinates = createCoordinates(s);
    }

    public int getX(){
            return coordinates.get(0);
    }

    public int getY(){
        return coordinates.get(1);
    }

    public int getZ(){
        return coordinates.get(2);
    }

    private List<Integer> createCoordinates(String s){
        return Arrays.stream(s.split(",")).map(Integer::parseInt).toList();
    }

    @Override
    public String toString() {
        return "JunctionBox{" +
                "coordinates=" + coordinates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        JunctionBox that = (JunctionBox) o;
        return Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coordinates);
    }
}
