import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import controller.Controller;
import controller.IController;

import maze.AbstractIMaze;
import maze.GamePlayExtended;

import maze.IGamePlayExtended;
import maze.IMaze;
import maze.NonPerfectMaze;
import maze.PerfectMaze;

/**
 * The type Driver.
 */
public class Driver {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    try {
      Scanner scanner = new Scanner(System.in);
      AbstractIMaze.setRandom(10);

      System.out.println("Please enter the number of rows in maze");
      int noOfRows = scanner.nextInt();
      System.out.println("Please enter the number of columns in maze");
      int noOfColumns = scanner.nextInt();
      System.out.println("Please enter the bat percentage");
      int batPercentage = scanner.nextInt();
      System.out.println("Please enter the pit percentage");
      int pitPercentage = scanner.nextInt();
      IMaze iMaze = null;
      while (iMaze == null) {
        System.out.println("Please enter the kind of maze perfect or imperfect maze");
        String typeMaze = scanner.next();
        System.out.println("Please enter wrapping as true or false");
        Boolean typeWrapping = scanner.nextBoolean();

        switch (typeMaze.toLowerCase()) {
          case "perfect": {
            iMaze = new PerfectMaze(noOfRows, noOfColumns, typeWrapping,
                    batPercentage, pitPercentage);
            break;
          }
          case "imperfect": {
            System.out.println("Please enter remaining walls in the maze");
            int remainingWalls = scanner.nextInt();
            iMaze = new NonPerfectMaze(noOfRows, noOfColumns, remainingWalls, typeWrapping,
                    batPercentage, pitPercentage);
            break;
          }
          default:
            System.out.println("Please enter the write maze option");
        }
      }

      IGamePlayExtended gamePlay = new GamePlayExtended(iMaze);
      System.out.println("Please select position from the given list");
      System.out.println(iMaze.getAvailablePlayerPositions().toString());
      int position = scanner.nextInt();
      int[] location = iMaze.getNumberToCoordinate().get(position);
      gamePlay.updatePlayerPosition(location[0], location[1]);

      Readable reader = new InputStreamReader(System.in);

      IController controller = new Controller(reader, System.out);
      controller.start(gamePlay);
      System.out.println(gamePlay.getMoveMemory());

    } catch (IllegalArgumentException | IOException e) {
      e.printStackTrace();
    }
  }
}
