package controller;

import java.io.IOException;

public interface Features {

  void processMazeType(String mazeType);

  void playerInGame(String playerInGame);

  void processInput(String inputs) throws IOException;

  void shootVisibility();

  void moveVisibility();

  void moveNorth(boolean actionFlag, String shootMoves);

  void moveSouth(boolean actionFlag, String shootMoves);

  void moveEast(boolean actionFlag, String shootMoves);

  void moveWest(boolean actionFlag, String shootMoves);

  void exit();

}
