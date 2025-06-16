/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.ReportePrestamoDTO;
import itson.sistemasgestorprestamos.DTO.SolicitudPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.TablaPrestamosDTO;
import itson.sistemasgestorprestamos.DTO.filtroPrestamosDTO;
import itson.sistemasgestorprestamos.Negocio.IPrestamoNegocio;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.Negocio.PrestamoNegocio;
import itson.sistemasgestorprestamos.dominios.Estatus;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;
import itson.sistemasgestorprestamos.persistencia.ConexionBD;
import itson.sistemasgestorprestamos.persistencia.IConexionBD;
import itson.sistemasgestorprestamos.persistencia.IPrestamoDAO;
import itson.sistemasgestorprestamos.persistencia.PrestamoDAO;
import java.util.List;

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
        return this.prestamoNegocio.guardarSolicitud(solicitud);
    }

    @Override
    public PrestamosDominio guardar(GuardarPrestamoDTO prestamo) throws NegocioException {
        return this.prestamoNegocio.guardar(prestamo);
    }

    @Override
    public PrestamosDominio cambiarEstatus(int id, Estatus estatus) throws NegocioException {
        return this.prestamoNegocio.cambiarEstatus(id, estatus);
    }

    @Override
    public PrestamosDominio buscarPorId(int idPrestamo) throws NegocioException {
        return this.prestamoNegocio.buscarPorId(idPrestamo);
    }

    @Override
    public List<TablaPrestamosDTO> buscarTabla(FiltroDTO filtro) throws NegocioException {
        return this.prestamoNegocio.buscarTabla(filtro);
    }

    @Override
    public List<ReportePrestamoDTO> obtenerPrestamosFiltrados(filtroPrestamosDTO filtro) throws NegocioException{
        return this.prestamoNegocio.obtenerPrestamosFiltrados(filtro);
    }


    @Override
    public int contarTotalPrestamos(FiltroDTO filtro) throws NegocioException {
        return this.prestamoNegocio.contarTotalPrestamos(filtro);
    }

}
