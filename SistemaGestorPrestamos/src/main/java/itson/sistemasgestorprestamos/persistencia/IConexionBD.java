/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author adell
 */
public interface IConexionBD {
    
        /**
         * declaracion del metodo crearcionexion  
         * @return devolvera una conexion a una base de datos
         * @throws SQLException 
         */
        Connection crearConexion() throws SQLException;

}
