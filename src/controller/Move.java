package controller;

import maze.Directions;
import maze.IGamePlayExtended;
import maze.Messages;

/**
 * The Move class has methods for moving the player based on the direction specified by the user.
 */
public class Move implements ICommand {

  /**
   * The Direction of Enum type. The direction the player has to move.
   */
  Directions direction;

  /**
   * Instantiates a new Move.
   *
   * @param move the move
   */
  public Move(Directions move) {
    this.direction = move;
  }

  @Override
  public Messages execute(IGamePlayExtended model) {
    return model.makeMove(direction);
  }

}
