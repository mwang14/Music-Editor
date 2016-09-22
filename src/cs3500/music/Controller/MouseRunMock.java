package cs3500.music.Controller;

/**
 * Mock MouseRun function object for testing
 */
public class MouseRunMock implements MouseRun {
  StringBuilder s;

  public MouseRunMock(StringBuilder s) {
    this.s = s;
  }

  /**
   * Appends "Mouse Works" to a String Builder for the purpose of testing.
   */
  public void run(int x, int y) {
    s.append("Mouse Works");
  }
}