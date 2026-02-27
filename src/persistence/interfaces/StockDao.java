package persistence.interfaces;
import domain.Stock;
import java.util.List;

public interface StockDao {
  void create(Stock stock);
  Stock getById(String symbol);
  List<Stock> getAll();
  void update(Stock stock);
  void delete(String symbol);
}