package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


import controller.ControllerGui;
import controller.IControllerGui;

/**
 * The type Maze view class is used for setting up the maze in the game with all the components and
 * elements required for making the maze view in the game. The class has methods giving the move
 * and shoot button also as part of the game.
 */
public class MazeView extends JFrame implements IView {

  private final JPanel panelMove;
  private final JPanel panelShoot;
  private final JButton move;
  private final JButton shoot;
  private JTextField shootField;
  private final JPanel[][] grid;
  private JButton north;
  private JButton south;
  private JButton east;
  private JButton west;
  private boolean actionFlag = false;
  private JLabel shootText;
  private final JLabel statusBar;
  private final GridBagConstraints constraints;

  /**
   * Instantiates a new Maze view new.
   *
   * @param rows       the rows
   * @param columns    the columns
   * @param controller the controller
   */
  public MazeView(int rows, int columns, ControllerGui controller) {
    this.setTitle("Maze");
    getContentPane().setLayout(new GridBagLayout());
    constraints = new GridBagConstraints();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(800, 800);
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    this.setResizable(false);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    // Pane for Maze
    JPanel panelMaze = new JPanel();
    constraints.gridy = 0;
    constraints.ipadx = 700;
    constraints.ipady = 700;
    constraints.fill = GridBagConstraints.BOTH;
    JScrollPane scroll = new JScrollPane(panelMaze);
    getContentPane().add(scroll, constraints);

    JPanel panelStatus = new JPanel();
    constraints.gridy = 1;
    constraints.ipadx = 0;
    constraints.ipady = 0;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    getContentPane().add(panelStatus, constraints);

    JPanel panelButton = new JPanel();
    constraints.fill = GridBagConstraints.HORIZONTAL;
    panelStatus.add(panelButton);

    // Maze build
    panelMaze.setLayout(new GridBagLayout());
    grid = new JPanel[rows][columns];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        grid[i][j] = new JPanel();
        grid[i][j].setLayout(new GridLayout(1, 1));
        grid[i][j].setVisible(true);
        grid[i][j].add(new JLabel());
        JLabel label = (JLabel) grid[i][j].getComponent(0);
        grid[i][j].setBackground(Color.BLACK);
        label.setBackground(Color.BLACK);
        label.setVisible(false);
        constraints.gridx = j;
        constraints.gridy = i;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        panelMaze.add(grid[i][j], constraints);
      }
    }
    panelMaze.setBackground(Color.BLACK);

    statusBar = new JLabel();
    panelStatus.add(statusBar);

    panelButton.setSize(500, 50);
    move = new JButton("Move");
    move.setActionCommand("Move");
    move.setSize(100, 50);

    shoot = new JButton("Shoot");
    shoot.setActionCommand("Shoot");
    shoot.setSize(100, 50);

    panelButton.add(move);
    panelButton.add(shoot);

    panelShoot = new JPanel();
    panelMove = new JPanel();

    shootButtons();
    moveButtons();

    panelMove.setVisible(false);
    panelShoot.setVisible(false);
    this.setResizable(true);
    this.setVisible(true);
  }

  @Override
  public void populateImage(int row, int column, ImageIcon imageIcon) {
    JLabel label = (JLabel) grid[row][column].getComponent(0);
    label.setIcon(imageIcon);
  }

  @Override
  public Icon getImage(int row, int column) {
    JLabel label = (JLabel) grid[row][column].getComponent(0);
    return label.getIcon();
  }

  @Override
  public void setVisibility(int row, int column) {
    JLabel label = (JLabel) grid[row][column].getComponent(0);
    label.setVisible(true);
  }

  @Override
  public void disableVisibility() {
    this.setVisible(false);
  }

  @Override
  public void disableVisibility(int row, int column) {
    JLabel label = (JLabel) grid[row][column].getComponent(0);
    label.setVisible(false);
  }

  private void moveButtons() {
    panelMove.add(north);
    panelMove.add(south);
    panelMove.add(east);
    panelMove.add(west);
    constraints.gridy = 2;
    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 0;
    getContentPane().add(panelMove, constraints);

  }

  @Override
  public void setStatus(String text, String imagePath) throws IOException {
    statusBar.setText(text);
    BufferedImage image = ImageIO.read(new File(imagePath));
    statusBar.setIcon((new ImageIcon(image)));
  }


  private void shootButtons() {
    north = new JButton("North");
    north.setActionCommand("North");
    north.setSize(100, 100);

    south = new JButton("South");
    south.setActionCommand("South");
    south.setSize(100, 100);

    east = new JButton("East");
    east.setActionCommand("East");
    east.setSize(100, 100);

    west = new JButton("West");
    west.setActionCommand("West");
    west.setSize(100, 100);

    shootField = new JTextField(4);
    shootField.setSize(100, 100);
    shootField.setLocation(400, 100);

    shootText = new JLabel("Enter the moves the arrow needs to move");

    panelShoot.add(north);
    panelShoot.add(south);
    panelShoot.add(east);
    panelShoot.add(west);
    panelShoot.add(shootText);
    panelShoot.add(shootField);
    constraints.gridy = 3;
    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 0;
    getContentPane().add(panelShoot, constraints);
  }


  @Override
  public void setFeatures(IControllerGui iControllerGui) {
    move.addActionListener(l -> {
      iControllerGui.moveVisibility();
      actionFlag = false;
      shootText.setVisible(false);
    });

    shoot.addActionListener(l -> {
      iControllerGui.shootVisibility();
      actionFlag = true;
      shootText.setVisible(true);
    });

    north.addActionListener(l -> {
      iControllerGui.move(north.getText().toLowerCase(), actionFlag, shootField.getText());
      shootField.setText("");
      moveShootVisibility();
    });

    south.addActionListener(l -> {
      iControllerGui.move(south.getText().toLowerCase(), actionFlag, shootField.getText());
      shootField.setText("");
      moveShootVisibility();
    });

    east.addActionListener(l -> {
      iControllerGui.move(east.getText().toLowerCase(), actionFlag, shootField.getText());
      shootField.setText("");
      moveShootVisibility();
    });

    west.addActionListener(l -> {
      iControllerGui.move(west.getText().toLowerCase(), actionFlag, shootField.getText());
      shootField.setText("");
      moveShootVisibility();
    });

  }


  @Override
  public void popUpBox(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  @Override
  public int gameEndPopUp(String message) {
    String[] options = {"End Game", "Restart previous game", "Start a new game"};
    int action = JOptionPane.showOptionDialog(this, message, "Game Over",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, options, options[0]);
    return action;
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }


  @Override
  public void remainingWallsFieldState(boolean flag) {
    throw new IllegalArgumentException("Shouldn't be here");
  }


  @Override
  public void moveVisibility() {
    shootField.setVisible(false);
    panelMove.setVisible(true);
    panelShoot.setVisible(true);
  }

  private void moveShootVisibility() {
    panelShoot.setVisible(false);
    panelMove.setVisible(false);
  }

  @Override
  public void shootVisibility() {
    shootField.setVisible(true);
    panelShoot.setVisible(true);
    panelMove.setVisible(true);
  }


}
