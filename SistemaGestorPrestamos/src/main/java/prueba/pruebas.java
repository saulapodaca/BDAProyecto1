/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarEmpleadoDTO;
import itson.sistemasgestorprestamos.persistencia.ConexionBD;
import itson.sistemasgestorprestamos.persistencia.EmpleadoDAO;
import itson.sistemasgestorprestamos.persistencia.IConexionBD;
import itson.sistemasgestorprestamos.persistencia.PersistenciaException;

/**
 *
 * @author adell
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException {
        // TODO code application logic here
        IConexionBD conexion = new ConexionBD();
        
        EmpleadoDAO empleado = new EmpleadoDAO(conexion);
        
        GuardarEmpleadoDTO empleado1 = new GuardarEmpleadoDTO("adel","mendez","lizo",true,"252770","modelito1",1);
        FiltroDTO filtro = new FiltroDTO (2,0,"");
        //empleado.guardar(empleado1);
     
        System.out.println(empleado.buscarTabla(filtro));
    }
    
}
