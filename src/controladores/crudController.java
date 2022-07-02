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
            cedula.toLowerCase(),
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

    public void updateUser(String nombre, String apellido, String cedula, String clave, String root) {
        clave = new CriptPass().Encriptar(cedula);
        manejador.ejecutarConsulta("UPDATE usuarios SET nombre='" + nombre + "', apellido = '" + apellido + "',"
                + "cedula = '" + cedula + "', clave ='" + clave + "', root ='" + root + "' WHERE cedula='" + cedula + "';");
    }

    public DefaultTableModel cargarTabla() {
        try {
            String[] columnas = {
                "N.", "Usuario", "Nombre", "Apellido", "Cedula", "Tipo Usuario"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT * FROM usuarios;");

            String[] registro = new String[6];
            int num =1;
            while (datos.next()) {
                registro[0]=String.valueOf(num);
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
                modeloTabla.addRow(registro);
                
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
                "N.", "Usuario", "Nombre", "Apellido", "Cedula", "Tipo Usuario"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT * FROM usuarios WHERE cedula='" + cedula + "';");
            String[] registro = new String[6];
            int num =1;
            while (datos.next()) {
                registro[0]=String.valueOf(num);
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
                if(datos.getString("estado").equals("1")){
                modeloTabla.addRow(registro);
                }
                num++;
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


}
