/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import modelos.DataManager;

/**
 *
 * @author Pc
 */
public class AsistenciaController {

    public String[] buscarJornada(String cedula) {
        DataManager manejador = new DataManager();
        ArrayList<Object> lista = new ArrayList<>();
        lista = manejador.resultado("SELECT entrada_man, salida_man, entrada_tarde, salida_tarde FROM jornadas WHERE ced_usuario='" + cedula + "';");
        String[] retorno = {
            lista.get(0).toString(),
            lista.get(1).toString(),
            lista.get(2).toString(),
            lista.get(3).toString()
        };
        return retorno;
    }

    public String obtenerFecha() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        return formateador.format(ahora);
    }

    public String obtenerHora() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
        return formateador.format(ahora);
    }

    public String entradaMatutina(String cedula, String fecha) {
        DataManager manejador = new DataManager();
        ArrayList<Object> lista = new ArrayList<>();
        lista = manejador.resultado("SELECT entrada_man FROM registros WHERE ced_usuario='" + cedula + "' AND fecha='" + fecha + "';");
        if (lista.size() < 1) {
            return null;
        } else {
            return lista.get(0).toString();
        }
    }

    public String entradaVespertina(String cedula, String fecha) {
        DataManager manejador = new DataManager();
        ArrayList<Object> lista = new ArrayList<>();
        lista = manejador.resultado("SELECT entrada_tarde FROM registros WHERE ced_usuario='" + cedula + "' AND fecha='" + fecha + "';");
        if (lista.get(0) == null) {
            return null;
        } else {
            return lista.get(0).toString();
        }
    }

    public String salidaMatutina(String cedula, String fecha) {
        DataManager manejador = new DataManager();
        ArrayList<Object> lista = new ArrayList<>();
        lista = manejador.resultado("SELECT salida_man FROM registros WHERE ced_usuario='" + cedula + "' AND fecha='" + fecha + "';");
        if (lista.get(0) == null) {
            return null;
        } else {
            return lista.get(0).toString();
        }
    }

    public String salidaVespertina(String cedula, String fecha) {
        DataManager manejador = new DataManager();
        ArrayList<Object> lista = new ArrayList<>();
        lista = manejador.resultado("SELECT salida_tarde FROM registros WHERE ced_usuario='" + cedula + "' AND fecha='" + fecha + "';");
        if (lista.get(0) == null) {
            return null;
        } else {
            return lista.get(0).toString();
        }
    }

    public void registrarEntradaMatutina(String hora, String fecha, String cedula) {
        DataManager manejador = new DataManager();
        manejador.ejecutarConsulta("INSERT INTO registros (entrada_man,salida_man,entrada_tarde,salida_tarde,fecha,ced_usuario) "
                + "VALUES ('" + hora + "',null,null,null,'" + fecha + "','" + cedula + "');");
    }

    public void registrarSalidaMatutina(String hora, String fecha, String cedula) {
        DataManager manejador = new DataManager();
        manejador.ejecutarConsulta("UPDATE registros SET salida_man = '" + hora + "' WHERE ced_usuario = '" + cedula + "' AND fecha = '" + fecha + "';");
    }

    public void registrarEntradaTarde(String hora, String fecha, String cedula) {
        DataManager manejador = new DataManager();
        manejador.ejecutarConsulta("UPDATE registros SET entrada_tarde = '" + hora + "' WHERE ced_usuario = '" + cedula + "' AND fecha = '" + fecha + "';");
    }

    public void registrarSalidaTarde(String hora, String fecha, String cedula) {
        DataManager manejador = new DataManager();
        manejador.ejecutarConsulta("UPDATE registros SET salida_tarde = '" + hora + "' WHERE ced_usuario = '" + cedula + "' AND fecha = '" + fecha + "';");
    }

    public void verificarRegistro(String fecha, String cedula) {
        DataManager manejador = new DataManager();
        ArrayList<Object> lista = new ArrayList<>();
        lista = manejador.resultado("SELECT entrada_man FROM registros WHERE ced_usuario='" + cedula + "' AND fecha='" + fecha + "';");
        if (lista.size() < 1) {
            manejador.ejecutarConsulta("INSERT INTO registros (entrada_man,salida_man,entrada_tarde,salida_tarde,fecha,ced_usuario) "
                    + "VALUES (null,null,null,null,'" + fecha + "','" + cedula + "');");
            System.out.println("NJ");
        }
    }
}
