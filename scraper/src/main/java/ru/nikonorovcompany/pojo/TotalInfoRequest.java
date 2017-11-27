package ru.nikonorovcompany.pojo;

public class TotalInfoRequest extends InfoRequest {

    @Override
    public String toString() {


        String result = "---------------Total---------------";
        result += countOfChars!=null ? "\ntotal count of characters on web page= " + countOfChars : "";
        result += countsOfWords!=null ? "\ntotal count of provided word(s) occurrence on web page(s) = " + countsOfWords : "";
        result += scrapTime!=null ? "\ntotal time spend on data scraping = " + scrapTime + " mcSec " +
                "\ntotal time spend on data processing  = " + processTime + " mcSec " : "";
        result += listOfSentence!= null ? "\ntotal list of Sentence with words : \n" + listOfSentence : "";


        return result;
    }
}
