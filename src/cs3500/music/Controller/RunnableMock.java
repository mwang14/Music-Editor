package cs3500.music.Controller;

/**
 * This is a mock Runnable function object used to test the KeyboardHandler
 */
class RunnableMock implements Runnable {
  StringBuilder s;

  public RunnableMock(StringBuilder s) {
    this.s = s;
  }

  @Override
  public void run() {
    s.append("test passed!");

  }
}