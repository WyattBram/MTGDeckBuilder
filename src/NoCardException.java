public class NoCardException extends Exception {
    NoCardException() {
        super("Can not find card, please enter card name again.");
    }
}
