package view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;


import controller.ControllerGui;
import controller.Features;

/**
 * The type Form view.
 */
public class FormView extends JFrame implements IView {


  private final JTextField mazeSizeRow;

  private final JTextField mazeSizeColumn;

  private final JComboBox<String> mazeSelection;

  private final JLabel wallLabel;

  private final JTextField wallsField;

  private final JTextField batField;

  private final JTextField pitField;

  private final JComboBox<String> playerSelection;

  private final JRadioButton wrappingOptionTrue;

  private final JRadioButton wrappingOptionFalse;

  private final JButton submit;

  private final JButton cancelButton;


  /**
   * Instantiates a new Form view.
   *
   * @param title      the title
   * @param controller the controller
   */
  public FormView(String title, ControllerGui controller) {
    super(title);

    setSize(475, 400);
    setLocation(200, 200);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Read about it
    this.setLayout(new FlowLayout());
    setResizable(false);
    Container container = getContentPane();
    container.setLayout(null);

    // Create banner/label
    JLabel heading = new JLabel("Lets kill the Wumpus !!!!");
    heading.setFont(new Font("Didot", Font.BOLD, 20));
    heading.setSize(500, 25);
    heading.setLocation(125, 25);
    this.add(heading);

    //Maze Creation Options, rows and columns
    JLabel rowLabel = new JLabel("Input the Rows in the Maze");
    rowLabel.setFont(new Font("Didot", Font.PLAIN, 15));
    rowLabel.setSize(500, 25);
    rowLabel.setLocation(50, 75);
    this.add(rowLabel);

    JLabel columnLabel = new JLabel("Input the Columns in the Maze");
    columnLabel.setFont(new Font("Didot", Font.PLAIN, 15));
    columnLabel.setSize(500, 25);
    columnLabel.setLocation(50, 100);
    this.add(columnLabel);

    mazeSizeRow = new JTextField();
    mazeSizeRow.setFont(new Font("Didot", Font.PLAIN, 15));
    mazeSizeRow.setSize(100, 25);
    mazeSizeRow.setLocation(300, 75);
    this.add(mazeSizeRow);

    mazeSizeColumn = new JTextField();
    mazeSizeColumn.setFont(new Font("Didot", Font.PLAIN, 15));
    mazeSizeColumn.setSize(100, 25);
    mazeSizeColumn.setLocation(300, 100);
    this.add(mazeSizeColumn);

    // Maze Kind -> perfect or imperfect
    JLabel mazeType = new JLabel("Choose the type of Maze");
    mazeType.setFont(new Font("Arial", Font.PLAIN, 15));
    mazeType.setSize(500, 25);
    mazeType.setLocation(50, 125);
    this.add(mazeType);

    String[] mazeList = {" ", "perfect", "imperfect"};
    mazeSelection = new JComboBox<>(mazeList);
    mazeSelection.setEditable(true);
    mazeSelection.setSize(100, 25);
    mazeSelection.setLocation(300, 125);
    this.add(mazeSelection);

    // Remaining Walls
    wallLabel = new JLabel("Input the Remaining Walls");
    wallLabel.setFont(new Font("Didot", Font.PLAIN, 15));
    wallLabel.setSize(500, 25);
    wallLabel.setLocation(50, 150);
    this.add(wallLabel);

    wallsField = new JTextField();
    wallsField.setFont(new Font("Didot", Font.PLAIN, 15));
    wallsField.setSize(100, 25);
    wallsField.setLocation(300, 150);
    this.add(wallsField);

    wallLabel.setVisible(false);
    wallsField.setVisible(false);


    // Bat percentage
    JLabel batLabel = new JLabel("Input the Bat Percentage");
    batLabel.setFont(new Font("Didot", Font.PLAIN, 15));
    batLabel.setSize(500, 25);
    batLabel.setLocation(50, 175);
    this.add(batLabel);

    batField = new JTextField();
    batField.setFont(new Font("Didot", Font.PLAIN, 15));
    batField.setSize(100, 25);
    batField.setLocation(300, 175);
    this.add(batField);

    // Pit Percentage
    JLabel pitLabel = new JLabel("Input the Pit Percentage");
    pitLabel.setFont(new Font("Didot", Font.PLAIN, 15));
    pitLabel.setSize(500, 25);
    pitLabel.setLocation(50, 200);
    this.add(pitLabel);

    pitField = new JTextField();
    pitField.setFont(new Font("Didot", Font.PLAIN, 15));
    pitField.setSize(100, 25);
    pitField.setLocation(300, 200);
    this.add(pitField);

    // Player option
    JLabel playerLabel = new JLabel("Choose the number of Players");
    playerLabel.setFont(new Font("Didot", Font.PLAIN, 15));
    playerLabel.setSize(500, 25);
    playerLabel.setLocation(50, 225);
    this.add(playerLabel);

    String[] playerList = {" ", "1-player", "2-player"};
    playerSelection = new JComboBox<>(playerList);
    playerSelection.setEditable(true);
    playerSelection.setSize(100, 25);
    playerSelection.setLocation(300, 225);
    this.add(playerSelection);

    // Wrapping Option
    JLabel wrappingLabel = new JLabel("Choose the type of Maze");
    wrappingLabel.setFont(new Font("Didot", Font.PLAIN, 15));
    wrappingLabel.setSize(500, 25);
    wrappingLabel.setLocation(50, 260);
    this.add(wrappingLabel);

    wrappingOptionTrue = new JRadioButton();
    wrappingOptionFalse = new JRadioButton();
    wrappingOptionTrue.setText("True");
    wrappingOptionFalse.setText("False");
    wrappingOptionTrue.setSize(80, 50);
    wrappingOptionFalse.setSize(80, 50);
    wrappingOptionTrue.setLocation(250, 250);
    wrappingOptionFalse.setLocation(350, 250);
    ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(wrappingOptionFalse);
    buttonGroup.add(wrappingOptionTrue);
    this.add(wrappingOptionTrue);
    this.add(wrappingOptionFalse);


    // Confirm Button
    submit = new JButton("Confirm");
    submit.setActionCommand("Confirm Button");
    submit.setSize(100, 25);
    submit.setLocation(100, 300);
    this.add(submit);

    // Cancel button
    cancelButton = new JButton("Cancel");
    cancelButton.setActionCommand("Cancel button");
    cancelButton.setSize(100, 25);
    cancelButton.setLocation(250, 300);
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
  public void disableVisibility(int row, int column) {
    throw new IllegalArgumentException("Shouldn't be here");
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
    JOptionPane.showMessageDialog(this,
            message);
  }

  @Override
  public void setVisibility(int row, int column) {
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
  public void setStatus(String text, String imagePath) {
    throw new IllegalArgumentException("Shouldn't be here");
  }

  @Override
  public void setFeatures(Features features) {
    mazeSelection.addItemListener(l -> features.processMazeType((String) ((JComboBox)
            l.getSource()).getSelectedItem()));

    playerSelection.addItemListener(l -> features.playerInGame((String) ((JComboBox)
            l.getSource()).getSelectedItem()));

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
