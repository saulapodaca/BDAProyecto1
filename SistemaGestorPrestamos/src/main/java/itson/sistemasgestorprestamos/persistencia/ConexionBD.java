/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author adell
 */
public class ConexionBD implements IConexionBD{
    private final String SERVER = "127.0.0.1";
    private final String BASE_DATOS = "finanzasglobales";
    private final String CADENA_CONEXION = "jdbc:mysql://" + SERVER + "/" + BASE_DATOS;
    private final String USUARIO = "root";
    private final String CONTRASEÑA = "moxxito1";
    
    
    /**
     * metodo que crea la conexion a la base de datos de finanzasglobales
     * @return devuelve la conexion especifca
     * @throws SQLException  excepciones de tipo sql
     */
    @Override
    public Connection crearConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);
        return conexion;
    }
}
