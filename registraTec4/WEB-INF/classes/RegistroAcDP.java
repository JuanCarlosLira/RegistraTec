package modelRegistraTec;

import java.util.StringTokenizer;


public class RegistroAcDP 
{
	String idRegAc, idRegistro, idEvento, fechaRegistro, asistencia, fechaAsistencia, extras;
	int noAc;
	String idAc, idLugar, idGuardia;
	String nombre, direccion, telefono, mail, idAlumno;

    public RegistroAcDP() 
    {
    	this.idRegAc = "";
    	this.noAc = 0;
    	this.idRegistro = "";
    	this.idEvento = "";
    	this.fechaRegistro = "";
    	this.asistencia = "";
    	this.fechaAsistencia = "";
    	this.extras = "";
    	this.idAc = "";
    	this.idLugar = "";
    	this.idGuardia = "";
    	this.nombre = "";
    	this.direccion = "";
    	this.telefono = "";
    	this.mail = "";
    	this.idAlumno = "";
    }
    
    public RegistroAcDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		this.idRegAc = st.nextToken();
		this.noAc = Integer.parseInt(st.nextToken());
    	this.idRegistro = st.nextToken();
    	this.idEvento = st.nextToken();
    	this.fechaRegistro = st.nextToken();
    	this.asistencia = st.nextToken();
    	this.fechaAsistencia = st.nextToken();
    	this.extras = st.nextToken();
    	this.idAc = st.nextToken();
    	this.idLugar = st.nextToken();
    	this.idGuardia = st.nextToken();
	}
	
	//Geters
	public String getIdRegAc()
	{
		return this.idRegAc;
	}
	
	public int getNoAc()
	{
		return this.noAc;
	}
	
	public String getIdRegistro()
	{
		return this.idRegistro;
	}
	
	public String getIdEvento()
	{
		return this.idEvento;
	}
	
	public String getFechaRegistro()
	{
		return this.fechaRegistro;
	}
	
	public String getAsistencia()
	{
		return this.asistencia;
	}
	
	public String getFechaAsistencia()
	{
		return this.fechaAsistencia;
	}
	
	public String getExtras()
	{
		return this.extras;
	}
	
	public String getIdAc()
	{
		return this.idAc;
	}
	
	public String getIdLugar()
	{
		return this.idLugar;
	}
	
	public String getIdGuardia()
	{
		return this.idGuardia;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getIdAlumno()
	{
		return this.idAlumno;
	}
	
	public String getMail()
	{
		return this.mail;
	}
	
	//Seters
	public void setIdRegAc(String reg)
	{
		this.idRegAc = reg;
	}
	
	public void setNoAc(int no)
	{
		this.noAc = no;
	}
	
	public void setIdRegistro(String registro)
	{
		this.idRegistro = registro;
	}
	
	public void setIdEvento(String evento)
	{
		this.idEvento = evento;
	}
	
	public void setFechaRegistro(String dateR)
	{
		this.fechaRegistro = dateR;
	}
	
	public void setAsistencia(String asis)
	{
		this.asistencia = asis;
	}
	
	public void setFechaAsistencia(String dateA)
	{
		this.fechaAsistencia = dateA;
	}
	
	public void setExtras(String extras)
	{
		this.extras = extras;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setIdAlumno(String idAlumno){
		this.idAlumno = idAlumno;
	}
	
	public void setMail(String mail){
		this.mail = mail;
	}
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	public void setTelefono(String telefono){
		this.telefono = telefono;
	}
	
	public void setIdAc(String idac)
	{
		this.idAc = idac;
	}
	
	public void setIdLugar(String place)
	{
		this.idLugar = place;
	}
	
	public void setIdGuardia(String guard)
	{
		this.idGuardia = guard;
	}
	
	
	public String toString()
	{
		return this.idRegAc+"_"+this.noAc+"_"+this.idRegistro+"_"+this.idEvento+"_"+this.fechaRegistro+"_"+this.asistencia+"_"+this.fechaAsistencia+
				"_"+this.extras+"_"+this.idAc+"_"+this.idLugar+"_"+this.idGuardia;
	}
	
	public String toStringRegistroAcompSql()
	{
		return "'"+this.nombre+"','"+this.direccion+"','"+this.telefono+"','"+this.mail+"'";
	}
	
	public String toStringRegistroEventoSql()
	{
		return this.noAc+",'"+this.idAc+"','"+this.idRegistro+"','"+this.idEvento+"',STR_TO_DATE('"+this.fechaRegistro+"','%d/%m/%Y'),'"+this.extras+"'";
	}
	
	public String toStringJson()
	{
		return "{\"idregac\":\""+this.idRegAc+"\",\"noac\":\""+this.noAc+"\",\"idregistro\":\""+this.idRegistro+"\",\"idevento\":\""+this.idEvento+
				"\",\"fecharegistro\":\""+this.fechaRegistro+"\",\"asistencia\":\""+this.asistencia+"\",\"fechaasistencia\":\""+this.fechaAsistencia+"\",\"extras\":\""+this.extras+
				"\",\"idac\":\""+this.idAc+"\",\"idlugar\":\""+this.idLugar+"\",\"idguardia\":\""+this.idGuardia+"\"}";
	}
    
    
}