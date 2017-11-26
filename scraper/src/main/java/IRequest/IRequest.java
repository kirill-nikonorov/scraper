package IRequest;

import staticOperators.CommandsRunner;
import staticOperators.HTMLReader;

import java.util.ArrayList;

public class IRequest {

    private String url = "";
    private String words = "";
    private boolean v;
    private boolean c;
    private boolean w;
    private boolean e;
    private Integer cResult;
    private Integer wResult;
    private Long vScrapResult;
    private Long vProcessResult;
    private ArrayList<String> eResult;

    public IRequest() {
    }

    public void runCommands() {
        if (v) {
            long st, en;
            st = System.nanoTime();
            String html = HTMLReader.getContentOfHTTPPage(url);
            en = System.nanoTime();
            vScrapResult = (en - st) / 1000; //в микросекундах
            st = System.nanoTime();
            if (c) {
                cResult = CommandsRunner.runCTask(html);
            }
            if (w) {
                wResult = CommandsRunner.runWTask(html, words);
            }
            if (e) {
                eResult = CommandsRunner.runETask(html, words);
            }
            en = System.nanoTime();
            vProcessResult = (en - st) / 1000; //в микросекундах


        } else {
            String html = HTMLReader.getContentOfHTTPPage(url);
            if (c) {
                cResult = CommandsRunner.runCTask(html);
            }
            if (w) {
                wResult = CommandsRunner.runWTask(html, words);
            }
            if (e) {
                eResult = CommandsRunner.runETask(html, words);
            }
        }


    }


    public boolean isV() {
        return v;
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

    public void setV(boolean v) {
        this.v = v;
    }

    public void setC(boolean c) {
        this.c = c;
    }

    public void setW(boolean w) {
        this.w = w;
    }

    public void setE(boolean e) {
        this.e = e;
    }

    public Integer getcResult() {
        return cResult;
    }

    public void setCResult(Integer cResult) {
        this.cResult = cResult;
    }

    public Integer getWResult() {
        return wResult;
    }

    public void setWResult(Integer wResult) {
        this.wResult = wResult;
    }

    public Long getVScrapResult() {
        return vScrapResult;
    }

    public void setVScrapResult(Long vScrapResult) {
        this.vScrapResult = vScrapResult;
    }

    public Long getVProcessResult() {
        return vProcessResult;
    }

    public void setVProcessResult(Long vProcessResult) {
        this.vProcessResult = vProcessResult;
    }

    public void setEResult(ArrayList<String> eResult) {
        this.eResult = eResult;
    }

    public boolean isC() {
        return c;
    }



    public boolean isW() {
        return w;
    }


    public boolean isE() {
        return e;
    }


    public ArrayList<String> geteResult() {
        return eResult;
    }


    @Override
    public String toString() {

        String result = "\n-----------------------------------";
        result += "\nFor Url =" + url;
        result += c ? "\nnumber of characters on web page= " + cResult : "";
        result += w ? "\nnumber of provided word(s) occurrence on web page(s) = " + wResult : "";
        result += v ? "\ntime spend on data scraping = " + vScrapResult + " mcSec " +
                "\ntime spend on data processing  = " + vProcessResult + " mcSec " : "";
        result += eResult!=null ? "\nlist of Sentence with words : \n" + eResult : "\nno sentence to print";


        return result;

    }
}

