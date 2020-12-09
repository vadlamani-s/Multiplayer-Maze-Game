package controller;

import java.io.IOException;

/**
 * The interface IControllerGui has all the methods required to run a controller. The controller
 * acts as the brain of project delegating work between the view and the model. This interface
 * specifically has methods for making running the game with GUI.
 */
public interface IControllerGui {

  /**
   * Process maze type method process the maze type field from the form which takes in the initial
   * data for building the maze.
   *
   * @param mazeType the maze type which is of string type
   */
  void processMazeType(String mazeType);

  /**
   * Player in game method processes the number of players field from the form which is used for
   * building the game.
   *
   * @param playerInGame the players in the game
   */
  void playerInGame(String playerInGame);

  /**
   * Process input processes all the inputs from the view required for building a maze.Once the all
   * the inputs are valid the maze object is built and players are deployed.
   *
   * @param inputs the inputs are taken as a String
   * @throws IOException the io exception
   */
  void processInput(String inputs) throws IOException;

  /**
   * Shoot visibility is used for setting the move panel when clicked upon and gives all the
   * directions that can be shot as well as the moves the arrows have to be shot.
   */
  void shootVisibility();

  /**
   * Move visibility is used for setting the move panel when clicked upon and gives all the
   * directions that can be moved.
   */
  void moveVisibility();

  /**
   * The move method is used for dedicating the task of making a move or shooting an arrow based on
   * the button clicks. The action flag is used for setting move and shoot while true being shoot
   * and move for false.
   *
   * @param move       the move to be made by the player
   * @param actionFlag the action flag for deciding between shoot and move
   * @param shootMoves the shoot moves for arrow
   */
  void move(String move, boolean actionFlag, String shootMoves);

  /**
   * Exit method is used for exiting the program at the end of the game.
   */
  void exit();

}
