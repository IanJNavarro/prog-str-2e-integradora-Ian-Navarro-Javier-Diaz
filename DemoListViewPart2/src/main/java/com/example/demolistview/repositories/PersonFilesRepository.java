package com.example.demolistview.repositories;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class PersonFilesRepository {

    private final Path filetPath = Paths.get("data","persons.csv");

    private void ensureFile() throws IOException {
        if(Files.notExists(filetPath)){
            Files.createFile(filetPath);
        }
    }

    public List<String> readAllLines() throws IOException {
        ensureFile();
        return Files.readAllLines(filetPath);
    }

    public void appendNewLine(String line) throws IOException {
        ensureFile();
        Files.writeString(filetPath,line+System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    public void saveFile(List<String> lines) throws IOException {
        ensureFile();
        Files.write(filetPath, lines , StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
    }


}
