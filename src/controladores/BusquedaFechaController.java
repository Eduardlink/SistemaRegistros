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
public class BusquedaFechaController {

    public void cargarTabla(String fecha) {
        try {
            DataManager manejador = new DataManager();
            String[] titulos = {
                "Cedula", "Nombre", "Apellido", "Hor_Ent_Mat", "Hor_Sal_Mat", "Hor_Ent_Ves", "Hor_Sal_Ve", "Horas"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, titulos);
            ResultSet resultado = manejador.obtenerDatos("SELECT u.cedula, u.nombre, u.apellido, r.entrada_man,r.salida_man, r.entrada_tarde, r.salida_tarde "
                    + "FROM usuarios u, registros r "
                    + "WHERE r.fecha='" + fecha + "';");

            String[] registro = new String[8];
            int horas_man, horas_tarde;
            int id;
            while (resultado.next()) {
                horas_man = horas_tarde = 0;
                registro[0] = resultado.getString("cedula");
                registro[1] = resultado.getString("nombre");
                registro[2] = resultado.getString("apellido");
                registro[3] = resultado.getString("entrada_man");
                registro[4] = resultado.getString("salida_man");
                registro[5] = resultado.getString("entrada_tarde");
                registro[6] = resultado.getString("salida_tarde");
                id = new inforDocController().obtenerID(registro[0]);
                ArrayList<Object> datos = new ArrayList<>();
                if (registro[3] != null && registro[4] != null) {
                    datos = manejador.resultado("SELECT (salida_man-entrada_man) horas FROM registros WHERE usuario = " + id + ";");
                    horas_man = Integer.valueOf(datos.get(0).toString());
                }
                if (registro[5] != null && registro[6] != null) {
                    datos = manejador.resultado("SELECT (salida_tarde-entrada_tarde) horas FROM registros WHERE usuario = " + id + ";");
                    horas_tarde = Integer.valueOf(datos.get(0).toString());
                }
                registro[7] = String.valueOf(horas_man + horas_tarde);
                modeloTabla.addRow(registro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BusquedaFechaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
