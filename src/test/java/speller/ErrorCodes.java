package speller;

public class ErrorCodes {
    public static final int ERROR_UNKNOWN_WORD = 1;     //  Слова нет в словаре.
    public static final int ERROR_REPEAT_WORD = 2;      //  Повтор слова.
    public static final int ERROR_CAPITALIZATION = 3;   //  Неверное употребление прописных и строчных букв.
    public static final int ERROR_TOO_MANY_ERRORS = 4;  //  Текст содержит слишком много ошибок.
}
