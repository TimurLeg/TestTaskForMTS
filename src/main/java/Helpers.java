import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Methods that help perform the tests.
 */
public class Helpers {


    /**
     * Gets response from target url as raw string.
     *
     * @param urlString target url.
     * @param method    ex: "GET", "POST", "DELETE".
     * @return
     */
    public static String getResponseFromURL(String urlString, String method) {
        StringBuilder inline = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.connect();
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("ResponseCode :" + responseCode);
            } else {
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline.append(sc.nextLine());
                }
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inline.toString();
    }

    /**
     * Trying to find film release year in raw data.
     *
     * @param response with raw data.
     */
    public static String getReleaseYearFromSting(String response) {
        String releaseYearRegex = "(?<=\"release_year\\\\\":)([\\s\\S]+?)(?=,)";
        return getFirstMatchByRegex(response, releaseYearRegex);
    }

    /**
     * Trying to find film restriction age in raw data.
     *
     * @param response with raw data.
     */
    public static String getRestrictionAgeFromSting(String response) {
        String restrictionAgeRegex = "(?<=\"restriction_age\\\\\":)([\\s\\S]+?)(?=,)";
        return getFirstMatchByRegex(response, restrictionAgeRegex);
    }

    private static String getFirstMatchByRegex(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
            return matcher.group();
        return "";
    }
}
