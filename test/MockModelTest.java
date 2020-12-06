import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import controller.Controller;
import maze.MockModel;

import static org.junit.Assert.assertEquals;


/**
 * The type Mock model test.
 */
public class MockModelTest {

  /**
   * The In.
   */
  Reader in;
  /**
   * The Out.
   */
  StringBuffer out;
  /**
   * The Controller.
   */
  Controller controller;
  /**
   * The Model.
   */
  MockModel model;
  /**
   * The Builder.
   */
  StringBuilder builder;

  /**
   * Sets up.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp() throws Exception {
    out = new StringBuffer();
    builder = new StringBuilder();
    model = new MockModel(builder, 10000);
  }

  /**
   * Arrow make move.
   *
   * @throws IOException the io exception
   */
  @Test
  public void arrowMakeMove() throws IOException {
    in = new StringReader("shoot east 4 q");
    controller = new Controller(in, out);
    controller.start(model);
    assertEquals("Input: 10000 10000EAST 4Input: 10000 10000", builder.toString());
    assertEquals("the player is current at 10000,10000\n"
            + "\n"
            + "please choose between shoot and move\n"
            + "Enter the direction to be shot\n"
            + "[]\n"
            + "Enter the number of moves the arrow has to be shot\n"
            + "Testing\n"
            + "the player is current at 10000,10000\n"
            + "\n"
            + "please choose between shoot and move\n", out.toString());
  }

  /**
   * Hint.
   *
   * @throws IOException the io exception
   */
  @Test
  public void hintImpl() throws IOException {
    in = new StringReader("q");
    controller = new Controller(in, out);
    controller.start(model);
    assertEquals("Input: 10000 10000", builder.toString());
    assertEquals("the player is current at 10000,10000\n"
            + "\n"
            + "please choose between shoot and move\n", out.toString());
  }

  /**
   * Gets moves.
   *
   * @throws IOException the io exception
   */
  @Test
  public void getMoves() throws IOException {
    in = new StringReader("q");
    controller = new Controller(in, out);
    controller.start(model);
    assertEquals("the player is current at 10000,10000\n"
            + "\n"
            + "please choose between shoot and move\n", out.toString());
  }

  /**
   * Make move.
   *
   * @throws IOException the io exception
   */
  @Test
  public void makeMove() throws IOException {
    in = new StringReader("move east q");
    controller = new Controller(in, out);
    controller.start(model);
    assertEquals("Input: 10000 10000Input: 10000 10000", builder.toString());
    assertEquals("the player is current at 10000,10000\n"
            + "\n"
            + "please choose between shoot and move\n"
            + "Enter the direction to be moved\n"
            + "[]\n"
            + "Testing\n"
            + "the player is current at 10000,10000\n"
            + "\n"
            + "please choose between shoot and move\n", out.toString());
  }

  /**
   * Gets player pos row.
   *
   * @throws IOException the io exception
   */
  @Test
  public void getPlayerPosRow() throws IOException {
    in = new StringReader("q");
    controller = new Controller(in, out);
    controller.start(model);
    assertEquals("the player is current at 10000,10000\n"
            + "\n"
            + "please choose between shoot and move\n", out.toString());
  }

  /**
   * Gets player pos column.
   *
   * @throws IOException the io exception
   */
  @Test
  public void getPlayerPosColumn() throws IOException {
    in = new StringReader("q");
    controller = new Controller(in, out);
    controller.start(model);
    assertEquals("the player is current at 10000,10000\n"
            + "\n"
            + "please choose between shoot and move\n", out.toString());
  }

  /**
   * Gets player position.
   *
   * @throws IOException the io exception
   */
  @Test
  public void getPlayerPosition() throws IOException {
    in = new StringReader("q");
    controller = new Controller(in, out);
    controller.start(model);
    assertEquals("the player is current at 10000,10000\n"
            + "\n"
            + "please choose between shoot and move\n", out.toString());
  }
}