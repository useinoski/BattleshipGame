package battleship;

public class InvalidCoordinateException extends RuntimeException {
    public InvalidCoordinateException(String message) {
        super(message);
    }

    public InvalidCoordinateException(){
        super("Error!");
    }
}
