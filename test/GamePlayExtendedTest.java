//import org.junit.Before;
//import org.junit.Test;
//import maze.AbstractIMaze;
//import maze.Directions;
//import maze.GamePlayExtended;
//import maze.IMaze;
//import maze.Messages;
//import maze.PerfectMaze;
//
//import static org.junit.Assert.assertEquals;
//
//
///**
// * The type Game play extended test.
// */
//public class GamePlayExtendedTest {
//
//  /**
//   * The Game play extended.
//   */
//  GamePlayExtended gamePlayExtended;
//  /**
//   * The Maze.
//   */
//  IMaze iMaze;
//
//  /**
//   * Sets up.
//   *
//   * @throws Exception the exception
//   */
//  @Before
//  public void setUp() throws Exception {
//    AbstractIMaze.setRandom(10);
//    iMaze = new PerfectMaze(4,3,false,90,20);
//  }
//
//  /**
//   * Test invalid bat percentage.
//   */
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidBatPercentage() {
//    iMaze = new PerfectMaze(4,3,false,-2,40);
//  }
//
//  /**
//   * Test invalid pit percentage.
//   */
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidPitPercentage() {
//    iMaze = new PerfectMaze(4,3,false,40,-2);
//  }
//
//  /**
//   * Test invalid pit percentage 1.
//   */
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidPitPercentage1() {
//    iMaze = new PerfectMaze(4,3,false,40,110);
//  }
//
//  /**
//   * Test invalid bat percentage 1.
//   */
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidBatPercentage1() {
//    iMaze = new PerfectMaze(4,3,false,120,40);
//  }
//
//
//  /**
//   * Test wampus killed.
//   */
//  @Test
//  public void testWampusKilled() {
//    gamePlayExtended = new GamePlayExtended(iMaze, 3, 0);
//    Messages messages =  gamePlayExtended.makeMove(Directions.EAST);
//    assertEquals(messages, Messages.WAMPUS);
//  }
//
//
//  /**
//   * Test kill wampus.
//   */
//  @Test
//  public void testKillWampus() {
//    gamePlayExtended = new GamePlayExtended(iMaze, 3, 0);
//    Messages messages1 =  gamePlayExtended.arrowMakeMove(Directions.EAST, 1);
//    assertEquals(messages1, Messages.WIN);
//  }
//
//  /**
//   * Test pit.
//   */
//  @Test
//  public void testPit() {
//    AbstractIMaze.setRandom(10);
//    iMaze = new PerfectMaze(4,3,false,30,20);
//    gamePlayExtended = new GamePlayExtended(iMaze, 1, 2);
//    Messages messages1 = gamePlayExtended.makeMove(Directions.WEST);
//    assertEquals(messages1, Messages.PIT);
//  }
//
//  /**
//   * Test bat.
//   */
//  @Test
//  public void testBAT() {
//    AbstractIMaze.setRandom(10);
//    IMaze iMaze1 = new PerfectMaze(4,3,false,30,20);
//    gamePlayExtended = new GamePlayExtended(iMaze1, 3, 2);
//    gamePlayExtended.makeMove(Directions.NORTH);
//    gamePlayExtended.makeMove(Directions.WEST);
//    Messages messages =  gamePlayExtended.makeMove(Directions.WEST);
//    assertEquals(messages, Messages.PIT);
//  }
//
//  /**
//   * Test arrow limit.
//   */
//  @Test
//  public void testArrowLimit() {
//    AbstractIMaze.setRandom(10);
//    IMaze iMaze2 = new PerfectMaze(4,3,false,30,20);
//    gamePlayExtended = new GamePlayExtended(iMaze2, 3, 2);
//    gamePlayExtended.arrowMakeMove(Directions.NORTH, 2);
//    gamePlayExtended.arrowMakeMove(Directions.NORTH, 2);
//    gamePlayExtended.arrowMakeMove(Directions.NORTH, 2);
//    Messages messages = gamePlayExtended.arrowMakeMove(Directions.NORTH, 2);
//    assertEquals(messages, Messages.NOARROWS);
//  }
//
//  /**
//   * Test arrow miss.
//   */
//  @Test
//  public void testArrowMiss() {
//    AbstractIMaze.setRandom(10);
//    IMaze iMaze3 = new PerfectMaze(4,3,false,30,20);
//    gamePlayExtended = new GamePlayExtended(iMaze3, 3, 2);
//    Messages messages = gamePlayExtended.arrowMakeMove(Directions.NORTH, 4);
//    assertEquals(messages, Messages.ARROWMISS);
//  }
//
//  /**
//   * Hint.
//   */
//  @Test
//  public void hintImpl() {
//    AbstractIMaze.setRandom(10);
//    IMaze iMaze4 = new PerfectMaze(4,3,false,30,20);
//    gamePlayExtended = new GamePlayExtended(iMaze4, 2, 1);
//    String string =  gamePlayExtended.hintImpl(2,1);
//    assertEquals(string, "Draft near by\n");
//  }
//
//  /**
//   * Test wampus miss.
//   */
//  @Test
//  public void testWampusMiss() {
//    AbstractIMaze.setRandom(10);
//    IMaze iMaze4 = new PerfectMaze(4,3,false,30,20);
//    gamePlayExtended = new GamePlayExtended(iMaze4, 3, 2);
//    Messages messages = gamePlayExtended.arrowMakeMove(Directions.NORTH, 2);
//    assertEquals(messages, Messages.WAMPUSMISS);
//  }
//
//  /**
//   * Test move check.
//   */
//  @Test
//  public void testMoveCheck() {
//    AbstractIMaze.setRandom(10);
//    IMaze iMaze4 = new PerfectMaze(4,3,false,30,20);
//    gamePlayExtended = new GamePlayExtended(iMaze4, 3, 2);
//    Messages messages = gamePlayExtended.makeMove(Directions.NORTH);
//    assertEquals(8,  gamePlayExtended.getPlayerPosition());
//  }
//
//  /**
//   * Test invalid move.
//   */
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidMove() {
//    AbstractIMaze.setRandom(10);
//    IMaze iMaze4 = new PerfectMaze(4,3,false,30,20);
//    gamePlayExtended = new GamePlayExtended(iMaze4, 3, 2);
//    Messages messages = gamePlayExtended.makeMove(Directions.SOUTH);
//    assertEquals(8,  gamePlayExtended.getPlayerPosition());
//  }
//
//  /**
//   * Test wampus smell check.
//   */
//  @Test
//  public void testWampusSmellCheck() {
//    AbstractIMaze.setRandom(10);
//    IMaze iMaze4 = new PerfectMaze(4,3,false,30,20);
//    gamePlayExtended = new GamePlayExtended(iMaze4, 1, 2);
//    String stringMessage = gamePlayExtended.hintImpl(1,2);
//    assertEquals(stringMessage,  "Wampus near by\n");
//  }
//
//
//}