package itson.sistemasgestorprestamos.DTO;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import java.time.LocalDateTime;


public class ReportePrestamoDTO {

    private String tipoPrestamo;
    private String departamento;
    private LocalDateTime fechaHora;
    private String nombreSolicitante;
    private float montoSolicitado;

    public ReportePrestamoDTO(String tipoPrestamo, String departamento, LocalDateTime fechaHora, String nombreSolicitante, float montoSolicitado) {
        this.tipoPrestamo = tipoPrestamo;
        this.departamento = departamento;
        this.fechaHora = fechaHora;
        this.nombreSolicitante = nombreSolicitante;
        this.montoSolicitado = montoSolicitado;
    }

    public ReportePrestamoDTO() {
    }

    public String getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setTipoPrestamo(String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public float getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(float montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }
    
    
}
