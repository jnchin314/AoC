package org.adventofcode.y2025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class ReadFile {
    protected List<String> lines;
    public ReadFile(String string){
        try {
            //one line
            lines = Files.readAllLines(Path.of(string));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
