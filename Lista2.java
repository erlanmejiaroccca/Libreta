import java.awt.*;
import javax.swing.*;

import javax.swing.table.*;

import data.Libreta;
import data.LibretaData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Lista2 extends JFrame {
    /**
     * Descripción de la clase
     */
    private static final long serialVersionUID = 1L;
    // CRUD Contacto
    int libretaId = 0;
    LibretaData libretaData = new LibretaData();
    String[] libretaColumns = { "Numero de Libros ", "Nombre", "Precio" };
    String[][] libretaMatriz = new String[0][libretaColumns.length];
    DefaultTableModel model = new DefaultTableModel(libretaMatriz, libretaColumns);
    JTable libretaTable = new JTable(model);
    JScrollPane libretaSP = new JScrollPane();

    // CRUD Contacto End

    public Lista2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        // Creando MenuBar y agregando items
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Crud");
        JMenu m2 = new JMenu("Ayuda");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("libretas");
        JMenuItem m12 = new JMenuItem("Libros");
        JMenuItem m19 = new JMenuItem("Salir");

        // CRUD Contacto
        JPanel libretaPanel = new JPanel();
        libretaPanel.setLayout(new BoxLayout(libretaPanel, BoxLayout.Y_AXIS));
        JLabel libretaLblNombre = new JLabel("Ingrese nombre de libreta:");       
        JTextField libretaTxtNombre = new JTextField();
        JButton libretaBtnAdd = new JButton("Add");        
        JButton button;  
       
        button = new JButton("Remove");
                
        libretaSP.setViewportView(libretaTable);
        libretaPanel.add(libretaLblNombre);
        libretaPanel.add(libretaTxtNombre);
        libretaPanel.add(libretaBtnAdd);
        libretaPanel.add(button);
        libretaPanel.add(libretaSP);
        

        libretaBtnAdd.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                libretaId++;
                Libreta d = new Libreta();
                d.setId(libretaId);
                d.setNombre(libretaTxtNombre.getText());
                libretaData.create(d);
                
                List<Libreta> miLista = libretaData.list();
                libretaMatriz = new String[miLista.size()][libretaColumns.length];
                for (int i = 0; i < miLista.size(); i++) {
                    libretaMatriz[i][0] = miLista.get(i).getId() + "";
                    libretaMatriz[i][1] = miLista.get(i).getNombre() + "";
                    libretaMatriz[i][2] = miLista.get(i).getId() + "";
                }
                model = new DefaultTableModel(libretaMatriz, libretaColumns);
                libretaTable = new JTable(model);// f5 table
               

                libretaSP.setViewportView(libretaTable);// f5 table

            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // check for selected row first
                if (libretaTable.getSelectedRow() != -1) {
                    String ids = null;
                    int[] row = libretaTable.getSelectedRows();
                    ids = (String) libretaTable.getValueAt(row[0], 0);
                    System.out.println("Table element selected es: " + ids);
                    int id = Integer.parseInt(ids);
                    libretaTxtNombre.setText(" " + id);
                    
                    
                    // remove selected row from the model
                    model.removeRow(libretaTable.getSelectedRow());
                    try {
                        libretaData.delete(id);
                    } catch (java.util.ConcurrentModificationException e2) {
                        System.out.println("libreta si exist e2="+e2);
                    }
                    

                   // JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
            }
        });

        // CRUD Contacto End

        // CRUD Producto
        JPanel productoPanel = new JPanel();
        productoPanel.setLayout(new BoxLayout(productoPanel, BoxLayout.Y_AXIS));
        JLabel productoLblNombre = new JLabel("Ingrese los libros:");

        productoPanel.add(productoLblNombre);
        // CRUD Producto End

        // Actions del JFrame
        m11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println(" Libreria");
                JOptionPane.showMessageDialog(null, libretaPanel, "Libreria", JOptionPane.PLAIN_MESSAGE);
            }
        });
        m12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Ir a Libros");
                JOptionPane.showMessageDialog(null, productoPanel, "Libreria", JOptionPane.PLAIN_MESSAGE);
            }
        });
        m19.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        // Actions del JFrame End

        m1.add(m11);
        m1.add(m12);
        m1.add(m19);

        // Creando el panel en la parte inferior y agregando componentes
        JPanel footPanel = new JPanel();
        JLabel footLblCopy = new JLabel("Proyecto");
        footPanel.add(footLblCopy);

        // Agregar componentes al marco.
        add(BorderLayout.NORTH, mb);
        add(BorderLayout.SOUTH, footPanel);

    }

    public static void main(String args[]) {
        // Creando el Marco
        Lista2 ex = new Lista2();
        ex.setVisible(true);
    }

}