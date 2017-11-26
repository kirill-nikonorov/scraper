package operators;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsRunner {
    private static String removeAllTags(String html) {

        return html.replaceAll("<[^<>]+>", "");
    }

    private CommandsRunner() {
    }

    public static int runCTask(String html) {
        html = removeAllTags(html);
        return html.length();
    }

    public static int runWTask(String html, String words) {
        html = removeAllTags(html);
        int count = 0;
        String[] arrOfWords = words.split(",");
        for (String word : arrOfWords) {

            Matcher m = Pattern.compile(word, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(html);
            while (m.find()) {
                count++;
            }

        }
        return count;
    }

    public static List runETask(String html, String words) {

        html = removeAllTags(html);

        String[] arrOfWords = words.split(",");

        ArrayList<String> listOfSentences = new ArrayList<>();

        String commonRegEx = "[^\\.!?;][^\\.!?;]*%s[^\\.!?]*[\\.!?;]";

        for (String word : arrOfWords) {

            String regEx = String.format(commonRegEx, word);
            Matcher m = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(html);

            while (m.find()) {
                listOfSentences.add("- " + html.substring(m.start(), m.end()) + "\n");
            }

        }
        return listOfSentences;


    }
}