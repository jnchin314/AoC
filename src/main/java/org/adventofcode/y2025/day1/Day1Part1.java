package org.adventofcode.y2025.day1;
//https://adventofcode.com/2025/day/1

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day1Part1 {

    public static class Lock {
        private int currentLocation = 0;
        private int secretHitCount = 0;
        private int lockSpot;

        public Lock(int lockSpot, int currentLocation){
            this.lockSpot = lockSpot;
            this.currentLocation = currentLocation;
        }

        public int getSecretHitCount() {
            return secretHitCount;
        }
        public int getCurrentLocation() {
            return currentLocation;
        }

        public void rotateLock(String rotation){
            //Assumption we are only rotating the lock a 'sane' amount
            int rotateValue = Integer.parseInt(rotation.substring(1));
            if (rotation.charAt(0) == 'L'){
                //towards the smaller numbers so we want to subtract
                rotateValue *= -1;
            }

            // the + 100 and modding 100 again is in case the number is under negative we can wrap to the top
            currentLocation = ((currentLocation + rotateValue) % 100 + 100) % 100;

            if (currentLocation == lockSpot){
                secretHitCount++;
            }
        }



    }

    public static void main(String[] args) {
        List<String> lines = null;
        Lock lock = new Lock(0, 50);
        try {
            lines = Files.readAllLines(Path.of("src/main/resources/y2025/day1/input1.txt"));
            lines.forEach(rotate -> {lock.rotateLock(rotate); System.out.println(lock.getCurrentLocation());});
            System.out.println(lock.getSecretHitCount());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


