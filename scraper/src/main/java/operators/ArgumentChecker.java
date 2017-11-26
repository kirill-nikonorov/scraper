package operators;

public class ArgumentChecker {
    private ArgumentChecker() {
    }

    public static boolean isTxt(String string) {
        String regEx = ".*\\.txt$";
        return (string.matches(regEx));
    }

    public static boolean isUrl(String string) {
        String regEx = "^http(s)?://[a-z0-9-]+(.[a-z0-9-]+)*(:[0-9]+)?(/.*)?$";
        return (string.matches(regEx));
    }

    public static boolean isComands(String string) {
        String regEx = "^[-–][vwce]$";
        return  (string.matches(regEx));
    }
}
