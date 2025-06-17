package itson.sistemasgestorprestamos.persistencia;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class InicializacionDAO implements IInicializacionDAO {

    private IConexionBD conexionBD;
    
    public InicializacionDAO(IConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }
    
    @Override
    public void insertarDatosMasivos() throws PersistenciaException{
        String codigoSQL = "{CALL insercion_masiva()}";
        try{
            Connection connection = conexionBD.crearConexion();
            CallableStatement comando = connection.prepareCall(codigoSQL);
            comando.execute();
            comando.close();
            connection.close();
        } catch (SQLException e){
            throw new PersistenciaException("Hubo un error al insertar los datos.");
        }
    }
    
    @Override
    public boolean existenDatosIniciales() throws PersistenciaException{
        String codigoSQL = "SELECT count(id) FROM departamentos";
        try {
            Connection connection = conexionBD.crearConexion();
            PreparedStatement comando = connection.prepareStatement(codigoSQL);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next())
                return resultado.getInt(1) > 0;
            return false;
        } catch (SQLException e){
            throw new PersistenciaException("Error al validar datos iniciales.");
        }
    }
}
