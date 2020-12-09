import controller.ControllerGui;
import view.FormView;
import view.IView;


/**
 * The type Driver multi player is used for running the maze between text and GUI based maze game.
 */
public class DriverMultiPlayer {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    if (args.length <= 0) {
      throw new IllegalArgumentException("Argument not provided");
    } else {
      if (args[0].equals("--text")) {
        Driver.main(args);
      }
      else if (args[0].equals("--gui")) {
        ControllerGui controller = new ControllerGui();
        IView iView = new FormView("Welcome to the Game", controller);
        controller.setView(iView);
      } else {
        throw new IllegalArgumentException("Incorrect argument provided");
      }
    }
  }
}
