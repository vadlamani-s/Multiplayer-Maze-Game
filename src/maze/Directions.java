package maze;

/**
 * The enum Directions is used for taking in the directions entered by the user as well as for
 * deciding the neighbours of a room.
 */
public enum Directions {
  /**
   * North directions.
   */
  NORTH(-1, 0),
  /**
   * South directions.
   */
  SOUTH(1, 0),
  /**
   * East directions.
   */
  EAST(0, 1),
  /**
   * West directions.
   */
  WEST(0, -1);

  private final int row;
  private final int column;

  Directions(int row, int col) {
    this.row = row;
    this.column = col;
  }

  /**
   * Gets direction of the enum type when given the coordinates of the direction in which to be
   * moved.
   *
   * @param row    the row of the maze
   * @param column the column of the maze
   * @return the direction of enum type
   */
  public static Directions getDirection(int row, int column) {
    for (Directions directions : Directions.values()) {
      if (directions.row == row && directions.column == column) {
        return directions;
      }
    }
    throw new IllegalArgumentException("Invalid entry");
  }

}
