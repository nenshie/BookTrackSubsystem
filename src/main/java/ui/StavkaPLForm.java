package ui;

import controller.PrijemniListController;
import services.StavkaPLService;
import domain.entities.PrijemniList;
import domain.entities.StavkaPL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StavkaPLForm extends JFrame {

    private PrijemniList parentPrijemniList;
    private PrijemniListForm parentPrijemniListForm;

    private List<StavkaPL> stavkaPLList;
    private StavkaPLService stavkaPLService;

    private JTable tableStavke;
    private JButton btnDodaj;
    private JButton btnObrisi;
    private JLabel lblNaslov;

    public StavkaPLForm(PrijemniList prijemniList, PrijemniListForm parentForm) {
        this.parentPrijemniList = prijemniList;
        this.parentPrijemniListForm = parentForm;
        stavkaPLList = new ArrayList<>();
        stavkaPLService = new StavkaPLService();

        initComponents();
        setTitle("Stavke prijemnog lista " + parentPrijemniList.getPrijemniListId());
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        lblNaslov = new JLabel("Stavke prijemnog lista: " + parentPrijemniList.getPrijemniListId());
        lblNaslov.setFont(new Font("Arial", Font.BOLD, 16));
        lblNaslov.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblNaslov, BorderLayout.NORTH);

        tableStavke = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableStavke);
        scrollPane.setPreferredSize(new Dimension(700, 200));

        JPanel pnlTabela = new JPanel(new BorderLayout());
        pnlTabela.add(scrollPane, BorderLayout.CENTER);
        pnlTabela.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnDodaj = new JButton("Dodaj");
        btnObrisi = new JButton("Obriši");

        JPanel pnlDugmici = new JPanel();
        pnlDugmici.setLayout(new BoxLayout(pnlDugmici, BoxLayout.Y_AXIS));
        btnDodaj.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnObrisi.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlDugmici.add(btnDodaj);
        pnlDugmici.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlDugmici.add(btnObrisi);
        pnlDugmici.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 20));

        JPanel pnlCenter = new JPanel(new BorderLayout());
        pnlCenter.add(pnlTabela, BorderLayout.CENTER);
        pnlCenter.add(pnlDugmici, BorderLayout.EAST);

        add(pnlCenter, BorderLayout.CENTER);

        // Load data
        try {
            ucitajStavke();
            popuniTabelu();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Greška pri učitavanju stavki: " + e.getMessage());
        }

        btnDodaj.addActionListener(e -> {
            StavkaPLDialog dialog = new StavkaPLDialog(this, parentPrijemniList.getPrijemniListId());
            dialog.setVisible(true);

            try {
                ucitajStavke();
                popuniTabelu();

                if (parentPrijemniListForm != null) {
                    parentPrijemniListForm.ucitajPrijemneListove();
                    parentPrijemniListForm.popuniTabelu();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Greška pri osvežavanju: " + ex.getMessage());
            }
        });

        btnObrisi.addActionListener(e -> obrisiStavku());
    }

    private void ucitajStavke() throws Exception {
        stavkaPLList = stavkaPLService.getAll();
        stavkaPLList.removeIf(s -> s.getPrijemniListId() != parentPrijemniList.getPrijemniListId());
    }

    private void popuniTabelu() {
        String[] kolone = {"RB", "Kolicina", "Sifra PDV", "Artikal ID"};
        DefaultTableModel model = new DefaultTableModel(kolone, 0);

        for (StavkaPL s : stavkaPLList) {
            Object[] red = { s.getRb(), s.getKolicina(), s.getSifraPDV(), s.getArtikalId() };
            model.addRow(red);
        }

        tableStavke.setModel(model);
    }

    private void obrisiStavku() {
        int selectedRow = tableStavke.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Izaberite stavku za brisanje!");
            return;
        }

        StavkaPL stavka = stavkaPLList.get(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Da li ste sigurni da želite obrisati stavku RB " + stavka.getRb() + "?",
                "Potvrda brisanja",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                stavkaPLService.delete(stavka);
                ucitajStavke();
                popuniTabelu();
                JOptionPane.showMessageDialog(this, "Stavka obrisana. UkupnoStavki se automatski ažurira preko trigera.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Greška prilikom brisanja: " + e.getMessage());
            }
        }
    }
}
