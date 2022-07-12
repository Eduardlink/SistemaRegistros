/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
        try {
            clave = new EncriptadorAES().encriptar(clave, "SisTech");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] ingreso = {
            crearUsuario(nombre, apellido, cedula),
            clave,
            nombre,
            apellido,
            cedula,
            root
        };
        String sql = String.format("INSERT INTO usuarios(usuario,clave,nombre,apellido,cedula,root,estado) "
                + "VALUES ('%s','%s','%s','%s','%s','%s','1');", ingreso);
        manejador.ejecutarConsulta(sql);
    }

    public String crearUsuario(String nombre, String apellido, String cedula) {
        nombre = nombre.toLowerCase().substring(0, 1);
        apellido = apellido.toLowerCase();
        cedula = cedula.substring(6, 10);
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

    public void actualizarEstado(String cedula) {
        manejador.ejecutarConsulta("UPDATE usuarios SET estado= 1 WHERE cedula='" + cedula + "';");
    }

    public void agregarJornada(String cedula, String entrada_man, String salida_man, String entrada_tarde, String salida_tarde) {
        entrada_man = verificarHora(entrada_man);
        salida_man = verificarHora(salida_man);
        entrada_tarde = verificarHora(entrada_tarde);
        salida_tarde = verificarHora(salida_tarde);
        String sql = "INSERT INTO jornadas(entrada_man,salida_man,entrada_tarde,salida_tarde,ced_usuario) "
                + "VALUES('" + entrada_man + "','" + salida_man + "','" + entrada_tarde + "','" + salida_tarde + "','" + cedula + "');";
        manejador.ejecutarConsulta(sql);
    }

    public String verificarHora(String hora) {
        if (hora.length() == 4) {
            return "0" + hora;
        } else {
            return hora;
        }
    }

    public DefaultTableModel cargarTabla() {
        try {
            String[] columnas = {
                "Usuario", "Nombre", "Apellido", "Cedula", "Tipo Usuario", "Jornada Matutina", "Jornada Vespertina"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT usuario, nombre, apellido, cedula, root, estado FROM usuarios;");
            String[] registro = new String[7];
            ArrayList<Object> lista = new ArrayList<>();
            while (datos.next()) {
                registro[0] = datos.getString("usuario");
                registro[1] = datos.getString("nombre");
                registro[2] = datos.getString("apellido");
                registro[3] = datos.getString("cedula");
                registro[4] = datos.getString("root");
                if (registro[4].equals("0")) {
                    registro[4] = "Docente";
                } else if (registro[4].equals("1")) {
                    registro[4] = "Administrador";
                }

                lista = manejador.resultado("SELECT entrada_man,salida_man,entrada_tarde,salida_tarde FROM jornadas WHERE ced_usuario = '" + registro[3] + "';");
                registro[5] = lista.get(0).toString() + " - " + lista.get(1).toString();
                registro[6] = lista.get(2).toString() + " - " + lista.get(3).toString();
                if (datos.getString("estado").equals("1")) {
                    modeloTabla.addRow(registro);
                }
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
                "Usuario", "Nombre", "Apellido", "Cedula", "Tipo Usuario", "Jornada Matutina", "Jornada Vespertina"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT usuario, nombre, apellido, cedula, root, estado FROM usuarios WHERE cedula='" + cedula + "';");
            String[] registro = new String[7];
            ArrayList<Object> lista = new ArrayList<>();
            while (datos.next()) {
                registro[0] = datos.getString("usuario");
                registro[1] = datos.getString("nombre");
                registro[2] = datos.getString("apellido");
                registro[3] = datos.getString("cedula");
                registro[4] = datos.getString("root");
                if (registro[4].equals("0")) {
                    registro[4] = "Docente";
                } else if (registro[4].equals("1")) {
                    registro[4] = "Administrador";
                }

                lista = manejador.resultado("SELECT entrada_man,salida_man,entrada_tarde,salida_tarde FROM jornadas WHERE ced_usuario = '" + cedula + "';");
                registro[5] = lista.get(0).toString() + " - " + lista.get(1).toString();
                registro[6] = lista.get(2).toString() + " - " + lista.get(3).toString();
                if (datos.getString("estado").equals("1")) {
                    modeloTabla.addRow(registro);
                }
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error: " + ex);
            return null;
        }
    }

    public DefaultTableModel cargarTablaInactivos() {
        try {
            String[] columnas = {
                "Usuario", "Nombre", "Apellido", "Cedula", "Tipo Usuario", "Jornada Matutina", "Jornada Vespertina"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT usuario, nombre, apellido, cedula, root, estado FROM usuarios WHERE estado = 0;");
            String[] registro = new String[7];
            ArrayList<Object> lista = new ArrayList<>();
            while (datos.next()) {
                registro[0] = datos.getString("usuario");
                registro[1] = datos.getString("nombre");
                registro[2] = datos.getString("apellido");
                registro[3] = datos.getString("cedula");
                registro[4] = datos.getString("root");
                if (registro[4].equals("0")) {
                    registro[4] = "Docente";
                } else if (registro[4].equals("1")) {
                    registro[4] = "Administrador";
                }

                lista = manejador.resultado("SELECT entrada_man,salida_man,entrada_tarde,salida_tarde FROM jornadas WHERE ced_usuario = '" + registro[3] + "';");
                registro[5] = lista.get(0).toString() + " - " + lista.get(1).toString();
                registro[6] = lista.get(2).toString() + " - " + lista.get(3).toString();
                modeloTabla.addRow(registro);
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
