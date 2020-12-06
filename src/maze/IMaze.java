package maze;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The interface of Maze represents various kinds of mazes like perfect, imperfect with subtypes of
 * wrapping and non wrapping. The interface contains all the methods which are necessary for the
 * problem statement. The interface has methods to keep a track of gold and thief if present in each
 * room of the maze.
 */
public interface IMaze {


  /**
   * Gets maze size.
   *
   * @return the maze size
   */
  int getMazeSize();

  /**
   * Gets wall count in a maze.
   *
   * @return the wall count
   */
  int getWallCount();

  /**
   * Current move list keeps track of all the moves a player can make at from a particular
   * position.
   *
   * @param row    the row number of the room.
   * @param column the column number of the room.
   * @return the hash map
   */
  Map<Directions, int[]> currMoveList(int row, int column);


  /**
   * Gets rows of the maze.
   *
   * @return the rows
   */
  int getRows();

  /**
   * Gets columns of the maze.
   *
   * @return the columns
   */
  int getColumns();


  /**
   * The method creates a maze once all the rooms are defined.
   */
  void createMaze();

  /**
   * Gets a adjacency list containing all the possible moves in the maze.
   *
   * @return a hash map of all the possible moves in a maze
   */
  String getMazePath();


  /**
   * Gets node or the room corresponding to the room number.
   *
   * @param number the number
   * @return the node
   */
  Node getNode(int number);


  /**
   * Gets number to coordinate mapping.
   *
   * @return the number to coordinate
   */
  HashMap<Integer, int[]> getNumberToCoordinate();


  /**
   * Gets available player positions as a list of integers.
   *
   * @return the available player positions
   */
  List<Integer> getAvailablePlayerPositions();


  /**
   * Move method returns the coordinates of the move given the direction in terms of coordinates row
   * and column.
   *
   * @param directionMap the directions hash map of direction and corresponding row and column
   * @param north        the move of enum type
   * @return the array of coordinates in terms of rows and columns
   */
  int[] move(Map<Directions, int[]> directionMap, Directions north);

  /**
   * Gets maze path list.
   *
   * @return the maze path list
   */
  Map<Integer, Set<Integer>> getMazePathList();

  /**
   * Gets hall way path list.
   *
   * @return the hall way path list
   */
  List<Integer> getHallWayPathList();

  /**
   * Gets wampus list.
   *
   * @return the wampus list
   */
  int getWampusLocation();

  /**
   * Gets bat list.
   *
   * @return the bat list
   */
  List<Integer> getBatList();

  /**
   * Gets pit list.
   *
   * @return the pit list
   */
  List<Integer> getPitList();
}
