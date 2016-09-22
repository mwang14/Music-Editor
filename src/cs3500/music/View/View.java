package cs3500.music.View;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

/**
 * This is an interface that represents all the different views.
 */
public interface View {
  /**
   * This renders whichever view is selected.
   */
  void render() throws InvalidMidiDataException, InterruptedException, IOException;

}