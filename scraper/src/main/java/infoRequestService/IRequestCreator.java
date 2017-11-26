package infoRequestService;

import IRequest.IRequest;
import staticOperators.ArgumentChecker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class IRequestCreator {
    public String[] elements = null;
    public ArrayList<String> listOfUrls = new ArrayList();
    public String words = "";
    public boolean v;
    public boolean c;
    public boolean w;
    public boolean e;

    @Override
    public String toString() {
        return "IRequestCreator{" +
                "elements=" + Arrays.toString(elements) +
                ", listOfUrls=" + listOfUrls +
                ", words='" + words + '\'' +
                ", v=" + v +
                ", c=" + c +
                ", w=" + w +
                ", e=" + e +
                '}';
    }

    public IRequestCreator(String[] taskString) {
        this.elements = taskString;
        configureCreator();
    }

    public static String readUrlsFromFile(String fileName) {
        BufferedReader reader = null;
        String line = null;
        String result = "";
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "cp1251"));
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<IRequest> createRequests() {
        ArrayList<IRequest> listOfRequest = new ArrayList<IRequest>();
        for (int i = 0; i < listOfUrls.size(); i++) {
            IRequest req = new IRequest();
            req.setUrl(listOfUrls.get(i));
            req.setWords(words);
            req.setC(c);
            req.setE(e);
            req.setV(v);
            req.setW(w);
            listOfRequest.add(req);
        }
        return listOfRequest;


    }

    public void configureCreator() {

        for (int i = 0; i < elements.length; i++) {
            if(elements[i].length()==0) continue;

            if (ArgumentChecker.isComands(elements[i])) {
                assignComand(elements[i]);
                continue;
            }

            if (ArgumentChecker.isTxt(elements[i])) {

                String urls = readUrlsFromFile(elements[i]);
                assignUrls(urls);

                continue;
            }

            if (ArgumentChecker.isUrl(elements[i])) {
                listOfUrls.add(elements[i]);
                continue;
            }

            words = elements[i];
            continue;
        }

    }

    public void assignComand(String command) {
        char com = command.charAt(1);
        switch (com) {
            case 'c':
                c = true;
                break;
            case 'w':
                w = true;
                break;
            case 'v':
                v = true;
                break;
            case 'e':
                e = true;
                break;
        }

    }

    public void assignUrls(String urls) {
        String[] arrOfUrls = urls.split("\n");
        for (int i = 0; i < arrOfUrls.length; i++) {
            listOfUrls.add(arrOfUrls[i]);
        }
    }

}
