import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import maze.Directions;
import maze.IMaze;
import maze.NonPerfectMaze;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * A JUnit test class for the NonPerfectMazeTest class.
 */
public class NonPerfectMazeTest {
  IMaze iMaze;

  @Before
  public void setUp() throws Exception {
    iMaze = new NonPerfectMaze(10, 10, 10, true,
            5, 5);
  }

  @Test
  public void checkConstructor() {
    try {
      new NonPerfectMaze(10, 10, 10, true,
              5, 5);
    } catch (Exception e) {
      fail("shouldn't be here");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkConstructorInvalid() {
    new NonPerfectMaze(10, 0,
            10, true, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkConstructorInvalid1() {
    new NonPerfectMaze(0, 10,
            10, true, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkConstructorInvalid2() {
    new NonPerfectMaze(10, 10, 10, true,
            11, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkConstructorInvalid3() {
    new NonPerfectMaze(10, 10, 10, true, 4,
            11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkConstructorInvalid4() {
    new NonPerfectMaze(-1, 10, 10, true,
            4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkConstructorInvalid5() {
    new NonPerfectMaze(10, 10, 101, true,
            4, 4);
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
    int remainingWalls = 10 + rows + columns;
    assertEquals(remainingWalls, iMaze.getWallCount());
  }

  @Test
  public void testMazeWallRemovedCount() {
    IMaze iMaze1 = new NonPerfectMaze(10, 10, 10,
            false, 5, 5);
    iMaze1.createMaze();
    int rows = iMaze1.getRows();
    int columns = iMaze1.getColumns();
    int totalWalls = rows * (columns - 1) + columns * (rows - 1);
    int remainingWalls = 10;
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
    IMaze iMaze1 = new NonPerfectMaze(3, 3, 3,
            false, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(1, 1);
    int[] temp = {1, 2};
    int[] location = iMaze1.move(directionMap, Directions.EAST);
    assertArrayEquals(temp, location);
  }

  @Test(expected = AssertionError.class)
  public void testMoveWallExist() {
    IMaze iMaze1 = new NonPerfectMaze(3, 3, 3,
            false, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(1, 1);
    int[] temp = {1, 0};
    int[] location = iMaze1.move(directionMap, Directions.WEST);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveNorth() {
    IMaze iMaze1 = new NonPerfectMaze(3, 3, 3,
            false, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(1, 1);
    int[] temp = {0, 1};
    int[] location = iMaze1.move(directionMap, Directions.NORTH);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveSouth() {
    IMaze iMaze1 = new NonPerfectMaze(3, 3, 3,
            false, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(1, 1);
    int[] temp = {2, 1};
    int[] location = iMaze1.move(directionMap, Directions.SOUTH);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveNorthWrapping() {
    IMaze iMaze1 = new NonPerfectMaze(3, 3, 3,
            true, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(0, 1);
    int[] temp = {2, 1};
    int[] location = iMaze1.move(directionMap, Directions.NORTH);
    assertArrayEquals(temp, location);
  }

  @Test(expected = AssertionError.class)
  public void testMoveWestWrappingInvalid() {
    IMaze iMaze1 = new NonPerfectMaze(3, 3, 3,
            true, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(1, 0);
    int[] temp = {1, 6};
    int[] location = iMaze1.move(directionMap, Directions.WEST);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveWestWrapping() {
    IMaze iMaze1 = new NonPerfectMaze(3, 3, 3,
            true, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(0, 0);
    int[] temp = {0, 2};
    int[] location = iMaze1.move(directionMap, Directions.WEST);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveSouthWrapping() {
    IMaze iMaze1 = new NonPerfectMaze(3, 3, 3,
            true, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(2, 1);
    int[] temp = {0, 1};
    int[] location = iMaze1.move(directionMap, Directions.SOUTH);
    assertArrayEquals(temp, location);
  }

  @Test
  public void testMoveEastWrapping() {
    IMaze iMaze1 = new NonPerfectMaze(3, 3, 3,
            true, 2, 2);
    iMaze1.createMaze();
    Map<Directions, int[]> directionMap = iMaze1.currMoveList(0, 2);
    int[] temp = {0, 0};
    int[] location = iMaze1.move(directionMap, Directions.EAST);
    assertArrayEquals(temp, location);
  }

}