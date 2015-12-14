/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base_de_Datos;

import java.sql.*;


//-------------------------------------------------------------------
public class Conexion {

    private String urlBD, url, usuario, pwd;
    private Connection conexion;

    public Conexion() {
        urlBD = "jdbc:postgresql://localhost:5432/";
        url = "jdbc:postgresql://localhost:5432/Flash_DB";
        usuario = "postgres";
        pwd = "qwerty";
    }

    public Connection conectarBD() {

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("El Driver fue Cargado con exito");
        } catch (Exception e) {
            System.out.println("Fue Imposible Cargar el Driver.");
        }

        try {
            conexion = DriverManager.getConnection(urlBD, usuario, pwd);
            System.out.println("Conexion Abierta");
            return conexion;
        } catch (Exception e) {
            System.out.println("Fue imposible establecer la conextion.");
            return null;
        }

    }

    public void cerrarConexionABaseDeDatos() {
        try {
            conexion.close();
        } catch (Exception e) {
            System.out.println("Fue imposible cerrar la conexion.");
        }
    }
}
