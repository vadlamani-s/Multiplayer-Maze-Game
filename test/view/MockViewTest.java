package view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ControllerGui;
import maze.AbstractIMaze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The type Mock view test class is used for testing the mock view along with the controller.
 */
public class MockViewTest {

  private StringBuilder in;
  private ControllerGui controller;
  private MockMazeView view;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    in = new StringBuilder();
    StringBuilder out = new StringBuilder();
    int intOut = 1000;
    StringBuilder builder = new StringBuilder();
    controller = new ControllerGui();
    view = new MockMazeView(in, out, intOut);
  }


  /**
   * Test set v iew.
   */
  @Test
  public void testSetVIew() {
    controller.setView(view);
    assertEquals(in.toString(), "mazeType='null', playerOption=0, rows=0, columns=0, "
            + "remainingWalls=0, batPercentage=0, pitPercentage=0, "
            + "wrapping=falselistener added\n");
  }


  /**
   * Test process maze type 1.
   */
  @Test
  public void testProcessMazeType1() {
    controller.setView(view);
    controller.processMazeType("perfect");
    assertEquals(in.toString(), "mazeType='null', playerOption=0, rows=0, columns=0, "
            + "remainingWalls=0, batPercentage=0, pitPercentage=0, wrapping=falselistener added\n"
            + "false\n");
  }

  /**
   * Test process maze type 2.
   */
  @Test
  public void testProcessMazeType2() {
    controller.setView(view);
    controller.processMazeType("imperfect");
    assertEquals(in.toString(), "mazeType='null', playerOption=0, rows=0, columns=0, "
            + "remainingWalls=0, batPercentage=0, pitPercentage=0, wrapping=falselistener added\n"
            + "true\n");
  }

  /**
   * Test process maze type 3.
   */
  @Test
  public void testProcessMazeType3() {
    controller.setView(view);
    controller.processMazeType("");
    assertEquals(in.toString(), "mazeType='null', playerOption=0, rows=0, columns=0, "
            + "remainingWalls=0, batPercentage=0, pitPercentage=0, wrapping=falselistener added\n"
            + "false\n");
  }


  /**
   * Test player in game.
   */
  @Test
  public void testPlayerInGame() {
    controller.setView(view);
    controller.playerInGame("1-Rambo");
    assertEquals(controller.toString(), "mazeType='null', playerOption=1, rows=0, columns=0, "
            + "remainingWalls=0, batPercentage=0, pitPercentage=0, wrapping=false");
  }

  /**
   * Test player in game 2.
   */
  @Test
  public void testPlayerInGame2() {
    controller.setView(view);
    controller.playerInGame(" ");
    assertEquals(controller.toString(), "mazeType='null', playerOption=0, rows=0, columns=0, "
            + "remainingWalls=0, batPercentage=0, pitPercentage=0, wrapping=false");
  }

  /**
   * Process input.
   *
   * @throws IOException the io exception
   */
  @Test
  public void processInput() throws IOException {
    controller.setView(view);
    StringBuilder build = new StringBuilder();
    controller.processMazeType("perfect");
    controller.playerInGame("1-Rambo");
    build.append("12,").append("10,").append("10,").append("20,").append("10,").append("true");
    controller.processInput(build.toString());
    assertEquals(controller.toString(), "mazeType='perfect', playerOption=1, rows=12, "
            + "columns=10, remainingWalls=10, batPercentage=20, pitPercentage=10, wrapping=true");
  }

  /**
   * Shoot visibility.
   */
  @Test
  public void shootVisibility() {
    controller.setView(view);
    controller.shootVisibility();
    assertEquals(in.toString(), "mazeType='null', playerOption=0, rows=0, columns=0, "
            + "remainingWalls=0, "
            + "batPercentage=0, pitPercentage=0, wrapping=falselistener added\n"
            + "this method was accessed\n");
  }

  /**
   * Move visibility.
   */
  @Test
  public void moveVisibility() {
    controller.setView(view);
    controller.moveVisibility();
    assertEquals(in.toString(), "mazeType='null', playerOption=0, rows=0, columns=0, "
            + "remainingWalls=0, "
            + "batPercentage=0, pitPercentage=0, wrapping=falselistener added\n"
            + "this method was called\n");

  }

  /**
   * Move.
   *
   * @throws IOException the io exception
   */
  @Test
  public void move1Player() throws IOException {
    StringBuilder build = new StringBuilder();
    AbstractIMaze.setRandom(10);
    controller.setView(view);
    controller.processMazeType("perfect");
    controller.playerInGame("1-player");
    build.append("4,").append("4,").append("10,").append("0,").append("0,").append("true");
    controller.processInput(build.toString());
    controller.setView(view);
    controller.move("west", false, "0");
    assertTrue(in.toString().contains("player1's turn | arrows left: 3 | position: 2,2"));
    assertTrue(in.toString().contains("player1's turn | arrows left: 3 | position: 2,0"));
  }

  /**
   * Shoot arrow.
   *
   * @throws IOException the io exception
   */
  @Test
  public void shootArrow1Player() throws IOException {
    StringBuilder build = new StringBuilder();
    AbstractIMaze.setRandom(10);
    controller.setView(view);
    controller.processMazeType("perfect");
    controller.playerInGame("1-player");
    build.append("4,").append("4,").append("10,").append("0,").append("0,").append("true");
    controller.processInput(build.toString());
    controller.setView(view);
    controller.move("west", true, "4");
    assertTrue(in.toString().contains("player1 | Arrow missed"));
    assertTrue(in.toString().contains("player1's turn | arrows left: 2 | position: 2,2"));
    assertTrue(in.toString().contains("player1's turn | arrows left: 3 | position: 2,2"));
  }

  /**
   * Shoot arrow 1.
   *
   * @throws IOException the io exception
   */
  @Test
  public void shootArrow1() throws IOException {
    StringBuilder build = new StringBuilder();
    AbstractIMaze.setRandom(10);
    controller.setView(view);
    controller.processMazeType("perfect");
    controller.playerInGame("1-player");
    build.append("3,").append("3,").append("10,").append("0,").append("0,").append("true");
    controller.processInput(build.toString());
    controller.setView(view);
    controller.move("west", true, "4");
    controller.move("east", true, "4");
    controller.move("north", true, "4");
    controller.move("south", true, "4");
    controller.move("south", false, "");
    assertTrue(in.toString().contains("player1 | Arrow missed"));
    assertTrue(in.toString().contains("player1's turn | arrows left: 2 | position: 1,1"));
    assertTrue(in.toString().contains("player1's turn | arrows left: 3 | position: 1,1"));
    assertTrue(in.toString().contains("player1's turn | arrows left: 1 | position: 1,1"));
    assertTrue(in.toString().contains("player1 | You have no arrows left. Game's Over"));
  }

  /**
   * Move 2 player.
   *
   * @throws IOException the io exception
   */
  @Test
  public void move2Player() throws IOException {
    StringBuilder build = new StringBuilder();
    AbstractIMaze.setRandom(10);
    controller.setView(view);
    controller.processMazeType("perfect");
    controller.playerInGame("2-player");
    build.append("4,").append("4,").append("10,").append("0,").append("0,").append("true");
    controller.processInput(build.toString());
    controller.setView(view);
    controller.move("west", false, "0");
    controller.move("north", false, "0");
    controller.move("east", true, "2");
    controller.move("west", true, "1");
    assertTrue(in.toString().contains("player1's turn | arrows left: 3 | position: 2,2"));
    assertTrue(in.toString().contains("player1's turn | arrows left: 3 | position: 2,0"));
    assertTrue(in.toString().contains("player2's turn | arrows left: 3 | position: 1,3"));
    assertTrue(in.toString().contains("player2's turn | arrows left: 3 | position: 0,3"));
    assertTrue(in.toString().contains("player1's turn | arrows left: 2 | position: 2,0"));
    assertTrue(in.toString().contains("player1 | You missed the Wampus"));
    assertTrue(in.toString().contains("player2 | Arrow missed"));
  }

  /**
   * Shoot 2 player.
   *
   * @throws IOException the io exception
   */
  @Test
  public void shoot2Player() throws IOException {
    StringBuilder build = new StringBuilder();
    AbstractIMaze.setRandom(10);
    controller.setView(view);
    controller.processMazeType("imperfect");
    controller.playerInGame("2-player");
    build.append("10,").append("10,").append("10,").append("10,").append("10,").append("false");
    controller.processInput(build.toString());
    controller.setView(view);
    controller.move("west", true, "10");
    controller.move("north", true, "4");
    controller.move("east", true, "2");
    controller.move("west", true, "1");
    assertTrue(in.toString().contains("player1's turn | arrows left: 3 | position: 3,9"));
    assertTrue(in.toString().contains("player2's turn | arrows left: 3 | position: 0,5"));
    assertTrue(in.toString().contains("player1 | Arrow missed"));
    assertTrue(in.toString().contains("player1's turn | arrows left: 2 | position: 3,9"));
    assertTrue(in.toString().contains("player2's turn | arrows left: 2 | position: 0,5"));
    assertTrue(in.toString().contains("player1's turn | arrows left: 1 | position: 3,9"));
    assertTrue(in.toString().contains("player1 | Arrow missed"));
    assertTrue(in.toString().contains("player2 | You missed the Wampus"));
  }

}
