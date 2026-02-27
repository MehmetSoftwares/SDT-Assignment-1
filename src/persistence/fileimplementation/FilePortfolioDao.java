package persistence.fileimplementation;

import domain.Portfolio;
import persistence.interfaces.PortfolioDao;
import shared.logging.Logger;

import java.util.List;

public class FilePortfolioDao implements PortfolioDao {

  private FileUnitOfWork uow;
  private static int nextId = 1;
  private Logger logger = Logger.getInstance();

  public FilePortfolioDao(FileUnitOfWork uow) {
    this.uow = uow;
    findNextId();
  }

  private void findNextId() {
    List<Portfolio> portfolios = uow.getPortfolios();
    for (Portfolio p : portfolios) {
      if (p.getId() >= nextId) {
        nextId = p.getId() + 1;
      }
    }
  }

  @Override
  public void create(Portfolio portfolio) {
    portfolio.setId(nextId++);
    uow.getPortfolios().add(portfolio);
  }

  @Override
  public Portfolio getById(int id) {
    for (Portfolio p : uow.getPortfolios()) {
      if (p.getId() == id) {
        return p;
      }
    }
    logger.log("WARNING", "Portfolio with ID " + id + " not found.");
    return null;
  }

  @Override
  public List<Portfolio> getAll() {
    return uow.getPortfolios();
  }

  @Override
  public void update(Portfolio portfolio) {
    List<Portfolio> portfolios = uow.getPortfolios();
    for (int i = 0; i < portfolios.size(); i++) {
      if (portfolios.get(i).getId() == portfolio.getId()) {
        portfolios.set(i, portfolio);
        return;
      }
    }
    logger.log("WARNING", "Cannot update. Portfolio with ID " + portfolio.getId() + " not found.");
  }

  @Override
  public void delete(int id) {
    List<Portfolio> portfolios = uow.getPortfolios();
    for (int i = 0; i < portfolios.size(); i++) {
      if (portfolios.get(i).getId() == id) {
        portfolios.remove(i);
        return;
      }
    }
    logger.log("WARNING", "Cannot delete. Portfolio with ID " + id + " not found.");
  }
}