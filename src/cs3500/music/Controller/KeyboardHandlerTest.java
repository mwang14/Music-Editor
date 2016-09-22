package cs3500.music.Controller;

import org.junit.Test;

import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

/**
 * Created by Kathy on 11/24/2015.
 */
public class KeyboardHandlerTest {

  KeyboardHandler kbhandler = new KeyboardHandler();


  @Test
  public void testPressed1() {
    StringBuilder s = new StringBuilder();
    kbhandler.addRunnable(new RunnableMock(s), KeyEvent.VK_P, KeyboardHandler.options.PRESSED);
    kbhandler.runPressed(KeyEvent.VK_P);
    assertEquals(s.toString(), "test passed!");
  }

  @Test
  public void testTyped1() {
    StringBuilder s = new StringBuilder();
    kbhandler.addRunnable(new RunnableMock(s), KeyEvent.VK_L, KeyboardHandler.options.TYPED);
    kbhandler.runTyped(KeyEvent.VK_L);
    assertEquals(s.toString(), "test passed!");
  }

  @Test
  public void testReleased1() {
    StringBuilder s = new StringBuilder();
    kbhandler.addRunnable(new RunnableMock(s), KeyEvent.VK_K, KeyboardHandler.options.RELEASED);
    kbhandler.runReleased(KeyEvent.VK_K);
    assertEquals(s.toString(), "test passed!");
  }
}