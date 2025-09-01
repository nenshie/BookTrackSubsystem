package ui;

import controller.DostavnicaController;
import controller.PrijemniListController;
import domain.entities.Dostavnica;
import domain.entities.PrijemniList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PrijemniListForm extends javax.swing.JFrame {

    List<PrijemniList> prijemniListList;
    PrijemniListController controller;

    private JTable tablePrijemniList;
    private JButton btnDodaj;
    private JButton btnIzmeni;
    private JLabel lblNaslov;

    public PrijemniListForm() {
        prijemniListList = new ArrayList<>();
        controller = new PrijemniListController();

        initComponents();
        setTitle("Prijemni list");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        lblNaslov = new JLabel("Pregled prijemnih listova");
        lblNaslov.setFont(new Font("Arial", Font.BOLD, 18));
        lblNaslov.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblNaslov, BorderLayout.NORTH);

        tablePrijemniList = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablePrijemniList);
        scrollPane.setPreferredSize(new Dimension(800, 200));

        JPanel pnlTabela = new JPanel(new BorderLayout());
        pnlTabela.add(scrollPane, BorderLayout.WEST);
        pnlTabela.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlDugmici = new JPanel();
        pnlDugmici.setLayout(new BoxLayout(pnlDugmici, BoxLayout.Y_AXIS));

        btnDodaj = new JButton("Dodaj");
        btnIzmeni = new JButton("Izmeni");

        btnDodaj.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIzmeni.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlDugmici.add(btnDodaj);
        pnlDugmici.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlDugmici.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 20));
        pnlDugmici.add(btnIzmeni);

        JPanel pnlCenter = new JPanel(new BorderLayout());
        pnlCenter.add(pnlTabela, BorderLayout.CENTER);
        pnlCenter.add(pnlDugmici, BorderLayout.EAST);

        add(pnlCenter, BorderLayout.CENTER);

        try {
            ucitajArtikle();
            popuniTabelu();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Greška pri učitavanju liste prijemnih listova: " + e.getMessage());
        }

        btnDodaj.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Ovde će biti forma za dodavanje pl.");
        });
    }

    private void ucitajArtikle() throws Exception {
        prijemniListList = controller.getAllPrijemniListovi();
    }

    private void popuniTabelu() {
        String[] kolone = {"Prijemni list Id", "Datum od", "Osnov", "Komentar", "Dostavnica", "Radnik"};
        DefaultTableModel model = new DefaultTableModel(kolone, 0);

        for (PrijemniList pl : prijemniListList) {
            Object[] red = {
               pl.getPrijemniListId(),
                    pl.getDatumOd(),
                    pl.getOsnov(),
                    pl.getKomentar(),
                    pl.getDostavnica(),
                    pl.getRadnik()

            };
            model.addRow(red);
        }

        tablePrijemniList.setModel(model);
    }
}
