package staticOperators;

public class ArgumentChecker {

    public static boolean isTxt(String string) {
        String regEx = ".*\\.txt$";
        if (string.matches(regEx)) return true;
        return false;

    }

    public static boolean isUrl(String string) {
        String regEx = "^http(s)?:\\/\\/[a-z0-9-]+(.[a-z0-9-]+)*(:[0-9]+)?(\\/.*)?$";
        if (string.matches(regEx)) return true;
        return false;
    }

    public static boolean isComands(String string) {
        String regEx = "^[-–][vwce]$";
        if (string.matches(regEx)) return true;
        return false;
    }
}
