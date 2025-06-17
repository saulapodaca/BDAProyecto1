/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.DTO;

/**
 *
 * @author Camila Zubía
 */
public class TablaCuentasEmpleadoDTO {
    
    private int id;
    private String clabe;
    private String activo;
    private String nombreBanco;
    private float saldo;
    private int id_empleado;

    public TablaCuentasEmpleadoDTO(int id, String clabe, String activo, String nombreBanco, float saldo, int id_empleado) {
        this.id = id;
        this.clabe = clabe;
        this.activo = activo;
        this.nombreBanco = nombreBanco;
        this.saldo = saldo;
        this.id_empleado = id_empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String numeroBanco) {
        this.nombreBanco = numeroBanco;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
    
    
}
