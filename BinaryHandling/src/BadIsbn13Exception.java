public class BadIsbn13Exception extends Exception {
    public BadIsbn13Exception() {
        super("The 13 digit isbn is invalid");
    }
}
