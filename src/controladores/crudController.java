/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.util.ArrayList;
import modelos.DataManager;

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
        String sql = String.format("INSERT INTO usuarios(usuario,clave,nombre,apellido,cedula,root) "
                + "VALUES ('%s','%s','%s','%s','%s','%s');", ingreso);
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
        manejador.ejecutarConsulta("DELETE FROM usuarios WHERE cedula='" + cedula + "';");
        deleteRegistroByUser(cedula);
    }

    public void deleteRegistroByUser(String cedula) {
        manejador.ejecutarConsulta("DELETE FROM registros WHERE usuario=" + cedula + ";");
    }

    public void updateUser(String nombre, String apellido, String cedula, String clave,String root) {
        manejador.ejecutarConsulta("");
    }
    
    public String[] buscarUsuario(String cedula){
        return null;
    }
    
    
}
