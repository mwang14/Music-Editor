package cs3500.music.iMusic;

public abstract class ANote {
  // Represents a pitch from 0-11 (C, C#, ..., B) respectively.
  protected int pitch;

  // Represents the Octave of the pitch.
  protected int octave;

  // Represents the time (the beat) at which the note starts playing.
  protected int startTime;

  // Represents the beat at which the note stops playing.
  protected int endTime;

  // Represents the instrument that is playing the note.
  protected int instrument;

  //Represents the Volume of the note
  protected int volume;

  protected boolean selected;

  /**
   * Represents an abstracted note
   */
  /*INVARIANTS
   1. pitch must be between 0 and 11
   2. Octave must be between -1 and 10
   3. startTime must be positive
   4. endTime must be positive
   5. startTime must be before endTime
   6. instrument must be between 0 and 128
   7. volume must be between 0 and 128
   */
  protected ANote(int pitch, int octave, int startTime, int endTime, int instrument, int volume) {
    if (pitch < 0 || pitch > 11) {
      throw new IllegalArgumentException("A number out of the range 0-11 is not a valid pitch!");
    }
    this.pitch = pitch;

    if (octave < -1 || octave > 10) {
      throw new IllegalArgumentException("A note can only be heard in a range of -1 - 10 " +
              "octave!");
    }
    this.octave = octave;

    if (startTime < 0) {
      throw new IllegalArgumentException("A note cannot start playing at a negative beat!");
    }
    this.startTime = startTime;

    if (endTime < startTime) {
      throw new IllegalArgumentException("A note cannot end before it even starts playing!");
    }
    if (endTime < 0) {
      throw new IllegalArgumentException("A note cannot end at a negative beat!");
    }
    if (instrument < 0 || instrument > 128) {
      throw new IllegalArgumentException("Instruments aren't in that range");
    }
    if (volume < 0 || volume > 128) {
      throw new IllegalArgumentException("Volume is out of range");
    }
    this.endTime = endTime;
    this.instrument = instrument;
    this.volume = volume;
    this.selected = false;
  }

  /**
   * Returns the Note's pitch
   *
   * @return int this note's pitch
   */
  public int getPitch() {
    return this.pitch;
  }

  /**
   * Returns the Note's octave
   *
   * @return int this note's octave
   */
  public int getOctave() {
    return this.octave;
  }

  /**
   * Returns the Note's startTime
   *
   * @return int this note's startTime
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Returns the Note's endTime
   *
   * @return int this note's endTime
   */
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * Returns the Note's instrument
   *
   * @return int this note's instrument
   */
  public int getInstrument() {
    return this.instrument;
  }

  public int getVolume() {
    return this.volume;
  }

  /**
   * Returns the name of the given integer pitch (from 0-11) as a String (ex. C or C#). (Note that
   * C# = D flat and hence only sharp naming convention will be used to represent the pitch).
   *
   * @return a string with the pitch in letter form.
   */

  private String pitchToStringNote() {
    if (this.pitch == 0) {
      return "C";
    } else if (this.pitch == 1) {
      return "C#";
    } else if (this.pitch == 2) {
      return "D";
    } else if (this.pitch == 3) {
      return "D#";
    } else if (this.pitch == 4) {
      return "E";
    } else if (this.pitch == 5) {
      return "F";
    } else if (this.pitch == 6) {
      return "F#";
    } else if (this.pitch == 7) {
      return "G";
    } else if (this.pitch == 8) {
      return "G#";
    } else if (this.pitch == 9) {
      return "A";
    } else if (this.pitch == 10) {
      return "A#";
    } else {
      return "B";
    }
  }

  public int totalNotePitch() {
    return this.pitch + ((1 + this.octave) * 12);
  }


  /**
   * Returns the name of the note (ex. C3) as a String (Note that C# = D flat and hence only sharp
   * naming convention will be used to represent the pitch).
   *
   * @return a string with the pitch (in letter form) and octave of the note.
   */

  protected String noteNameToString() {
    return this.pitchToStringNote() + Integer.toString(this.octave);
  }

  /**
   * Returns this node in String format with specifics of the note.
   *
   * @return string containing details of this note.
   */
  public String noteToString() {
    return "Note: " + this.noteNameToString() + ", starting beat at: " + Integer.toString(this
            .startTime) + ", ending beat at: " + Integer.toString(this
            .endTime) + ", played using instrument number: " + Integer.toString(this.instrument);
  }

  public Note createNote(int notePitch, int octave, int start, int end, int instrument, int
          volume) {
    return new Note(notePitch, octave, start, end, instrument, volume);
  }

  /**
   * Checks if the Start time of this note is equal to the start time of That note.
   *
   */

  /**
   * This determines whether this note is Selected or not.
   *
   * @return Boolean
   */
  public boolean isSelected() {
    return this.selected;
  }

  /**
   * This selects or deselects the note.
   */
  public void setSelected(boolean select) {
    this.selected = select;
  }


}