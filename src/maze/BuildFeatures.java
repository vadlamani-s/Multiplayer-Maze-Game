package maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The Build features class represents all the features that have to be added to each of the room.
 * The class has logic for building features in each of the rooms in teh maze and takes in the inout
 * for percentage of bat and pits in the maze.
 */
class BuildFeatures extends MazeGraph {

  private final int batPercentage;
  private final int pitPercentage;
  private List<Integer> hallwayList;
  private List<Integer> goldList;
  private List<Integer> pitList;
  private List<Integer> batList;
  private final int rows;
  private final int columns;
  private int wampusLocation;

  /**
   * The constructor instantiates a new Build features object with the size of the maze in terms of
   * rows and columns. It also instantiates the percentage bats and pits in the maze.
   *
   * @param batPercentage the bat percentage
   * @param pitPercentage the pit percentage
   * @param rows          the rows
   * @param columns       the columns
   */
  public BuildFeatures(int batPercentage, int pitPercentage, int rows, int columns) {
    super();
    this.batPercentage = batPercentage;
    this.pitPercentage = pitPercentage;
    hallwayList = new ArrayList<>();
    goldList = new ArrayList<>();
    pitList = new ArrayList<>();
    batList = new ArrayList<>();
    this.rows = rows;
    this.columns = columns;
    wampusLocation = 0;
  }


  /**
   * Build features method is used to build all the features in the maze taking in to consideration
   * the walls in the maze.
   *
   * @param mazeMap    the wall map
   * @param mapIntNode the map between room number and the room object
   * @return the list
   */
  public List<Integer> buildFeatures(Map<Integer, Set<Integer>> mazeMap,
                                     Map<Integer, Node> mapIntNode) {
    this.wampusLocation = buildList(mazeMap);
    for (Integer number : mapIntNode.keySet()) {
      if (hallwayList.contains(number)) {
        mapIntNode.get(number).getFeatureList().add(Features.HALLWAY);
      } else {
        mapIntNode.get(number).getFeatureList().remove(Features.HALLWAY);
      }
      if (batList.contains(number)) {
        mapIntNode.get(number).getFeatureList().add(Features.BAT);
      } else {
        mapIntNode.get(number).getFeatureList().remove(Features.BAT);
      }
      if (pitList.contains(number)) {
        mapIntNode.get(number).getFeatureList().add(Features.PIT);
      } else {
        mapIntNode.get(number).getFeatureList().remove(Features.PIT);
      }
      if (goldList.contains(number)) {
        mapIntNode.get(number).getFeatureList().add(Features.GOLD);
      } else {
        mapIntNode.get(number).getFeatureList().remove(Features.GOLD);
      }
      if (number == wampusLocation) {
        mapIntNode.get(number).getFeatureList().add(Features.WAMPUS);
      } else {
        mapIntNode.get(number).getFeatureList().remove(Features.WAMPUS);
      }
    }

    List<Integer> availablePlayerPosition = new ArrayList<>(mapIntNode.keySet());
    availablePlayerPosition.removeAll(hallwayList);
    availablePlayerPosition.remove((Integer) wampusLocation);
    availablePlayerPosition.removeAll(batList);
    availablePlayerPosition.removeAll(pitList);
    return availablePlayerPosition;
  }


  public List<Integer> getHallWayPathList() {
    return hallwayList;
  }

  public int getWampusLocation() {
    return wampusLocation;
  }

  public List<Integer> getBatList() {
    return batList;
  }

  public List<Integer> getPitList() {
    return pitList;
  }

  private int buildList(Map<Integer, Set<Integer>> mazeMap) {
    hallwayList = hallway(mazeMap);
    int wampusLocation = wampusLocation(rows * columns, hallwayList);
    int numberOfHallways = hallwayList.size();
    int numberOfCaves = rows * columns - numberOfHallways - 2;
    int numberOfBats = (int) Math.floor(numberOfCaves * batPercentage * 0.01);
    int numberOfPits = (int) Math.floor(numberOfCaves * pitPercentage * 0.01);
    int goldCount = (int) Math.ceil(numberOfCaves * 0.2);
    if (numberOfCaves <= 2) {
      numberOfBats = 0;
      numberOfPits = 0;
    }
    goldList = goldAndBatListGeneration(rows * columns, goldCount, hallwayList,
            wampusLocation);
    batList = goldAndBatListGeneration(rows * columns, numberOfBats, hallwayList,
            wampusLocation);
    pitList = pitListGeneration(rows * columns, numberOfPits, hallwayList, wampusLocation,
            goldList);
    return wampusLocation;
  }


  private int wampusLocation(int totalNodes, List<Integer> hallWayList) {

    int wampusLocation = AbstractIMaze.random.nextInt(totalNodes);
    while (hallWayList.contains(wampusLocation)) {
      wampusLocation = AbstractIMaze.random.nextInt(totalNodes);
    }
    return wampusLocation;
  }

  private List<Integer> pitListGeneration(int limit, int total, List<Integer> hallway, int wampus,
                                          List<Integer> goldList) {
    List<Integer> list = new ArrayList<>();
    while (list.size() < total) {
      int num = AbstractIMaze.random.nextInt(limit);
      if (!list.contains(num) && !hallway.contains(num) && !goldList.contains(num)
              && wampus != num) {
        list.add(num);
      }
    }
    return list;
  }

  private List<Integer> hallway(Map<Integer, Set<Integer>> wallMap) {
    List<Integer> hallway = new ArrayList<>();
    for (Integer nodeNumber : wallMap.keySet()) {
      if (wallMap.get(nodeNumber).size() == 2) {
        hallway.add(nodeNumber);
      }
    }
    return hallway;
  }


  private List<Integer> goldAndBatListGeneration(int totalNodes, int total, List<Integer> hallway,
                                                 int wampus) {
    List<Integer> list = new ArrayList<>();
    while (list.size() < total) {
      int num = AbstractIMaze.random.nextInt(totalNodes);
      if (!list.contains(num) && !hallway.contains(num) && wampus != num) {
        list.add(num);
      }
    }
    return list;
  }
}
