package commonregex;

import commonregex.languages.UnsupportedLanguageException;
import commonregex.languages.Language;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author talyssonoc
 */
public class CommonRegex {

    private final CharSequence text;
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

    //Common Patterns
    private final Language language;
    private static Pattern linkPattern,
            emailPattern,
            IPv4Pattern,
            IPv6Pattern,
            hexColorPattern,
            acronymPattern;

    static {
        linkPattern = Pattern.compile("((?:https?:\\/\\/|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}\\/)(?:[^\\s()<>]+|\\((?:[^\\s()<>]+|(?:\\([^\\s()<>]+\\)))*\\))+(?:\\((?:[^\\s()<>]+|(?:\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:\\'\".,<>?\\xab\\xbb\\u201c\\u201d\\u2018\\u2019]))",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

        emailPattern = Pattern.compile("([a-z0-9!#$%&'*+\\/=?^_`{|}~-]+@([a-z0-9]+\\.)+([a-z0-9]+))", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        IPv4Pattern = Pattern.compile("\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b", Pattern.MULTILINE);
        IPv6Pattern = Pattern.compile("((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))\\b", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        hexColorPattern = Pattern.compile("#(?:[0-9a-fA-F]{3}){1,2}\\b", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        acronymPattern = Pattern.compile("\\b(([A-Z]\\.)+|([A-Z]){2,})", Pattern.MULTILINE);
    }

    public CommonRegex(CharSequence text, Language language) {
        this.text = text;
        this.language = language;
    }

    public CommonRegex(CharSequence text) {
        this.text = text;
        this.language = Language.en_US;
    }

    public CommonRegex() {
        this.text = "";
        this.language = Language.en_US;
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

    public static String[] getDates(String lang, CharSequence text) {
        Language l = Language.fromString(lang);
        if (l != null) {
            return getMatches(l.getLanguageSupport().getDatePattern(), text);
        } else {
            throw new UnsupportedLanguageException();
        }
    }

    public static String[] getDates(Language lang, CharSequence text) {
        return getMatches(lang.getLanguageSupport().getDatePattern(), text);
    }

    public static String[] getDates(CharSequence text) {
        return getDates(Language.en_US, text);
    }

    public String[] getDates() {
        if (dates == null) {
            dates = getDates(language, text);
        }

        return dates;
    }

    public static String[] getTimes(String lang, CharSequence text) {
        Language l = Language.fromString(lang);
        if (l != null) {
            return getMatches(l.getLanguageSupport().getTimePattern(), text);
        } else {
            throw new UnsupportedLanguageException();
        }
    }

    public static String[] getTimes(Language lang, CharSequence text) {
        return getMatches(lang.getLanguageSupport().getTimePattern(), text);
    }

    public static String[] getTimes(CharSequence text) {
        return getTimes(Language.en_US, text);
    }

    public String[] getTimes() {
        if (times == null) {
            times = getTimes(language, text);
        }

        return times;
    }

    public static String[] getPhones(String lang, CharSequence text) {
        Language l = Language.fromString(lang);
        if (l != null) {
            return getMatches(l.getLanguageSupport().getPhonePattern(), text);
        } else {
            throw new UnsupportedLanguageException();
        }
    }

    public static String[] getPhones(Language lang, CharSequence text) {
        return getMatches(lang.getLanguageSupport().getPhonePattern(), text);
    }

    public static String[] getPhones(CharSequence text) {
        return getPhones(Language.en_US, text);
    }

    public String[] getPhones() {
        if (phones == null) {
            phones = getPhones(language, text);
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

    public static String[] getMoney(String lang, CharSequence text) {
        Language l = Language.fromString(lang);
        if (l != null) {
            return getMatches(l.getLanguageSupport().getMoneyPattern(), text);
        } else {
            throw new UnsupportedLanguageException();
        }
    }

    public static String[] getMoney(Language lang, CharSequence text) {
        return getMatches(lang.getLanguageSupport().getMoneyPattern(), text);
    }

    public static String[] getMoney(CharSequence text) {
        return getMoney(Language.en_US, text);
    }

    public String[] getMoney() {
        if (money == null) {
            money = getMoney(language, text);
        }

        return money;
    }

    public static String[] getPercentages(String lang, CharSequence text) {
        Language l = Language.fromString(lang);
        if (l != null) {
            return getMatches(l.getLanguageSupport().getPercentagePattern(), text);
        } else {
            throw new UnsupportedLanguageException();
        }
    }

    public static String[] getPercentages(Language lang, CharSequence text) {
        return getMatches(lang.getLanguageSupport().getPercentagePattern(), text);
    }

    public static String[] getPercentages(CharSequence text) {
        return getPercentages(Language.en_US, text);
    }

    public String[] getPercentages() {
        if (percentages == null) {
            percentages = getPercentages(language, text);
        }

        return percentages;
    }
}
