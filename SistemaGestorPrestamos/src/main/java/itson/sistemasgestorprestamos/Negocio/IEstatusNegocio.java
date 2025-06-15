/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.TablaEstatusDTO;
import itson.sistemasgestorprestamos.dominios.EstatusDominio;
import java.util.List;

/**
 *
 * @author Camila Zubía
 */
public interface IEstatusNegocio {
    
    public EstatusDominio buscarPorId(int id) throws NegocioException;

    public List<TablaEstatusDTO> buscarTabla(FiltroDTO filtro) throws NegocioException;
    
}
