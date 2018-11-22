<%
	String idevento = request.getParameter("idEvento");
	String res = request.getParameter("res");
%>
<html>
   <head>
   	  <title>RegistraTEC</title>
   	   <link href="rT.css" rel="stylesheet" type="text/css">
   </head>
   	  <body>
		<center>
		<h1>RegistraTEC</h1>
<%
	if(res.equals("ERROR"))
	{
%>
		<h2>Error al borrar el registro al evento con id: <%=idevento%>!</h2>
<%
	}

	else
	{
%>
		<h2>Tu registro al evento con id <%=idevento%> fue exitosamente borrado</h2>
<%
	}
%>

	<a href = "menuAlumno2.jsp"><button class="boton">Aceptar</button></a>
		</center>
	</body>
</html>