/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Pc
 */
public class Conexion {
    public  Connection getConexion(){
                
        try{
        String conexionURL = "jdbc:sqlserver://mysqlregistros.database.windows.net:1433;"
                + "database=SistemaRegistros;"
                + "user=Edu@mysqlregistros;"
                + "password=Tictactoe2001;"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;"
                + "loginTimeout=30;";
            Connection con = DriverManager.getConnection(conexionURL);
            return con;
        }catch(Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }
}
