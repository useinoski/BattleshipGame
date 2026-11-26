package battleship;


/**
 * This class is the game field class that will hold the grid and the placements of the ships on it
 */
public class Battlefield {

    /**
     * The grid array which is 10*10 will be filled in game
     */
    private final char[][] grid = new char[10][10];
    CellState state;

    public Battlefield() {
        fillFogGrid();
    }

    /**
     * This fills the grid Array with the fog of war(empty grid)
     */
    private void fillFogGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char fog = CellState.EMPTY.getState();
                grid[i][j] = fog;
            }
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
        int row1 = start.getRow();
        int col1 = start.getColumn();
        int row2 = end.getRow();
        int col2 = end.getColumn();
        switch(horizontalOrVertical(start, end)){
            case "horizontal" -> {
                if (col1 > col2) {
                    for (int i = col1; i >= col2; i--) {
                        grid[row1][i] = CellState.SHIP.getState();
                    }
                }
                if (col1 < col2) {
                    for (int i = col1; i <= col2; i++) {
                        grid[row1][i] = CellState.SHIP.getState();
                    }
                }
            }
            case "vertical" ->
            {
                if (row1 < row2){
                    for (int i = row1;i <= row2 ; i++ ){
                        grid[i][col1] = CellState.SHIP.getState();

                    }

                }
                if (row1 > row2) {
                    for (int i = row1;i >= row2 ; i-- ){
                        grid[i][col1] = CellState.SHIP.getState();

                    }
                }
            }
        }


    }

    private static String horizontalOrVertical(Coordinate start,Coordinate end){
        int row1 = start.getRow();
        int col1 = start.getColumn();
        int row2 = end.getRow();
        int col2 = end.getColumn();
        if (row1 == row2){
            return "horizontal";
        } else return "vertical";
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

        int row = cord.getRow();
        int col = cord.getColumn();
        if (grid[row][col] == CellState.SHIP.getState()){
            grid[row][col] = CellState.HIT.getState();
            return "hit";
        }
        if (grid[row][col] == CellState.EMPTY.getState()){
            grid[row][col] = CellState.MISS.getState();
            return "miss";
        }
        return "";
    }


}
