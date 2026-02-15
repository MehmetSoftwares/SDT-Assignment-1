package shared.logging;

public class Logger {
  private static volatile Logger instance;
  private LogOutput output;

  private Logger() {

  }

  public static Logger getInstance() {
    if (instance == null) {
      synchronized (Logger.class) {
        if (instance == null) {
          instance = new Logger();
        }
      }
    }
    return instance;
  }

   public void setLogOutput(LogOutput output) {
    this.output = output;
  }

  public void log(String level, String message) {
    if (output != null) {
      output.log(level, message);
    } else {
      System.out.println("Logger Error: No LogOutput set!");
    }
  }
}