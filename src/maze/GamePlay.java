package maze;

import java.util.Map;
import java.util.Set;


/**
 * The Game play class is used to set the player on the maze and start the game. It keeps track of
 * all position of the player, the method to move the player in the maze. The game play is started
 * and the winner is decided if goal is reached.
 */
public class GamePlay implements IGamePlay {

  /**
   * The Player.
   */
  protected Player player;
  /**
   * The Maze.
   */
  protected final IMaze iMaze;
  private final int goalPosRow;
  private final int goalPosColumn;


  /**
   * The game play constructor instantiates the maze as well as the player. The player is placed on
   * the maze and the maze is created.
   *
   * @param mazeType     the maze decided by the user
   * @param playerPosRow the player position row
   * @param playerPosCol the player position column
   * @param goalPosRow   the goal pos row
   * @param goalPosCol   the goal pos col
   */
  public GamePlay(IMaze mazeType, int playerPosRow, int playerPosCol, int goalPosRow,
                  int goalPosCol) {
    if (mazeType == null) {
      throw new IllegalArgumentException("maze cannot be null");
    }
    this.goalPosRow = goalPosRow;
    this.goalPosColumn = goalPosCol;
    this.iMaze = mazeType;
    iMaze.createMaze();
    int totalColumns = iMaze.getColumns();
    this.player = new Player(playerPosRow, playerPosCol, totalColumns);
    if (this.player.getPlayerRow() == goalPosCol
            && this.player.getPlayerCol() == goalPosCol) {
      throw new IllegalArgumentException("Player position and Goal position have to be different");
    }
  }

  @Override
  public void updatePlayerPosition(int playerRow, int playerColumn) {
    if (!(iMaze.getAvailablePlayerPositions().contains(coorToNumber(playerRow, playerColumn)))) {
      throw new IllegalArgumentException("The player position is invalid");
    }
    this.player.setPosition(new Position(playerRow, playerColumn));
    // adds player's position to the list
    this.player.getMoveMemory().add(this.player.getPositionNumber());
  }

  /**
   * Gets moves by the player can make depending on if the wall is present.
   *
   * @param row    the row of the room
   * @param column the column of the room
   * @return the moves as set type
   */
  @Override
  public Set<Directions> getMoves(int row, int column) {
    return iMaze.currMoveList(row,
            column).keySet();
  }


  /**
   * The method is used to make a move by the player in the maze. It updates the players position as
   * well as the gold value if collected. It returns an object of the game type.
   *
   * @param move the move to be made
   */
  @Override
  public Messages makeMove(Directions move) {
    Map<Directions, int[]> directionsHashMap = iMaze.currMoveList(this.player.getPlayerRow(),
            this.player.getPlayerCol());
    if (!directionsHashMap.containsKey(move)) {
      throw new IllegalArgumentException("Enter the correct move");
    }
    int[] location = move(directionsHashMap, move);
    int newPosRow = location[0];
    int newPosCol = location[1];
    updateGold(newPosRow, newPosCol);
    player.setPosition(new Position(newPosRow, newPosCol));
    // adds player's position to the list
    this.player.getMoveMemory().add(this.player.getPositionNumber());
    return Messages.MOVESUCCESSFUL;
  }


  /**
   * Move method returns the coordinates of the move given the direction in terms of coordinates row
   * and column.
   *
   * @param directionsHashMap the directions hash map of direction and corresponding row and column
   * @param move              the move of enum type
   * @return the array of coordinates in terms of rows and columns
   */
  protected int[] move(Map<Directions, int[]> directionsHashMap, Directions move) {
    return directionsHashMap.get(move);
  }


  private int coorToNumber(int row, int col) {
    return player.calculateNumber(row, col);
  }


  private void updateGold(int newPosRow, int newPosCol) {
    if (this.iMaze.getNode(coorToNumber(newPosRow, newPosCol)).getFeatureList()
            .contains(Features.GOLD)) {
      player.updatePlayerGold(player.goldCount() + 10);
    }
    if (this.iMaze.getNode(coorToNumber(newPosRow, newPosCol)).getFeatureList()
            .contains(Features.THIEF)) {
      player.updatePlayerGold((float) (player.goldCount() * 0.9));
    }

    iMaze.getNode(coorToNumber(newPosRow, newPosCol)).getFeatureList().remove(Features.GOLD);
  }


  /**
   * The methods returns true if winner ie if the player reaches the goal.
   *
   * @return the boolean to represent if the game is won or not
   */
  @Override
  public Boolean isWinner() {
    return this.player.getPlayerCol() == this.goalPosColumn
            && this.player.getPlayerRow() == this.goalPosRow;
  }


  /**
   * The method returns the players details which includes the golds count and the position.
   *
   * @return the details as a string
   */
  @Override
  public String playerDetails() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("gold count : ").append(this.player.goldCount()).append("\n");
    stringBuilder.append("position row, col : ").append(this.player.getPlayerRow()).append(',')
            .append(this.player.getPlayerCol()).append("\n");
    stringBuilder.append("number : ").append(this.player.getPositionNumber()).append("\n");
    return stringBuilder.toString();
  }


  /**
   * Gets the gold value a player holds.
   *
   * @return the gold value
   */
  @Override
  public double getPlayerGold() {
    return this.player.goldCount();
  }


  /**
   * Gets the player's row position.
   *
   * @return the row
   */
  @Override
  public int getPlayerPosRow() {
    return this.player.getPlayerRow();
  }

  /**
   * Gets the player's column position.
   *
   * @return the column
   */
  @Override
  public int getPlayerPosColumn() {
    return this.player.getPlayerCol();
  }

  /**
   * Gets the number of the room the player is present.
   *
   * @return the number
   */
  @Override
  public int getPlayerPosition() {
    return this.player.getPositionNumber();
  }

}
