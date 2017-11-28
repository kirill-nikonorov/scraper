package ru.nikonorovcompany.pojo;

import java.util.List;

public class InfoRequest {

    private String url;
    private String words;
    private Integer countOfChars;
    private Integer countsOfWords;
    private Long scrapTime;
    private Long processTime;
    private List<String> listOfSentence;


    public InfoRequest(String url, String words, Integer countOfChars, Integer countsOfWords, Long scrapTime, Long processTime, List<String> listOfSentence) {
        this.url = url;
        this.words = words;
        this.countOfChars = countOfChars;
        this.countsOfWords = countsOfWords;
        this.scrapTime = scrapTime;
        this.processTime = processTime;
        this.listOfSentence = listOfSentence;
    }

    public Integer getCountOfChars() {
        return countOfChars;
    }

    public Integer getCountsOfWords() {
        return countsOfWords;
    }

    public Long getScrapTime() {
        return scrapTime;
    }

    public Long getProcessTime() {
        return processTime;
    }

    public List<String> getListOfSentence() {
        return listOfSentence;
    }

    @Override
    public String toString() {

        String result = "-----------------------------------";
        result += "\nFor Url =" + url;
        result += words.length()>0? "\nword(s) is(are) = " +words : "\nthere is no any word in args";
        result += countOfChars != null ? "\ncount of characters on web page= " + countOfChars : "";
        result += countsOfWords != null ? "\ncount of provided words occurrence on web page(s) = " + countsOfWords : "";
        result += scrapTime != null ? "\ntime spend on data scraping = " + scrapTime / 1000 + " mcSec " +
                "\ntime spend on data processing  = " + processTime / 1000 + " mcSec " : "";
        result += listOfSentence != null ? "\nlist of Sentence with words : \n" + listOfSentence : "";

        return result;
    }

}

