package commonregex.languages;

import java.util.regex.Pattern;

public enum Language {

    en_US("en_US"){
        
        @Override
        public LanguageSupport getLanguageSupport() {
            return EnglishSupport.getInstance();
        }
        
    };
                

    private String name;

    Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Language fromString(String name) {
        if (name != null) {
            for (Language b : Language.values()) {
                if (name.equalsIgnoreCase(b.name)) {
                    return b;
                }
            }
        }
        return null;
    }

   public abstract LanguageSupport getLanguageSupport();
}
