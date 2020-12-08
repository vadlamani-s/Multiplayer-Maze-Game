package maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Mock model.
 */
public class MockModel implements IGamePlayExtended {

  private final StringBuilder logIn;
  private final int logOut;

  /**
   * Instantiates a new Mock model.
   *
   * @param logIn  the log in
   * @param logOut the log out
   */
  public MockModel(StringBuilder logIn, int logOut) {
    this.logIn = logIn;
    this.logOut = logOut;
  }

  @Override
  public Messages arrowMakeMove(Directions move, int numberOfMoves) {
    logIn.append(move.toString()).append(" ").append(numberOfMoves);
    return Messages.DEFAULT;
  }

  @Override
  public StringBuilder hintImpl(int row, int column) {
    logIn.append("Input: ").append(row).append(" ").append(column);
    return new StringBuilder(logOut);
  }

  @Override
  public Set<Integer> getMoveMemory() {
    return new HashSet<>();
  }

  @Override
  public int getArrows() {
    return logOut;
  }

  @Override
  public Set<Directions> getMoves(int row, int column) {
    return new HashSet<>();
  }

  @Override
  public Messages makeMove(Directions move) {
    return Messages.DEFAULT;
  }

  @Override
  public Boolean isWinner() {
    return false;
  }

  @Override
  public String playerDetails() {
    return new StringBuilder(logOut).toString();
  }

  @Override
  public double getPlayerGold() {
    return (double) logOut;
  }

  @Override
  public int getPlayerPosRow() {
    return logOut;
  }

  @Override
  public int getPlayerPosColumn() {
    return logOut;
  }

  @Override
  public int getPlayerPosition() {
    return logOut;
  }

  @Override
  public void updatePlayerPosition(int playerRow, int playerColumn) {
  }
}
