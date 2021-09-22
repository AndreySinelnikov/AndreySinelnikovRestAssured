package service;

public class SpellerOptions {
    private int options;

    public SpellerOptions() {
        options = 0;
    }

    public SpellerOptions setIgnoreDigits() {
        // Пропускать слова с цифрами, например, "авп17х4534".
        options += 2;
        return this;
    }

    public SpellerOptions setIgnoreUrls() {
        // Пропускать интернет-адреса, почтовые адреса и имена файлов.
        options += 4;
        return this;
    }

    public SpellerOptions setFindRepeatWords() {
        // 	Подсвечивать повторы слов, идущие подряд. Например, "я полетел на на Кипр".
        options += 8;
        return this;
    }

    public SpellerOptions setIgnoreCapitalization() {
        // Игнорировать неверное употребление ПРОПИСНЫХ/строчных букв, например, в слове "москва".
        options += 512;
        return this;
    }

    public int getOptionsCode() {
        return options;
    }
}
