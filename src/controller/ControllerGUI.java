package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.*;

import maze.AbstractIMaze;
import maze.Directions;
import maze.GamePlayMultiPlayer;
import maze.IGamePlayMultiplayer;
import maze.IMaze;
import maze.Messages;
import maze.NonPerfectMaze;
import maze.PerfectMaze;
import view.FormView;
import view.IView;
import view.MazeView;

public class ControllerGUI implements Features {

  private IView view;
  private IGamePlayMultiplayer model;
  private String mazeType;
  private int playerOption;
  private int rows;
  private int columns;
  private int remainingWalls = 0;
  private int batPercentage;
  private int pitPercentage;
  private boolean wrapping;
  private final String path = "C:/Users/Satyanarayana/Documents/CS5010/projects/HW6/"
          + "hunt-the-wumpus-images/hunt-the-wumpus-images/";
  private Map<Set<String>, String> textImageMap;
  private int seed;


  public ControllerGUI() {
    Random random = new Random();
    seed = random.nextInt();
    AbstractIMaze.setRandom(seed);
    this.model = null;
    textImageMap = new HashMap<>();
    populateMap(textImageMap);
  }


  private void populateMap(Map<Set<String>, String> textImageMap) {
    textImageMap.put(new HashSet<>() {{
      add("bat");
    }}, "bats.png");
    textImageMap.put(new HashSet<>() {{
      add("black");
    }}, "black.png");
    textImageMap.put(new HashSet<>() {{
      add("breeze");
    }}, "breeze.png");
    textImageMap.put(new HashSet<>() {{
      add("east");
    }}, "E.png");
    textImageMap.put(new HashSet<>() {{
      add("east");
      add("west");
    }}, "EW.png");
    textImageMap.put(new HashSet<>() {{
      add("north");
    }}, "N.png");
    textImageMap.put(new HashSet<>() {{
      add("north");
      add("east");
    }}, "NE.png");
    textImageMap.put(new HashSet<>() {{
      add("north");
      add("east");
      add("west");
    }}, "NEW.png");
    textImageMap.put(new HashSet<>() {{
      add("north");
      add("south");
    }}, "NS.png");
    textImageMap.put(new HashSet<>() {{
      add("north");
      add("south");
      add("east");
    }}, "NSE.png");
    textImageMap.put(new HashSet<>() {{
                       add("north");
                       add("south");
                       add("east");
                       add("west");
                     }},
            "NSEW.png");
    textImageMap.put(new HashSet<>() {{
      add("north");
      add("south");
      add("west");
    }}, "NSW.png");
    textImageMap.put(new HashSet<>() {{
      add("north");
      add("west");
    }}, "NW.png");
    textImageMap.put(new HashSet<>() {{
      add("pit");
    }}, "pit.png");
    textImageMap.put(new HashSet<>() {{
      add("player");
    }}, "player.png");
    textImageMap.put(new HashSet<>() {{
      add("south");
    }}, "S.png");
    textImageMap.put(new HashSet<>() {{
      add("south");
      add("east");
    }}, "SE.png");
    textImageMap.put(new HashSet<>() {{
      add("west");
      add("south");
      add("east");
    }}, "SEW.png");
    textImageMap.put(new HashSet<>() {{
      add("stench");
    }}, "stench.png");
    textImageMap.put(new HashSet<>() {{
      add("south");
      add("west");
    }}, "SW.png");
    textImageMap.put(new HashSet<>() {{
      add("thief");
    }}, "thief.png");
    textImageMap.put(new HashSet<>() {{
      add("west");
    }}, "W.png");
    textImageMap.put(new HashSet<>() {{
      add("wumpus");
    }}, "wumpus.png");
  }


  public void setView(IView v) {
    view = v;
    view.setFeatures(this);
  }

  // Convert String to integer
  private int getShootMoves(String moves) {
    return Integer.parseInt(moves);
  }

  private void messageCheck(Messages message) {
    int action;
    if (message == Messages.BAT) {
      throw new IllegalArgumentException(Messages.getMessages(Messages.BAT));
    }
    if (message == Messages.PIT) {
      action = view.gameEndPopUp(Messages.getMessages(Messages.PIT));
      endPopUpImpl(action);
    }
    if (message == Messages.ARROWMISS) {
      throw new IllegalArgumentException(Messages.getMessages(Messages.ARROWMISS));
    }
    if (message == Messages.WAMPUSMISS) {
      throw new IllegalArgumentException(Messages.getMessages(Messages.WAMPUSMISS));
    }
    if (message == Messages.NOTBAT) {
      throw new IllegalArgumentException(Messages.getMessages(Messages.NOTBAT));
    }
    if (message == Messages.NOARROWS) {
      action = view.gameEndPopUp(Messages.getMessages(Messages.NOARROWS));
      endPopUpImpl(action);
    }
    if (message == Messages.GAMEOVER) {
      action = view.gameEndPopUp(Messages.getMessages(Messages.GAMEOVER));
      endPopUpImpl(action);
    }
    if (message == Messages.WAMPUS) {
      action = view.gameEndPopUp(Messages.getMessages(Messages.WAMPUS));
      endPopUpImpl(action);
    }
    if (message == Messages.WIN) {
      action = view.gameEndPopUp(Messages.getMessages(Messages.WIN));
      endPopUpImpl(action);
    }
  }

  private void endPopUpImpl(int choice) {
    try {
      switch (choice) {
        case 0:
          System.exit(0);
          break;
        case 1:
          AbstractIMaze.setRandom(seed);
          setModel();
          break;
        case 2:
          view.disableVisibility();
//          ControllerGUI controllerNew = new ControllerGUI();
          view = new FormView("Welcome to the Game", this);
          this.setView(view);
          break;
        default:
          throw new IllegalArgumentException("Incorrect Choice");
      }
    } catch (Exception e) {
      view.popUpBox(e.getMessage());
    }
  }


  // actionFlag -> false for move; actionFlag -> true for shoot
  @Override
  public void moveNorth(boolean actionFlag, String shootMoves) {
    Messages messages;
    try {
      int number = shootMoves.equals("") ? 0 : getShootMoves(shootMoves);
      if (actionFlag) {
        messages = model.arrowMakeMove(Directions.NORTH, number);
        model.changePlayer();
      } else {
        messages = model.makeMove(Directions.NORTH);
        playGame();
      }
      messageCheck(messages);
    } catch (Exception e) {
      view.popUpBox(e.getMessage());
    }
  }

  // actionFlag -> false for move; actionFlag -> true for shoot
  @Override
  public void moveSouth(boolean actionFlag, String shootMoves) {
    Messages messages;
    try {
      int number = shootMoves.equals("") ? 0 : getShootMoves(shootMoves);
      if (actionFlag) {
        messages = model.arrowMakeMove(Directions.SOUTH, number);
        model.changePlayer();
      } else {
        messages = model.makeMove(Directions.SOUTH);
        playGame();
      }
      messageCheck(messages);
    } catch (Exception e) {
      view.popUpBox(e.getMessage());
    }
  }

  // actionFlag -> false for move; actionFlag -> true for shoot
  @Override
  public void moveEast(boolean actionFlag, String shootMoves) {
    Messages messages;
    try {
      int number = shootMoves.equals("") ? 0 : getShootMoves(shootMoves);
      if (actionFlag) {
        messages = model.arrowMakeMove(Directions.EAST, number);
        model.changePlayer();
      } else {
        messages = model.makeMove(Directions.EAST);
        playGame();
      }
      messageCheck(messages);
    } catch (Exception e) {
      view.popUpBox(e.getMessage());
    }
  }


  // actionFlag -> false for move; actionFlag -> true for shoot
  @Override
  public void moveWest(boolean actionFlag, String shootMoves) {
    Messages messages;
    try {
      int number = shootMoves.equals("") ? 0 : getShootMoves(shootMoves);
      if (actionFlag) {
        messages = model.arrowMakeMove(Directions.WEST, number);
        model.changePlayer();
      } else {
        messages = model.makeMove(Directions.WEST);
        playGame();
      }
      messageCheck(messages);
    } catch (Exception e) {
      view.popUpBox(e.getMessage());
    }
  }


  @Override
  public void shootVisibility() {
    view.shootVisibility();
  }

  @Override
  public void moveVisibility() {
    view.moveVisibility();
  }


  @Override
  public void processMazeType(String mazeType) {
    this.mazeType = mazeType;
    view.remainingWallsFieldState(!mazeType.equals("perfect") && !mazeType.equals(" "));
  }

  @Override
  public void playerInGame(String playerInGame) {
    this.playerOption = Integer.parseInt(String.valueOf(playerInGame.charAt(0)));
  }

  @Override
  public void processInput(String inputs) throws IOException {
    try {
      String[] list = inputs.split(",");
      rows = Integer.parseInt(list[0]);
      columns = Integer.parseInt(list[1]);
      remainingWalls = list[2].equals("") ? 0 : Integer.parseInt(list[2]);
      batPercentage = Integer.parseInt(list[3]);
      pitPercentage = Integer.parseInt(list[4]);
      if (list.length < 6) {
        throw new IllegalArgumentException("Select wrapping or non Wrapping");
      }
      wrapping = Boolean.parseBoolean(list[5]);
    } catch (Exception e) {
      view.popUpBox(e.getMessage());
      return;
    }
    setModel();
  }

  private void setModel() throws IOException {
    IMaze maze = null;
    try {
      if (mazeType == null) {
        throw new IllegalArgumentException("select the maze type");
      }

      if (mazeType.equals("perfect")) {
        maze = new PerfectMaze(rows, columns, wrapping, batPercentage, pitPercentage);
      }
      if (mazeType.equals("imperfect")) {
        maze = new NonPerfectMaze(rows, columns, remainingWalls, wrapping,
                batPercentage, pitPercentage);
      }
      model = new GamePlayMultiPlayer(maze, playerOption);
    } catch (Exception e) {
      view.popUpBox(e.getMessage());
      return;
    }
    view.disableVisibility();
    view = new MazeView(rows, columns, this);
    setView(view);
    playGame();
  }

  // Change the state of the game/maze
  private void playGame() throws IOException {
    updateImage();
    initialisePlayers();
    initialiseWampus();
    initialiseHint();
    playerPreviousState();
  }

  private void playerPreviousState() {
    Set<Integer> memory = model.getMoveMemory();
    for (Integer integer : memory) {
      int[] location = getNumberToCoordinate(integer);
      view.setVisibility(location[0], location[1]);
    }
  }


  private void initialiseHint() throws IOException {
    StringBuilder stringBuilder = model.hintImpl(model.getPlayerPosRow(),
            model.getPlayerPosColumn());
    String stenchPath = path + "stench.png";
    String breezePath = path + "breeze.png";
    if (stringBuilder.indexOf("Wampus near by\n") >= 0) {
      ImageIcon imageIcon = (ImageIcon) view.getImage(model.getPlayerPosRow(),
              model.getPlayerPosColumn());
      BufferedImage image = (BufferedImage) imageIcon.getImage();
      image = overlay(image, stenchPath, 10);
      view.populateImage(model.getPlayerPosRow(), model.getPlayerPosColumn(), new ImageIcon(image));
    }
    if (stringBuilder.indexOf("Draft near by\n") >= 0) {
      ImageIcon imageIcon = (ImageIcon) view.getImage(model.getPlayerPosRow(),
              model.getPlayerPosColumn());
      BufferedImage image = (BufferedImage) imageIcon.getImage();
      image = overlay(image, breezePath, 10);
      view.populateImage(model.getPlayerPosRow(), model.getPlayerPosColumn(), new ImageIcon(image));
    }
  }


  private void initialiseWampus() throws IOException {
    String wampusPath = path + "wumpus.png";
    int[] location = this.getNumberToCoordinate(model.getWampusLocation());
    ImageIcon imageIcon = (ImageIcon) view.getImage(location[0], location[1]);
    BufferedImage image = (BufferedImage) imageIcon.getImage();
    image = overlay(image, wampusPath, 10);
    view.populateImage(location[0], location[1], new ImageIcon(image));
  }

  // Initialise Players on the maze initially
  private void initialisePlayers() throws IOException {
    int count = playerOption;
    while (count != 0) {
      updatePlayerImage();
      model.changePlayer();
      count--;
    }
  }

  // To Overlay Images
  private BufferedImage overlay(BufferedImage starting, String fpath, int offset) throws
          IOException {
    BufferedImage overlay = ImageIO.read(new File(fpath));
    int w = Math.max(starting.getWidth(), overlay.getWidth());
    int h = Math.max(starting.getHeight(), overlay.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(overlay, offset, offset, null);
    return combined;
  }

  // Reads and returns an image given the path
  private BufferedImage getImage(String filePath) throws IOException {
    BufferedImage picture = ImageIO.read(new File(filePath));
    return picture;
  }

  // Updates the players position on the maze
  private void updatePlayerImage() throws IOException {
    String playerPath = path + "player.png";
    ImageIcon imageIcon = (ImageIcon) view.getImage(model.getPlayerPosRow(),
            model.getPlayerPosColumn());
    BufferedImage image = (BufferedImage) imageIcon.getImage();
    image = overlay(image, playerPath, 10);
    view.populateImage(model.getPlayerPosRow(), model.getPlayerPosColumn(), new ImageIcon(image));
    view.setVisibility(model.getPlayerPosRow(), model.getPlayerPosColumn());
  }

  // Method for populating the view with images
  public void updateImage() throws IOException {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        int number = getCoordinateToNumber(i, j);
        BufferedImage image = getImage(path + getImage(i, j));
        if (model.getBatList().contains(number)) {
          String batPath = path + "bats.png";
          image = overlay(image, batPath, 0);
        }
        if (model.getPitList().contains(number)) {
          String pitPath = path + "pit.png";
          image = overlay(image, pitPath, 0);
        }
        view.populateImage(i, j, new ImageIcon(image));
      }
    }
  }


  // Returns the image name for forming the maze path
  private String getImage(int row, int column) {
    Set<String> temp = new HashSet<>();
    for (Directions directions : model.getMoves(row, column)) {
      temp.add(directions.toString().toLowerCase());
    }
    return textImageMap.get(temp);
  }


  private int getCoordinateToNumber(int row, int column) {
    return columns * row + column;
  }

  private int[] getNumberToCoordinate(int number) {
    return model.getNumberToCoordinate().get(number);
  }


  @Override
  public void exit() {
    System.exit(0);
  }

}
