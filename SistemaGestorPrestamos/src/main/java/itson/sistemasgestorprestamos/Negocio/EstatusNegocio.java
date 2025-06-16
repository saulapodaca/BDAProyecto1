/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.TablaEstatusDTO;
import itson.sistemasgestorprestamos.dominios.EstatusDominio;
import itson.sistemasgestorprestamos.persistencia.IEstatusDAO;
import itson.sistemasgestorprestamos.persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Camila Zubía
 */
public class EstatusNegocio implements IEstatusNegocio{
    
    private IEstatusDAO EstatusDAO;

    public EstatusNegocio(IEstatusDAO EstatusDAO) {
        this.EstatusDAO = EstatusDAO;
    }

    @Override
    public EstatusDominio buscarPorId(int id) throws NegocioException {
        try {
            this.idInvalido(id);
            return this.EstatusDAO.buscarPorId(id);
        } catch (PersistenciaException ex) {
            Logger.getLogger(EstatusNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<TablaEstatusDTO> buscarTabla(FiltroDTO filtro) throws NegocioException {
        try {
            this.limiteValido(filtro);
            this.offsetValido(filtro);
            return this.EstatusDAO.buscarTabla(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(EstatusNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void idInvalido(int id) throws NegocioException {
        if (id <= 0) {
            throw new NegocioException("la id es invalida");
        }
    }
    
    private void limiteValido(FiltroDTO filtro) throws NegocioException {
        if (filtro.getLimit() < 0) {
            throw new NegocioException("el limite no es valido");
        }
    }

    private void offsetValido(FiltroDTO filtro) throws NegocioException {
        if (filtro.getOffset() < 0) {
            throw new NegocioException("el offset no es valido");
        }
    }

    @Override
    public int contarTotalEstatus(FiltroDTO filtro) throws NegocioException {
        try {
            this.limiteValido(filtro);
            this.offsetValido(filtro);
            return this.EstatusDAO.contarTotalEstatus(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(EstatusNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
