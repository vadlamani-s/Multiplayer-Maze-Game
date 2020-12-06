package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import maze.Directions;
import maze.IGamePlayExtended;
import maze.Messages;

/**
 * The Controller implements the IController interface and has methods to start the game using the
 * model which is taken as an input.The controller takes in the generic tye of input and output
 * giving the choice to take input from any form.
 */
public class Controller implements IController {

  private final Readable in;
  private final Appendable out;

  /**
   * Instantiates a new Controller with a generic inout and output types.
   *
   * @param in  the in of inout type
   * @param out the out of output type
   */
  public Controller(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void start(IGamePlayExtended model) throws IOException {

    Scanner scanner = new Scanner(in);
    ICommand iCommand = null;

    HashMap<String, Directions> directionMap = new HashMap<>();
    directionMap.put("east", Directions.EAST);
    directionMap.put("west", Directions.WEST);
    directionMap.put("north", Directions.NORTH);
    directionMap.put("south", Directions.SOUTH);

    while (true) {
      try {
        this.out.append("the player is current at ").append(String.valueOf(model.getPlayerPosRow()))
                .append(",").append(String.valueOf(model.getPlayerPosColumn())).append("\n");
        this.out.append(model.hintImpl(model.getPlayerPosRow(),
                model.getPlayerPosColumn())).append("\n");
        this.out.append("please choose between shoot and move").append("\n");
        String choice = scanner.next();
        switch (choice) {
          case "q":
          case "quit":
            return;
          case "move":
            this.out.append("Enter the direction to be moved").append("\n");
            this.out.append(model.getMoves(model.getPlayerPosRow(), model.getPlayerPosColumn())
                    .toString()).append("\n");
            String direction = scanner.next();
            iCommand = new Move(directionMap.get(direction));
            break;
          case "shoot":
            this.out.append("Enter the direction to be shot").append("\n");
            this.out.append(model.getMoves(model.getPlayerPosRow(), model.getPlayerPosColumn())
                    .toString()).append("\n");
            direction = scanner.next();
            this.out.append("Enter the number of moves the arrow has to be shot").append("\n");
            int moves = scanner.nextInt();
            iCommand = new Shoot(directionMap.get(direction), moves);
            break;
          default:
            this.out.append("Invalid Command");
            iCommand = null;
            break;
        }
        if (iCommand != null) {
          Messages messages = iCommand.execute(model);
          this.out.append(Messages.getMessages(messages)).append("\n");
          if (Messages.GAMEOVER == messages) {
            return;
          }
          if (Messages.WIN == messages) {
            return;
          }
          if (Messages.PIT == messages) {
            this.out.append("Game Over").append("\n");
            return;
          }
          if (Messages.WAMPUS == messages) {
            this.out.append("Game Over").append("\n");
            return;
          }
          if (Messages.NOARROWS == messages) {
            this.out.append("Game Over").append("\n");
            return;
          }

        }

      } catch (IOException | IllegalArgumentException e) {
        this.out.append(e.getMessage()).append("\n");
      }
    }
  }


}
