package org.adventofcode.y2025.day8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.adventofcode.y2025.ReadFile;

public class Day8Part1 extends ReadFile {
    long count = 0;
    Map<JunctionBox,Set<JunctionBox>> junctionBoxToCircuits;

    public Day8Part1() {
        super("src/main/resources/y2025/day8/input.txt");

        List<JunctionBox> junctionBoxList = createJunctionBoxList(lines);
        List<DistancePair> distancePairs = createDistancePairList(junctionBoxList);
        junctionBoxToCircuits = new HashMap<>();
        System.out.println(distancePairs);
        System.out.println(junctionBoxList);
        for(int i = 0;i < 1000;i++){
            addJunctionBoxToCircuit(distancePairs.get(i).getJunctionBox1(), distancePairs.get(i).getJunctionBox2(), junctionBoxToCircuits);
        }

        Set<Set<JunctionBox>> circuitSet = new HashSet<>(junctionBoxToCircuits.values());
        List<Set<JunctionBox>> circuitList = new ArrayList<>(circuitSet.stream().toList());
        circuitList = circuitList.stream().sorted(Comparator.comparingInt(Set::size)).toList().reversed();

        count = (long) circuitList.getFirst().size() * circuitList.get(1).size() * circuitList.get(2).size();
        System.out.println(count);

    }

    public Map<JunctionBox,Set<JunctionBox>> addJunctionBoxToCircuit(JunctionBox junctionBox1, JunctionBox junctionBox2, Map<JunctionBox,Set<JunctionBox>> junctionBoxToCircuits){
        Set<JunctionBox> circuit1 = junctionBoxToCircuits.get(junctionBox1);
        Set<JunctionBox> circuit2 = junctionBoxToCircuits.get(junctionBox2);


        Set<JunctionBox> targetCircuit;
        Set<JunctionBox> otherCircuit;

        //if they dont exist add to one circuit
        if (circuit1 == null && circuit2 == null){
            Set<JunctionBox> newCircuit = new HashSet<JunctionBox>();
            newCircuit.add(junctionBox1);
            newCircuit.add(junctionBox2);
            junctionBoxToCircuits.put(junctionBox1, newCircuit);
            junctionBoxToCircuits.put(junctionBox2, newCircuit);
            return junctionBoxToCircuits;
        }

        if (circuit1 != null) {
            targetCircuit = circuit1;
            otherCircuit = circuit2;
        }
        else {
            targetCircuit = circuit2;
            otherCircuit = circuit1;
        }


        //check for same reference hence !=
        if(otherCircuit != null && otherCircuit != targetCircuit){
            targetCircuit.addAll(otherCircuit);
            for(JunctionBox jbox : otherCircuit){
                //put same set reference
                junctionBoxToCircuits.put(jbox, targetCircuit);
            }

        }

        //this logic should cover for now the case where we have two circuits and when one is null
        targetCircuit.add(junctionBox1);
        targetCircuit.add(junctionBox2);
        junctionBoxToCircuits.put(junctionBox1, targetCircuit);
        junctionBoxToCircuits.put(junctionBox2, targetCircuit);

        return junctionBoxToCircuits;
    }

    public List<JunctionBox> createJunctionBoxList(List<String> junctionBoxes){
        return junctionBoxes.stream().map(JunctionBox::new).toList();
    }

    public List<DistancePair> createDistancePairList(List<JunctionBox> junctionBoxList){
        List<DistancePair> distancePairs = new ArrayList<>();
        for(int i = 0;i < junctionBoxList.size()-1;i++){
            for(int j = i+1;j < junctionBoxList.size();j++){
                distancePairs.add(new DistancePair(junctionBoxList.get(i), junctionBoxList.get(j)));
            }
        }
        distancePairs.sort(Comparator.comparingDouble(DistancePair::getDistance));
        return distancePairs;
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Day8Part1 day8Part1 = new Day8Part1();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + " ms");
    }
}