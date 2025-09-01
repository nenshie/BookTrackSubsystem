package ui;

import controller.MagacinController;
import domain.entities.Magacin;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MagacinForm extends JFrame {


    private final MagacinController controller = new MagacinController();
    private final DefaultTableModel model;
    private final JTable table;

    public MagacinForm() {
        setTitle("Pregled magacina");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] columnNames = {
                "MagacinID",
                "Naziv"
        };

        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 1;
            }
        };

        table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(300, 150));
        table.setFillsViewportHeight(true);




        JLabel naslovLabel = new JLabel("Pregled magacina");
        naslovLabel.setFont(new Font("Arial", Font.BOLD, 18));
        naslovLabel.setHorizontalAlignment(SwingConstants.LEFT);
        naslovLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(naslovLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tablePanel.add(scrollPane);

        centerPanel.add(tablePanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);


        model.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int col = e.getColumn();

                if (col == 1) {
                    try {
                        int magacinId = (int) model.getValueAt(row, 0);
                        String naziv = (String) model.getValueAt(row, 1);

                        Magacin updated = new Magacin(
                                magacinId,
                                naziv
                        );

                        controller.updateMagacin(updated);
                        JOptionPane.showMessageDialog(this, "Uspešno ažuriranje!");


                    } catch (SQLException ex) {
                        prikaziGresku("Greška pri izmeni naziva:\n" + ex.getMessage());
                        loadMagacini();
                    }
                }
            }
        });

        loadMagacini();
    }

    private void loadMagacini() {
        try {
            model.setRowCount(0);
            List<Magacin> magacini = controller.getAllMagacini();
            for (Magacin m : magacini) {
                model.addRow(new Object[]{
                        m.getMagacinId(),
                        m.getNaziv()
                });
            }
        } catch (SQLException e) {
            prikaziGresku("Greška pri učitavanju zaliha:\n" + e.getMessage());
        }
    }


    private void prikaziGresku(String poruka) {
        JOptionPane.showMessageDialog(this, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
    }
}
