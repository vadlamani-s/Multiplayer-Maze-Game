package maze;

import java.util.Map;
import java.util.Set;

/**
 * The type Perfect maze create a maze of perfect type of both wrapping and non wrapping types. The
 * class is responsible for creating a maze taking all the parameters into consideration.
 */
public class PerfectMaze extends AbstractIMaze {


  /**
   * Instantiates a new Perfect maze.
   *
   * @param rows     the row size of the maze
   * @param columns  the column size of the maze
   * @param wrapping the wrapping or non wrapping type of the maze
   */
  public PerfectMaze(int rows, int columns, Boolean wrapping, int batPercentage,
                     int pitPercentage) {
    super(rows, columns, rows * (columns - 1) + columns * (rows - 1) - (rows * columns)
            + 1, wrapping, batPercentage, pitPercentage);
  }


  @Override
  public void createMaze() {
    super.createMaze();
    features();
  }

  /**
   * Maze path string.
   *
   * @return the string of the path in the maze
   */
  public String getMazePath() {
    return this.mazePath.toString();
  }


  private Map<Integer, Set<Integer>> getMaze() {
    return this.wallMap;
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
    return edgeCount(this.getMaze());
  }

}

