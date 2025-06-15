/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarAbonoDTO;
import itson.sistemasgestorprestamos.DTO.TablaAbonosDTO;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.AbonoDominio;
import java.util.List;

/**
 *
 * @author Camila Zubía
 */
public interface IAbonoFachada {
    
    public AbonoDominio registrarAbono(RegistrarAbonoDTO abono) throws NegocioException;

    public List<TablaAbonosDTO> buscarTabla(FiltroDTO filtro) throws NegocioException;

    public AbonoDominio buscarPorID(int id) throws NegocioException;
    
}
