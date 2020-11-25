package in.ameykamat.intuit.chess.board;

import in.ameykamat.intuit.chess.piece.Pawn;
import in.ameykamat.intuit.chess.piece.Piece;
import org.junit.jupiter.api.Test;

import static in.ameykamat.intuit.chess.constant.Color.BLACK;
import static in.ameykamat.intuit.chess.constant.Color.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void shouldBuildCell(){
        final Cell cell = new Cell(Position.of('A', 10), WHITE);

        assertThat(cell, instanceOf(Cell.class));
        assertThat(cell.getPosition().equals(Position.of('A', 10)), is(true));
        assertThat(cell.getColor(), is(WHITE));
        assertThat(cell.getPiece().isPresent(), is(false));
    }

    @Test
    void shouldAddPiece(){
        final Cell cell = new Cell(Position.of('A', 10), WHITE);
        final Piece piece = new Pawn(BLACK);
        cell.setPiece(piece);

        assertThat(cell.getPiece().isPresent(), is(true));
        assertThat(cell.getPiece().get(), is(piece));
    }

    @Test
    void shouldClearPiece(){
        final Cell cell = new Cell(Position.of('A', 10), WHITE);
        final Piece piece = new Pawn(BLACK);
        cell.setPiece(piece);
        cell.clearPiece();

        assertThat(cell.getPiece().isPresent(), is(false));
    }



}