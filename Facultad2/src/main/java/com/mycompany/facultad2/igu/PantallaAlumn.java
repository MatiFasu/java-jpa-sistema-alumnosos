
package com.mycompany.facultad2.igu;

import com.mycompany.facultad2.logica.Controladora;
import com.mycompany.facultad2.logica.Curso;
import com.mycompany.facultad2.logica.Usuario;
import static java.awt.SystemColor.control;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class PantallaAlumn extends javax.swing.JFrame {

    Controladora control;
    Usuario usuario;
    
    public PantallaAlumn(Controladora control, Usuario usu) {
        initComponents();
        this.control = control;
        this.usuario = usu;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCurso = new javax.swing.JTable();
        btnInscribirse = new javax.swing.JButton();
        btnDejarCurso = new javax.swing.JButton();
        btnCargarTabla = new javax.swing.JButton();
        btnVerMaterias = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        jLabel1.setText("Visualizacion Alumno");

        tablaCurso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaCurso);

        btnInscribirse.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnInscribirse.setText("Inscribirse");
        btnInscribirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscribirseActionPerformed(evt);
            }
        });

        btnDejarCurso.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnDejarCurso.setText("Dejar Curso");
        btnDejarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDejarCursoActionPerformed(evt);
            }
        });

        btnCargarTabla.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnCargarTabla.setText("Cargar Tabla");
        btnCargarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarTablaActionPerformed(evt);
            }
        });

        btnVerMaterias.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnVerMaterias.setText("Ver Materias");
        btnVerMaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMateriasActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCargarTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnInscribirse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDejarCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVerMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(115, 115, 115)
                        .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnInscribirse)
                        .addGap(18, 18, 18)
                        .addComponent(btnDejarCurso)
                        .addGap(18, 18, 18)
                        .addComponent(btnCargarTabla)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerMaterias)
                        .addGap(50, 50, 50)
                        .addComponent(btnSalir)
                        .addGap(63, 63, 63)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInscribirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInscribirseActionPerformed
        if( tablaCurso.getRowCount() > 0 ) {
            // controlo que se haya seleccionado una fila
            if( tablaCurso.getSelectedRow() != -1 ) {
                int id_curso = Integer.parseInt(String.valueOf(tablaCurso.getValueAt(tablaCurso.getSelectedRow(),0)));

                boolean validarInscripcion = control.inscribirAlumno(id_curso, usuario);

                if( validarInscripcion ) {
                    mostrarMensaje("Alumno inscrito correctamente", "Info", "Inscribir Alumno");
                    cargarTabla();
                } else {
                    mostrarMensaje("Alumno no Inscrito por falta de cupo o ya esta inscrito","Error", "Error");
                }

            }
            else {
                mostrarMensaje("No selecciono ningun curso", "Error", "Error");
            }
        }
        else {
            mostrarMensaje("No hay nada en la tabla para seleccionar", "Error", "Error");
        }
    }//GEN-LAST:event_btnInscribirseActionPerformed

    private void btnDejarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDejarCursoActionPerformed
        if( tablaCurso.getRowCount() > 0 ) {
            // controlo que se haya seleccionado una fila
            if( tablaCurso.getSelectedRow() != -1 ) {
                int id_curso = Integer.parseInt(String.valueOf(tablaCurso.getValueAt(tablaCurso.getSelectedRow(),0)));

                boolean dejarCurso = control.dejarCurso(id_curso, usuario);

                if( dejarCurso ) {
                    mostrarMensaje("Alumno dejo el curso correctamente", "Info", "Alumno se dio de baja");
                    cargarTabla();
                } else {
                    mostrarMensaje("No esta inscrito en este curso","Error", "No inscrito");
                }

            }
            else {
                mostrarMensaje("No selecciono ningun curso", "Error", "Error");
            }
        }
        else {
            mostrarMensaje("No hay nada en la tabla para seleccionar", "Error", "Error");
        }
    }//GEN-LAST:event_btnDejarCursoActionPerformed

    private void btnCargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarTablaActionPerformed
        cargarTabla();
    }//GEN-LAST:event_btnCargarTablaActionPerformed

    private void btnVerMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMateriasActionPerformed
        VerMaterias pantallaVerMaterias = new VerMaterias(control, usuario);
        pantallaVerMaterias.setVisible(true);
        pantallaVerMaterias.setLocationRelativeTo(null);

        //this.dispose();
    }//GEN-LAST:event_btnVerMateriasActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        Login login = new Login();
        login.setVisible(true);
        login.setLocationRelativeTo(null);

        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        lblNombre.setText("Bienvenido, " + usuario.getUsuario());
        cargarTabla();
    }//GEN-LAST:event_formWindowOpened

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarTabla;
    private javax.swing.JButton btnDejarCurso;
    private javax.swing.JButton btnInscribirse;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVerMaterias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTable tablaCurso;
    // End of variables declaration//GEN-END:variables

    private void cargarTabla() {
        // definir el modelo que queremos que tenga la tabla
        DefaultTableModel modeloTabla = new DefaultTableModel() {
            
            // que filas y col no sean editables
            @Override
            public boolean isCellEditable (int row, int col) {
                return false;
            }
                   
        };
        
        // nombres de las columnas
        String titulos[] = {"Id", "Materia", "Precio", "Cupo", "Anotados"};
        modeloTabla.setColumnIdentifiers(titulos);
        
        //carga de los datos desde la abse de datos
        List<Curso> listaCurso = control.traerCursos();
        
        // recorrer la lista y mostrar cada uno de los elementos en la tabla
        if( listaCurso != null ) {
            
            for(Curso c : listaCurso) {
                Object[] objeto = {c.getId(), c.getNombre(), c.getPrecio(), c.getCupo(), c.getAnotados()};
                
                modeloTabla.addRow(objeto);
            }
            
        }
        
        tablaCurso.setModel(modeloTabla);
    }
    
    private void mostrarMensaje(String mensaje, String tipo, String titulo) {
        JOptionPane optionPane = new JOptionPane(mensaje);
        
        if( tipo.equals("Info")){
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        }
        else if( tipo.equals("Error") ) {
            optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
        }
        
        JDialog dialog = optionPane.createDialog(titulo);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
}
