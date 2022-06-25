/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control_ingreso;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author spc
 */
public class Conexion {
    Connection con;
    public Connection conectar(){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/proyectobd","root","");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Sin conexion");
            }
        return con;
    }
    
}
