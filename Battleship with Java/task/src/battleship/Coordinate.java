package battleship;

public class Coordinate {

    private static final int SIZE = 10;

    private final int row;
    private final int column;

    public Coordinate(String coordinates) {
        if (coordinates == null || coordinates.length() < 2) {
            throw new InvalidCoordinateException("Error!");
        }
        int r = findRow(getChar(coordinates));
        int c;
        try {
            c = findColumn(getInt(coordinates));
        } catch (NumberFormatException e) {
            throw new InvalidCoordinateException("Error!");
        }
        validate(r, c);
        this.row = r;
        this.column = c;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    private char getChar(String cords){
        return cords.charAt(0);
    }

    private int getInt(String cords){
        return Integer.parseInt(cords.substring(1));
    }


    private int findRow(char c){
        return Character.toUpperCase(c) - 'A';
    }

    private int findColumn(int n){
        return n-1;
    }

    @Override
    public String toString() {
        char r = (char) ((char) 'A' + (char) row);
        return ""+ r + (column +1 );
    }

    private static void validate(int row, int column) {
        if (row < 0 || row >= SIZE || column < 0 || column >= SIZE) {
            throw new InvalidCoordinateException("Error!");
        }
    }


}


