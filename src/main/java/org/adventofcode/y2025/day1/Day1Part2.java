package org.adventofcode.y2025.day1;
//https://adventofcode.com/2025/day/1

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day1Part2 {

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

        public int rotateLock(String rotation){
            //Assumption we are only rotating the lock a 'sane' amount
            int rotateValue = Integer.parseInt(rotation.substring(1));
            int rotatedZeroCount;
            if (rotation.charAt(0) == 'L'){
                //towards the smaller numbers so we want to subtract

                //these if statements are awful
                if(currentLocation < rotateValue) {
                    if(currentLocation == 0){
                        rotatedZeroCount = Math.floorDiv(currentLocation - rotateValue, -100);
                    }else {
                        rotatedZeroCount = Math.floorDiv(currentLocation - rotateValue, -100) + 1;
                    }
                }else if(currentLocation > rotateValue){
                    rotatedZeroCount = 0;
                }else{
                    rotatedZeroCount = 1;
                }
                rotateValue *= -1;
            }else{
                rotatedZeroCount = (currentLocation + rotateValue) / 100;
            }

            // the + 100 and modding 100 again is in case the number is under negative we can wrap to the top
            currentLocation = ((currentLocation + rotateValue) % 100 + 100) % 100;

            secretHitCount += rotatedZeroCount;

            return currentLocation;

        }



    }

    public static void main(String[] args) {
        List<String> lines = null;
        Lock lock = new Lock(0, 50);
        try {
            lines = Files.readAllLines(Path.of("src/main/resources/y2025/day1/input1.txt"));
            lines.forEach(rotate -> {System.out.println(lock.getCurrentLocation() + " : rotate " + rotate + " : new position " + lock.rotateLock(rotate) + " : count " + lock.getSecretHitCount());});
            System.out.println(lock.getSecretHitCount());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


