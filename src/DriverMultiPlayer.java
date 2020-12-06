import controller.ControllerGUI;
import view.IView;
import view.FormView;

public class DriverMultiPlayer {

  public static void main(String[] args) {

    ControllerGUI controller = new ControllerGUI();
    IView iView = new FormView("Welcome to the Game", controller);
    controller.setView(iView);
  }
}
