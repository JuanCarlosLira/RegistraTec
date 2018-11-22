package profesorModel;

import java.util.StringTokenizer;


public class PonenteDP {
	String id_ponente, nombre, direccion, telefono, email;

    public PonenteDP() {
    	this.id_ponente="";
    	this.nombre="";
    	this.direccion="";
    	this.telefono="";
    	this.email="";
    }
    
    public PonenteDP(String datos) {
    	StringTokenizer st = new StringTokenizer(datos,"_");
    	this.id_ponente=st.nextToken();
    	this.nombre=st.nextToken();
    	this.direccion=st.nextToken();
    	this.telefono=st.nextToken();
    	this.email=st.nextToken();
    }
    
    public String getIdPon(){
        return this.id_ponente;
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
     public String getMail(){
        return this.email;
     }
     
     
     
     public void setIdPon(String x){
         this.id_ponente=x;
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
     public void setMail(String x){
         this.email=x;
     }
    
    
   public String toString(){
        return this.id_ponente+"_"+this.nombre+"_"+this.direccion+"_"+this.telefono+"_"+this.email;
    }
    
    public String toStringSQL(){
        return "null,'"+this.nombre+"','"+this.direccion+"','"+this.telefono+"','"+this.email+"'";
    }
    
    public String toStringJson()
	{
		return "{\"id_ponente\":\""+this.id_ponente+"\",\"nombre\":\""+this.nombre+"\",\"direccion\":\""+this.direccion+"\",\"telefono\":\""+this.telefono+"\",\"email\":\""+this.email+"\"}";
	}
    
}