package org.adventofcode.y2025.day9.part2;

import java.util.Objects;

public class Rectangle {
    private final Long area;
    private final Point corner1, corner2;


    public Rectangle(Point c1, Point c2) {
        this.area = computeArea(c1, c2);
        this.corner1 = c1;
        this.corner2 = c2;
    }

    public long computeArea(Point corner1, Point corner2) {
       return (long) (Math.abs(corner1.getX() - corner2.getX()) + 1) * (Math.abs(corner1.getY() - corner2.getY()) + 1);
    }

    public Long getArea() {
        return area;
    }

    public Point getCorner1() {
        return corner1;
    }

    public Point getCorner2() {
        return corner2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(area, rectangle.area) && Objects.equals(corner1, rectangle.corner1) && Objects.equals(corner2, rectangle.corner2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, corner1, corner2);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "area=" + area +
                ", corner1=" + corner1 +
                ", corner2=" + corner2 +
                '}';
    }
}
