package profesorModel;

import java.util.StringTokenizer;

public class AdministradorDP {
	String id_administrador, id_departamento, nombre, contrasena, direccion, telefono, email, fecha_nacimiento, posicion;
	
    public AdministradorDP() {
    	this.id_administrador="";
    	this.id_departamento="";
    	this.nombre="";
    	this.contrasena="";
    	this.direccion="";
    	this.telefono="";
    	this.email="";
    	this.fecha_nacimiento="";
    	this.posicion="";
    }
    
    public AdministradorDP(String datos){
    	StringTokenizer st = new StringTokenizer(datos,"_");
        
        this.id_administrador = st.nextToken();
        this.id_departamento = st.nextToken();
        this.nombre = st.nextToken();
        this.contrasena = st.nextToken();
        this.direccion = st.nextToken();
        this.telefono = st.nextToken();
        this.email = st.nextToken();
        this.fecha_nacimiento = st.nextToken();
        this.posicion = st.nextToken();
    }
    
    public String getIdAdmin(){
    	return this.id_administrador;
    }
    public String getIdDepto(){
    	return this.id_departamento;
    }
    public String getNombre(){
    	return this.nombre;
    }
    public String getContra(){
    	return this.contrasena;
    }
    public String getDire(){
    	return this.direccion;
    }
    public String getTel(){
    	return this.telefono;
    }
    public String getMail(){
    	return this.email;
    }
    public String getFecNac(){
    	return this.fecha_nacimiento;
    }
    public String getPos(){
    	return this.posicion;
    }
    
    
    public void setIdAdmin(String adm){
    	this.id_administrador=adm;
    }
    public void setIdDepto(String dep){
    	 this.id_departamento =dep;
    }
    public void setNombre(String name){
    	 this.nombre = name;
    }
    public void setContra(String pswd){
    	 this.contrasena=pswd;
    }
    public void setDire(String dir){
    	 this.direccion=dir;
    }
    public void setTel(String fon){
    	 this.telefono=fon;
    }
    public void setMail(String mail){
    	 this.email=mail;
    }
    public void setFecNac(String nac){
    	 this.fecha_nacimiento=nac;
    }
    public void setPos(String pos){
    	 this.posicion=pos;
    }
    
    public String toStringSQL(){
        this.contrasena = "password";
        return "('"+this.id_administrador+"','"+this.id_departamento+"','"+this.nombre+"','"+this.contrasena+"','"+this.direccion+"','"+this.telefono+"','"+this.email+"',STR_TO_DATE('"+this.fecha_nacimiento+"','%Y-%m-%d'),'"+this.posicion+"')";
    }
    
    
    public String toString(){
        return this.id_administrador+"_"+this.id_departamento+"_"+this.nombre+"_"+this.contrasena+"_"+this.direccion+"_"+this.telefono+"_"+this.email+"_"+this.fecha_nacimiento+"_"+posicion;
    }
    
    public String toStringJson(){
		return "{\"id_administrador\":\""+this.id_administrador+"\",\"id_departamento\":\""+this.id_departamento+"\",\"contrasena\":\""+this.contrasena+"\",\"direccion\":\""+this.direccion+"\",\"telefono\":\""+this.telefono+"\",\"email\":\""+this.email+"\",\"fecha_nacimiento\":\""+this.fecha_nacimiento+"\",\"posicion\":\""+this.posicion+"\",\"nombre\":\""+this.nombre+"\"}";
	}
    
}