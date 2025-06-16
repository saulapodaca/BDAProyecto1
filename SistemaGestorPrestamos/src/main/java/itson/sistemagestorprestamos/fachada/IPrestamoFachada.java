/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.SolicitudPrestamoDTO;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;

/**
 *
 * @author adell
 */
public interface IPrestamoFachada {

    public PrestamosDominio guardarSolicitud(SolicitudPrestamoDTO solicitud) throws NegocioException;

}
