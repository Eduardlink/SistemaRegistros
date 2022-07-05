/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pc
 */
public class DataBase {

    private String url;
    private String driver;
    private Connection conexion;

    public DataBase() {
        this.driver = "jdbc:sqlite";
        this.url = "dataBase.db";
    }

    protected void conectar() {
        try {
            this.conexion = DriverManager.getConnection(this.driver + ":" + this.url);
            if (!this.conexion.isClosed()) {
                    System.out.println("Conectado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void cerrar(){
        try {
            if(!this.conexion.isClosed()){
                this.conexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected Connection getConexion() {
        return conexion;
    }
    
}
