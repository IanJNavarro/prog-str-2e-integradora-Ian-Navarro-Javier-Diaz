package com.example.demolistview.services;

import com.example.demolistview.repositories.PersonFilesRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

    PersonFilesRepository repo = new PersonFilesRepository();

    public List<String> loadDataForListView() throws IOException {

        List<String> lines = repo.readAllLines();
        List<String> result = new ArrayList<>();

        for (String line: lines){
            if (line==null || line.isBlank()) continue;

            String[] parts = line.split(",");
            String name = parts[0];
            String email = parts[1];
            String age = parts[2];

            result.add(name + " - " + email + " - " + age );
        }

        return result;
    }

    public void addPerson(String name, String email, int age) throws IOException {
        validate(name, email, age);
        repo.appendNewLine(name+","+email+","+age);
    }

    private void validate(String name, String email, int age){
        if (name == null || name.isBlank() || name.length()<3){
            throw new IllegalArgumentException("El nombre es incorrecto");
        }
        String emailConverted = (email==null) ? "" : email.trim();
        if (emailConverted.isBlank() || !emailConverted.contains("@") || !emailConverted.contains(".")){
            throw new IllegalArgumentException("El email es incorrecto");
        }
        if (age < 18 || age > 110 ){
            throw new IllegalArgumentException("La edad no esta en un rango valido.");
        }
    }

    private List<String> getCleanLines() throws IOException {
        List<String> lines = repo.readAllLines();
        List<String> cleanLines = new ArrayList<>();

        for (String line : lines){
            if (line != null && !line.isBlank()){
                cleanLines.add(line);
            }
        }
        return cleanLines;
    }

    public void updateInfo(int indice, String name, String email, int age) throws IOException {
        if (indice < 0){
            throw new IllegalArgumentException("El indice es invalido");
        }
        List<String> data = getCleanLines();

        data.set(indice,name+","+email+","+age);
        repo.saveFile(data);
    }

    public void deletePerson(int index) throws IOException {
        if (index < 0){
            throw new IllegalArgumentException("El indice es invalido");
        }
        List<String> data = getCleanLines();

        data.remove(index);
        repo.saveFile(data);
    }
}
