package net;

import gui.Jugador;
import gui.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Despachador extends Thread {
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;
    private VentanaPrincipal gui = null;
    private ArrayList<Despachador> jugadoresenlinea = new ArrayList<>();
    private HashMap<String, Jugador> jugadores = new HashMap<>(2);
    private int Xnicial = 0, Yinicial = 0;

    public Timer tiempo;

    public Despachador(Socket socket) {
        try {
            this.in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.socket = socket;
        } catch (Exception e) {
            System.out.println("Error Despachador: " + e.getMessage());
        }
    }

    public void run() {
        try {
            iniciarJuego();
        } catch (Exception e) {
            System.out.println("Error Run: " + e.getMessage());
        }
    }

    private void iniciarJuego() throws IOException {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (gui != null) {  // Cliente
                despachadorCliente(inputLine);
            }
            if (gui == null) {  // Servidor
                despachadorServidor(inputLine);
            }
        }
    }

    public void despachadorCliente(String inputLine) {
        double tiempoUltimoFrame = 0.0;
        String[] datosJugadores = inputLine.split("#");
        for (String jugador : datosJugadores) {
            String[] login = jugador.split(",");
            gui.getLienzo().getJugadores().put(login[0], new Jugador(login[0], seleccionarcolor(login[0]), Integer.parseInt(login[1]), Integer.parseInt(login[2])));

            if (Integer.parseInt(login[1]) == 330 && Integer.parseInt(login[2]) == 180) {
                JOptionPane.showMessageDialog(null, "Ganador: Jugador  " + login[0]);
                System.exit(0);
            }
        }
        gui.getLienzo().repaint();
    }

    public void despachadorServidor(String inputLine) {
        String[] datos = inputLine.split(":");
        if (datos[0].equals("login")) {
            jugadores.put(datos[1], new Jugador(datos[1], seleccionarcolor(datos[1]), Xnicial, Yinicial));
        } else if (datos[0].equals("mover")) {
            String[] datosJugador = datos[1].split(",");
            jugadores.get(datosJugador[0]).setX(Integer.parseInt(datosJugador[1]));
            jugadores.get(datosJugador[0]).setY(Integer.parseInt(datosJugador[2]));
        }

        String[] lista = new String[jugadores.size()];
        int index = 0;
        for (Jugador e : jugadores.values()) {
            lista[index++] = e.getNickname() + "," + e.getX() + "," + e.getY();
        }

        for (Despachador e : jugadoresenlinea) {
            e.send(String.join("#", lista));
        }
    }

    public void send(String inputLine) {
        try {
            out.println(inputLine);
        } catch (Exception e) {
            System.out.println("Error Send: " + e.getMessage());
        }
    }

    public Color seleccionarcolor(String seleccionadoColor) {
        switch (seleccionadoColor) {
            case "rojo":
                return Color.RED;
            case "verde":
                return Color.GREEN;
            case "azul":
                return Color.BLUE;
            default:
                return Color.black;
        }
    }

    public void asignarposicioninicial(int numerojugadores) {
        switch (numerojugadores) {
            case 1:
                Xnicial = 30;
                Yinicial = 30;
                break;
            case 2:
                Xnicial = 630;
                Yinicial = 330;
                break;
        }

    }

    public void setGui(VentanaPrincipal gui) {
        this.gui = gui;
    }

    public void setJugadoresenlinea(ArrayList<Despachador> jugadoresenlinea) {
        this.jugadoresenlinea = jugadoresenlinea;
    }

}