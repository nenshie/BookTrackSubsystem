package controller;

import domain.entities.PrijemniList;
import services.PrijemniListService;

import java.util.List;

public class PrijemniListController {

    private PrijemniListService service;

    public PrijemniListController() {
        service = new PrijemniListService();
    }

    public int addPrijemniList(PrijemniList pl) throws Exception {
        return service.add(pl);
    }

    public int updatePrijemniList(PrijemniList pl) throws Exception {
        return service.update(pl);
    }

    public int deletePrijemniList(PrijemniList pl) throws Exception {
        return service.delete(pl);
    }

    public List<PrijemniList> getAllPrijemniListovi() throws Exception {
        return service.getAll();
    }
}
