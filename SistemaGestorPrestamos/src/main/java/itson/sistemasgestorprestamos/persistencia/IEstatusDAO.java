/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.TablaEstatusDTO;
import itson.sistemasgestorprestamos.dominios.EstatusDominio;
import java.util.List;

/**
 * Interfaz para gestionar la persistencia de los estatus en el sistema. Define
 * los métodos de acceso a la información de estatus.
 *
 * @author Camila Zubía
 */
public interface IEstatusDAO {

    /**
     * Busca un estatus específico en la base de datos según su identificador
     * único.
     *
     * @param id Identificador del estatus.
     * @return Instancia de `EstatusDominio` si el estatus se encuentra en la
     * base de datos.
     * @throws PersistenciaException Si hay un error en la consulta o el estatus
     * no existe.
     */
    public EstatusDominio buscarPorId(int id) throws PersistenciaException;

    /**
     * Obtiene una lista de estatus según los criterios de filtro
     * proporcionados.
     *
     * @param filtro Objeto que contiene los criterios de búsqueda.
     * @return Lista de `TablaEstatusDTO` con los estatus filtrados.
     * @throws PersistenciaException Si ocurre un error en la consulta o no se
     * encuentran registros.
     */
    public List<TablaEstatusDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException;

    /**
     * Cuenta el número total de registros de estatus en la base de datos según
     * los filtros aplicados.
     *
     * @param filtro Objeto con los criterios de búsqueda.
     * @return Número total de registros encontrados.
     * @throws PersistenciaException Si ocurre un error en la consulta.
     */
    public int contarTotalEstatus(FiltroDTO filtro) throws PersistenciaException;

}
