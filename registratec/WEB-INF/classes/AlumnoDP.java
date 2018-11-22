package profesorModel;

import java.util.StringTokenizer;

public class AlumnoDP{
    private String idAlumno;
    private String nombre;
    private String contrasena;
    private String direccion;
    private String telefono;
    private String email;
    private String fechaNacimiento;
    private String programa;
    private String semestre;
    private String idEscuela;
    
    public AlumnoDP(){
        this.idAlumno = "";
        this.nombre = "";
        this.contrasena = "";
        this.direccion = "";
        this.telefono = "";
        this.email = "";
        this.fechaNacimiento = "";
        this.programa = "";
        this.semestre = "";
        this.idEscuela = "";
    }

    public AlumnoDP(String datos){
        StringTokenizer st = new StringTokenizer(datos, "_");
        this.idAlumno = st.nextToken();
        this.nombre = st.nextToken(); 
        this.contrasena = st.nextToken();
        this.direccion = st.nextToken();
        this.telefono = st.nextToken();
        this.email = st.nextToken();
        this.fechaNacimiento =st.nextToken();
        this.programa = st.nextToken();
        this.semestre = st.nextToken();
        this.idEscuela = st.nextToken();
    }

    public String getIdAlumno(){return this.idAlumno;}
    public String getNombre(){return this.nombre;}
    public String getContrasena(){return this.contrasena;}
    public String getDireccion(){return this.direccion;}
    public String getTelefono(){return this.telefono;}
    public String getEmail(){return this.email;}
    public String getFechaNacimiento(){return this.fechaNacimiento;}
    public String getPrograma(){return this.programa;}
    public String getSemestre(){return this.semestre;}
    public String getIdEscuela(){return this.idEscuela;}

    public void setIdAlumno(String idAlumno){this.idAlumno=idAlumno;}
    public void setNombre(String nombre){this.nombre=nombre;}
    public void setContrasena(String contrasena){this.contrasena=contrasena;}
    public void setDireccion(String direccion){this.direccion=direccion;}
    public void setTelefono(String telefono){this.telefono=telefono;}
    public void setEmail(String email){this.email=email;}
    public void setFechaNacimiento(String fechaNacimiento){this.fechaNacimiento=fechaNacimiento;}
    public void setPrograma(String programa){this.programa=programa;}
    public void setSemestre(String semestre){this.semestre=semestre;}
    public void setIdEscuela(String escuela){this.idEscuela=escuela;}

    public String toStringJSON(){
        return "{"+"idAlumno:'"+this.idAlumno+"',nombre:'"+this.nombre+"',contrasena:'"+this.contrasena+"',direccion:'"+this.direccion+"',telefono:'"+this.telefono+"',email:'"+this.email+"',fechaNacimiento:'"+this.fechaNacimiento+"',programa:'"+this.programa+"',semestre:'"+this.semestre+"',idEscuela:'"+this.idEscuela+"'}";
    }

    public String toStringSQL(){
        this.contrasena = this.idAlumno + this.fechaNacimiento;
        this.direccion = "Introduzca su direccion";
        this.telefono = "Telefono";
        return "('"+this.idAlumno+"','"+this.nombre+"','"+this.contrasena+"','"+this.direccion+"','"+this.telefono+"','"+this.email+"',STR_TO_DATE('"+this.fechaNacimiento+"','%Y-%m-%d'),'"+this.programa+"','"+this.semestre+"','"+this.idEscuela+"')";
    }
}