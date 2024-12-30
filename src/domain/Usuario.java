/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Objects;

public class Usuario {
    private String id; 
    private String nombreCompleto;
    private String apellidos; 
    private String celular; 
    private String correo; 
    private String contraseña; 
 
    
    
   public Usuario (String id, String nombreCompleto, String apellidos, String celular, String correo,String contraseña){
   this.id = id;
   this.nombreCompleto = nombreCompleto;
   this.apellidos = apellidos; 
   this.celular= celular;
   this.correo = correo;
   this.contraseña= contraseña;
   
   
   }
    public String getId (){
    return id;
    
    }
    
    public void setId (String id){
    this.id = id;
    
    } 
    public String getNombreCompleto (){
    return nombreCompleto;
    
    }
    
    public void setNombreCompleto (String nombreCompleto){
    this.nombreCompleto = nombreCompleto;
    
    }
    public String getApellidos (){
    return apellidos;
    
    }
    
    public void setApellidos (String apellidos){
    this.apellidos = apellidos;
    
    }
    
    public String getCelular (){
    return celular;
    
    }
    
    public void setCelular (String celular){
    this.celular = celular;
    
    }
    
    public String getCorreo (){
    return correo;
    
    }
    
    public void setCorreo (String correo){
    this.correo = correo;
    
    }
    
    public String getContraseña (){
    return contraseña;
    
    }
    
    public void setContraseña (String contraseña){
    this.contraseña = contraseña;

    }

   
   @Override
   public String toString (){
   return id + ";" + nombreCompleto + ";" + apellidos + ";"+ celular + ";" + correo + ";" + contraseña;
   }

  
}
   