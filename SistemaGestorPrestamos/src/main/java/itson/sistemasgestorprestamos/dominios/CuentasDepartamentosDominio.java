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
    private String estatus;
    private float saldo;
    private int idDepartamento;

    public CuentasDepartamentosDominio() {
    }

    public CuentasDepartamentosDominio(int id, String numeroCuenta, String Clabe, String nombreBanco, float saldo, String estatus, int idDepartamento) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.Clabe = Clabe;
        this.nombreBanco = nombreBanco;
        this.saldo = saldo;
        this.estatus = estatus;
        this.idDepartamento = idDepartamento;
    }

    public int getId() {
        return id;
    }
    
    public String getEstatus(){
        return estatus;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getClabe() {
        return Clabe;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public float getSaldo() {
        return saldo;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }    
    
}
