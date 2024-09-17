/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Usuario
 */
public class Docente  extends Persona{
    private int id;
    private String salario;
    private String fecha_ingreso_laborar, fecha_ingreso_registro;
    
    Conexion cn;

    public Docente(String text, String text1, String text2, String text3, String text4, String text5, String text6, String text7, String text8){}
    public Docente(int id, String salario, String fecha_ingreso_laborar, String fecha_ingreso_registro, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nit, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.id = id;
        
        this.salario = salario;
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
        this.fecha_ingreso_registro = fecha_ingreso_registro;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getFecha_ingreso_laborar() {
        return fecha_ingreso_laborar;
    }

    public void setFecha_ingreso_laborar(String fecha_ingreso_laborar) {
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
    }

    public String getFecha_ingreso_registro() {
        return fecha_ingreso_registro;
    }

    public void setFecha_ingreso_registro(String fecha_ingreso_registro) {
        this.fecha_ingreso_registro = fecha_ingreso_registro;
    }
    
    public DefaultTableModel leer(){
  DefaultTableModel tabla = new DefaultTableModel();
  try{
   cn = new Conexion();
   cn.abrir_conexion();
    String query;
    query = "Select id_docente as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento from docentes;";
     ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      
      String encabezado[] = {"id","Nit","Nombres","Apellidos","Direccion","Telefono","Nacimiento","Salario","Fecha de Ingreso Laborar", "Fecha de Ingreso de Registro"};
      tabla.setColumnIdentifiers(encabezado);
      
      String datos[]=new String[10];
      
   while(consulta.next()){
      datos[0] = consulta.getString("id");
      datos[1] = consulta.getString("nit");
      datos[2] = consulta.getString("nombres");
      datos[3] = consulta.getString("apellidos");
      datos[4] = consulta.getString("direccion");
      datos[5] = consulta.getString("telefono");
      datos[6] = consulta.getString("fecha_nacimiento");
      datos[7] = consulta.getString("salario");
      datos[8] = consulta.getString("fecha_ingreso_laborar");
      datos[9] = consulta.getString("fecha_ingreso_registro");
      tabla.addRow(datos);
      }
   cn.cerrar_conexion();
    
      
  }catch(SQLException ex){
      cn.cerrar_conexion();
      System.out.println("Error: " + ex.getMessage() );
  
  }
  return tabla;
  }
    @Override
   public void agregar(){
     try{
         PreparedStatement parametro;
         cn = new Conexion();
         cn.abrir_conexion();
         String query;
            query = "insert into docentes(nit,nombres,apellidos,direccion,telefono,fecha_nacimiento,salario,fecha_ingreso_laborar,fecha_ingreso_registro) "+
                 "values(?,?,?,?,?,?,?,?,?,?);";
         parametro  = (PreparedStatement) cn.conexionBD.prepareStatement(query);
         parametro.setString(1, getNit());
         parametro.setString(2, getNombres());
         parametro.setString(3, getApellidos());
         parametro.setString(4, getDireccion());
         parametro.setString(5, getTelefono());
         parametro.setString(6, getFecha_nacimiento());
         parametro.setString(7, getSalario());
         parametro.setString(8, getFecha_ingreso_laborar());
         parametro.setString(9, getFecha_ingreso_registro());
         
         int executar= parametro.executeUpdate();
         cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Ingresado",
             "Mensaje",JOptionPane.INFORMATION_MESSAGE);
     }catch(HeadlessException | SQLException ex){
         System.out.println("Error"+ex.getMessage());
     }
   }
   
   @Override
   public void actualizar(){
         try{
         PreparedStatement parametro;
         cn = new Conexion();
         cn.abrir_conexion();
         String query;
        query = "update docentes set nit = ?,nombres= ?,apellidos= ?,direccion= ?,telefono= ?,fecha_nacimiento= ?, salario = ?, fecha_ingreso_laborar = ?, fecha_ingreso_registro=?"+
                 "where id_docente = ?";
         parametro  = (PreparedStatement) cn.conexionBD.prepareStatement(query);
         parametro.setString(1, getNit());
         parametro.setString(2, getNombres());
         parametro.setString(3, getApellidos());
         parametro.setString(4, getDireccion());
         parametro.setString(5, getTelefono());
         parametro.setString(6, getFecha_nacimiento());
         parametro.setInt(7, getId());
         parametro.setString(8, getFecha_ingreso_laborar());
         parametro.setString(9, getFecha_ingreso_registro());
         
         int executar= parametro.executeUpdate();
         cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Actualizado",
             "Mensaje",JOptionPane.INFORMATION_MESSAGE);
     }catch(HeadlessException | SQLException ex){
         System.out.println("Error"+ex.getMessage());
     }
   
   }
   @Override
   public void eliminar(){
   
    try{
         PreparedStatement parametro;
         cn = new Conexion();
         cn.abrir_conexion();
         String query;
        query = "delete from docentes where id_docente = ?";
                 
         parametro  = (PreparedStatement) cn.conexionBD.prepareStatement(query);
         parametro.setInt(1, getId());
         
         int executar= parametro.executeUpdate();
         cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Eliminado",
             "Mensaje",JOptionPane.INFORMATION_MESSAGE);
     }catch(HeadlessException | SQLException ex){
         System.out.println("Error"+ex.getMessage());
     }
   
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

