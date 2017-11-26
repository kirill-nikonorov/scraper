package service;

import pojo.InfoRequest;
import operators.ArgumentChecker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfoRequestCreator {
    public String[] getElements() {
        return elements;
    }

    public void setElements(String[] elements) {
        this.elements = elements;
    }

    public List<String> getListOfUrls() {
        return listOfUrls;
    }

    public void setListOfUrls(List<String> listOfUrls) {
        this.listOfUrls = listOfUrls;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public boolean isV() {
        return v;
    }

    public void setV(boolean v) {
        this.v = v;
    }

    public boolean isC() {
        return c;
    }

    public void setC(boolean c) {
        this.c = c;
    }

    public boolean isW() {
        return w;
    }

    public void setW(boolean w) {
        this.w = w;
    }

    public boolean isE() {
        return e;
    }

    public void setE(boolean e) {
        this.e = e;
    }

    private String[] elements = null;
    private List<String> listOfUrls = new ArrayList();
    private String words = "";
    private boolean v;
    private boolean c;
    private boolean w;
    private boolean e;

    @Override
    public String toString() {
        return "InfoRequestCreator{" +
                "elements=" + Arrays.toString(elements) +
                ", listOfUrls=" + listOfUrls +
                ", words='" + words + '\'' +
                ", v=" + v +
                ", c=" + c +
                ", w=" + w +
                ", e=" + e +
                '}';
    }

    public InfoRequestCreator(String[] taskString) {
        this.elements = taskString;
        configureCreator();
    }

    private static String readUrlsFromFile(String fileName) {
        String line = null;
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "cp1251"))) {

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");

            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return builder.toString();
    }

    public List<InfoRequest> createRequests() {
        ArrayList<InfoRequest> listOfRequest = new ArrayList<>();
        for(String url : listOfUrls) {
            InfoRequest req = new InfoRequest();
            req.setUrl(url);
            req.setWords(words);
            req.setC(c);
            req.setE(e);
            req.setV(v);
            req.setW(w);
            listOfRequest.add(req);
        }
        return listOfRequest;


    }

    private void configureCreator() {

        for (String element : elements) {
            if (element.length() == 0) continue;

            if (ArgumentChecker.isComands(element)) {
                assignComand(element);
                continue;
            }

            if (ArgumentChecker.isTxt(element)) {

                String urls = readUrlsFromFile(element);
                assignUrls(urls);

                continue;
            }

            if (ArgumentChecker.isUrl(element)) {
                listOfUrls.add(element);
                continue;
            }

            words = element;
        }

    }

    public void assignComand(String command) {
        char com = command.charAt(1);
        switch (com) {
            case 'c':
                c = true;
                break;
            case 'w':
                w = true;
                break;
            case 'v':
                v = true;
                break;
            case 'e':
                e = true;
                break;
            default:
                break;
        }

    }

    private void assignUrls(String urls) {
        String[] arrOfUrls = urls.split("\n");
        listOfUrls.addAll(Arrays.asList(arrOfUrls));
    }

}
