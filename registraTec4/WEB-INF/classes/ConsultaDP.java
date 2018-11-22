package modelRegistraTec;

import java.util.StringTokenizer;

public class ConsultaDP 
{
	
	String idEvent, idAdmin, idDepto, nombreDepto, nombre, dateIni, dateFin, status, nombreAdmin;
	int capMax, capAct, acomp;
	String dateIniReg, dateIniFin, carta, requisitos, idLugar;

    public ConsultaDP() 
    {
    	this.idEvent = "";
    	this.idAdmin = "";
    	this.idDepto = "";
    	this.nombre = "";
    	this.nombreDepto = "";
    	this.nombreAdmin = "";
    	this.dateIni = "";
    	this.dateFin = "";
    	this.status = "";
    	this.capMax = 0;
    	this.capAct = 0;
    	this.acomp = 0;
    	this.dateIniReg = "";
    	this.dateIniFin = "";
    	this.carta = "";
    	this.requisitos = "";
    	this.idLugar = "";
    }
    
    public ConsultaDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		this.idEvent = st.nextToken();
    	this.idAdmin = st.nextToken();
    	this.idDepto = st.nextToken();
    	this.nombre = st.nextToken();
    	this.nombreDepto = st.nextToken();
    	this.dateIni = st.nextToken();
    	this.dateFin = st.nextToken();
    	this.status = st.nextToken();
    	this.capMax = Integer.parseInt(st.nextToken());
    	this.capAct = Integer.parseInt(st.nextToken());
    	this.dateIniReg = st.nextToken();
    	this.dateIniFin = st.nextToken();
    	this.carta = st.nextToken();
    	this.requisitos = st.nextToken();
    	this.idLugar = st.nextToken();
	}
	
	//Geters
	
	public String getIdEvent()
	{
		return this.idEvent;
	}
	
	public String getIdAdmin()
	{
		return this.idAdmin;
	}
	
	public String getIdDepto()
	{
		return this.idDepto;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getNombreDepto()
	{
		return this.nombreDepto;
	}
	
	public String getNombreAdmin()
	{
		return this.nombreAdmin;
	}
	
	public String getDateIni()
	{
		return this.dateIni;
	}
	
	public String getDateFin()
	{
		return this.dateFin;
	}
	
	public String getStatus()
	{
		return this.status;
	}
	
	public int getCapMax()
	{
		return this.capMax;
	}
	
	public int getCapAct()
	{
		return this.capAct;
	}
    
    public String getDateIniReg()
	{
		return this.dateIniReg;
	}
	
	public String getDateIniFin()
	{
		return this.dateIniFin;
	}
	
	public String getCarta()
	{
		return this.carta;
	}
	
	public String getRequisitos()
	{
		return this.requisitos;
	}
	
	public String getIdLugar()
	{
		return this.idLugar;
	}
	
	
	//Seters
	
	public void setIdEvent(String evento)
	{
		this.idEvent = evento;
	}
	
	public void setIdAdmin(String admin)
	{
		this.idAdmin = admin;
	}
	
	public void setIdDepto(String depto)
	{
		this.idDepto = depto;
	}
	
	public void setNombre(String name)
	{
		this.nombre = name;
	}
	
	public void setNombreDepto(String nameDepto)
	{
		this.nombreDepto = nameDepto;
	}
	
	public void setNombreAdmin(String nameAdmin)
	{
		this.nombreAdmin = nameAdmin;
	}
	
	public void setDateIni(String dateIni)
	{
		this.dateIni = dateIni;
	}
	
	public void setDateFin(String dateFin)
	{
		this.dateFin = dateFin;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public void setCapMax(int capmax)
	{
		this.capMax = capmax;
	}
	
	public void setCapAct(int capact)
	{
		this.capAct = capact;
	}
    
    public void setDateIniReg(String dateIniReg)
	{
		this.dateIniReg = dateIniReg;
	}
	
	public void setDateIniFin(String dateIniFin)
	{
		this.dateIniFin = dateIniFin;
	}
	
	public void setCarta(String carta)
	{
		this.carta = carta;
	}
	
	public void setRequisitos(String requisitos)
	{
		this.requisitos = requisitos;
	}
	
	public void setIdLugar(String lugar)
	{
		this.idLugar = lugar;
	}
	
	public void setAcomp(int acomp)
	{
		this.acomp = acomp;
	}
	
	void setString(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		this.idEvent = st.nextToken();
    	this.idAdmin = st.nextToken();
    	this.idDepto = st.nextToken();
    	this.nombre = st.nextToken();
    	this.nombreDepto = st.nextToken();
    	this.dateIni = st.nextToken();
    	this.dateFin = st.nextToken();
    	this.status = st.nextToken();
    	this.capMax = Integer.parseInt(st.nextToken());
    	this.capAct = Integer.parseInt(st.nextToken());
    	this.dateIniReg = st.nextToken();
    	this.dateIniFin = st.nextToken();
    	this.carta = st.nextToken();
    	this.requisitos = st.nextToken();
    	this.idLugar = st.nextToken();
	}
	
	public String toString()
	{
		return this.idEvent+"_"+this.idAdmin+"_"+this.idDepto+"_"+this.nombre+"_"+this.nombreDepto+"_"+this.dateIni+"_"+this.dateFin+"_"+this.status+
				"_"+this.capMax+"_"+this.capAct+"_"+this.dateIniReg+"_"+this.dateIniFin+"_"+this.carta+"_"+this.requisitos+"_"+this.idLugar;
	}
	
	public String toStringSql()
	{
		return "'"+this.idEvent+"','"+this.idAdmin+"','"+this.idDepto+"','"+this.nombre.toUpperCase()+"','"+this.nombreDepto.toUpperCase()+"','"+this.dateIni+"','"
				+this.dateFin+"','"+this.status+"',"+this.capMax+","+this.capAct+",'"+this.dateIniReg+"','"+this.dateIniFin+"','"
				+this.carta+"','"+this.requisitos+"','"+this.idLugar+"'";
	}
	
	public String toStringJson()
	{
		return "{\"idevent\":\""+this.idEvent+"\",\"idadmin\":\""+this.idAdmin+"\",\"iddepto\":\""+this.idDepto+"\",\"nombre\":\""+this.nombre+"\",\"nombreDepto\":\""+this.nombreDepto+"\",\"nombreadmin\":\""+this.nombreAdmin+
				"\",\"dateini\":\""+this.dateIni+"\",\"datefin\":\""+this.dateFin+"\",\"status\":\""+this.status+"\",\"capmax\":\""+this.capMax+
				"\",\"capact\":\""+this.capAct+"\",\"dateinireg\":\""+this.dateIniReg+"\",\"dateinifin\":\""+this.dateIniFin+"\",\"carta\":\""+this.carta+
				"\",\"requisitos\":\""+this.requisitos+"\",\"idlugar\":\""+this.idLugar+"\",\"acomp\":\""+this.acomp+"\"}";
	}
}