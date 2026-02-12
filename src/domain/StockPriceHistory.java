package domain;

import java.time.LocalDateTime;

public class StockPriceHistory {
  private final int id;
  private String stockSymbol;
  private double price;
  private final LocalDateTime timestamp;

  public StockPriceHistory(int id, String stockSymbol, double price, LocalDateTime timestamp) {
    this.id = id;
    this.stockSymbol = stockSymbol;
    this.price = price;
    this.timestamp = timestamp;
  }

  public int getId() {
    return id;
  }

  public String getStockSymbol() {
    return stockSymbol;
  }

  public void setStockSymbol(String stockSymbol) {
    this.stockSymbol = stockSymbol;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}