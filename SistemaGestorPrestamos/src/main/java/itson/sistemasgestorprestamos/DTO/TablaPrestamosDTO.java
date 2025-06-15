/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.DTO;

import itson.sistemasgestorprestamos.dominios.Estatus;
import java.time.LocalDateTime;

/**
 *
 * @author Camila Zubía
 */
public class TablaPrestamosDTO {
    private int id;
    private LocalDateTime fechaHora;
    private float monto;
    private Estatus estatus;
    private int tipoPrestamo;
    private int cuentaDepartamento;
    private int cuentaEmpleado;

    public TablaPrestamosDTO(int id, LocalDateTime fechaHora, float monto, Estatus estatus, int tipoPrestamo, int cuentaDepartamento, int cuentaEmpleado) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.estatus = estatus;
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

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "TablaPrestamosDTO{" + "id=" + id + ", fechaHora=" + fechaHora + ", monto=" + monto + ", estatus=" + estatus + ", tipoPrestamo=" + tipoPrestamo + ", cuentaDepartamento=" + cuentaDepartamento + ", cuentaEmpleado=" + cuentaEmpleado + '}';
    }
}
