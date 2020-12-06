package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;


/**
 * The NonPerfect maze class represents a maze which is not perfect. It has methods to create the
 * maze and also to get the possible paths in the maze.
 */
public class NonPerfectMaze extends AbstractIMaze {

  private final int rows;
  private final int columns;
  private final int remainingWalls;


  /**
   * The constructor of the class Instantiates all the variables of a non perfect maze. It has the
   * dimensions of the maze to be created, the goal position as well the remaining walls to be
   * present in the maze.
   *
   * @param rows           the rows of the maze
   * @param columns        the columns of the maze
   * @param remainingWalls the remaining walls in the maze
   * @param wrapping       if the wrapping type of maze tobe created or not
   */
  public NonPerfectMaze(int rows, int columns, int remainingWalls, Boolean wrapping,
                        int batPercentage, int pitPercentage) {
    super(rows, columns, remainingWalls, wrapping, batPercentage, pitPercentage);
    this.rows = rows;
    this.columns = columns;
    this.remainingWalls = remainingWalls;
  }

  @Override
  public void createMaze() {
    super.createMaze();
    removeExtraWalls(this.remainingWalls);
    super.features();
  }

  @Override
  public String getMazePath() {
    return this.mazePath.toString();
  }

  /**
   * The method returns a hash map containing the wall positions in the maze..
   *
   * @return a hash in string format
   */
  public String getWallMap() {
    return this.wallMap.toString();
  }


  private void removeExtraWalls(int remainingWalls) {
    ArrayList<Integer> list = new ArrayList<Integer>(this.wallMap.keySet());
    Collections.shuffle(list, AbstractIMaze.random);
    int wallsToBeRemoved = rows * (columns - 1) + columns * (rows - 1) - (rows * columns) + 1
            - remainingWalls;
    while (list.size() != 0 && wallsToBeRemoved != 0) {
      if (this.wallMap.get(list.get(0)).size() != 0) {
        int key = list.get(0);
        int value = this.wallMap.get(list.get(0)).iterator().next();
        wallMap.get(key).remove(value);
        wallMap.get(value).remove(key);
        mazePath.get(key).add(value);
        mazePath.get(value).add(key);
        wallsToBeRemoved--;
      } else {
        list.remove(0);
      }
    }
  }

  private int edgeCount(Map<Integer, Set<Integer>> map) {
    int count = 0;
    for (int t : map.keySet()) {
      count += map.get(t).size();
    }
    return count / 2;
  }


  @Override
  public int getWallCount() {
    return edgeCount(this.wallMap);
  }


}
