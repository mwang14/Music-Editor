package cs3500.music.Controller;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kathy on 11/24/2015.
 */
public class MouseHandlerTest {
  MouseHandler mhandler = new MouseHandler();

  @Test
  public void testClicked1() {
    StringBuilder s = new StringBuilder();
    mhandler.addMouseEvent(0, new MouseRunMock(s), MouseHandler.mouseOptions.CLICKED);
    mhandler.runClicked(0);
    assertEquals(s.toString(), "Mouse Works");
  }

  @Test
  public void testClicked2() {
    StringBuilder s = new StringBuilder();
    mhandler.addMouseEvent(1, new MouseRunMock(s), MouseHandler.mouseOptions.CLICKED);
    mhandler.runClicked(1);
    assertEquals(s.toString(), "Mouse Works");
  }

  @Test
  public void testClicked3() {
    StringBuilder s = new StringBuilder();
    mhandler.addMouseEvent(2, new MouseRunMock(s), MouseHandler.mouseOptions.CLICKED);
    mhandler.runClicked(2);
    assertEquals(s.toString(), "Mouse Works");
  }

  @Test
  public void testPressed1() {
    StringBuilder s = new StringBuilder();
    mhandler.addMouseEvent(0, new MouseRunMock(s), MouseHandler.mouseOptions.PRESSED);
    mhandler.runPressed(0);
    assertEquals(s.toString(), "Mouse Works");
  }

  @Test
  public void testPressed2() {
    StringBuilder s = new StringBuilder();
    mhandler.addMouseEvent(1, new MouseRunMock(s), MouseHandler.mouseOptions.PRESSED);
    mhandler.runPressed(1);
    assertEquals(s.toString(), "Mouse Works");
  }

  @Test
  public void testPressed3() {
    StringBuilder s = new StringBuilder();
    mhandler.addMouseEvent(2, new MouseRunMock(s), MouseHandler.mouseOptions.PRESSED);
    mhandler.runPressed(2);
    assertEquals(s.toString(), "Mouse Works");
  }

  @Test
  public void testReleased1() {
    StringBuilder s = new StringBuilder();
    mhandler.addMouseEvent(0, new MouseRunMock(s), MouseHandler.mouseOptions.RELEASED);
    mhandler.runReleased(0);
    assertEquals(s.toString(), "Mouse Works");
  }

  @Test
  public void testReleased2() {
    StringBuilder s = new StringBuilder();
    mhandler.addMouseEvent(1, new MouseRunMock(s), MouseHandler.mouseOptions.RELEASED);
    mhandler.runReleased(1);
    assertEquals(s.toString(), "Mouse Works");
  }

  @Test
  public void testReleased3() {
    StringBuilder s = new StringBuilder();
    mhandler.addMouseEvent(2, new MouseRunMock(s), MouseHandler.mouseOptions.RELEASED);
    mhandler.runReleased(2);
    assertEquals(s.toString(), "Mouse Works");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testButtonOutOfBounds1() {
    StringBuilder s = new StringBuilder();
    mhandler.addMouseEvent(-1, new MouseRunMock(s), MouseHandler.mouseOptions.CLICKED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testButtonOutOfBounds2() {
    StringBuilder s = new StringBuilder();
    mhandler.addMouseEvent(3, new MouseRunMock(s), MouseHandler.mouseOptions.CLICKED);
  }

}