package persistence.fileimplementation;

import domain.OwnedStock;
import domain.Portfolio;
import domain.Stock;
import persistence.interfaces.UnitOfWork;
import shared.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUnitOfWork implements UnitOfWork
{

  private String directoryPath;
  private static final Object FILE_WRITE_LOCK = new Object();
  private Logger logger = Logger.getInstance();

  private List<Stock> stocks;
  private List<Portfolio> portfolios;
  private List<OwnedStock> ownedStocks;

  public FileUnitOfWork(String directoryPath)
  {
    this.directoryPath = directoryPath;
    ensureFilesExist();
  }

  private void ensureFilesExist()
  {
    try
    {
      File dir = new File(directoryPath);
      if (!dir.exists())
      {
        dir.mkdirs();
      }

      File stockFile = new File(directoryPath + "/stocks.txt");
      if (!stockFile.exists())
      {
        stockFile.createNewFile();
      }

      File portfolioFile = new File(directoryPath + "/portfolios.txt");
      if (!portfolioFile.exists())
      {
        portfolioFile.createNewFile();
      }

      File ownedStockFile = new File(directoryPath + "/ownedstocks.txt");
      if (!ownedStockFile.exists())
      {
        ownedStockFile.createNewFile();
      }

    }
    catch (IOException e)
    {
      logger.log("ERROR",
          "Failed to create files in directory: " + directoryPath);
      throw new RuntimeException("Failed to ensure files exist", e);
    }
  }

  public List<Stock> getStocks()
  {
    if (stocks == null)
    {
      stocks = new ArrayList<>();
      List<String> lines = readAllLines(directoryPath + "/stocks.txt");

      for (String line : lines)
      {
        if (!line.trim().isEmpty())
        {
          stocks.add(fromStockPSV(line));
        }
      }
    }
    return stocks;
  }

  public List<Portfolio> getPortfolios()
  {
    if (portfolios == null)
    {
      portfolios = new ArrayList<>();
      List<String> lines = readAllLines(directoryPath + "/portfolios.txt");

      for (String line : lines)
      {
        if (!line.trim().isEmpty())
        {
          portfolios.add(fromPortfolioPSV(line));
        }
      }
    }
    return portfolios;
  }

  public List<OwnedStock> getOwnedStocks()
  {
    if (ownedStocks == null)
    {
      ownedStocks = new ArrayList<>();
      List<String> lines = readAllLines(directoryPath + "/ownedstocks.txt");

      for (String line : lines)
      {
        if (!line.trim().isEmpty())
        {
          ownedStocks.add(fromOwnedStockPSV(line));
        }
      }
    }
    return ownedStocks;
  }

  private List<String> readAllLines(String filePath)
  {
    try
    {
      return Files.readAllLines(Paths.get(filePath));
    }
    catch (IOException e)
    {
      logger.log("ERROR", "Failed to read from file: " + filePath);
      throw new RuntimeException("Failed to read from file: " + filePath, e);
    }
  }

  private String toPSV(Stock stock)
  {
    return stock.getSymbol() + "|" + stock.getName() + "|"
        + stock.getCurrentPrice() + "|" + stock.getCurrentState();
  }

  private Stock fromStockPSV(String psv)
  {
    String[] parts = psv.split("\\|");
    return new Stock(parts[0], parts[1], Double.parseDouble(parts[2]),
        parts[3]);
  }

  private String toPSV(Portfolio portfolio)
  {
    return portfolio.getId() + "|" + portfolio.getCurrentBalance();
  }

  private Portfolio fromPortfolioPSV(String psv)
  {
    String[] parts = psv.split("\\|");
    return new Portfolio(Integer.parseInt(parts[0]),
        Double.parseDouble(parts[1]));
  }

  private String toPSV(OwnedStock ownedStock)
  {
    return ownedStock.getId() + "|" + ownedStock.getPortfolioId() + "|"
        + ownedStock.getStockSymbol() + "|" + ownedStock.getNumberOfShares();
  }

  private OwnedStock fromOwnedStockPSV(String psv)
  {
    String[] parts = psv.split("\\|");
    return new OwnedStock(Integer.parseInt(parts[0]),
        Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]));
  }

  @Override public void begin()
  {
    clearData();
  }

  @Override public void rollback()
  {
    clearData();
  }

  @Override public void commit()
  {
    synchronized (FILE_WRITE_LOCK)
    {

      if (stocks != null)
      {
        saveToFile("/stocks.txt", stocks.stream().map(this::toPSV).toList());
      }

      if (portfolios != null)
      {
        saveToFile("/portfolios.txt",
            portfolios.stream().map(this::toPSV).toList());
      }

      if (ownedStocks != null)
      {
        saveToFile("/ownedstocks.txt",
            ownedStocks.stream().map(this::toPSV).toList());
      }
    }
    clearData();
  }

  private void saveToFile(String fileName, List<String> lines)
  {
    try
    {
      Files.write(Paths.get(directoryPath + fileName), lines);
    }
    catch (IOException e)
    {
      logger.log("ERROR", "Failed to save to file: " + fileName);
      throw new RuntimeException("Failed to save data", e);
    }
  }

  private void clearData()
  {
    this.stocks = null;
    this.portfolios = null;
    this.ownedStocks = null;
  }
}