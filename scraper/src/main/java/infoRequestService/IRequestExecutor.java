package infoRequestService;

import IRequest.IRequest;

import java.util.ArrayList;

public class IRequestExecutor {

    public static void execute(ArrayList<IRequest> list) {
        executeCommands(list);
        printResultsOfIReuests(list);
        getAndPrintTotalResults(list);

    }

    public static void getAndPrintTotalResults(ArrayList<IRequest> listOfIRequest) {
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

    public static Integer getCTotalResult(ArrayList<IRequest> listOfIRequest) {
        Integer cTotalResult = null;

        for (int i = 0; i < listOfIRequest.size(); i++) {

            if (cTotalResult == null) {
                if (listOfIRequest.get(i).isC()) {
                    cTotalResult = 0;
                    cTotalResult += listOfIRequest.get(i).getcResult();
                } else break;
            } else {
                cTotalResult += listOfIRequest.get(i).getcResult();
            }


        }
        return cTotalResult;
    }
    public static Integer getWTotalResult(ArrayList<IRequest> listOfIRequest) {
        Integer wTotalResult = null;

        for (int i = 0; i < listOfIRequest.size(); i++) {

            if (wTotalResult == null) {
                if (listOfIRequest.get(i).isW()) {
                    wTotalResult = 0;
                    wTotalResult += listOfIRequest.get(i).getWResult();
                } else break;
            } else {
                wTotalResult += listOfIRequest.get(i).getWResult();
            }


        }
        return wTotalResult;
    }
    public static Long[] getVTotalResults(ArrayList<IRequest> listOfIRequest) {
        Long vTotalScrapResult = null;
        Long vTotalProcessResult = null;

        for (int i = 0; i < listOfIRequest.size(); i++) {

            if (vTotalScrapResult == null) {
                if (listOfIRequest.get(i).isV()) {
                    vTotalScrapResult = (long) 0;
                    vTotalProcessResult = (long) 0;
                    vTotalScrapResult += listOfIRequest.get(i).getVScrapResult();
                    vTotalProcessResult += listOfIRequest.get(i).getVProcessResult();
                }
                else break;
            } else {
                vTotalScrapResult += listOfIRequest.get(i).getVScrapResult();
                vTotalProcessResult += listOfIRequest.get(i).getVProcessResult();
            }


        }
        return new Long[]{vTotalScrapResult , vTotalProcessResult};
    }

    public static void executeCommands(ArrayList<IRequest> listOfIRequest) {
        for (int i = 0; i < listOfIRequest.size(); i++) {
            listOfIRequest.get(i).runCommands();
        }
    }
    public static void printResultsOfIReuests(ArrayList<IRequest> listOfIRequest) {
        for (int i = 0; i < listOfIRequest.size(); i++) {
            System.out.println(listOfIRequest.get(i));
        }
    }



    /*

    //data for total output

        Integer cResult = null;
        Integer wResult = null;
        Long vScrapResult = null;
        Long vProcessResult = null;


        for (int i = 0; i < list.size(); i++) {

            list.get(i).runCommands();

            if (cResult == null) {
                if (list.get(i).isC()) {
                    cResult = 0;
                    cResult += list.get(i).getcResult();
                }
            } else {
                cResult += list.get(i).getcResult();
            }

            if (wResult == null) {
                if (list.get(i).isW()) {
                    wResult = 0;
                    wResult += list.get(i).getWResult();
                }
            } else {
                wResult += list.get(i).getWResult();
            }
            if (vScrapResult == null) {
                if (list.get(i).isV()) {
                    vScrapResult = (long) 0;
                    vProcessResult = (long) 0;
                    vScrapResult += list.get(i).getVScrapResult();
                    vProcessResult += list.get(i).getVProcessResult();
                }
            } else {
                vScrapResult += list.get(i).getVScrapResult();
                vProcessResult += list.get(i).getVProcessResult();
            }

            System.out.println(list.get(i));
        }


        String result = "\n----------------Total--------------";
        result += cResult != null ? "\nTotal number of characters on web page(s)= " + cResult : "";
        result += wResult != null ? "\nTotal number of provided word(s) occurrence on web page(s) = " + wResult : "";
        result += vScrapResult != null ? "\nTotal time spend on data scraping = " + vScrapResult + " mcSec " +
                "\nTotal time spend on data processing  = " + vProcessResult + " mcSec ." : "";

        System.out.println(result);


       /* String result = "Total number of characters on web page(s)= " + cResult +
                "\nTotal number of provided word(s) occurrence on web page(s) = " + wResult +
                "\nTotal time spend on data scraping = " + vScrapResult + " mcSec ," +
                "\nTotal time spend on data processing  = " + vProcessResult + " mcSec .";*/


}
