package maze;

import java.util.HashMap;
import java.util.List;


/**
 * The interface Game play multiplayer.
 */
public interface IGamePlayMultiplayer extends IGamePlayExtended {

  /**
   * Change player.
   *
   * @param playerNumber the player number
   */
  void changePlayer(int playerNumber);

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

  /**
   * Gets number to coordinate mapping.
   *
   * @return the number to coordinate
   */
  HashMap<Integer, int[]> getNumberToCoordinate();

}
