package controller;

import java.io.IOException;

import maze.IGamePlayExtended;

/**The interface IController is has all the methods required to run a controller. The controller
 * acts as the brain of project delegating work between the view and the model.
 */

public interface IController {

  /**
   * The start method is called from the driver class and the model is taken as a parameter.
   *
   * @param model the model of the project
   * @throws IOException the io exception
   */
  void start(IGamePlayExtended model) throws IOException;
}
