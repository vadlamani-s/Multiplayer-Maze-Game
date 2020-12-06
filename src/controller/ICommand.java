package controller;

import maze.IGamePlayExtended;
import maze.Messages;

/**
 * The ICommand interface has the functionalities required for using a command design pattern. The
 * methods are common and are executed based on the object that is being initialised.
 */
public interface ICommand {

  /**
   * Execute messages method perform the task of move or shoot arrow based on the class that
   * is calling it.
   *
   * @param model the model
   * @return the messages that is returned of Enum Message type
   */
  Messages execute(IGamePlayExtended model);
}
