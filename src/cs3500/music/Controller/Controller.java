package cs3500.music.Controller;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

public interface Controller {

  /**
   * Starts the Controller which controls all the Key Events and Mouse Events in a Compound View.
   * @throws IOException
   * @throws InvalidMidiDataException
   * @throws InterruptedException
   */
  void start() throws IOException, InvalidMidiDataException,
          InterruptedException;
}
