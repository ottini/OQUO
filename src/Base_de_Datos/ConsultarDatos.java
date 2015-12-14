//****************************************
//MIO DANIEL GAVIRIA
//PROYECTO PROGRAMACION INTERACTIVA
//Hector Fabio Padilla       - 201323335
//Daniel Eduardo Gaviria     - 201325535 
//Daniel Felipe Garcia       - 201430461
//****************************************
package Base_de_Datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConsultarDatos {
    
    private Conexion fachada;
    
    private ResultSet resultado;

    public ConsultarDatos() {
        
        this.fachada = new Conexion();
        
    }
    
      
    //Actualiza un comboBox N, segun el nombre de la tabla, reduce codigo de metodos que actualizan CB por cada CB 
    //( En especifico reduce consultarCategorias y consultarExamenesDisponibles en 1 )
    public void consultar_Actualizar_Categoria_Examen(String nombre_Tabla, JComboBox comboBox_Actualizar) { 
        
        comboBox_Actualizar.removeAllItems();
        
        comboBox_Actualizar.addItem("Seleccione");
        
        String consulta = "select * from " + nombre_Tabla + ";";
        
        Statement sentencia;
        
        try {
            Connection conexion = fachada.conectarBD();
            
            sentencia = conexion.createStatement();
            
            resultado = sentencia.executeQuery(consulta);
            
            fachada.cerrarConexionABaseDeDatos();
                
            while(resultado.next()) {
                
                comboBox_Actualizar.addItem(resultado.getString(1));
                
            }
            
        } 
        
        catch (SQLException ex) {
            
            System.out.println("Error al extraer la información de la Base de Datos");
            
        } 
        
    }
    
    public void consultar_Configuracion(String nombreExamen, JLabel nombre, JLabel codMaestro, JLabel cantPreguntas, JLabel fecha)
    {
        String consulta = "select * from config_examen where nom_examen = '" + nombreExamen + "';";
        Statement sentencia;
        try 
        {
            
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next())
            {
                
                nombre.setText(resultado.getString(1));
                
                codMaestro.setText(resultado.getString(2));
                
                cantPreguntas.setText(resultado.getString(3));
                
                fecha.setText(resultado.getString(5)); 
             
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Error al extraer la información de la Base de datos");
        }
    }
    
    public int codigoDeExamen(String nombreExamen)
    {
        int codigoExamen = 0;
        
        String consulta = "select * from config_examen where nom_examen = '" + nombreExamen + "';";
        
        Statement sentencia;
        
        try 
        {   

            Connection conexion = fachada.conectarBD();
            
            sentencia = conexion.createStatement();
            
            resultado = sentencia.executeQuery(consulta);
            
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next())
            {
                codigoExamen = Integer.parseInt( resultado.getString(6) );
            }
       
        } 
        catch (SQLException ex) 
        {
            
            System.out.println("Error al extraer la información de la Base de datos");
            
        }
        
        return codigoExamen;
    }
    
////    
////    //Reduce metodos para mostrar preguntas 
////    //( En especifico reduce mostrarPreguntasPorCategoria y mostrarPreguntasParaConfiguracionExamen en 1 )
////    //ConfiguracionExamen : Reduci un ciclo no necesario, acceder a tabla pregunta por el nombre de la misma
////    //Habiendo accedido a det_examen donde tambien esta el nombre no es necesario, pocas palabras consulta2, resultado2 no se necesitan  
////    public void consultar_Actualizar_Preguntas(String nombre_Categoria_Examen, String nombre_Tabla, JComboBox comboBox_Actualizar)
////    { 
////     
////            comboBox_Actualizar.removeAllItems(); 
////            
////            comboBox_Actualizar.addItem("Seleccione");
////            
////            int columna_Buscar, indice_Columna;         
////            
////            String consulta; 
////            
////            
////            //El if-else puede hacer ver al metodo un poco largo, pero mas largo es escribir dos metodos
////            if( nombre_Tabla.equals( "pregunta" ) )  
////            {
////                
////                indice_Columna = 9; 
////                
////                columna_Buscar = this.idCategoria(nombre_Categoria_Examen);
////                 
////                consulta = "select * from " + nombre_Tabla + " where id_categoria = '" + columna_Buscar + "';";
////                
////            }
////
////            else {
////
////                indice_Columna = 7;
////                
////                columna_Buscar = this.codigoDeExamen(nombre_Categoria_Examen);
////                
////                consulta = "select * from " + nombre_Tabla + " where consecutivo_examen = '" + columna_Buscar + "';";
////            
////            }
//// 
////            Statement sentencia;
////
////            try 
////            {   
////               
////                Connection conexion = fachada.conectarBD();
////                
////                sentencia = conexion.createStatement();
////                
////                resultado = sentencia.executeQuery(consulta);
////                
////                fachada.cerrarConexionABaseDeDatos();
////
////                while(resultado.next())
////                {
////                    
////                    comboBox_Actualizar.addItem( resultado.getString( indice_Columna ));
////                    
////                } 
////                
////            }
////            
////            catch (SQLException e)
////            {
////                
////                System.out.println("Error al extraer la información de la Base de datos");
////                
////            }
////     
////    }
    
    public void consultar_Actualizar_Detallado(String nombre_Examen, ArrayList<JLabel> jLabels_Actualizar) {

        int consecutivo_Examen = consecutivoExamen( nombre_Examen );
        
        String consulta = "select * from det_examen where consecutivo_examen = '" + consecutivo_Examen + "';";
         
        Statement sentencia;
        
        try 
        {
            
            Connection conexion = fachada.conectarBD();
            
            sentencia = conexion.createStatement();
            
            resultado = sentencia.executeQuery(consulta);
            
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next())
            {
                
                jLabels_Actualizar.get(0).setText( resultado.getString(5) );
                
                jLabels_Actualizar.get(1).setText( resultado.getString(4) );
                
                jLabels_Actualizar.get(2).setText( resultado.getString(3) );
                
                jLabels_Actualizar.get(3).setText( resultado.getString(6) ); 
                
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer la información de la Base de datos");
        }

        
        
    }
    
    public void consultar_Actualizar_Pregunta(String nombre_Pregunta, JLabel opcion_Correcta_Pregunta) {
    
         String consulta = "select * from pregunta where nombre_pregunta = '" + nombre_Pregunta + "';";
         
         Statement sentencia;
        
        try 
        {
            
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next())
            {
                opcion_Correcta_Pregunta.setText( resultado.getString(7) ); 
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer la información de la Base de datos");
        }
    
    } 
     
    public void consultarEnunciadoPregunta(String nombre, JTextArea areaTexto)
    {
        String consulta = "select * from pregunta where nombre_pregunta = '" + nombre + "';";
        
        Statement sentencia;
        
        try 
        {
            
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next())
            {
                areaTexto.setText(resultado.getString(2));
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer la información de la Base de datos");
        }
    }
    
    //DESARROLLO1 --> CANTIDAD SEDES.  
    public int cantidadSedes() {
        
        int id_nueva_sede = 0;
         
        String consulta = "select count (*) from sedes;" ; 
        
        Statement sentencia;
        
        try {
            
            Connection conexion = fachada.conectarBD();
            
            sentencia = conexion.createStatement();
            
            resultado = sentencia.executeQuery( consulta );
            
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next()) {
                id_nueva_sede = Integer.parseInt(resultado.getString(1)) + 1;
            }
        }
        catch(SQLException e) {
            System.out.println("Error al extraer la información de la Base de datos");   
        }
        return id_nueva_sede;  
    }
    
    String nombreSede(String id_sede){
        
            String respuesta = "";
            String consulta= "select nombre_sede from sedes where id_sede = '" + id_sede + "';";
            Statement sentencia;
        try 
        {
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next())
            {
                respuesta = resultado.getString(1);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer la información de la tabla categoria");
        }
        return respuesta;
    }
            
    
 
    public void datosParaModificarSede(String id_Sede, JTextField nombre_s, JTextField direccion_s, JTextField telefono_s, JComboBox estado_s, JTextField correo_s, JTextField ciudad_s, JTextField id_s)
    {
        String consulta = "select * from sedes where id_sede = '" + id_Sede + "';";
        Statement sentencia;
        try 
        {
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next())
            {
                nombre_s.setText(resultado.getString(2));
                direccion_s.setText(resultado.getString(3));
                telefono_s.setText(resultado.getString(4));
                String estadoActual = resultado.getString(5);
                if(estadoActual.equalsIgnoreCase("Activa"))
                {
                    estado_s.setSelectedIndex(1);
                }
                else
                {
                    estado_s.setSelectedIndex(2);
                }    
                correo_s.setText(resultado.getString(6));
                ciudad_s.setText(resultado.getString(7));
                id_s.setText(id_Sede);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer la información de la tabla categoria");
        }
    }
    
    
    public int consecutivoExamen(String nombre_examen)
    {
        
        int consecutivo = -1;
        
        String consulta = "select * from config_examen where nom_examen = '" + nombre_examen + "';";
        
        Statement sentencia;
        
        try 
        {
            
            Connection conexion = fachada.conectarBD();
            
            sentencia = conexion.createStatement();
            
            resultado = sentencia.executeQuery(consulta);
            
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next())
            {
                consecutivo = Integer.parseInt(resultado.getString(6));
            }
             
        }
        
        catch(SQLException e)
        {
            
            System.out.println("Error al extraer la información de la tabla categoria");
            
        }
        
        return consecutivo;
    }
    
    
    public int codigoPregunta(String nombrePregunta)
    {
        int codPregunta = -1;
        String consulta = "select * from pregunta where nombre_pregunta = '" + nombrePregunta + "';";
        Statement sentencia;
        try 
        {
            
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next())
            {
                codPregunta = Integer.parseInt(resultado.getString(1));
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer la información de la tabla categoria");
        }
        return codPregunta;
    }
    
    public ArrayList<String> consultar_Entregar_Preguntas_Examen(String nombre_Examen) {
    
        ArrayList<String> preguntas_Examen = new ArrayList<>();

        int consecutivo_Examen = consecutivoExamen(nombre_Examen );
        
        int codigoPregunta; 
        
        String consulta_Detallado = "select * from det_examen where consecutivo_examen = '" + consecutivo_Examen + "';";
        
        Statement sentencia_Detallado;
        
        ResultSet resultado_Preguntas;
        
        try 
        {   
            Connection conexion = fachada.conectarBD();
            
            sentencia_Detallado = conexion.createStatement();
            
            resultado = sentencia_Detallado.executeQuery( consulta_Detallado );
            
            fachada.cerrarConexionABaseDeDatos();
            
            while( resultado.next() )
            {   
                
                codigoPregunta = Integer.parseInt( resultado.getString(2) );
                
                String consulta_Pregunta = "select * from pregunta where cod_pregunta = '" + codigoPregunta + "';";
                
                Statement sentencia_Preguntas;
                
                conexion = fachada.conectarBD();
                
                sentencia_Preguntas = conexion.createStatement();
                
                resultado_Preguntas = sentencia_Preguntas.executeQuery( consulta_Pregunta );
                
                fachada.cerrarConexionABaseDeDatos();
                
                while( resultado_Preguntas.next() )
                {
                    
                    String registro_Actual = resultado_Preguntas.getString(1) + "-" + resultado_Preguntas.getString(2) + "-" + resultado_Preguntas.getString(3) + "-" + 
                                             resultado_Preguntas.getString(4) + "-" + resultado_Preguntas.getString(5) + "-" + resultado_Preguntas.getString(6) + "-" + 
                                                                                      resultado.getString(3) + "-" + consecutivo_Examen;
    
                    preguntas_Examen.add( registro_Actual ); 
                    
                }
            }  
        }
        catch (SQLException e)
        {
            System.out.println("Error al extraer la información de la Base de datos");
        }
        
        return preguntas_Examen;
    
    }
    
    public void actualizar_Estado_Pregunta(String pregunta_Seleccionada, String estado) {
        
        StringTokenizer separador = new StringTokenizer(pregunta_Seleccionada, "-");
         
        Integer codigo_Pregunta = Integer.parseInt( separador.nextToken() );
        
        separador.nextToken();/**/separador.nextToken();/**/separador.nextToken();/**/separador.nextToken();/**/separador.nextToken();/**/separador.nextToken();
        
        Integer consecutivo_Examen = Integer.parseInt( separador.nextToken() );

        String actualizacion = "update det_examen set estado = " + estado + " where consecutivo_examen = " + consecutivo_Examen + " and cod_pregunta = " + codigo_Pregunta;
        
        Statement sentencia;
        
        try 
        {
            
            Connection conexion = fachada.conectarBD();
            
            sentencia = conexion.createStatement();
            
            resultado = sentencia.executeQuery(actualizacion);
            
            fachada.cerrarConexionABaseDeDatos();

            
        }
        
        catch(SQLException e)
        {
            
            System.out.println("Error al actualizar estado");
            
        }
    
    } 
    
    public boolean comprobarRegistrosIguales(String nuevaCategoria) {

        boolean iguales = true;
        int filas;
        String consulta = "select * from categoria;";
        String consulta2 = "select count (*) from categoria;";

        Statement sentencia;
        Statement sentencia2;
        ResultSet contador;

        try {
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
            sentencia2 = conexion.createStatement();
            contador = sentencia2.executeQuery(consulta2);
            fachada.cerrarConexionABaseDeDatos();
            
            contador.next();
            filas = Integer.parseInt(contador.getString(1));

            if (filas < 1) {
                return iguales;
            } else {

                while (resultado.next()) {

                    String categoria = resultado.getString(1);

                    if (categoria.equalsIgnoreCase(nuevaCategoria)) {
                        iguales = false;
                        break;
                    }

                }

            }

        } catch (SQLException ex) {

            System.out.println("Error al extraer la información de la Base de Datos");

        }
        return iguales;
    }
    
    public String retornoTiempo(String nombreExamen)
    {
        String tiempo = "";
        String consulta = "select * from config_examen where nom_examen = '" + nombreExamen + "';";
        Statement sentencia;
        try 
        {
            
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next())
            {
                tiempo = resultado.getString(4);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer la información de la base de datos");
        }
        return tiempo;
    }
    
    public String nombrePregunta(String enunciado){
        
        String consulta = "select * from pregunta where enunciado = '" + enunciado + "';";
        
        Statement sentencia;
        try 
        {
            
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next())
            {
                consulta = resultado.getString(9);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer la información de la tabla categoria");
        }
        return consulta;
    }
    
    public void insertarRespuesta(String respuesta, String enunciado, String nombreExamen, int codAlumno){
        
        
        int consecutivo = consecutivoExamen(nombreExamen);
        
        String nombrePregunta = nombrePregunta(enunciado);
        
        String consulta = "update det_examen set resp_alumno = '" + respuesta + "', estado = 'Respondida', cod_alumno = " + codAlumno + " where nombre_pregunta = '" + nombrePregunta + "' and consecutivo_examen = " + consecutivo + ";";
        Statement sentencia;
        try 
        {
            
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer la información de la base de datos");
        }
    }
    
    //AGREGADO 10-6-15
    public boolean existenCategorias()
    {
        int contador = 0;
        String consulta = "select * from categoria;";
        Statement sentencia;
        try 
        {
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
            while (resultado.next()) 
            {
                contador++;
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer la información de la tabla categoria");
        }
        
        if(contador != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int preguntasSuficientesParaCreacionExamen()
    {
        int contador = 0;
        String consulta = "select * from pregunta;";
        Statement sentencia;
        try 
        {
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
            while (resultado.next()) 
            {
                contador++;
            }
        }
        catch(SQLException e)
        {
             System.out.println("Error al extraer la información de la tabla pregunta");
        }
        return contador;
    }
    
    public int consultar_Cantidad_Preguntas_Examen(String nombre_Examen) 
    {
     
        int cantidad_Preguntas = 0;
        
        int consecutivo_Examen = this.consecutivoExamen(nombre_Examen);
        
        String consulta = "select * from config_examen where consecutivo_examen = '" + consecutivo_Examen + "';";
        
        Statement sentencia;
        
        try 
        {
             
            Connection conexion = fachada.conectarBD();
            
            sentencia = conexion.createStatement();
            
            resultado = sentencia.executeQuery(consulta);
            
            fachada.cerrarConexionABaseDeDatos();
            
            while(resultado.next())
            {
                
                cantidad_Preguntas = Integer.parseInt(resultado.getString(3));
                
            }
            
        }
        
        catch(SQLException e)
        {
            
            System.out.println("Error al extraer la información de la tabla categoria");
            
        }
        
        return cantidad_Preguntas;
    
    }
    
    public boolean hayExamenes()
    {
        int contador = 0;
        String consulta = "select * from config_examen;";
        Statement sentencia;
        try 
        {
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
            while (resultado.next()) 
            {
                contador++;
            }
        }
        catch(SQLException e)
        {
             System.out.println("Error al extraer la información de la tabla pregunta");
        }
        if(contador > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void insertarRespuesta(String respuesta, String enunciado, String nombreExamen){
        
        
        int consecutivo = consecutivoExamen(nombreExamen);
        
        String nombrePregunta = nombrePregunta(enunciado);
        
        String consulta = "update det_examen set resp_alumno = '" + respuesta + "' where nombre_pregunta = '" + nombrePregunta + "' and consecutivo_examen = " + consecutivo + ";";
        Statement sentencia;
        try 
        {
            
            Connection conexion = fachada.conectarBD();
            sentencia = conexion.createStatement();
            sentencia.executeQuery(consulta);
            fachada.cerrarConexionABaseDeDatos();
            
        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer la información de la base de datos");
        }
    }
}