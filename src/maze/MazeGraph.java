package maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * The class is used to create a maze by setting up each of the room and placing walls in the maze
 * at random positions based on the type of the maze and the parameters.
 */
public class MazeGraph {


  private final Map<Integer, Set<Integer>> map = new HashMap<>();

  private final List<ArrayList<Node>> grid = new ArrayList<>();


  private final Map<Integer, Node> mapIntNode = new HashMap<>();

  private final HashMap<Integer, int[]> numberToCoordinate = new HashMap<>();


  private void addNode(int node) {
    map.put(node, new HashSet<>());
  }


  private void addEdge(int source, int dest) {

    if (!map.containsKey(source)) {
      addNode(source);
    }
    if (!map.containsKey(dest)) {
      addNode(dest);
    }
    map.get(source).add(dest);
    map.get(dest).add(source);
  }


  private void setGrid(int rows, int columns) {
    List<Integer> goldList = goldList(rows * columns, (int) (Math.ceil(rows * columns * 0.2)));
    List<Integer> thiefList = thiefList(rows * columns,
            (int) (Math.ceil(rows * columns * 0.1)), goldList);
    int count = 0;
    for (int i = 0; i < rows; i++) {
      grid.add(new ArrayList<>());
      Node node;
      for (int j = 0; j < columns; j++) {
        count = columns * i + (j);
        node = new Node(i, j, count);
        grid.get(i).add(node);
      }
    }
  }


  /**
   * The method is used to keep a track of the room number and the corresponding coordinates of the
   * room in terms of row and column number.
   *
   * @param rows    the rows of the maze
   * @param columns the columns of the maze
   * @return the hash map containing room number and corresponding coordinates
   */
  public HashMap<Integer, int[]> numberCoordinate(int rows, int columns) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        int[] array = {i, j};
        numberToCoordinate.put(grid.get(i).get(j).getNumber(), array);
      }
    }
    return numberToCoordinate;
  }


  /**
   * The method is used ot keep a track of the room number and the corresponding object of the
   * room.
   *
   * @param rows    the rows of the maze
   * @param columns the columns of the maze
   * @return the map of the room number and the room object
   */
  public Map<Integer, Node> mapIntNode(int rows, int columns) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        mapIntNode.put(grid.get(i).get(j).getNumber(), grid.get(i).get(j));
      }
    }
    return mapIntNode;
  }


  /**
   * The method is used to create a map ie to place walls between the rooms.
   *
   * @param rows    the rows of the maze
   * @param columns the columns of the maze
   * @return the adjacency map of room number and corresponding walls between the rooms.
   */
  public Map<Integer, Set<Integer>> createMap(int rows, int columns) {
    setGrid(rows, columns);
    mapIntNode(rows, columns);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (mazeCheck(j - 1, columns)) {
          addEdge(grid.get(i).get(j).getNumber(), grid.get(i).get(j - 1).getNumber());
        }
        if (mazeCheck(j + 1, columns)) {
          addEdge(grid.get(i).get(j).getNumber(), grid.get(i).get(j + 1).getNumber());
        }
        if (mazeCheck(i - 1, rows)) {
          addEdge(grid.get(i).get(j).getNumber(), grid.get(i - 1).get(j).getNumber());
        }
        if (mazeCheck(i + 1, rows)) {
          addEdge(grid.get(i).get(j).getNumber(), grid.get(i + 1).get(j).getNumber());
        }
      }
    }
    return map;
  }


  /**
   * The method is used to create a map for wrapping maze ie to place walls between the rooms.
   *
   * @param rows    the rows of the maze
   * @param columns the columns of the maze
   * @return the adjacency map of room number and corresponding walls between the rooms.
   */
  public Map<Integer, Set<Integer>> createMapWrapping(int rows, int columns) {
    setGrid(rows, columns);
    mapIntNode(rows, columns);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (mazeCheck(j - 1, columns)) {
          addEdge(grid.get(i).get(j).getNumber(), grid.get(i).get(j - 1).getNumber());
        } else {
          addEdge(grid.get(i).get(j).getNumber(), grid.get(i).get(columns - j - 1).getNumber());
        }
        if (mazeCheck(j + 1, columns)) {
          addEdge(grid.get(i).get(j).getNumber(), grid.get(i).get(j + 1).getNumber());
        } else {
          addEdge(grid.get(i).get(j).getNumber(), grid.get(i).get(j + 1 - columns).getNumber());
        }
        if (mazeCheck(i - 1, rows)) {
          addEdge(grid.get(i).get(j).getNumber(), grid.get(i - 1).get(j).getNumber());
        } else {
          addEdge(grid.get(i).get(j).getNumber(), grid.get(rows - i - 1).get(j).getNumber());
        }
        if (mazeCheck(i + 1, rows)) {
          addEdge(grid.get(i).get(j).getNumber(), grid.get(i + 1).get(j).getNumber());
        } else {
          addEdge(grid.get(i).get(j).getNumber(), grid.get(i + 1 - rows).get(j).getNumber());
        }
      }
    }
    return map;
  }


  private List<Integer> goldList(int limit, int totalGold) {
    List<Integer> list = new ArrayList<>();
    while (list.size() < totalGold) {
      int num = AbstractIMaze.random.nextInt(limit);
      if (!list.contains(num)) {
        list.add(num);
      }
    }
    return list;
  }


  private List<Integer> thiefList(int limit, int totalThief, List<Integer> gold) {
    List<Integer> list = new ArrayList<>();
    while (list.size() < totalThief) {
      int num = AbstractIMaze.random.nextInt(limit);
      if (!list.contains(num) && !gold.contains(num)) {
        list.add(num);
      }
    }
    return list;
  }


  private Boolean mazeCheck(int position, int length) {
    return position >= 0 && position < length;
  }


  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int t : map.keySet()) {
      stringBuilder.append(t);
      stringBuilder.append("--");
      for (int m : map.get(t)) {
        stringBuilder.append(m);
      }
      stringBuilder.append("|");
    }
    return stringBuilder.toString();
  }


}
