/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.SolicitudPrestamoDTO;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;

/**
 *
 * @author adell
 */
public interface IPrestamoNegocio {
        public PrestamosDominio guardarSolicitud(SolicitudPrestamoDTO solicitud) throws NegocioException;

}
