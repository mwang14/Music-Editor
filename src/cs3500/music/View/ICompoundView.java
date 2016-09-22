package cs3500.music.View;

import java.awt.event.MouseListener;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.Controller.KeyboardHandler;

public interface ICompoundView extends View {
  /**
   * Sets the KeyListener of an ICompoundView
   */
  void setKeyListener(KeyboardHandler kbhandler);

  /**
   * Sets the MouseListener of an ICompoundView
   */
  void addMouseHandler(MouseListener mhandler);

}