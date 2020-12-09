package view;

import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.IControllerGui;

/**
 * The type Mock maze view class is used for creating a mock class for testing the view along with
 * the controller.
 */
public class MockMazeView implements IView {


  private final StringBuilder log;
  private final StringBuilder out;
  private final int outInt;
  private final String path = Paths.get("").toAbsolutePath().toString() + "\\" + "res\\"
          + "hunt-the-wumpus-images\\hunt-the-wumpus-images\\";

  /**
   * Instantiates a new Mock maze view.
   *
   * @param log    the log
   * @param out    the out
   * @param outInt the out int
   */
  public MockMazeView(StringBuilder log, StringBuilder out, int outInt) {
    this.log = log;
    this.out = out;
    this.outInt = outInt;
  }


  @Override
  public void setFeatures(IControllerGui iControllerGui) {
    this.log.append(iControllerGui);
  }

  @Override
  public void remainingWallsFieldState(boolean flag) {
    this.log.append(flag).append("\n");
  }

  @Override
  public void disableVisibility() {
    return;
  }

  @Override
  public void disableVisibility(int row, int column) {
    this.log.append(row).append("\n").append(column).append("\n");
  }

  @Override
  public void moveVisibility() {
    this.log.append("this method was called\n");
  }

  @Override
  public void shootVisibility() {
    this.log.append("this method was accessed\n");
  }

  @Override
  public void populateImage(int row, int column, ImageIcon imageIcon) {
    this.log.append(row).append("\n").append(column).append("\n").append(imageIcon).append("\n");
  }

  @Override
  public Icon getImage(int row, int column) throws IOException {
    this.log.append(row).append("\n").append(column).append("\n");
    this.out.append("attaching Icon for overlaying");
    BufferedImage image = ImageIO.read(new File(path + "black.png"));
    JLabel label = new JLabel(new ImageIcon(image));
    return label.getIcon();
  }

  @Override
  public void popUpBox(String message) {
    this.log.append(message).append('\n');
  }

  @Override
  public void setVisibility(int row, int column) {
    this.log.append(row).append("\n").append(column).append("\n");
  }

  @Override
  public int gameEndPopUp(String message) {
    this.log.append(message).append("\n");
    return outInt;
  }

  @Override
  public void resetFocus() {
    return;
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    this.log.append("listener added").append("\n");
  }

  @Override
  public void setStatus(String text, String imagePath) throws IOException {
    this.log.append(text).append("\n").append(imagePath).append("\n");
  }
}
