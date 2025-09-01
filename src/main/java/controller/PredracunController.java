package controller;

import domain.entities.Predracun;
import services.PredracunService;

import java.sql.SQLException;
import java.util.List;

public class PredracunController {
    private PredracunService service;

    public PredracunController() {
        this.service = new PredracunService();
    }

    public List<Predracun> getAllPredracuni() throws SQLException {

            return service.getAllPredracuni();

    }


    public boolean addPredracun(Predracun p) throws SQLException {
            service.addPredracun(p);
            return true;

    }

    public boolean updatePredracun(Predracun p) throws SQLException {
            service.updatePredracun(p);
            return true;

    }

    public boolean deletePredracun(Predracun p) throws SQLException {
            service.deletePredracun(p);
            return true;

    }

    public List<Predracun> getAllByPartition(String partitionName) throws SQLException {
        return service.getAllByPartition(partitionName);
    }

    public List<String> getAllPartitions() throws SQLException {
        return service.getAllPartitions();
    }
}
