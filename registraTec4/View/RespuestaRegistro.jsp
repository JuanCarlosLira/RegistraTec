<%
if(session.getAttribute("user") == null){
	response.sendRedirect("../registraTec.html");		
}else{
	String usuario = (String)session.getAttribute("user");
	String nombreUsuario = (String)session.getAttribute("userName");
	String respuesta = request.getParameter("res");
%>
<html>
   <head>
   	  <title>RegistraTEC</title>
   	   <link href="rT.css" rel="stylesheet" type="text/css">
   </head>
		<center>
		<h1>RegistraTEC</h1>
<%
	if(respuesta.equals("100")){
%>
		<h2>Se ha realizado el registro correctamente!</h2>
		<h2>Se envio un correo con los datos del registro</h2>
<%
	}else if(respuesta.equals("101")){
%>
		<h2>Error al realizar el registro!</h2>
		<h2>Hubo un error al registrar acompañantes, vuelve a intentarlo.</h2>
<%
	}else if(respuesta.equals("102")){
%>
		<h2>Error al realizar el registro!</h2>
		<h2>Hubo un error al registrarte al evento, vuelve a intentarlo.</h2>
<%
	}else if(respuesta.equals("103")){
%>
		<h2>Se ha realizado el registro del acompanante correctamente!</h2>
		<h2>Se le envio un correo con los datos del registro</h2>
<%
	}
	else{
		response.sendRedirect("menuAlumno2.jsp");
	}
%>
		<a href = "menuAlumno2.jsp"><button class="boton">Aceptar</button></a>
		</center>
	</body>
</html>
<%
}
%>