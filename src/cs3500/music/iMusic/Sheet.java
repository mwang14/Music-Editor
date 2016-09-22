package cs3500.music.iMusic;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import cs3500.music.Util.CompositionBuilder;

public class Sheet implements iMusic {


  //Builder class
  public static final class Builder implements CompositionBuilder<Sheet> {

    private int tempo;
    ArrayList<ANote> Notes;
    ArrayList<Integer[]> repeats;
    ArrayList<ArrayList<Integer>> endingTimes;
    int start;
    ArrayList<Integer> numEndings;
    int altEndingsStart;
    ArrayList<Integer> endstarttime;

//    private int start = 0;
//    private ArrayList<Integer> numEndings;
//    private int altEndingsStart = 0;
//    private ArrayList<Integer>endstarttime = new ArrayList<Integer>();
//    int a = 0;
//    boolean alternateEnding = false;
//    private ArrayList<ArrayList<Integer>> altendingspots = new ArrayList<ArrayList<Integer>>();


    public Builder() {
      this.tempo = 5;
      this.Notes = new ArrayList<ANote>();
      this.repeats = new ArrayList<Integer[]>();
      this.endingTimes = new ArrayList<ArrayList<Integer>>();
      this.start = 0;
      this.numEndings = new ArrayList<Integer>();
      this.endstarttime = new ArrayList<Integer>();
      this.altEndingsStart = 0;

    }

    @Override
    public Sheet build() {
      Sheet s = new Sheet();
      s.setTempo(this.tempo);
      s.setStart(this.start);
      s.setnumEndings(this.numEndings);
      s.setEndStartTime(this.endstarttime);

      for (ANote n : Notes) {
        // System.out.println(n.getStartTime());
        s.addNote(n.getPitch(), n.getOctave(), n.getStartTime(), n.getEndTime(), n
                .getInstrument(), n.getVolume());
      }

      for (Integer[] n : repeats) {
        s.addRepeat(n);
      }

      for (ArrayList<Integer> n : endingTimes) {
        s.addEndingspots(n);
      }
      return s;
      //return new Sheet(this.tempo, this.Notes);
    }

    @Override
    public CompositionBuilder<Sheet> setStart(int start) {
      this.start = start;
      return this;
    }

    @Override
    public CompositionBuilder<Sheet> setNumEndings(int numEndings) {
      this.numEndings.add(numEndings);
      return this;
    }

    @Override
    public CompositionBuilder<Sheet> setEndStartTime(int endstarttime) {
      this.endstarttime.add(endstarttime);
      return this;
    }

    @Override
    public CompositionBuilder<Sheet> addEndingTime(ArrayList<Integer> endingTime) {
      this.endingTimes.add(endingTime);
      return this;
    }


    @Override
    public CompositionBuilder<Sheet> setTempo(int tempo) {
      this.tempo = tempo; // <--- example of mutating the field here so you can set the tempo
      return this;
    }

    @Override
    public CompositionBuilder<Sheet> addNote(int start, int end, int instrument, int pitch, int
            volume) {
      int octave = (int) Math.floor(pitch / 12) - 1;
      int notePitch = pitch % 12;
      ANote n = new Note(notePitch, octave, start, end, instrument, volume);
      this.Notes.add(n);
      return this;

    }

    @Override
    public CompositionBuilder<Sheet> addRepeat(Integer[] repeat) {
      repeats.add(repeat);
      return this;
    }


  }


  // Represents the beat measure of the piece.
  private int tempo;
  private int beat;
  private TreeMap<Integer, ArrayList<ANote>> Notes;
  private int duration;

  private ArrayList<Integer[]> repeats = new ArrayList<Integer[]>();

  //Used for the alternate endings
  private int start = 0;
  private ArrayList<Integer> numEndings;
  private int altEndingsStart = 0;
  private ArrayList<Integer>endstarttime = new ArrayList<Integer>();
  int a = 0;
  boolean alternateEnding = false;
  private ArrayList<ArrayList<Integer>> altendingspots = new ArrayList<ArrayList<Integer>>();




  /*INVARIANTS
  1. There cannot be 2 of the same note within a sheet of music.
  2. A note with the same pitch and octave cannot overlap.
   */
  public Sheet() {
    this.Notes = new TreeMap<Integer, ArrayList<ANote>>();
    this.beat = 0;
    this.tempo = 1000;
    //.out.println(this.songDuration());
    this.duration = 0;
  }

  // Displays the console.
  public static void main(String[] args) throws IOException {

    ArrayList<Note> notes = new ArrayList<Note>();
    Note g = new Note(3, 1, 1, 2, 2, 100);
    Note a = new Note(0, 0, 5, 5, 2, 100);
    Note r = new Note(5, 0, 3, 11, 2, 100);
    Note r2 = new Note(5, 0, 6, 8, 2, 100);
    Note r3 = new Note(5, 0, 9, 11, 2, 100);
    Note b = new Note(11, 0, 3, 5, 1, 100);
    Note cs = new Note(1, 1, 3, 8, 1, 100);
    System.out.println(b.totalNotePitch());
    notes.add(g);
    notes.add(r);
    notes.add(a);
    notes.add(b);
    notes.add(cs);
    notes.add(r2);
    notes.add(r3);
    Sheet m = new Sheet();
    m.addNote(3, 1, 1, 4, 2, 100);
    m.addNote(6, 3, 1, 4, 4, 100);
    m.addNote(3, 2, 5, 9, 4, 100);
    m.addNote(3, 2, 7, 10, 4, 100);
    //toConsole print = new toConsole(m);
    //print.toConsole(System.out);
    //System.out.println(m.noteRange());
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public void setTempo(int newTempo) {
    this.tempo = newTempo;
  }

  @Override
  public TreeMap<Integer, ArrayList<ANote>> getNotes() {
    return this.Notes;
  }

  @Override
  public void setNotes(TreeMap<Integer, ArrayList<ANote>> notes) {
    this.Notes = notes;
  }

  @Override
  public void setDuration(int duration) {
    this.duration = duration;
  }

  @Override
  public int getDuration() {
    return this.duration;
  }

  @Override
  public ArrayList<Integer[]> getRepeats() {
    return this.repeats;
  }

  @Override
  public ArrayList<ArrayList<Integer>> getAltEndings() {
    return this.altendingspots;
  }

  @Override
  public boolean getAlternateEnding() {
    return this.alternateEnding;
  }

  @Override
  public int getStart() {
    return this.start;
  }

  @Override
  public ArrayList<Integer> getEndTimeStart() {
    return this.endstarttime;
  }
  /**
   * Returns the given integer pitch as its written value (C, C#, ... B) respectively.
   *
   * @return a String of the name of the note.
   */
  public String pitchToString(int n) {
    if (n == 0) {
      return "C";
    } else if (n == 1) {
      return "C#";
    } else if (n == 2) {
      return "D";
    } else if (n == 3) {
      return "D#";
    } else if (n == 4) {
      return "E";
    } else if (n == 5) {
      return "F";
    } else if (n == 6) {
      return "F#";
    } else if (n == 7) {
      return "G";
    } else if (n == 8) {
      return "G#";
    } else if (n == 9) {
      return "A";
    } else if (n == 10) {
      return "A#";
    } else {
      return "B";
    }
  }

  @Override
  public void addToBeat() {
    this.beat += 1;
  }

  @Override
  public int getBeat() {
    return this.beat;
  }

  @Override
  public void subtractFromBeat() {
    this.beat -= 1;
  }

  @Override
  public void setBeat(int beat) {
    this.beat = beat;
  }

  @Override
  public int noteRange() {
    int i = 12 - this.lowestNote().getPitch();
    int j = (this.getHighestOctave() - this.getLowestOctave() - 1) * 12;
    int k = this.highestNote().getPitch();
    return i + j + k;
  }

  @Override
  public void addNote(int newPitch, int newOctave, int newStartTime, int newEndTime,
                      int newInstrument, int newVolume) {
    if (newPitch < 0 || newPitch > 11 || newOctave < 0 || newOctave > 10 || newVolume < 0
            || newVolume > 128 || newInstrument < 0 || newInstrument > 128 || newStartTime <
            0 || newEndTime < 0 || newStartTime > newEndTime) {
      throw new IllegalArgumentException("can't add that");
    }
    Note newNote = new Note(newPitch, newOctave, newStartTime, newEndTime, newInstrument,
            newVolume);
    if (!Notes.containsKey(newStartTime)) {
      ArrayList<ANote> newNotes = new ArrayList<ANote>();
      newNotes.add(newNote);
      Notes.put(newStartTime, newNotes);
    } else if (Notes.get(newStartTime).contains(newNote)) {
      throw new IllegalArgumentException("Note already exists!");

    } else {
      ArrayList<ANote> newNotes = Notes.get(newStartTime);
      newNotes.add(newNote);
      Notes.put(newStartTime, newNotes);
    }
  }


  @Override
  public void changeNote(int pitchFrom, int octaveFrom, int startTimeFrom, int endTimeFrom, int
          instrumentFrom, int volumeFrom, int pitchTo, int octaveTo, int startTimeTo,
                         int endTimeTo, int instrumentTo, int volumeTo) {
    ANote note = new Note(pitchFrom, octaveFrom, startTimeFrom, endTimeFrom, instrumentFrom,
            volumeFrom);
    this.removeNote(note);
    this.addNote(pitchTo, octaveTo, startTimeTo, endTimeTo, instrumentTo, volumeTo);
  }


  @Override
  public void removeNote(ANote n) {

    if (!Notes.containsKey(n.getStartTime()) || !Notes.get(n.getStartTime()).contains(n)) {
      throw new IllegalArgumentException("Cannot remove a nonexistent node!");
    }
    for (int i = 0; i < Notes.get(n.getStartTime()).size(); i++) {
      if (Notes.get(n.getStartTime()).get(i).equals(n)) {
        Notes.get(n.getStartTime()).remove(i);
      }
    }
  }

  @Override
  public int songDuration() {
    int longestNoteTime = 0;
    if (Notes.size() != 0) {
      for (Map.Entry<Integer, ArrayList<ANote>> entry : Notes.entrySet()) {
        ArrayList<ANote> noteshere = entry.getValue();
        for (int i = 0; i < noteshere.size(); i++) {
          if (noteshere.get(i).getEndTime() > longestNoteTime) {
            longestNoteTime = noteshere.get(i).getEndTime();
          }
        }
      }
    }
    return longestNoteTime;
  }


  @Override
  public int pitchFromHighest(ANote note) {
    return this.highestNote().totalNotePitch() - note.totalNotePitch();
  }

  @Override
  public boolean containsNoteHere(int start, int end, int pitchFromHighest) {
    if (this.allNotesPlayingAtRow(start).size() != 0) {
      ArrayList<ANote> notesHere = this.allNotesPlayingAtRow(start);

      for (int i = 0; i < this.allNotesPlayingAtRow(start).size(); i++) {
        if (this.pitchFromHighest(notesHere.get(i)) == pitchFromHighest) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public ANote noteHere(int start, int end, int pitchFromHighest) {
    ANote n = new Note(1, 2, 3, 4, 5, 6);
    if (this.allNotesPlayingAtRow(start).size() != 0) {
      ArrayList<ANote> notesHere = this.allNotesPlayingAtRow(start);

      for (int i = 0; i < this.allNotesPlayingAtRow(start).size(); i++) {
        if (this.pitchFromHighest(notesHere.get(i)) == pitchFromHighest) {
          n = notesHere.get(i);
        }
      }
    }
    return n;
  }

  @Override
  public ArrayList<ANote> notesPlayingAtRow(int time) {

    if (Notes.isEmpty()) {
      throw new IllegalArgumentException("it's empty");
    }
    ArrayList<ANote> n = new ArrayList<ANote>();

    for (Map.Entry<Integer, ArrayList<ANote>> entry : Notes.entrySet()) {
      ArrayList<ANote> noteshere = entry.getValue();
      for (int i = 0; i < noteshere.size(); i++) {
        if (noteshere.get(i).getStartTime() < time && time < noteshere.get(i).getEndTime()) {
          n.add(noteshere.get(i));
        }
      }
    }
    return n;
  }

  @Override
  public ArrayList<ANote> allNotesPlayingAtRow(int time) {

    if (Notes.isEmpty()) {
      throw new IllegalArgumentException("it's empty");
    }
    ArrayList<ANote> n = new ArrayList<ANote>();

    for (Map.Entry<Integer, ArrayList<ANote>> entry : Notes.entrySet()) {
      ArrayList<ANote> noteshere = entry.getValue();
      for (int i = 0; i < noteshere.size(); i++) {
        if (noteshere.get(i).getStartTime() <= time && time < noteshere.get(i).getEndTime()) {
          n.add(noteshere.get(i));
        }
      }
    }
    return n;
  }

  @Override
  public ArrayList<ANote> notesEndingAtBeat(int time) {

    if (Notes.isEmpty()) {
      throw new IllegalArgumentException("it's empty");
    }
    ArrayList<ANote> n = new ArrayList<ANote>();

    for (Map.Entry<Integer, ArrayList<ANote>> entry : Notes.entrySet()) {
      ArrayList<ANote> noteshere = entry.getValue();
      for (int i = 0; i < noteshere.size(); i++) {
        if (noteshere.get(i).getEndTime() == time) {
          n.add(noteshere.get(i));
        }
      }
    }
    return n;
  }


  @Override
  public ArrayList<ANote> notesStartPlayingAtRow(int time) {

    if (Notes.isEmpty()) {
      throw new IllegalArgumentException("it's empty");
    }

    ArrayList<ANote> n = new ArrayList<ANote>();
    if (Notes.containsKey(time)) {
      ArrayList<ANote> noteshere = Notes.get(time);
      for (int i = 0; i < noteshere.size(); i++) {
        if (noteshere.get(i).getStartTime() == time) {
          n.add(noteshere.get(i));
        }
      }
    }
    return n;
  }


  @Override
  public String notesPlayingAtRowAsString(int rowNumber) {
    String template = "";
    for (int i = 0; i < this.notesPlayingAtRow(rowNumber).size(); i++) {
      template = this.notesPlayingAtRow(rowNumber).get(i).noteToString() + " CONTINUES playing,"
              + " " + template;
    }

    for (int i = 0; i < this.notesStartPlayingAtRow(rowNumber).size(); i++) {
      template = this.notesStartPlayingAtRow(rowNumber).get(i).noteToString() + " STARTS " +
              "playing, " +
              template;
    }

    if (template.length() == 0) {
      return template;
    } else {
      return template.substring(0, template.length() - 2);
    }
  }


  @Override
  public int getLowestOctave() {
    if (Notes.size() == 0) {
      throw new IllegalArgumentException("There are no notes in this sheet!");
    }
    int lowestOctave = 10;

    for (Map.Entry<Integer, ArrayList<ANote>> entry : Notes.entrySet()) {
      ArrayList<ANote> note = entry.getValue();
      for (int j = 0; j < note.size(); j++) {
        if (note.get(j).getOctave() <= lowestOctave) {
          lowestOctave = note.get(j).getOctave();
        }
      }
    }
    return lowestOctave;
  }

  @Override
  public ANote lowestNote() {
    int lowestOctave = this.getLowestOctave();
    int lowestPitch = 13;
    ANote lowestNote = new Note(5, this.getLowestOctave(), 5, 5, 5, 100);
    for (Map.Entry<Integer, ArrayList<ANote>> entry : Notes.entrySet()) {
      ArrayList<ANote> noteshere = entry.getValue();
      for (int i = 0; i < noteshere.size(); i++) {
        if (noteshere.get(i).getOctave() == lowestOctave && noteshere.get(i).getPitch()
                < lowestPitch) {
          lowestPitch = noteshere.get(i).getPitch();
          lowestNote = noteshere.get(i);
        }
      }
    }
    return lowestNote;
  }

  @Override
  public String lowestNoteString() {
    return this.lowestNote().noteToString();
  }

  @Override
  public int getHighestOctave() {
    if (Notes.size() == 0) {
      throw new IllegalArgumentException("There are no notes in this sheet!");
    }
    int highestOctave = -1;

    for (Map.Entry<Integer, ArrayList<ANote>> entry : Notes.entrySet()) {
      ArrayList<ANote> notes = entry.getValue();
      for (int i = 0; i < notes.size(); i++) {
        if (notes.get(i).getOctave() >= highestOctave) {
          highestOctave = notes.get(i).getOctave();
        }
      }
    }
    return highestOctave;
  }

  @Override
  public ANote highestNote() {
    int highestOctave = this.getHighestOctave();
    int highestPitch = -1;
    ANote highestNote = new Note(0, this.getLowestOctave(), 5, 5, 5, 100);
    for (Map.Entry<Integer, ArrayList<ANote>> entry : Notes.entrySet()) {
      ArrayList<ANote> noteshere = entry.getValue();
      for (int i = 0; i < noteshere.size(); i++) {
        if (noteshere.get(i).getOctave() == highestOctave && noteshere.get(i).getPitch() >
                highestPitch) {
          highestPitch = noteshere.get(i).getPitch();
          highestNote = noteshere.get(i);
        }
      }
    }
    return highestNote;
  }

  @Override

  public String highestNoteString() {
    return this.highestNote().noteToString();
  }

  @Override
  public ArrayList<String> notesFromLowestToHighest() {
    ArrayList<String> temp = new ArrayList<String>();
    for (int i = this.lowestNote().getPitch(); i < 12; i++) {
      temp.add("    ");
    }
    for (int i = this.getLowestOctave() + 1; i <= this.getHighestOctave() + 1; i++) {
      for (int j = 0; j < 12; j++) {
        temp.add("    ");
      }
    }
    for (int i = 0; i <= this.highestNote().getPitch(); i++) {
      temp.add("    ");
    }
    return temp;
  }

  /**
   * Returns the index of the given note in this sheet music (or piece).
   *
   * @return integer of the index of the given note in this sheet.
   */
  private int noteIndex(ANote n) {
    return ((n.getOctave() - this.getLowestOctave()) * 12) + n.getPitch() - this.lowestNote()
            .getPitch();
  }

  /**
   * Pads the number of spaces to be displayed in console.
   *
   * @return a String with correct number of spaces for proper visuals.
   */
  public String pad(String s, int numPad) {
    int numSpaces = numPad - s.length();
    for (int i = 0; i < numSpaces; i++) {
      s = s + " ";
    }
    return s;
  }

  @Override
  public String rowToString(int rowNumber) {
    String[] allNotes = new String[this.notesFromLowestToHighest().size()];
    for (int i = 0; i < allNotes.length; i++) {
      allNotes[i] = this.notesFromLowestToHighest().get(i);
    }
    ArrayList<ANote> notesStartPlayingAtRow = notesStartPlayingAtRow(rowNumber);
    for (int i = 0; i < notesStartPlayingAtRow.size(); i++) {
      allNotes[noteIndex(notesStartPlayingAtRow.get(i))] = "X" + allNotes[noteIndex
              (notesStartPlayingAtRow.get(i))].substring(1);
    }
    ArrayList<ANote> notesPlayingAtRow = notesPlayingAtRow(rowNumber);
    for (int i = 0; i < notesPlayingAtRow.size(); i++) {
      if (!allNotes[noteIndex(notesPlayingAtRow.get(i))].contains("X")) {
        allNotes[noteIndex(notesPlayingAtRow.get(i))] = "|" + allNotes[noteIndex
                (notesPlayingAtRow.get(i))].substring(1);
      }
    }
    StringBuilder builder = new StringBuilder("");
    for (int i = 0; i < allNotes.length; i++) {
      builder.append(allNotes[i]);
    }
    return builder.toString();
  }

  @Override
  public void append(iMusic that) {
    for (Map.Entry<Integer, ArrayList<ANote>> entry : that.getNotes().entrySet()) {
      int key = Notes.lastKey() +entry.getKey();
      ArrayList<ANote> noteshere = entry.getValue();
      Notes.put(key, noteshere);
    }
  }

  @Override
  public void setRepeat(int start, int end) {
    if (beat == end) {
      beat = start;
      }
    }

  @Override
  public void setRepeats() {
    if (this.repeats.size() > 0) {
      for (int i = 0; i < this.repeats.size(); i++) {
        Integer[] repeathere = this.repeats.get(i);
        int start = repeathere[0];
        int end = repeathere[1];
        int numrepeats = repeathere[2];
        if (numrepeats > 0 && this.beat == end) {
          this.setRepeat(start, end);
          numrepeats -= 1;
          repeathere[2] = numrepeats;
        }
      }
    }
  }

  @Override
  public void addRepeat(Integer[] repeat) {
    this.repeats.add(repeat);
  }

  //Used for the alternate endings
//  private int start = 0;
//  private int numrepeats = 0;
//  private ArrayList<Integer> altendingspots = new ArrayList<Integer>();
//  private int altEndingsStart = 0;
//  private int endstarttime = 0;

  @Override
  public void setStart(int start) {
    this.start = start;
  }

  @Override
  public void setnumEndings(ArrayList<Integer> repeats) {
    this.numEndings = repeats;
  }

  @Override
  public void addEndingspots(ArrayList<Integer> endingSpot) {
    this.altendingspots.add(endingSpot);
  }

  @Override
  public void setEndStartTime(ArrayList<Integer> endstarttime) {
    this.endstarttime = endstarttime;
  }
  @Override
  public void setAltEndings() {

    this.alternateEnding = true;
    if (this.altendingspots.size() > 0) {
      for (int i = 0; i < altendingspots.size(); i++) {
        if (this.beat == altendingspots.get(i).get
                (altEndingsStart) && numEndings.get(i) > 0) {
          this.setRepeat(start, altendingspots.get(i).get(altEndingsStart));
          numEndings.set(i, numEndings.get(i) - 1);
          if (altEndingsStart < altendingspots.size() - 1) {
            altEndingsStart += 1;
          }
          a += 1;
        }
        if (altEndingsStart > 0 && a != altendingspots.size()) {
          if (this.beat == endstarttime.get(i) + 1) {
            this.setBeat(altendingspots.get(i).get(altEndingsStart - 1));
          }
        } else if (altEndingsStart > 0 && a == altendingspots.size()) {
          if (this.beat == endstarttime.get(i) + 1) {
            this.setBeat(altendingspots.get(i).get(altEndingsStart));
          }
        }
        a = 0;
        altEndingsStart = 0;
      }
    }
//    this.alternateEnding = true;
//    if (this.altendingspots.size() > 0) {
//      if (this.beat == altendingspots.get
//              (altEndingsStart) && numEndings > 0) {
//        this.setRepeat(start, altendingspots.get(altEndingsStart));
//        numEndings = numEndings - 1;
//        if (altEndingsStart < altendingspots.size() - 1) {
//          altEndingsStart += 1;
//        }
//        a+= 1;
//
//
//      }
//      if (altEndingsStart > 0 && a != altendingspots.size()) {
//        if (this.beat == endstarttime + 1) {
//          this.setBeat(altendingspots.get(altEndingsStart - 1));
//        }
//      } else if (altEndingsStart > 0 && a == altendingspots.size()) {
//        if (this.beat == endstarttime + 1) {
//          this.setBeat(altendingspots.get(altEndingsStart));
//        }
//      }
//    }
  }


}