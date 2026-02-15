package Main;

import shared.logging.ConsoleLogOutput;
import shared.logging.LogOutput;
import shared.logging.Logger;

public class RunApp {
  public static void main(String[] args) {
       Logger logger = Logger.getInstance();

        LogOutput console = new ConsoleLogOutput();

        logger.setLogOutput(console);

        logger.log("INFO", "Application started");
    logger.log("WARNING", "Stock not found in database");


    try {

      throw new Exception("Database connection failed");
    } catch (Exception e) {
      logger.log("ERROR", "Failed to save data: " + e.getMessage());
    }
  }
}