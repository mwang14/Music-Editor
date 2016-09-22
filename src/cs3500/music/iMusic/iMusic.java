package cs3500.music.iMusic;

import java.util.ArrayList;
import java.util.TreeMap;

public interface iMusic {

  /**
   * Sets the stage to repeat
   *
   */

  void setRepeat(int start, int end);

  void setBeat(int beat);

  void setAltEndings();

  void addRepeat(Integer[] repeat);

  void setRepeats();

  void setStart(int start);

  void setnumEndings(ArrayList<Integer> repeats);

  void addEndingspots(ArrayList<Integer> endingspot);

  void setEndStartTime(ArrayList<Integer> endstarttime);

  ArrayList<Integer[]> getRepeats();

  ArrayList<ArrayList<Integer>> getAltEndings();

  boolean getAlternateEnding();

  int getStart();

  ArrayList<Integer> getEndTimeStart();


  /**
   * Adds a note to the song piece.
   *
   * @throws IllegalArgumentException if trying to add an already existing note
   */
  void addNote(int newPitch, int newOctave, int newStartTime, int newEndTime, int
          newInstrument, int newVolume);

  /**
   * Changes an existing note's values by replacing them with a new note values.
   *
   * @throws IllegalArgumentException if note to be changed is not in the sheet.
   */
  void changeNote(int pitchFrom, int octaveFrom, int startTimeFrom, int endTimeFrom, int
          instrumentFrom, int volumeFrom, int pitchTo, int octaveTo, int startTimeTo,
                  int endTimeTo,
                  int instrumentTo, int volumeTo);

  /**
   * Removes a note from the sheet music to be played.
   *
   * @throws IllegalArgumentException if note you want to remove is not in the sheet.
   */
  void removeNote(ANote newNote);

  /**
   * Returns the duration of the song in beats.
   *
   * @return An integer representing length of the song.
   */
  int songDuration();

  /**
   * Returns the Notes being played in the row (time beat) as a string.
   *
   * @return A string describing the nodes that are being played at the given row (beat).
   */
  String notesPlayingAtRowAsString(int rowNumber);

  /**
   * Returns the lowest octave that is played in this sheet of music.
   *
   * @return an integer that is the lowest octave played in this piece.
   */
  int getLowestOctave();

  /**
   * Returns a String that represents the note (with details) with the lowest key in this sheet
   * music (or piece).
   *
   * @return a String that represents the note played at the lowest key
   */
  String lowestNoteString();

  /**
   * Returns the highest octave that is played in this sheet of music.
   *
   * @return an integer that is the highest octave played in this piece.
   */
  int getHighestOctave();

  /**
   * Returns a String that represents the note (with details) with the highest key in this sheet
   * music (or piece).
   *
   * @return a String that represents the note played at the highest key
   */

  String highestNoteString();

  /**
   * Returns an ArrayList of Strings that represents the range of all the notes in the sheet music
   * (song) to be used to represent the sheet.
   *
   * @return an ArrayList of Strings of the range of all possible notes in the song in blank.
   */
  ArrayList<String> notesFromLowestToHighest();

  /**
   * This returns the range of all notes in the piece.
   *
   * @return Integer representing the range of all notes in a piece
   */
  int noteRange();

  /**
   * This returns an abstracted list of Notes in the piece the starting at the Row.
   *
   * @return an ArrayList of ANotes
   */
  ArrayList<ANote> notesStartPlayingAtRow(int time);

  /**
   * This returns the notes currently playing at the row.
   *
   * @return an ArrayList of ANotes
   */
  ArrayList<ANote> notesPlayingAtRow(int time);

  /**
   * This returns the Tempo of the piece.
   *
   * @return an Integer representing the tempo
   */
  int getTempo();

  /**
   * This returns the Treemap of all the notes in the piece.
   *
   * @return a Treemap where each Integer represents the beat
   */
  TreeMap<Integer, ArrayList<ANote>> getNotes();


  /**
   * This takes in a beat/row, and prints out all the notes playing at that beat as a String.
   *
   * @return String
   */
  String rowToString(int rowNumber);

  /**
   * This takes a String s, and pads it to numPad digits.
   *
   * @return a padded String
   */
  String pad(String s, int numPad);

  /**
   * This returns the lowest ANote in the piece.
   *
   * @return ANote which is the lowest
   */
  ANote lowestNote();

  /**
   * This returns the highest ANote in the piece.
   *
   * @return ANote which is the highest
   */
  ANote highestNote();

  /**
   * This takes in an int n which represents a pitch, and returns it as a String.
   *
   * @return String of pitch which represents n
   */
  String pitchToString(int n);

  /**
   * This sets the music's tempo to the newTempo.
   */
  void setTempo(int newTempo);

  /**
   * This sets the Notes of a piece of music to notes.
   */
  void setNotes(TreeMap<Integer, ArrayList<ANote>> notes);


  /**
   * This Increments the beat by 1.
   */
  void addToBeat();

  /**
   * This Returns the beat.
   */
  int getBeat();

  /**
   * This Subtracts 1 from the beat.
   */

  void subtractFromBeat();

  /**
   * This Returns the notes ending at the beat.
   */
  ArrayList<ANote> notesEndingAtBeat(int beat);

  /**
   * This determines if a there is a note playing at a certain time.
   */
  boolean containsNoteHere(int start, int end, int pitchFromLowest);

  /**
   * This returns how many notes there are between this note and the Highest.
   */
  int pitchFromHighest(ANote note);

  /**
   * This returns all the notes either starting or playing at a given beat.
   *
   * @return ArrayList<ANote>
   */

  ArrayList<ANote> allNotesPlayingAtRow(int beat);

  /**
   * This returns the Note given the start, end, and the pitch form the highest
   *
   * @return ANote
   */
  ANote noteHere(int start, int end, int pitchFromHighest);

  /**
   * Sets the Duration of the piece to the gvien duration.
   */
  void setDuration(int Duration);

  /**
   * Returns the duration of the piece.
   *
   * @return Int representing the duration
   */
  int getDuration();

  /**
   * Append 2 pieces of Music
   */

  void append(iMusic that);
  /**
   * Increments the model's beat value
   *
   */
  //void incBeat()
}
