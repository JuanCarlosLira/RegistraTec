package profesorModel;

import java.util.StringTokenizer;

public class LugarDP {
	String id_lugar, nombre, direccion, telefono;

    public LugarDP() {
    	this.id_lugar="";
    	this.nombre="";
    	this.direccion="";
    	this.telefono="";
    }
    
    public LugarDP(String datos) {
    	StringTokenizer st = new StringTokenizer(datos,"_");
    	this.id_lugar=st.nextToken();
    	this.nombre=st.nextToken();
    	this.direccion=st.nextToken();
    	this.telefono=st.nextToken();
    }
    
    public String getIDL(){
    	return this.id_lugar;
    }
    public String getNombre(){
    	return this.nombre;
    }
    public String getDir(){
    	return this.direccion;
    }
    public String getTel(){
    	return this.telefono;
    }
    
    public void setIDL(String x){
    	 this.id_lugar=x;
    }
    public void setNombre(String x){
    	this.nombre=x;
    }
    public void setDir(String x){
    	 this.direccion=x;
    }
    public void setTel(String x){
    	 this.telefono=x;
    }
    
    
    public String toStringSQL(){
        return "null,'"+this.nombre+"','"+this.direccion+"','"+this.telefono+"'";
    }
    
    public String toString(){
        return this.id_lugar+"_"+this.nombre+"_"+this.direccion+"_"+this.telefono;
    }
    
    public String toStringJson()
	{
		return "{\"id_lugar\":\""+this.id_lugar+"\",\"nombre\":\""+this.nombre+"\",\"direccion\":\""+this.direccion+"\",\"telefono\":\""+this.telefono+"\"}";
	}
    
    
    
}