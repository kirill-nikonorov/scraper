package ru.nikonorovcompany.pojo;

import java.util.List;

public class Instruction {
    private boolean c;
    private boolean v;
    private boolean w;
    private boolean e;
    private List<String> urls;
    private String words;

    public boolean isC() {
        return c;
    }

    public boolean isV() {
        return v;
    }


    public boolean isW() {
        return w;
    }

    public boolean isE() {
        return e;
    }

    public List<String> getUrls() {
        return urls;
    }

    public String getWords() {
        return words;
    }

    public Instruction(List<String> urls, String words, boolean c, boolean w, boolean e, boolean v) {

        this.urls = urls;
        this.words = words;
        this.c = c;
        this.v = v;
        this.w = w;
        this.e = e;

    }

    @Override
    public String toString() {
        return "Instruction{" +
                "urls=" + urls +
                ", words='" + words + '\'' +
                ", c=" + c +
                ", v=" + v +
                ", w=" + w +
                ", e=" + e +
                '}';
    }
}
