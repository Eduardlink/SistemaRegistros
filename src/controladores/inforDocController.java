/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.util.ArrayList;
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
            datos.get(3).toString(),};
        return info;
    }

    public DefaultTableModel cargarTabla() {
        DataManager manejador = new DataManager();
        String[] titulos ={
           "Fecha","Entrada Matutina","Salida Matutina","Entrada Vespertina","Salida Vespertina","Horas" 
        } ;
        DefaultTableModel modeloTabla = new DefaultTableModel(null,titulos);
        
        return null;
    }
}
