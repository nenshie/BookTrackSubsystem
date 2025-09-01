package controller;

import domain.entities.Magacin;
import domain.entities.Zaliha;
import domain.entities.Artikal;
import services.MagacinService;
import services.ZalihaService;
import services.ArtikalService;

import java.sql.SQLException;
import java.util.List;

public class ZalihaController {
    private final ZalihaService zalihaService;
    private final MagacinService magacinService;
    private final ArtikalService artikalService;

    public ZalihaController() {
        this.zalihaService = new ZalihaService();
        this.magacinService = new MagacinService();
        this.artikalService = new ArtikalService();
    }
    public boolean updateMagacinNaziv(int magacinId, String noviNaziv) throws SQLException {
        Magacin m = new Magacin();
        m.setMagacinId(magacinId);
        m.setNaziv(noviNaziv);
        // Parametri za ažuriranje - samo naziv ide u SET deo
        List<Object> updateParams = List.of(noviNaziv);
        // Parametri za WHERE deo - id magacina
        List<Object> whereParams = List.of(magacinId);
        int affected = magacinService.update(m, updateParams, whereParams);
        return affected > 0;
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
