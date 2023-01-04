package gui;

import controller.Controlador;
import levels.Laberinto;
import net.Despachador;

import java.awt.*;
import java.util.HashMap;

public class Tablero extends Canvas {
    Laberinto laberinto = new Laberinto();
    int[][] lab = laberinto.obtenerLaberinto();
    private HashMap<String, Controlador.Jugador> jugadores = new HashMap<>(2);
    private String jugadorPresente = "";
    private Despachador despachador;
    private final int ancho = 30;
    private final int alto = 30;

    public Tablero() {
        super();
        setBackground(Color.white);
        setSize(690, 390);
        setFocusable(true);
    }

    public void paint(Graphics g) {

        for (Controlador.Jugador j : jugadores.values()) {
            laberinto.paintfield(g);
            g.setColor(j.getLogin());
            g.fillOval(j.getX(), j.getY(), ancho, alto);
        }

    }

    public void setJugadorPresente(String jugadorPresente){
        this.jugadorPresente = jugadorPresente;
    }

    public void setJugadores(HashMap<String, Controlador.Jugador> jugadores){
        this.jugadores = jugadores;
    }

    public HashMap<String, Controlador.Jugador> getJugadores() {
        return jugadores;
    }

}
