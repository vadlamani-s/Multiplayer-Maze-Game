package maze;

/**
 * The type Position class is used to encapsulate the coordinates of row and column of a room into
 * an object of position.
 */
public class Position {
  /**
   * The Row position.
   */
  private final int rowPosition;
  /**
   * The Column position.
   */
  private final int columnPosition;

  /**
   * Instantiates a new Position class which keeps track of the row and column as a single object.
   *
   * @param rowPosition    the row position
   * @param columnPosition the column position
   */
  public Position(int rowPosition, int columnPosition) {
    this.rowPosition = rowPosition;
    this.columnPosition = columnPosition;
  }

  /**
   * Gets row position.
   *
   * @return the row position
   */
  int getRowPosition() {
    return rowPosition;
  }

  /**
   * Gets column position.
   *
   * @return the column position
   */
  int getColumnPosition() {
    return columnPosition;
  }


}