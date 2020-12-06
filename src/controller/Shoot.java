package controller;

import maze.Directions;
import maze.IGamePlayExtended;
import maze.Messages;


/**
 * The Shoot class has methods for shooting the arrow based on the direction specified by the user.
 */
public class Shoot implements ICommand {

  /**
   * The Direction of Enum type. The direction the player has to move.
   */
  Directions direction;
  /**
   * The Number of moves.
   */
  int numberOfMoves;

  /**
   * Instantiates a new Shoot.
   *
   * @param directions    the directions
   * @param numberOfMoves the number of moves
   */
  public Shoot(Directions directions, int numberOfMoves) {
    this.direction = directions;
    this.numberOfMoves = numberOfMoves;
  }

  @Override
  public Messages execute(IGamePlayExtended model) {
    return model.arrowMakeMove(direction, numberOfMoves);
  }
}
