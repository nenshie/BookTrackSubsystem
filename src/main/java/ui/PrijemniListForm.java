package ui;

import controller.PrijemniListController;
import domain.entities.PrijemniList;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PrijemniListForm extends JFrame {

    private List<PrijemniList> prijemniListList;
    private PrijemniListController controller;

    private JTable tablePrijemniList;
    private JLabel lblNaslov;

    public PrijemniListForm() {
        prijemniListList = new ArrayList<>();
        controller = new PrijemniListController();

        initComponents();
        setTitle("Prijemni list");
        setSize(950, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        lblNaslov = new JLabel("Pregled prijemnih listova");
        lblNaslov.setFont(new Font("Arial", Font.BOLD, 18));
        lblNaslov.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lblNaslov.setHorizontalAlignment(SwingConstants.LEFT);
        add(lblNaslov, BorderLayout.NORTH);

        tablePrijemniList = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablePrijemniList);
        scrollPane.setPreferredSize(new Dimension(850, 200));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlTabela = new JPanel();
        pnlTabela.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlTabela.add(scrollPane);
        pnlTabela.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(pnlTabela, BorderLayout.WEST);

        try {
            ucitajPrijemneListove();
            popuniTabelu();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Greška pri učitavanju prijemnih listova: " + e.getMessage());
        }

        tablePrijemniList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int red = tablePrijemniList.getSelectedRow();
                    if (red >= 0) {
                        PrijemniList pl = prijemniListList.get(red);
                        StavkaPLForm stavkeForm = new StavkaPLForm(pl, PrijemniListForm.this);
                        stavkeForm.setVisible(true);
                    }
                }
            }
        });
    }

    public void ucitajPrijemneListove() throws Exception {
        prijemniListList = controller.getAllPrijemniListovi();
    }

    public void popuniTabelu() {
        String[] kolone = {"Prijemni list Id", "Datum od", "Osnov", "Komentar", "Dostavnica", "Radnik", "Ukupno Stavki"};
        DefaultTableModel model = new DefaultTableModel(kolone, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // sve kolone su neizmenljive
            }
        };

        for (PrijemniList pl : prijemniListList) {
            Object[] red = {
                    pl.getPrijemniListId(),
                    pl.getDatumOd(),
                    pl.getOsnov(),
                    pl.getKomentar(),
                    pl.getDostavnica(),
                    pl.getRadnik(),
                    pl.getUkupnoStavki()
            };
            model.addRow(red);
        }

        tablePrijemniList.setModel(model);
    }
}
