package maze;

/**
 * The enum Messages is a enum type having pre defined messages that are printed during the
 * gameplay.
 */
public enum Messages {

  /**
   * The Bat encounter message.
   */
  BAT("Bat just picked you up"),
  /**
   * The Bat missed the pickup.
   */
  NOTBAT(":(, Bat missed you!!!"),
  /**
   * The Pit encounter message.
   */
  PIT("You fell into a pit"),
  /**
   * The Wampus encounter message.
   */
  WAMPUS("You just encountered a Wampus and were killed!!!"),
  /**
   * The Gameover message.
   */
  GAMEOVER("The game is over"),
  /**
   * The Win message.
   */
  WIN("You win the game"),
  /**
   * The Arrowmiss message.
   */
  ARROWMISS("Arrow missed"),
  /**
   * The NoArrows message.
   */
  NOARROWS("You have no arrows left. Game's Over"),
  /**
   * The Wampusmiss message.
   */
  WAMPUSMISS("You missed the Wampus"),
  /**
   * The Movesuccessful message.
   */
  MOVESUCCESSFUL("Move made successfully"),
  /**
   * The Arrowsuccessful message.
   */
  ARROWSUCCESSFUL("Arrow shot successfully"),

  /**
   * The BatPit message.
   */
  BATPIT("Bat picked you up and dropped you on the pit"),

  /**
   * The BatWampus message.
   */
  BATWAMPUS("Bat picked you up and dropped you on the Wampus :("),

  /**
   * Default message.
   */
  DEFAULT("Testing");

  private final String message;

  Messages(String message) {
    this.message = message;
  }

  /**
   * Gets messages.
   *
   * @param messages the messages
   * @return the messages
   */
  public static String getMessages(Messages messages) {
    return messages.message;
  }

}




