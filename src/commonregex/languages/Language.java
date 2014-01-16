package commonregex.languages;

import static commonregex.CommonRegex.any;
import static commonregex.CommonRegex.group;
import static commonregex.CommonRegex.opt;
import java.util.regex.Pattern;

public enum Language {
    en_US {
                @Override
                public Pattern getDatePattern() {
                    String monthRegex = "(?:jan\\.?|january|feb\\.?|february|mar\\.?|march|apr\\.?|april|may|jun\\.?|june|jul\\.?|july|aug\\.?|august|sep\\.?|september|oct\\.?|october|nov\\.?|november|dec\\.?|december)";
                    String dayRegex = "[0-3]?\\d(?:st|nd|rd|th)?";
                    String yearRegex = "\\d{4}";

                    String[] anyRegexes = new String[]{dayRegex + "\\s+(?:of\\s+)?" + monthRegex,
                        monthRegex + "\\s+" + dayRegex};

                    return Pattern.compile(group(any(anyRegexes)) + "(?:\\\\,)?\\s*" + opt(yearRegex) + "|[0-3]?\\d[-/][0-3]?\\d[-/]\\d{2,4}",
                            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
                }

                @Override
                public Pattern getTimePattern() {
                    return Pattern.compile("((0?[0-9]|1[0-2]):[0-5][0-9](am|pm)|([01]?[0-9]|2[0-3]):[0-5][0-9])", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
                }

                @Override
                public Pattern getPhonePattern() {
                    return Pattern.compile("(\\d?[^\\s\\w]*(?:\\(?\\d{3}\\)?\\W*)?\\d{3}\\W*\\d{4})", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
                }

                @Override
                public Pattern getMoneyPattern() {
                    return Pattern.compile("((^|\\b)US?)?\\$\\s?[0-9]{1,3}((,[0-9]{3})+|([0-9]{3})+)?(\\.[0-9]{1,2})?\\b", Pattern.MULTILINE);
                }

                @Override
                public Pattern getPercentagePattern() {
                    return Pattern.compile("(100(\\.0+)?|[0-9]{1,2}(\\.[0-9]+)?)%", Pattern.MULTILINE);
                }
            };

    public abstract Pattern getDatePattern();

    public abstract Pattern getTimePattern();

    public abstract Pattern getPhonePattern();

    public abstract Pattern getMoneyPattern();

    public abstract Pattern getPercentagePattern();
}
