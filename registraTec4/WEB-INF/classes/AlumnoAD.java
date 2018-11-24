package modelRegistraTec;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import java.util.Properties;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.internet.MimeBodyPart;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class AlumnoAD
{
	private Connection conexion;
    private Statement statement;

    private ConsultaDP consultadp;
    private RegistroDP registrodp;
    private RegistroAcDP registroacdp;
    private RegistroAlumnoDP registroalumnodp;
	private TutorDP tutordp;

	private int acciones, totalAcomp;
	private String fecha;

	//CORREO SET CLASSPATH=C:\apache-tomcat-8.5.34\webapps\registraTec4\WEB-INF\lib\mail-1.4.7.jar;.;%CLASSPATH%

    public AlumnoAD(){
        try{
			//CONEXION MYSQL
			//SET CLASSPATH=C:\Users\Esteban\Desktop\mysql-connector-java-5.1.40\mysql-connector-java-5.1.40-bin.jar;.;%CLASSPATH%
			Class.forName("com.mysql.jdbc.Driver").newInstance();
            //conexion = DriverManager.getConnection("jdbc:mysql://localhost/registratec?user=root");
						conexion = DriverManager.getConnection("jdbc:mysql://10.25.250.124:3306/registratec","root2","qwe");;
            System.out.println("Conexion exitosa a la BD registraTec en MySQL...");

			//CONEXION ORACLE
			//SET CLASSPATH=C:\apache-tomcat-8.5.20\webapps\registraTec\WEB-INF\lib\ojdbc14.jar;.;%CLASSPATH%
			/*Class.forName("oracle.jdbc.OracleDriver");
			conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","registratec","aguadec0c0");
			conexion.setAutoCommit(false);
			System.out.println("Conexion exitosa a la BD registraTec en ORACLE SQL...(AutoCommit disabled)");*/
			acciones = 0;
        }catch(ClassNotFoundException cnfe){
            System.out.println("Error: "+cnfe);
        }catch(InstantiationException ie){
			System.out.println("Error: "+ie);
		}catch(IllegalAccessException iae){
			System.out.println("Error: "+iae);
		}catch(SQLException sqle){
            System.out.println("Error: "+sqle);
        }

    }

    public String inicioSesion(String user, String password){
    	String respuesta="";
    	String query="";
    	ResultSet tr;

        boolean encontrado=false;

		//query = "SELECT count(*) FROM ALUMNO";
        query = "SELECT * FROM ALUMNO WHERE id_alumno = '"+user+"'";

        try {
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);

            while(tr.next()){
                encontrado = true;
            }

            statement.close();

            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            respuesta = "ERROR";
            System.out.println("Error: "+sqle);
        }

        if(!encontrado)
            respuesta = "ALUMNO_NO_LOCALIZADO";
        else{
        	String resultado = searchPassword(user,password);

        	if(resultado.equals("NO_LOCALIZADO") || resultado.equals("ERROR"))
        		respuesta = "CONTRASENA_INCORRECTA";
        	else{
        		respuesta = "EXITO";
        	}
        }

    	return respuesta;
    }

	public String buscarNombreUsuario(String user){
		String respuesta="";
    	String query="";
    	ResultSet tr;

        boolean encontrado=false;

        query = "SELECT nombre FROM ALUMNO WHERE id_alumno='"+user+"'";

        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            while(tr.next()){
                respuesta = tr.getString(1);
            }

            statement.close();


            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            respuesta = "ERROR";
            System.out.println("Error: "+sqle);
        }
		return respuesta;
	}

    public String searchPassword(String user, String password){
    	String resultado="";
    	String query="";

    	ResultSet tr;

        boolean encontrado=false;

        //query = "SELECT * FROM ALUMNO WHERE id_alumno='"+user+"' AND contrasena='"+password+"'";
        query = "SELECT * FROM ALUMNO WHERE id_alumno='"+user+"' AND contrasena='"+password+"'";

        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            while(tr.next()){
                encontrado = true;
            }

            statement.close();

            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            resultado = "ERROR";
            System.out.println("Error: "+sqle);
        }

        if(!encontrado)
        	resultado = "NO_LOCALIZADO";
        else{
        	resultado = "EXITO";
        }

    	return resultado;
    }

	public int noAcompEvento(String idEvento){
		int numero = 0;
    	String query = "";
        ResultSet tr;

        boolean encontrado=false;

        query = "SELECT no_acomp FROM evento WHERE id_evento = '"+idEvento+"'";
         System.out.println(query);
        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            consultadp = new ConsultaDP();
            while(tr.next()){
            	numero = Integer.parseInt(tr.getString(1));
                encontrado = true;
            }

            statement.close();

            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
        }

    	return numero;
	}

    public String consulta(String idAlumno){
    	String datos="[";
    	String query;
        ResultSet tr;

        boolean encontrado=false;

        query = "SELECT e.id_evento, e.nombre, d.nombre, e.fecha_inicio_evento, e.status_evento FROM Evento e, Departamento d WHERE e.id_departamento = d.id_departamento AND e.status_evento != 'CONCLUIDO' AND e.id_evento NOT IN (SELECT id_evento FROM registro_alumno WHERE id_alumno = '"+idAlumno+"')";

        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            consultadp = new ConsultaDP();
            while(tr.next()){
            	consultadp.setIdEvent(tr.getString(1));
            	consultadp.setNombre(tr.getString(2));
            	consultadp.setNombreDepto(tr.getString(3));
            	consultadp.setDateIni(tr.getString(4));
            	consultadp.setStatus(tr.getString(5));

                datos = datos + consultadp.toStringJson() +",";

                encontrado = true;
            }

            statement.close();

            datos = datos.substring(0,datos.length()-1) + "]";

            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }

        if(!encontrado){
            datos = "[{\"resultado\":\"No hay eventos disponibles\"},";
			datos = datos.substring(0,datos.length()-1) + "]";
		}

    	return datos;
    }

	public String consultarMiInfo(String idAlumno){
		String datos="[";
    	String query;
        ResultSet tr;

        boolean encontrado=false;

        query = "SELECT a.*, e.nombre FROM alumno a, escuela e WHERE a.id_alumno = '"+idAlumno+"' AND e.id_escuela = a.id_escuela";

        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            registroalumnodp = new RegistroAlumnoDP();
            while(tr.next()){
            	registroalumnodp.setIdAlumno("");
            	registroalumnodp.setNombre(tr.getString(2));
            	registroalumnodp.setContrasena(tr.getString(3));
            	registroalumnodp.setDireccion(tr.getString(4));
            	registroalumnodp.setTelefono(tr.getString(5));
            	registroalumnodp.setEmail(tr.getString(6));
            	registroalumnodp.setFechaNacimiento(tr.getString(7));
            	registroalumnodp.setPrograma(tr.getString(8));
            	registroalumnodp.setSemestre(tr.getInt(9));
            	registroalumnodp.setIdEscuela(tr.getString(11));

                datos = datos + registroalumnodp.toStringJson() +",";

                encontrado = true;
            }

            statement.close();

            datos = datos.substring(0,datos.length()-1) + "]";
            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }

		 if(!encontrado){
            datos = "[{\"resultado\":\"Error\"},";
			datos = datos.substring(0,datos.length()-1) + "]";
		}

    	return datos;
	}

	public String consultaTutores(String idAlumno){
		String datos="[";
    	String query;
        ResultSet tr;

        boolean encontrado=false;

        query = "SELECT * FROM tutor WHERE id_alumno = '"+idAlumno+"'";

        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            tutordp = new TutorDP();
            while(tr.next()){
            	tutordp.setIdTutor(tr.getString(1));
            	tutordp.setNombre(tr.getString(3));

                datos = datos + tutordp.toStringJson() +",";

                encontrado = true;
            }

            statement.close();

            datos = datos.substring(0,datos.length()-1) + "]";
            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }

		 if(!encontrado){
            datos = "[]";
		}

    	return datos;
	}

	public String consultarMisEventos(String idUsuario){
    	String datos="[";
    	String query;
        ResultSet tr;

        boolean encontrado=false;

        query = "SELECT r.id_registro, r.id_evento, e.nombre, r.fecha_registro FROM registro_alumno r, evento e WHERE e.id_evento = r.id_evento AND r.id_alumno = '"+idUsuario+"'";

        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            registrodp = new RegistroDP();
            while(tr.next()){
            	registrodp.setIdRegistro(tr.getString(1));
            	registrodp.setIdEvento(tr.getString(2));
            	registrodp.setNombreEvento(tr.getString(3));
            	registrodp.setFechaRegistro(tr.getString(4));

                datos = datos + registrodp.toStringJson() +",";

                encontrado = true;
            }

            statement.close();

            datos = datos.substring(0,datos.length()-1) + "]";

            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }

        if(!encontrado){
            datos = "[{\"resultado\":\"No tienes ningun evento registrado\"},";
			datos = datos.substring(0,datos.length()-1) + "]";
		}

    	return datos;
    }

	public String consultaId(String idEvento){
    	String datos="[";
    	String query;
        ResultSet tr;

        boolean encontrado=false;

        //query = "SELECT * FROM EVENTO WHERE id_evento='"+idEvento+"'";
        query = "SELECT * FROM evento INNER JOIN departamento ON evento.id_departamento = departamento.id_departamento INNER JOIN administrador ON administrador.id_administrador = evento.id_administrador WHERE evento.id_evento = '"+idEvento+"'";

        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            consultadp = new ConsultaDP();
            while(tr.next()){
            	consultadp.setIdEvent(tr.getString(1));
            	consultadp.setIdAdmin(tr.getString(2));
            	consultadp.setIdDepto(tr.getString(3));
            	consultadp.setNombre(tr.getString(4));
            	consultadp.setDateIni(tr.getString(5));
            	consultadp.setDateFin(tr.getString(6));
            	consultadp.setStatus(tr.getString(7));
            	consultadp.setCapMax(tr.getInt(8));
            	consultadp.setCapAct(tr.getInt(9));
            	consultadp.setDateIniReg(tr.getString(10));
            	consultadp.setDateIniFin(tr.getString(11));
            	consultadp.setCarta(tr.getString(12));
            	consultadp.setRequisitos(tr.getString(13));
            	consultadp.setIdLugar(tr.getString(14));
            	consultadp.setNombreDepto(tr.getString(17));
				consultadp.setNombreAdmin(tr.getString(20));
				consultadp.setAcomp(tr.getInt(15));

                //datos = consultadp.toString();
                datos = consultadp.toStringJson()+",";

                encontrado = true;
            }

            statement.close();
            datos = "["+datos.substring(0,datos.length()-1) + "]";

            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle)
        {
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }

        if(!encontrado){
            datos = "[{\"resultado\":\"No existe el evento: "+idEvento+"\"},";
			datos = datos.substring(0,datos.length()-1) + "]";
		}

    	return datos;
    }

    public String consultaNombre(String nombre, String idAlumno){
    	String datos="[";
    	String query;
        ResultSet tr;

        boolean encontrado=false;

        query = "SELECT e.id_evento, e.nombre, d.nombre, e.fecha_inicio_evento, e.status_evento FROM Evento e, Departamento d WHERE e.id_departamento = d.id_departamento AND e.nombre LIKE '%"+nombre+"%' AND e.status_evento != 'CONCLUIDO' AND e.id_evento NOT IN (SELECT id_evento FROM registro_alumno WHERE id_alumno = '"+idAlumno+"')";
        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            consultadp = new ConsultaDP();
            while(tr.next()){
            	consultadp.setIdEvent(tr.getString(1));
            	consultadp.setNombre(tr.getString(2));
            	consultadp.setNombreDepto(tr.getString(3));
            	consultadp.setDateIni(tr.getString(4));
            	consultadp.setStatus(tr.getString(5));

                datos = datos + consultadp.toStringJson() +",";

                encontrado = true;
            }

            statement.close();

            datos = datos.substring(0,datos.length()-1) + "]";

            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }

        if(!encontrado){
            datos = "[{\"resultado\":\"No existe el evento: "+nombre+"\"},";
			datos = datos.substring(0,datos.length()-1) + "]";
		}
    	return datos;
    }

	public String consultaIdBrief(String id, String idAlumno){
    	String datos="[";
    	String query;
        ResultSet tr;

        boolean encontrado=false;

        query = "SELECT e.id_evento, e.nombre, d.nombre, e.fecha_inicio_evento, e.status_evento FROM Evento e, Departamento d WHERE e.id_departamento = d.id_departamento AND e.id_evento = '"+id+"' AND e.status_evento != 'CONCLUIDO' AND e.id_evento NOT IN (SELECT id_evento FROM registro_alumno WHERE id_alumno = '"+idAlumno+"')";
        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            consultadp = new ConsultaDP();
            while(tr.next()){
            	consultadp.setIdEvent(tr.getString(1));
            	consultadp.setNombre(tr.getString(2));
            	consultadp.setNombreDepto(tr.getString(3));
            	consultadp.setDateIni(tr.getString(4));
            	consultadp.setStatus(tr.getString(5));

                datos = datos + consultadp.toStringJson() +",";

                encontrado = true;
            }

            statement.close();

            datos = datos.substring(0,datos.length()-1) + "]";

            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }

        if(!encontrado){
            datos = "[{\"resultado\":\"No existe el evento con ID: "+id+"\"},";
			datos = datos.substring(0,datos.length()-1) + "]";
		}
    	return datos;
    }

    public String registrarAlumnoEvento(String idEvento, String idAlumno, String comentario){
    	String resultado="";
    	String idEscuela="";
    	String insert;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
		Date date = new Date();
		fecha = dateFormat.format(date);
		RegistroDP registrodp = new RegistroDP();
		registrodp.setFechaRegistro(fecha);
		registrodp.setIdAlumno(idAlumno);
		registrodp.setIdEvento(idEvento);
		registrodp.setExtras(comentario);
		 //System.out.println(registrodp.toString());
		ResultSet tr;

		String query = "SELECT e.id_escuela FROM escuela e, alumno a WHERE e.id_escuela = a.id_escuela AND a.id_alumno = '"+idAlumno+"'";

		try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            consultadp = new ConsultaDP();
            while(tr.next()){
            	registrodp.setIdEscuela(tr.getString(1));

            }
            statement.close();
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
        }

		//insert = "INSERT INTO REGISTRO_ALUMNO (id_registro, id_alumno, id_escuela, id_evento, fecha_registro, extras) VALUES("+registrodp.toStringSql()+")";
		insert = "INSERT INTO REGISTRO_ALUMNO (id_alumno, id_escuela, id_evento, fecha_registro, extras) VALUES("+registrodp.toStringSql()+")";

		System.out.println(insert);

		try{
            statement = conexion.createStatement();

           	statement.executeUpdate(insert);

            statement.close();

            System.out.println(conexion.nativeSQL(insert));

            resultado = "EXITO";
			acciones++;

			enviarMail(idAlumno,idEvento);
			enviarMailTutor(idAlumno,idEvento);

        } catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            resultado = "CAPTURA FALLIDA";
        }

    	return resultado;
    }

    public void enviarMail(String idAlumno, String idEvento){
    	String query="";
    	String query2="";
    	ResultSet tr;
    	String email="";
    	String nombre="";
    	String nombreE="";
    	String registro="";
    	String idLugar="";
    	String lugar="";
    	String idAdmin="";
    	String admin="";
    	String fecha="";
    	String carta="";

		query = "SELECT a.email, a.nombre, e.nombre, e.id_lugar, e.id_administrador, e.fecha_inicio_evento, e.carta FROM alumno a, evento e WHERE a.id_alumno = '"+idAlumno+"' AND e.id_evento='"+idEvento+"'";

		try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            while(tr.next()){
            	email = tr.getString(1);
            	nombre = tr.getString(2);
            	nombreE = tr.getString(3);
            	idLugar = tr.getString(4);
            	idAdmin = tr.getString(5);
            	fecha = tr.getString(6);
            	carta = tr.getString(7);

            	System.out.println(query);
            	System.out.println(email);
            	System.out.println(carta);
            }
            statement.close();
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
        }

        query2 = "SELECT l.nombre, a.nombre FROM lugar l, administrador a WHERE l.id_lugar='"+idLugar+"' AND a.id_administrador='"+idAdmin+"'";

        	try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query2);
            while(tr.next()) {
            	lugar = tr.getString(1);
            	admin = tr.getString(2);

   				System.out.println(query2);
            }
            statement.close();
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
        }


        registro = "�FELICIDADES "+nombre+"!\nFuiste correctamente registrado al evento: "+nombreE+", administrado por: "+admin+". \nEl evento se llevara a cabo el "+fecha+" en: "+lugar+".";

        //El correo que utilices para enviar tiene que ser de gmail
        //Tambi�n tienes que dejar que "aplicaciones desconocidas" (o una wea as�) accedan a tu correo. Si no lo has hecho...
        //... te va a preguntar si quieres permitirlo.
        	Properties props = new Properties();
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.port", "587");

		    Session session = Session.getInstance(props,
		      new javax.mail.Authenticator() {
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication("pexamen8@gmail.com", "qweqweqwe");
		        }
		      });

		    try {

		        Message message = new MimeMessage(session);
		        message.setFrom(new InternetAddress("pexamen8@gmail.com"));
		        message.setRecipients(Message.RecipientType.TO,
		        InternetAddress.parse(email));
		        message.setSubject("Registro Exitoso");

				//PRUEBA
				if(carta != null){
					BodyPart messageBodyPart = new MimeBodyPart();
					messageBodyPart.setText(registro);

					Multipart multipart = new MimeMultipart();
					multipart.addBodyPart(messageBodyPart);

					messageBodyPart = new MimeBodyPart();
					//CHANGE DIRECTORY ACCORDINGLY
					String filename = "C:/Users/Esteban/Desktop/DOC.docx";
					DataSource source = new FileDataSource(filename);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(filename);
					multipart.addBodyPart(messageBodyPart);

					message.setContent(multipart);
				}else{
					message.setText(registro);
				}

				//END PRUEBA
		        //message.setText(registro);

		        Transport.send(message);

		        System.out.println("Done");

		    } catch (MessagingException e) {
		        throw new RuntimeException(e);
		    }
    }

	public void enviarMailTutor(String idAlumno, String idEvento){
    	String query="";
    	String query2="";
    	ResultSet tr;
    	String email="";
    	String emailTutor="";
    	String nombre="";
    	String nombreTutor="";
    	String nombreE="";
    	String registro="";
    	String idLugar="";
    	String lugar="";
    	String idAdmin="";
    	String admin="";
    	String fecha="";
		String parentezco = "";
		String carta = "";

		query = "SELECT t.email, t.nombre, t.parentezco, a.email, a.nombre, e.nombre, e.id_lugar, e.id_administrador, e.fecha_inicio_evento, e.carta FROM tutor t, alumno a, evento e WHERE a.id_alumno = '"+idAlumno+"' AND e.id_evento='"+idEvento+"' AND t.id_alumno = a.id_alumno";

		try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            while(tr.next()){
            	emailTutor = tr.getString(1);
            	nombreTutor = tr.getString(2);
				parentezco = tr.getString(3);
				email= tr.getString(4);
            	nombre = tr.getString(5);
            	nombreE = tr.getString(6);
            	idLugar = tr.getString(7);
            	idAdmin = tr.getString(8);
            	fecha = tr.getString(9);
            	carta = tr.getString(10);

            	System.out.println(query);
            	System.out.println(email);
            }
            statement.close();
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
        }

        query2 = "SELECT l.nombre, a.nombre FROM lugar l, administrador a WHERE l.id_lugar='"+idLugar+"' AND a.id_administrador='"+idAdmin+"'";

        	try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query2);
            while(tr.next()) {
            	lugar = tr.getString(1);
            	admin = tr.getString(2);

   				System.out.println(query2);
            }
            statement.close();
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
        }


        registro = "Estimado: "+nombreTutor+"!\n  "+parentezco+" de "+nombre+".\n "+nombre+"  Se registr� al evento: "+nombreE+", administrado por: "+admin+". \nEl evento se llevara a cabo el "+fecha+" en: "+lugar+".";

        //El correo que utilices para enviar tiene que ser de gmail
        //Tambi�n tienes que dejar que "aplicaciones desconocidas" (o una wea as�) accedan a tu correo. Si no lo has hecho...
        //... te va a preguntar si quieres permitirlo.
        	Properties props = new Properties();
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.port", "587");

		    Session session = Session.getInstance(props,
		      new javax.mail.Authenticator() {
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication("pexamen8@gmail.com", "qweqweqwe");
		        }
		      });

		    try {

		        Message message = new MimeMessage(session);
		        message.setFrom(new InternetAddress("pexamen8@gmail.com"));
		        message.setRecipients(Message.RecipientType.TO,
		        InternetAddress.parse(emailTutor));
		        message.setSubject("Registro Exitoso");

				if(carta != null){
					BodyPart messageBodyPart = new MimeBodyPart();
					messageBodyPart.setText(registro);

					Multipart multipart = new MimeMultipart();
					multipart.addBodyPart(messageBodyPart);

					messageBodyPart = new MimeBodyPart();
					//CHANGE DIRECTORY ACCORDINGLY
					String filename = "C:/Users/Esteban/Desktop/DOC.docx";
					DataSource source = new FileDataSource(filename);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(filename);
					multipart.addBodyPart(messageBodyPart);

					message.setContent(multipart);
				}else{
					message.setText(registro);
				}

		        Transport.send(message);

		        System.out.println("Done");

		    } catch (MessagingException e) {
		        throw new RuntimeException(e);
		    }
    }

	public void enviarMailAcomp(RegistroAcDP registroacdp){
    	String query="";
    	String query2="";
    	ResultSet tr;
		String nombreE = "";
		String nombreAlu = "";
		String idAdmin = "";
		String idLugar = "";
		String fecha = "";
		String lugar = "";
		String admin = "";
		String nombre = registroacdp.getNombre();
		String idAlu = registroacdp.getIdAlumno();
		String email = registroacdp.getMail();
		String idEvento = registroacdp.getIdEvento();

		query = "SELECT a.nombre, e.nombre, e.id_lugar, e.id_administrador, e.fecha_inicio_evento FROM evento e, alumno a WHERE a.id_alumno = '"+idAlu+"' AND e.id_evento='"+idEvento+"'";

		try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            while(tr.next()){

				nombreAlu = tr.getString(1);
            	nombreE = tr.getString(2);
            	idLugar = tr.getString(3);
            	idAdmin = tr.getString(4);
            	fecha = tr.getString(5);

            	System.out.println(query);
            }
            statement.close();
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
        }

        query2 = "SELECT l.nombre, a.nombre FROM lugar l, administrador a WHERE l.id_lugar='"+idLugar+"' AND a.id_administrador='"+idAdmin+"'";

        	try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query2);
            while(tr.next()) {
            	lugar = tr.getString(1);
            	admin = tr.getString(2);

   				System.out.println(query2);
            }
            statement.close();
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
        }


		String registro = "�FELICIDADES "+nombre+"!\n "+nombreAlu+" te registro correctamente al evento: "+nombreE+", administrado por: "+admin+". \nEl evento se llevara a cabo el "+fecha+" en: "+lugar+".";

        //El correo que utilices para enviar tiene que ser de gmail
        //Tambi�n tienes que dejar que "aplicaciones desconocidas" (o una wea as�) accedan a tu correo. Si no lo has hecho...
        //... te va a preguntar si quieres permitirlo.
        	Properties props = new Properties();
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.port", "587");

		    Session session = Session.getInstance(props,
		      new javax.mail.Authenticator() {
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication("pexamen8@gmail.com", "qweqweqwe");
		        }
		      });

		    try {

		        Message message = new MimeMessage(session);
		        message.setFrom(new InternetAddress("pexamen8@gmail.com"));
		        message.setRecipients(Message.RecipientType.TO,
		        InternetAddress.parse(email));
		        message.setSubject("Registro Exitoso");
		        message.setText(registro);

		        Transport.send(message);

		        System.out.println("Done");

		    } catch (MessagingException e) {
		        throw new RuntimeException(e);
		    }
    }

	public String registrarAcompanante(RegistroAcDP registroacdp){
		String resultado="";
    	String insert;

		//INSERTAMOS EL ACOMPANANTE A LA TABLA DE ACOMPANANTE
		insert = "INSERT INTO ACOMPANANTE (nombre, direccion, telefono, email) VALUES ("+registroacdp.toStringRegistroAcompSql()+")";

		ResultSet tr;

		try{
            statement = conexion.createStatement();

           	statement.executeUpdate(insert);

            statement.close();

            System.out.println(conexion.nativeSQL(insert));

            resultado = "EXITO";
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            resultado = "CAPTURA FALLIDA";
        }

    	return resultado;
	}

	public String borrarTutor(String idtutor){
		String respuesta="";
		String delete="";

		ResultSet tr;

		delete = "DELETE FROM tutor WHERE id_tutor='"+idtutor+"'";

		try{
            statement = conexion.createStatement();

           	statement.executeUpdate(delete);

            statement.close();

            System.out.println(conexion.nativeSQL(delete));
            respuesta = "EXITO";

            acciones++;
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            respuesta = "ERROR";
        }

		return respuesta;
	}

	public String borrarRegistroEvento(String idevento, String idregistro){
		String respuesta="";
		String delete="";
		String delete2="";

		ResultSet tr;

		delete = "DELETE FROM REGISTRO_ACOMPANANTE WHERE id_registro='"+idregistro+"' AND id_evento='"+idevento+"'";
		delete2 = "DELETE FROM REGISTRO_ALUMNO WHERE id_registro='"+idregistro+"' AND id_evento='"+idevento+"'";

		try{
            statement = conexion.createStatement();

           	statement.executeUpdate(delete);
           	statement.executeUpdate(delete2);

            statement.close();

            System.out.println(conexion.nativeSQL(delete));
            System.out.println(conexion.nativeSQL(delete2));
            respuesta = "EXITO";

            acciones++;
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            respuesta = "ERROR";
        }

		return respuesta;
	}

	public String actualizarNoAcompEvento2(String idEvento){
		String resultado="";
    	String insert;

		insert = "UPDATE evento SET capacidad_actual = capacidad_actual - "+acciones+" WHERE id_evento = '"+idEvento+"'";

		ResultSet tr;

		try{
            statement = conexion.createStatement();

           	statement.executeUpdate(insert);

            statement.close();

            System.out.println(conexion.nativeSQL(insert));

            resultado = "EXITO";
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            resultado = "ERROR";
        }

    	return resultado;
	}

	private int obtenerSeqAcomp(){
		int seqAcomp = -1;
		ResultSet tr;
		String query = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES  WHERE TABLE_SCHEMA = 'registratec'  AND TABLE_NAME = 'acompanante';";

        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            while(tr.next()){
				seqAcomp = tr.getInt(1);
				seqAcomp -= 1;
            }

            statement.close();

            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
        }

		return seqAcomp;
	}

	private int obtenerSeqRegistro(){
		int seqReg = -1;
		ResultSet tr;
		String query = "SELECT seq_registro.currval FROM REGISTRO_ALUMNO";

        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            while(tr.next()){
                seqReg = tr.getInt(1);
            }

            statement.close();

            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
        }

		return seqReg;
	}

	private int getAcompanantesRegistradosEvento(String idRegistro){
		ResultSet tr;
		String query = "SELECT COUNT(*) FROM registro_acompanante WHERE id_registro = "+idRegistro;
		int seqReg =-1;

		try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            while(tr.next()){
                seqReg = tr.getInt(1);
            }

            statement.close();

            System.out.println(conexion.nativeSQL(query));
			System.out.println("Registrados: "+seqReg);
			return seqReg;
        }catch(SQLException sqle){
			System.out.println("Error: "+sqle);
			return -1;
        }
	}

	private int getAcompanantesPermitidosEvento(String idEvento){
		ResultSet tr;
		String query = "SELECT no_acomp FROM evento WHERE id_evento = "+idEvento;

		int seqReg =-1;

		try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            while(tr.next()){
                seqReg = tr.getInt(1);
            }

            statement.close();

            System.out.println(conexion.nativeSQL(query));
			System.out.println("Permitidos: "+seqReg);
			return seqReg;
        }catch(SQLException sqle){
			System.out.println("Error: "+sqle);
			return -1;
        }
	}

	private int espacioParaAcompanante(String idRegistro, String idEvento){

		int registrados = getAcompanantesRegistradosEvento(idRegistro);
		int totalAcomp = getAcompanantesPermitidosEvento(idEvento);

		if(registrados < totalAcomp)
			return (totalAcomp - (totalAcomp - registrados) + 1);
		else
			return -1;
	}

    public String registrarAcompananteEvento(RegistroAcDP registroacdp){
    	String resultado="";
    	String insert;
    	String query;
		int seqAcomp = 0;
		int seqRegistro = 0;
		int no_acompanante;
		System.out.println("RESULTADO");
		//CHECAMOS QUE EL NUMERO DE ACOMPANANTES AL EVENTO DEL ALUMNO AÚN SEA MENOR AL DE LOS PERMITIDOS
		no_acompanante = espacioParaAcompanante(registroacdp.getIdRegistro(), registroacdp.getIdEvento());
		System.out.println(no_acompanante);


		if(no_acompanante > 0){


			//OBTENEMOS EL CURRENT SEQ_REGISTRO
			//seqRegistro = obtenerSeqRegistro();

			//INSERTAMOS EL ACOMPANANTE A LA TABLA DE ACOMPANANTE
			String registoAcompanante = registrarAcompanante(registroacdp);

			if(registoAcompanante.equals("EXITO")){
				//OBTENEMOS EL CURRENT SEQ_ACOMP
				seqAcomp = obtenerSeqAcomp();

				DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
				Date date = new Date();
				fecha = dateFormat.format(date);

				registroacdp.setIdAc(""+seqAcomp);
				registroacdp.setIdRegistro(""+registroacdp.getIdRegistro());
				registroacdp.setFechaRegistro(fecha);
				registroacdp.setNoAc(no_acompanante);

				insert = "INSERT INTO REGISTRO_ACOMPANANTE (no_acompanante, id_acompanante, id_registro, id_evento, fecha_registro, extras) VALUES ("+registroacdp.toStringRegistroEventoSql()+")";

				ResultSet tr;

				try{
					statement = conexion.createStatement();

					statement.executeUpdate(insert);

					statement.close();

					System.out.println(conexion.nativeSQL(insert));
					enviarMailAcomp(registroacdp);
					resultado = "EXITO";
					acciones++;
				}catch(SQLException sqle){
					System.out.println("Error: "+sqle);
					resultado = "ERROR";
				}
			}else{
				resultado = "ERROR";
			}
		}else{
			resultado = "ERROR LLENO";
		}

    	return resultado;
    }

    public String modificarInfoAlumno(RegistroAlumnoDP registroalumnodp, String idAlumno){
		String resultado="";
    	String insert;

		insert = "UPDATE alumno SET contrasena='"+registroalumnodp.getContrasena()+"', direccion='"+registroalumnodp.getDireccion()+"', telefono='"+registroalumnodp.getTelefono()+"', email='"+registroalumnodp.getEmail()+"' WHERE id_alumno = '"+idAlumno+"'";

		 System.out.println(insert);
		ResultSet tr;

		try{
            statement = conexion.createStatement();

           	statement.executeUpdate(insert);

            statement.close();

            System.out.println(conexion.nativeSQL(insert));

            resultado = "EXITO";
			realizarCommit();
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            resultado = "ERROR";
        }

		System.out.println(resultado);
    	return resultado;
	}

	public String registrarAlumno(RegistroAlumnoDP registroalumnodp, TutorDP tutordp){
    	String resultado="";
    	String insert;

		insert = "INSERT INTO ALUMNO VALUES("+registroalumnodp.toStringSql()+")";

		  System.out.println(insert);
		ResultSet tr;

		try{
            statement = conexion.createStatement();

           	statement.executeUpdate(insert);

            statement.close();

            System.out.println(conexion.nativeSQL(insert));

            resultado = "CAPTURA EXITOSA";
			resultado = registrarTutor(registroalumnodp, tutordp);
			if(resultado.equals("CAPTURA EXITOSA")){
				realizarCommit();
			}
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            resultado = "CAPTURA FALLIDA ALUMNO";
        }

    	return resultado;
    }

	public String consultarTutor(String idTutor){
		String datos="[";
		String query;
			ResultSet tr;

			boolean encontrado=false;

			query = "SELECT * FROM tutor WHERE id_tutor ="+idTutor;

			try{
					statement = conexion.createStatement();

					tr = statement.executeQuery(query);
					tr.next();
					tutordp = new TutorDP();
						tutordp.setNombre(tr.getString(3));
						tutordp.setDireccion(tr.getString(4));
						tutordp.setTelefono(tr.getString(5));
						tutordp.setEmail(tr.getString(6));
						tutordp.setParentezco(tr.getString(8));
						tutordp.setIdEscuela(tr.getString(9));

							datos = datos + tutordp.toStringJson();

							encontrado = true;

					statement.close();

					datos += "]";

					System.out.println(conexion.nativeSQL(query));
			}catch(SQLException sqle){
					datos = "ERROR";
					System.out.println("Error: "+sqle);
			}

			if(!encontrado){
					datos = "[{\"resultado\":\"No tienes ningun evento registrado\"},";
		datos = datos.substring(0,datos.length()-1) + "]";
	}

		return datos;
	}

	public String crearTutor(TutorDP tutordp){
		String resultado="";
		String insert;

		insert = "INSERT INTO TUTOR VALUES(NULL, '"+tutordp.getIdAlumno()+"','"+tutordp.getNombre()+"','"+tutordp.getDireccion()+"','"+tutordp.getTelefono()+"','"+tutordp.getEmail()+"',NULL, '"+tutordp.getParentezco()+"',"+tutordp.getIdEscuela()+")";

		System.out.println(insert);
		ResultSet tr;

		try{
						statement = conexion.createStatement();

						statement.executeUpdate(insert);

						statement.close();

						System.out.println(conexion.nativeSQL(insert));

						resultado = "CAPTURA EXITOSA";
				}catch(SQLException sqle){
						System.out.println("Error: "+sqle);
						resultado = "CAPTURA FALLIDA TUTOR";
				}

			return resultado;
	}

	public String registrarTutor(RegistroAlumnoDP registroalumnodp, TutorDP tutordp){
    	String resultado="";
    	String insert;

		tutordp.setIdAlumno(registroalumnodp.getIdAlumno());
		tutordp.setIdEscuela(registroalumnodp.getIdEscuela());
		insert = "INSERT INTO TUTOR (id_alumno, nombre, direccion, telefono, email, fecha_nacimiento, parentezco, id_escuela) VALUES("+tutordp.toStringSql()+")";

		  System.out.println(insert);
		ResultSet tr;

		try{
            statement = conexion.createStatement();

           	statement.executeUpdate(insert);

            statement.close();

            System.out.println(conexion.nativeSQL(insert));

            resultado = "CAPTURA EXITOSA";
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            resultado = "CAPTURA FALLIDA TUTOR";
        }

    	return resultado;
    }

	public String actualizarTutor(TutorDP tut){
		String resultado="";
    String update;

		update = "UPDATE tutor SET nombre = '"+tut.getNombre()+"', direccion='"+tut.getDireccion()+"', telefono='"+tut.getTelefono()+"', email='"+tut.getEmail()+"', parentezco='"+tut.getParentezco()+"', id_escuela="+tut.getIdEscuela()+" WHERE id_tutor = '"+tut.IdTutor()+"'";

		ResultSet tr;

		try{
            statement = conexion.createStatement();

           	statement.executeUpdate(update);

            statement.close();

            System.out.println(conexion.nativeSQL(update));

            resultado = "EXITO";
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            resultado = "ERROR";
        }

    	return resultado;
	}

	public String actualizarEstadoEvento(String idEvento){
		String resultado="";
    	String insert;

		insert = "UPDATE evento SET status_evento = 'LLENO' WHERE id_evento = '"+idEvento+"'";

		ResultSet tr;

		try{
            statement = conexion.createStatement();

           	statement.executeUpdate(insert);

            statement.close();

            System.out.println(conexion.nativeSQL(insert));



            resultado = "EXITO";
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            resultado = "ERROR";
        }

    	return resultado;
	}

	public String actualizarEvento(String idEvento){
		String respuesta = "";
		int actual= 0;
		int maxima = 0;
    	String query="";
    	ResultSet tr;

        boolean encontrado=false;

        query = "SELECT capacidad_actual, capacidad_maxima FROM EVENTO WHERE id_evento='"+idEvento+"'";

        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            while(tr.next()){
                actual = tr.getInt(1);
                maxima = tr.getInt(2);
            }



			if(actualizarNoAcompEvento(idEvento).equals("EXITO")){
				if((actual + acciones) >= maxima){
					if(!actualizarEstadoEvento(idEvento).equals("EXITO"))
						respuesta = "ERROR";
				}
				respuesta = "EXITO";
			}else
				respuesta = "ERROR";

            statement.close();
            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            respuesta = "ERROR";
            System.out.println("Error: "+sqle);
        }
		return respuesta;
	}

	public String actualizarEvento2(String idEvento){
		String respuesta = "";
		int actual= 0;
		int maxima = 0;
    	String query="";
    	ResultSet tr;

        boolean encontrado=false;

        query = "SELECT capacidad_actual, capacidad_maxima FROM EVENTO WHERE id_evento='"+idEvento+"'";

        try{
            statement = conexion.createStatement();

            tr = statement.executeQuery(query);
            while(tr.next()){
                actual = tr.getInt(1);
                maxima = tr.getInt(2);
            }



			if(actualizarNoAcompEvento2(idEvento).equals("EXITO")){
				respuesta = "EXITO";
			}else
				respuesta = "ERROR";

            statement.close();
            System.out.println(conexion.nativeSQL(query));
        }catch(SQLException sqle){
            respuesta = "ERROR";
            System.out.println("Error: "+sqle);
        }
		return respuesta;
	}

	public String actualizarNoAcompEvento(String idEvento){
		String resultado="";
    	String insert;

		insert = "UPDATE evento SET capacidad_actual = capacidad_actual+"+acciones+" WHERE id_evento = '"+idEvento+"'";

		ResultSet tr;

		try{
            statement = conexion.createStatement();

           	statement.executeUpdate(insert);

            statement.close();

            System.out.println(conexion.nativeSQL(insert));



            resultado = "EXITO";
        }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            resultado = "ERROR";
        }

    	return resultado;
	}

	public void realizarCommit(){
		//String resultado = "EXITO";
		try{
			conexion.commit();
		 }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            //resultado = "ERROR";
        }
		//return resultado;
	}

	public void realizarRollBack(){
		//String resultado = "EXITO";
		try{
			conexion.rollback();
		 }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            //resultado = "ERROR";
        }
		//return resultado;
	}

	public void cerrarConexion(){
		//String resultado = "EXITO";
		try{
			conexion.close();
			 System.out.println("Conexion cerrada");
		 }catch(SQLException sqle){
            System.out.println("Error: "+sqle);
            //resultado = "ERROR";
        }
		//return resultado;
	}

 }
