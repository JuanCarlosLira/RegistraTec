<%@ page import = "java.io.PrintWriter"%>
<jsp:useBean id="registrodp" class="modelRegistraTec.RegistroDP"/>
<jsp:useBean id="registroacdp" class="modelRegistraTec.RegistroAcDP"/>
<jsp:useBean id="alumnoad" class="modelRegistraTec.AlumnoAD"/>
<jsp:useBean id="consultadp" class="modelRegistraTec.ConsultaDP"/>
<jsp:setProperty name="consultadp" property = "*"/>
<jsp:setProperty name="registrodp" property = "*"/>
<jsp:setProperty name="registroacdp" property = "*"/>

<%
	
	if(request.getParameter("bConsultarTodo") == null && request.getParameter("bConsultarNombre") == null && request.getParameter("bConsultarId") == null && request.getParameter("bConsultarMisEventos") == null && request.getParameter("bConsultarIdFull") == null && request.getParameter("bRegistrarAlumnoEvento") == null && request.getParameter("bRegistrarAcompanante") == null){
		response.sendRedirect("../View/menuAlumno.jsp");
	}else{
		if(request.getParameter("bConsultarTodo") != null){
			String respuesta = alumnoad.consulta(request.getParameter("idAlumno"));
			alumnoad.cerrarConexion();
			PrintWriter salida = response.getWriter();
			response.setContentType("text/plain\n\n");
			salida.println(respuesta);
			salida.close();
			
			System.out.println(respuesta);
		}else if(request.getParameter("bConsultarNombre") != null){
			String respuesta = alumnoad.consultaNombre(request.getParameter("nombre"), request.getParameter("idAlumno"));
			alumnoad.cerrarConexion();
			PrintWriter salida = response.getWriter();
			response.setContentType("text/plain\n\n");
			salida.println(respuesta);
			salida.close();
			
			System.out.println(respuesta);
		}else if(request.getParameter("bConsultarId") != null){
			String respuesta = alumnoad.consultaIdBrief(request.getParameter("id"), request.getParameter("idAlumno"));
			alumnoad.cerrarConexion();
			PrintWriter salida = response.getWriter();
			response.setContentType("text/plain\n\n");
			salida.println(respuesta);
			salida.close();
			
			System.out.println(respuesta);
		}else if(request.getParameter("bConsultarIdFull") != null){
			System.out.println(request.getParameter("id"));
			String respuesta = alumnoad.consultaId(request.getParameter("id"));
			alumnoad.cerrarConexion();
			PrintWriter salida = response.getWriter();
			response.setContentType("text/plain\n\n");
			salida.println(respuesta);
			salida.close();
			
			System.out.println(respuesta);
		}else if(request.getParameter("bConsultarMisEventos") != null){
			String respuesta = alumnoad.consultarMisEventos(request.getParameter("idAlumno"));
			alumnoad.cerrarConexion();
			PrintWriter salida = response.getWriter();
			response.setContentType("text/plain\n\n");
			salida.println(respuesta);
			salida.close();
			
			System.out.println(respuesta);
		}else if(request.getParameter("bRegistrarAlumnoEvento") != null){
			int numero = alumnoad.noAcompEvento(request.getParameter("idEvento"));
			String respuesta = alumnoad.registrarAlumnoEvento(request.getParameter("idEvento"), request.getParameter("idAlumno"), request.getParameter("comentario"));
			
			if(respuesta.equals("EXITO")){
				if(alumnoad.actualizarEvento(request.getParameter("idEvento")).equals("EXITO")){
					alumnoad.realizarCommit();
					alumnoad.cerrarConexion();
					//AQUÍ SE ENVÍA CORREO A USUARIO
					response.sendRedirect("../View/RespuestaRegistro.jsp?res=100");
				}else
					response.sendRedirect("../View/RespuestaServer.jsp?res=102");
			}else if(respuesta.equals("ERROR1"))
				response.sendRedirect("../View/RespuestaServer.jsp?res=101");
			else if(respuesta.equals("ERROR"))
				response.sendRedirect("../View/RespuestaServer.jsp?res=102");
			
		}else if(request.getParameter("bRegistrarAcompanante") != null){
			String respuesta = "";
			registroacdp.setNombre(request.getParameter("nombre"));
			
			System.out.println("nombre: "+request.getParameter("nombre"));
			registroacdp.setDireccion(request.getParameter("direccion"));
			registroacdp.setTelefono(request.getParameter("telefono"));
			registroacdp.setMail(request.getParameter("email"));
			registroacdp.setExtras(request.getParameter("comentario"));
			registroacdp.setIdEvento(request.getParameter("idevento"));
			registroacdp.setIdRegistro(request.getParameter("idregistro"));
			//registroacdp.setNoAc(i);
			registroacdp.setIdAlumno(request.getParameter("idalumno"));
			respuesta = alumnoad.registrarAcompananteEvento(registroacdp);

			System.out.println(respuesta);
			if(respuesta.equals("EXITO")){
				//alumnoad.realizarRollBack();
				response.sendRedirect("../View/RespuestaRegistro.jsp?res=103");
			}else if(respuesta.equals("ERROR")){
				response.sendRedirect("../View/RespuestaServer.jsp?res=101");
			}else if(respuesta.equals("ERROR LLENO")){
				response.sendRedirect("../View/RespuestaServer.jsp?res=103");
			}
		}			
	}
%>