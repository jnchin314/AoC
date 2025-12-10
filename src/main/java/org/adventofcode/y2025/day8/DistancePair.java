package org.adventofcode.y2025.day8;

public class DistancePair {
    private final Double distance;
    private final JunctionBox junctionBox1, junctionBox2;


    public DistancePair(JunctionBox c1, JunctionBox c2) {
        this.distance = Math.sqrt(
                Math.pow(c2.getX() - c1.getX(), 2) +
                        Math.pow(c2.getY() - c1.getY(), 2) +
                        Math.pow(c2.getZ() - c1.getZ(), 2)
        );
        this.junctionBox1 = c1;
        this.junctionBox2 = c2;
    }

    @Override
    public String toString() {
        return "DistancePair{" +
                "distance=" + distance +
                ", junctionBox1=" + junctionBox1 +
                ", junctionBox2=" + junctionBox2 +
                '}' + "\n";
    }

    public static DistancePair createDistancePair(JunctionBox c1, JunctionBox c2) {
        return new DistancePair(c1, c2);
    }

    public Double getDistance() {
        return distance;
    }

    public JunctionBox getJunctionBox1() {
        return junctionBox1;
    }

    public JunctionBox getJunctionBox2() {
        return junctionBox2;
    }
}
