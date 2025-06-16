/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.DTO;

import java.time.LocalDateTime;

/**
 *
 * @author adell
 */
public class SolicitudPrestamoDTO {

    private LocalDateTime hora;
    private double monto;
    private String estatus;
    private int idTipo;
    private int idCuentaEmpleado;

    public SolicitudPrestamoDTO(LocalDateTime hora, double monto, String estatus, int idTipo, int idCuentaEmpleado) {
        this.hora = hora;
        this.monto = monto;
        this.estatus = estatus;
        this.idTipo = idTipo;
        this.idCuentaEmpleado = idCuentaEmpleado;
    }
    
    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdCuentaEmpleado() {
        return idCuentaEmpleado;
    }

    public void setIdCuentaEmpleado(int idCuentaEmpleado) {
        this.idCuentaEmpleado = idCuentaEmpleado;
    }

  

    
    
    
}
