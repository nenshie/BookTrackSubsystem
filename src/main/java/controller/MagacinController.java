package controller;

import domain.entities.Magacin;
import services.MagacinService;

import java.sql.SQLException;
import java.util.List;

public class MagacinController {

    private final MagacinService service;

    public MagacinController() {
        this.service = new MagacinService();
    }

    public int addMagacin(Magacin m) throws SQLException {
        return service.addMagacin(m);
    }

    public int updateMagacin(Magacin m) throws SQLException {
        return service.updateMagacin(m);
    }

    public int deleteMagacinById(int magacinId) throws SQLException {
        return service.deleteMagacinById(magacinId);
    }

    public List<Magacin> getAllMagacini() throws SQLException {
        return service.getAllMagacini();
    }

//    public Magacin getMagacinById(int magacinId) throws SQLException {
//        return service.getMagacinById(magacinId);
//    }
}
