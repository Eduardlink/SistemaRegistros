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
import vistas.CRUD;

/**
 *
 * @author Pc
 */
public class crudController {

    private DataManager manejador;

    public crudController() {
        manejador = new DataManager();
    }

    //agregar
    public void agregarUsuario(String nombre, String apellido, String cedula, String clave, String root) {
        CriptPass cripto = new CriptPass();
        String[] ingreso = {
            crearUsuario(nombre, apellido, cedula),
            cripto.Encriptar(clave),
            nombre.toLowerCase(),
            apellido.toLowerCase(),
            cedula,
            root
        };
        String sql = String.format("INSERT INTO usuarios(usuario,clave,nombre,apellido,cedula,root,estado) "
                + "VALUES ('%s','%s','%s','%s','%s','%s','1');", ingreso);
        manejador.ejecutarConsulta(sql);
    }

    public String crearUsuario(String nombre, String apellido, String cedula) {
        nombre = nombre.toLowerCase();
        apellido = apellido.toLowerCase().substring(0, 2);
        cedula = cedula.substring(6, 9);
        return nombre + apellido + cedula;
    }

    public ArrayList buscarCedula(String cedula) {
        ArrayList<Object> datos = new ArrayList<>();
        datos = manejador.resultado("SELECT cedula FROM usuarios WHERE cedula = '" + cedula + "';");
        return datos;
    }

    //eliminar
    public void eliminarUsuario(String cedula) {
        manejador.ejecutarConsulta("UPDATE usuarios SET estado='0' WHERE cedula='" + cedula + "';");
    }

    public void updateUser(String nombre, String apellido, String cedula, String root) {
        manejador.ejecutarConsulta("UPDATE usuarios SET nombre='" + nombre + "', apellido = '" + apellido + "',"
                + "cedula = '" + cedula + "', root ='" + root + "' WHERE cedula='" + cedula + "';");
    }

    public void agregarJornada(String cedula, String entrada_man, String salida_man, String entrada_tarde, String salida_tarde) {
        String sql = "INSERT INTO jornadas(entrada_man,salida_man,entrada_tarde,salida_tarde,ced_usuario) "
                + "VALUES('0" + entrada_man + "','" + salida_man + "','" + entrada_tarde + "','" + salida_tarde + "','" + cedula+ "');";
        manejador.ejecutarConsulta(sql);
    }

    public DefaultTableModel cargarTabla() {
        try {
            String[] columnas = {
                "N.", "Usuario", "Nombre", "Apellido", "Cedula", "Tipo Usuario", "Jornada Matutina", "Jornada Vespertina"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT usuario, nombre, apellido, cedula, root, estado FROM usuarios;");
            String[] registro = new String[8];
            int num = 1;
            ArrayList<Object> lista = new ArrayList<>();
            while (datos.next()) {
                registro[0] = String.valueOf(num);
                registro[1] = datos.getString("usuario");
                registro[2] = datos.getString("nombre");
                registro[3] = datos.getString("apellido");
                registro[4] = datos.getString("cedula");
                registro[5] = datos.getString("root");
                if (registro[5].equals("0")) {
                    registro[5] = "Docente";
                } else if (registro[5].equals("1")) {
                    registro[5] = "Administrador";
                }

                lista = manejador.resultado("SELECT entrada_man,salida_man,entrada_tarde,salida_tarde FROM jornadas WHERE ced_usuario = '" + registro[4] + "';");
                registro[6] = lista.get(0).toString() + " - " + lista.get(1).toString();
                registro[7] = lista.get(2).toString() + " - " + lista.get(3).toString();
                if (datos.getString("estado").equals("1")) {
                    modeloTabla.addRow(registro);
                }
                num++;
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error: " + ex);
            return null;
        }
    }

    public DefaultTableModel cargarTabla(String cedula) {
        try {
            String[] columnas = {
                "N.", "Usuario", "Nombre", "Apellido", "Cedula", "Tipo Usuario", "Jornada Matutina", "Jornada Vespertina"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT usuario, nombre, apellido, cedula, root, estado FROM usuarios WHERE cedula='"+cedula+"';");
            String[] registro = new String[8];
            int num = 1;
            ArrayList<Object> lista = new ArrayList<>();
            while (datos.next()) {
                registro[0] = String.valueOf(num);
                registro[1] = datos.getString("usuario");
                registro[2] = datos.getString("nombre");
                registro[3] = datos.getString("apellido");
                registro[4] = datos.getString("cedula");
                registro[5] = datos.getString("root");
                if (registro[5].equals("0")) {
                    registro[5] = "Docente";
                } else if (registro[5].equals("1")) {
                    registro[5] = "Administrador";
                }

                lista = manejador.resultado("SELECT entrada_man,salida_man,entrada_tarde,salida_tarde FROM jornadas WHERE ced_usuario = '" + cedula + "';");
                registro[6] = lista.get(0).toString() + " - " + lista.get(1).toString();
                registro[7] = lista.get(2).toString() + " - " + lista.get(3).toString();
                if (datos.getString("estado").equals("1")) {
                    modeloTabla.addRow(registro);
                }
                num++;
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error: " + ex);
            return null;
        }
    }

}
