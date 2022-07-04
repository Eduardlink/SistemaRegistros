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
public class InformeDiarioController {

    public DefaultTableModel cargarTablaMatutina(String cedula) {
        try {
            String[] titulos = {
                "N.", "Jornada", "Horas de jornada", "Hora entrada", "Hora Salida", "Horas registradas"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, titulos);
            DataManager manejador = new DataManager();
            ResultSet registros = manejador.obtenerDatos("SELECT * FROM registros WHERE ced_usuario = '" + cedula + "';");
            ArrayList<Object> jornada = new ArrayList<>();
            jornada = manejador.resultado("SELECT * FROM jornadas WHERE ced_usuario = '" + cedula + "';");
            ArrayList<Object> datosSubcon = new ArrayList<>();
            String[] datos = new String[6];
            int contador = 1;
            float horas;
            while (registros.next()) {
                horas = 0;
                datos[0] = String.valueOf(contador);
                datos[1] = jornada.get(1).toString() + " - " + jornada.get(2).toString();
                datos[2] = calcularHorasMan(manejador, cedula, true);
                if (registros.getString("entrada_man") == null) {
                    datos[3] = "Sin registrar";
                } else {
                    datos[3] = registros.getString("entrada_man");
                }
                if (registros.getString("salida_man") == null) {
                    datos[4] = "Sin registrar";
                } else {
                    datos[4] = registros.getString("salida_man");
                }
                if (registros.getString("entrada_man") != null && registros.getString("salida_man") != null) {
                    datosSubcon = manejador.resultado("SELECT (salida_man-entrada_man) horas FROM registros WHERE ced_usuario = '" + cedula + "';");
                    horas = Float.valueOf(datosSubcon.get(0).toString());
                }
                datos[5] = String.valueOf(horas);
                modeloTabla.addRow(datos);
                contador++;
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (SQLException ex) {
            Logger.getLogger(InformeDiarioController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String calcularHorasMan(DataManager manejador, String cedula, boolean jornadaMan) {
        float horas;
        ArrayList<Object> datosSubcon = new ArrayList<>();
        if (jornadaMan == true) {
            datosSubcon = manejador.resultado("SELECT (salida_man-entrada_man) horas FROM jornadas WHERE ced_usuario = '" + cedula + "';");
            horas = Float.valueOf(datosSubcon.get(0).toString());
            return String.valueOf(horas);
        } else {
            datosSubcon = manejador.resultado("SELECT (salida_tarde-entrada_tarde) horas FROM jornadas WHERE ced_usuario = '" + cedula + "';");
            horas = Float.valueOf(datosSubcon.get(0).toString());
            return String.valueOf(horas);
        }

    }
    public DefaultTableModel cargarTablaVespertina(String cedula) {
        try {
            String[] titulos = {
                "N.", "Jornada", "Horas de jornada", "Hora entrada", "Hora Salida", "Horas registradas"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, titulos);
            DataManager manejador = new DataManager();
            ResultSet registros = manejador.obtenerDatos("SELECT * FROM registros WHERE ced_usuario = '" + cedula + "';");
            ArrayList<Object> jornada = new ArrayList<>();
            jornada = manejador.resultado("SELECT * FROM jornadas WHERE ced_usuario = '" + cedula + "';");
            ArrayList<Object> datosSubcon = new ArrayList<>();
            String[] datos = new String[6];
            int contador = 1;
            float horas;
            while (registros.next()) {
                horas = 0;
                datos[0] = String.valueOf(contador);
                datos[1] = jornada.get(1).toString() + " - " + jornada.get(2).toString();
                datos[2] = calcularHorasMan(manejador, cedula, false);
                if (registros.getString("entrada_tarde") == null) {
                    datos[3] = "Sin registrar";
                } else {
                    datos[3] = registros.getString("entrada_tarde");
                }
                if (registros.getString("salida_tarde") == null) {
                    datos[4] = "Sin registrar";
                } else {
                    datos[4] = registros.getString("salida_tarde");
                }
                if (registros.getString("entrada_tarde") != null && registros.getString("salida_tarde") != null) {
                    datosSubcon = manejador.resultado("SELECT (salida_tarde-entrada_tarde) horas FROM registros WHERE ced_usuario = '" + cedula + "';");
                    horas = Float.valueOf(datosSubcon.get(0).toString());
                }
                datos[5] = String.valueOf(horas);
                modeloTabla.addRow(datos);
                contador++;
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (SQLException ex) {
            Logger.getLogger(InformeDiarioController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String buscarUsuario(String usuario){
        System.out.println(usuario);
        DataManager manejador = new DataManager();
        ArrayList<Object> lista = new  ArrayList<>();
        lista = manejador.resultado("SELECT cedula FROM usuarios WHERE usuario = '"+usuario+"';");
        return lista.get(0).toString();
    }
}
