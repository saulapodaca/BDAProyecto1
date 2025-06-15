/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.TablaEstatusDTO;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.EstatusDominio;
import java.util.List;

/**
 *
 * @author Camila Zubía
 */
public interface IEstatusFachada {
    
    public EstatusDominio buscarPorId(int id) throws NegocioException;

    public List<TablaEstatusDTO> buscarTabla(FiltroDTO filtro) throws NegocioException;
    
}
