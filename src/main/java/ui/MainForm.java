package ui;

import javax.swing.*;
import java.sql.SQLException;

public class MainForm extends javax.swing.JFrame {

    private JMenuBar jMenuBar1;
    private JMenu jMenuFileZalihe;
    private JMenu jMenuFileMagacin;

    private JMenu jMenuFilePredracuni;
    private JMenu jMenuFileReklamacioniList;
    private JMenu jMenuFileArtikli;
    private JMenu jMenuFileDostavnica;
    private JMenu jMenuFilePrijemniList;


    private JMenuItem menuZalihe;
    private JMenuItem menuMagacin;

    private JMenuItem menuPredraucn;
    private JMenuItem menuReklamacioniList;
    private JMenuItem menuArtikli;
    private JMenuItem menuDostavnica;
    private JMenuItem menuPrijemniList;

    public MainForm() {
        setTitle("Glavna forma");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        jMenuBar1 = new JMenuBar();

        jMenuFileZalihe = new JMenu("Zalihe");
        jMenuFileMagacin = new JMenu("Magacini");

        jMenuFilePredracuni = new JMenu("Predračuni");
        jMenuFileArtikli = new JMenu("Artikli");

        jMenuFileReklamacioniList = new JMenu("Reklamacioni List");
        jMenuFileDostavnica = new JMenu("Dostavnica");
        jMenuFilePrijemniList = new JMenu("Prijemni List");

        menuZalihe = new JMenuItem("Zalihe");
        menuMagacin = new JMenuItem("Magacini");

        menuPredraucn = new JMenuItem("Predračuni");
        menuArtikli = new JMenuItem("Artikli");

        menuReklamacioniList = new JMenuItem("Reklamacioni List");

        menuDostavnica = new JMenuItem("Dostavnica");
        menuPrijemniList = new JMenuItem("Prijemni List");

        jMenuFileZalihe.add(menuZalihe);
        jMenuFileMagacin.add(menuMagacin);

        jMenuFilePredracuni.add(menuPredraucn);
        jMenuFileArtikli.add(menuArtikli);

        jMenuFileReklamacioniList.add(menuReklamacioniList);
        jMenuFileDostavnica.add(menuDostavnica);
        jMenuFilePrijemniList.add(menuPrijemniList);

        jMenuBar1.add(jMenuFileZalihe);
        jMenuBar1.add(jMenuFileMagacin);

        jMenuBar1.add(jMenuFilePredracuni);
        jMenuBar1.add(jMenuFileArtikli);

        jMenuBar1.add(jMenuFileReklamacioniList);

        jMenuBar1.add(jMenuFileDostavnica);
        jMenuBar1.add(jMenuFilePrijemniList);

        setJMenuBar(jMenuBar1);

        menuZalihe.addActionListener(e -> {
            ZaliheForm form = new ZaliheForm();
            form.setVisible(true);
        });
        menuMagacin.addActionListener(e -> {
            MagacinForm form = new MagacinForm();
            form.setVisible(true);
        });

        menuPredraucn.addActionListener(e -> {
            PredracunForm form = null;
            try {
                form = new PredracunForm();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            form.setVisible(true);
        });

        menuReklamacioniList.addActionListener(e -> {
            ReklamacioniListForm form = new ReklamacioniListForm();
            form.setVisible(true);
        });

        menuArtikli.addActionListener(e -> {
            ArtikalForm form = new ArtikalForm();
            form.setVisible(true);
        });

        menuDostavnica.addActionListener(e -> {
            DostavnicaForm form = new DostavnicaForm();
            form.setVisible(true);
        });

        menuPrijemniList.addActionListener(e -> {
            PrijemniListForm form = new PrijemniListForm();
            form.setVisible(true);
        });
    }

}
