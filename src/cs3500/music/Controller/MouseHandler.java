package cs3500.music.Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
  private final MouseRun[] Pressed;
  private final MouseRun[] Clicked;
  private final MouseRun[] Released;

  /**
   * Creates a MouseHandler that controls the mouse events.
   */
  public MouseHandler() {
    Pressed = new MouseRun[3];
    Clicked = new MouseRun[3];
    Released = new MouseRun[3];
  }

  public enum mouseOptions {
    PRESSED, CLICKED, RELEASED
  }

  /**
   * This runs the corresponding event in the Pressed array.
   */
  public void runPressed(int key) {
    if (key < 0 || key > 2) {
      throw new IllegalArgumentException("That's not an event");
    }
    Pressed[key].run(5, 5);
  }

  /**
   * This runs the corresponding event in the Clicked array.
   */
  public void runClicked(int key) {
    if (key < 0 || key > 2) {
      throw new IllegalArgumentException("That's not an event");
    }
    Clicked[key].run(5, 5);
  }

  /**
   * This runs the corresponding event in the Released array.
   */
  public void runReleased(int key) {
    if (key < 0 || key > 2) {
      throw new IllegalArgumentException("That's not an event");
    }
    Released[key].run(5, 5);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // System.out.println("clicked");
//   Clicked[e.getButton() - 1].run(e.getX(), e.getY());
  }

  @Override
  public void mousePressed(MouseEvent e) {
    Pressed[e.getButton() - 1].run(e.getX(), e.getY());
  }

  @Override
  public void mouseReleased(MouseEvent e) {
//   Released[e.getButton() - 1].run(e.getX(), e.getY());
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // System.out.println("entered");
  }

  @Override
  public void mouseExited(MouseEvent e) {
    //  System.out.println("exited");
  }

  /**
   * Adds a mouseEvent to a MouseHandler
   */
  public void addMouseEvent(int button, MouseRun run, mouseOptions s) {
    if (button < 0 || button > 2) {
      throw new IllegalArgumentException("that's not a mouse button");
    }

    if (s.equals(mouseOptions.PRESSED)) {
      this.Pressed[button] = run;
    } else if (s.equals(mouseOptions.CLICKED)) {
      this.Clicked[button] = run;
    } else if (s.equals(mouseOptions.RELEASED)) {
      this.Released[button] = run;
    } else {
      throw new IllegalArgumentException("that's not a mouse event");
    }
  }

}