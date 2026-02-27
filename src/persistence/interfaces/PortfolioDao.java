package persistence.interfaces;

import domain.Portfolio;

import java.util.List;

public interface PortfolioDao {
  void create(Portfolio portfolio);
  Portfolio getById(int id);
  List<Portfolio> getAll();
  void update(Portfolio portfolio);
  void delete(int id);
}