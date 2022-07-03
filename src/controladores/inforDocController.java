/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelos.DataManager;

/**
 *
 * @author Pc
 */
public class inforDocController {

    public String[] mostrarDatos(String cedula) {
        DataManager manejador = new DataManager();
        String sql = "SELECT usuario,nombre,apellido,cedula FROM usuarios WHERE cedula ='" + cedula + "';";
        ArrayList<Object> datos = new ArrayList<>();
        datos = manejador.resultado(sql);
        String[] info = {
            datos.get(0).toString(),
            datos.get(1).toString(),
            datos.get(2).toString(),
            datos.get(3).toString()};
        return info;
    }

    public DefaultTableModel cargarTabla(String cedula) {
        try {
            int id = this.obtenerID(cedula);
            DataManager manejador = new DataManager();
            String[] titulos = {
                "Fecha", "Entrada Matutina", "Salida Matutina", "Entrada Vespertina", "Salida Vespertina", "Horas"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, titulos);
            ResultSet resultado = manejador.obtenerDatos("SELECT fecha,entrada_man,salida_man,entrada_tarde,salida_tarde FROM registros WHERE usuario = " + id + ";");
            String[] registro = new String[6];
            int horas_man, horas_tarde;
            while (resultado.next()) {
                horas_man = 0;
                horas_tarde = 0;
                registro[0] = resultado.getString("fecha");
                registro[1] = resultado.getString("entrada_man");
                registro[2] = resultado.getString("salida_man");
                registro[3] = resultado.getString("entrada_tarde");
                registro[4] = resultado.getString("salida_tarde");

                ArrayList<Object> datos = new ArrayList<>();
                if (registro[1] != null && registro[2] != null) {
                    datos = manejador.resultado("SELECT (salida_man-entrada_man) horas FROM registros WHERE usuario = " + id + ";");
                    horas_man = Integer.valueOf(datos.get(0).toString());
                }
                if (registro[3] != null && registro[4] != null) {
                    datos = manejador.resultado("SELECT (salida_tarde-entrada_tarde) horas FROM registros WHERE usuario = " + id + ";");
                    horas_tarde = Integer.valueOf(datos.get(0).toString());
                }
                registro[5] = String.valueOf(horas_man + horas_tarde);
                modeloTabla.addRow(registro);
            }
            return modeloTabla;
        } catch (SQLException ex) {
            Logger.getLogger(inforDocController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return null;
        }
    }

    public int obtenerID(String cedula) {
        try {
            DataManager manejador = new DataManager();
            ResultSet resultado = manejador.obtenerDatos("SELECT id FROM usuarios WHERE cedula = '" + cedula + "';");
            //System.out.println(resultado.getInt("id"));
            return resultado.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(inforDocController.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}