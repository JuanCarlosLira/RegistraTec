package profesorModel;

import java.util.StringTokenizer;


public class EventoDP {
	String id_evento, id_administrador, id_departamento, nombre, fecha_inicio_evento, fecha_termino_evento, status_evento, fecha_inicio_registro, fecha_inicio_termino, carta, requisitos, id_lugar;
	int capacidad_maxima,capacidad_actual,acomp;
    public EventoDP() {
    	this.id_evento="";
    	this.id_administrador="";
    	this.id_departamento="";
    	this.nombre="";
    	this.fecha_inicio_evento="";
    	this.fecha_termino_evento="";
    	this.status_evento="";
    	this.capacidad_maxima=0;
    	this.capacidad_actual=0;
    	this.fecha_inicio_registro="";
    	this.fecha_inicio_termino="";
    	this.carta="";
    	this.requisitos="";
        this.id_lugar="";
        this.acomp = 0;
    }

    public EventoDP(String datos) {
    	StringTokenizer st = new StringTokenizer(datos,"_");
    	this.id_evento=st.nextToken();
    	this.id_administrador=st.nextToken();
    	this.id_departamento=st.nextToken();
    	this.nombre=st.nextToken();
    	this.fecha_inicio_evento=st.nextToken();
    	this.fecha_termino_evento=st.nextToken();
    	this.status_evento=st.nextToken();
    	this.capacidad_maxima=Integer.parseInt(st.nextToken());
    	this.capacidad_actual=Integer.parseInt(st.nextToken());
    	this.fecha_inicio_registro=st.nextToken();
    	this.fecha_inicio_termino=st.nextToken();
    	this.carta=st.nextToken();
    	this.requisitos=st.nextToken();
        this.id_lugar=st.nextToken();
        this.acomp = 0;
    }

     public String getIdEve(){
        return this.id_evento;
     }
     public String getIdAdmin(){
        return this.id_administrador;
     }
     public String getIdDep(){
        return this.id_departamento;
     }
     public String getNombre(){
        return this.nombre;
     }
     public String getFInE(){
        return this.fecha_inicio_evento;
     }
     public String getFTerE(){
        return this.fecha_termino_evento;
     }
     public String getStatus(){
        return this.status_evento;
     }
     public int getCMax(){
        return this.capacidad_maxima;
     }
     public int getCAct(){
        return this.capacidad_actual;
     }
     public String getFInR(){
        return this.fecha_inicio_registro;
     }
     public String getFInTer(){
        return this.fecha_inicio_termino;
     }
     public String getCarta(){
        return this.carta;
     }
     public String getReq(){
        return this.requisitos;
     }
     public String getIdLug(){
        return this.id_lugar;
     }

     public int getAcomp(){
        return this.acomp;
     }

     public void setAcomp(int ac){
        this.acomp = ac;
     }

     public void setIdEve(String e){
         this.id_evento=e;
     }
     public void setIdAdmin(String a){
         this.id_administrador=a;
     }
     public void setIdDep(String d){
         this.id_departamento=d;
     }
     public void setNombre(String n){
         this.nombre=n;
     }
     public void setFInE(String fine){
         this.fecha_inicio_evento=fine;
     }
     public void setFTerE(String ftere){
         this.fecha_termino_evento=ftere;
     }
     public void setStatus(String s){
         this.status_evento=s;
     }
     public void setCMax(int c){
         this.capacidad_maxima=c;
     }
     public void setCAct(int c){
         this.capacidad_actual=c;
     }
     public void setFInR(String finr){
         this.fecha_inicio_registro=finr;
     }
     public void setFInTer(String finter){
         this.fecha_inicio_termino=finter;
     }
     public void setCarta(String c){
         this.carta =c;
     }
     public void setReq(String r){
         this.requisitos=r;
     }
     public void setIdLug(String iL){
         this.id_lugar=iL;
     }

     public String toString(){
        return this.id_evento+"_"+this.id_administrador+"_"+this.id_departamento+"_"+this.nombre+"_"+this.fecha_inicio_evento+"_"+this.fecha_termino_evento+"_"+this.status_evento+"_"+this.capacidad_maxima+"_"+this.capacidad_actual+"_"+this.fecha_inicio_registro+"_"+this.fecha_inicio_termino+"_"+this.carta+"_"+this.requisitos+"_"+this.id_lugar+"_"+this.acomp;
    }
    public String toStringSQL(){
        return "null,'"+this.id_administrador+"','"+this.id_departamento+"','"+this.nombre+"',STR_TO_DATE('"+this.fecha_inicio_evento+"','%Y-%m-%d'),STR_TO_DATE('"+this.fecha_termino_evento+"','%Y-%m-%d'),'"+this.status_evento+"',"+this.capacidad_maxima+","+this.capacidad_actual+",STR_TO_DATE('"+this.fecha_inicio_registro+"','%Y-%m-%d'),STR_TO_DATE('"+this.fecha_inicio_termino+"','%Y-%m-%d'),NULL,'"+this.requisitos+"','"+this.id_lugar+"',"+this.acomp;
    }

    public String toStringJson(){
		return "{\"id_evento\":\""+this.id_evento+"\",\"id_administrador\":\""+this.id_administrador+"\",\"id_departamento\":\""+this.id_departamento+"\",\"nombre\":\""+this.nombre+"\",\"fecha_inicio_evento\":\""+this.fecha_inicio_evento+"\",\"fecha_termino_evento\":\""+this.fecha_termino_evento+"\",\"status_evento\":\""+this.status_evento+"\",\"capacidad_maxima\":\""+this.capacidad_actual+"\",\"fecha_inicio_registro\":\""+this.fecha_inicio_registro+"\",\"fecha_inicio_termino\":\""+this.fecha_inicio_termino+"\",\"carta\":\""+this.carta+"\",\"requisitos\":\""+this.requisitos+"\",\"id_lugar\":\""+this.id_lugar+"\",\"acomp\":"+this.acomp+"}";
	}
}
