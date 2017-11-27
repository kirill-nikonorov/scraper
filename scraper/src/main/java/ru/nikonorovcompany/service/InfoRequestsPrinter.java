package ru.nikonorovcompany.service;

import ru.nikonorovcompany.pojo.InfoRequest;

import java.util.List;

public class InfoRequestsPrinter {
    private List<InfoRequest> listOfRequests;
    private Integer totalCountOfChars;
    private Integer totalCountOfWords;
    private Long totalScrapingTime;
    private Long totalProcessingTime;
    private List<String> listOfAllSentence;

    public InfoRequestsPrinter(List<InfoRequest> listOfRequests) {
        this.listOfRequests = listOfRequests;
    }

    private void printTotalData() {

        String result = "---------------Total---------------";
        result += totalCountOfChars != null ? "\ntotal count of characters on web page= " + totalCountOfChars : "";
        result += totalCountOfWords != null ? "\ntotal count of provided word(s) occurrence on web page(s) = " + totalCountOfWords : "";
        result += totalScrapingTime != null ? "\ntotal time spend on data scraping = " + totalScrapingTime /1000 + " mcSec " +
                "\ntotal time spend on data processing  = " + totalProcessingTime /1000 + " mcSec " : "";
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


        totalCountOfChars = combine(totalCountOfChars, r.getCountOfChars());
        totalCountOfWords = combine(totalCountOfWords, r.getCountsOfWords());
        combine(listOfAllSentence, r.getListOfSentence());
        totalScrapingTime = combine(totalScrapingTime, r.getScrapTime());
        totalProcessingTime = combine(totalProcessingTime, r.getProcessTime());

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

