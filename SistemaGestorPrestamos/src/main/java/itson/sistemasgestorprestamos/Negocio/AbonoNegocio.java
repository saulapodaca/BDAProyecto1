/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarAbonoDTO;
import itson.sistemasgestorprestamos.DTO.TablaAbonosDTO;
import itson.sistemasgestorprestamos.dominios.AbonoDominio;
import itson.sistemasgestorprestamos.persistencia.IAbonoDAO;
import itson.sistemasgestorprestamos.persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Camila Zubía
 */
public class AbonoNegocio implements IAbonoNegocio{
    
    private IAbonoDAO AbonoDAO;

    public AbonoNegocio(IAbonoDAO AbonoDAO) {
        this.AbonoDAO = AbonoDAO;
    }

    @Override
    public AbonoDominio registrarAbono(RegistrarAbonoDTO abono) throws NegocioException {
        try {
            this.parametroNulo(abono);
            this.datoFaltante(abono);
            this.montoInvalido(abono);
            this.idInvalido(abono.getIdJefe());
            this.idInvalido(abono.getIdPrestamo());
            return AbonoDAO.registrarAbono(abono);
        } catch (PersistenciaException ex) {
            Logger.getLogger(AbonoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<TablaAbonosDTO> buscarTabla(FiltroDTO filtro) throws NegocioException {
        try {
            this.limiteValido(filtro);
            this.offsetValido(filtro);
            this.filtroValido(filtro);
            return AbonoDAO.buscarTabla(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(AbonoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public AbonoDominio buscarPorID(int id) throws NegocioException {
        try {
            this.idInvalido(id);
            return AbonoDAO.buscarPorID(id);
        } catch (PersistenciaException ex) {
            Logger.getLogger(AbonoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //validaciones
    
    private void parametroNulo(RegistrarAbonoDTO abono) throws NegocioException{
        if (abono == null) {
            throw new NegocioException("el parametro es nulo");
        }
    }
    
    private void datoFaltante(RegistrarAbonoDTO abono) throws NegocioException{
        if (abono.getFechaHora() == null) {
            throw new NegocioException("la fecha es nula");
        }
    }
    
    private void idInvalido(int id)throws NegocioException{
        if (id <= 0) {
            throw new NegocioException("la id es invalida");
        }
    }
    
    private void montoInvalido(RegistrarAbonoDTO abono)throws NegocioException{
        if (abono.getMonto() <= 0) {
            throw new NegocioException("el valor del monto es invalido");
        }
    }
    
    //falta validar que el monto del abono no sea mayor al prestamo
    
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

    private void filtroValido(FiltroDTO filtro) throws NegocioException {
        if (filtro.getFiltro() == null || filtro.getFiltro().trim().isEmpty()) {
            throw new NegocioException("el nombre no existe");
        }
    }
}
