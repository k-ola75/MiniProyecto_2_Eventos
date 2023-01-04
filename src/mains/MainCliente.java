package mains;/*
  Autores:
    Juan Sebastian Oviedo Oviedo (2179238-2724)
    Kirk Olaya Villamarín (2178638-2724)
  E-mail:
    Juan.sebastian.oviedo@orreounivalle.edu.co
    kikr.olaya@correounivalle.edu.co
  Fecha: 2022-11-30
  Propósito: Académico
  Versión actual: 2.0
  Versión anterior: 1.0
*/


import controller.Controlador;
import gui.VentanaPrincipal;

public class MainCliente {
    public static void main(String[] args)
    {
        // Vista
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);

        // Controlador
        Controlador controlador = new Controlador(ventana);
        ventana.getLienzo().addKeyListener(controlador);

        controlador.conectar();
    }
}
