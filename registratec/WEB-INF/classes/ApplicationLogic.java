package profesorModel;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;

import java.util.*;
import java.text.*;
import java.io.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import profesorModel.*;

public class ApplicationLogic{
    private Connection conexion;
    private Statement   statement;

    private AdministradorDP admindp;
    private DepartamentoDP deptodp;
    private EventoDP eventodp;
    private ListaPonentesDP listadp;
    private PonenteDP ponentedp;
    private TutorDP tutordp;
	private LugarDP lugardp;
	private EscuelaDP escueladp;
    private AlumnoDP alumnodp;

    public ApplicationLogic() {
    	try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/registratec","root","");
            //conexion = DriverManager.getConnection("jdbc:mysql://10.25.250.124:3306/registratec","root2","qwe");
            System.out.println("Conexion exitosa a la BD en MySQL...");
			System.out.println("Conexion exitosa a la BD registraTec en ORACLE SQL...(AutoCommit disabled)");
        }
		catch(Exception e){
			e.printStackTrace();
		}
    }

	/**
	 * Insert functions
	 */

	//Pasar a alumno
    public String registrarParientes(TutorDP tutor){
    	String query="";
    	String datos="";
    	String works="";
    	query="INSERT INTO TUTOR(id_tutor, id_alumno, nombre, direccion, telefono, email, fecha_nacimiento, parentezco, id_escuela)VALUES("+tutor.toStringSQL()+")";
    	System.out.println(query);

    	try{
    		statement=conexion.createStatement();
			statement.executeUpdate(query);
			//conexion.commit();

    		works="FUNCIONA"+query;
			datos="Datos capturados exitosamente: "+tutor.toString();

			statement.close();
			conexion.close();
    	}catch(Exception sqle){
    		System.out.println("Error: "+sqle);
    		datos="Error en captura de Pariente";
    	}finally{

		}

    	return datos;
    }

    public String registrarProfe(AdministradorDP admin){
    	String query="";
    	String datos="";
    	String works="";
    	query="INSERT INTO ADMINISTRADOR(id_administrador,id_departamento,nombre,contrasena,direccion,telefono,email,fecha_nacimiento,posicion)VALUES("+admin.toStringSQL()+")";
    	System.out.println(query);

    	try{
    		statement=conexion.createStatement();
			statement.executeUpdate(query);
			//conexion.commit();

    		works="FUNCIONA"+query;
			datos="Datos capturados exitosamente: "+admin.toString();

			statement.close();
			conexion.close();
    	}catch(Exception sqle){
    		System.out.println("Error: "+sqle);
    		datos="Error en captura de Profesor";
    	}finally{

		}

    	return datos;
    }

	public String crearEvento(EventoDP evento){
    	String query="";
    	String datos="";
    	String works="";
    	query="INSERT INTO EVENTO(id_evento,id_administrador,id_departamento,nombre,fecha_inicio_evento,fecha_termino_evento,status_evento,capacidad_maxima,capacidad_actual,fecha_inicio_registro,fecha_termino_registro,carta,requisitos,id_lugar,no_acomp)VALUES("+evento.toStringSQL()+")";
    	System.out.println(query);

    	try{
    		statement=conexion.createStatement();
			statement.executeUpdate(query);
			//conexion.commit();

    		works="FUNCIONA"+query;
			datos="Datos capturados exitosamente";

			statement.close();
			conexion.close();
    	}catch(Exception sqle){
    		System.out.println("Error: "+sqle);
    		datos="Error en captura de evento";
    	}finally{

		}
    	return datos;
    }

	public String creaPonente(PonenteDP ponente){
    	String query="";
    	String datos="";
    	String works="";
    	query="INSERT INTO PONENTE(id_ponente,nombre,direccion,telefono,email)VALUES("+ponente.toStringSQL()+")";
    	System.out.println(query);

    	try{
    		statement=conexion.createStatement();
			statement.executeUpdate(query);
			//conexion.commit();

    		works="FUNCIONA"+query;
			datos="Datos capturados exitosamente";

			statement.close();
			conexion.close();
    	}catch(Exception sqle){
    		System.out.println("Error: "+sqle);
    		datos="Error en captura de Ponente";
    	}finally{

		}
    	return datos;
	}

	public String creaLugar(LugarDP lugar){
    	String query="";
    	String datos="";
    	String works="";
    	query="INSERT INTO LUGAR(id_lugar,nombre,direccion,telefono)VALUES("+lugar.toStringSQL()+")";
    	System.out.println(query);

    	try{
    		statement=conexion.createStatement();
			statement.executeUpdate(query);
			//conexion.commit();

    		works="FUNCIONA"+query;
			datos="Datos capturados exitosamente";

			statement.close();
			conexion.close();
    	}catch(Exception sqle){
    		System.out.println("Error: "+sqle);
    		datos="Error en captura de lugar";
    	}finally{

		}
    	return datos;
	}

	public String insertEstudiante(AlumnoDP alunno){
		String query = "INSERT INTO alumno VALUES"+alunno.toStringSQL()+"";
		String data = "";
		try{
			statement=conexion.createStatement();
			statement.executeUpdate(query);
			//conexion.commit();

			data = "Alumno capturado correctamente";

			statement.close();
			conexion.close();
		}catch(Exception e){
			System.out.println("Error: "+e);
			data = "Error al capturar alumno, revise los datos";
			System.out.println(query);
		}finally{

		}
		return data;
	}

	public String insertEscuela(EscuelaDP escuela){
		String query = "INSERT INTO escuela VALUES("+escuela.toStringSQL()+")";
		String data = "";
		try{
			statement=conexion.createStatement();
			statement.executeUpdate(query);
			//conexion.commit();

			System.out.println(query);
			data = "Escuela creada correctamente";

			statement.close();
			conexion.close();
		}catch(Exception e){
			System.out.println("Error: "+e);
			data = "Error al crear la escuela";
		}finally{

		}
		return data;
	}

	public String crearDepto(String departamento){
		String query = "INSERT INTO departamento VALUES(seq_departamento.nextval,'"
						+departamento+"')";

		String data = "";

		try {
			statement = conexion.createStatement();
			statement.executeUpdate(query);
			//conexion.commit();

			System.out.println(query);
			data = "Departamento creado correctamente";

			statement.close();
			conexion.close();
		} catch (Exception e) {
			//TODO: handle exception
			System.out.println(query);
			e.printStackTrace();
			data = "Error al insertar departamento";
		}finally{

		}

		return data;
	}


	public String crearAdmin(AdministradorDP administrador){
		String query = "INSERT INTO administrador VALUES"+administrador.toStringSQL();
		String data  = "";

		try{
			statement = conexion.createStatement();
			statement.executeUpdate(query);
			//conexion.commit();

			System.out.println(query);
			data = "Administrador registrado en el sistema";

			statement.close();
			conexion.close();
		}catch(Exception e){
			e.printStackTrace();
			data = "Error al insertar departamento";
		}finally{

		}
		return data;
	}

	/**
	 * Retrieve functions
	 */

    public String consultaEventos(){
    	String datos="[";
    	String query="";
    	ResultSet tr;
    	query="SELECT * FROM EVENTO WHERE status_evento='ABIERTO'";

    	try{
    		statement=conexion.createStatement();
    		tr=statement.executeQuery(query);
    		eventodp= new EventoDP();
    		while(tr.next()){
    		 eventodp.setIdEve(tr.getString(1));
     		 eventodp.setIdAdmin(tr.getString(2));
    		 eventodp.setIdDep(tr.getString(3));
     		 eventodp.setNombre(tr.getString(4));
     		 eventodp.setFInE(tr.getString(5));
		     eventodp.setFTerE(tr.getString(6));
		     eventodp.setStatus(tr.getString(7));
		     eventodp.setCMax(tr.getInt(8));
		     eventodp.setCAct(tr.getInt(9));
		     eventodp.setFInR(tr.getString(10));
		     eventodp.setFInTer(tr.getString(11));
		     eventodp.setCarta(tr.getString(12));
		     eventodp.setReq(tr.getString(13));
			 eventodp.setIdLug(tr.getString(14));
			 eventodp.setAcomp(tr.getInt(15));

		     datos = datos+eventodp.toStringJson()+",";
    		}


			datos=datos.substring(0,datos.length()-1)+"]";

			tr.close();
			statement.close();
			conexion.close();
    	}catch(SQLException sqle){
    		datos ="ERROR";
    		System.out.println("Error: "+sqle);
    	}finally{

		}
    	System.out.println(datos);
    	return datos;
	}


	public String obtenerEscuelas(){
		String datos="[";
		String query = "SELECT * FROM escuela";
    	ResultSet tr;

		escueladp = new EscuelaDP();

		try{
    		statement=conexion.createStatement();
			tr=statement.executeQuery(query);


			while(tr.next()){
				escueladp.setIdEscuela(tr.getString(1));
				escueladp.setNombre(tr.getString(2));
				escueladp.setDireccion(tr.getString(3));
				escueladp.setTelefono(tr.getString(4));

				datos = datos + escueladp.toStringJSON()+",";
			}

    		datos=datos.substring(0,datos.length()-1)+"]";

			tr.close();
			statement.close();
			conexion.close();
		}catch(Exception e){
			e.printStackTrace();
			datos = "ERROR";
		}finally{

		}

		System.out.println(datos);

		return datos;
	}

	public String obtenerEstudiantes(){
		String datos="[";
		String query = "SELECT * FROM alumno";
		ResultSet tr;

		alumnodp = new AlumnoDP();

		try{
			statement=conexion.createStatement();
			tr=statement.executeQuery(query);

			while(tr.next()){
				alumnodp.setIdAlumno(tr.getString("id_alumno"));
				alumnodp.setNombre(tr.getString("nombre"));
				alumnodp.setEmail(tr.getString("email"));
				alumnodp.setFechaNacimiento(tr.getString("fecha_nacimiento"));
				alumnodp.setSemestre(tr.getString("semestre"));
				alumnodp.setPrograma(tr.getString("programa"));

				datos = datos + alumnodp.toStringJSON() +",";
			}


    		datos=datos.substring(0,datos.length()-1)+"]";

			tr.close();
			statement.close();
			conexion.close();
		}catch(Exception e){
			e.printStackTrace();
			datos = "ERROR";
		}finally{

		}
		System.out.println(datos);
		return datos;

	}

	public String obtenerLugares(){
		String query = "SELECT * FROM LUGAR";
		String datos = "[";
		ResultSet rs;
		int found = 0;

		try{
			statement=conexion.createStatement();
			rs=statement.executeQuery(query);

			lugardp = new LugarDP();

			while(rs.next()){
				found = 1;
				lugardp.setNombre(rs.getString("nombre"));
				lugardp.setIDL(rs.getString("id_lugar"));
				lugardp.setDir(rs.getString("direccion"));
				lugardp.setTel(rs.getString("telefono"));

				datos = datos+lugardp.toStringJson()+",";
			}

			statement.close();
    		datos=datos.substring(0,datos.length()-1)+"]";

			if(found == 0){
				datos = "{\"msg\":\"NotFound\"}";
			}

			rs.close();
			statement.close();
			conexion.close();
		}catch(Exception e){
			e.printStackTrace();
			datos = "{\"msg\":\"ErrorProc\"}";
		}finally{

		}
		return datos;
	}

	public String obtenerPonentes(){
		String query = "SELECT * FROM PONENTE";
		String datos = "[";
		ResultSet rs;
		int found = 0;

		try{
			statement=conexion.createStatement();
			rs=statement.executeQuery(query);

			ponentedp = new PonenteDP();

			while(rs.next()){
				found = 1;
				ponentedp.setIdPon(rs.getString("id_ponente"));
				ponentedp.setNombre(rs.getString("nombre"));
				ponentedp.setMail(rs.getString("email"));
				ponentedp.setDir(rs.getString("direccion"));
				ponentedp.setTel(rs.getString("telefono"));

				datos = datos+ponentedp.toStringJson()+",";
			}

			statement.close();
    		datos=datos.substring(0,datos.length()-1)+"]";

			if(found == 0){
				datos = "{\"msg\":\"NotFound\"}";
			}

			rs.close();
			statement.close();
			conexion.close();
		}catch(Exception e){
			e.printStackTrace();
			datos = "{\"msg\":\"ErrorProc\"}";
		}finally{

		}
		return datos;
	}

	public String getDeptos(){
		String sql = "SELECT * FROM departamento";

		String datos = "[";
		ResultSet tr;

		boolean found = false;

		try {
			statement=conexion.createStatement();
			tr=statement.executeQuery(sql);


			while (tr.next()) {
				datos = datos + "{idDpto:'"+tr.getString("id_departamento")+"',nombreDpto:'"+tr.getString(2)+"'},";
				found = true;
			}

			datos=datos.substring(0,datos.length()-1)+"]";

			if(!found){
				datos = "{\"msg\":\"NotFound\"}";
			}

			tr.close();
			statement.close();
			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
			datos = "{\"msg\":\"ErrorProc\"}";
		}

		return datos;
	}

	public String getAdmins(){
		String sql = "SELECT * FROM administrador";

		String datos = "[";
		ResultSet tr;
		boolean found = false;


		try {
			statement=conexion.createStatement();
			tr=statement.executeQuery(sql);

			while (tr.next()) {
				admindp = new AdministradorDP();

				admindp.setIdAdmin(tr.getString("id_administrador"));
				admindp.setNombre(tr.getString("nombre"));
				admindp.setTel(tr.getString("telefono"));
				admindp.setPos(tr.getString("posicion"));
				admindp.setFecNac(tr.getString("fecha_nacimiento"));
				admindp.setMail(tr.getString("email"));

				datos = datos + admindp.toStringJson() + ",";

				found = true;
			}

			datos=datos.substring(0,datos.length()-1)+"]";


			if(!found){
				datos = "{\"msg\":\"NotFound\"}";
			}

			tr.close();
			statement.close();
			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
			datos = "{\"msg\":\"ErrorProc\"}";
		}
		return datos;
	}

    public String logInProfe(String mail, String pswd){
    	String datos="";
    	String contra="";
    	String query;
    	ResultSet tr;
    	boolean encontrado =false;
    	query="SELECT * FROM ADMINISTRADOR WHERE email='"+mail+"' AND contrasena='"+pswd+"'";
    	try{
    		statement= conexion.createStatement();
    		tr= statement.executeQuery(query);
    		admindp = new AdministradorDP();
    		while(tr.next()){
    				admindp.setIdAdmin(tr.getString(1));
    				admindp.setIdDepto(tr.getString(2));
    				admindp.setNombre(tr.getString(3));
    				admindp.setContra(tr.getString(4));
       				admindp.setDire(tr.getString(5));
       				admindp.setTel(tr.getString(6));
       				admindp.setMail(tr.getString(7));
       				admindp.setFecNac(tr.getString(8));
       				admindp.setPos(tr.getString(9));
       				datos = datos+admindp.toString()+"";
       				encontrado=true;
			}


			tr.close();
			statement.close();
			conexion.close();
    	}catch(SQLException sqle){
    		datos ="ERROR";
    		System.out.println("Error: "+sqle);
    	}
    	if(!encontrado) datos = "NO_LOCALIZADO";
    	System.out.println(datos);
    	return datos;
    }

    public String agregarCarta(EventoDP carta, String id){
    	String datos="";
    	String query;
    	query="UPDATE EVENTO SET carta='"+carta.getCarta()+"'WHERE id_evento='"+carta.getIdEve()+"'";
    	try{
    		statement = conexion.createStatement();
    		statement.executeUpdate(query);

    		datos="CARTA AGREGADA EXITOSAMENTE";
			System.out.println(query);

			//conexion.commit();

			statement.close();
			conexion.close();
    	}catch(SQLException sqle){
    		System.out.println("Error: "+sqle);
    		datos = "ERROR EN CAPTURA DE CARTA";
    	}
    	return datos;

    }

	public String linkPonenteEvento(ListaPonentesDP lista){
		String query = "INSERT INTO lista_ponentes VALUES("+lista.toStringSQL()+")";

		String data = "";

		try{
			statement=conexion.createStatement();
			statement.executeUpdate(query);
			//conexion.commit();

			System.out.println(query);
			data = "Ponente añadido a lista de ponentes para evento";

			statement.close();
			conexion.close();
		}catch(Exception e){
			System.out.println("Error: "+e);
			data = "Error al capturar la relación, revise el ponente y el evento";
		}

		return data;
	}

	public String ponentesEvento(String idEve){
		String query = "";
		String results = "[";
		ResultSet tr;

		if(idEve.equals("")){
			query= "SELECT pon.id_ponente,pon.nombre,ev.id_evento,ev.nombre,ev.fecha_inicio_evento FROM ponente pon JOIN lista_ponentes lp ON pon.id_ponente = lp.id_ponente JOIN evento ev ON lp.id_evento = ev.id_evento ORDER BY ev.fecha_inicio_evento ";
		}else{
			query= "SELECT pon.id_ponente,pon.nombre,ev.id_evento,ev.nombre,ev.fecha_inicio_evento FROM ponente pon JOIN lista_ponentes lp ON pon.id_ponente = lp.id_ponente JOIN evento ev ON lp.id_evento = ev.id_evento WHERE ev.id_evento='"+idEve+"'";
		}

		try{
			statement=conexion.createStatement();
    		tr=statement.executeQuery(query);

			while(tr.next()){
				results += "{id_ponente:'"+tr.getString(1)+"',nombre:'"+tr.getString(2)+"',id_evento:'"+tr.getString(3)+"',nombreEvento:'"+tr.getString(4)+"',fechaInicio:'"+tr.getString(5)+"'},";
			}

			results = results.substring(0,results.length()-1)+"]";

			tr.close();
			statement.close();
			conexion.close();
		}catch(Exception e){
			System.out.println("Error: "+e);
			results = "ERRNO";
		}
		System.out.println(results);

		return results;
	}

	public String eventosPorLugar(String lugar){
		String query = "";
		String results = "[";
		ResultSet tr;

		if(lugar.equals("")){
			query = "SELECT lu.nombre, lu.direccion, lu.telefono, ev.id_evento, ev.nombre,ev.fecha_inicio_evento FROM lugar lu JOIN evento ev ON lu.id_lugar = ev.id_lugar ";
		}
		else{
			query = "SELECT lu.nombre, lu.direccion, lu.telefono, ev.id_evento, ev.nombre,ev.fecha_inicio_evento FROM lugar lu JOIN evento ev ON lu.id_lugar = ev.id_lugar WHERE lu.nombre='"+lugar+"'";
		}

		try{
			statement=conexion.createStatement();
    		tr=statement.executeQuery(query);

			while(tr.next()){
				results += "{nombrelugar:'"+tr.getString(1)+"',direccion:'"+tr.getString(2)+"',telefono:'"+tr.getString(3)+"',idEve:'"+tr.getString(4)+"',nombre:'"+tr.getString(5)+"',fechainicio:'"+tr.getString(6)+"'},";
			}


			results = results.substring(0,results.length()-1)+"]";

			tr.close();
			statement.close();
			conexion.close();
		}catch(Exception e){
			System.out.println("Error: "+e);
			results = "ERRNO";
		}
		return results;
	}

	public String eventosRecientes(){
		String datos = "[";
		String query = "SELECT evento.nombre, evento.fecha_inicio_evento,evento.fecha_termino_evento,lugar.nombre,ponente.nombre "+
						" FROM evento"+
						" JOIN lugar ON evento.id_lugar=lugar.id_lugar"+
						" JOIN lista_ponentes ON evento.id_evento=lista_ponentes.id_evento"+
						" JOIN ponente ON lista_ponentes.id_ponente=ponente.id_ponente"+
						" WHERE evento.status_evento='ABIERTO'";

		ResultSet tr;
		boolean found = false;

		try{
			statement=conexion.createStatement();
			tr=statement.executeQuery(query);

			while(tr.next()){
				String json = "{nombreEvento:'"+tr.getString(1) + "',fechaInicio:'"+tr.getString(2)+"',fechaTermino:'"+tr.getString(3)+
								"',nombreLugar:'"+tr.getString(4)+"',nombrePonente:'"+tr.getString(5)+"'}";
				datos = datos + json + ",";
				found=true;
			}


    		datos=datos.substring(0,datos.length()-1)+"]";

			if(!found){
				datos = "{\"msg\":\"NotFound\"}";
			}

			tr.close();
			statement.close();
			conexion.close();
		}catch(Exception e){
			datos = "{\"msg\":\"Error\"}";
			e.printStackTrace();
		}
		return datos;
	}

	public String getEventos(){
		String datos = "[";
		String query = "SELECT evento.nombre, evento.fecha_inicio_evento,evento.fecha_termino_evento,lugar.nombre"+
						" FROM evento"+
						" JOIN lugar ON evento.id_lugar=lugar.id_lugar"+
						" WHERE evento.status_evento='ABIERTO'";

		ResultSet tr;
		boolean found = false;

		try{
			statement=conexion.createStatement();
			tr=statement.executeQuery(query);

			while(tr.next()){
				String json = "{nombreEvento:'"+tr.getString(1) + "',fechaInicio:'"+tr.getString(2)+"',fechaTermino:'"+tr.getString(3)+
								"',nombreLugar:'"+tr.getString(4)+"'}";
				datos = datos + json + ",";
				found=true;
			}


    		datos=datos.substring(0,datos.length()-1)+"]";

			if(!found){
				datos = "{\"msg\":\"NotFound\"}";
			}

			tr.close();
			statement.close();
			conexion.close();
		}catch(Exception e){
			datos = "{\"msg\":\"Error\"}";
			e.printStackTrace();
		}
		return datos;
	}

}
