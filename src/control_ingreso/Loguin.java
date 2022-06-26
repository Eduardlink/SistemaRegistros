/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control_ingreso;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import conexionBD.Conexion;
import java.sql.Connection;

/**
 *
 * @author Pc
 */
public class Loguin {

    private Conexion cc = new Conexion();
    private Connection con = cc.getConexion();

    public void Validar(String usr, String pass) {

        String sql = "Select * from docentes where usuario = '" + usr + "' and clave='" + pass + "' ";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println(rs);
                JOptionPane.showMessageDialog(null, "INGRESO EXITOSO");
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR!!!");
        }
    }

    public boolean[] verificarClave(String usr, String pass) {
        boolean[] datosCorrectos = {false,false};
        String sql = "SELECT usuario,clave FROM Docentes WHERE usuario='" + usr + "';";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if(rs.getString("usuario").equals(usr)){
                    datosCorrectos[0]=true;
                    System.out.println(datosCorrectos[0]);
                }
            }
            String claveDS = new CriptPass().Desencriptar(rs.getString("clave"));
            if(claveDS.equals(pass)){
                datosCorrectos[1]=false;
                System.out.println(datosCorrectos[0]);
            }
        } catch (Exception ex) {
            System.out.println("Eror_Verif._pass_" + ex);
        }
        return datosCorrectos;
    }
}
