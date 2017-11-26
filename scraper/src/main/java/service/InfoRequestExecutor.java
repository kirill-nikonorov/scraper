package service;

import pojo.InfoRequest;

import java.util.List;

public class InfoRequestExecutor {


    private InfoRequestExecutor() {
    }

    public static void execute(List<InfoRequest> list) {
        executeCommands(list);
        printResultsOfIReuests(list);
        getAndPrintTotalResults(list);

    }

    private static void getAndPrintTotalResults(List<InfoRequest> listOfIRequest) {
        //data for total output
        Integer cTotalResult = getCTotalResult(listOfIRequest);
        Integer wTotalResult = getWTotalResult(listOfIRequest);
        Long vTotalScrapResult = getVTotalResults(listOfIRequest)[0];
        Long vTotalProcessResult = getVTotalResults(listOfIRequest)[1];


        String result = "\n----------------Total--------------";
        result += cTotalResult != null ? "\nTotal number of characters on web page(s)= " + cTotalResult : "";
        result += wTotalResult != null ? "\nTotal number of provided word(s) occurrence on web page(s) = " + wTotalResult : "";
        result += vTotalScrapResult != null ? "\nTotal time spend on data scraping = " + vTotalScrapResult + " mcSec " +
                "\nTotal time spend on data processing  = " + vTotalProcessResult + " mcSec ." : "";

        System.out.println(result);
    }

    private static Integer getCTotalResult(List<InfoRequest> listOfIRequest) {
        Integer cTotalResult = null;

        for (InfoRequest request : listOfIRequest) {

            if (cTotalResult == null) {
                if (request.isC()) {
                    cTotalResult = 0;
                    cTotalResult += request.getcResult();
                } else break;
            } else {
                cTotalResult += request.getcResult();
            }


        }
        return cTotalResult;
    }

    private static Integer getWTotalResult(List<InfoRequest> listOfIRequest) {
        Integer wTotalResult = null;

        for (InfoRequest request : listOfIRequest) {

            if (wTotalResult == null) {
                if (request.isW()) {
                    wTotalResult = 0;
                    wTotalResult += request.getWResult();
                } else break;
            } else {
                wTotalResult += request.getWResult();
            }


        }
        return wTotalResult;
    }

    private static Long[] getVTotalResults(List<InfoRequest> listOfIRequest) {
        Long vTotalScrapResult = null;
        Long vTotalProcessResult = null;

        for (InfoRequest request : listOfIRequest) {

            if (vTotalScrapResult == null) {
                if (request.isV()) {
                    vTotalScrapResult = (long) 0;
                    vTotalProcessResult = (long) 0;
                    vTotalScrapResult += request.getVScrapResult();
                    vTotalProcessResult += request.getVProcessResult();
                } else break;
            } else {
                vTotalScrapResult += request.getVScrapResult();
                vTotalProcessResult += request.getVProcessResult();
            }


        }
        return new Long[]{vTotalScrapResult, vTotalProcessResult};
    }

    private static void executeCommands(List<InfoRequest> listOfIRequest) {
        for (InfoRequest request: listOfIRequest) {
            request.runCommands();
        }
    }

    private static void printResultsOfIReuests(List<InfoRequest> listOfIRequest) {
        for (InfoRequest request : listOfIRequest) {
            System.out.println(request);
        }
    }


}
