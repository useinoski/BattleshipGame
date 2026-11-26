package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Battlefield field = new Battlefield();
        field.printGrid();


        for (Ship ship: Ship.values()) {
            boolean placed = false;
            System.out.printf("Enter the coordinates of the %s (%d cells):\n", ship.getName(),ship.getSize());
            while(!placed) {
                try {
                    Coordinate start = new Coordinate(sc.next());
                    Coordinate end = new Coordinate(sc.next());
                    // printParts(start, end);
                    int length = calculateLength(start,end);
                    if (length == ship.getSize() && field.canPlace(start,end)){
                        field.placeShip(start,end);
                        field.printGrid();
                        placed = true;
                    } else if(length != ship.getSize()){
                        System.out.println("Error! Wrong length of the " + ship.getName() + " Try again:");
                    } else if (!field.canPlace(start,end)){
                        System.out.println("Error! Wrong ship location! Try again:");
                    }


                } catch (InvalidCoordinateException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        boolean gameWon = false;
        startGame(field);
        printTakeShot();
        while (!gameWon) {

                try {

                    Coordinate shot = new Coordinate(sc.next());

                    String checkShot = field.shoot(shot);

                    field.printFogGrid();
                    if (checkShot.equals("hit")) {
                        System.out.println("You hit a ship! Try again:");

                    } else if (checkShot.equals("miss")) {

                        System.out.println("You missed. Try again:");

                    }

                } catch (InvalidCoordinateException e) {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                }

        }

    }




    public static void printTakeShot(){
        System.out.println("Take a shot!");
    }

    public static void startGame(Battlefield field){
        System.out.println("The game starts!");
        field.printFogGrid();
    }


    public static boolean isSizeBiggerThanLength(int length,int size){
        return length <= size;
    }

    public static void printParts(Coordinate cord1, Coordinate cord2) {
        int row1 = cord1.getRow();
        int col1 = cord1.getColumn();
        int row2 = cord2.getRow();
        int col2 = cord2.getColumn();
        //System.out.println("Length: " + calculateLength(cord1, cord2));
        char r = (char) ('A' + (char) row1);
        if (row1 == row2) {
            if (col1 > col2) {
                System.out.print("Parts: " + r + (col1 + 1) + " ");
                for (int i = col1; i > col2; i--) {
                    System.out.print("" + r + i + " ");
                }
            }
            if (col1 < col2) {
                for (int i = col1; i < col2; i++) {
                    System.out.print("" + r + (i + 1)+ " ");

                }
                System.out.print(""+ r + (col2+1));
            }
        }
        if (col1 == col2){
            if (row1 < row2){
                for (int i = row1;i < row2 + 1; i++ ){
                    char d = (char) ('A' +  i);
                    System.out.println("" + d + (col1+1));
                }

            }
            if (row1 > row2) {
                for (int i = row1;i > row2 - 1; i-- ){
                    char d = (char) ('A' +  i);
                    System.out.println("" + d + (col1+1));
                }
            }
        }
    }


    public static int calculateLength(Coordinate cord1, Coordinate cord2) {
        int row1 = cord1.getRow();
        int col1 = cord1.getColumn();
        int row2 = cord2.getRow();
        int col2 = cord2.getColumn();

        int length = 0;
        if (row1 == row2) {
            if (col1 > col2) {
                length = col1 - col2 + 1;
                return length;
            }
            if (col2 > col1) {
                length = col2 - col1 + 1;
                return length;
            }
        }
        if (col1 == col2) {
            if (row1 > row2) {
                length = row1 - row2 + 1;
                return length;
            }
            if (row2 > row1) {
                length = row2 - row1 + 1;
                return length;
            }

        } else
            throw new InvalidCoordinateException("Error!");
        return length;
    }



}
