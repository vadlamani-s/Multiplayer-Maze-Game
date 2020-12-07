import controller.ControllerGUI;
import view.IView;
import view.FormView;

public class DriverMultiPlayer {

  public static void main(String[] args) {

    if (args.length == 0) {
      throw new IllegalArgumentException("Argument not provided");
    } else {
      if (args[0].equals("--text")) {
        Driver.main(args);
      }
      else if (args[0].equals("--gui")) {
        ControllerGUI controller = new ControllerGUI();
        IView iView = new FormView("Welcome to the Game", controller);
        controller.setView(iView);
      } else {
        throw new IllegalArgumentException("Incorrect argument provided");
      }
    }
  }
}
