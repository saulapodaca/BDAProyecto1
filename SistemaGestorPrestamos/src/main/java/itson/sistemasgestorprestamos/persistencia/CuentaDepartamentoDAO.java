package itson.sistemasgestorprestamos.persistencia;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaDepartamentoDTO;
import itson.sistemasgestorprestamos.dominios.CuentasDepartamentosDominio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class CuentaDepartamentoDAO {

    private IConexionBD manejadorConexiones;
    
    public CuentaDepartamentoDAO (IConexionBD manejadorConexiones){
        this.manejadorConexiones = manejadorConexiones;
    }
    
    public CuentasDepartamentosDominio registrarCuenta(RegistrarCuentaDepartamentoDTO cuentaDepartamento) throws PersistenciaException{
        try{
            Connection conexion = this.manejadorConexiones.crearConexion();
            String codigoSQL = """
                               INSERT INTO CUENTAS_DEPARTAMENTOS
                               (clabe, banco, numeroCuenta, saldo, idDepartamento)
                               VALUES (?,?,?,?,?);
                               """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL, Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, cuentaDepartamento.getClabe());
            comando.setString(2, cuentaDepartamento.getBanco());
            comando.setString(3, cuentaDepartamento.getNumeroCuenta());
            comando.setFloat(4, cuentaDepartamento.getSaldo());
            comando.setInt(5, cuentaDepartamento.getIdDepartamento());
            
            int filasAfectadas = comando.executeUpdate();
            if (filasAfectadas == 0)
                throw new PersistenciaException("Error al registrar la cuenta del empleado");

            ResultSet resultado = comando.getGeneratedKeys();
            int id = resultado.getInt(1);
            CuentasDepartamentosDominio cuenta = buscarCuentaPorId(id);
            resultado.close();
            comando.close();
            conexion.close();

            return cuenta;
        } catch (SQLException e){
            throw new PersistenciaException("La cuenta del departamento no se pudo ingresar: " + e.getMessage());
        }
    }
    
    public List<CuentasDepartamentosDominio> buscarCuentasDepartamentosPorId(FiltroDTO filtro) throws PersistenciaException{
        List<CuentasDepartamentosDominio> listaCuentas = new LinkedList<>();
        try{
            Connection conexion = manejadorConexiones.crearConexion();
            String codigoSQL = """
                               SELECT 
                               idCuentaDepartamento, 
                               clabe, 
                               estatus, 
                               banco,
                               saldo,
                               numeroCuenta,
                               idDepartamento
                               FROM CUENTAS_EMPLEADOS
                               WHERE idDepartamento = ?
                               LIMIT ?
                               OFFSET ?;
                               """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setInt(1,  Integer.parseInt(filtro.getFiltro()));
            comando.setInt(2, filtro.getLimit());
            comando.setInt(3, filtro.getOffset());
            
            
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()){
                CuentasDepartamentosDominio cuenta = convertirCuentaDominio(resultado);
                listaCuentas.add(cuenta);
            }
            resultado.close();
            comando.close();
            conexion.close();

            if (listaCuentas == null)
                throw new PersistenciaException("No se encontró la cuenta del empleado: " + filtro.getFiltro());
   
            return listaCuentas;
        } catch (SQLException e){
            throw new PersistenciaException("Ocurrió un error al buscar la cuenta : " + e.getMessage());
        }
    }
    
    public void eliminarCuentaPorId (int id) throws PersistenciaException{
        try {
            Connection conexion = manejadorConexiones.crearConexion();
            String codigoSQL = """
                               UPDATE CUENTAS_DEPARTAMENTOS
                               SET ESTATUS = 'INACTIVA'
                               WHERE idCuentaDepartamento = ?;
                               """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setInt(1, id);
            int filasAfectadas = comando.executeUpdate();
            if (filasAfectadas == 0) 
                throw new PersistenciaException("No se eliminó la cuenta.");
            conexion.close();
        } catch (SQLException e) {
            throw new PersistenciaException("No se eliminó la cuenta.: " + e.getMessage());
        }
    }
    
    private CuentasDepartamentosDominio buscarCuentaPorId(int id) throws PersistenciaException{
        try{
            Connection conexion = manejadorConexiones.crearConexion();
            String codigoSQL = """
                               SELECT
                               idCuentaDepartamento,
                               clabe,
                               estatus,
                               banco,
                               numeroCuenta,
                               saldo,
                               idDepartamento
                               FROM CUENTAS_DEPARTAMENTOS
                               WHERE idCuentaDepartamento = ?;
                               """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setInt(1, id);
            ResultSet resultado =  comando.executeQuery();
            CuentasDepartamentosDominio cuenta = convertirCuentaDominio(resultado);
            resultado.close();
            comando.close();
            conexion.close();
            if (cuenta == null)
                throw new PersistenciaException("No se encontró la cuenta: " + id);
   
            return cuenta;
        } catch (SQLException e) {
            throw new PersistenciaException("Ocurrió un error al buscar la cuenta : " + e.getMessage());
        }
    }
    
    private CuentasDepartamentosDominio convertirCuentaDominio(ResultSet resultado) throws SQLException{
        int id = resultado.getInt("idCuentaDepartamento");
        String clabe = resultado.getString("clabe");
        String estatus = resultado.getString("estatus");
        String banco = resultado.getString("banco");
        String numeroCuenta = resultado.getString("numeroCuenta");
        float saldo = resultado.getFloat("saldo");
        int idDepartamento = resultado.getInt("idEmpelado");
        return new CuentasDepartamentosDominio(id, numeroCuenta, clabe, banco, saldo, estatus, idDepartamento);
    }
}
    
