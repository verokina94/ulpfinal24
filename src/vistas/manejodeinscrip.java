/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import accesoadatos.AlumnoData;
import accesoadatos.InscripcionData;
import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materias;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author santi
 */
public class manejodeinscrip extends javax.swing.JInternalFrame {

    
    private DefaultTableModel modelo = new DefaultTableModel();
    Materias materiaSelec;
    Alumno alumnoSelec;
        
    
    public manejodeinscrip() {
        initComponents();
        armarcabezera();
        cargarCombo();
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RadioGrupo = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableinscripcion = new javax.swing.JTable();
        jinscribir = new javax.swing.JButton();
        janularins = new javax.swing.JButton();
        jsalir2 = new javax.swing.JButton();
        JRInscriptas = new javax.swing.JRadioButton();
        JRNoinscriptas = new javax.swing.JRadioButton();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setText("formulario de inscripcion");

        jLabel2.setText("Seleccione alumno");

        jComboBox1.setSelectedItem(getName());

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel3.setText("listado de materias");

        jTableinscripcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableinscripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableinscripcionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableinscripcion);

        jinscribir.setText("Inscribir");
        jinscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jinscribirActionPerformed(evt);
            }
        });

        janularins.setText("Anular Inscripcion");
        janularins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                janularinsActionPerformed(evt);
            }
        });

        jsalir2.setText("Salir");
        jsalir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsalir2ActionPerformed(evt);
            }
        });

        JRInscriptas.setText("Materias Inscriptas");
        JRInscriptas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JRInscriptasMouseClicked(evt);
            }
        });
        JRInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRInscriptasActionPerformed(evt);
            }
        });

        JRNoinscriptas.setText("Materias NO Inscriptas");
        JRNoinscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRNoinscriptasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jinscribir)
                .addGap(63, 63, 63)
                .addComponent(janularins, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jsalir2)
                .addGap(39, 39, 39))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(JRInscriptas)
                        .addGap(94, 94, 94)
                        .addComponent(JRNoinscriptas))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jLabel1)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JRInscriptas)
                    .addComponent(JRNoinscriptas))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jinscribir)
                    .addComponent(janularins)
                    .addComponent(jsalir2))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JRInscriptasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JRInscriptasMouseClicked
       
    }//GEN-LAST:event_JRInscriptasMouseClicked

    private void JRInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRInscriptasActionPerformed
        // CLICK PARA OBTENER MATERIAS CURSADAS
        limpiarTabla();
        cargarTabla1();
       
    }//GEN-LAST:event_JRInscriptasActionPerformed

    private void JRNoinscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRNoinscriptasActionPerformed
        // CLICK PARA OBTENER MATERIAS <NO> CURSADAS
        limpiarTabla();
        cargarTabla2();
    
    }//GEN-LAST:event_JRNoinscriptasActionPerformed

    private void jinscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jinscribirActionPerformed
        // BOTON INSCRIBIR > no se puede probar porque falta SQL de matNoInscriptas
        
        // se creo un nuevo Constructor de 'Inscripcion' para este llamado a la accion
        Inscripcion insSelec = new Inscripcion (alumnoSelec, materiaSelec,0);
        InscripcionData ins = new InscripcionData () ;
        ins.guardarInscripcion(insSelec);
        
    }//GEN-LAST:event_jinscribirActionPerformed

    private void jTableinscripcionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableinscripcionMouseClicked
                   // CLICK SOBRE LA TABLA 
       // de la fila seleccionada tomar cada elemento de cada columna 
       int filaSelec = jTableinscripcion.getSelectedRow() ; //indice fila seleccionada 
                                                       // sino hay ninguna selec devuelve -1
       //corroborar que hay una seleccion
       if (filaSelec!=-1) {
           //se crea cada ATRIBUTO de la clase 'Materias'
           int id = (int) jTableinscripcion.getValueAt(filaSelec, 0) ;
           String nombre = (String) jTableinscripcion.getValueAt(filaSelec, 1) ;
           int anio = (int) jTableinscripcion.getValueAt(filaSelec, 2) ;
           //crear OBJETO clase 'Materias' con los atributos traidos de la tabla
           materiaSelec= new Materias (id, nombre, anio, true);
           
       }
       
    }//GEN-LAST:event_jTableinscripcionMouseClicked

    private void janularinsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_janularinsActionPerformed
        // BOTON ANULAR INSCRIPCION 
       Inscripcion insSelec = new Inscripcion (alumnoSelec, materiaSelec,0);
       InscripcionData ins = new InscripcionData () ;
       ins.borrarInscripcionMateriaAlumno(alumnoSelec.getIdAlumno(), materiaSelec.getIdMateria());
        
    }//GEN-LAST:event_janularinsActionPerformed

    private void jsalir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsalir2ActionPerformed
        // BOTON SALIR 
        this.dispose();
    }//GEN-LAST:event_jsalir2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JRInscriptas;
    private javax.swing.JRadioButton JRNoinscriptas;
    private javax.swing.ButtonGroup RadioGrupo;
    private javax.swing.JComboBox<Alumno> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableinscripcion;
    private javax.swing.JButton janularins;
    private javax.swing.JButton jinscribir;
    private javax.swing.JButton jsalir2;
    // End of variables declaration//GEN-END:variables

private void armarcabezera(){
    
    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("año");
    
    jTableinscripcion.setModel(modelo);

}

private void cargarCombo(){
    //cargamos el combo box para que se vean las diferentes opciones de alumnos
     AlumnoData alu = new AlumnoData();
     List<Alumno> listar = alu.listarAlumnos();
     
     for (Alumno completo: listar) {
         
        // solucionar la vista en el combo box
        jComboBox1.addItem(completo);
        
     }
     
    }

private void cargarTabla1() {
    
    RadioGrupo.add(JRInscriptas);
    RadioGrupo.add(JRNoinscriptas);
    
   // LIMPIAR EL GRUPO DE OPCIONES
   // RadioGrupo.clearSelection();
    
    AlumnoData alu = new AlumnoData();
    alumnoSelec = (Alumno) jComboBox1.getSelectedItem();
    InscripcionData ins = new InscripcionData();
    int id = alumnoSelec.getIdAlumno();
    
   
    
    List<Materias> lista = ins.obtenerMateriasCursadas(id);
       //limpiarTabla();
       for (Materias obj : lista) {
          
          modelo.addRow(new Object [] {obj.getIdMateria(), obj.getNombre(), obj.getAnio() }); 
       }
      
}

private void cargarTabla2() {
    RadioGrupo.add(JRInscriptas);
    RadioGrupo.add(JRNoinscriptas);
    
   // LIMPIAR EL GRUPO DE OPCIONES
   // RadioGrupo.clearSelection();
    
    AlumnoData alu = new AlumnoData();
    alumnoSelec = (Alumno) jComboBox1.getSelectedItem();
    InscripcionData ins = new InscripcionData();
    int id = alumnoSelec.getIdAlumno();
    
    limpiarTabla();
    List<Materias> lista = ins.obtenerMateriasNoCursadas(id);
       for (Materias obj : lista) {
          
          modelo.addRow(new Object [] {obj.getIdMateria(), obj.getNombre(), obj.getAnio() }); 
       }

    
}


   private void limpiarTabla() {
       
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        
    }
}
