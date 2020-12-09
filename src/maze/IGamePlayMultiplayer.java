package maze;

import java.util.HashMap;
import java.util.List;


/**
 * The interface Game play Multiplayer extended contains all the for the extended game play
 * functionality for the Wampus game. The interface contains additional functionality added for this
 * game for moving, the arrow and hints as wells as switching between players.
 */
public interface IGamePlayMultiplayer extends IGamePlayExtended {

  /**
   * Change player during the gameplay. According to the game the players switch turns during the
   * gameplay between moves and shoot.
   *
   * @param playerNumber the player number that has to play the game
   */
  void changePlayer(int playerNumber);

  /**
   * Gets wampus location in the maze.
   *
   * @return the wampus location
   */
  int getWampusLocation();

  /**
   * Gets bat list in the maze based on the location number.
   *
   * @return the bat list
   */
  List<Integer> getBatList();

  /**
   * Gets pit list in the maze based on the location number.
   *
   * @return the pit list
   */
  List<Integer> getPitList();

  /**
   * Gets number to coordinate mapping in the maze.
   *
   * @return the number to coordinate mapping
   */
  HashMap<Integer, int[]> getNumberToCoordinate();

}
