package net;

import gui.Tablero;
import gui.VentanaPrincipal;

import java.net.Socket;

public class Cliente {
    public void conectar(VentanaPrincipal gui, Tablero tablero) {
        String hostName = "localhost";
        int portNumber = 1234;

        try {
            Socket kkSocket = new Socket(hostName, portNumber);

            Despachador cliente = new Despachador(kkSocket);//jugador
            cliente.setGui(gui);
            gui.getLienzo().setDespachador(cliente);
            gui.setDespachador(cliente);
            cliente.start();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
    }
}