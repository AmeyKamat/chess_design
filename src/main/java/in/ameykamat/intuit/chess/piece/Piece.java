package in.ameykamat.intuit.chess.piece;

import in.ameykamat.intuit.chess.board.Board;
import in.ameykamat.intuit.chess.board.Cell;
import in.ameykamat.intuit.chess.constant.Color;
import in.ameykamat.intuit.chess.exception.IllegalCellException;

public abstract class Piece {

    private final Color color;
    private Cell cell;
    private boolean isMoved;

    protected Piece(final Color color) {
        this.color = color;
        isMoved = false;
    }

    public abstract boolean isValid(final Cell destination, final Board board) throws IllegalCellException;
    public abstract boolean isKillValid(final Cell destination, final Board board) throws IllegalCellException;

    public Color getColor() {
        return color;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public boolean isMoved() {
        return isMoved;
    }

    protected void setMoved(){
        this.isMoved = true;
    }

    public void moved(){ setMoved(); };
}
