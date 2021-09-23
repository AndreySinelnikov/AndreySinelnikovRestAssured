package parameters;

public class SpellerOptions {
    private int options;

    public SpellerOptions() {
        options = 0;
    }

    public SpellerOptions setIgnoreDigits() {
        options += 2;
        return this;
    }

    public SpellerOptions setIgnoreUrls() {
        options += 4;
        return this;
    }

    public SpellerOptions setFindRepeatWords() {
        options += 8;
        return this;
    }

    public SpellerOptions setIgnoreCapitalization() {
        options += 512;
        return this;
    }

    public int getOptionsCode() {
        return options;
    }
}
