package controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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

/**
 * The ControllerGui implements the IControllerGui interface and has methods to start the game using
 * the model which is taken as an input. The controller makes decisions about the gameplay and
 * dedicates the task of the view and model.
 */
public class ControllerGui implements IControllerGui {

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
  private List<String> playerIndices;
  private final Set<Integer> memory;

  private final String path = Paths.get("").toAbsolutePath().toString() + "\\" + "res\\"
          + "hunt-the-wumpus-images\\hunt-the-wumpus-images\\";

  private final Map<Set<String>, String> textImageMap;
  private final int seed;
  private final Map<String, Directions> stringDirectionsMap;


  /**
   * Instantiates a new ControllerGui object with instance variables. The constructor loads the
   * hashmap with image names as well as the corresponding image paths.
   */
  public ControllerGui() {
    Random random = new Random();
    seed = random.nextInt();
    AbstractIMaze.setRandom(seed);
    this.model = null;
    textImageMap = new HashMap<>();
    populateMap(textImageMap);
    playerIndices = new ArrayList<>();
    memory = new HashSet<>();
    stringDirectionsMap = new HashMap<>();
    loadStringDirection();
  }

  private void loadStringDirection() {
    stringDirectionsMap.put("north", Directions.NORTH);
    stringDirectionsMap.put("south", Directions.SOUTH);
    stringDirectionsMap.put("east", Directions.EAST);
    stringDirectionsMap.put("west", Directions.WEST);

  }

  private void populateMap(Map<Set<String>, String> textImageMap) {
    textImageMap.put(new HashSet<>() {
      {
        add("bat");
      }
    }, "bats.png");
    textImageMap.put(new HashSet<>() {
      {
        add("black");
      }
    }, "black.png");
    textImageMap.put(new HashSet<>() {
      {
        add("breeze");
      }
    }, "breeze.png");
    textImageMap.put(new HashSet<>() {
      {
        add("east");
      }
    }, "E.png");
    textImageMap.put(new HashSet<>() {
      {
        add("east");
        add("west");
      }
    }, "EW.png");
    textImageMap.put(new HashSet<>() {
      {
        add("north");
      }
    }, "N.png");
    textImageMap.put(new HashSet<>() {
      {
        add("north");
        add("east");
      }
    }, "NE.png");
    textImageMap.put(new HashSet<>() {
      {
        add("north");
        add("east");
        add("west");
      }
    }, "NEW.png");
    textImageMap.put(new HashSet<>() {
      {
        add("north");
        add("south");
      }
    }, "NS.png");
    textImageMap.put(new HashSet<>() {
      {
        add("north");
        add("south");
        add("east");
      }
    }, "NSE.png");
    textImageMap.put(new HashSet<>() {
      {
        add("north");
        add("south");
        add("east");
        add("west");
      }
    }, "NSEW.png");
    textImageMap.put(new HashSet<>() {
      {
        add("north");
        add("south");
        add("west");
      }
    }, "NSW.png");
    textImageMap.put(new HashSet<>() {
      {
        add("north");
        add("west");
      }
    }, "NW.png");
    textImageMap.put(new HashSet<>() {
      {
        add("pit");
      }
    }, "pit.png");
    textImageMap.put(new HashSet<>() {
      {
        add("player1");
      }
    }, "player1.png");
    textImageMap.put(new HashSet<>() {
      {
        add("player2");
      }
    }, "player2.png");
    textImageMap.put(new HashSet<>() {
      {
        add("south");
      }
    }, "S.png");
    textImageMap.put(new HashSet<>() {
      {
        add("south");
        add("east");
      }
    }, "SE.png");
    textImageMap.put(new HashSet<>() {
      {
        add("west");
        add("south");
        add("east");
      }
    }, "SEW.png");
    textImageMap.put(new HashSet<>() {
      {
        add("stench");
      }
    }, "stench.png");
    textImageMap.put(new HashSet<>() {
      {
        add("south");
        add("west");
      }
    }, "SW.png");
    textImageMap.put(new HashSet<>() {
      {
        add("thief");
      }
    }, "thief.png");
    textImageMap.put(new HashSet<>() {
      {
        add("west");
      }
    }, "W.png");
    textImageMap.put(new HashSet<>() {
      {
        add("wumpus");
      }
    }, "wumpus.png");
  }


  /**
   * Sets view passes the control of the view to the controller. It also sets the features of the
   * view as well as the key board features.
   *
   * @param v the v is the view object that is passed to the controller
   */
  public void setView(IView v) {
    view = v;
    view.setFeatures(this);
    setKeyBoard();
  }

  // Convert String to integer
  private int getShootMoves(String moves) {
    return Integer.parseInt(moves);
  }

  private void messageCheck(Messages message) {
    int action;
    int index = playerIndices.size() - 1;
    if (message == Messages.BAT) {
      throw new IllegalArgumentException(playerIndices.get(index).split(",")[1] + " | "
              + Messages.getMessages(Messages.BAT));
    }

    if (message == Messages.PIT) {

      String playerNum = playerIndices.remove(index);
      if (playerIndices.size() >= 1) {
        throw new IllegalArgumentException(playerNum.split(",")[1] + " | "
                + Messages.getMessages(Messages.PIT));
      } else {
        action = view.gameEndPopUp(playerNum.split(",")[1] + " | "
                + Messages.getMessages(Messages.PIT));
        endPopUpImpl(action);
      }
    }

    if (message == Messages.ARROWMISS) {
      throw new IllegalArgumentException(playerIndices.get(index).split(",")[1] + " | "
              + Messages.getMessages(Messages.ARROWMISS));
    }

    if (message == Messages.WAMPUSMISS) {
      throw new IllegalArgumentException(playerIndices.get(index).split(",")[1] + " | "
              + Messages.getMessages(Messages.WAMPUSMISS));
    }
    if (message == Messages.NOTBAT) {
      throw new IllegalArgumentException(playerIndices.get(index).split(",")[1] + " | "
              + Messages.getMessages(Messages.NOTBAT));
    }
    if (message == Messages.NOARROWS) {
      String playerNum = playerIndices.remove(index);
      if (playerIndices.size() >= 1) {
        throw new IllegalArgumentException(playerNum.split(",")[1] + " | "
                + Messages.getMessages(Messages.NOARROWS));
      } else {
        action = view.gameEndPopUp(playerNum.split(",")[1] + " | "
                + Messages.getMessages(Messages.NOARROWS));
        endPopUpImpl(action);
      }
    }
    if (message == Messages.GAMEOVER) {
      String playerNum = playerIndices.remove(index);
      if (playerIndices.size() >= 1) {
        throw new IllegalArgumentException(playerNum.split(",")[1] + " | "
                + Messages.getMessages(Messages.GAMEOVER));
      } else {
        action = view.gameEndPopUp(playerNum.split(",")[1] + " | "
                + Messages.getMessages(Messages.GAMEOVER));
        endPopUpImpl(action);
      }
    }
    if (message == Messages.BATPIT) {
      String playerNum = playerIndices.remove(index);
      if (playerIndices.size() >= 1) {
        throw new IllegalArgumentException(playerNum.split(",")[1] + " | "
                + Messages.getMessages(Messages.BATPIT));
      } else {
        action = view.gameEndPopUp(playerNum.split(",")[1] + " | "
                + Messages.getMessages(Messages.BATPIT));
        endPopUpImpl(action);
      }
    }

    if (message == Messages.BATWAMPUS) {
      String playerNum = playerIndices.remove(index);
      if (playerIndices.size() >= 1) {
        throw new IllegalArgumentException(playerNum.split(",")[1] + " | "
                + Messages.getMessages(Messages.BATWAMPUS));
      } else {
        action = view.gameEndPopUp(playerNum.split(",")[1] + " | "
                + Messages.getMessages(Messages.BATWAMPUS));
        endPopUpImpl(action);
      }
    }

    if (message == Messages.WAMPUS) {
      String playerNum = playerIndices.remove(index);
      if (playerIndices.size() >= 1) {
        throw new IllegalArgumentException(playerNum.split(",")[1] + " | "
                + Messages.getMessages(Messages.WAMPUS));
      } else {
        action = view.gameEndPopUp(playerNum.split(",")[1] + " | "
                + Messages.getMessages(Messages.WAMPUS));
        endPopUpImpl(action);
      }
    }
    if (message == Messages.WIN) {
      String playerNum = playerIndices.remove(index);
      action = view.gameEndPopUp(playerNum.split(",")[1] + " won the game");
      endPopUpImpl(action);
    }
  }

  private void endPopUpImpl(int choice) {
    try {
      switch (choice) {
        case -1:
        case 0:
          System.exit(0);
          break;
        case 1:
          AbstractIMaze.setRandom(seed);
          memory.clear();
          updatePlayerIndices();
          setModel();
          break;
        case 2:
          view.disableVisibility();
          memory.clear();
          view = new FormView("Welcome to the Game", this);
          this.setView(view);
          break;
        default:
          view.gameEndPopUp("Choose one of the options");
      }
    } catch (Exception e) {
      view.popUpBox(e.getMessage());
    }
  }

  private int shufflePlayer() {
    Collections.reverse(playerIndices);
    return Integer.parseInt(playerIndices.get(0).split(",")[0]);
  }


  // actionFlag -> false for move; actionFlag -> true for shoot
  @Override
  public void move(String move, boolean actionFlag, String shootMoves) {
    Messages messages;
    Directions direction = stringDirectionsMap.get(move);
    try {
      view.setStatus(String.format("%s's turn | arrows left: %d | position: %s,%s",
              playerIndices.get(0).split(",")[1], model.getArrows(), model.getPlayerPosRow(),
              model.getPlayerPosColumn()), path
              + playerIndices.get(0).split(",")[1] + ".png");
      int number = shootMoves.equals("") ? 0 : getShootMoves(shootMoves);
      if (actionFlag) {
        messages = model.arrowMakeMove(direction, number);
      } else {
        messages = model.makeMove(direction);
        playGame(false);
        // player's state changed
      }
      model.changePlayer(shufflePlayer());
      view.setStatus(String.format("%s's turn | arrows left: %d | position: %s,%s",
              playerIndices.get(0).split(",")[1], model.getArrows(), model.getPlayerPosRow(),
              model.getPlayerPosColumn()), path
              + playerIndices.get(0).split(",")[1] + ".png");
      messageCheck(messages);
    } catch (Exception e) {
      view.popUpBox(e.getMessage());
    }
  }

  @Override
  public void shootVisibility() {
    view.shootVisibility();
    view.resetFocus();
  }

  @Override
  public void moveVisibility() {
    view.moveVisibility();
    view.resetFocus();
  }

  @Override
  public void processMazeType(String mazeType) {
    this.mazeType = mazeType;
    view.remainingWallsFieldState(!mazeType.equals("perfect") && !mazeType.equals(""));
  }

  //Players are updated to the Map having playerName and index
  @Override
  public void playerInGame(String playersInGame) {
    try {
      if (playersInGame.equals("") || playersInGame.equals(" ")) {
        throw new IllegalArgumentException("Please select a player");
      }
      this.playerOption = Integer.parseInt(String.valueOf(playersInGame.charAt(0)));
      updatePlayerIndices();
    } catch (Exception e) {
      view.popUpBox(e.getMessage());
    }
  }

  private void updatePlayerIndices() {
    String s = "player";
    playerIndices = new ArrayList<>();
    for (int i = 0; i < playerOption; i++) {
      playerIndices.add(i + "," + s + (i + 1));
    }
  }

  @Override
  public void processInput(String inputs) throws IOException {
    try {
      String[] list = inputs.split(",");
      if (list.length < 5) {
        throw new IllegalArgumentException("Fill all the fields");
      }
      rows = list[0].equals("") ? 0 : Integer.parseInt(list[0]);
      columns = list[1].equals("") ? 0 : Integer.parseInt(list[1]);
      remainingWalls = list[2].equals("") ? -1 : Integer.parseInt(list[2]);
      batPercentage = list[3].equals("") ? -1 : Integer.parseInt(list[3]);
      pitPercentage = list[4].equals("") ? -1 : Integer.parseInt(list[4]);
      if (list.length < 6) {
        throw new IllegalArgumentException("Select Wrapping or non Wrapping");
      }
      wrapping = Boolean.parseBoolean(list[5]);
    } catch (Exception e) {
      view.popUpBox(e.getMessage());
      return;
    }

    view.popUpBox(this.toString());
    setModel();
  }

  private void setModel() throws IOException {
    IMaze maze = null;
    try {
      if (mazeType == null || mazeType.equals("")) {
        throw new IllegalArgumentException("Select the Maze Type");
      } else if (mazeType.equals("perfect")) {
        maze = new PerfectMaze(rows, columns, wrapping, batPercentage, pitPercentage);
      } else if (mazeType.equals("imperfect")) {
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
    view.resetFocus();
    setView(view);
    playGame(true);
  }


  // Change the state of the game/maze
  private void playGame(boolean initialFlag) throws IOException {
    updateImage();
    initialiseWampus();
    initialiseHint();
    initialisePlayers();
    playerPreviousState();
  }


  private void updateImage() throws IOException {
    // Method for populating the view with images
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        int number = getCoordinateToNumber(i, j);
        BufferedImage image = getImage(path + getImage(i, j));
        if (model.getBatList().contains(number)) {
          Set<String> tempBat = new HashSet<>() {
            {
              add("bat");
            }
          };
          String batPath = path + textImageMap.get(tempBat);
          image = overlay(image, batPath, 0);
        }
        if (model.getPitList().contains(number)) {
          Set<String> tempPit = new HashSet<>();
          tempPit.add("pit");
          String pitPath = path + textImageMap.get(tempPit);
          image = overlay(image, pitPath, 0);
        }
        view.populateImage(i, j, new ImageIcon(image));
      }
    }
  }

  private void initialiseWampus() throws IOException {
    Set<String> tempWampus = new HashSet<>();
    tempWampus.add("wumpus");
    String wampusPath = path + textImageMap.get(tempWampus);
    int[] location = this.getNumberToCoordinate(model.getWampusLocation());
    ImageIcon imageIcon = (ImageIcon) view.getImage(location[0], location[1]);
    BufferedImage image = (BufferedImage) imageIcon.getImage();
    image = overlay(image, wampusPath, 10);
    view.populateImage(location[0], location[1], new ImageIcon(image));
  }

  private void initialiseHint() throws IOException {
    int count = 0;
    while (count < playerIndices.size()) {
      StringBuilder stringBuilder = model.hintImpl(model.getPlayerPosRow(),
              model.getPlayerPosColumn());
      Set<String> tempStench = new HashSet<>();
      tempStench.add("stench");
      String stenchPath = path + textImageMap.get(tempStench);
      Set<String> tempBreeze = new HashSet<>();
      tempBreeze.add("breeze");
      String breezePath = path + textImageMap.get(tempBreeze);
      if (stringBuilder.indexOf("Wampus near by\n") >= 0) {
        ImageIcon imageIcon = (ImageIcon) view.getImage(model.getPlayerPosRow(),
                model.getPlayerPosColumn());
        BufferedImage image = (BufferedImage) imageIcon.getImage();
        image = overlay(image, stenchPath, 10);
        view.populateImage(model.getPlayerPosRow(), model.getPlayerPosColumn(),
                new ImageIcon(image));
      }
      if (stringBuilder.indexOf("Draft near by\n") >= 0) {
        ImageIcon imageIcon = (ImageIcon) view.getImage(model.getPlayerPosRow(),
                model.getPlayerPosColumn());
        BufferedImage image = (BufferedImage) imageIcon.getImage();
        image = overlay(image, breezePath, 10);
        view.populateImage(model.getPlayerPosRow(), model.getPlayerPosColumn(),
                new ImageIcon(image));
      }
      model.changePlayer(shufflePlayer());
      count++;
    }
  }

  // Initialise Players on the maze initially
  private void initialisePlayers() throws IOException {
    int count = 0;
    while (count < playerIndices.size()) {
      updatePlayerImage(playerIndices.get(0).split(",")[1]);
      memory.addAll(model.getMoveMemory());
      count++;
      model.changePlayer(shufflePlayer());
    }
    view.setStatus(String.format("%s's turn | arrows left: %d | position: %s,%s",
            playerIndices.get(0).split(",")[1], model.getArrows(), model.getPlayerPosRow(),
            model.getPlayerPosColumn()), path
            + playerIndices.get(0).split(",")[1] + ".png");
  }

  // Updates the players position on the maze
  private void updatePlayerImage(String playerImage) throws IOException {
    int offset = playerImage.equals("player1") ? 10 : 20;
    String playerPath = path + playerImage + ".png";
    ImageIcon imageIcon = (ImageIcon) view.getImage(model.getPlayerPosRow(),
            model.getPlayerPosColumn());
    BufferedImage image = (BufferedImage) imageIcon.getImage();
    image = overlay(image, playerPath, offset);
    view.populateImage(model.getPlayerPosRow(), model.getPlayerPosColumn(), new ImageIcon(image));
    view.setVisibility(model.getPlayerPosRow(), model.getPlayerPosColumn());
  }

  // Sets the visibility of the maze
  private void playerPreviousState() {
    for (Integer integer : memory) {
      int[] location = getNumberToCoordinate(integer);
      view.setVisibility(location[0], location[1]);
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


  private void setKeyBoard() {
    final Map<Character, Runnable> keyTyped = new HashMap<>();
    final Map<Integer, Runnable> keyPresses = new HashMap<>();
    final Map<Integer, Runnable> keyReleases = new HashMap<>();

    keyPresses.put(KeyEvent.VK_UP, () -> {
      this.move("north", false, "0");
    });
    keyPresses.put(KeyEvent.VK_DOWN, () -> {
      this.move("south", false, "0");
    }
    );
    keyPresses.put(KeyEvent.VK_LEFT, () -> {
      this.move("west", false, "0");
    }
    );
    keyPresses.put(KeyEvent.VK_RIGHT, () -> {
      this.move("east", false, "0");
    }
    );
    keyPresses.put(KeyEvent.VK_C, () -> {
      this.bounty();
    }
    );

    keyReleases.put(KeyEvent.VK_C, () -> {
    }
    );

    keyTyped.put((char) KeyEvent.VK_C, () -> {
    }
    );


    KeyboardListener kbd = new KeyboardListener();

    kbd.setKeyTypedMap(keyTyped);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    view.addKeyListener(kbd);
  }


  private void bounty() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        view.setVisibility(i, j);
      }
    }
  }


  @Override
  public void exit() {
    System.exit(0);
  }


  @Override
  public String toString() {
    return "mazeType='" + mazeType + '\''
            + ", playerOption=" + playerOption
            + ", rows=" + rows
            + ", columns=" + columns
            + ", remainingWalls=" + remainingWalls
            + ", batPercentage=" + batPercentage
            + ", pitPercentage=" + pitPercentage
            + ", wrapping=" + wrapping;
  }
}
