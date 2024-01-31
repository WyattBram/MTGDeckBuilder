public class NoCardException extends Exception {
    NoCardException(String card) {
        super("I cannot find " + card + ", please try again.");
    }
}
