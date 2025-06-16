/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.ReportePrestamoDTO;
import itson.sistemasgestorprestamos.DTO.SolicitudPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.TablaPrestamosDTO;
import itson.sistemasgestorprestamos.DTO.filtroPrestamosDTO;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.Estatus;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;
import java.util.List;

/**
 *
 * @author adell
 */
public interface IPrestamoFachada {

    public PrestamosDominio guardarSolicitud(SolicitudPrestamoDTO solicitud) throws NegocioException;

    public PrestamosDominio guardar(GuardarPrestamoDTO prestamo) throws NegocioException;

    public PrestamosDominio cambiarEstatus(int id, Estatus estatus) throws NegocioException;

    public PrestamosDominio buscarPorId(int idPrestamo) throws NegocioException;

    public List<TablaPrestamosDTO> buscarTabla(FiltroDTO filtro) throws NegocioException;
   
    public List<ReportePrestamoDTO> obtenerPrestamosFiltrados(filtroPrestamosDTO filtro) throws NegocioException;

    public int contarTotalPrestamos(FiltroDTO filtro) throws NegocioException;
    
    public List<TablaPrestamosDTO> buscarTablaAbonar(FiltroDTO filtro) throws NegocioException;

}
