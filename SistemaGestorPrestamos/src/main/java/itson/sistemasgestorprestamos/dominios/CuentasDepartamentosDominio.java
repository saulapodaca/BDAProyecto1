/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.dominios;

/**
 *
 * @author adell
 */
public class CuentasDepartamentosDominio {
    private int id;
    private String numeroCuenta;
    private String Clabe;
    private String nombreBanco;
    private float saldo;
    private int idDepartamento;

    public CuentasDepartamentosDominio(int id, String numeroCuenta, String Clabe, String nombreBanco, float saldo, int idDepartamento) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.Clabe = Clabe;
        this.nombreBanco = nombreBanco;
        this.saldo = saldo;
        this.idDepartamento = idDepartamento;
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
        return Clabe;
    }

    public void setClabe(String Clabe) {
        this.Clabe = Clabe;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

   
}
