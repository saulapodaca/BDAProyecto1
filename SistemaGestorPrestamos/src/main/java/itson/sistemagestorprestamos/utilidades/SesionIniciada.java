/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemagestorprestamos.utilidades;

import itson.sistemasgestorprestamos.DTO.SesionEmpleadoDTO;

/**
 *
 * @author Camila Zubía
 */
public class SesionIniciada {
    
    private static SesionIniciada instancia;
    private SesionEmpleadoDTO empleado;

    public SesionIniciada() {
    }

    public static SesionIniciada getInstancia() {
        if (instancia == null) {
            instancia = new SesionIniciada();
        }
        return instancia;
    }
    
    public void iniciarSesion(SesionEmpleadoDTO empleado){
        this.empleado = empleado;
    }

    public SesionEmpleadoDTO getEmpleado() {
        return empleado;
    }
    
    public void cerrarSesion(){
        this.empleado = null;
        instancia = null;
    }
}
