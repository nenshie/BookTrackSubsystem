package controller;

import domain.entities.ReklamacioniList;
import services.ReklamacioniListService;

import java.sql.SQLException;
import java.util.List;

public class ReklamacioniListController {
    private ReklamacioniListService service;

    public ReklamacioniListController() {
        this.service = new ReklamacioniListService();
    }

    public List<ReklamacioniList> getAllReklamacioniListovi() throws Exception {
            return service.getAll();

    }

    public boolean addReklamacioniList(ReklamacioniList rl) throws Exception {
            service.add(rl);
            return true;

    }

    public boolean updateReklamacioniList(ReklamacioniList rl) throws Exception {
            service.update(rl);
            return true;
    }

    public boolean deleteReklamacioniList(ReklamacioniList rl) throws Exception {
            service.delete(rl);
            return true;
    }

    public String proveriDatum(int reklamacioniListId) throws SQLException {
            return service.proveriDatum(reklamacioniListId);
    }
}
