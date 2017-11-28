package ru.nikonorovcompany.service;

import ru.nikonorovcompany.pojo.Instruction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class InstructionCreator {
    private String[] elements;

    public InstructionCreator(String[] elements) {
        this.elements = elements;
    }

    private List<String> readUrlsFromFile(String fileName) throws IOException {
        String line;
        ArrayList<String> listOfStrings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "cp1251"))) {
            while ((line = reader.readLine()) != null) {
                listOfStrings.add(line);
            }
        }
        return listOfStrings;
    }

    private List<String> extractUrls() throws IOException {
        if (hasUrl()) {
            String regEx = "^http(s)?://[a-z0-9-]+(.[a-z0-9-]+)*(:[0-9]+)?(/.*)?$";
            return Arrays.stream(elements).filter(s -> Pattern.compile(regEx).matcher(s).matches()).collect(Collectors.toCollection(ArrayList::new));
        }

        return extractUrlsFromFiles();
    }

    private String[] extractFilesAdresses() {
        String regEx = "[^\\s]*\\.txt";
        return Arrays.stream(elements).filter(s -> Pattern.compile(regEx).matcher(s).matches()).toArray(String[]::new);
    }

    private List<String> extractUrlsFromFiles() throws IOException {
        String[] listOfTxtFiles = extractFilesAdresses();
        return extractFilesContent(listOfTxtFiles);
    }

    private List<String> extractFilesContent(String[] listOfTextFiles) throws IOException {
        List<String> listOfUrls = new ArrayList<>();
        for (String fileName : listOfTextFiles) {
            listOfUrls.addAll(readUrlsFromFile(fileName));
        }
        return listOfUrls;
    }

    private boolean hasUrl() {
        String regEx = "^http(s)?://[a-z0-9-]+(.[a-z0-9-]+)*(:[0-9]+)?(/.*)?$";
        return Arrays.stream(elements).anyMatch(s -> Pattern.compile(regEx).matcher(s).matches());
    }

    private boolean isContainCommand(char c) {
        String regEx = "^[-–]" + c + "$";
        return Arrays.stream(elements).anyMatch(s -> Pattern.compile(regEx,Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE ).matcher(s).matches());
    }

    public Instruction createInstruction() throws IOException {
        List<String> urls = extractUrls();
        String words = extractWords();
        boolean c = isContainCommand('c');
        boolean v = isContainCommand('v');
        boolean w = isContainCommand('w');
        boolean e = isContainCommand('e');
        return new Instruction(urls, words, c, w, e, v);
    }

    private String extractWords() {
        String regEx = "[\\w,]+";
        return Arrays.stream(elements).filter(w -> Pattern.compile(regEx,Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE ).matcher(w).matches()).collect(joining(","));

    }


}




