package controller;

import domain.entities.StavkaPredracuna;
import services.StavkaPredracunaService;

import java.sql.SQLException;
import java.util.List;

public class StavkaPredracunaController {

    private final StavkaPredracunaService service = new StavkaPredracunaService();

    public List<StavkaPredracuna> getAllStavke() throws SQLException {
        return service.getAll();
    }

    public int addStavka(StavkaPredracuna stavka) throws SQLException {
        return service.add(stavka);
    }

    public int updateStavka(StavkaPredracuna stavka) throws SQLException {
        return service.update(stavka);
    }

    public int deleteStavka(StavkaPredracuna stavka) throws SQLException {
        return service.delete(stavka);
    }

    public List<StavkaPredracuna> getStavkeByPredracun(int brojPredracuna) throws SQLException {
        return service.getAllByPredracun(brojPredracuna);
    }
}
