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
            DataManager manejador = new DataManager();
            String[] titulos = {
                "Fecha", "Entrada Matutina", "Salida Matutina", "Entrada Vespertina", "Salida Vespertina", "Horas"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, titulos);
            ResultSet resultado = manejador.obtenerDatos("SELECT fecha,entrada_man,salida_man,entrada_tarde,salida_tarde FROM registros WHERE cedula = '" + cedula + "';");
            String[] registro = new String[6];
            int horas;
            while(resultado.next()){
                horas=0;
                registro[0]=resultado.getNString("fecha");
                registro[1]=resultado.getString("entrada_man");
                registro[2]=resultado.getString("salida_man");
                registro[3]=resultado.getString("entrada_tarde");
                registro[4]=resultado.getString("salida_tarde");
                
                //if(registro[1]!=null && registro[2]=!=null){
                    
                //}else if(registro[]){
                    
                //}
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(inforDocController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
}
