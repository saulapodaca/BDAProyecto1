/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.SolicitudPrestamoDTO;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;
import itson.sistemasgestorprestamos.persistencia.IEmpleadoDAO;
import itson.sistemasgestorprestamos.persistencia.IPrestamoDAO;
import itson.sistemasgestorprestamos.persistencia.PersistenciaException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adell
 */
public class PrestamoNegocio implements IPrestamoNegocio {

    private IPrestamoDAO PrestamoDAO;

    public PrestamoNegocio(IPrestamoDAO PrestamoDAO) {
        this.PrestamoDAO = PrestamoDAO;
    }

    @Override
    public PrestamosDominio guardarSolicitud(SolicitudPrestamoDTO solicitud) throws NegocioException {
        try {
            return PrestamoDAO.guardarSolicitud(solicitud);
        } catch (PersistenciaException ex) {
            Logger.getLogger(PrestamoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
