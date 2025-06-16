/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.LoginEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.SesionEmpleadoDTO;
import itson.sistemasgestorprestamos.persistencia.ConexionBD;
import itson.sistemasgestorprestamos.persistencia.EmpleadoDAO;
import itson.sistemasgestorprestamos.persistencia.IConexionBD;
import itson.sistemasgestorprestamos.persistencia.PersistenciaException;

/**
 *
 * @author Camila Zubía
 */
public class Login {

    private final EmpleadoDAO empleadoDAO;

    public Login() {
        IConexionBD conexionBD = new ConexionBD();
        this.empleadoDAO = new EmpleadoDAO(conexionBD);
    }

    public SesionEmpleadoDTO login(LoginEmpleadoDTO empleado) throws PersistenciaException {
        validarUsuario(empleado);
        validarContraseña(empleado);
        return empleadoDAO.buscarPorUsuarioYContraseña(empleado);
    }

    private void validarUsuario(LoginEmpleadoDTO empleado) throws PersistenciaException {
        String usuario = empleado.getUsuario();
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new PersistenciaException("El campo usuario está vacío.");
        }
    }

    private void validarContraseña(LoginEmpleadoDTO empleado) throws PersistenciaException {
        String contraseña = empleado.getContraseña();
        if (contraseña == null || contraseña.trim().isEmpty()) {
            throw new PersistenciaException("El campo contraseña está vacío.");
        }
    }
}
