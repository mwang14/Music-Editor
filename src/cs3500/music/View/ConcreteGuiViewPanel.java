package cs3500.music.View;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;

import cs3500.music.iMusic.ANote;
import cs3500.music.iMusic.iMusic;

/**
 * Draws the sheet of music to a separate panel
 */
public class ConcreteGuiViewPanel extends JPanel {
  private iMusic sheet;
  private int range;
  private int duration;

  /**
   * Creates the Graphic View
   */
  public ConcreteGuiViewPanel(iMusic sheet) {
    this.sheet = sheet;
    //sheet.setDuration(sheet.songDuration());
    this.range = (sheet.songDuration() / 4) + 1;
    //System.out.println(sheet.songDuration());
    this.duration = sheet.getDuration();

  }


  /**
   * This draws everything except for the red line.
   */
  protected void drawBackground(Graphics g) {
    int height = this.getPreferredSize().height;


    //prints out the numbers on the top
    for (int i = 0; i <= (sheet.songDuration() / 4) + 1; i++) {
      g.drawString(Integer.toString(4 * i), 100 * (i + 1), 30);
    }
    int highestOctave = sheet.getHighestOctave();
    // Prints out the Notes on the left side
    for (int j = 1; j <= sheet.noteRange() + 1; j++) {
      int printNote = (sheet.highestNote().getPitch() + (sheet.getHighestOctave() * 12) - j + 1)
              % 12;
      g.drawString(sheet.pitchToString(printNote) + Integer.toString(highestOctave),
              70, (40 * j) + 20);
      if (printNote == 0) {
        highestOctave = highestOctave - 1;
      }
    }


    // Prints out all the rectangles
    for (int i = 1; i <= sheet.noteRange() + 1; i++) {
      for (int j = 1; j <= (sheet.songDuration() / 4) + 1; j++) {
        g.drawRect(100 * j, 40 * i, 100, 40);
      }
    }

    //fills the rectangles with the starting spots
    for (Map.Entry<Integer, ArrayList<ANote>> entry : sheet.getNotes().entrySet()) {
      int key = entry.getKey();
      ArrayList<ANote> notes = entry.getValue();
      for (int i = 0; i < notes.size(); i++) {
        g.fillRect(100 + (25 * key), height - (notes.get(i).totalNotePitch() + 1 - sheet
                .lowestNote().totalNotePitch()) * 40, 25, 40);
      }
    }

    //prints out all the note duration(the green ones)
    for (int i = 0; i <= sheet.songDuration(); i++) {
      ArrayList<ANote> noteshere = sheet.notesPlayingAtRow(i);

      for (int j = 0; j < noteshere.size(); j++) {
        //.out.println(height);
        if (!sheet.notesStartPlayingAtRow(i).contains(noteshere.get(j)) && !noteshere.get(j)
                .isSelected()) {
          g.setColor(Color.green);
          g.fillRect(100 + (25 * i), height - (noteshere.get(j).totalNotePitch() + 1 - sheet
                  .lowestNote().totalNotePitch()) * 40, 25, 40);
        }
        if (!sheet.notesStartPlayingAtRow(i).contains(noteshere.get(j)) && noteshere.get(j)
                .isSelected()) {
          g.setColor(Color.red);
          g.fillRect(100 + (25 * i), height - (noteshere.get(j).totalNotePitch() + 1 - sheet
                  .lowestNote().totalNotePitch()) * 40, 25, 40);
        }
      }
    }

    g.setColor(Color.blue);
    for (int i = 0; i < sheet.getRepeats().size(); i++) {
      Integer[] repeathere = sheet.getRepeats().get(i);
      if (repeathere[0] != 0) {
        g.drawLine(repeathere[0] * 25 + 100, 40, repeathere[0] * 25 + 100, height);
      }
      g.drawLine(repeathere[1] * 25 + 100, 40, repeathere[1] * 25 + 100, height);
    }
    g.setColor(Color.orange);
    for (int i = 0; i < sheet.getAltEndings().size(); i++) {
      for (int j = 0; j < sheet.getAltEndings().get(i).size(); j++) {
        int altending = sheet.getAltEndings().get(i).get(j);
        g.drawLine(altending * 25 + 100, 40, altending * 25 + 100, height);
      }
    }

    g.setColor(Color.yellow);
    if (sheet.getAlternateEnding() && sheet.getStart() != 0) {
      g.drawLine(sheet.getStart() * 25 + 100, 40, sheet.getStart() * 25 + 100, height);
      g.setColor(Color.pink);
      for (int i = 0; i < sheet.getEndTimeStart().size(); i++) {
        if (sheet.getEndTimeStart().get(i) != 0) {
          g.drawLine(sheet.getEndTimeStart().get(i) * 25 + 100, 40, sheet.getEndTimeStart().get
                          (i) * 25 +
                          100,
                  height);
        }
      }
    }

  }


  @Override
  public void paint(Graphics g) {
    super.paint(g);
    this.drawBackground(g);
    int height = this.getPreferredSize().height;
    g.setColor(Color.red);
    //System.out.println("here");
    //System.out.println("buzz");
    g.drawLine(sheet.getBeat() * 25 + 100, 40, sheet.getBeat() * 25 + 100, height);
    g.setColor(Color.black);


  }


  @Override
  public Dimension getPreferredSize() {
    return new Dimension(((sheet.songDuration() / 4) + 1) * 100, (sheet.noteRange() + 2) * 40);
  }


  /**
   * Adds a Key Listener to the super class.
   */
  public void addKeyListener(KeyListener key) {
    super.addKeyListener(key);
  }

  /**
   * Adds a Mouse Listener to the super class.
   */
  public void addMouseListener(MouseListener mouse) {
    super.addMouseListener(mouse);
  }


}

