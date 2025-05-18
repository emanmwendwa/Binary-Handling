public class BadPriceException extends Exception {
    public BadPriceException() {
        super("The price is invalid");
    }
}
