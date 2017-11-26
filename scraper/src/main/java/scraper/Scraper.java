package scraper;

import pojo.InfoRequest;
import service.InfoRequestCreator;
import service.InfoRequestExecutor;

import java.util.List;

public class Scraper {
    public static void main(String[] args) {

         String[] attempt2 = "C:\\Users\\√агарин\\Desktop\\For_Project\\tryings\\qqq.txt Microsoft,Apple -v Цw Цc Цe".split(" ");

        InfoRequestCreator creator = new InfoRequestCreator(attempt2);

        List<InfoRequest> list = creator.createRequests();

        InfoRequestExecutor.execute(list);


    }

}
