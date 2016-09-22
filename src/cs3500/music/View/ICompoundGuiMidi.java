package cs3500.music.View;

import java.util.ArrayList;

import cs3500.music.iMusic.ANote;

public interface ICompoundGuiMidi extends ICompoundView {


  /**
   * Returns the GuiView of a ICompoundGuiMidi
   *
   * @return GuiView
   */
  GuiView getGui();

  /**
   * Returns the MidiView of an ICompoundGuiMidi
   *
   * @return MidiView
   */
  MidiView getMidi();

  void addNote(int pitchNum,int octavenum,int  startTime, int endTime, int instrumentNum, int
          volumeNum);

  int songDuration();

  void addToBeat();

  void subtractFromBeat();

  void setDuration(int duration);

  void removeNote(ANote note);

  void addRepeat(Integer[] repeat);

  void addEndingSpot(ArrayList<Integer> endingspot);

  void setStart(int start);

  void setNumEndings(ArrayList<Integer> numEndings);

  void setEndStartTime(ArrayList<Integer> endstartTime);

  ANote noteHere(int a, int b, int c);

  void setRepeats();

  void setAltEndings();
}