package shared.configuration;

public class AppConfig {
  private static AppConfig instance;


  private final int startingBalance;
  private final double transactionFee;
  private final int updateFrequencyInMs;
  private final double stockResetValue;

  private AppConfig() {

    this.startingBalance = 10000; //hvor mange penge man starter med
    this.transactionFee = 0.05; // 5% gebyr pr handel
    this.updateFrequencyInMs = 1000; // 1 sekund opdateringshastighed
    this.stockResetValue = 0.0; // nulstillings værdi for aktier
  }

    public static AppConfig getInstance() {
    if (instance == null) {
      instance = new AppConfig();
    }
    return instance;
  }

  public int getStartingBalance() {
    return startingBalance;
  }

  public double getTransactionFee() {
    return transactionFee;
  }

  public int getUpdateFrequencyInMs() {
    return updateFrequencyInMs;
  }

  public double getStockResetValue() {
    return stockResetValue;
  }
}