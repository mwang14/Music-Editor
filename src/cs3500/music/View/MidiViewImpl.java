package cs3500.music.View;
//package cs3500.music.view;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.iMusic.ANote;
import cs3500.music.iMusic.iMusic;

/**
 * A skeleton for MIDI playback
 */

public class MidiViewImpl implements MidiView {
  private Synthesizer synth;
  private Receiver receiver;
  private iMusic sheet;
  Timer t = new Timer();


  public MidiViewImpl(iMusic sheet) {
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
      this.sheet = sheet;
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  // Convenience contructor that allows to choose between mock receiver and the actual receiver
  public MidiViewImpl(iMusic sheet, Synthesizer synth) {
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.sheet = sheet;
      this.synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void playNote(int i) throws InvalidMidiDataException {
    ArrayList<ANote> notes = sheet.notesStartPlayingAtRow(i);
    for (ANote n : notes) {
      ShortMessage start = new ShortMessage();
      ShortMessage end = new ShortMessage();
      start.setMessage(ShortMessage.NOTE_ON, n.getInstrument(), n.totalNotePitch(),
              n.getVolume());
      end.setMessage(ShortMessage.NOTE_OFF, n.getInstrument(), n.totalNotePitch(),
              n.getVolume());
      receiver.send(start, -1);
    }

    ArrayList<ANote> notesending = sheet.notesEndingAtBeat(i);
    for (ANote note : notesending) {
      ShortMessage end2 = new ShortMessage();
      end2.setMessage(ShortMessage.NOTE_OFF, note.getInstrument(), note.totalNotePitch(),
              note.getVolume());
      receiver.send(end2, -1);
    }
  }

  @Override
  public void render() throws InvalidMidiDataException {

    this.playNote(sheet.getBeat());
  }

  @Override
  public void renderTotal() throws InvalidMidiDataException {
    t.schedule(new playNotes(), 0, sheet.getTempo() /
            1000);
  }


  /**
   * a Render method which can be tested to ensure that the Midi is working properly.
   */
  public void renderForTest() throws InvalidMidiDataException, InterruptedException {
    ShortMessage start = new ShortMessage();
    ShortMessage end = new ShortMessage();
    for (int i = 0; i <= sheet.songDuration(); i++) {
      for (ANote n : sheet.notesStartPlayingAtRow(i)) {
        start.setMessage(ShortMessage.NOTE_ON, n.getInstrument(), n.totalNotePitch(),
                n.getVolume());
        end.setMessage(ShortMessage.NOTE_OFF, n.getInstrument(), n.totalNotePitch(),
                n.getVolume());
        receiver.send(start, -1);
        receiver.send(end, synth.getMicrosecondPosition() + n.getEndTime() * sheet.getTempo());
      }
      Thread.sleep(sheet.getTempo() / 1000);
    }
    this.receiver.close(); // Only call this once you're done playing *all* notes
  }

  /**
   * TimerTask to play all the Notes in Midi
   */
  private class playNotes extends TimerTask {
    int time = 0;

    @Override
    public void run() {
      if (time == sheet.songDuration()) {
        System.exit(0);
      }
      try {
        playNote(time);
        time++;
      } catch (InvalidMidiDataException e) {
        throw new IllegalArgumentException("no");
      }
    }
  }


  @Override
  public String toString() {
    return this.receiver.toString();
  }


}



