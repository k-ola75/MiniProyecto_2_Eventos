package net;

import gui.Tablero;
import gui.VentanaPrincipal;

import java.net.Socket;

public class Cliente {

    private String hostName;
    private int portNumber;

    public Cliente(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    public Despachador conectar(VentanaPrincipal gui) throws Exception
    {
            Socket kkSocket = new Socket(hostName, portNumber);

            Despachador cliente = new Despachador(kkSocket);
            cliente.setGui(gui);
            cliente.start();

        return cliente;
    }
}