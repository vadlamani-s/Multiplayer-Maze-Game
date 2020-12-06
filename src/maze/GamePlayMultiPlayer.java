package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GamePlayMultiPlayer extends GamePlayExtended implements IGamePlayMultiplayer {

  private final List<Player> playerList;

  /**
   * The game play constructor instantiates the maze as well as the player. The player is placed on
   * the maze and the maze is created.
   *
   * @param maze the maze decided by the user
   */
  public GamePlayMultiPlayer(IMaze maze, int noOfPlayers) {
    super(maze);
    playerList = new ArrayList<>(noOfPlayers);
    for (int i = 0; i < noOfPlayers; i++) {
      playerList.add(new Player(0, 0, maze.getColumns()));
    }
    // Shuffling the available positions
    List<Integer> randomPositions = new ArrayList<>(maze.getAvailablePlayerPositions());
    Collections.shuffle(randomPositions, AbstractIMaze.random);

    // Assigning the available positions to each of the players
    for (int i = 0; i < noOfPlayers; i++) {
      int[] location = maze.getNumberToCoordinate().get(randomPositions.get(0));
      this.player = playerList.get(i);
      this.updatePlayerPosition(location[0], location[1]);
      randomPositions.remove(0);
    }
  }

  @Override
  public void changePlayer() {
    this.player = playerList.get(0);
    playerList.add(playerList.remove(0));
  }

  @Override
  public Messages makeMove(Directions move) {
    Messages message = super.makeMove(move);
    changePlayer();
    return message;
  }

  @Override
  public Messages arrowMakeMove(Directions move, int noOfMoves) {
    Messages message = super.arrowMakeMove(move, noOfMoves);
    changePlayer();
    return message;
  }

  @Override
  public Map<Integer, Set<Integer>> getMazePathList() {
    return this.iMaze.getMazePathList();
  }

  @Override
  public List<Integer> getHallWayPathList() {
    return new ArrayList<>(this.iMaze.getHallWayPathList());
  }

  @Override
  public int getWampusLocation() {
    return this.iMaze.getWampusLocation();
  }

  @Override
  public List<Integer> getBatList() {
    return new ArrayList<>(this.iMaze.getBatList());
  }

  @Override
  public List<Integer> getPitList() {
    return new ArrayList<>(this.iMaze.getPitList());
  }

  @Override
  public HashMap<Integer, int[]> getNumberToCoordinate() {
    return this.iMaze.getNumberToCoordinate();
  }

}
