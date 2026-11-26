package battleship;
import java.util.ArrayList;
import java.util.List;

public class PlacedShip {
    private List<Coordinate> parts = new ArrayList<>();

    public List<Coordinate> getParts() {
        return parts;
    }


    public PlacedShip (Coordinate start, Coordinate end){
        int row1 = start.getRow();
        int col1 = start.getColumn();
        int row2 = end.getRow();
        int col2 = end.getColumn();
        int largestRow = Math.max(row1, row2);
        int smallestRow = Math.min(row1,row2);
        int largestCol = Math.max(col1,col2);
        int smallestCol = Math.min(col1,col2);
        for (int i = smallestRow; i <= largestRow; i++){
            for (int j = smallestCol; j <= largestCol; j++){
                Coordinate cord = new Coordinate(i,j);
                parts.add(cord);
            }
        }
    }
}
