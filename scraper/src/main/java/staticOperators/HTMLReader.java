package staticOperators;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HTMLReader {

    public static String getContentOfHTTPPage(String pageAddress) {
        String codePage = "UTF-8";
        StringBuilder sb = new StringBuilder();
        try {

            URL pageURL = new URL(pageAddress);


            URLConnection uc = pageURL.openConnection();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            uc.getInputStream(), codePage));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }
}
