/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.TablaEstatusDTO;
import itson.sistemasgestorprestamos.Negocio.EstatusNegocio;
import itson.sistemasgestorprestamos.Negocio.IEstatusNegocio;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.EstatusDominio;
import itson.sistemasgestorprestamos.persistencia.ConexionBD;
import itson.sistemasgestorprestamos.persistencia.EstatusDAO;
import itson.sistemasgestorprestamos.persistencia.IConexionBD;
import itson.sistemasgestorprestamos.persistencia.IEstatusDAO;
import java.util.List;

/**
 *
 * @author Camila Zubía
 */
public class EstatusFachada implements IEstatusFachada{
    
    private IEstatusNegocio estatusNegocio;

    public EstatusFachada() {
        IConexionBD conexion = new ConexionBD();
        IEstatusDAO estatusDAO = new EstatusDAO(conexion);
        this.estatusNegocio = new EstatusNegocio(estatusDAO);
    }

    @Override
    public EstatusDominio buscarPorId(int id) throws NegocioException {
        return this.estatusNegocio.buscarPorId(id);
    }

    @Override
    public List<TablaEstatusDTO> buscarTabla(FiltroDTO filtro) throws NegocioException {
        return this.estatusNegocio.buscarTabla(filtro);
    }

    @Override
    public int contarTotalEstatus(FiltroDTO filtro) throws NegocioException {
        return this.estatusNegocio.contarTotalEstatus(filtro);
    }
    
}
