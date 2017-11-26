package staticOperators;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsRunner {

    public static int runCTask(String html) {
        html = html.replaceAll("<[^<>]+>", "");
        return html.length();
    }

    public static int runWTask(String html, String words) {
        html = html.replaceAll("<[^<>]+>", "");
        int count = 0;
        String[] arrOfWords = words.split(",");
        for (int i = 0; i < arrOfWords.length; i++) {

            String regEx = arrOfWords[i];

            Matcher m = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(html);
            while (m.find()) {
                count++;
            }

        }
        return count;
    }

    public static ArrayList<String> runETask(String html, String words) {

        //убираем тегги из html
        html = html.replaceAll("<[^<>]+>", "");
        //создаем массив из полученных  для поиска слов
        String[] arrOfWords = words.split(",");

        ArrayList<String> listOfSentences = new ArrayList<String>();
        //регулярное выражение для поиска предложений со словами
        String commonRegEx = "[^\\.!?;][^\\.!?;]*%s[^\\.!?]*[\\.!?;]";

        for (int i = 0; i < arrOfWords.length; i++) {

            String regEx = String.format(commonRegEx, arrOfWords[i]);
            Matcher m = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(html);
            while (m.find()) {
                listOfSentences.add("- " + html.substring(m.start(), m.end()) + "\n");
            }

        }
        return listOfSentences;


    }
}