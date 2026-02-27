package persistence.fileimplementation;

import domain.Stock;
import persistence.interfaces.StockDao;
import shared.logging.Logger;
import java.util.List;

public class StockFileDAO implements StockDao {
  private FileUnitOfWork uow;
  private Logger logger = Logger.getInstance();

  public StockFileDAO(FileUnitOfWork uow) {
    this.uow = uow;
  }

  @Override
  public void create(Stock stock) {
    uow.getStocks().add(stock);
  }

  @Override
  public Stock getById(String symbol) {
    for (Stock s : uow.getStocks()) {
      if (s.getSymbol().equals(symbol)) return s;
    }
    logger.log("WARNING", "Stock " + symbol + " not found.");
    return null;
  }

  @Override
  public List<Stock> getAll() {
    return uow.getStocks();
  }

  @Override
  public void update(Stock stock) {
    List<Stock> stocks = uow.getStocks();
    for (int i = 0; i < stocks.size(); i++) {
      if (stocks.get(i).getSymbol().equals(stock.getSymbol())) {
        stocks.set(i, stock);
        return;
      }
    }
  }

  @Override
  public void delete(String symbol) {
    uow.getStocks().removeIf(s -> s.getSymbol().equals(symbol));
  }
}