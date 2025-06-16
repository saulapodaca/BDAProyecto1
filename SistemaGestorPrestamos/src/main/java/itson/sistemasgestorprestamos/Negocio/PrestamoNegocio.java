/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.ReportePrestamoDTO;
import itson.sistemasgestorprestamos.DTO.SolicitudPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.TablaPrestamosDTO;
import itson.sistemasgestorprestamos.DTO.filtroPrestamosDTO;
import itson.sistemasgestorprestamos.dominios.Estatus;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;
import itson.sistemasgestorprestamos.persistencia.IPrestamoDAO;
import itson.sistemasgestorprestamos.persistencia.PersistenciaException;
import java.util.List;
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
            this.parametroNulo2(solicitud);
            return PrestamoDAO.guardarSolicitud(solicitud);
        } catch (PersistenciaException ex) {
            Logger.getLogger(PrestamoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public PrestamosDominio guardar(GuardarPrestamoDTO prestamo) throws NegocioException {
        try {
            this.parametroNulo(prestamo);
            return PrestamoDAO.guardar(prestamo);
        } catch (PersistenciaException ex) {
            Logger.getLogger(PrestamoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public PrestamosDominio cambiarEstatus(int id, Estatus estatus) throws NegocioException {
        try {
            this.idInvalido(id);
            return PrestamoDAO.cambiarEstatus(id, estatus);
        } catch (PersistenciaException ex) {
            Logger.getLogger(PrestamoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public PrestamosDominio buscarPorId(int idPrestamo) throws NegocioException {
        try{
            this.idInvalido(idPrestamo);
            return PrestamoDAO.buscarPorId(idPrestamo);
        } catch (PersistenciaException ex) {
            Logger.getLogger(PrestamoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<TablaPrestamosDTO> buscarTabla(FiltroDTO filtro) throws NegocioException {
        try {
            this.limiteValido(filtro);
            this.offsetValido(filtro);
            return PrestamoDAO.buscarTabla(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(PrestamoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<ReportePrestamoDTO> obtenerPrestamosFiltrados(filtroPrestamosDTO filtro) throws NegocioException{
        try {
            return PrestamoDAO.obtenerPrestamosFiltrados(filtro);
        }catch (PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
    }

    
    private void limiteValido(FiltroDTO filtro) throws NegocioException {
        if (filtro.getLimit() < 0) {
            throw new NegocioException("el limite no es valido");
        }
    }

    private void offsetValido(FiltroDTO filtro) throws NegocioException {
        if (filtro.getOffset() < 0) {
            throw new NegocioException("el offset no es valido");
        }
    }

    private void filtroValido(FiltroDTO filtro) throws NegocioException {
        if (filtro.getFiltro() == null || filtro.getFiltro().trim().isEmpty()) {
            throw new NegocioException("el nombre no existe");
        }
    }
    
    private void idInvalido(int id)throws NegocioException{
        if (id <= 0) {
            throw new NegocioException("la id es invalida");
        }
    }

    private void parametroNulo(GuardarPrestamoDTO prestamo) throws NegocioException {
        if (prestamo == null) {
            throw new NegocioException("el parametro es nulo");
        }
    }
    
    private void parametroNulo2(SolicitudPrestamoDTO solicitud) throws NegocioException {
        if (solicitud == null) {
            throw new NegocioException("el parametro es nulo");
        }
    }
    
    private void tablaNula(SolicitudPrestamoDTO solicitud) throws NegocioException {
        if (solicitud == null) {
            throw new NegocioException("el parametro es nulo");
        }
    }

    @Override
    public int contarTotalPrestamos(FiltroDTO filtro) throws NegocioException {
        try {
            return PrestamoDAO.contarTotalPrestamos(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(PrestamoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
