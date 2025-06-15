/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.GuardarPrestamoDTO;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;

/**
 *
 * @author Camila Zubía
 */
public interface IPrestamoDAO {
    
    public PrestamosDominio guardar(GuardarPrestamoDTO prestamo) throws PersistenciaException;
}
