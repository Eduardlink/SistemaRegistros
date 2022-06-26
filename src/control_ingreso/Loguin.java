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

    public void Validar(String usr, String pass) {
        Conexion cc = new Conexion();
        Connection con = cc.getConexion();
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
}
