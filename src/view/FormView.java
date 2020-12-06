package view;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import controller.ControllerGUI;
import controller.Features;

public class FormView extends JFrame implements IView {


  Container container;
  JLabel heading;
  JLabel rowLabel;
  JLabel columnLabel;

  JTextField mazeSizeRow;
  JTextField mazeSizeColumn;
  JLabel mazeType;
  JComboBox<String> mazeSelection;
  JLabel wallLabel;
  JTextField wallsField;
  JLabel batLabel;
  JTextField batField;
  JTextField pitField;
  JComboBox<String> playerSelection;
  JRadioButton wrappingOptionTrue;
  JRadioButton wrappingOptionFalse;

  JButton submit;
  JButton cancelButton;
  ControllerGUI controller;


  public FormView(String title, ControllerGUI controller) {
    super(title);
    this.controller = controller;

    setSize(550, 400);
    setLocation(200, 200);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Read about it
    this.setLayout(new FlowLayout());
    setResizable(false);
    container = getContentPane();
    container.setLayout(null);

    // Create banner/label
    heading = new JLabel("Lets kill the Wumpus!!!!");
    heading.setFont(new Font("Arial", Font.PLAIN, 20));
    heading.setSize(500, 25);
    heading.setLocation(175, 25);
    this.add(heading);

    //Maze Creation Options, rows and columns
    rowLabel = new JLabel("Rows of the maze");
    rowLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    rowLabel.setSize(500, 25);
    rowLabel.setLocation(50, 75);
    this.add(rowLabel);

    columnLabel = new JLabel("Columns of the maze");
    columnLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    columnLabel.setSize(500, 25);
    columnLabel.setLocation(50, 100);
    this.add(columnLabel);

    mazeSizeRow = new JTextField();
    mazeSizeRow.setFont(new Font("Arial", Font.PLAIN, 10));
    mazeSizeRow.setSize(50, 25);
    mazeSizeRow.setLocation(250, 75);
    this.add(mazeSizeRow);

    mazeSizeColumn = new JTextField();
    mazeSizeColumn.setFont(new Font("Arial", Font.PLAIN, 10));
    mazeSizeColumn.setSize(50, 25);
    mazeSizeColumn.setLocation(250, 100);
    this.add(mazeSizeColumn);

    // Maze Kind -> perfect or imperfect
    mazeType = new JLabel("Choose the type of maze");
    mazeType.setFont(new Font("Arial", Font.PLAIN, 15));
    mazeType.setSize(500, 25);
    mazeType.setLocation(50, 125);
    this.add(mazeType);

    String[] mazeList = {" ", "perfect", "imperfect"};
    mazeSelection = new JComboBox<>(mazeList);
    mazeSelection.setEditable(true);
    mazeSelection.setSize(100, 25);
    mazeSelection.setLocation(250, 125);
    this.add(mazeSelection);

    // Remaining Walls
    wallLabel = new JLabel("Remaining Walls");
    wallLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    wallLabel.setSize(500, 25);
    wallLabel.setLocation(50, 150);
    this.add(wallLabel);

    wallsField = new JTextField();
    wallsField.setFont(new Font("Arial", Font.PLAIN, 10));
    wallsField.setSize(100, 25);
    wallsField.setLocation(250, 150);
    this.add(wallsField);

    wallLabel.setVisible(false);
    wallsField.setVisible(false);


    // Bat percentage
    batLabel = new JLabel("Bat percentage");
    batLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    batLabel.setSize(500, 25);
    batLabel.setLocation(50, 175);
    this.add(batLabel);

    batField = new JTextField();
    batField.setFont(new Font("Arial", Font.PLAIN, 10));
    batField.setSize(100, 25);
    batField.setLocation(250, 175);
    this.add(batField);

    // Pit Percentage
    JLabel pitLabel = new JLabel("Pit percentage");
    pitLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    pitLabel.setSize(500, 25);
    pitLabel.setLocation(50, 200);
    this.add(pitLabel);

    pitField = new JTextField();
    pitField.setFont(new Font("Arial", Font.PLAIN, 10));
    pitField.setSize(100, 25);
    pitField.setLocation(250, 200);
    this.add(pitField);

    // Player option
    JLabel playerLabel = new JLabel("Choose Players");
    playerLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    playerLabel.setSize(500, 25);
    playerLabel.setLocation(50, 225);
    this.add(playerLabel);

    String[] playerList = {" ", "1-player", "2-player"};
    playerSelection = new JComboBox<>(playerList);
    playerSelection.setEditable(true);
    playerSelection.setSize(100, 25);
    playerSelection.setLocation(250, 225);
    this.add(playerSelection);

    // Wrapping Option
    JLabel wrappingLabel = new JLabel("Choose if wrapping");
    wrappingLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    wrappingLabel.setSize(500, 25);
    wrappingLabel.setLocation(50, 260);
    this.add(wrappingLabel);

    wrappingOptionTrue = new JRadioButton();
    wrappingOptionFalse = new JRadioButton();
    wrappingOptionTrue.setText("True");
    wrappingOptionFalse.setText("False");
    wrappingOptionTrue.setSize(120, 50);
    wrappingOptionFalse.setSize(120, 50);
    wrappingOptionTrue.setLocation(250, 250);
    wrappingOptionFalse.setLocation(400, 250);
    ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(wrappingOptionFalse);
    buttonGroup.add(wrappingOptionTrue);
    this.add(wrappingOptionTrue);
    this.add(wrappingOptionFalse);


    // Confirm Button
    submit = new JButton("Confirm");
    submit.setActionCommand("Confirm Button");
    submit.setSize(100, 25);
    submit.setLocation(150, 300);
    this.add(submit);

    // Cancel button
    cancelButton = new JButton("Cancel");
    cancelButton.setActionCommand("Cancel button");
    cancelButton.setSize(100, 25);
    cancelButton.setLocation(300, 300);
    this.add(cancelButton);

    this.setVisible(true);
  }


  @Override
  public void remainingWallsFieldState(boolean flag) {
    if (flag) {
      wallLabel.setVisible(true);
      wallsField.setVisible(true);
    } else {
      wallLabel.setVisible(false);
      wallsField.setVisible(false);
      wallsField.setText("");
    }
  }

  @Override
  public void disableVisibility() {
    this.setVisible(false);
  }

  @Override
  public void moveVisibility() {
    throw new IllegalArgumentException("Shouldn't be here");
  }

  @Override
  public void shootVisibility() {
    throw new IllegalArgumentException("Shouldn't be here");
  }

  @Override
  public void populateImage(int row, int column, ImageIcon imageIcon) {
    throw new IllegalArgumentException("Shouldn't be here");
  }

  @Override
  public Icon getImage(int row, int column) {
    throw new IllegalArgumentException("Shouldn't be here");
  }

  @Override
  public void popUpBox(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  @Override
  public void setVisibility(int row, int column) {
    throw new IllegalArgumentException("Shouldn't be here");
  }

  @Override
  public void disableVisibility(int row, int column) {
    throw new IllegalArgumentException("Shouldn't be here");
  }

  @Override
  public int gameEndPopUp(String message) {
    throw new IllegalArgumentException("Shouldn't be here");
  }

  @Override
  public void resetFocus() {
    throw new IllegalArgumentException("Shouldn't be here");
  }

  @Override
  public void setStatus(String text) {
    throw new IllegalArgumentException("Shouldn't be here");
  }

  @Override
  public void setFeatures(Features features) {
    mazeSelection.addItemListener(l -> features.
            processMazeType((String) ((JComboBox) l.getSource()).getSelectedItem()));

    playerSelection.addItemListener(l -> features.
            playerInGame((String) ((JComboBox) l.getSource()).getSelectedItem()));

    submit.addActionListener(l -> {
      try {
        features.processInput(processForm());
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    cancelButton.addActionListener(l -> features.exit());
  }

  private String processForm() {
    StringBuilder inputs = new StringBuilder();
    inputs.append(mazeSizeRow.getText()).append(",");
    inputs.append(mazeSizeColumn.getText()).append(",");
    inputs.append(wallsField.getText()).append(",");
    inputs.append(batField.getText()).append(",");
    inputs.append(pitField.getText()).append(",");
    if (wrappingOptionTrue.isSelected()) {
      inputs.append("true");
    }
    if (wrappingOptionFalse.isSelected()) {
      inputs.append("false");
    }
    return inputs.toString();
  }

}
