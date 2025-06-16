/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.SolicitudPrestamoDTO;
import itson.sistemasgestorprestamos.Negocio.IPrestamoNegocio;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.Negocio.PrestamoNegocio;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;
import itson.sistemasgestorprestamos.persistencia.ConexionBD;
import itson.sistemasgestorprestamos.persistencia.IConexionBD;
import itson.sistemasgestorprestamos.persistencia.IPrestamoDAO;
import itson.sistemasgestorprestamos.persistencia.PrestamoDAO;

/**
 *
 * @author adell
 */
public class prestamoFachada implements IPrestamoFachada {

    private IPrestamoNegocio prestamoNegocio;

    public prestamoFachada() {
        IConexionBD conexion = new ConexionBD();
        IPrestamoDAO prestamoDAO = new PrestamoDAO(conexion);
        this.prestamoNegocio = new PrestamoNegocio(prestamoDAO);
    }

    @Override
    public PrestamosDominio guardarSolicitud(SolicitudPrestamoDTO solicitud) throws NegocioException {
        return prestamoNegocio.guardarSolicitud(solicitud);
    }
}
