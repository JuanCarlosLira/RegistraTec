package profesorModel;

import java.util.StringTokenizer;


public class DepartamentoDP {
	String id_departamento, nombre;

    public DepartamentoDP() {
    	this.id_departamento="";
    	this.nombre="";
    }
    
    public DepartamentoDP(String datos) {
    	StringTokenizer st = new StringTokenizer(datos,"_");
    	this.id_departamento=st.nextToken();
    	this.nombre=st.nextToken();
    }
    
    public String getIdDep(){
        return this.id_departamento;
     }
     public String getNombre(){
        return this.nombre;
     }
    
    
    
    public void setIdDep(String d){
         this.id_departamento=d;
     }
     public void setNombre(String n){
         this.nombre=n;
     }
     
     public String toStringSQL(){
        return "null,'"+this.nombre+"'";
    }
     
     public String toString(){
        return this.id_departamento+"_"+this.nombre;
    }
    
    public String toStringJson(){
		return "{\"id_departamento\":\""+this.id_departamento+"\",\"nombre\":\""+this.nombre+"\"}";
	}
     
}