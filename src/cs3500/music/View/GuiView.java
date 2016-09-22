package cs3500.music.View;


import java.awt.event.MouseListener;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;

import cs3500.music.Controller.KeyboardHandler;

public interface GuiView extends View {

  /**
   * Updates the Scroll Pane based on where the red line is.
   */
  void updateScrollPane();

  /**
   * Returns the Scroll Pane of a GuiView.
   *
   * @return JScrollPane
   */
  JScrollPane getScrollPane();

  /**
   * Sets the KeyListener of a GuiView.
   */
  void setKeyListener(KeyboardHandler kbhandler);

  /**
   * Sets the MouseListener of a GuiView.
   */
  void setMouseListener(MouseListener mhandler);

  /**
   * Determines if there is a Note at the specified Coordinate.
   *
   * @return boolean
   */
  boolean NoteatCoordinate(int x, int y);

  /**
   * Updates the Horizontal Scrollbar based on the parameter adjust.
   */
  void updateHorizontalScroll(int adjust);

  /**
   * Updates the Vertical Scrollbar based on the parameter adjust.
   */
  void updateVerticalScroll(int adjust);
}
