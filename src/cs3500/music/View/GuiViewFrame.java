package cs3500.music.View;

import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.Controller.KeyboardHandler;
import cs3500.music.iMusic.iMusic;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements GuiView {
  private final iMusic sheet;
  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel
  KeyboardHandler kbhandler = new KeyboardHandler();
  private final JScrollPane scrollPane;


  /**
   * Creates new GuiView
   */


  public GuiViewFrame(iMusic sheet) {
    this.sheet = sheet;
    ConcreteGuiViewPanel c = new ConcreteGuiViewPanel(sheet);
    this.displayPanel = c;
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    scrollPane = new JScrollPane(this.displayPanel);
    this.getContentPane().add(scrollPane);
    this.pack();
  }

  @Override
  public void updateScrollPane() {
    if (sheet.getBeat() * 25 > scrollPane.getHorizontalScrollBar().getValue() + this.getWidth
            ()) {
      // System.out.println("here");
      scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar()
              .getValue() + this.getWidth());
    } else if (sheet.getBeat() * 25 < scrollPane.getHorizontalScrollBar().getValue() - this
            .getWidth
            ()) {
      // System.out.println("here");
      scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar()
              .getValue() - this.getWidth());//((sheet.getBeat() * 25 - this.getWidth())) * this
              // .getWidth());
    }
  }

  // renders the view
  @Override
  public void render() {
    this.repaint();
    this.setVisible(true);
  }

  @Override
  public JScrollPane getScrollPane() {
    return this.scrollPane;
  }

  //ensures that it is visible
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(2000, 1000);
  }


  @Override
  public void setKeyListener(KeyboardHandler kbhandler) {
    this.kbhandler = kbhandler;
    this.addKeyListener(kbhandler);
  }

  @Override
  public void setMouseListener(MouseListener mhandler) {
    this.displayPanel.addMouseListener(mhandler);
  }

  @Override
  public boolean NoteatCoordinate(int x, int y) {
    int ytoNote = (y - 40) / 40;
    int xtoNote = (x - 100) / 25;
    return sheet.containsNoteHere(xtoNote, xtoNote, ytoNote);
  }

  @Override
  public void updateHorizontalScroll(int adjust) {
    int currentScroll = scrollPane.getHorizontalScrollBar().getValue();
    scrollPane.getHorizontalScrollBar().setValue(currentScroll + adjust);
  }

  @Override
  public void updateVerticalScroll(int adjust) {
    int currentScroll = scrollPane.getVerticalScrollBar().getValue();
    scrollPane.getVerticalScrollBar().setValue(currentScroll + adjust);
  }


}
