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
public class TablaEstatusDTO {
    
    private int id;
    private Estatus nombre;
    private LocalDateTime fechaHora;
    private int idJefe;
    private int idPrestamo;

    public TablaEstatusDTO(int id, Estatus nombre, LocalDateTime fechaHora, int idJefe, int idPrestamo) {
        this.id = id;
        this.nombre = nombre;
        this.fechaHora = fechaHora;
        this.idJefe = idJefe;
        this.idPrestamo = idPrestamo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estatus getNombre() {
        return nombre;
    }

    public void setNombre(Estatus nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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
        return "TablaEstatusDTO{" + "id=" + id + ", nombre=" + nombre + ", fechaHora=" + fechaHora + ", idJefe=" + idJefe + ", idPrestamo=" + idPrestamo + '}';
    }
    
}
