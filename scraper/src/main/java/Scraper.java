import IRequest.IRequest;
import infoRequestService.IRequestCreator;
import infoRequestService.IRequestExecutor;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraper {
    public static void main(String[] args) {

        IRequestCreator creator = new IRequestCreator(args);

        ArrayList<IRequest> list = creator.createRequests();

        IRequestExecutor.execute(list);


    }

}
