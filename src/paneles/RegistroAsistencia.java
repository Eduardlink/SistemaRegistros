/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package paneles;

import controladores.AsistenciaController;
import controladores.InformeDiarioController;
import java.awt.ComponentOrientation;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import javax.swing.JOptionPane;

/**
 *
 * @author Pc
 */
public class RegistroAsistencia extends javax.swing.JPanel {

    private String user;
    private String entrada_man;
    private String salida_man;
    private String entrada_tarde;
    private String salida_tarde;
    private AsistenciaController controlador;

    /**
     * Creates new form RegistroAsistencia
     */
    public RegistroAsistencia(String usuario) {
        initComponents();
        this.controlador=new AsistenciaController();
        this.user = usuario;
        String[] registro = this.controlador.buscarJornada(new InformeDiarioController().buscarUsuario(usuario));
        entrada_man = registro[0];
        salida_man = registro[1];
        entrada_tarde = registro[2];
        salida_tarde = registro[3];
        jbtnEntrada.setEnabled(false);
        jbtnSalida.setEnabled(false);
        activarBoton();
    }

    public void activarBoton() {
        LocalTime horaActual = LocalTime.now();
        if (horaActual.isAfter(LocalTime.parse(entrada_man).minusMinutes(10)) && horaActual.isBefore(LocalTime.parse(entrada_man).plusMinutes(15))) {
            jbtnEntrada.setEnabled(true);
        } else {
            jbtnEntrada.setEnabled(false);
        }
        if (horaActual.isAfter(LocalTime.parse(salida_man).minusMinutes(5)) && horaActual.isBefore(LocalTime.parse(salida_man).plusMinutes(10))) {
            jbtnSalida.setEnabled(true);
        } else {
            jbtnSalida.setEnabled(false);
        }
        if (horaActual.isAfter(LocalTime.parse(entrada_tarde).minusMinutes(10)) && horaActual.isBefore(LocalTime.parse(entrada_tarde).plusMinutes(15))) {
            jbtnEntrada.setEnabled(true);
        } else {
            jbtnEntrada.setEnabled(false);
        }
        if (horaActual.isAfter(LocalTime.parse(salida_tarde).minusMinutes(5)) && horaActual.isBefore(LocalTime.parse(salida_tarde).plusMinutes(10))) {
            jbtnSalida.setEnabled(true);
        } else {
            jbtnSalida.setEnabled(false);
        }
    }
    
    public void inicializarAsistencia(){
        LocalTime horaActual = LocalTime.now();
        if(horaActual.isBefore(LocalTime.parse(entrada_man).plusMinutes(15))){
            this.controlador.verificarRegistro(this.controlador.obtenerFecha(), new InformeDiarioController().buscarUsuario(user));
        }
    }

    public void registrarEntrada() {
        String cedula = new InformeDiarioController().buscarUsuario(user);
        LocalTime horaActual = LocalTime.now();
        if (horaActual.isAfter(LocalTime.parse(entrada_man).minusMinutes(10)) && horaActual.isBefore(LocalTime.parse(entrada_man).plusMinutes(15))) {
            if (controlador.entradaMatutina(cedula, controlador.obtenerFecha()) == null) {
                controlador.registrarEntradaMatutina(controlador.obtenerHora(), controlador.obtenerFecha(), cedula);
            } else {
                JOptionPane.showMessageDialog(null, "Ya se ha guardado su registro");
            }
        }else if(horaActual.isAfter(LocalTime.parse(entrada_tarde).minusMinutes(10)) && horaActual.isBefore(LocalTime.parse(entrada_tarde).plusMinutes(15))){
            if (controlador.entradaVespertina(cedula, controlador.obtenerFecha()) == null) {
                controlador.registrarEntradaTarde(controlador.obtenerHora(), controlador.obtenerFecha(), cedula);
            } else {
                JOptionPane.showMessageDialog(null, "Ya se ha guardado su registro");
            }
        }
    }
    
    public void registrarSalida(){
        String cedula = new InformeDiarioController().buscarUsuario(user);
        LocalTime horaActual = LocalTime.now();
        if (horaActual.isAfter(LocalTime.parse(salida_man).minusMinutes(5)) && horaActual.isBefore(LocalTime.parse(salida_man).plusMinutes(10))) {
            if (controlador.salidaMatutina(cedula, controlador.obtenerFecha()) == null) {
                controlador.registrarSalidaMatutina(controlador.obtenerHora(), controlador.obtenerFecha(), cedula);
            } else {
                JOptionPane.showMessageDialog(null, "Ya se ha guardado su registro");
            }
        }else if(horaActual.isAfter(LocalTime.parse(salida_tarde).minusMinutes(5)) && horaActual.isBefore(LocalTime.parse(salida_tarde).plusMinutes(10))){
            if (controlador.salidaVespertina(cedula, controlador.obtenerFecha()) == null) {
                controlador.registrarSalidaTarde(controlador.obtenerHora(), controlador.obtenerFecha(), cedula);
            } else {
                JOptionPane.showMessageDialog(null, "Ya se ha guardado su registro");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtnSalida = new javax.swing.JButton();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        jbtnEntrada = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jbtnSalida.setBackground(new java.awt.Color(51, 51, 51));
        jbtnSalida.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jbtnSalida.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSalida.setText("Registrar Salida");
        jbtnSalida.setBorder(null);
        jbtnSalida.setBorderPainted(false);
        jbtnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalidaActionPerformed(evt);
            }
        });

        rSLabelHora1.setForeground(new java.awt.Color(0, 0, 0));
        rSLabelHora1.setFont(new java.awt.Font("Roboto Bold", 1, 48)); // NOI18N

        jbtnEntrada.setBackground(new java.awt.Color(236, 71, 71));
        jbtnEntrada.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jbtnEntrada.setForeground(new java.awt.Color(255, 255, 255));
        jbtnEntrada.setText("Registrar Entrada");
        jbtnEntrada.setBorder(null);
        jbtnEntrada.setBorderPainted(false);
        jbtnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEntradaActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesFrames/Asistencias.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(414, 414, 414)
                        .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jbtnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jbtnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEntradaActionPerformed
        registrarEntrada();
    }//GEN-LAST:event_jbtnEntradaActionPerformed

    private void jbtnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalidaActionPerformed
        registrarSalida();
    }//GEN-LAST:event_jbtnSalidaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnEntrada;
    private javax.swing.JButton jbtnSalida;
    private rojeru_san.RSLabelHora rSLabelHora1;
    // End of variables declaration//GEN-END:variables
}
