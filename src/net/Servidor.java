package net;

import controller.Controlador;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Servidor {

    private int portNumber;
    private int max_num_jugadores;
    private ArrayList<Despachador> jugadoresenlinea = new ArrayList<>();
    private HashMap<String, Controlador.Jugador> jugadores = new HashMap<>();

    public Servidor(int portNumber) {
        this.portNumber = portNumber;
    }
    public void conectar() throws Exception{

        int portNumber = 1234;
        int numeroJugadores = 0;

            ServerSocket serverSocket = new ServerSocket(portNumber);
            while (true) {
                System.out.println("ESPERANDO JUGADORES...");
                Socket clientSocket = serverSocket.accept();
                numeroJugadores++;
                Despachador servidor = new Despachador(clientSocket);
                servidor.asignarposicioninicial(numeroJugadores);
                jugadoresenlinea.add(servidor);
                servidor.setJugadoresenlinea(jugadoresenlinea);

                if (numeroJugadores == 2) {
                    for (Despachador e : jugadoresenlinea) {
                        e.start();
                    }
                    serverSocket.close();
                    System.out.println("NUMERO MAXIMO DE JUGADORES ALCANZADO");
                }
            }

    }
}
