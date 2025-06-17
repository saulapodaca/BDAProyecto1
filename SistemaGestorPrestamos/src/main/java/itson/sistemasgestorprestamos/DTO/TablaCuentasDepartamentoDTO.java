/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.DTO;

/**
 *
 * @author Camila Zubía
 */
public class TablaCuentasDepartamentoDTO {
    
    private int id;
    private String numeroCuenta;
    private String clabe;
    private String nombreBanco;
    private float saldo;
    private int id_departamento;

    public TablaCuentasDepartamentoDTO(int id, String numeroCuenta, String clabe, String nombreBanco, float saldo, int id_departamento) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.clabe = clabe;
        this.nombreBanco = nombreBanco;
        this.saldo = saldo;
        this.id_departamento = id_departamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
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

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }
    
    
}
