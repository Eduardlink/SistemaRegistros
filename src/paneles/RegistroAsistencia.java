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
        jbtnEntrada.setVisible(false);
        jbtnSalida.setVisible(false);
        activarBoton();
    }

    public void activarBoton() {
        LocalTime horaActual = LocalTime.now();
        if (horaActual.isAfter(LocalTime.parse(entrada_man).minusMinutes(10)) && horaActual.isBefore(LocalTime.parse(entrada_man).plusMinutes(15))) {
            jbtnEntrada.setVisible(true);
        } else {
            jbtnEntrada.setVisible(false);
        }
        if (horaActual.isAfter(LocalTime.parse(salida_man).minusMinutes(5)) && horaActual.isBefore(LocalTime.parse(salida_man).plusMinutes(10))) {
            jbtnSalida.setVisible(true);
        } else {
            jbtnSalida.setVisible(false);
        }
        if (horaActual.isAfter(LocalTime.parse(entrada_tarde).minusMinutes(10)) && horaActual.isBefore(LocalTime.parse(entrada_tarde).plusMinutes(15))) {
            jbtnEntrada.setVisible(true);
        } else {
            jbtnEntrada.setVisible(false);
        }
        if (horaActual.isAfter(LocalTime.parse(salida_tarde).minusMinutes(5)) && horaActual.isBefore(LocalTime.parse(salida_tarde).plusMinutes(10))) {
            jbtnSalida.setEnabled(true);
        } else {
            jbtnSalida.setVisible(false);
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

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jpMenuBar = new javax.swing.JPanel();
        jbtnadmin = new javax.swing.JButton();
        jbtndocente = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        jpMenuBar1 = new javax.swing.JPanel();
        jbtnEntrada = new javax.swing.JButton();
        jbtnSalida = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jpMenuBar.setBackground(new java.awt.Color(63, 78, 79));
        jpMenuBar.setToolTipText("");
        jpMenuBar.setPreferredSize(new java.awt.Dimension(1250, 37));
        jpMenuBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnadmin.setBackground(new java.awt.Color(63, 78, 79));
        jbtnadmin.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 24)); // NOI18N
        jbtnadmin.setForeground(new java.awt.Color(255, 255, 255));
        jbtnadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesFrames/admin_icon.png"))); // NOI18N
        jbtnadmin.setText("Acceder como administrador");
        jbtnadmin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnadmin.setBorderPainted(false);
        jbtnadmin.setContentAreaFilled(false);
        jbtnadmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnadmin.setDefaultCapable(false);
        jbtnadmin.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jbtnadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnadminActionPerformed(evt);
            }
        });
        jpMenuBar.add(jbtnadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, -20, 400, 100));

        jbtndocente.setBackground(new java.awt.Color(63, 78, 79));
        jbtndocente.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 24)); // NOI18N
        jbtndocente.setForeground(new java.awt.Color(255, 255, 255));
        jbtndocente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesFrames/user_icon.png"))); // NOI18N
        jbtndocente.setText("Acceder como docente");
        jbtndocente.setBorder(null);
        jbtndocente.setBorderPainted(false);
        jbtndocente.setContentAreaFilled(false);
        jbtndocente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtndocente.setDefaultCapable(false);
        jbtndocente.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jbtndocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtndocenteActionPerformed(evt);
            }
        });
        jpMenuBar.add(jbtndocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, -20, 330, 100));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        rSLabelHora1.setForeground(new java.awt.Color(0, 0, 0));
        rSLabelHora1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 48)); // NOI18N

        jpMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jpMenuBar1.setToolTipText("");
        jpMenuBar1.setPreferredSize(new java.awt.Dimension(1250, 37));
        jpMenuBar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnEntrada.setBackground(new java.awt.Color(236, 71, 71));
        jbtnEntrada.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 36)); // NOI18N
        jbtnEntrada.setForeground(new java.awt.Color(255, 255, 255));
        jbtnEntrada.setText("Registrar Entrada");
        jbtnEntrada.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnEntrada.setBorderPainted(false);
        jbtnEntrada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnEntrada.setDefaultCapable(false);
        jbtnEntrada.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jbtnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEntradaActionPerformed(evt);
            }
        });
        jpMenuBar1.add(jbtnEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 380, 100));

        jbtnSalida.setBackground(new java.awt.Color(63, 78, 79));
        jbtnSalida.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 36)); // NOI18N
        jbtnSalida.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSalida.setText("Registrar Salida");
        jbtnSalida.setBorder(null);
        jbtnSalida.setBorderPainted(false);
        jbtnSalida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnSalida.setDefaultCapable(false);
        jbtnSalida.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jbtnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalidaActionPerformed(evt);
            }
        });
        jpMenuBar1.add(jbtnSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 380, 100));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesFrames/Asistencias.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(113, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jpMenuBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE))
            .addComponent(jpMenuBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jbtnadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnadminActionPerformed
        
    }//GEN-LAST:event_jbtnadminActionPerformed

    private void jbtndocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtndocenteActionPerformed
        
    }//GEN-LAST:event_jbtndocenteActionPerformed

    private void jbtnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEntradaActionPerformed
        registrarEntrada();
    }//GEN-LAST:event_jbtnEntradaActionPerformed

    private void jbtnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalidaActionPerformed
        registrarSalida();
    }//GEN-LAST:event_jbtnSalidaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnEntrada;
    private javax.swing.JButton jbtnSalida;
    private javax.swing.JButton jbtnadmin;
    private javax.swing.JButton jbtndocente;
    private javax.swing.JPanel jpMenuBar;
    private javax.swing.JPanel jpMenuBar1;
    private rojeru_san.RSLabelHora rSLabelHora1;
    // End of variables declaration//GEN-END:variables
}
