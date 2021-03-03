import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

    public static String getResponseFromURLAsString(String urlString, String method) {
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
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return inline.toString();
    }

    public static String getReleaseYearFromSting(String response){
        String releaseYearRegex = "(?<=\"release_year\\\\\":)([\\s\\S]+?)(?=,)";
        return getFirstMatchByRegex(response,releaseYearRegex);
    }

    public static String getRestrictionAgeFromSting(String response){
        String restrictionAgeRegex = "(?<=\"restriction_age\\\\\":)([\\s\\S]+?)(?=,)";
        return getFirstMatchByRegex(response,restrictionAgeRegex);
    }

    private static String getFirstMatchByRegex(String text, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
            return matcher.group();
        return "";
    }
}
