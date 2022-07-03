/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.Date;
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
public class BusquedaFechaController {

    public DefaultTableModel cargarTabla(Date fecha) {
        try {
            DataManager manejador = new DataManager();
            String[] titulos = {
                "Cedula", "Nombre", "Apellido", "Hor_Ent_Mat", "Hor_Sal_Mat", "Hor_Ent_Ves", "Hor_Sal_Ve", "Horas"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, titulos);
            System.out.println(fecha);
            ResultSet resultado = manejador.obtenerDatos("SELECT * FROM registros WHERE fecha = '"+fecha+"';");
            System.out.println(resultado.getString("fecha"));
            String[] registro = new String[8];
            int horas_man, horas_tarde;
            ArrayList<Object> datos = new ArrayList<>();
            while (resultado.next()) {
                horas_man = horas_tarde = 0;
                registro[0] = resultado.getString("ced_usuario");
                registro[3] = resultado.getString("entrada_man");
                registro[4] = resultado.getString("salida_man");
                registro[5] = resultado.getString("entrada_tarde");
                registro[6] = resultado.getString("salida_tarde");
                
                if (registro[3] != null && registro[4] != null) {
                    datos = manejador.resultado("SELECT (salida_man-entrada_man) horas FROM registros WHERE ced_usuario = '" + registro[0] + "';");
                    horas_man = Integer.valueOf(datos.get(0).toString());
                }
                if (registro[5] != null && registro[6] != null) {
                    datos = manejador.resultado("SELECT (salida_tarde-entrada_tarde) horas FROM registros WHERE ced_usuario = '" + registro[0] + "';");
                    horas_tarde = Integer.valueOf(datos.get(0).toString());
                }
                registro[7] = String.valueOf(horas_man + horas_tarde);
                
                datos = manejador.resultado("SELECT nombre, apellido FROM usuarios WHERE cedula = '"+registro[0]+"';");
                
                registro[1] = datos.get(0).toString();
                registro[2] = datos.get(1).toString();
                modeloTabla.addRow(registro);
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (SQLException ex) {
            Logger.getLogger(BusquedaFechaController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
