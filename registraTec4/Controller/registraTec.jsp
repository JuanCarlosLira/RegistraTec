<%@ page import = "java.io.PrintWriter"%>
<jsp:useBean id="registroalumnodp" class="modelRegistraTec.RegistroAlumnoDP"/>
<jsp:useBean id="tutordp" class="modelRegistraTec.TutorDP"/>
<jsp:useBean id="alumnoad" class="modelRegistraTec.AlumnoAD"/>
<jsp:setProperty name="registroalumnodp" property = "*"/>
<%
	//request.getSession().removeAttribute("user");
	//request.getSession().removeAttribute("userName");
	if(request.getParameter("bIniciarSesion") == null && request.getParameter("bRegistrarme") == null && request.getParameter("bRegistrarAlumnoSistema") == null && request.getParameter("bCerrarSesion") == null && request.getParameter("bConsultarMiInfo") == null && request.getParameter("bModificarMiInfo") == null){
		response.sendRedirect("registraTec.html");

	}else{
		if(request.getParameter("bIniciarSesion") != null){
			
			String usuario = request.getParameter("user");
			String password = request.getParameter("password");
			String mensaje = "";
			if(usuario.equals("") && password.equals("")){
				mensaje = "100";
			}else{
				String respuesta = alumnoad.inicioSesion(usuario, password);
				if(respuesta.equals("ERROR")){
					mensaje = "101";
				}else if(respuesta.equals("ALUMNO_NO_LOCALIZADO")){
					mensaje = "102";
				}if(respuesta.equals("CONTRASENA_INCORRECTA")){
					mensaje = "103";
				}if(respuesta.equals("EXITO")){
					System.out.println("exito");
					mensaje = "0";
					session.setAttribute("user",usuario);  
					String nombreUsuario = alumnoad.buscarNombreUsuario(usuario);
					
					alumnoad.cerrarConexion();
					
					session.setAttribute("userName",nombreUsuario); 
					System.out.println("exito "+nombreUsuario);					
					
				}
			}	
			if(mensaje.equals("0")){
				response.sendRedirect("../View/menuAlumno2.jsp");
			}else{
				response.sendRedirect("../registraTec.html?error="+mensaje);
			}	
			
			
		}else if(request.getParameter("bCerrarSesion") != null){
			
			request.getSession().removeAttribute("user");
			request.getSession().removeAttribute("userName");
			response.sendRedirect("../registraTec.html");
			
		}else if(request.getParameter("bRegistrarme") != null){
			
			response.sendRedirect("../Registro.html");
			
		}else if(request.getParameter("bRegistrarAlumnoSistema") != null){
			
			tutordp.setNombre(request.getParameter("nombreTut"));
			tutordp.setTelefono(request.getParameter("telefonoTut"));
			tutordp.setEmail(request.getParameter("emailTut"));
			tutordp.setParentezco(request.getParameter("parentezco"));
			tutordp.setDireccion(request.getParameter("direccionTut"));
			tutordp.setFechaNacimiento(request.getParameter("fechaNacimientoTut"));
			String respuesta = alumnoad.registrarAlumno(registroalumnodp, tutordp);
			
			alumnoad.cerrarConexion();
			
			if(respuesta == "CAPTURA EXITOSA"){
				
				response.sendRedirect("../View/menuAlumno.jsp");
				
			}else{
				response.sendRedirect("../Registro.html?error="+respuesta);				
			}
			
			
		}else if(request.getParameter("bConsultarMiInfo") != null){
			String respuesta = alumnoad.consultarMiInfo(request.getParameter("idAlumno"));
			
			PrintWriter salida = response.getWriter();
			response.setContentType("text/plain\n\n");
			salida.println(respuesta);
			salida.close();
			
			System.out.println(respuesta);
			alumnoad.cerrarConexion();
			
		}else if(request.getParameter("bModificarMiInfo") != null){
			String respuesta = alumnoad.modificarInfoAlumno(registroalumnodp, request.getParameter("idAlumno"));
			alumnoad.cerrarConexion();
			response.sendRedirect("../View/infoAlumno.jsp");
		}
	}
%>