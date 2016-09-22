package cs3500.music.View;

import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;

import cs3500.music.Controller.KeyboardHandler;
import cs3500.music.iMusic.ANote;
import cs3500.music.iMusic.iMusic;

public class CompoundView extends JFrame implements ICompoundGuiMidi {
  private final GuiView gui;
  private final MidiView midi;
  private final iMusic sheet;
  private KeyboardHandler kbhandler = new KeyboardHandler();
  public CompoundView(iMusic sheet) {
    this.sheet = sheet;
    gui = new GuiViewFrame(sheet);
    midi = new MidiViewImpl(sheet);
  }

  /**
   * Combines the Midi and the Gui.
   */
  @Override
  public void render() throws InvalidMidiDataException, IOException, InterruptedException {
    gui.render();
    midi.render();
  }

  /**
   * Adds a KeyListener to the Gui view
   */
  public void setKeyListener(KeyboardHandler kbhandler) {
    this.kbhandler = kbhandler;
    this.gui.setKeyListener(kbhandler);
  }

  /**
   * Adds a mouseHandler to the Gui view
   */
  public void addMouseHandler(MouseListener mhandler) {
    this.gui.setMouseListener(mhandler);
  }

  /**
   * Returns the Gui
   */
  public GuiView getGui() {
    return this.gui;
  }

  /**
   * returns the Midi
   */
  public MidiView getMidi() {
    return this.midi;
  }

  @Override
  public  void addNote(int pitchNum,int octavenum,int  startTime, int endTime, int instrumentNum, int
          volumeNum) {
    sheet.addNote(pitchNum, octavenum, startTime, endTime, instrumentNum, volumeNum);
  }

  @Override
  public int songDuration() {
    return sheet.songDuration();
  }

  @Override
  public void addToBeat() {
    this.sheet.addToBeat();
  }

  @Override
  public void subtractFromBeat() {
    this.sheet.subtractFromBeat();
  }

  @Override
  public void setDuration(int duration) {
    this.sheet.setDuration(duration);
  }
  @Override
  public void removeNote(ANote note) {
    this.sheet.removeNote(note);
  }

  @Override
  public void addRepeat(Integer[] repeat) {
    this.sheet.addRepeat(repeat);
  }

  @Override
  public void addEndingSpot(ArrayList<Integer> endingSpot) {
    this.sheet.addEndingspots(endingSpot);
  }

  @Override
  public void setStart(int start) {
    this.sheet.setStart(start);
  }

  @Override
  public void setNumEndings(ArrayList<Integer> numEndings) {
    this.sheet.setnumEndings(numEndings);
  }

  @Override
  public void setEndStartTime(ArrayList<Integer> endstarttime) {
    this.sheet.setEndStartTime(endstarttime);
  }

  @Override
  public ANote noteHere(int a, int b, int c) {
    return this.sheet.noteHere(a, b, c);
  }

  @Override
  public void setRepeats() {
    this.sheet.setRepeats();
  }

  @Override
  public void setAltEndings() {
    this.sheet.setAltEndings();
  }
}