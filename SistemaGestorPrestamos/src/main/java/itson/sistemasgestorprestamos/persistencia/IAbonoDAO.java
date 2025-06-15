/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarAbonoDTO;
import itson.sistemasgestorprestamos.DTO.TablaAbonosDTO;
import itson.sistemasgestorprestamos.dominios.AbonoDominio;
import java.util.List;

/**
 *
 * @author Camila Zubía
 */
public interface IAbonoDAO {
    
    public AbonoDominio registrarAbono(RegistrarAbonoDTO abono) throws PersistenciaException;
    public List<TablaAbonosDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException;
    public AbonoDominio buscarPorID(int id) throws PersistenciaException;
    
}
