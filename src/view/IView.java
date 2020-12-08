package view;

import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;


import controller.Features;

/**
 * The interface View.
 */
public interface IView {

  /**
   * Sets features initialises all the action listeners in which are being used by the view in the
   * game. The features is an interface of the controller.
   *
   * @param features the features is an interface of controller
   */
  void setFeatures(Features features);

  /**
   * Remaining walls field state checks if the remaining field has to be visible or not based on
   * the type of maze being used.
   *
   * @param flag the flag is true if maze is imperfect else false
   */
  void remainingWallsFieldState(boolean flag);

  /**
   * Disable visibility disables the visibility of the view.
   */
  void disableVisibility();

  /**
   * Disable visibility disables the visibility of the view of each grid of maze taking in the
   * row and column number of the grid.
   *
   * @param row    the row number of the grid
   * @param column the column number of the grid
   */
  void disableVisibility(int row, int column);

  /**
   * Move visibility functions changes the visibility of the move panel in the maze depending on the
   * whether the move is clicked or not. The panel has buttons for choosing the direction.
   */
  void moveVisibility();

  /**
   * Shoot visibility functions changes the visibility of the shoot panel in the maze depending on
   * the whether the shoot button is clicked or not. The panel has buttons for choosing the
   * direction.
   */
  void shootVisibility();

  /**
   * Populate image method adds image to the maze based on the features present in the room in the
   * maze. The method takes in the location of the maze and the image that has to be displayed in
   * the room.
   *
   * @param row       the row number of the room
   * @param column    the column number of the room
   * @param imageIcon the image icon to be stored in the room
   */
  void populateImage(int row, int column, ImageIcon imageIcon);

  /**
   * Gets the image that is stored in the particular coordinates of the grid which represents the
   * room in the maze.
   *
   * @param row    the row number of the room
   * @param column the column number of the room
   * @return the image icon to be stored in the room
   */
  Icon getImage(int row, int column);

  /**
   * Pop up box, displays a message as a prompt on the screen. The method is used for displaying
   * events during the game play.
   *
   * @param message the message that has to be displayed on the screen
   */
  void popUpBox(String message);

  /**
   * Sets visibility.
   *
   * @param row    the row
   * @param column the column
   */
  void setVisibility(int row, int column);

  /**
   * Game end pop up int.
   *
   * @param message the message
   * @return the int
   */
  int gameEndPopUp(String message);

  /**
   * Reset focus.
   */
  void resetFocus();

  /**
   * Add key listener.
   *
   * @param listener the listener
   */
  void addKeyListener(KeyListener listener);

  /**
   * Sets status.
   *
   * @param text the text
   */
  void setStatus(String text, String imagePath) throws IOException;

}
