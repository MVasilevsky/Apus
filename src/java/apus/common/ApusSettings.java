package apus.common;

/**
 * @author Maxim Vasilevsky
 */
public class ApusSettings {

    private String numberPrefix;
    private int numberSize;
    private int tariff;
    private String defaultLanguage;
    private boolean quickSearch;

    public String getNumberPrefix() {
        return numberPrefix;
    }

    public void setNumberPrefix(String numberPrefix) {
        this.numberPrefix = numberPrefix;
    }

    public int getNumberSize() {
        return numberSize;
    }

    public void setNumberSize(int numberSize) {
        this.numberSize = numberSize;
    }

    public int getTariff() {
        return tariff;
    }

    public void setTariff(int tariff) {
        this.tariff = tariff;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public boolean getQuickSearch() {
        return quickSearch;
    }

    public void setQuickSearch(boolean quickSearch) {
        this.quickSearch = quickSearch;
    }
}
