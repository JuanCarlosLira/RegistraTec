package profesorModel;

import java.util.StringTokenizer;


public class TutorDP {
	String id_tutor, id_alumno, nombre, direccion, telefono, email, fecha_nacimiento, parentezco, id_escuela;

    public TutorDP() {
	    this.id_tutor="";
	    this.id_alumno="";
	    this.nombre="";
	    this.direccion="";
	    this.telefono="";
	    this.email="";
	    this.fecha_nacimiento="";
	    this.parentezco="";
	    this.id_escuela="";
    }
    
    public TutorDP(String datos){
    	StringTokenizer st = new StringTokenizer(datos,"_");
    	this.id_tutor=st.nextToken();
	    this.id_alumno=st.nextToken();
	    this.nombre=st.nextToken();
	    this.direccion=st.nextToken();
	    this.telefono=st.nextToken();
	    this.email=st.nextToken();
	    this.fecha_nacimiento=st.nextToken();
	    this.parentezco=st.nextToken();
	    this.id_escuela=st.nextToken();
    }
    
    public String getIdTuto(){
        return this.id_tutor;
     }
     public String getIdAlu(){
        return this.id_alumno;
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
     public String getFecNac(){
        return this.fecha_nacimiento;
     }
     public String getParen(){
        return this.parentezco;
     }
     public String getIdEsc(){
        return this.id_escuela;
     }
     
     
     public void setIdTuto(String i){
         this.id_tutor=i;
     }
     public void setIdAlu(String x){
         this.id_alumno=x;
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
     public void setFecNac(String x){
         this.fecha_nacimiento=x;
     }
     public void setParen(String x){
         this.parentezco=x;
     }
     public void setIdEsc(String x){
         this.id_escuela=x;
     }
    
    
    
    
    public String toStringSQL(){
        return "null,'"+this.id_alumno+"','"+this.nombre+"','"+this.direccion+"','"+this.telefono+"','"+this.email+"','"+this.fecha_nacimiento+"','"+this.parentezco+"','"+this.id_escuela+"'";
    }
    
    public String toString(){
        return this.id_tutor+"_"+this.id_alumno+"_"+this.nombre+"_"+this.direccion+"_"+this.telefono+"_"+this.email+"_"+this.fecha_nacimiento+"_"+this.parentezco+"_"+this.id_escuela;
    }
    
    public String toStringJson()
	{
		return "{\"id_tutor\":\""+this.id_tutor+"\",\"id_alumno\":\""+this.id_alumno+"\",\"nombre\":\""+this.nombre+"\",\"direccion\":\""+this.direccion+"\",\"telefono\":\""+this.telefono+"\",\"email\":\""+this.email+"\",\"fecha_nacimiento\":\""+this.fecha_nacimiento+"\",\"parentezco\":\""+this.parentezco+"\",\"id_escuela\":\""+this.id_escuela+"\"}";
	}
    
}