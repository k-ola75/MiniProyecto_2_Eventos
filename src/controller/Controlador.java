package controller;

import gui.VentanaPrincipal;
import levels.Laberinto;
import net.Cliente;
import net.Despachador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Controlador implements KeyListener
{
    VentanaPrincipal vista;
    public HashMap<String, Jugador> jugadores = new HashMap<>();
    public String jugadorPresente = "";
    public Despachador despachador;
    Laberinto laberinto = new Laberinto();

    int [][] lab = laberinto.obtenerLaberinto();

    public Controlador(VentanaPrincipal v)
    {
        vista = v;
        vista.getLienzo().setJugadores( jugadores );
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int movimiento = 30;
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                if(lab[(jugadores.get(jugadorPresente).getY()/ movimiento)-1][jugadores.get(jugadorPresente).getX()/ movimiento] != 1){
                    jugadores.get(jugadorPresente).moverY(-movimiento);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(lab[(jugadores.get(jugadorPresente).getY()) / movimiento][(jugadores.get(jugadorPresente).getX()/ movimiento)+1] != 1){
                    jugadores.get(jugadorPresente).moverX(movimiento);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(lab[(jugadores.get(jugadorPresente).getY()/movimiento)+1][(jugadores.get(jugadorPresente).getX()/movimiento)] != 1){
                    jugadores.get(jugadorPresente).moverY(movimiento);
                }
                break;
            case KeyEvent.VK_LEFT:
                if(lab[jugadores.get(jugadorPresente).getY()/30][(jugadores.get(jugadorPresente).getX()/30)-1] != 1){
                    jugadores.get(jugadorPresente).moverX(-movimiento);
                }
                break;
        }

        int _x = jugadores.get(jugadorPresente).getX();
        int _y = jugadores.get(jugadorPresente).getY();
        despachador.send("mover:" + jugadorPresente + "," + _x + "," + _y );
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) { }

    private void ingresar(){
        String color = JOptionPane.showInputDialog(vista, "Digita el color con el que deseas jugar: ",
                "Bienvenido al desafio de laberintos", JOptionPane.QUESTION_MESSAGE);
        despachador.send("login:" + color);
        jugadorPresente = color;
    }

    public void conectar()
    {
        Cliente conexion = new Cliente("localhost", 1234);
        try {
            despachador = conexion.conectar(vista);

            if (despachador != null) {
                ingresar();
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage());
        }
    }


    public static class Jugador {
        private Color login;
        private String nickname;
        private int x;
        private int y;

        public Jugador(String nick, Color log, int x, int y) {
            nickname = nick;
            login = log;
            this.x = x;
            this.y = y;
        }

        public Color getLogin() {
            return login;
        }

        public String getNickname() {
            return nickname;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void moverX(int movement)
        {
            x += movement;
        }

        public void moverY(int movement)
        {
            y += movement;
        }
    }
}
