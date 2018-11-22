<jsp:useBean id="registrodp" class="modelRegistraTec.RegistroDP"/>
<jsp:useBean id="alumnoad" class="modelRegistraTec.AlumnoAD"/>
<jsp:setProperty name="registrodp" property = "*"/>

<%
	
	if(request.getParameter("bCancelar") == null)
	{
		response.sendRedirect("--/View/menuAlumno.jsp");
	}
	else
	{

		
		if(request.getParameter("bCancelar") != null)
		{
			String idevento = request.getParameter("idEvento");
			String idregistro = request.getParameter("idRegistro");

			if(alumnoad.borrarRegistroEvento(idevento,idregistro).equals("EXITO"))
			{
				String respuesta = alumnoad.actualizarEvento2(idevento);

				if(respuesta.equals("EXITO"))
				{
					alumnoad.realizarCommit();
					response.sendRedirect("../View/RespuestaBorrar.jsp?idEvento="+idevento+"&res="+respuesta);
				}
				else
				{
					alumnoad.realizarRollBack();

					response.sendRedirect("../View/RespuestaBorrar.jsp?idEvento="+idevento+"&res="+respuesta);
				}
			}

			else
			{
				alumnoad.realizarRollBack();
			}
		}
		
	}
%>
