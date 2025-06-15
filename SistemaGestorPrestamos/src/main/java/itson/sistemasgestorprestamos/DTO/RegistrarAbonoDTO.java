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
public class RegistrarAbonoDTO {
    private LocalDateTime fechaHora;
    private float monto;
    private int idJefe;
    private int idPrestamo;

    public RegistrarAbonoDTO(float monto, int idJefe, int idPrestamo) {
        this.fechaHora = LocalDateTime.now();
        this.monto = monto;
        this.idJefe = idJefe;
        this.idPrestamo = idPrestamo;
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

    public void setMonto(float monto) {
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
