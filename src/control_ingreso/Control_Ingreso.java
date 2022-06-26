/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control_ingreso;

/**
 *
 * @author Pc
 */
public class Control_Ingreso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Registro reg = new Registro();
        String[] datos = {"mh1234","9874","Maria","Hidalgo","1803"};
        reg.almacenarDatos(datos);
    }
    
}
