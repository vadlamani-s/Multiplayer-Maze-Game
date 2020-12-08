package controller;

import java.io.IOException;

/**
 * The interface Features.
 */
public interface Features {

  /**
   * Process maze type.
   *
   * @param mazeType the maze type
   */
  void processMazeType(String mazeType);

  /**
   * Player in game.
   *
   * @param playerInGame the player in game
   */
  void playerInGame(String playerInGame);

  /**
   * Process input.
   *
   * @param inputs the inputs
   * @throws IOException the io exception
   */
  void processInput(String inputs) throws IOException;

  /**
   * Shoot visibility.
   */
  void shootVisibility();

  /**
   * Move visibility.
   */
  void moveVisibility();

  /**
   * Move.
   *
   * @param move       the move
   * @param actionFlag the action flag
   * @param shootMoves the shoot moves
   */
  void move(String move, boolean actionFlag, String shootMoves);

  /**
   * Exit.
   */
  void exit();

}
