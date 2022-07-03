/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.util.ArrayList;
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
        CriptPass cripto = new CriptPass();
        String pass;
        String sql = "SELECT cedula,clave FROM usuarios WHERE usuario = '" + usuario + "' AND root='" + root + "';";
        ArrayList<Object> datos = new ArrayList<>();
        datos = manejador.resultado(sql);
        if (datos.size() < 1) {
            return valido;
        } else {
            pass = cripto.Desencriptar(datos.get(1).toString());
            if (pass.equals(clave)) {
                valido[0] = true;
                valido[1] = true;
                return valido;
            } else {
                valido[0] = true;
                valido[1] = false;
                return valido;
            }
        }
    }
}
