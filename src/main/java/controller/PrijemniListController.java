package controller;

import domain.entities.PrijemniList;
import domain.entities.StavkaPL;
import services.PrijemniListService;
import services.StavkaPLService;

import java.sql.SQLException;
import java.util.List;

public class PrijemniListController {

    private final PrijemniListService prijemniListService;
    private final StavkaPLService stavkaPLService;

    public PrijemniListController() {
        prijemniListService = new PrijemniListService();
        stavkaPLService = new StavkaPLService();
    }

    public List<PrijemniList> getAllPrijemniListovi() throws SQLException {
        return prijemniListService.getAll();
    }

    public int addPrijemniList(PrijemniList pl) throws SQLException {
        return prijemniListService.add(pl);
    }

    public int updatePrijemniList(PrijemniList pl) throws SQLException {
        return prijemniListService.update(pl);
    }

    public int deletePrijemniList(PrijemniList pl) throws SQLException {
        return prijemniListService.delete(pl);
    }


    public List<StavkaPL> getAllStavke(int prijemniListId) throws SQLException {
        List<StavkaPL> sveStavke = stavkaPLService.getAll();
        return sveStavke.stream()
                .filter(s -> s.getPrijemniListId() == prijemniListId)
                .toList();
    }

    public int addStavka(StavkaPL stavka) throws SQLException {
        return stavkaPLService.add(stavka);
    }

    public int updateStavka(StavkaPL stavka) throws SQLException {
        return stavkaPLService.update(stavka);
    }

    public int deleteStavka(StavkaPL stavka) throws SQLException {
        return stavkaPLService.delete(stavka);
    }
}
