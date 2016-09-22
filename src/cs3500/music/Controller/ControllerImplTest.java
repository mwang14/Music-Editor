package cs3500.music.Controller;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.util.IllegalFormatCodePointException;

import cs3500.music.iMusic.Sheet;

import static org.junit.Assert.*;

/**
 * Created by Kathy on 11/24/2015.
 */
public class ControllerImplTest {


  @Test(expected = IllegalArgumentException.class)
  public void testAddOutOfRange1() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(9999, 5, 5, 5, 5, 5);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOutOfRange2() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(-2, 5, 5, 5, 5, 5);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testAddOutOfRange3() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(5, -2, 5, 5, 5, 5);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOutOfRange4() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(5, 5, -2, 5, 5, 5);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOutOfRange5() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(5, 11, 5, 5, 5, 5);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOutOfRange6() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(5, 5, 5, -2, 5, 5);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOutOfRange7() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(5, 5, 5, 3, 5, 5);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOutOfRange8() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(5, 5, 5, 5, -1, 5);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOutOfRange9() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(5, 5, 5, 5, 130, 5);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOutOfRange10() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(5, 5, 5, 5, 5, -1);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOutOfRange11() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(5, 5, 2, 5, 5, 150);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSameNote1() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(5, 5, 5, 5, 5, 5);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }


  @Test
  public void testNoteOutOfRange() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(10, 9, 10, 10, 10, 10);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }

  @Test
  public void testAddNote() {
    Sheet s = new Sheet();
    s.addNote(5, 5, 5, 5, 5, 5);
    ControllerImpl controller = new ControllerImpl(s);
    KeyboardHandler k = controller.getKeyboardHandler();
    k.addRunnable(new Runnable() {
      @Override
      public void run() {
        s.addNote(10, 9, 3, 7, 10, 10);
      }
    }, KeyEvent.VK_J, KeyboardHandler.options.PRESSED);
    k.runPressed(KeyEvent.VK_J);
  }
}