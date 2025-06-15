package itson.sistemasgestorprestamos.DTO;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

public class RegistrarCuentaDepartamentoDTO {
    private String clabe;
    private String banco;
    private String numeroCuenta;
    private float saldo;
    private int idDepartamento;

    public RegistrarCuentaDepartamentoDTO(String clabe, String banco, String numeroCuenta, float saldo, int idDepartamento) {
        this.clabe = clabe;
        this.banco = banco;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.idDepartamento = idDepartamento;
    }

    public String getClabe() {
        return clabe;
    }

    public String getBanco() {
        return banco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }
    
}
