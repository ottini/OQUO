//****************************************
//PROYECTO PROGRAMACION INTERACTIVA
//Hector Fabio Padilla       - 201323335
//Daniel Eduardo Gaviria     - 201325535 
//Daniel Felipe Garcia       - 201430461
//****************************************
package Base_de_Datos;
import GUI.Sede;
import java.sql.*;
import javax.swing.JOptionPane;


public class IngresarDatos {
    
    Conexion fachada;
    Statement sentencia;
    ConsultarDatos consultar;
    

    public IngresarDatos() {
        fachada = new Conexion();
        consultar = new ConsultarDatos();
    }
    /**
    public int ingresarSede(String nombre_categoria)
    {
        int numeroFilas;
        int id_categoria = consultar.codigo_Nuevo_Registro("categoria");  
        
        String ingresar = "insert into categoria (nombre, id_categoria) " + "values ('" + nombre_categoria + "'," + id_categoria + ");";
        
        try{
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            numeroFilas = sentencia.executeUpdate(ingresar);
            fachada.cerrarConexionABaseDeDatos();
        }
        catch(SQLException e)
        {
            System.out.println("Error de Sql al ingresar una categoria\n");
            numeroFilas=-1;
        }
        return numeroFilas;
    }
    **/
    
    public int ingresarSede(String nombre_s, String direccion_s, String telefono_s, String estado_s, String correo_s, String ciudad_s, String id_s){
        
        int numeroFilas;
        
        String ingresar = "insert into sedes (id_sede, nombre_sede, direccion_sede, telefono_sede, estado_sede, correo_sede, ciudad_sede)"
                + " values('"+ id_s +"', '" + nombre_s + "','" + direccion_s + "','" + telefono_s + "','" + estado_s + "','" + correo_s + "','" + ciudad_s +"');";        
        try{
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            numeroFilas = sentencia.executeUpdate(ingresar);
            fachada.cerrarConexionABaseDeDatos();
        }
        catch(SQLException e)
        {
            System.out.println("Error de Sql al ingresar una sede\n");
            numeroFilas=-1;
        }    
        return numeroFilas;
    }
    
    public int eliminarSede(String id_sede){
    
        int numeroFilas = 0;
        
        int confirmacion = JOptionPane.showConfirmDialog(null, "Seguro desea eliminar la sede " + consultar.nombreSede(id_sede));
        
        if(confirmacion == 0)
        {
            String ingresar = "delete from sedes where id_sede = '" + id_sede + "';";
            try{
                Connection conexion = fachada.conectarBD();
                sentencia = conexion.createStatement();
                numeroFilas = sentencia.executeUpdate(ingresar);
                fachada.cerrarConexionABaseDeDatos();
            }
            catch(SQLException e)
            {
                System.out.println("Error de Sql al ingresar una sede\n");
                numeroFilas=-1;
            }    
            return numeroFilas;
        }
        else{
            return numeroFilas;
        }
    }
    /**
    public int ingresarConfiguracionExamen(String nombreExamen, int codMaestro, int cantPreguntas, String tiempo, String fecha)
    {
        int numeroFilas;
        
        int consecutivoExamen = consultar.codigo_Nuevo_Registro("config_examen"); 
        
        String ingresar ="insert into config_examen (nom_examen, cod_maestro, cant_preguntas, tiempo, fecha, consecutivo_examen) "
                + "values ('" + nombreExamen + "'," + codMaestro + "," + cantPreguntas + ",'" + tiempo + "','" + fecha + "'," + consecutivoExamen + ");";
       
        try{
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            numeroFilas = sentencia.executeUpdate(ingresar);
            fachada.cerrarConexionABaseDeDatos();
        }
        catch(SQLException e)
        {
            System.out.println("Error de Sql al ingresar una configuracion de examen\n");
            numeroFilas=-1;
        }
        
        return numeroFilas;
    }
    
    //No son necesarios los otros parametros, por defecto se debe iniciar con los apuntados, los otros son necesarios a la hora de modificar un registro
    public int ingresarDetalladoExamen(String nombre_pregunta, String nombre_examen)
    { 
        int numeroFilas;
        
        int consecutivo_examen = consultar.consecutivoExamen( nombre_examen );   
        
        int cod_pregunta = consultar.codigoPregunta(nombre_pregunta);
        
        String ingresar ="insert into det_examen (consecutivo_examen, cod_pregunta, estado, resp_alumno, cod_alumno, calificacion, nombre_pregunta) values (" 
                
                         + consecutivo_examen + "," + cod_pregunta + ",'Libre','0', 0, 0,'" + nombre_pregunta + "');";
        
        try{
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            numeroFilas = sentencia.executeUpdate(ingresar);
            fachada.cerrarConexionABaseDeDatos();
        }
        catch(SQLException e)
        {
            System.out.println("Error de Sql al ingresar una configuracion de examen\n");
            numeroFilas=-1;
        }    
        return numeroFilas;
    }
    * **/
}