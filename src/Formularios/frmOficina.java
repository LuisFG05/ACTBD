/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Formularios;

import conection.Consultas;
import javax.swing.JComboBox;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
import javax.swing.JOptionPane;

/**
 *
 * @author luill
 */
public class frmOficina extends javax.swing.JDialog {

    /**
     * Creates new form frmEstancia
     */
    public frmOficina(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        NewRegistro = new javax.swing.JButton();
        DeleteRegistro = new javax.swing.JButton();
        UpdateRegistro = new javax.swing.JButton();
        SelectRegistro = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TxtNumOficina = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        combobox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        TxtNumZona = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaImpresiones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        NewRegistro.setText("Nuevo Registro");
        NewRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewRegistroActionPerformed(evt);
            }
        });

        DeleteRegistro.setText("Eliminar Registro");
        DeleteRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteRegistroActionPerformed(evt);
            }
        });

        UpdateRegistro.setText("Actualizar Registro");
        UpdateRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateRegistroActionPerformed(evt);
            }
        });

        SelectRegistro.setText("Consultar Registros");
        SelectRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectRegistroActionPerformed(evt);
            }
        });

        jLabel1.setText("Número de oficina");

        jLabel2.setText("Llave");

        combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));
        combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxActionPerformed(evt);
            }
        });

        jLabel3.setText("Número de Zona");

        TablaImpresiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id_oficina", "hay_llave", "id_zona", "nombre_zona"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaImpresiones);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NewRegistro)
                .addGap(52, 52, 52)
                .addComponent(DeleteRegistro)
                .addGap(34, 34, 34)
                .addComponent(UpdateRegistro)
                .addGap(49, 49, 49)
                .addComponent(SelectRegistro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNumOficina, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(TxtNumZona, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NewRegistro)
                    .addComponent(DeleteRegistro)
                    .addComponent(UpdateRegistro)
                    .addComponent(SelectRegistro))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(TxtNumOficina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(jLabel3)
                        .addGap(32, 32, 32)
                        .addComponent(TxtNumZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxActionPerformed
       String[] opciones = {"Sí", "No"};
        combobox = new JComboBox<>(opciones);
        // TODO add your handling code here:
    }//GEN-LAST:event_comboboxActionPerformed

    private void NewRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewRegistroActionPerformed
      String idOficina = TxtNumOficina.getText().trim();
String seleccion = (String) combobox.getSelectedItem(); // Obtener el valor seleccionado del JComboBox
boolean opciones = seleccion.equals("Sí"); // Convertir el valor seleccionado a un valor booleano
String idZona = TxtNumZona.getText().trim();

// Verificar si ambos campos no están vacíos
if (!idOficina.isEmpty() && !idZona.isEmpty()) {
    try {
        // Convertir el idZona a entero
        int id = Integer.parseInt(idZona); 
        
        // Convertir el idOficina a entero
        int idOficinaInt = Integer.parseInt(idOficina);
        
        // Llamar al método insertarOficina con los datos proporcionados
        Consultas consultas = new Consultas();
        consultas.insertarOficina(idOficinaInt, opciones, id); // Pasamos el valor booleano como argumento
        
        // Limpiar los campos de texto después de insertar la oficina
        TxtNumOficina.setText("");
        combobox.setSelectedIndex(-1); // Limpiar la selección del JComboBox
        TxtNumZona.setText("");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El ID de la oficina y el ID de la zona deben ser números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
    }
} else {
    JOptionPane.showMessageDialog(this, "Por favor, ingrese tanto el ID de la oficina como el ID de la zona.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
}


// TODO add your handling code here:
    }//GEN-LAST:event_NewRegistroActionPerformed

    private void DeleteRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteRegistroActionPerformed
       String idOficina = TxtNumOficina.getText().trim();
    
    // Verificar si el campo de ID no está vacío
    if (!idOficina.isEmpty()) {
        try {
            // Convertir el idCiudad a entero
            int id = Integer.parseInt(idOficina);
            
            // Llamar al método borrarCiudad con el ID proporcionado
            Consultas consultas = new Consultas();
            consultas.borrarOficina(id);
            
            // Limpiar el campo de ID después de borrar la ciudad
            TxtNumOficina.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de la oficina debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID de la oficina que desea borrar.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
    } 
       // TODO add your handling code here:
    }//GEN-LAST:event_DeleteRegistroActionPerformed

    private void UpdateRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateRegistroActionPerformed
        String idOficina = TxtNumOficina.getText().trim();
String seleccion = (String) combobox.getSelectedItem(); // Obtener el valor seleccionado del JComboBox
boolean opciones = seleccion.equals("Sí"); // Convertir el valor seleccionado a un valor booleano
String idZona = TxtNumZona.getText().trim();

// Verificar si ambos campos no están vacíos
if (!idOficina.isEmpty() && !idZona.isEmpty()) {
    try {
        // Convertir el idZona a entero
        int id = Integer.parseInt(idZona); 
        
        // Convertir el idOficina a entero
        int idOficinaInt = Integer.parseInt(idOficina);
        
        // Llamar al método actualizarOficina con los datos proporcionados
        Consultas consultas = new Consultas();
        consultas.actualizarOficina(idOficinaInt, opciones, id); // Pasamos el valor booleano como argumento
        
        // Limpiar los campos de texto después de actualizar la oficina
        TxtNumOficina.setText("");
        combobox.setSelectedIndex(-1); // Limpiar la selección del JComboBox
        TxtNumZona.setText("");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El ID de la oficina y el ID de la zona deben ser números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
    }
} else {
    JOptionPane.showMessageDialog(this, "Por favor, ingrese tanto el ID de la oficina como el ID de la zona.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
}
// TODO add your handling code here:
    }//GEN-LAST:event_UpdateRegistroActionPerformed

    private void SelectRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectRegistroActionPerformed
      Consultas consultas = new Consultas();
ResultSet rs = consultas.consultarOficina(); // Llamar al método consultarOficina en lugar de consultarZona

DefaultTableModel modelo = new DefaultTableModel();
modelo.addColumn("ID Oficina"); // Cambiar el nombre de la columna de ID
modelo.addColumn("Hay Llave"); // Agregar una columna para mostrar si hay llave
modelo.addColumn("ID Zona");
modelo.addColumn("Nombre Zona");

// Verificar si se obtuvieron resultados
if (rs != null) {
    try {
        // Limpiar el modelo antes de agregar nuevos datos
        modelo.setRowCount(0);
        
        // Iterar sobre los resultados del ResultSet y agregarlos al modelo
        while (rs.next()) {
            int idOficina = rs.getInt("id_oficina");
            boolean hayLlave = rs.getBoolean("hay_llave");
            int idZona = rs.getInt("id_zona");
            String nombreZona = rs.getString("nombre_zona");
            modelo.addRow(new Object[]{idOficina, hayLlave, idZona, nombreZona});
        }
        
        // Establecer el modelo en la tabla
        TablaImpresiones.setModel(modelo);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al procesar los resultados de la consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
} else {
   JOptionPane.showMessageDialog(this, "No se encontraron resultados.", "Información", JOptionPane.INFORMATION_MESSAGE);
}
  // TODO add your handling code here:
    }//GEN-LAST:event_SelectRegistroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmOficina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmOficina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmOficina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmOficina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmOficina dialog = new frmOficina(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteRegistro;
    private javax.swing.JButton NewRegistro;
    private javax.swing.JButton SelectRegistro;
    private javax.swing.JTable TablaImpresiones;
    private javax.swing.JTextField TxtNumOficina;
    private javax.swing.JTextField TxtNumZona;
    private javax.swing.JButton UpdateRegistro;
    private javax.swing.JComboBox<String> combobox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
