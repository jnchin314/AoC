package org.adventofcode.y2025.day9.part2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class CompressedGrid {
    List<List<Character>> grid;

    List<Integer> xSorted, ySorted;
    Map<Integer, Integer> xAxisMap, yAxisMap;


    public CompressedGrid(int gridSize, List<Point> lines){
        initializeGrid(lines);
        for(List<Character> row : grid.reversed()){
            for(char c: row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public boolean isWithinArea(Rectangle rectangle){
        Point p1 = rectangle.getCorner1();
        Point p2 = rectangle.getCorner2();
        Point smallP;
        StringBuilder smallPString = new StringBuilder();
        Point largeP;
        StringBuilder largePString = new StringBuilder();
        if (p1.getX() < p2.getX()){
            smallPString.append(p1.getX()).append(",");
            largePString.append(p2.getX()).append(",");
        }else{
            smallPString.append(p2.getX()).append(",");
            largePString.append(p1.getX()).append(",");
        }

        if (p1.getY() < p2.getY()){
            smallPString.append(p1.getY());
            largePString.append(p2.getY());
        }else{
            smallPString.append(p2.getY());
            largePString.append(p1.getY());
        }
        smallP = new Point(smallPString.toString());
        largeP = new Point(largePString.toString());

        for(int row = smallP.getConvertedY();row <= largeP.getConvertedY();row++){
            for(int col = smallP.getConvertedX();col <= largeP.getConvertedX();col++){
                char c = grid.get(row).get(col);
                //check area
                if (grid.get(row).get(col) !='X' && grid.get(row).get(col) !='#'){
                    return false;
                }
            }
        }
        return true;
    }

    private Map<Integer,Integer> createCompressedAxisMap(List<Point> lines, ToIntFunction<Point> axis){
        List<Integer> sorted = getSortedAxis(lines, axis);
        Map<Integer,Integer> compressedAxisMap = new HashMap<>();
        for(int i = 0;i< sorted.size();i++){
            compressedAxisMap.put(sorted.get(i), i);
        }

        return compressedAxisMap;
    }

    private static List<Integer> getSortedAxis(List<Point> lines, ToIntFunction<Point> toIntFunction) {
        //adding the point, and the place after the point.
        // This is to allow for representation of the line between the two points
        return lines.stream()
                    .mapToInt(toIntFunction).flatMap(v -> Stream.of(
                            v, v+1).mapToInt(x -> x))
                    .distinct()
                    .sorted()
                    .boxed()
                    .toList();
    }

    public void initializeGrid(List<Point> points){
        xAxisMap = createCompressedAxisMap(points, Point::getX);
        yAxisMap = createCompressedAxisMap(points, Point::getY);
        Point.setCoordinateMaps(xAxisMap,yAxisMap);

        grid = new ArrayList<>();
        for(int row = 0;row < yAxisMap.size();row++){
            List<Character> rows = new ArrayList<>();
            for(int col = 0; col < xAxisMap.size();col++){
                rows.add('.');
            }
            grid.add(rows);
        }


        LinkAllPoints(points);
        fillPolygon();
    }

    private void fillPolygon() {
        int rowSize = grid.size();
        int colSize = grid.getFirst().size();
        for(int row = 1;row < rowSize - 1;row++){
            boolean inside = false;
            boolean topCrossed = false;
            boolean bottomCrossed = false;
            for(int col = 0;col < colSize - 1;col++){
                if((col == 0 && grid.get(row).get(col) == '#') || grid.get(row).get(col) == '#' && grid.get(row).get(col - 1) != '#' && grid.get(row).get(col + 1) != '#' ){
                    inside = !inside;
                }else {
                    if (grid.get(row).get(col) == '#' && (grid.get(row).get(col - 1) != '#' && grid.get(row).get(col + 1) == '#')) {
                        if (grid.get(row - 1).get(col) == '#') {
                            topCrossed = true;
                        } else {
                            bottomCrossed = true;
                        }
                    }
                    if (grid.get(row).get(col) == '#' && (grid.get(row).get(col - 1) == '#' && grid.get(row).get(col + 1) != '#')) {
                        if ((grid.get(row - 1).get(col) == '#' && bottomCrossed) || (grid.get(row + 1).get(col) == '#' && topCrossed)) {
                                inside = !inside;
                        }

                        bottomCrossed = false;
                        topCrossed = false;
                    }
                }

                if(grid.get(row).get(col) == '.' && inside){
                    grid.get(row).set(col, 'X');
                }
            }
        }
    }

    private void LinkAllPoints(List<Point> points) {
        Point p1;
        Point p2;
        for(int i = 0; i < points.size()-1; i++){
            p1 = points.get(i);
            p2 = points.get(i+1);

            linkPoints(p1, p2);
        }
        p1 = points.getFirst();
        p2 = points.getLast();
        linkPoints(p1,p2);
    }

    private void linkPoints(Point p1, Point p2) {
        Point targetPoint;
        if(p1.getConvertedX() == p2.getConvertedX()){
            targetPoint = p1.getConvertedY() < p2.getConvertedY() ? p1 : p2;
            int distance = Math.abs(p1.getConvertedY() - p2.getConvertedY());
            for(int j = targetPoint.getConvertedY();j <= targetPoint.getConvertedY() + distance;j++){
                grid.get(j).set(p1.getConvertedX(), '#');
            }
        }
        if(p1.getConvertedY() == p2.getConvertedY()){
            targetPoint = p1.getConvertedX() < p2.getConvertedX() ? p1 : p2;
            int distance = Math.abs(p1.getConvertedX() - p2.getConvertedX());
            for(int j = targetPoint.getConvertedX();j <= targetPoint.getConvertedX() + distance;j++){
                grid.get(p1.getConvertedY()).set(j, '#');
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Grid:\n");
        for(List<Character> row : grid){
            sb.append(row);
            sb.append("\n");
        }
        return sb.toString();
    }
}
