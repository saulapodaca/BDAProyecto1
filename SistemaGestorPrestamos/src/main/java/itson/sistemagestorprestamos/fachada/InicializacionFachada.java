package itson.sistemagestorprestamos.fachada;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemasgestorprestamos.Negocio.IInicializacionNegocio;
import itson.sistemasgestorprestamos.Negocio.InicializacioNegocio;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.persistencia.ConexionBD;
import itson.sistemasgestorprestamos.persistencia.IConexionBD;
import itson.sistemasgestorprestamos.persistencia.IInicializacionDAO;
import itson.sistemasgestorprestamos.persistencia.InicializacionDAO;


public class InicializacionFachada implements IInicializacionFachada{

    private IInicializacionNegocio inicializacionNegocio;
    
    public InicializacionFachada (){
        IConexionBD conexion = new ConexionBD();
        IInicializacionDAO cuentaEmpleadoDAO= new  InicializacionDAO(conexion);
        this.inicializacionNegocio = new InicializacioNegocio(cuentaEmpleadoDAO);
    }
    
    @Override
    public void insertarDatosMasivos() throws NegocioException{
        inicializacionNegocio.insertarDatosMasivos();
    }
    
    @Override
    public boolean existenDatosIniciales() throws NegocioException{
        return inicializacionNegocio.existenDatosIniciales();
    }

}
