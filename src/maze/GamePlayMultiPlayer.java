package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * The type Game play multi player class is used for making the moves of the player, shooting arrows
 * as well as switching between multiple players in the game.
 */
public class GamePlayMultiPlayer extends GamePlayExtended implements IGamePlayMultiplayer {

  private final List<Player> playerList;

  /**
   * The game play constructor instantiates the maze as well as the player. The player is placed on
   * the maze and the maze is created.
   *
   * @param maze        the maze decided by the user
   * @param noOfPlayers the no of players
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
      this.player.getMoveMemory().add(randomPositions.get(0));
      randomPositions.remove(0);
    }
    changePlayer(0);
  }

  @Override
  public void changePlayer(int playerNumber) {
    this.player = playerList.get(playerNumber);
  }

  @Override
  public Messages makeMove(Directions move) {
    Messages message = super.makeMove(move);
    return message;
  }

  @Override
  public Messages arrowMakeMove(Directions move, int noOfMoves) {
    Messages message = super.arrowMakeMove(move, noOfMoves);
    return message;
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
