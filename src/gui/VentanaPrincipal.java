package gui;

import net.Cliente;
import net.Despachador;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    Container panel;
    private Tablero lienzo;

    public VentanaPrincipal() {
        super("----MAZE----");
        setSize(1000, 570);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        panel = getContentPane();
        panel.setLayout(new BorderLayout());

        JPanel titulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel txt_level = new JLabel("---------LEVEL 1---------");
        titulo.add(txt_level);
        panel.add(titulo, BorderLayout.NORTH);

        JPanel general = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        lienzo = new Tablero();
        general.add(lienzo);
        JTextArea historial = new JTextArea(30,20);
        historial.setEnabled(false);
        general.add(historial);
        JScrollPane scrollpane1=new JScrollPane(historial);
        scrollpane1.setBounds(10,10,100,50);
        general.add(scrollpane1);

        panel.add(general,BorderLayout.CENTER);
    }

    public Tablero getLienzo() {
        return lienzo;
    }
}