/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control_ingreso;

import conexionBD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Pc
 */
public class Registro {

    //Este metodo solo ingresa los datos a la BD, falta los metodos de control
    public boolean almacenarDatos(String[] datos) {
        try {
            CriptPass cript = new CriptPass();
            Conexion cc = new Conexion();
            Connection con = cc.getConexion();
            String sql = "INSERT INTO [dbo].[Docentes] ([usuario] ,[clave],[nombre] ,[apellido],[cedula]) VALUES (?,?,?,?,?)";
            PreparedStatement psd = con.prepareStatement(sql);
            psd.setString(1, datos[0]);
            psd.setString(2, cript.Encriptar(datos[1]));
            psd.setString(3, datos[2]);
            psd.setString(4, datos[3]);
            psd.setString(5, datos[4]);
            int n = psd.executeUpdate();
            if (n > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return false;
    }
}
