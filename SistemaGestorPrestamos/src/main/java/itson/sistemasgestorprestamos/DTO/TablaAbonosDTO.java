/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Camila Zubía
 */
public class TablaAbonosDTO {
    private int id;
    private LocalDateTime fecha;
    private double monto;
    private int idJefe;
    private int idPrestamo;

    public TablaAbonosDTO(int id, LocalDateTime fecha, double monto, int idJefe, int idPrestamo) {
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.idJefe = idJefe;
        this.idPrestamo = idPrestamo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(int idJefe) {
        this.idJefe = idJefe;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    @Override
    public String toString() {
        return "TablaAbonosDTO{" + "id=" + id + ", fecha=" + fecha + ", monto=" + monto + ", idJefe=" + idJefe + ", idPrestamo=" + idPrestamo + '}';
    }
    
    
}
