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
                char fog = '~';
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

    public static void placeShip(int start,int end){


    }
}
