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
   * Sets features.
   *
   * @param features the features
   */
  void setFeatures(Features features);

  /**
   * Remaining walls field state.
   *
   * @param flag the flag
   */
  void remainingWallsFieldState(boolean flag);

  /**
   * Disable visibility.
   */
  void disableVisibility();

  /**
   * Disable visibility.
   *
   * @param row    the row
   * @param column the column
   */
  void disableVisibility(int row, int column);

  /**
   * Move visibility.
   */
  void moveVisibility();

  /**
   * Shoot visibility.
   */
  void shootVisibility();

  /**
   * Populate image.
   *
   * @param row       the row
   * @param column    the column
   * @param imageIcon the image icon
   */
  void populateImage(int row, int column, ImageIcon imageIcon);

  /**
   * Gets image.
   *
   * @param row    the row
   * @param column the column
   * @return the image
   */
  Icon getImage(int row, int column);

  /**
   * Pop up box.
   *
   * @param message the message
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
