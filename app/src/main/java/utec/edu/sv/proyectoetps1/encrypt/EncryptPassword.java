package utec.edu.sv.proyectoetps1.encrypt;
import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {
    public static String encryptPassword(String password) {
        try {
            // Obtener instancia del algoritmo de hashing
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Obtener el hash de la contraseña
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Codificar el hash en Base64 para almacenarlo
            return Base64.encodeToString(hashedBytes, Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}



