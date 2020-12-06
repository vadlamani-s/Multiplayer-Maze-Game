package maze;


import java.util.Set;

/**
 * The interface Game play extended contains all the for the extended game play functionality for
 * the Wampus game. The interface contains additional functionality added for this game for moving
 * the arrow and hints.
 */
public interface IGamePlayExtended extends IGamePlay {

  /**
   * The method is used to make a move by the arrow in the maze. It updates the arrow position
   * depending on the number of moves and returns the status if the arrow missed or hit.
   *
   * @param move          the move of enum type
   * @param numberOfMoves the number of moves the arrow has to move
   * @return the messages
   */
  Messages arrowMakeMove(Directions move, int numberOfMoves);

  /**
   * HintImpl method returns a string taking the current position of the player in terms of rows and
   * columns, the hint in terms of a string.
   *
   * @param row    the row coordinate of the room
   * @param column the column coordinate of the room
   * @return the string of the hints
   */
  StringBuilder hintImpl(int row, int column);


  /**
   * Gets player's move memory.
   *
   * @return set of moves by the player on the maze
   */
  Set<Integer> getMoveMemory();

}
