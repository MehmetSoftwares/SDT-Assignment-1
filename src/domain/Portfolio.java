package domain;

public class Portfolio {
  private int id;
  private double currentBalance;

  public Portfolio(int id, double currentBalance) {
    this.id = id;
    this.currentBalance = currentBalance;
  }

  public int getId() {
    return id;
  }

  public double getCurrentBalance() {
    return currentBalance;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setCurrentBalance(double currentBalance) {
    this.currentBalance = currentBalance;
  }
}