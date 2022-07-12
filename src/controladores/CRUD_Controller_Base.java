/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import modelos.DataManager;

/**
 *
 * @author Pc
 */
public class CRUD_Controller_Base {
    
    private boolean valido;
    
    public boolean isValido() {
        return valido;
    }
    
    public boolean ValidarUsuario(String usuario, String clave) {
        try {
            clave = new EncriptadorAES().encriptar(clave, "SisTech");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        }
        DataManager manejador = new DataManager();
        ArrayList<Object> lista = new ArrayList<>();
        lista = manejador.resultado("SELECT * FROM usuarios WHERE usuario='"+usuario+"' AND clave='"+clave+"';");
        if (lista.size() > 0) {
            this.valido = true;
        }
        return this.valido;
    }

    //usuario
    public void createUsuario(Object[] datos) {
        try {
            datos[1] = new EncriptadorAES().encriptar(datos[1].toString(), "SisTech");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        }
        //datos[1] = new SecretPass().Encriptar(datos[1].toString());
        DataManager manejador = new DataManager();
        String sql = String.format("INSERT INTO usuarios(usuario,clave,nombre,apellido,cedula,root) "
                + "VALUES ('%s','%s','%s','%s','%s','%s');",datos) ;
        manejador.ejecutarConsulta(sql);
    }
    
    public void createUsuario2(Object[] datos) {
        try {
            datos[1] = new EncriptadorAES().encriptar(datos[1].toString(), "SisTech");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CRUD_Controller_Base.class.getName()).log(Level.SEVERE, null, ex);
        }
        //datos[1] = new SecretPass().Encriptar(datos[1].toString());
        DataManager manejador = new DataManager();
        String sql = String.format("INSERT INTO usuarios(usuario,clave,nombre,apellido,cedula,root) "
                + "VALUES ('" + datos[0] + "','" + datos[1] + "','" + datos[2] + "','" + datos[3] + "','" + datos[4] + "','" + datos[5] + "');");
        manejador.ejecutarConsulta(sql);
    }
    
    public ArrayList readUsuario(String usuario) {
        DataManager manejador = new DataManager();
        ArrayList<Object> lista = new ArrayList<>();
        lista = manejador.resultado(String.format("SELECT * FROM usuarios WHERE usuario='%s'", usuario));
        if (lista.size() > 0) {
            this.valido = true;
        }
        return lista;
    }
    
    public void updateUsuario(Object[] datos, String usuario) {
        DataManager manejador = new DataManager();
        String sql = ("UPDATE usuarios"
                + "SET usuario=" + datos[0] + ","
                + "clave = " + datos[1] + ","
                + "nombre=" + datos[2] + ","
                + "apellido=" + datos[3] + ","
                + "cedula" + datos[4] + ","
                + "root" + datos[5] + " WHERE usuario=" + usuario + ";");
        manejador.ejecutarConsulta(sql);
    }
    
    public void deleteUsuario(String usuario) {
        DataManager manejador = new DataManager();
        String sql = ("DELETE FROM usuarios WHERE usuario=" + usuario + ";");
        manejador.ejecutarConsulta(sql);
        deleteRegistroByUser(usuario);
    }

    //registros
    public void createRegistro(Object[] datos) {
        DataManager manejador = new DataManager();
        String sql = ("INSERT INTO Registros(entrada_man,salida_man,entrada_tarde,salida_tarde,fecha,usuario) "
                + "VALUES (" + datos[0] + "," + datos[1] + "," + datos[2] + "," + datos[3] + "," + datos[4] + "," + datos[5] + ");");
        //manejador.ingresarConsulta(sql);
    }
    
    public ArrayList readRegistro(String usuario) {
        DataManager manejador = new DataManager();
        ArrayList<Object> lista = new ArrayList<>();
        lista = manejador.resultado(String.format("SELECT * FROM registros WHERE usuario='%s'", usuario));
        if (lista.size() > 0) {
            this.valido = true;
        }
        return lista;
    }
    
    public void updateRegistro(Object[] datos) {
        DataManager manejador = new DataManager();
        String sql = ("UPDATE registros (entrada_man,salida_man,entrada_tarde,salida_tarde,fecha,usuario) "
                + "SET entrada_man=" + datos[0] + ","
                + "salida_man" + datos[1] + ","
                + "entrada_tarde" + datos[2] + ","
                + "salida_tarde" + datos[3] + ","
                + "fecha" + datos[4] + ","
                + "usuario" + datos[5] + ") "
                + "WHERE usuario=" + datos[5] + ";");
        manejador.ejecutarConsulta(sql);
    }
    
    public void deleteRegistro(Object[] datos) {
        DataManager manejador = new DataManager();
        String sql = ("DELETE FROM registros WHERE entrada_ma=" + datos[0] + " AND salida_man=" + datos[1] + " AND entrada_tarde=" + datos[2]
                + " AND salida_tarde=" + datos[3] + " AND fecha=" + datos[4] + " AND usuario=" + datos[5] + ";");
        manejador.ejecutarConsulta(sql);
    }
    
    public void deleteRegistroByUser(String usuario) {
        DataManager manejador = new DataManager();
        String sql = ("DELETE FROM registros WHERE usuario=" + usuario + ";");
        manejador.ejecutarConsulta(sql);
    }
    
    public void agregarRegistro(){
        DataManager manejador = new DataManager();
        manejador.ejecutarConsulta("INSERT INTO registros(entrada_man,salida_man,entrada_tarde,salida_tarde) "
                + "VALUES (");
    }
}
