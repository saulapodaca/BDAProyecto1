package itson.sistemasgestorprestamos.Negocio;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemasgestorprestamos.persistencia.IInicializacionDAO;
import itson.sistemasgestorprestamos.persistencia.PersistenciaException;


public class InicializacioNegocio implements IInicializacionNegocio{
    
    private IInicializacionDAO inicializacionDAO;
    
    public InicializacioNegocio (IInicializacionDAO inicializacionDAO){
        this.inicializacionDAO = inicializacionDAO;
    }
    
    @Override
    public void insertarDatosMasivos() throws NegocioException{
        try{
            validarExistencia();
            inicializacionDAO.insertarDatosMasivos();
        } catch (PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    @Override
    public boolean existenDatosIniciales() throws NegocioException{
        try{
            return inicializacionDAO.existenDatosIniciales();
        } catch (PersistenciaException e){
            throw  new NegocioException(e.getMessage());
        }
    }
    
    private void validarExistencia() throws NegocioException{
        try{
            if (existenDatosIniciales())
                throw new NegocioException("Ya existen datos en la BD.");
        } catch (NegocioException e){
            throw new NegocioException(e.getMessage());
        }
    }
}
