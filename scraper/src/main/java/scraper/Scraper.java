package scraper;

import pojo.InfoRequest;
import service.InfoRequestCreator;
import service.InfoRequestExecutor;

import java.util.List;

public class Scraper {
    public static void main(String[] args) {

         String[] attempt2 = "C:\\Users\\�������\\Desktop\\For_Project\\tryings\\qqq.txt Microsoft,Apple -v �w �c �e".split(" ");

        InfoRequestCreator creator = new InfoRequestCreator(attempt2);

        List<InfoRequest> list = creator.createRequests();

        InfoRequestExecutor.execute(list);


    }

}
