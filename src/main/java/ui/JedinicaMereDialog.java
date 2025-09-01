package ui;

import controller.ArtikalController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class JedinicaMereDialog extends JDialog {

    private boolean succeeded;
    private JTextField txtNovaJedinica;
    private final JComboBox<String> cbPostojece;
    private final ArtikalController controller = new ArtikalController();

    public JedinicaMereDialog(Frame parent, java.util.List<String> postojece) {
        super(parent, "Dodaj jedinicu mere", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(5,5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        topPanel.add(new JLabel("Postojeće vrednosti:"), BorderLayout.NORTH);

        cbPostojece = new JComboBox<>();
        for(String jm : postojece){
            cbPostojece.addItem(jm);
        }
        topPanel.add(cbPostojece, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        inputPanel.add(new JLabel("Nova jedinica mere:"));
        txtNovaJedinica = new JTextField(15);
        inputPanel.add(txtNovaJedinica);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDodaj = new JButton("Dodaj");
        JButton btnCancel = new JButton("Otkaži");

        btnDodaj.addActionListener(e -> {
            String nova = txtNovaJedinica.getText().trim();
            if(nova.isEmpty()){
                JOptionPane.showMessageDialog(this, "Unesite naziv jedinice mere.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try{
                controller.dodajJedinicuMere(nova);
                JOptionPane.showMessageDialog(this, "Jedinica mere uspešno dodata!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                succeeded = true;
                dispose();
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(this, "Greška pri dodavanju jedinice: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> dispose());
        btnPanel.add(btnDodaj);
        btnPanel.add(btnCancel);

        add(topPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}
