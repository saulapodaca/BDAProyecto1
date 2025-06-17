package itson.sistemagestorprestamos.main;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemagestorprestamos.fachada.IInicializacionFachada;
import itson.sistemagestorprestamos.fachada.InicializacionFachada;
import itson.sistemagestorprestamos.presentacion.InicioSesionFrm;
import itson.sistemagestorprestamos.presentacion.InsercionMasivaFrm;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import javax.swing.JOptionPane;


public class Principal {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                    IInicializacionFachada inicializacionFachada = new InicializacionFachada();
                if (!inicializacionFachada.existenDatosIniciales()) {
                    InsercionMasivaFrm insercion = new InsercionMasivaFrm();
                    insercion.setVisible(true);
                } else {
                    InicioSesionFrm login = new InicioSesionFrm();
                    login.setVisible(true);
                }

            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
