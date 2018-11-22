package modelRegistraTec;

import java.util.StringTokenizer;

public class RegistroDP 
{
	String idRegistro, idAlumno, idGuardia, idEscuela, idEvento, fechaRegistro, asistencia;
	String fechaAsistencia, extras;
	String nombreEvento;
	
    public RegistroDP() 
    {
    	this.idRegistro = "";
    	this.idAlumno = "";
    	this.idGuardia = "";
    	this.idEscuela = "";
    	this.idEvento = "";
    	this.fechaRegistro = "";
    	this.asistencia = "";
    	this.fechaAsistencia = "";
    	this.extras = "";
    	this.nombreEvento = "";
    }
    
    public RegistroDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		this.idRegistro = st.nextToken();
    	this.idAlumno = st.nextToken();
    	this.idGuardia = st.nextToken();
    	this.idEscuela = st.nextToken();
    	this.idEvento = st.nextToken();
    	this.fechaRegistro = st.nextToken();
    	this.asistencia = st.nextToken();
    	this.fechaAsistencia = st.nextToken();
    	this.extras = st.nextToken();
	}
    
    
    //Geters
    public String getIdRegistro()
    {
    	return this.idRegistro;
    }
    
    public String getIdAlumno()
    {
    	return this.idAlumno;
    }
    
    public String getIdGuardia()
    {
    	return this.idGuardia;
    }
    
    public String getIdEscuela()
    {
    	return this.idEscuela;
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
    
	public String getNombreEvento()
    {
    	return this.nombreEvento;
    }
    
    //Mutators
     public void setIdRegistro(String registro)
    {
    	this.idRegistro = registro;
    }
    
    public void setIdAlumno(String alumno)
    {
    	this.idAlumno = alumno;
    }
    
    public void setIdGuardia(String guardia)
    {
    	this.idGuardia = guardia;
    }
    
    public void setIdEscuela(String escuela)
    {
    	this.idEscuela = escuela;
    }
    
    public void setIdEvento(String evento)
    {
    	this.idEvento = evento;
    }
    
    public void setFechaRegistro(String fechaR)
    {
    	this.fechaRegistro = fechaR;
    }
    
    public void setAsistencia(String asistencia)
    {
    	this.asistencia = asistencia;
    }
    
    public void setFechaAsistencia(String fechaA)
    {
    	this.fechaAsistencia = fechaA;
    }
    
    public void setExtras(String extras)
    {
    	this.extras = extras;
    }
    
     public void setNombreEvento(String nombreEvento)
    {
    	this.nombreEvento = nombreEvento;
    }
	
	
    	
	public String toString()
	{
		return this.idRegistro+"_"+this.idAlumno+"_"+this.idGuardia+"_"+this.idEscuela+"_"+this.idEvento+"_"+this.fechaRegistro+"_"+this.asistencia+
				"_"+this.fechaAsistencia+"_"+this.extras;
	}
	
	public String toStringSql()
	{
		//return "seq_registro.nextval, '"+this.idAlumno+"','"+this.idEscuela+"','"+this.idEvento+"',TO_DATE('"+this.fechaRegistro+"','dd/mm/YYYY'), '"+this.extras+"'";
		//return "'"+this.idAlumno+"','"+this.idEscuela+"','"+this.idEvento+"',TO_DATE('"+this.fechaRegistro+"','dd/mm/YYYY'), '"+this.extras+"'";
		return "'"+this.idAlumno+"','"+this.idEscuela+"','"+this.idEvento+"', STR_TO_DATE('"+this.fechaRegistro+"', '%d/%m/%Y'), '"+this.extras+"'";
	}
	
	public String toStringJson()
	{
		return "{\"idregistro\":\""+this.idRegistro+"\",\"idalumno\":\""+this.idAlumno+"\",\"idguardia\":\""+this.idGuardia+"\",\"idescuela\":\""+this.idEscuela+
				"\",\"idevento\":\""+this.idEvento+"\",\"nombrevento\":\""+this.nombreEvento+"\",\"fecharegistro\":\""+this.fechaRegistro+"\",\"asistencia\":\""+this.asistencia+"\",\"fechaasistencia\":\""+this.fechaAsistencia+
				"\",\"extras\":\""+this.extras+"\"}";
	}
}