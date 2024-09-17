/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class Conexion {
    private final String puerto = "3306";
    private final String db = "db_actividad3";
    private final String urlConexion =String.format("jdbc:mysql://localhost:%s/%s?serverTimezone=UTC",puerto, db);
    private final String usuario = "root";
    private final String contra = "2024";
    private final String jdbc = "com.mysql.cj.jdbc.Driver";
    public Connection conexionBD;
    
    public void abrir_conexion(){
        try{
            //codigo aca
            Class.forName(jdbc);
            conexionBD = DriverManager.getConnection(urlConexion,usuario,contra);
            System.out.println("Conexion Exitosa...");
         }catch(ClassNotFoundException | SQLException ex){ // bombilla
             //sout+ctrl+barra +enter
             System.out.println("Algo salio mal:(" + ex.getMessage()); 
            }
    }
    
      public void cerrar_conexion(){
        try{
            //codigo aca
            conexionBD.close();
         }catch(SQLException ex){//bombilla
             //sout+ctrl+barra +enter
             System.out.println("Algo salio mal:(" + ex.getMessage());
            
            
            }
    }
    
    
}
