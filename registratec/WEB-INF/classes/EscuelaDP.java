package profesorModel;

import java.util.StringTokenizer;

public class EscuelaDP{
    public String idEscuela;
    public String nombre;
    public String direccion;
    public String telefono;

    public EscuelaDP(){
        this.idEscuela = "";
        this.nombre     ="";
        this.direccion="";
        this.telefono="";
    }

    public EscuelaDP(String data){
        StringTokenizer st = new StringTokenizer(data,"_");

        this.idEscuela = st.nextToken();
        this.nombre     = st.nextToken();
        this.direccion= st.nextToken();
        this.telefono= st.nextToken();
    }

    public String getIdEscuela(){return this.idEscuela;}
    public String getNombre(){return this.nombre;}
    public String getDireccion(){return this.direccion;}
    public String getTelefono(){return this.telefono;}

    public void setIdEscuela(String idE){this.idEscuela=idE;}
    public void setNombre(String nombre){this.nombre=nombre;}
    public void setDireccion(String dir){this.direccion=dir;}
    public void setTelefono(String tel){this.telefono=tel;}

    public String toStringJSON(){
        return "{idEscuela:'"+this.idEscuela+"',nombre:'"+this.nombre+"',direccion:'"+this.direccion+"',telefono:'"+this.telefono+"'}";
    }

    public String toStringSQL(){
        return "null,'"+this.nombre+"','"+this.direccion+"','"+this.telefono+"'";
    }
}