package cs3500.music.Controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyboardHandler implements KeyListener {
  private final Map<Integer, Runnable> Pressed;
  private final Map<Integer, Runnable> Typed;
  private final Map<Integer, Runnable> Released;

  public enum options {
    PRESSED, TYPED, RELEASED
  }

  /**
   * Creates the KeyHandler.
   */
  public KeyboardHandler() {
    Pressed = new HashMap<Integer, Runnable>();
    Typed = new HashMap<Integer, Runnable>();
    Released = new HashMap<Integer, Runnable>();
  }


  /**
   * This method Runs the corresponding Pressed key.
   */
  public void runPressed(int key) {
    if (Pressed.containsKey(key)) {
      Pressed.get(key).run();
    }
  }

  /**
   * This method runs the corresponding Typed key.
   */
  public void runTyped(int key) {
    if (Typed.containsKey(key)) {
      Typed.get(key).run();
    }
  }

  /**
   * This method runs the corresponding Released key.
   */
  public void runReleased(int key) {
    if (Released.containsKey(key)) {
      Released.get(key).run();
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (Typed.containsKey(e.getKeyCode())) {
      Typed.get(e.getKeyCode()).run();
    }
  }


  @Override
  public void keyPressed(KeyEvent e) {
    if (Pressed.containsKey(e.getKeyCode())) {
      Pressed.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (Released.containsKey(e.getKeyCode())) {
      Released.get(e.getKeyCode()).run();
    }
  }

  public void addRunnable(Runnable r, int i, options s) {
    if (s.equals(options.PRESSED)) {

      if (!Pressed.containsKey(i)) {
        Pressed.put(i, r);
      } else {
        Pressed.remove(i);
        Pressed.put(i, r);
      }

    } else if (s.equals(options.TYPED)) {
      if (!Typed.containsKey(i)) {
        Typed.put(i, r);
      } else {
        Typed.remove(i);
        Typed.put(i, r);
      }
      Typed.put(i, r);
    } else if (s.equals(options.RELEASED)) {
      if (!Released.containsKey(i)) {
        Released.put(i, r);
      } else {
        Released.remove(i);
        Released.put(i, r);
      }
    } else {
      throw new IllegalArgumentException("that's not a key event");
    }
  }
}