package gui;

import levels.Laberinto;
import net.Despachador;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Tablero extends Canvas implements KeyListener {
    Laberinto laberinto = new Laberinto();
    int [][] lab = laberinto.obtenerLaberinto();
    private HashMap<String, Jugador> jugadores = new HashMap<>(2);
    private String jugadorPresente = "";
    private Despachador despachador;
    private final int ancho = 30;
    private final int alto = 30;
    private final int movimiento = 30;

    public Tablero()
    {
        super();
        setBackground(Color.white);
        setSize(690,390);
        addKeyListener(this);
        setFocusable(true);
   }

    public void paint(Graphics g) {
        int posicion = 30;
        for (Jugador j: jugadores.values()) {
            laberinto.paintfield(g);
            g.setColor(j.getLogin());
            g.fillOval(j.getX(), j.getY(), ancho, alto);
            posicion+=posicion;
        }
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                if(lab[(jugadores.get(jugadorPresente).getY()/30)-1][jugadores.get(jugadorPresente).getX()/30] != 1){
                    jugadores.get(jugadorPresente).setY(jugadores.get(jugadorPresente).getY() - movimiento);
                }
                break;
            case KeyEvent.VK_RIGHT:
               if(lab[(jugadores.get(jugadorPresente).getY()) /30][(jugadores.get(jugadorPresente).getX()/30)+1] != 1){
                   jugadores.get(jugadorPresente).setX(jugadores.get(jugadorPresente).getX() + movimiento);
               }
                break;
            case KeyEvent.VK_DOWN:
                if(lab[(jugadores.get(jugadorPresente).getY()/30)+1][(jugadores.get(jugadorPresente).getX()/30)] != 1){
                    jugadores.get(jugadorPresente).setY(jugadores.get(jugadorPresente).getY() + movimiento);
                }
                break;
            case KeyEvent.VK_LEFT:
                if(lab[jugadores.get(jugadorPresente).getY()/30][(jugadores.get(jugadorPresente).getX()/30)-1] != 1){
                    jugadores.get(jugadorPresente).setX(jugadores.get(jugadorPresente).getX() - movimiento);
                }
                break;
        }

        int _x = jugadores.get(jugadorPresente).getX();
        int _y = jugadores.get(jugadorPresente).getY();
        despachador.send("mover:" + jugadorPresente + "," + _x + "," + _y );
    }

    public void setJugadorPresente(String jugadorPresente){
        this.jugadorPresente = jugadorPresente;
    }

    public HashMap<String, Jugador> getJugadores() {
        return jugadores;
    }

    public void setDespachador(Despachador despachador) {
        this.despachador = despachador;
    }

    public void actualizar(double dt) {
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) { }
}
