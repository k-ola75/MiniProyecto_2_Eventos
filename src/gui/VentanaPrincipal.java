package gui;

import net.Cliente;
import net.Despachador;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    Container panel;
    private Tablero lienzo;
    private Despachador despachador;

    public VentanaPrincipal() {
        super("----MAZE----");
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = getContentPane();
        panel.setLayout(new FlowLayout());
        JLabel txt_level = new JLabel("---------LEVEL 1---------");
        panel.add(txt_level);
        lienzo = new Tablero();
        panel.add(lienzo);

        Cliente conexion = new Cliente();
        conexion.conectar(this, lienzo); // se conecta al servidor
        conectar();  // conecta el usuario con el juego en linea
    }

    public void conectar() {
        String color = JOptionPane.showInputDialog(this, "Digita el color con el que deseas jugar: ",
                "Bienvenido al desafio de laberintos", JOptionPane.QUESTION_MESSAGE);
        despachador.send("login:" + color);
        lienzo.setJugadorPresente(color);
    }

    public void setLienzo(Tablero lienzo) {
        this.lienzo = lienzo;
    }

    public void setDespachador(Despachador despachador) {
        this.despachador = despachador;
    }

    public Tablero getLienzo() {
        return lienzo;
    }
}