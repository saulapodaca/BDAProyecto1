/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.dominios;

import java.time.LocalDateTime;

/**
 *
 * @author adell
 */
public class PrestamosDominio {
    private int id;
    private LocalDateTime fechaHora;
    private float monto;
    private int tipoPrestamo;
    private int cuentaDepartamento;
    private int cuentaEmpleado;

    public PrestamosDominio() {
    }

    public PrestamosDominio(int id, LocalDateTime fechaHora, float monto, int tipoPrestamo, int cuentaDepartamento, int cuentaEmpleado) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.tipoPrestamo = tipoPrestamo;
        this.cuentaDepartamento = cuentaDepartamento;
        this.cuentaEmpleado = cuentaEmpleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setTipoPrestamo(int tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public int getCuentaDepartamento() {
        return cuentaDepartamento;
    }

    public void setCuentaDepartamento(int cuentaDepartamento) {
        this.cuentaDepartamento = cuentaDepartamento;
    }

    public int getCuentaEmpleado() {
        return cuentaEmpleado;
    }

    public void setCuentaEmpleado(int cuentaEmpleado) {
        this.cuentaEmpleado = cuentaEmpleado;
    }
    
    
}
