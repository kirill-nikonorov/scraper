package ru.nikonorovcompany.service;

import ru.nikonorovcompany.pojo.InfoRequest;

import java.util.List;

public class InfoRequestsPrinter {
    private List<InfoRequest> listOfRequests;
    private String listOfAllWords;
    private Integer totalCountOfChars;
    private Integer totalCountsOfWords;
    private Long totalScrapTime;
    private Long totalProcessTime;
    private List<String> listOfAllSentence;

    public InfoRequestsPrinter(List<InfoRequest> listOfRequests) {
        this.listOfRequests = listOfRequests;
    }

    private void printTotalData() {

        String result = "---------------Total---------------";
        result += totalCountOfChars != null ? "\ntotal count of characters on web page= " + totalCountOfChars : "";
        result += totalCountsOfWords != null ? "\ntotal count of provided word(s) occurrence on web page(s) = " + totalCountsOfWords : "";
        result += totalScrapTime != null ? "\ntotal time spend on data scraping = " + totalScrapTime + " mcSec " +
                "\ntotal time spend on data processing  = " + totalProcessTime + " mcSec " : "";
        result += listOfAllSentence != null ? "\ntotal list of Sentence with words : \n" + listOfAllSentence : "";

        System.out.println(result);

    }

    public void print() {
        for (InfoRequest request : listOfRequests) {
            System.out.println(request);
            addToTotalData(request);
        }
        printTotalData();

    }

    private void addToTotalData(InfoRequest r) {

        listOfAllWords = r.getWords();
        totalCountOfChars = combine(totalCountOfChars, r.getCountOfChars());
        totalCountsOfWords = combine(totalCountsOfWords, r.getCountsOfWords());
        combine(listOfAllSentence, r.getListOfSentence());
        totalScrapTime = combine(totalScrapTime, r.getScrapTime());
        totalProcessTime = combine(totalProcessTime, r.getProcessTime());

    }

    private void combine(List<String> totalValue, List<String> requestValue) {
        if (requestValue == null) return;
        if (totalValue == null) {
            listOfAllSentence = requestValue;
        } else
            totalValue.addAll(requestValue);
    }

    private Integer combine(Integer totalValue, Integer requestValue) {
        if (requestValue == null) return null;
        if (totalValue == null) {
            return requestValue;
        }
        return requestValue + totalValue;

    }

    private Long combine(Long totalValue, Long requestValue) {
        if (requestValue == null) return null;
        if (totalValue == null) {
            return requestValue;
        }
        return requestValue + totalValue;

    }

}

