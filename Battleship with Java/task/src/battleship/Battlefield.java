package battleship;


import java.util.ArrayList;
import java.util.List;

/**
 * This class is the game field class that will hold the grid and the placements of the ships on it
 */
public class Battlefield {

    /**
     * The grid array which is 10*10 will be filled in game
     */
    private final char[][] grid = new char[10][10];
    private final char[][] fogGrid = new char[10][10];
    private List<PlacedShip> placedShips = new ArrayList<>();




    public List<PlacedShip> getPlacedShips() {
        return placedShips;
    }

    public Battlefield() {
        fillFogGrid(grid);
        fillFogGrid(fogGrid);
    }

    /**
     * This fills the grid Array with the fog of war(empty grid)
     */
    private void fillFogGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char fog = CellState.EMPTY.getState();
                grid[i][j] = fog;
            }
        }
    }

    public void printFogGrid() {
        char letter ='A';
        System.out.print("  ");
        for (int i = 1; i <= fogGrid.length; i++){
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0; i < fogGrid.length; i++) {
            System.out.print((char)(letter + i) + " ");
            for (int j = 0; j <fogGrid[i].length; j++){
                System.out.print(fogGrid[i][j] + " ");

            }
            System.out.println();
        }
    }

    /**
     * Prints the battlefield
     */

    public void printGrid() {
        char letter ='A';
        System.out.print("  ");
        for (int i = 1; i <= grid.length; i++){
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0; i < grid.length; i++) {
            System.out.print((char)(letter + i) + " ");
            for (int j = 0; j <grid[i].length; j++){
            System.out.print(grid[i][j] + " ");

        }
            System.out.println();
        }
    }

    public void placeShip(Coordinate start,Coordinate end){
        PlacedShip ship = new PlacedShip(start,end);
        for(Coordinate cord: ship.getParts()){
            grid[cord.getRow()][cord.getColumn()] = CellState.SHIP.getState();
        }
        placedShips.add(ship);
    }

    public boolean canPlace(Coordinate start,Coordinate end){
        int row1 = start.getRow();
        int col1 = start.getColumn();
        int row2 = end.getRow();
        int col2 = end.getColumn();
        int largestRow = Math.max(row1, row2);
        int smallestRow = Math.min(row1,row2);
        int largestCol = Math.max(col1,col2);
        int smallestCol = Math.min(col1,col2);
        int startRow =Math.max(0,smallestRow -1);
        int endRow =Math.min(9,largestRow +1);
        int startCol = Math.max(0,smallestCol -1);
        int endCol =Math.min(9,largestCol +1);

        for(int i = startRow ; i <= endRow; i++ ){
            for (int j = startCol ; j <= endCol; j++ ) {
                if (grid[i][j] == ( CellState.SHIP.getState())) {
                    return false;
                }
            }
        }
        return true;
    }

    public String shoot(Coordinate cord) {

        int r = cord.getRow();
        int c = cord.getColumn();
        if (grid[r][c] == CellState.SHIP.getState()){
            grid[r][c] = CellState.HIT.getState();
            fogGrid[r][c] = CellState.HIT.getState();
            PlacedShip ship = findShip(cord);
            if (allSunk()){
                return "won";
            }
            if(isSunk(ship)){
                return "sank";
            }

            return "hit";
        }

        if (grid[r][c] == CellState.HIT.getState()){
            return "hit";
        }
        if (grid[r][c] == CellState.EMPTY.getState()){
            grid[r][c] = CellState.MISS.getState();
            fogGrid[r][c] = CellState.MISS.getState();
        }
        return "miss";
    }

    public boolean isSunk(PlacedShip ship){
        for (Coordinate cord: ship.getParts()){
            if(grid[cord.getRow()][cord.getColumn()] != CellState.HIT.getState()){
                return false;
            }
        }
        return true;
    }

    public PlacedShip findShip(Coordinate shot){
        for (PlacedShip ship:placedShips){
            for (Coordinate cord:ship.getParts()){
                if (cord.getRow() == shot.getRow() && cord.getColumn() == shot.getColumn()) {
                    return ship;
                }
            }
        }
        return null;
    }

    public boolean allSunk(){
        for(PlacedShip ship: placedShips){
            if (!isSunk(ship)){
                return false;
            }
        }
        return true;
    }



}
