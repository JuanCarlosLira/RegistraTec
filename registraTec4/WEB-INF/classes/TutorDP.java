package modelRegistraTec;

import java.util.StringTokenizer;

public class TutorDP
{
	String idTutor, idAlumno, nombre, direccion, telefono, email, fechaNacimiento;
	String parentezco, idEscuela;

    public TutorDP()
    {
    	this.idTutor = "";
    	this.idAlumno = "";
    	this.nombre = "";
    	this.direccion = "";
    	this.telefono = "";
    	this.email = "";
    	this.fechaNacimiento = "";
    	this.parentezco = "";
    	this.idEscuela = "";
    }

	//Geters
	public String IdTutor()
	{
		return this.idTutor;
	}

	public String getIdAlumno()
	{
		return this.idAlumno;
	}

	public String getNombre()
	{
		return this.nombre;
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

	public String getParentezco()
	{
		return this.parentezco;
	}

	public String getIdEscuela()
	{
		return this.idEscuela;
	}

	//Seters
	public void setIdTutor(String tutor)
	{
		this.idTutor = tutor;
	}

	public void setIdAlumno(String alumno)
	{
		this.idAlumno = alumno;
	}

	public void setNombre(String name)
	{
		this.nombre = name;
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

	public void setParentezco(String parentezco)
	{
		this.parentezco = parentezco;
	}

	public void setIdEscuela(String school)
	{
		this.idEscuela = school;
	}

	public String toStringSql()
	{
		return "'"+this.idAlumno+"','"+this.nombre+"','"+this.direccion+"','"+this.telefono+"','"
				+this.email+"', STR_TO_DATE('"+this.fechaNacimiento+"', '%d/%m/%Y'),'"+this.parentezco+"','"+this.idEscuela+"'";
	}

	public String toStringJson()
	{
		return "{\"idalumno\":\""+this.idAlumno+"\",\"idtutor\":\""+this.idTutor+"\",\"nombre\":\""+this.nombre+"\",\"direccion\":\""+this.direccion+
				"\",\"telefono\":\""+this.telefono+"\",\"email\":\""+this.email+"\",\"fechanacimiento\":\""+this.fechaNacimiento+"\",\"parentezco\":\""+this.parentezco+"\",\"idescuela\":\""+this.idEscuela+"\"}";
	}

}
