/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarAbonoDTO;
import itson.sistemasgestorprestamos.DTO.SolicitudPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.TablaPrestamosDTO;
import itson.sistemasgestorprestamos.dominios.Estatus;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;
import java.util.List;

/**
 *
 * @author Camila Zubía
 */
public interface IPrestamoDAO {
    
    
    public PrestamosDominio guardarSolicitud(SolicitudPrestamoDTO solicitud) throws PersistenciaException;
    public PrestamosDominio guardar(GuardarPrestamoDTO prestamo) throws PersistenciaException;
    public PrestamosDominio cambiarEstatus(int id, Estatus estatus) throws PersistenciaException;
    public PrestamosDominio buscarPorId(int idPrestamo) throws PersistenciaException;
    public List<TablaPrestamosDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException;
    
}
