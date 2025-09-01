package ui;

import controller.DostavnicaController;
import domain.entities.Dostavnica;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DostavnicaForm extends JFrame {

    private final DostavnicaController controller = new DostavnicaController();
    private final DefaultTableModel model;
    private final JTable table;

    public DostavnicaForm() {
        setTitle("Pregled dostavnica");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNaslov = new JLabel("Pregled svih dostavnica");
        lblNaslov.setFont(new Font("Arial", Font.BOLD, 16));
        lblNaslov.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(lblNaslov);
        add(headerPanel, BorderLayout.NORTH);

        String[] kolone = {"DostavnicaId", "Datum Izdavanja", "Tekuci Racun", "Opis", "Radnik Odgovoran",
                "Ulica", "Mesto", "Broj", "Dobavljac", "Predracun", "Radnik", "Valuta"};

        model = new DefaultTableModel(kolone, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col != 0;
            }
        };

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);

        table.getColumnModel().getColumn(5).setPreferredWidth(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(60);
        table.getColumnModel().getColumn(7).setPreferredWidth(60);
        table.getColumnModel().getColumn(8).setPreferredWidth(80);
        table.getColumnModel().getColumn(9).setPreferredWidth(80);
        table.getColumnModel().getColumn(10).setPreferredWidth(80);
        table.getColumnModel().getColumn(11).setPreferredWidth(80);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnOsvezi = new JButton("Osveži");
       // JButton btnDodaj = new JButton("Dodaj");
        JButton btnObrisi = new JButton("Obriši");

        btnPanel.add(btnOsvezi);
        //btnPanel.add(btnDodaj);
        btnPanel.add(btnObrisi);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));
        add(btnPanel, BorderLayout.SOUTH);

        ucitajDostavnice();

        model.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                try {
                    Dostavnica d = new Dostavnica();
                    d.setDostavnicaId((int) model.getValueAt(row, 0));
                    d.setDatumIzdavanja(parseDate(model.getValueAt(row, 1)));
                    d.setTekuciRacun((String) model.getValueAt(row, 2));
                    d.setOpis((String) model.getValueAt(row, 3));
                    d.setRadnikOdgovoran((String) model.getValueAt(row, 4));
                    d.setUlica(Integer.parseInt(model.getValueAt(row, 5).toString()));
                    d.setMesto(Integer.parseInt(model.getValueAt(row, 6).toString()));
                    d.setBroj(Integer.parseInt(model.getValueAt(row, 7).toString()));
                    d.setDobavljac(Integer.parseInt(model.getValueAt(row, 8).toString()));
                    d.setPredracun(Integer.parseInt(model.getValueAt(row, 9).toString()));
                    d.setRadnik(Integer.parseInt(model.getValueAt(row, 10).toString()));
                    d.setValuta(Integer.parseInt(model.getValueAt(row, 11).toString()));

                    controller.updateDostavnica(d);
                    JOptionPane.showMessageDialog(this, "Dostavnica uspešno izmenjena.");
                    ucitajDostavnice();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Greška pri izmeni: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    ucitajDostavnice();
                }
            }
        });

        btnOsvezi.addActionListener(e -> ucitajDostavnice());

//        btnDodaj.addActionListener(e -> {
//            JOptionPane.showMessageDialog(this, "Ovde će biti forma za dodavanje dostavnice.");
//        });



        btnObrisi.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) model.getValueAt(row, 0);
                try {
                    controller.deleteDostavnicaById(id);
                    JOptionPane.showMessageDialog(this, "Dostavnica obrisana.");
                    ucitajDostavnice();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Greška pri brisanju: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Greška: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Izaberi dostavnicu za brisanje.");
            }
        });
    }

    private void ucitajDostavnice() {
        try {
            model.setRowCount(0);
            List<Dostavnica> lista = controller.getAllDostavnice();
            for (Dostavnica d : lista) {
                model.addRow(new Object[]{
                        d.getDostavnicaId(),
                        d.getDatumIzdavanja(),
                        d.getTekuciRacun(),
                        d.getOpis(),
                        d.getRadnikOdgovoran(),
                        d.getUlica(),
                        d.getMesto(),
                        d.getBroj(),
                        d.getDobavljac(),
                        d.getPredracun(),
                        d.getRadnik(),
                        d.getValuta()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Greška pri učitavanju dostavnica: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private java.sql.Date parseDate(Object value) throws SQLException {
        if (value instanceof java.sql.Date) {
            return (java.sql.Date) value;
        } else if (value instanceof java.util.Date) {
            return new java.sql.Date(((java.util.Date) value).getTime());
        } else if (value instanceof String) {
            return java.sql.Date.valueOf((String) value);
        } else {
            throw new SQLException("Nevažeći format datuma");
        }
    }
}
