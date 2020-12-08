package maze;

import java.util.Set;

/**
 * The interface Game play is a model used for controlling the game state. The interface has all the
 * common functionalities of the both player and the maze. It also has methods for moving the player
 * and updating the gold value of the player.
 */
public interface IGamePlay {

  /**
   * Gets moves by the player can make depending on if the wall is present.
   *
   * @param row    the current row of the room/cave
   * @param column the current column of the room/cave
   * @return the moves as set type
   */
  Set<Directions> getMoves(int row, int column);

  /**
   * The method is used to make a move by the player in the maze. It updates the players position as
   * well as the gold value if collected. It returns an object of the game type.
   *
   * @param move the move to be made
   * @return the messages
   */
  Messages makeMove(Directions move);

  /**
   * The methods returns true if winner ie if the player reaches the goal.
   *
   * @return the boolean to represent if the game is won or not
   */
  Boolean isWinner();

  /**
   * The method returns the players details which includes the golds count and the position.
   *
   * @return the details as a string
   */
  String playerDetails();

  /**
   * Gets the gold value a player holds.
   *
   * @return the gold value
   */
  double getPlayerGold();

  /**
   * Gets the player's row position.
   *
   * @return the row
   */
  int getPlayerPosRow();

  /**
   * Gets the player's column position.
   *
   * @return the column
   */
  int getPlayerPosColumn();

  /**
   * Gets the number of the room the player is present.
   *
   * @return the number
   */
  int getPlayerPosition();

  /**
   * Updates player's position on the maze.
   *
   * @param playerRow    the player row position
   * @param playerColumn the player column position
   */
  void updatePlayerPosition(int playerRow, int playerColumn);
}
