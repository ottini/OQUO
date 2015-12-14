/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base_de_Datos;

import GUI.Usuario;

/**
 *
 * @author lambda
 */
public class LoginSQL {
    
    
    //-------------Usuario
    Usuario user;
    //------------------------
    
    
    private String usuario;
    private String password;
    
    
    public LoginSQL()
    { 
        //user = new Usuario();
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
    
}
