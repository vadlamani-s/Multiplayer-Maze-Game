import org.junit.Before;
import org.junit.Test;

import java.util.Map;


import maze.Directions;
import maze.IMaze;
import maze.PerfectMaze;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for the PerfectMazeTest class.
 */
public class PerfectMazeTest {

  IMaze iMaze;

  @Before
  public void setUp() throws Exception {
    iMaze = new PerfectMaze(10, 10, true, 5, 5);
  }

  @Test
  public void checkConstructor() {
    try {
      new PerfectMaze(10, 10, true, 5, 5);
    } catch (Exception e) {
      fail("shouldn't be here");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkConstructorInvalid() {
    new PerfectMaze(10, 0,
            true, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkConstructorInvalid1() {
    new PerfectMaze(0, 10,
            true, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkConstructorInvalid2() {
    new PerfectMaze(10, 10,
            true, 11, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkConstructorInvalid3() {
    new PerfectMaze(10, 10,
            true, 4, 11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkConstructorInvalid4() {
    new PerfectMaze(-1, 10,
            true, 4, 4);
  }


  @Test
  public void testMazeSizeRow() {
    assertEquals(10, iMaze.getRows());
  }

  @Test
  public void testMazeSizeColumn() {
    assertEquals(10, iMaze.getColumns());
  }


  @Test
  public void testMazeWallRemovedCountWrapping() {
    iMaze.createMaze();
    int rows = iMaze.getRows();
    int columns = iMaze.getColumns();
    int totalWalls = rows * (columns - 1) + columns * (rows - 1) + rows + columns;
    int remainingWalls = totalWalls - rows * columns + 1;
    assertEquals(remainingWalls, iMaze.getWallCount());
  }

  @Test
  public void testMazeWallRemovedCount() {
    IMaze iMaze1 = new PerfectMaze(10, 10, false, 5, 5);
    iMaze1.createMaze();
    int rows = iMaze1.getRows();
    int columns = iMaze1.getColumns();
    int totalWalls = rows * (columns - 1) + columns * (rows - 1);
    int remainingWalls = totalWalls - rows * columns + 1;
    assertEquals(remainingWalls, iMaze1.getWallCount());
  }


  @Test
  public void testMoveByOnePosition() {
    iMaze.createMaze();
    Map<Directions, int[]> directionMap = iMaze.currMoveList(1, 1);
    int[] newPos = {0, 1};
    int[] location = iMaze.move(directionMap, Directions.NORTH);
    int count = location[0] - newPos[0] + location[0] - newPos[0];
    assertEquals(0, count);
  }


  @Test
  public void testMoveByOnePositionArray() {
    iMaze.createMaze();
    Map<Directions, int[]> directionMap = iMaze.currMoveList(0, 0);
    int[] temp = {9, 0};
    int[] location = iMaze.move(directionMap, Directions.NORTH);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveEast() {
    IMaze iMaze1 = new PerfectMaze(3, 3, false, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(1, 1);
    int[] temp = {1, 2};
    int[] location = iMaze1.move(directionMap, Directions.EAST);
    assertArrayEquals(temp, location);
  }

  @Test(expected = AssertionError.class)
  public void testMoveWallExist() {
    IMaze iMaze1 = new PerfectMaze(3, 3, false, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(1, 1);
    int[] temp = {1, 0};
    int[] location = iMaze1.move(directionMap, Directions.WEST);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveNorth() {
    IMaze iMaze1 = new PerfectMaze(3, 3, false, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(1, 1);
    int[] temp = {0, 1};
    int[] location = iMaze1.move(directionMap, Directions.NORTH);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveSouth() {
    IMaze iMaze1 = new PerfectMaze(3, 3, false, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(1, 1);
    int[] temp = {2, 1};
    int[] location = iMaze1.move(directionMap, Directions.SOUTH);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveNorthWrapping() {
    IMaze iMaze1 = new PerfectMaze(5, 7, true, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(0, 0);
    int[] temp = {4, 0};
    int[] location = iMaze1.move(directionMap, Directions.NORTH);
    assertArrayEquals(temp, location);
  }

  @Test(expected = AssertionError.class)
  public void testMoveWestWrappingInvalid() {
    IMaze iMaze1 = new PerfectMaze(5, 7, true, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(1, 0);
    int[] temp = {1, 6};
    int[] location = iMaze1.move(directionMap, Directions.WEST);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveWestWrapping() {
    IMaze iMaze1 = new PerfectMaze(5, 7, true, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(2, 0);
    int[] temp = {2, 6};
    int[] location = iMaze1.move(directionMap, Directions.WEST);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveSouthWrapping() {
    IMaze iMaze1 = new PerfectMaze(5, 7, true, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(4, 6);
    int[] temp = {0, 6};
    int[] location = iMaze1.move(directionMap, Directions.SOUTH);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveEastWrapping() {
    IMaze iMaze1 = new PerfectMaze(5, 7, true, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(0, 6);
    int[] temp = {0, 0};
    int[] location = iMaze1.move(directionMap, Directions.EAST);
    assertArrayEquals(temp, location);
  }

}