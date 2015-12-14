package GUI;


import javax.swing.JComboBox;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author neox
 */
public class Sede {
    String nombre_sede;
    String direccion_sede;
    String telefono_sede;
    String correo_sede;
    boolean estado_sede;
    int id_sede;

    public Sede() {
    }

    
    
    public void habilitarGestion(JTextField nombre_s,JTextField direccion_s, JTextField telefono_s, JComboBox combo_s, JTextField correo_s, JTextField ciudad_s, JTextField id_s,int id_sede){
        nombre_s.setEditable(true);
        nombre_s.setText("");
        direccion_s.setEditable(true);
        direccion_s.setText("");
        telefono_s.setEditable(true);
        telefono_s.setText("");
        combo_s.setEnabled(true);
        correo_s.setEditable(true);
        correo_s.setText("");
        ciudad_s.setEditable(true);
        ciudad_s.setText("");
        id_s.setText("");
        id_s.setText(""+id_sede);
    }
    
    public void reestablecerGestion (JTextField nombre_s,JTextField direccion_s, JTextField telefono_s, JComboBox combo_s, JTextField correo_s, JTextField ciudad_s, JTextField id_s){
        nombre_s.setEditable(false);
        nombre_s.setText("");
        direccion_s.setEditable(false);
        direccion_s.setText("");
        telefono_s.setEditable(false);
        telefono_s.setText("");
        combo_s.setSelectedIndex(0);
        combo_s.setEnabled(false);
        correo_s.setEditable(false);
        correo_s.setText("");
        ciudad_s.setEditable(false);
        ciudad_s.setText("");
        id_s.setText("");
    }
}
