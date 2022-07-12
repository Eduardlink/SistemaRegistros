/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import modelos.DataManager;

/**
 *
 * @author Pc
 */
public class LoguinController {

    //[false,false]-> [0]usuario
    public boolean[] verificar(String usuario, String clave, String root) {
        boolean[] valido = {false, false};
        DataManager manejador = new DataManager();
        String pass;
        String sql = "SELECT cedula,clave FROM usuarios WHERE usuario = '" + usuario + "' AND root='" + root + "';";
        ArrayList<Object> datos = new ArrayList<>();
        datos = manejador.resultado(sql);
        if (datos.size() < 1) {
            return valido;
        } else {
            try {
                pass = new EncriptadorAES().desencriptar(datos.get(1).toString(), "SisTech");
                //pass = new SecretPass().Desencriptar(datos.get(1).toString());
                if (pass.equals(clave)) {
                    valido[0] = true;
                    valido[1] = true;
                    return valido;
                } else {
                    valido[0] = true;
                    valido[1] = false;
                    return valido;
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(LoguinController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoguinController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (InvalidKeyException ex) {
                Logger.getLogger(LoguinController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(LoguinController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(LoguinController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (BadPaddingException ex) {
                Logger.getLogger(LoguinController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
    }
}
