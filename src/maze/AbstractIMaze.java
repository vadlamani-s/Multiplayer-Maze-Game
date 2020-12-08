package maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


/**
 * The type Abstract class implements the Imaze interface. The class has all the methods defined in
 * the interface. The common methods are extending the interface have the implementation in this
 * class.
 */
public abstract class AbstractIMaze implements IMaze {
  /**
   * The Maze.
   */
  protected final Map<Integer, Set<Integer>> maze;
  /**
   * The Map int node.
   */
  protected final Map<Integer, Node> mapIntNode;
  /**
   * The Wall map.
   */
  protected final Map<Integer, Set<Integer>> wallMap;
  /**
   * The Maze path.
   */
  protected final Map<Integer, Set<Integer>> mazePath;
  private final int rows;
  private final int columns;
  private final HashMap<Integer, int[]> numberToCoordinate;
  private final MazeGraph mazeGraph;
  private final BuildFeatures buildFeatures;
  private List<Integer> availablePlayerPositions;
  static Random random = new Random();

  public static void setRandom(int seed) {
    AbstractIMaze.random = new Random(seed);
  }


  /**
   * Instantiates a new Abstract i maze.
   *
   * @param rows           the rows
   * @param columns        the columns
   * @param remainingWalls the remaining walls
   * @param wrappingCheck  the wrapping check
   * @param batPercentage  the bat percentage
   * @param pitPercentage  the pit percentage
   */
  public AbstractIMaze(int rows, int columns, int remainingWalls, Boolean wrappingCheck,
                       int batPercentage, int pitPercentage) {
    if (batPercentage < 0 || pitPercentage < 0 || batPercentage >= 100 || pitPercentage >= 100) {
      throw new IllegalArgumentException("invalid percentage value, has to be between 0 and 100");
    }

    if (rows <= 2 || columns <= 2) {
      throw new IllegalArgumentException("rows and columns cannot be less than or equal to 2");
    }
    int minWalls = rows * (columns - 1) + columns * (rows - 1) - (rows * columns) + 1;

    if (remainingWalls > minWalls || remainingWalls < 0) {
      throw new IllegalArgumentException("incorrect remaining walls entered");
    }

    this.rows = rows;
    this.columns = columns;
    int totalWalls = minWalls + (rows * columns) - 1;
    this.mazeGraph = new MazeGraph();
    this.maze = new HashMap<>();
    this.mapIntNode = new HashMap<>();
    this.wallMap = new HashMap<>();
    this.mazePath = new HashMap<>();
    this.numberToCoordinate = new HashMap<>();
    if (wrappingCheck) {
      createMapWrapping();
    } else {
      createMap();
    }
    mapIntNode();
    numberToCoordinate();
    maze();
    buildFeatures = new BuildFeatures(batPercentage, pitPercentage, rows, columns);
    availablePlayerPositions = new ArrayList<>(buildFeatures.buildFeatures(this.wallMap,
            this.mapIntNode));
  }


  private void createMap() {
    this.maze.putAll(this.mazeGraph.createMap(this.rows, this.columns));
  }


  private void createMapWrapping() {
    this.maze.putAll(this.mazeGraph.createMapWrapping(this.rows, this.columns));
  }

  private void mapIntNode() {
    this.mapIntNode.putAll(this.mazeGraph.mapIntNode(this.rows, this.columns));
  }

  private void numberToCoordinate() {
    this.numberToCoordinate.putAll(this.mazeGraph.numberCoordinate(this.rows, this.columns));
  }

  public Map<Integer, Set<Integer>> getMazePathList() {
    return new HashMap<>(this.mazePath);
  }

  public List<Integer> getHallWayPathList() {
    return new ArrayList<>(buildFeatures.getHallWayPathList());
  }

  public int getWampusLocation() {
    return buildFeatures.getWampusLocation();
  }

  public List<Integer> getBatList() {
    return new ArrayList<>(buildFeatures.getBatList());
  }

  public List<Integer> getPitList() {
    return new ArrayList<>(buildFeatures.getPitList());
  }

  @Override
  public List<Integer> getAvailablePlayerPositions() {
    return availablePlayerPositions;
  }


  @Override
  public void createMaze() {
    mazePath();
  }

  protected void features() {
    availablePlayerPositions = buildFeatures.buildFeatures(this.mazePath, this.mapIntNode);
  }

  @Override
  public abstract String getMazePath();

  @Override
  public HashMap<Integer, int[]> getNumberToCoordinate() {
    return numberToCoordinate;
  }


  private void maze() {
    List<List<Integer>> edgeList = edgeList();
    Map<String, Set<Integer>> component = new HashMap<>();
    for (int i = 0; i < this.rows * this.columns; i++) {
      String s = "C" + i;
      component.put(s, new HashSet<>(Arrays.asList(i)));
    }

    for (int node : this.maze.keySet()) {
      wallMap.put(node, new HashSet<>(this.maze.get(node)));
    }

    int edgesToBeRemoved = this.rows * this.columns - 1;
    Collections.shuffle(edgeList, AbstractIMaze.random);

    while (edgesToBeRemoved > 0) {
      List<Integer> list = edgeList.get(0);
      String componentIndex1 = "";
      String componentIndex2 = "";
      boolean checkPoint = false;
      for (String s : component.keySet()) {
        if (component.get(s).contains(list.get(1)) && component.get(s).contains(list.get(0))) {
          checkPoint = true;
          break;
        }
        if (component.get(s).contains(list.get(1))) {
          componentIndex2 = s;
        }
        if (component.get(s).contains(list.get(0))) {
          componentIndex1 = s;
        }
      }
      edgeList.remove(list);
      if (!checkPoint) {
        wallMap.get(list.get(0)).remove(list.get(1));
        wallMap.get(list.get(1)).remove(list.get(0));
        if (componentIndex1.compareTo(componentIndex2) < 0) {
          component.get(componentIndex1).addAll(component.get(componentIndex2));
          component.remove(componentIndex2);
        } else {
          component.get(componentIndex2).addAll(component.get(componentIndex1));
          component.remove(componentIndex1);
        }
        edgesToBeRemoved -= 1;
      }
    }
  }


  private void mazePath() {
    for (int node : this.maze.keySet()) {
      this.mazePath.put(node, new HashSet<>(this.maze.get(node)));
    }
    for (Integer node : this.mazePath.keySet()) {
      this.mazePath.get(node).removeAll(this.wallMap.get(node));
    }
  }

  @Override
  public Node getNode(int position) {
    return mapIntNode.get(position);
  }


  private List<List<Integer>> edgeList() {
    List<List<Integer>> edgeList = new ArrayList<>();
    for (int i : this.maze.keySet()) {
      for (int j : maze.get(i)) {
        if (i < j) {
          List<Integer> list = new ArrayList<>();
          list.add(i);
          list.add(j);
          edgeList.add(list);
        }
      }
    }
    return edgeList;
  }


  @Override
  public Map<Directions, int[]> currMoveList(int currRow, int currCol) {
    Map<Directions, int[]> directionMap = new HashMap<>();
    int currNumber = this.columns * currRow + currCol;
    for (int i : this.mazePath.get(currNumber)) {
      int[] coordinates = this.numberToCoordinate.get(i);
      int tempRow = coordinates[0] - currRow;
      int tempCol = coordinates[1] - currCol;
      if (tempRow > 1) {
        tempRow = -1;
      }
      if (tempRow < -1) {
        tempRow = 1;
      }
      if (tempCol < -1) {
        tempCol = 1;
      }
      if (tempCol > 1) {
        tempCol = -1;
      }
      Directions directions = Directions.getDirection(tempRow, tempCol);
      directionMap.put(directions, coordinates);
    }
    return directionMap;
  }

  @Override
  public int[] move(Map<Directions, int[]> directionsHashMap, Directions move) {
    return directionsHashMap.get(move);
  }

  @Override
  public int getRows() {
    return this.rows;
  }

  @Override
  public int getColumns() {
    return this.columns;
  }


  @Override
  public int getMazeSize() {
    return this.columns * this.rows;
  }

  @Override
  public abstract int getWallCount();

}
