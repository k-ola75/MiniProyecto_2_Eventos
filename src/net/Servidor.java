package net;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    public static void main(String[] args) {
        int portNumber = 1234;
        ArrayList<Despachador> jugadoresenlinea = new ArrayList<>();
        int numeroJugadores = 0;

        try {
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
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
