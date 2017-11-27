package service;

import pojo.Instruction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InstructionCreator {
    private String[] elements;

    public InstructionCreator(String[] elements) {
        this.elements = elements;
    }

    private static List<String> readUrlsFromFile(String fileName) {
        String line = null;
        ArrayList<String> listOfStrings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "cp1251"))) {

            while ((line = reader.readLine()) != null) {
                listOfStrings.add(line);

            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return listOfStrings;
    }

    private List<String> extractUrls(String elements) {
        List<String> listOfUrls = new ArrayList<>();
        if (isUrl(elements)) {
            listOfUrls.add(elements);
            return listOfUrls;
        }
        listOfUrls = readUrlsFromFile(elements);
        return listOfUrls;

    }

    private boolean isUrl(String string) {
        String regEx = "^http(s)?://[a-z0-9-]+(.[a-z0-9-]+)*(:[0-9]+)?(/.*)?$";
        return (string.matches(regEx));
    }

    private boolean isContainCommand(char c) {
        String regEx = "^[-–]" + c + "$";
        for (String element : elements) {
            if (element.matches(regEx)) return true;
        }
        return false;
    }

    public Instruction createInstruction() {
        List<String> urls = extractUrls(elements[0]);
        String words = extractWords();
        boolean c = isContainCommand('c');
        boolean v = isContainCommand('v');
        boolean w = isContainCommand('w');
        boolean e = isContainCommand('e');
        return new Instruction(urls, words, c, w, e, v);
    }

    private String extractWords() {
        String regEx = "[\\w,]+";
        for (String element : elements) {
            if (element.matches(regEx)) return element;
        }
        return null;

    }



}




