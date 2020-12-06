package maze;

import java.util.ArrayList;
import java.util.List;

/**
 * The class used ot create a room in the maze with corresponding coordinates and placing all the
 * features in the room.
 */
class Node {

  private final List<Features> featuresList;
  private final int nodeNumberRow;
  private final int nodeNumberCol;
  private final int number;

  /**
   * The constructor instantiates the parameters of the room with coordinates, gold value and
   * thief.
   *
   * @param nodeNumberRow the node number row
   * @param nodeNumberCol the node number col
   * @param number        the number of the room
   */

  public Node(int nodeNumberRow, int nodeNumberCol, int number) {
    this.nodeNumberRow = nodeNumberRow;
    this.nodeNumberCol = nodeNumberCol;
    this.number = number;
    this.featuresList = new ArrayList<>();
  }

  public List<Features> getFeatureList() {
    return featuresList;
  }


  /**
   * Gets room number row position.
   *
   * @return the node number row
   */
  public int getNodeNumberRow() {
    return nodeNumberRow;
  }


  /**
   * Gets node number column position.
   *
   * @return the node number col
   */
  public int getNodeNumberCol() {
    return nodeNumberCol;
  }


  /**
   * Gets number of the room in the maze.
   *
   * @return the number
   */
  public int getNumber() {
    return number;
  }
}
