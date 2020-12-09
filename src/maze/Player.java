package maze;

import java.util.HashSet;
import java.util.Set;

/**
 * The Player class has all the details pertaining to the player who is deployed on the maze. The
 * player keeps track of his position in terms of coordinates as well. The player also has a track
 * of the gold obtained from the maze.
 */
public class Player {

  private Position positionObj;
  private float goldCount;
  private final int totalColumns;
  private final Set<Integer> moveMemory;
  private int arrows;

  /**
   * Instantiates a new Player in the maze with corresponding row and column number as well as the
   * total columns in the maze.
   *
   * @param posRow       the pos row in the maze
   * @param posCol       the pos col in the maze
   * @param totalColumns the total columns in the maze
   */
  public Player(int posRow, int posCol, int totalColumns) {
    this.goldCount = 0;
    this.totalColumns = totalColumns;
    this.positionObj = new Position(posRow, posCol);
    this.moveMemory = new HashSet<>();
    this.arrows = 3;
  }

  /**
   * Gets arrows the player currently holds.
   *
   * @return the arrows held by the player
   */
  public int getArrows() {
    return arrows;
  }

  /**
   * Reduce the arrow count by 1.
   */
  public void updateArrows() {
    this.arrows = arrows - 1;
  }

  /**
   * Calculate the position or room number using coordinates.
   *
   * @param row the row number
   * @param col the col number
   * @return the player position corresponding to the room.
   */
  public int calculateNumber(int row, int col) {
    return totalColumns * row + col;
  }


  /**
   * Sets the Gold count of the player.
   *
   * @param goldCount gold count of the player
   */
  public void updatePlayerGold(float goldCount) {
    this.goldCount = goldCount;
  }


  /**
   * Sets the position of the player.
   *
   * @param newPosition the position of the player
   */
  public void setPosition(Position newPosition) {
    this.positionObj = newPosition;
  }


  /**
   * Gets player row in the maze.
   *
   * @return the player row
   */
  public int getPlayerRow() {
    return this.positionObj.getRowPosition();
  }


  /**
   * Gets player col in the maze.
   *
   * @return the player col
   */
  public int getPlayerCol() {
    return this.positionObj.getColumnPosition();
  }


  /**
   * Gets position number.
   *
   * @return the position number
   */
  public int getPositionNumber() {
    return calculateNumber(getPlayerRow(), getPlayerCol());
  }


  /**
   * Gold count float.
   *
   * @return the float
   */
  public float goldCount() {
    return this.goldCount;
  }

  /**
   * Gets the past moves of the player.
   *
   * @return list of all the moves of the player
   */
  public Set<Integer> getMoveMemory() {
    return moveMemory;
  }
}
