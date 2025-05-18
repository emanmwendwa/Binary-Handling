public class BadIsbn10Exception extends Exception {
    public BadIsbn10Exception() {
        super("The 10 digit isbn is invalid");
    }
}
