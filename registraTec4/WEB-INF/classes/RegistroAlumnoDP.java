package modelRegistraTec;

import java.util.StringTokenizer;

public class RegistroAlumnoDP 
{
	String idAlumno, nombre, contrasena, direccion, telefono, email, fechaNacimiento;
	String programa, idEscuela;
	int semestre;

    public RegistroAlumnoDP() 
    {
    	this.idAlumno = "";
    	this.nombre = "";
    	this.contrasena = "";
    	this.direccion = "";
    	this.telefono = "";
    	this.email = "";
    	this.fechaNacimiento = "";
    	this.programa = "";
    	this.semestre = 0;
    	this.idEscuela = "";
    }
    
    public RegistroAlumnoDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		this.idAlumno = st.nextToken();
    	this.nombre = st.nextToken();
    	this.contrasena = st.nextToken();
    	this.direccion = st.nextToken();
    	this.telefono = st.nextToken();
    	this.email = st.nextToken();
    	this.fechaNacimiento = st.nextToken();
    	this.programa = st.nextToken();
    	this.semestre = Integer.parseInt(st.nextToken());
    	this.idEscuela = st.nextToken();
	}
	
	//Geters
	public String getIdAlumno()
	{
		return this.idAlumno;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getContrasena()
	{
		return this.contrasena;
	}
	
	public String getDireccion()
	{
		return this.direccion;
	}
	
	public String getTelefono()
	{
		return this.telefono;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public String getFechaNacimiento()
	{
		return this.fechaNacimiento;
	}
	
	public String getPrograma()
	{
		return this.programa;
	}
	
	public int getSemestre()
	{
		return this.semestre;
	}
	
	public String getIdEscuela()
	{
		return this.idEscuela;
	}
	
	//Seters
	public void setIdAlumno(String alumno)
	{
		this.idAlumno = alumno;
	}
	
	public void setNombre(String name)
	{
		this.nombre = name;
	}
	
	public void setContrasena(String pass)
	{
		this.contrasena = pass;
	}
	
	public void setDireccion(String address)
	{
		this.direccion = address;
	}
	
	public void setTelefono(String phone)
	{
		this.telefono = phone;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setFechaNacimiento(String date)
	{
		this.fechaNacimiento = date;
	}
	
	public void setPrograma(String program)
	{
		this.programa = program;
	}
	
	public void setSemestre(int semestre)
	{
		this.semestre = semestre;
	}
	
	public void setIdEscuela(String school)
	{
		this.idEscuela = school;
	}
    
    public String toString()
	{
		return this.idAlumno+"_"+this.nombre+"_"+this.contrasena+"_"+this.direccion+"_"+this.telefono+"_"+this.email+"_"+this.fechaNacimiento+
				"_"+this.programa+"_"+this.semestre+"_"+this.idEscuela;
	}
	
	public String toStringSql()
	{
		return "'"+this.idAlumno+"','"+this.nombre+"','"+this.contrasena+"','"+this.direccion+"','"+this.telefono+"','"
				+this.email+"', TO_DATE('"+this.fechaNacimiento+"', 'dd/mm/YYYY'),'"+this.programa+"',"+this.semestre+",'"+this.idEscuela+"'";
	}
	
	public String toStringJson()
	{
		return "{\"idalumno\":\""+this.idAlumno+"\",\"nombre\":\""+this.nombre+"\",\"contrasena\":\""+this.contrasena+"\",\"direccion\":\""+this.direccion+
				"\",\"telefono\":\""+this.telefono+"\",\"email\":\""+this.email+"\",\"fechanacimiento\":\""+this.fechaNacimiento+"\",\"programa\":\""+this.programa+
				"\",\"semestre\":\""+this.semestre+"\",\"idescuela\":\""+this.idEscuela+"\"}";
	}
    
}