package cs3500.music.View;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.View.View;

public interface MidiView extends View {
  /**
   * Plays all the notes at a beat i.
   */
  void playNote(int i) throws InvalidMidiDataException;

  /**
   * Plays the entire song from beginning to end.
   */
  void renderTotal() throws InvalidMidiDataException;
}