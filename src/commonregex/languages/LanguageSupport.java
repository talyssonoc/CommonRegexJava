/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonregex.languages;

import java.util.regex.Pattern;

/**
 *
 * @author oliver
 */
public abstract class LanguageSupport {

    public abstract Pattern getDatePattern();

    public abstract Pattern getTimePattern();

    public abstract Pattern getPhonePattern();

    public abstract Pattern getMoneyPattern();

    public abstract Pattern getPercentagePattern();

    protected String opt(String regex) {
        return "(?:" + regex + ")?";
    }

    protected String group(String regex) {
        return "(?:" + regex + ")";
    }

    protected String any(String[] regexes) {
        StringBuilder any = new StringBuilder();
        for (String string : regexes) {
            any.append(string);
            any.append("|");
        }
        return any.length() > 0 ? any.substring(0, any.length() - 1) : "";
    }
}
