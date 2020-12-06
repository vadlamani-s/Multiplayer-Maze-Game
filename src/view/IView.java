package view;

import javax.swing.*;

import controller.Features;

public interface IView {

  void setFeatures(Features features);

  void remainingWallsFieldState(boolean flag);

  void disableVisibility();

  void moveVisibility();

  void shootVisibility();

  void populateImage(int row, int column, ImageIcon imageIcon);

  Icon getImage(int row, int column);

  void popUpBox(String message);

  void setVisibility(int row, int column);

  void disableVisibility(int row, int column);

  int gameEndPopUp(String message);

}
