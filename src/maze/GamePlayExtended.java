package maze;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * The type Game play extended.
 */
public class GamePlayExtended extends GamePlay implements IGamePlayExtended {

  /**
   * The game play constructor instantiates the maze as well as the player. The player is placed on
   * the maze and the maze is created.
   *
   * @param mazeType the maze decided by the user
   */
  public GamePlayExtended(IMaze mazeType) {
    super(mazeType, 0, 0, mazeType.getRows() + 1,
            mazeType.getColumns() + 1);
  }

  @Override
  public Messages makeMove(Directions move) {
    super.makeMove(move);
    Node currentNode = this.iMaze.getNode(this.player.getPositionNumber());
    if (currentNode.getFeatureList().size() != 0) {
      while (currentNode.getFeatureList().contains(Features.HALLWAY)) {
        Directions opposite = oppositeDirectionMap(move);
        Map<Directions, int[]> moves = this.iMaze.currMoveList(this.player.getPlayerRow(),
                this.player.getPlayerCol());
        moves.remove(opposite);
        move = (Directions) moves.keySet().toArray()[0];
        super.makeMove(move);
        currentNode = this.iMaze.getNode(this.player.getPositionNumber());
        // Adding players move
        this.getMoveMemory().add(currentNode.getNumber());
      }
      if (currentNode.getFeatureList().contains(Features.BAT)
              && currentNode.getFeatureList().contains(Features.PIT)) {
        int prevPosition = player.getPositionNumber();
        return batPitRandomMove();
      }
      if (currentNode.getFeatureList().contains(Features.PIT)) {
        return Messages.PIT;
      }
      if (currentNode.getFeatureList().contains(Features.WAMPUS)) {
        return Messages.WAMPUS;
      }
      if (currentNode.getFeatureList().contains(Features.BAT)) {
        return batRandomMove();
      }
    }
    return Messages.MOVESUCCESSFUL;
  }


  /**
   * The method is used to make a move by the arrow in the maze. It updates the arrow position
   * depending on the number of moves and returns the status if the arrow missed or hit.
   *
   * @param move          the move of enum type
   * @param numberOfMoves the number of moves the arrow has to move
   */
  @Override
  public Messages arrowMakeMove(Directions move, int numberOfMoves) {
    boolean flag = false;
    if (this.player.getArrows() <= 0) {
      return Messages.NOARROWS;
    }
    this.player.updateArrows();
    Arrow arrow = new Arrow(player.getPlayerRow(), player.getPlayerCol(), move);
    if (numberOfMoves <= 0) {
      throw new IllegalArgumentException("Moves has to be greater than zero");
    }
    while (numberOfMoves != 0) {
      Map<Directions, int[]> directionsHashMap = iMaze.currMoveList(arrow.getRow(),
              arrow.getColumn());
      if (!directionsHashMap.containsKey(move)) {
        return Messages.ARROWMISS;
      }
      int[] location = move(directionsHashMap, move);
      arrow.updateArrow(location[0], location[1]);
      Node currentNode = this.iMaze.getNode(coorToNumber(arrow.getRow(), arrow.getColumn()));
      while (currentNode.getFeatureList().contains(Features.HALLWAY)) {
        Directions opposite = oppositeDirectionMap(move);
        directionsHashMap = this.iMaze.currMoveList(arrow.getRow(),
                arrow.getColumn());
        directionsHashMap.remove(opposite);
        move = (Directions) directionsHashMap.keySet().toArray()[0];
        location = move(directionsHashMap, move);
        arrow.updateArrow(location[0], location[1]);
        currentNode = this.iMaze.getNode(coorToNumber(arrow.getRow(), arrow.getColumn()));
      }
      numberOfMoves--;
      if (currentNode.getFeatureList().contains(Features.WAMPUS)) {
        flag = true;
      }
    }
    Node currentNode = this.iMaze.getNode(coorToNumber(arrow.getRow(), arrow.getColumn()));
    if (!(currentNode.getFeatureList().contains(Features.WAMPUS) && flag)) {

      if (this.player.getArrows() == 0) {
        return Messages.NOARROWS;
      }
      return Messages.WAMPUSMISS;
    }
    if (!(currentNode.getFeatureList().contains(Features.WAMPUS))) {

      if (this.player.getArrows() == 0) {
        return Messages.NOARROWS;
      }
      return Messages.WAMPUSMISS;
    }
    if (currentNode.getFeatureList().contains(Features.WAMPUS)) {
      return gameWon();
    }

    this.player.updateArrows();

    if (this.player.getArrows() == 0) {
      return gameOver();
    }
    return Messages.ARROWSUCCESSFUL;
  }


  /**
   * HintImpl method returns a string taking the current position of the player in terms of rows and
   * columns, the hint in terms of a string.
   *
   * @param row    the row coordinate of the room
   * @param column the column coordinate of the room
   * @return the string of the hints
   */
  @Override
  public StringBuilder hintImpl(int row, int column) {
    StringBuilder stringBuilder = new StringBuilder();
    boolean draftCheck = false;
    Map<Directions, int[]> directionsHashMap = iMaze.currMoveList(row, column);
    for (Directions direction : directionsHashMap.keySet()) {
      int[] location = directionsHashMap.get(direction);
      Node nextNode = getNextNode(location[0], location[1], direction);
      if (nextNode.getFeatureList().contains(Features.WAMPUS)) {
        stringBuilder.append("Wampus near by").append("\n");
      }
      if (nextNode.getFeatureList().contains(Features.PIT)) {
        draftCheck = true;
      }
    }
    if (draftCheck) {
      stringBuilder.append("Draft near by").append("\n");
    }
    return stringBuilder;
  }

  @Override
  public Set<Integer> getMoveMemory() {
    return this.player.getMoveMemory();
  }


  private Node getNextNode(int row, int column, Directions move) {
    Node currentNode = this.iMaze.getNode(coorToNumber(row, column));
    while (currentNode.getFeatureList().contains(Features.HALLWAY)) {
      Directions opposite = oppositeDirectionMap(move);
      Map<Directions, int[]> directionsHashMap = this.iMaze.currMoveList(row, column);
      directionsHashMap.remove(opposite);
      move = (Directions) directionsHashMap.keySet().toArray()[0];
      int[] location = move(directionsHashMap, move);
      row = location[0];
      column = location[1];
      currentNode = this.iMaze.getNode(coorToNumber(row, column));
    }
    return currentNode;
  }


  private int coorToNumber(int row, int col) {
    return player.calculateNumber(row, col);
  }


  private Directions oppositeDirectionMap(Directions move) {
    Map<Directions, Directions> oppositeMap = new HashMap<>();
    oppositeMap.put(Directions.NORTH, Directions.SOUTH);
    oppositeMap.put(Directions.SOUTH, Directions.NORTH);
    oppositeMap.put(Directions.EAST, Directions.WEST);
    oppositeMap.put(Directions.WEST, Directions.EAST);
    return oppositeMap.get(move);
  }

  private Messages gameOver() {
    return Messages.GAMEOVER;

  }

  private Messages gameWon() {
    return Messages.WIN;

  }

  private Messages batPitRandomMove() {
    boolean batPick = AbstractIMaze.random.nextBoolean();
    if (!batPick) {
      return Messages.PIT;
    }
    batMove();
    return Messages.BAT;
  }

  private Messages batRandomMove() {
    boolean batPick = AbstractIMaze.random.nextBoolean();
    if (batPick) {
      batMove();
      Node currentNode = this.iMaze.getNode(this.player.getPositionNumber());
      if (currentNode.getFeatureList().contains(Features.PIT)) {
        return Messages.BATPIT;
      }
      if (currentNode.getFeatureList().contains(Features.WAMPUS)) {
        return Messages.BATWAMPUS;
      }

    } else {
      return Messages.NOTBAT;
    }
    return Messages.BAT;
  }

  private void batMove() {
    boolean batPick = true;
    while (this.iMaze.getNode(player.getPositionNumber()).getFeatureList().contains(Features.BAT)
            && batPick || this.iMaze.getNode(player.getPositionNumber()).getFeatureList()
            .contains(Features.HALLWAY)) {
      int location = AbstractIMaze.random.nextInt(this.iMaze.getColumns()
              * this.iMaze.getRows());
      int[] locationCoord = this.iMaze.getNumberToCoordinate().get(location);
      player.setPosition(new Position(locationCoord[0], locationCoord[1]));
      player.getMoveMemory().add(player.getPositionNumber());
      batPick = AbstractIMaze.random.nextBoolean();
    }
  }

  @Override
  public int getArrows() {
    return player.getArrows();
  }

}
