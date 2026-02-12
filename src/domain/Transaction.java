package domain;

import java.time.LocalDateTime;

public class Transaction {
  private final int id;
  private int portfolioId;
  private String stockSymbol;
  private String type;
  private int quantity;
  private double pricePerShare;
  private double totalAmount;
  private double fee;
  private final LocalDateTime timestamp;

  public Transaction(int id, int portfolioId, String stockSymbol, String type, int quantity, double pricePerShare, double totalAmount, double fee, LocalDateTime timestamp) {
    this.id = id;
    this.portfolioId = portfolioId;
    this.stockSymbol = stockSymbol;
    this.type = type;
    this.quantity = quantity;
    this.pricePerShare = pricePerShare;
    this.totalAmount = totalAmount;
    this.fee = fee;
    this.timestamp = timestamp;
  }

  public int getId() {
    return id;
  }

  public int getPortfolioId() {
    return portfolioId;
  }

  public void setPortfolioId(int portfolioId) {
    this.portfolioId = portfolioId;
  }

  public String getStockSymbol() {
    return stockSymbol;
  }

  public void setStockSymbol(String stockSymbol) {
    this.stockSymbol = stockSymbol;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPricePerShare() {
    return pricePerShare;
  }

  public void setPricePerShare(double pricePerShare) {
    this.pricePerShare = pricePerShare;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public double getFee() {
    return fee;
  }

  public void setFee(double fee) {
    this.fee = fee;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}