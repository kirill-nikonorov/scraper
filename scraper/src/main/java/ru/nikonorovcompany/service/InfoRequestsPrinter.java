package ru.nikonorovcompany.service;

import ru.nikonorovcompany.pojo.InfoRequest;
import ru.nikonorovcompany.pojo.TotalInfoRequest;

import java.util.List;

public class InfoRequestsPrinter {
    private List<InfoRequest> listOfRequests;
    private TotalInfoRequest totalDataInfoRequest;

    public InfoRequestsPrinter(List<InfoRequest> listOfRequests) {
        this.listOfRequests = listOfRequests;
        totalDataInfoRequest = new TotalInfoRequest();
    }

    public void print() {
        for (InfoRequest request : listOfRequests) {
            System.out.println(request);
            addToTotalData(request);
        }
        System.out.println(totalDataInfoRequest);
    }

    private void addToTotalData(InfoRequest r) {

        totalDataInfoRequest.setWords(r.getWords());
        totalDataInfoRequest.setCountOfChars(combine(totalDataInfoRequest.getCountOfChars(), r.getCountOfChars()));
        totalDataInfoRequest.setCountsOfWords(combine(totalDataInfoRequest.getCountsOfWords(), r.getCountsOfWords()));
        combine(totalDataInfoRequest.getListOfSentence(), r.getListOfSentence());
        totalDataInfoRequest.setScrapTime(combine(totalDataInfoRequest.getScrapTime(), r.getScrapTime()));
        totalDataInfoRequest.setProcessTime(combine(totalDataInfoRequest.getProcessTime(), r.getProcessTime()));

    }

    private void combine(List<String> totalValue, List<String> requestValue) {
        if (requestValue == null) return;
        if (totalValue == null) {
            totalDataInfoRequest.setListOfSentence(requestValue);
            return;
        }
        totalDataInfoRequest.getListOfSentence().addAll(requestValue);
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

