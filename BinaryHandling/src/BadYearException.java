public class BadYearException extends Exception {
    public BadYearException() {
        super("The price is invalid");
    }
}
