package view;

import java.awt.*;
import javax.swing.*;

import controller.ControllerGUI;
import controller.Features;

public class MazeView extends JFrame implements IView {

  private JPanel panel;
  private JPanel panelButton;
  private JPanel panelMove;
  private JPanel panelShoot;
  private JButton move;
  private JButton shoot;
  private JTextField shootField;
  private final JLabel[][] grid;
  private JButton north;
  private JButton south;
  private JButton east;
  private JButton west;
  private boolean actionFlag = false;
  private JLabel shootText;


  public MazeView(int rows, int columns, ControllerGUI controller) {
    this.setTitle("Maze");
    getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500, 500);

    // Pane for Maze
    panel = new JPanel();
    panel.setLayout(new GridLayout(rows, columns, 0, 0));
    grid = new JLabel[rows][columns];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        grid[i][j] = new JLabel();
        panel.add(grid[i][j]);
        grid[i][j].setVisible(false);
      }
    }

    JScrollPane scroll = new JScrollPane(panel);

    getContentPane().add(scroll);
    panel.setBackground(Color.WHITE);

    panelButton = new JPanel();
    panelButton.setSize(500, 200);
    move = new JButton("Move");
    move.setActionCommand("Move");
    move.setSize(100, 100);

    shoot = new JButton("Shoot");
    shoot.setActionCommand("Shoot");
    shoot.setSize(100, 100);

    panelButton.add(move);
    panelButton.add(shoot);
    getContentPane().add(panelButton);

    panelShoot = new JPanel();
    panelMove = new JPanel();

    shootButtons();
    moveButtons();

    panelMove.setVisible(false);
    panelShoot.setVisible(false);
    this.setResizable(true);
//    System.out.println(Arrays.toString(this.getContentPane().getComponents()));
//    this.getContentPane().getComponent(1);
    this.setVisible(true);
  }

  @Override
  public void populateImage(int row, int column, ImageIcon imageIcon) {
    grid[row][column].setIcon(imageIcon);
  }

  @Override
  public Icon getImage(int row, int column) {
    return grid[row][column].getIcon();
  }


  public void setVisibility(int row, int column) {
    grid[row][column].setVisible(true);
  }

  public void disableVisibility(int row, int column) {
    grid[row][column].setVisible(false);
  }

  private void moveButtons() {
    panelMove.add(north);
    panelMove.add(south);
    panelMove.add(east);
    panelMove.add(west);
    getContentPane().add(panelMove);
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

    getContentPane().add(panelShoot);
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
      features.moveNorth(actionFlag, shootField.getText());
      shootField.setText("");
      moveShootVisibility();
    });

    south.addActionListener(l -> {
      features.moveSouth(actionFlag, shootField.getText());
      shootField.setText("");
      moveShootVisibility();
    });

    east.addActionListener(l -> {
      features.moveEast(actionFlag, shootField.getText());
      shootField.setText("");
      moveShootVisibility();
    });

    west.addActionListener(l -> {
      features.moveWest(actionFlag, shootField.getText());
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
