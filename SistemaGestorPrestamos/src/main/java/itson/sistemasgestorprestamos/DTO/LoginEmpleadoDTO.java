/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.DTO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Camila Zubía
 */
public class LoginEmpleadoDTO {
    private String usuario;
    private String contraseña;

    public LoginEmpleadoDTO(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = UtilidadesEncriptacion.encriptarSHA256(contraseña);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = UtilidadesEncriptacion.encriptarSHA256(contraseña);
    }

    @Override
    public String toString() {
        return "loginEmpleadoDTO{" + "usuario=" + usuario + ", contrase\u00f1a=" + contraseña + '}';
    }
    
    public class UtilidadesEncriptacion {

        public static String encriptarSHA256(String contra) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = md.digest(contra.getBytes(StandardCharsets.UTF_8));

                StringBuilder sb = new StringBuilder();
                for (byte b : hashBytes) {
                    sb.append(String.format("%02x", b));
                }

                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error al encriptar con SHA-256", e);
            }
        }
    }
}
