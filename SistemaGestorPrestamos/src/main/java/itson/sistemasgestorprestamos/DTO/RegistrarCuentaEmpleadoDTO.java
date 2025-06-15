package itson.sistemasgestorprestamos.DTO;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020
public class RegistrarCuentaEmpleadoDTO {

    private String clabe;
    private String banco;
    private float saldo;
    private int idEmpleado;

    public RegistrarCuentaEmpleadoDTO(int idCuenta, String clabe, String banco, float saldo, int idEmpleado) {
        this.clabe = clabe;
        this.banco = banco;
        this.saldo = saldo;
        this.idEmpleado = idEmpleado;
    }

    public String getClabe() {
        return clabe;
    }

    public String getBanco() {
        return banco;
    }
    
    public float getSaldo() {
        return saldo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

}
