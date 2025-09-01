package ui;

import domain.entities.StavkaPL;
import services.StavkaPLService;

import javax.swing.*;
import java.awt.*;

class StavkaPLDialog extends JDialog {

    private int prijemniListId;
    private JTextField txtRb, txtKolicina, txtSifraPDV, txtArtikalId;
    private JButton btnSacuvaj;
    private StavkaPLService stavkaService;

    public StavkaPLDialog(JFrame parent, int prijemniListId) {
        super(parent, "Dodaj stavku", true);
        this.prijemniListId = prijemniListId;
        this.stavkaService = new StavkaPLService();

        initComponents();
        setSize(300, 250);
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("RB:"));
        txtRb = new JTextField();
        add(txtRb);

        add(new JLabel("Količina:"));
        txtKolicina = new JTextField();
        add(txtKolicina);

        add(new JLabel("Šifra PDV:"));
        txtSifraPDV = new JTextField();
        add(txtSifraPDV);

        add(new JLabel("Artikal ID:"));
        txtArtikalId = new JTextField();
        add(txtArtikalId);

        btnSacuvaj = new JButton("Sačuvaj");
        add(btnSacuvaj);

        btnSacuvaj.addActionListener(e -> {
            try {
                int rb = Integer.parseInt(txtRb.getText());
                int kolicina = Integer.parseInt(txtKolicina.getText());
                int sifraPDV = Integer.parseInt(txtSifraPDV.getText());
                int artikalId = Integer.parseInt(txtArtikalId.getText());

                StavkaPL novaStavka = new StavkaPL(prijemniListId, rb, kolicina, sifraPDV, artikalId);
                stavkaService.add(novaStavka);

                JOptionPane.showMessageDialog(this, "Stavka uspešno dodata!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Greška pri dodavanju stavke: " + ex.getMessage());
            }
        });
    }
}