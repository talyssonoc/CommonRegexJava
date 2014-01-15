package commonregex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author talyssonoc
 */
public class CommonRegex {

    private CharSequence text;
    //Fields
    private String[] dates;
    private String[] times;
    private String[] phones;
    private String[] links;
    private String[] emails;
    private String[] IPv4;
    private String[] IPv6;
    private String[] hexColors;
    private String[] acronyms;
    private String[] money;
    private String[] percentages;
    
    //Patterns
    private static Pattern datePattern,
            timePattern,
            phonePattern,
            linkPattern,
            emailPattern,
            IPv4Pattern,
            IPv6Pattern,
            hexColorPattern,
            acronymPattern,
            moneyPattern,
            percentagePattern ;

    static {
        String monthRegex = "(?:jan\\.?|january|feb\\.?|february|mar\\.?|march|apr\\.?|april|may|jun\\.?|june|jul\\.?|july|aug\\.?|august|sep\\.?|september|oct\\.?|october|nov\\.?|november|dec\\.?|december)";
//        String dayRegex = "(?<!\\:)(?<!\\:\\d)[0-3]?\\d(?:st|nd|rd|th)?";
        String dayRegex = "[0-3]?\\d(?:st|nd|rd|th)?";
        String yearRegex = "\\d{4}";

        String[] anyRegexes = new String[]{dayRegex + "\\s+(?:of\\s+)?" + monthRegex,
            monthRegex + "\\s+" + dayRegex};

        datePattern = Pattern.compile(group(any(anyRegexes)) + "(?:\\\\,)?\\s*" + opt(yearRegex) + "|[0-3]?\\d[-/][0-3]?\\d[-/]\\d{2,4}",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

        timePattern = Pattern.compile("((0?[0-9]|1[0-2]):[0-5][0-9](am|pm)|([01]?[0-9]|2[0-3]):[0-5][0-9])", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        phonePattern = Pattern.compile("(\\d?[^\\s\\w]*(?:\\(?\\d{3}\\)?\\W*)?\\d{3}\\W*\\d{4})", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        linkPattern = Pattern.compile("((?:https?:\\/\\/|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}\\/)(?:[^\\s()<>]+|\\((?:[^\\s()<>]+|(?:\\([^\\s()<>]+\\)))*\\))+(?:\\((?:[^\\s()<>]+|(?:\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:\\'\".,<>?\\xab\\xbb\\u201c\\u201d\\u2018\\u2019]))",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

        emailPattern = Pattern.compile("([a-z0-9!#$%&'*+\\/=?^_`{|}~-]+@([a-z0-9]+\\.)+([a-z0-9]+))", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        IPv4Pattern = Pattern.compile("\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b", Pattern.MULTILINE);
        IPv6Pattern = Pattern.compile("((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))\\b", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        hexColorPattern = Pattern.compile("#(?:[0-9a-fA-F]{3}){1,2}\\b", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        acronymPattern = Pattern.compile("\\b(([A-Z]\\.)+|([A-Z]){2,})", Pattern.MULTILINE);
        moneyPattern = Pattern.compile("((^|\\b)US?)?\\$\\s?[0-9]{1,3}((,[0-9]{3})+|([0-9]{3})+)?(\\.[0-9]{1,2})?\\b", Pattern.MULTILINE);
        percentagePattern = Pattern.compile("(100(\\.0+)?|[0-9]{1,2}(\\.[0-9]+)?)%", Pattern.MULTILINE);
    }

    public CommonRegex(CharSequence text) {
        this.text = text;
    }

    public CommonRegex() {
        this.text = "";
    }

    private static String opt(String regex) {
        return "(?:" + regex + ")?";
    }

    private static String group(String regex) {
        return "(?:" + regex + ")";
    }

    private static String any(String[] regexes) {
        StringBuilder any = new StringBuilder();
        for (String string : regexes) {
            any.append(string);
            any.append("|");
        }
        return any.length() > 0 ? any.substring(0, any.length() - 1) : "";
    }

    private static String[] getMatches(Pattern pattern, CharSequence text) {
        Matcher matcher = pattern.matcher(text);
        ArrayList<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group());
        }

        String[] matchesArray = new String[matches.size()];

        for (int i = 0; i < matchesArray.length; i++) {
            matchesArray[i] = matches.get(i);
        }

        return matchesArray;
    }

    public static String[] getDates(CharSequence text) {
        return getMatches(datePattern, text);
    }

    public String[] getDates() {
        System.out.println(datePattern.toString());

        if (dates == null) {
            dates = getDates(text);
        }

        return dates;
    }

    public static String[] getTimes(CharSequence text) {
        return getMatches(timePattern, text);
    }

    public String[] getTimes() {
        if (times == null) {
            times = getTimes(text);
        }

        return times;
    }

    public static String[] getPhones(CharSequence text) {
        return getMatches(phonePattern, text);
    }

    public String[] getPhones() {
        if (phones == null) {
            phones = getPhones(text);
        }

        return phones;
    }

    public static String[] getLinks(CharSequence text) {
        return getMatches(linkPattern, text);
    }

    public String[] getLinks() {
        if (links == null) {
            links = getLinks(text);
        }

        return links;
    }

    public static String[] getEmails(CharSequence text) {
        return getMatches(emailPattern, text);
    }

    public String[] getEmails() {
        if (emails == null) {
            emails = getEmails(text);
        }

        return emails;
    }

    public static String[] getIPv4(CharSequence text) {
        return getMatches(IPv4Pattern, text);
    }

    public String[] getIPv4() {
        if (IPv4 == null) {
            IPv4 = getIPv4(text);
        }

        return IPv4;
    }
    
    public static String[] getIPv6(CharSequence text) {
        return getMatches(IPv6Pattern, text);
    }

    public String[] getIPv6() {
        if (IPv6 == null) {
            IPv6 = getIPv6(text);
        }

        return IPv6;
    }

    public static String[] getHexColors(CharSequence text) {
        return getMatches(hexColorPattern, text);
    }

    public String[] getHexColors() {
        if (hexColors == null) {
            hexColors = getHexColors(text);
        }

        return hexColors;
    }

    public static String[] getAcronyms(CharSequence text) {
        return getMatches(acronymPattern, text);
    }

    public String[] getAcronyms() {
        if (acronyms == null) {
            acronyms = getAcronyms(text);
        }

        return acronyms;
    }

    public static String[] getMoney(CharSequence text) {
        return getMatches(moneyPattern, text);
    }

    public String[] getMoney() {
        if (money == null) {
            money = getMoney(text);
        }

        return money;
    }
    
    public static String[] getPercentages(CharSequence text) {
        return getMatches(percentagePattern, text);
    }

    public String[] getPercentages() {
        if (percentages == null) {
            percentages = getPercentages(text);
        }

        return percentages;
    }
}
