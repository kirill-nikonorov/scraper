package ru.nikonorovcompany.service;

import ru.nikonorovcompany.pojo.InfoRequest;
import ru.nikonorovcompany.pojo.Instruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoRequestCreator {

    private Instruction instruction;

    public InfoRequestCreator(Instruction instruction) {
        this.instruction = instruction;
    }

    private static String dataScrapping(String pageAddress) throws IOException {
        String codePage = "UTF-8";
        StringBuilder sb = new StringBuilder();
        URL pageURL = new URL(pageAddress);
        URLConnection uc = pageURL.openConnection();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        uc.getInputStream(), codePage));
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
        return sb.toString();
    }

    public List<InfoRequest> createInfoRequests() throws IOException {
        if (instruction.isV()) {
            return executeOnTime();
        } else {
            return executeNotOnTime();
        }
    }

    private List<InfoRequest> executeNotOnTime() throws IOException {
        List<InfoRequest> listOfRequests = new ArrayList<>();
        String html;
        for (String url : instruction.getUrls()) {

            html = dataScrapping(url);
            String words = instruction.getWords();
            Integer countsOfChars = instruction.isC() ? getCountsOfChars(html) : null;
            Integer countsOfWords = instruction.isV() ? getCountsOfWords(html, instruction.getWords()) : null;
            List<String> countsOfSentence = instruction.isE() ? getSentences(html, instruction.getWords()) : null;
            InfoRequest request = new InfoRequest(url, words, countsOfChars, countsOfWords, null, null, countsOfSentence);

            listOfRequests.add(request);
        }
        return listOfRequests;
    }

    private List<InfoRequest> executeOnTime() throws IOException {
        List<InfoRequest> listOfRequests = new ArrayList<>();
        String html;
        for (String url : instruction.getUrls()) {
            Long startOfScrapping = System.nanoTime();
            html = dataScrapping(url);
            Long endOfScrapping = System.nanoTime();

            Long startOfProcessing = System.nanoTime();
            String words = instruction.getWords();
            Integer countsOfChars = instruction.isC() ? getCountsOfChars(html) : null;
            Integer countsOfWords = instruction.isV() ? getCountsOfWords(html, instruction.getWords()) : null;
            List<String> sentences = instruction.isE() ? getSentences(html, instruction.getWords()) : null;
            Long endOfProcessing = System.nanoTime();

            Long scrapingTime = endOfScrapping - startOfScrapping;
            Long processTime = endOfProcessing - startOfProcessing;

            InfoRequest request = new InfoRequest(url, words, countsOfChars, countsOfWords, scrapingTime, processTime, sentences);
            listOfRequests.add(request);
        }
        return listOfRequests;
    }

    private Integer getCountsOfChars(String html) {
        return html.length();
    }

    private Integer getCountsOfWords(String html, String words) {
        int count = 0;
        String[] arrOfWords = words.split(",");
        for (String word : arrOfWords) {
            Matcher m = Pattern.compile(word, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(html);
            while (m.find()) {
                count++;
            }
        }
        return count;
    }

    private List<String> getSentences(String html, String words) {
        ArrayList<String> listOfSentences = new ArrayList<>();
        String commonSentenceRegEx = "[^\\.!?;][^\\.!?;]*%s[^\\.!?]*[\\.!?;]";

        Arrays.stream(words.split(",")).forEach(word -> {
            final String SentnceRegEx = String.format(commonSentenceRegEx, word);
            Matcher m = Pattern.compile(SentnceRegEx, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.DOTALL).matcher(html);
            while (m.find()) {
                listOfSentences.add("- " + html.substring(m.start(), m.end()) + "\n");
            }
        });
        return listOfSentences;
    }


}
