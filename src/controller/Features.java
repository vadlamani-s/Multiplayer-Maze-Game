package controller;

import java.io.IOException;

public interface Features {

  void processMazeType(String mazeType);

  void playerInGame(String playerInGame);

  void processInput(String inputs) throws IOException;

  void shootVisibility();

  void moveVisibility();

  void move(String move, boolean actionFlag, String shootMoves);

  void exit();

}
