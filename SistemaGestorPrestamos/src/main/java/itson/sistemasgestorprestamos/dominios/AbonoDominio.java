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
public class AbonoDominio {
    private int id;
    private LocalDateTime fechaHora;
    private float monto;
    private int idJefe;
    private int idPrestamo;

    public AbonoDominio() {
    }

    public AbonoDominio(int id, LocalDateTime fechaHora, float monto, int idJefe, int idPrestamo) {
        this.id = id;
        this.fechaHora = fechaHora;
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
    
    
    
}
