package cs3500.music.Controller;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;

import cs3500.music.View.CompoundView;
import cs3500.music.View.ICompoundGuiMidi;
import cs3500.music.iMusic.ANote;
import cs3500.music.iMusic.iMusic;

public class ControllerImpl implements Controller {
  private final ICompoundGuiMidi view;
  private final KeyboardHandler kbhandler = new KeyboardHandler();
  private final iMusic sheet;
  public Timer timer = new Timer();
  public int time = 0;
  private boolean playing = true;
  private final MouseHandler mhandler = new MouseHandler();
  private boolean remove = false;
  private boolean select = false;
  private ANote note = null;
  private ArrayList<Integer[]> repeats = new ArrayList<Integer[]>();

  //Used for the alternate endings
  private int start = 0;
  private int end = 0;
  private int numrepeats = 0;
  private ArrayList<Integer> altendingspots = new ArrayList<Integer>();
  private int altEndingsStart = 0;
  private boolean altendings = false;
  private int endstarttime = 0;


  /**
   * Creates a Controller that controls a view.
   */
  public ControllerImpl(iMusic sheet) {
    this.sheet = sheet;
    view = new CompoundView(sheet);
    /**
     * Adds the start and pause button
     */
    kbhandler.addRunnable(() -> playing = !playing, KeyEvent.VK_SPACE,
            KeyboardHandler.options.PRESSED);

    /**
     * Scrolling
     */

    //Scroll Right
    kbhandler.addRunnable(() -> {
      //view.scrollBy(90);
      view.getGui().updateHorizontalScroll(90);
    }, KeyEvent.VK_RIGHT, KeyboardHandler.options.PRESSED);

    //Scroll left
    kbhandler.addRunnable(() -> view.getGui().updateHorizontalScroll(-90), KeyEvent.VK_LEFT,
            KeyboardHandler.options.PRESSED);

    // Scroll down
    kbhandler.addRunnable(() -> view.getGui().updateVerticalScroll(90), KeyEvent.VK_DOWN,
            KeyboardHandler.options.PRESSED);

    //Scroll up
    kbhandler.addRunnable(() -> view.getGui().updateVerticalScroll(-90), KeyEvent.VK_UP
            , KeyboardHandler.options.PRESSED);

    //Scroll to beginning
    kbhandler.addRunnable(() -> {
      view.getGui().getScrollPane().getVerticalScrollBar().setValue(0);
      view.getGui().getScrollPane().getHorizontalScrollBar().setValue(0);

    }, KeyEvent.VK_HOME, KeyboardHandler.options.PRESSED);

    //Scroll to end
    kbhandler.addRunnable(() -> {
      view.getGui().getScrollPane().getVerticalScrollBar().setValue(0);
      view.getGui().getScrollPane().getHorizontalScrollBar().
              setValue(view.songDuration() * 100);

    }, KeyEvent.VK_END, KeyboardHandler.options.PRESSED);

    //Fasts forwards the song
    kbhandler.addRunnable(() -> {
      addToTime();
      view.addToBeat();
    }, KeyEvent.VK_2, KeyboardHandler.options.PRESSED);

    //Rewinds the song
    kbhandler.addRunnable(() -> {
      subtractFromTime();
      view.subtractFromBeat();
    }, KeyEvent.VK_1, KeyboardHandler.options.PRESSED);

    //Adds a note
    kbhandler.addRunnable(() -> {
      if (playing) {
        playing = !playing;
        String pn = JOptionPane.showInputDialog("Enter Pitch Number from 0 to 11");
        String octave = JOptionPane.showInputDialog("enter Octave from 0 to 10");
        String st = JOptionPane.showInputDialog("Enter Start time greater than 0");
        String et = JOptionPane.showInputDialog("Enter end time greater than Start Time");
        String in = JOptionPane.showInputDialog("Enter Instrument Number from 0 to 128");
        String vol = JOptionPane.showInputDialog("Enter volume from 0 to 128");
        int pitchNum = Integer.parseInt(pn);
        int octavenum = Integer.parseInt(octave);
        int startTime = Integer.parseInt(st);
        int endTime = Integer.parseInt(et);
        int instrumentNum = Integer.parseInt(in);
        int volumeNum = Integer.parseInt(vol);
        if (pitchNum < 0 || pitchNum > 11 || octavenum < 0 || octavenum > 10 || volumeNum < 0
                || volumeNum > 128 || instrumentNum < 0 || instrumentNum > 128 || startTime <
                0 || endTime < 0 || startTime > endTime) {
          throw new IllegalArgumentException("Can't add that");
        }
        if (endTime > sheet.getDuration()) {
          view.setDuration(endTime);
        }
        view.addNote(pitchNum, octavenum, startTime, endTime, instrumentNum, volumeNum);
        playing = !playing;
      } else {
        String pn = JOptionPane.showInputDialog("Enter Pitch Number from 0 to 11");
        String octave = JOptionPane.showInputDialog("enter Octave from 0 to 10");
        String st = JOptionPane.showInputDialog("Enter Start time greater than 0");
        String et = JOptionPane.showInputDialog("Enter end time greater than Start Time");
        String in = JOptionPane.showInputDialog("Enter Instrument Number from 0 to 128");
        String vol = JOptionPane.showInputDialog("Enter volume from 0 to 128");
        int pitchNum = Integer.parseInt(pn);
        int octavenum = Integer.parseInt(octave);
        int startTime = Integer.parseInt(st);
        int endTime = Integer.parseInt(et);
        int instrumentNum = Integer.parseInt(in);
        int volumeNum = Integer.parseInt(vol);
        if (pitchNum < 0 || pitchNum > 11 || octavenum < 0 || octavenum > 10 || volumeNum < 0
                || volumeNum > 128 || instrumentNum < 0 || instrumentNum > 128 || startTime <
                0 || endTime < 0 || startTime > endTime) {
          throw new IllegalArgumentException("can't add that");
        }
        if (endTime > sheet.getDuration()) {
          view.setDuration(endTime);
        }
        view.addNote(pitchNum, octavenum, startTime, endTime, instrumentNum, volumeNum);
      }
    }, KeyEvent.VK_A, KeyboardHandler.options.PRESSED);

    //Allows for Note Removal
    kbhandler.addRunnable(new Runnable() {
      @Override
      public void run() {
        remove = !remove;
        //System.out.println(remove);
      }
    }
            , KeyEvent.VK_R, KeyboardHandler.options.PRESSED);

    //Allows a note to change
    kbhandler.addRunnable(() -> {
      JOptionPane.showInputDialog("Select a Note to change! Type 'ok' to continue");
      if (note != null) {
        view.removeNote(note);
        if (playing) {
          playing = !playing;
          String rn = JOptionPane.showInputDialog("Enter Pitch Number from 0 to 11");
          String octave = JOptionPane.showInputDialog("enter Octave from 0 to 10");
          String st = JOptionPane.showInputDialog("Enter Start time greater than 0");
          String et = JOptionPane.showInputDialog("Enter end time greater than Start Time");
          String in = JOptionPane.showInputDialog("Enter Instrument Number from 0 to 128");
          String vol = JOptionPane.showInputDialog("Enter volume from 0 to 128");
          int pitchNum = Integer.parseInt(rn);
          int octavenum = Integer.parseInt(octave);
          int startTime = Integer.parseInt(st);
          int endTime = Integer.parseInt(et);
          int instrumentNum = Integer.parseInt(in);
          int volumeNum = Integer.parseInt(vol);
          if (pitchNum < 0 || pitchNum > 11 || octavenum < 0 || octavenum > 10 || volumeNum < 0
                  || volumeNum > 128 || instrumentNum < 0 || instrumentNum > 128 || startTime <
                  0 || endTime < 0 || startTime > endTime) {
            throw new IllegalArgumentException("can't add that");
          }
          if (endTime > view.songDuration()) {
            view.setDuration(endTime);
          }
          view.addNote(pitchNum, octavenum, startTime, endTime, instrumentNum, volumeNum);
          playing = !playing;
        } else {
          String rn = JOptionPane.showInputDialog("Enter Pitch Number from 0 to 11");
          String octave = JOptionPane.showInputDialog("enter Octave from 0 to 10");
          String st = JOptionPane.showInputDialog("Enter Start time greater than 0");
          String et = JOptionPane.showInputDialog("Enter end time greater than Start time");
          String in = JOptionPane.showInputDialog("Enter Instrument Number from 0 to 128");
          String vol = JOptionPane.showInputDialog("Enter volume from 0 to 128");
          int pitchNum = Integer.parseInt(rn);
          int octavenum = Integer.parseInt(octave);
          int startTime = Integer.parseInt(st);
          int endTime = Integer.parseInt(et);
          int instrumentNum = Integer.parseInt(in);
          int volumeNum = Integer.parseInt(vol);
          if (pitchNum < 0 || pitchNum > 11 || octavenum < 0 || octavenum > 10 || volumeNum < 0
                  || volumeNum > 128 || instrumentNum < 0 || instrumentNum > 128 || startTime <
                  0 || endTime < 0 || startTime > endTime) {
            throw new IllegalArgumentException("can't add that");
          }
          if (endTime > view.songDuration()) {
            view.setDuration(endTime);
          }
          view.addNote(pitchNum, octavenum, startTime, endTime, instrumentNum, volumeNum);
        }
      }
    }, KeyEvent.VK_C, KeyboardHandler.options.PRESSED);

    //Allows user to make Chords
    kbhandler.addRunnable(() -> {
      if (playing) {
        playing = !playing;
        if (!select) {
          JOptionPane.showInputDialog("Click on a Note to select it! type 'ok' to continue");
        }
        if (note != null) {
          String s = JOptionPane.showInputDialog("How far above do you want the chord?");
          int numAbove = Integer.parseInt(s);
          int newPitch = (note.getPitch() + numAbove) % 12;
          int newOctave = note.getOctave();
          if (numAbove + note.getPitch() > 11) {
            newOctave = note.getOctave() + 1;
          }
          if (newOctave < 0 || newOctave > 10) {
            throw new IllegalArgumentException("Out of range");
          }
          view.addNote(newPitch, newOctave, note.getStartTime(), note.getEndTime(), note
                  .getInstrument(), note.getVolume());
        }
        playing = !playing;
      } else {
        if (!select) {
          JOptionPane.showInputDialog("Click on a Note to select it! type 'ok' to continue");
        }
        if (note != null) {
          String s = JOptionPane.showInputDialog("How far above do you want the chord?");
          int numAbove = Integer.parseInt(s);
          int newPitch = (note.getPitch() + numAbove) % 12;
          int newOctave = note.getOctave();
          if (numAbove + note.getPitch() > 11) {
            newOctave = note.getOctave() + 1;
          }
          view.addNote(newPitch, newOctave, note.getStartTime(), note.getEndTime(), note
                  .getInstrument(), note.getVolume());
        }
      }
    }, KeyEvent.VK_I, KeyboardHandler.options.PRESSED);

;

    kbhandler.addRunnable(() -> {
      String end = JOptionPane.showInputDialog("Where do you want the repeat to end?");
      String start = JOptionPane.showInputDialog("Where do you repeat to start?");
      String numtimes = JOptionPane.showInputDialog("How many times do you want it to repeat?");
      int startint = Integer.parseInt(start);
      int endint = Integer.parseInt(end);
      int numtimesrepeat = Integer.parseInt(numtimes);
      Integer[] repeat = new Integer[3];
      repeat[0] = startint;
      repeat[1] = endint;
      repeat[2] = numtimesrepeat;
      view.addRepeat(repeat);
    }, KeyEvent.VK_L, KeyboardHandler.options.PRESSED);


    kbhandler.addRunnable(() -> {
      String start = JOptionPane.showInputDialog("Where should the repeated section start?");
      String repeatnum = JOptionPane.showInputDialog("How many endings do you want?");
      String end = JOptionPane.showInputDialog("Where should the endings start?");
      int endtime = Integer.parseInt(end);
      int startint = Integer.parseInt(start);
      int numtimesrepeat = Integer.parseInt(repeatnum);
      ArrayList<Integer> endings = new ArrayList<Integer>();
      for (int i = 0; i < numtimesrepeat; i++) {
        String a = JOptionPane.showInputDialog("Enter alternate ending number " + (i+1));
        int k = Integer.parseInt(a);
        endings.add(k);
      }
//      view.setStart(startint);
//      view.setNumEndings(numtimesrepeat);
//      view.setEndStartTime(endtime);

    }, KeyEvent.VK_E, KeyboardHandler.options.PRESSED);

    //Allows note to be selected
    kbhandler.addRunnable(() -> {
      if (playing) {
        playing = !playing;

        if (!select) {
          JOptionPane.showInputDialog("Click on a Note to select it! type 'ok' to continue");
        }
        select = !select;
        playing = !playing;
      } else {
        if (!select) {
          JOptionPane.showInputDialog("Click on a Note to select it! type 'ok' to continue");
        }
      }
    }, KeyEvent.VK_S, KeyboardHandler.options.PRESSED);

    kbhandler.addRunnable(() -> {
      select = false;
      note.setSelected(false);
    }, KeyEvent.VK_U, KeyboardHandler.options.PRESSED);

    // Removes the note at this coordinate
    mhandler.addMouseEvent(2, (x, y) -> {
      if (view.getGui().NoteatCoordinate(x, y)) {
        if (remove) {
          int ytoNote = (y - 40) / 40;
          int xtoNote = (x - 100) / 25;
          view.removeNote(sheet.noteHere(xtoNote, xtoNote, ytoNote));
        }
      }
    }, MouseHandler.mouseOptions.PRESSED);

    //selects the note at this coordinate
    mhandler.addMouseEvent(0, (x, y) -> {
      if (view.getGui().NoteatCoordinate(x, y)) {
        if (select) {
          int ytoNote = (y - 40) / 40;
          int xtoNote = (x - 100) / 25;
          if (note != null) {
            note.setSelected(false);
          }
          note = view.noteHere(xtoNote, xtoNote, ytoNote);
          note.setSelected(!note.isSelected());
        }
      }
    }, MouseHandler.mouseOptions.PRESSED);


    view.addMouseHandler(mhandler);
    view.setKeyListener(kbhandler);


  }

  @Override
  public void start() throws IOException, InvalidMidiDataException,
          InterruptedException {
    timer.schedule(new timerthing(), 0, sheet.getTempo() /
            1000);
    view.render();

  }

  //Increments the Time by 1
  public void addToTime() {
    this.time += 1;
  }

  //Subtracts 1 from the time
  public void subtractFromTime() {
    this.time -= 1;
  }

  // Set the playing boolean
  public void setPlaying(boolean b) {
    this.playing = b;
  }

  //Returns the playing boolean
  public boolean getPlaying() {
    return this.playing;
  }

  //Returns the KeyboardHandler
  public KeyboardHandler getKeyboardHandler() {
    return this.kbhandler;
  }

  public class timerthing extends TimerTask {


    @Override
    public void run() {
      if (sheet.getBeat() == sheet.songDuration()) {
        System.exit(0);
      }
      try {
        if (playing) {
          view.render();
          //time++;
          view.addToBeat();
          view.getGui().updateScrollPane();
          view.setRepeats();
          view.setAltEndings();
//          if (repeats.size() > 0) {
//            for (int i = 0; i < repeats.size(); i++) {
//              Integer[] repeathere = repeats.get(i);
//              int numTimesToRepeat = repeathere[2];
//              int endTime = repeathere[1];
//              int startTime = repeathere[0];
//              if (numTimesToRepeat > 0 && sheet.getBeat() == endTime) {
//                sheet.setRepeat(startTime, endTime);
//                numTimesToRepeat -= 1;
//                repeathere[2] = numTimesToRepeat;
//              }
//            }
//          }
//          if (altendings == true && sheet.getBeat() == altendingspots.get
//                  (altEndingsStart) && numrepeats > 0) {
//              sheet.setRepeat(start, altendingspots.get(altEndingsStart));
//            numrepeats = numrepeats - 1;
//            if (altEndingsStart < altendingspots.size() - 1) {
//              altEndingsStart += 1;
//            }
//          }
//          if (altEndingsStart > 0) {
//            if (sheet.getBeat() == endstarttime + 1) {
//              sheet.setBeat(altendingspots.get(altEndingsStart - 1));
//            }
//         }
        }
      } catch (InvalidMidiDataException e) {
        throw new IllegalArgumentException("wrong");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

