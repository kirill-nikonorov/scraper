package ru.nikonorovcompany.pojo;

import java.util.List;

public class InfoRequest {

    protected String url = "";
    protected String words = "";
    protected Integer countOfChars;
    protected Integer countsOfWords;
    protected Long scrapTime;
    protected Long processTime;
    protected List<String> listOfSentence;

    public InfoRequest() {

    }

    public InfoRequest(String url, String words, Integer countOfChars, Integer countsOfWords, Long scrapTime, Long processTime, List<String> listOfSentence) {
        this.url = url;
        this.words = words;
        this.countOfChars = countOfChars;
        this.countsOfWords = countsOfWords;
        this.scrapTime = scrapTime;
        this.processTime = processTime;
        this.listOfSentence = listOfSentence;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Integer getCountOfChars() {
        return countOfChars;
    }

    public void setCountOfChars(Integer countOfChars) {
        this.countOfChars = countOfChars;
    }

    public Integer getCountsOfWords() {
        return countsOfWords;
    }

    public void setCountsOfWords(Integer countsOfWords) {
        this.countsOfWords = countsOfWords;
    }

    public Long getScrapTime() {
        return scrapTime;
    }

    public void setScrapTime(Long scrapTime) {
        this.scrapTime = scrapTime;
    }

    public Long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Long processTime) {
        this.processTime = processTime;
    }

    public List<String> getListOfSentence() {
        return listOfSentence;
    }

    public void setListOfSentence(List<String> listOfSentence) {
        this.listOfSentence = listOfSentence;
    }

    @Override
    public String toString() {


        String result = "-----------------------------------";
        result += "\nFor Url =" + url;
        result += countOfChars!=null ? "\ncount of characters on web page= " + countOfChars : "";
        result += countsOfWords!=null ? "\ncount of provided word(s) occurrence on web page(s) = " + countsOfWords : "";
        result += scrapTime!=null ? "\ntime spend on data scraping = " + scrapTime + " mcSec " +
                "\ntime spend on data processing  = " + processTime + " mcSec " : "";
        result += listOfSentence!= null ? "\nlist of Sentence with words : \n" + listOfSentence : "";


        return result;
    }

}

