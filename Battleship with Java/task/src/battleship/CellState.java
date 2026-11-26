package battleship;

public enum CellState {
    EMPTY('~'),SHIP('O'),HIT('x'),MISS('-');
    public final char state;

    CellState(char state) {
        this.state = state;
    }

    public char getState() {
        return state;
    }
}
