package in.ameykamat.intuit.chess.piece;

import in.ameykamat.intuit.chess.board.Board;
import in.ameykamat.intuit.chess.board.Cell;
import in.ameykamat.intuit.chess.board.Position;
import in.ameykamat.intuit.chess.exception.IllegalCellException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.Optional;
import java.util.stream.Stream;

import static in.ameykamat.intuit.chess.constant.Color.BLACK;
import static in.ameykamat.intuit.chess.constant.Color.WHITE;
import static in.ameykamat.intuit.chess.utils.PositionUtils.getPosition;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class RookTest {

    @Mock
    private Board board;

    @Mock
    private Cell cell;

    @BeforeEach
    void init() throws IllegalCellException {
        initMocks(this);
        when(board.getCell(any(Position.class))).thenReturn(cell);
    }

    @ParameterizedTest(name = "[{index}] Rooks move from {0} to {1} while it jumps piece {2} is {3}")
    @MethodSource("moveProvider")
    void shouldValidateMove(final String startNotation,
                            final String endNotation,
                            final boolean jumpsPiece,
                            final boolean isValid) throws IllegalCellException {
        if(jumpsPiece) when(cell.getPiece()).thenReturn(Optional.of(new Pawn(WHITE)));
        else when(cell.getPiece()).thenReturn(Optional.empty());

        final Piece rook = new Rook(WHITE);
        rook.setCell(new Cell(getPosition(startNotation), WHITE));

        assertThat(rook.isValid(new Cell(getPosition(endNotation), BLACK), board), is(isValid));
    }

    private static Stream<Arguments> moveProvider() {
        return Stream.of(
                Arguments.of("e5", "e9", true, false),
                Arguments.of("e5", "e9", false, true),
                Arguments.of("e5", "e1", true, false),
                Arguments.of("e5", "e1", false, true),
                Arguments.of("e5", "a5", true, false),
                Arguments.of("e5", "a5", false, true),
                Arguments.of("e5", "h5", true, false),
                Arguments.of("e5", "h5", false, true),
                Arguments.of("e5", "a9", false, false)
        );
    }

}