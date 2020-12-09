import org.junit.Before;
import org.junit.Test;
import maze.AbstractIMaze;
import maze.Directions;
import maze.GamePlayExtended;
import maze.IMaze;
import maze.Messages;
import maze.PerfectMaze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * The type Game play extended test.
 */
public class GamePlayExtendedTest {

  /**
   * The Game play extended.
   */
  GamePlayExtended gamePlayExtended;
  /**
   * The Maze.
   */
  IMaze iMaze;

  /**
   * Sets up.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp() throws Exception {
    AbstractIMaze.setRandom(10);
    iMaze = new PerfectMaze(4,3,false,90,20);
  }

  /**
   * Test invalid bat percentage.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBatPercentage() {
    iMaze = new PerfectMaze(4,3,false,-2,40);
  }

  /**
   * Test invalid pit percentage.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPitPercentage() {
    iMaze = new PerfectMaze(4,3,false,40,-2);
  }

  /**
   * Test invalid pit percentage 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPitPercentage1() {
    iMaze = new PerfectMaze(4,3,false,40,110);
  }

  /**
   * Test invalid bat percentage 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBatPercentage1() {
    iMaze = new PerfectMaze(4,3,false,120,40);
  }


  /**
   * Test wampus killed.
   */
  @Test
  public void testWampusKilled() {
    gamePlayExtended = new GamePlayExtended(iMaze);
    Messages messages =  gamePlayExtended.makeMove(Directions.EAST);
    assertEquals(messages, Messages.BAT);
  }


  /**
   * Test kill wampus.
   */
  @Test
  public void testKillWampusMiss() {
    gamePlayExtended = new GamePlayExtended(iMaze);
    Messages messages1 =  gamePlayExtended.arrowMakeMove(Directions.EAST, 1);
    assertEquals(messages1, Messages.WAMPUSMISS);
  }


  /**
   * Test bat.
   */
  @Test
  public void testBAT() {
    AbstractIMaze.setRandom(10);
    IMaze iMaze1 = new PerfectMaze(4,3,false,30,20);
    gamePlayExtended = new GamePlayExtended(iMaze1);
    gamePlayExtended.makeMove(Directions.SOUTH);
    Messages messages =  gamePlayExtended.makeMove(Directions.NORTH);
    assertNotEquals(messages, Messages.PIT);
  }

  /**
   * Test arrow limit.
   */
  @Test
  public void testArrowLimit() {
    AbstractIMaze.setRandom(10);
    IMaze iMaze2 = new PerfectMaze(4,3,false,30,20);
    gamePlayExtended = new GamePlayExtended(iMaze2);
    gamePlayExtended.arrowMakeMove(Directions.NORTH, 2);
    gamePlayExtended.arrowMakeMove(Directions.NORTH, 2);
    gamePlayExtended.arrowMakeMove(Directions.NORTH, 2);
    Messages messages = gamePlayExtended.arrowMakeMove(Directions.NORTH, 2);
    assertEquals(messages, Messages.NOARROWS);
  }

  /**
   * Test arrow miss.
   */
  @Test
  public void testArrowMiss() {
    AbstractIMaze.setRandom(10);
    IMaze iMaze3 = new PerfectMaze(4,3,false,30,20);
    gamePlayExtended = new GamePlayExtended(iMaze3);
    Messages messages = gamePlayExtended.arrowMakeMove(Directions.NORTH, 4);
    assertEquals(messages, Messages.ARROWMISS);
  }

  /**
   * Hint.
   */
  @Test
  public void hintImpl() {
    AbstractIMaze.setRandom(10);
    IMaze iMaze4 = new PerfectMaze(4,3,false,30,20);
    gamePlayExtended = new GamePlayExtended(iMaze4);
    StringBuilder string =  gamePlayExtended.hintImpl(2,1);
    assertEquals(string.toString(), "Draft near by\n");
  }

  /**
   * Test wampus miss.
   */
  @Test
  public void testWampusMiss() {
    AbstractIMaze.setRandom(10);
    IMaze iMaze4 = new PerfectMaze(4,3,false,30,20);
    gamePlayExtended = new GamePlayExtended(iMaze4);
    Messages messages = gamePlayExtended.arrowMakeMove(Directions.NORTH, 2);
    assertEquals(messages, Messages.ARROWMISS);
  }

  /**
   * Test move check.
   */
  @Test
  public void testMoveCheck() {
    AbstractIMaze.setRandom(10);
    IMaze iMaze4 = new PerfectMaze(4,3,false,30,20);
    gamePlayExtended = new GamePlayExtended(iMaze4);
    Messages messages = gamePlayExtended.makeMove(Directions.EAST);
    assertEquals(5,  gamePlayExtended.getPlayerPosition());
  }

  /**
   * Test invalid move.
   */
  @Test(expected = AssertionError.class)
  public void testInvalidMove() {
    AbstractIMaze.setRandom(10);
    IMaze iMaze4 = new PerfectMaze(4,3,false,30,20);
    gamePlayExtended = new GamePlayExtended(iMaze4);
    Messages messages = gamePlayExtended.makeMove(Directions.SOUTH);
    assertEquals(6,  gamePlayExtended.getPlayerPosition());
  }

  /**
   * Test wampus smell check.
   */
  @Test
  public void testWampusSmellCheck() {
    AbstractIMaze.setRandom(10);
    IMaze iMaze4 = new PerfectMaze(4,3,false,30,20);
    gamePlayExtended = new GamePlayExtended(iMaze4);
    StringBuilder stringMessage = gamePlayExtended.hintImpl(1,2);
    assertEquals(stringMessage.toString(),  "Wampus near by\n");
  }


}