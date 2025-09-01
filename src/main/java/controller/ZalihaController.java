package controller;

import domain.entities.Artikal;
import domain.entities.Zaliha;
import services.ArtikalService;
import services.ZalihaService;

import java.sql.SQLException;
import java.util.List;

public class ZalihaController {
    private final ZalihaService zalihaService;
    private final ArtikalService artikalService;

    public ZalihaController() {
        this.zalihaService = new ZalihaService();
        this.artikalService = new ArtikalService();
    }

    public List<Zaliha> getAllZaliheWithArtikalNaziv() throws SQLException {
        return zalihaService.getAllZaliheWithArtikalNaziv();
    }

    public Artikal getArtikalById(int artikalId) throws SQLException {
        return artikalService.getAll().stream()
                .filter(a -> a.getArtikalId() == artikalId)
                .findFirst()
                .orElse(null);
    }

    public int updateZaliha(Zaliha z) throws SQLException {
        return zalihaService.updateZaliha(z);
    }

    public int addZaliha(Zaliha z) throws SQLException {
        return zalihaService.addZaliha(z);
    }

    public int deleteZaliha(Zaliha z) throws SQLException {
        return zalihaService.deleteZaliha(z);
    }
}
