package maze;

/**
 * The arrow class represents the state of an arrow in the game. The arrow has methods to keeps
 * track of its position in the maze as well as the move method to update its position in the game.
 */
public class Arrow {

  private int row;
  private int column;
  private Directions currentDirection;

  /**
   * The constructor instantiates a new Arrow with the current player position and the direction the
   * arrow ahs to be move.
   *
   * @param row  the row position of arrow
   * @param col  the col position of arrow
   * @param move the direction in which arrow has to move
   */
  public Arrow(int row, int col, Directions move) {
    this.row = row;
    this.column = col;
    currentDirection = move;
  }

  /**
   * Gets current direction arrow moves.
   *
   * @return the current direction of the arrow
   */
  public Directions getCurrentDirection() {
    return currentDirection;
  }

  /**
   * Gets row of the arrow.
   *
   * @return the row coordinate
   */
  public int getRow() {
    return row;
  }

  /**
   * Gets column of the arrow.
   *
   * @return the column coordinate
   */
  public int getColumn() {
    return column;
  }

  /**
   * Update arrow coordinates after movement.
   *
   * @param row    the row coordinate
   * @param column the column coordinate
   */
  public void updateArrow(int row, int column) {
    this.row = row;
    this.column = column;
  }


}
