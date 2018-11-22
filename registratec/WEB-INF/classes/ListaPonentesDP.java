package profesorModel;

import java.util.StringTokenizer;

public class ListaPonentesDP {
	String id_ponente, id_evento, id_lista_ponentes;

    public ListaPonentesDP() {
    	this.id_ponente="";
    	this.id_evento="";
    	this.id_lista_ponentes="";
    }
    
    public ListaPonentesDP(String datos) {
    	StringTokenizer st = new StringTokenizer(datos,"_");
    	this.id_ponente=st.nextToken();
    	this.id_evento=st.nextToken();
    	this.id_lista_ponentes=st.nextToken();
    }
    
     public String getIdPon(){
        return this.id_ponente;
     }
     public String getidEve(){
        return this.id_evento;
     }
     public String getIdLista(){
     	return this.id_lista_ponentes;
     }
     
     
     public void setIdPon(String x){
         this.id_ponente=x;
     }
     public void setidEve(String x){
         this.id_evento=x;
     }
     public void setIdLista(String x){
     	this.id_lista_ponentes=x;
     }
     
     
     public String toString(){
        return this.id_ponente+"_"+this.id_evento+"_"+this.id_lista_ponentes;
    }
    
    public String toStringSQL(){
        this.id_lista_ponentes = this.id_ponente+this.id_evento;
        return "'"+this.id_ponente+"','"+this.id_evento+"',null";
    }
    
    public String toStringJson()
	{
		return "{\"id_ponente\":\""+this.id_ponente+"\",\"id_evento\":\""+this.id_evento+"\",\"id_lista_ponentes\":\""+this.id_lista_ponentes+"\"}";
	}
}