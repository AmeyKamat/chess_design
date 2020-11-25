package in.ameykamat.intuit.chess;

import in.ameykamat.intuit.chess.board.Player;
import in.ameykamat.intuit.chess.board.Position;
import in.ameykamat.intuit.chess.constant.Color;
import in.ameykamat.intuit.chess.exception.IllegalStateException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static in.ameykamat.intuit.chess.constant.Color.BLACK;
import static in.ameykamat.intuit.chess.constant.Color.WHITE;
import static in.ameykamat.intuit.chess.constant.GameStatus.*;
import static in.ameykamat.intuit.chess.utils.PositionUtils.getPosition;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class ChessTest {

    @Test
    public void shouldCompleteGame(){
        final Player whitePlayer = new Player("player 1", WHITE);
        final Player blackPlayer = new Player("player 2", BLACK);

        final Chess game = new Chess(whitePlayer, blackPlayer);
        assertThat(game.getStatus(), is(RESET));

        game.startGame();
        assertThat(game.getStatus(), is(ACTIVE));

        game.makeMove(whitePlayer, getPosition("f2"), getPosition("f3"));
        assertThat(game.getStatus(), is(ACTIVE));

        game.makeMove(blackPlayer, getPosition("e7"), getPosition("e6"));
        assertThat(game.getStatus(), is(ACTIVE));

        game.makeMove(whitePlayer, getPosition("g2"), getPosition("g3"));
        assertThat(game.getStatus(), is(ACTIVE));

        game.makeMove(blackPlayer, getPosition("e6"), getPosition("e5"));
        assertThat(game.getStatus(), is(ACTIVE));

        game.makeMove(whitePlayer, getPosition("g3"), getPosition("g4"));
        assertThat(game.getStatus(), is(ACTIVE));

        game.makeMove(blackPlayer, getPosition("d8"), getPosition("h4"));
        assertThat(game.getStatus(), is(BLACK_WON));
    }

    @Test
    public void shouldTestKill(){
        final Player whitePlayer = new Player("player 1", WHITE);
        final Player blackPlayer = new Player("player 2", BLACK);

        final Chess game = new Chess(whitePlayer, blackPlayer);
        assertThat(game.getStatus(), is(RESET));

        game.startGame();
        assertThat(game.getStatus(), is(ACTIVE));

        game.makeMove(whitePlayer, getPosition("f2"), getPosition("f3"));
        assertThat(game.getStatus(), is(ACTIVE));

        game.makeMove(blackPlayer, getPosition("e7"), getPosition("e6"));
        assertThat(game.getStatus(), is(ACTIVE));

        game.makeMove(whitePlayer, getPosition("f3"), getPosition("f4"));
        assertThat(game.getStatus(), is(ACTIVE));

        game.makeMove(blackPlayer, getPosition("e6"), getPosition("e5"));
        assertThat(game.getStatus(), is(ACTIVE));

        game.makeMove(whitePlayer, getPosition("f4"), getPosition("e5"));
        assertThat(game.getStatus(), is(ACTIVE));
    }

    @Test
    public void shouldThrowExceptionOnIllegalState(){
        final Player whitePlayer = new Player("player 1", WHITE);
        final Player blackPlayer = new Player("player 2", BLACK);

        final Chess game = new Chess(whitePlayer, blackPlayer);
        game.startGame();

        game.makeMove(whitePlayer, getPosition("f2"), getPosition("f3"));

        assertThrows(IllegalStateException.class,
                () -> game.startGame());
    }

}