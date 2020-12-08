package view;

import java.awt.*;

import javax.swing.*;

import controller.ControllerGUI;
import controller.Features;

public class MazeViewNew extends JFrame implements IView {

  private JPanel panelMaze;
  private JPanel panelButton;
  private JPanel panelMove;
  private JPanel panelShoot;
  private JButton move;
  private JButton shoot;
  private JTextField shootField;
  private final JPanel[][] grid;
  private JButton north;
  private JButton south;
  private JButton east;
  private JButton west;
  private boolean actionFlag = false;
  private JLabel shootText;
  private JLabel statusBar;
  private JPanel panelStatus;
  private GridBagConstraints constraints;

  public MazeViewNew(int rows, int columns, ControllerGUI controller, String path) {
    this.setTitle("Maze");
    getContentPane().setLayout(new GridBagLayout());
    constraints = new GridBagConstraints();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(800, 800);

    // Pane for Maze
    panelMaze = new JPanel();
    constraints.gridy = 0;
    constraints.ipadx = 700;
    constraints.ipady = 700;
    constraints.fill = GridBagConstraints.BOTH;
    JScrollPane scroll = new JScrollPane(panelMaze);
    getContentPane().add(scroll, constraints);

    panelStatus = new JPanel();
    constraints.gridy = 1;
    constraints.ipadx = 0;
    constraints.ipady = 0;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    getContentPane().add(panelStatus, constraints);

    panelButton = new JPanel();
//    constraints.gridy = 3;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    panelStatus.add(panelButton);
//    getContentPane().add(panelButton, constraints);

    // Maze build
    panelMaze.setLayout(new GridBagLayout());
    grid = new JPanel[rows][columns];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        grid[i][j] = new JPanel();
        grid[i][j].setLayout(new GridLayout(1, 1));
        grid[i][j].setVisible(true);
        grid[i][j].add(new JLabel());
        JLabel label =  (JLabel) grid[i][j].getComponent(0);
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
    JLabel label =  (JLabel) grid[row][column].getComponent(0);
    label.setIcon(imageIcon);
  }

  @Override
  public Icon getImage(int row, int column) {
    JLabel label =  (JLabel) grid[row][column].getComponent(0);
    return label.getIcon();
  }


  public void setVisibility(int row, int column) {
    JLabel label =  (JLabel) grid[row][column].getComponent(0);
    label.setVisible(true);
//    grid[row][column].setVisible(true);
  }

  public void disableVisibility(int row, int column) {
    JLabel label =  (JLabel) grid[row][column].getComponent(0);
    label.setVisible(false);
//    grid[row][column].setVisible(false);

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


//    getContentPane().add(panelMove);
  }

  public void setStatus(String text) {
    statusBar.setText(text);
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

    shootText = new JLabel("Enter the moves");

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
//    getContentPane().add(panelShoot);
  }


  @Override
  public void setFeatures(Features features) {
    move.addActionListener(l -> {
      features.moveVisibility();
      actionFlag = false;
      shootText.setVisible(false);
    });

    shoot.addActionListener(l -> {
      features.shootVisibility();
      actionFlag = true;
      shootText.setVisible(true);
    });

    north.addActionListener(l -> {
      features.move(north.getText().toLowerCase(), actionFlag, shootField.getText());
      shootField.setText("");
      moveShootVisibility();
    });

    south.addActionListener(l -> {
      features.move(south.getText().toLowerCase(), actionFlag, shootField.getText());
      shootField.setText("");
      moveShootVisibility();
    });

    east.addActionListener(l -> {
      features.move(east.getText().toLowerCase(), actionFlag, shootField.getText());
      shootField.setText("");
      moveShootVisibility();
    });

    west.addActionListener(l -> {
      features.move(west.getText().toLowerCase(), actionFlag, shootField.getText());
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
  public void disableVisibility() {
    this.setVisible(false);
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
