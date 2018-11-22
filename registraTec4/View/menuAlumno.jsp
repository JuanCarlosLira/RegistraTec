<%@ page import = "java.io.PrintWriter"%>

<%
if(session.getAttribute("user") == null){
	response.sendRedirect("../registraTec.html");
}else{
	String usuario = (String)session.getAttribute("user");
	String nombreUsuario = (String)session.getAttribute("userName");
%>
<script language = javascript>	
	function consultarTodo(){
		//PREPARAR URL STRING
		var urlString =  "../Controller/evento.jsp?bConsultarTodo=consultar&idAlumno=<%= usuario%>";
		xhr = new XMLHttpRequest();
		//ESTABLECER CONEXIÓN CON EL SERVER
		establecerConexion(urlString);
	}
	
	function consultarMisEventos(){
		//PREPARAR URL STRING
		var urlString =  "../Controller/evento.jsp?bConsultarMisEventos=consultar&idAlumno=<%= usuario%>";
		xhr = new XMLHttpRequest();
		//ESTABLECER CONEXIÓN CON EL SERVER
		establecerConexion2(urlString);
	}
	
	function consultarNombre(){
		//OBTENER EDITORIAL DEL TF
		var nombre = document.getElementById("busqueda").value
		//CHECAR QUE NO ESTE VACÍO EL TF
		if(nombre == ""){
			alert("Llenar el campo de busqueda");
		}else{
			//PREPARAR URL STRING
			var urlString =  "../Controller/evento.jsp?bConsultarNombre=consultar&nombre="+nombre+"&idAlumno=<%= usuario%>";
			xhr = new XMLHttpRequest();
			//ESTABLECER CONEXIÓN CON EL SERVER
			establecerConexion(urlString);
		}			
	}
	
	function consultarId(){
		//OBTENER EDITORIAL DEL TF
		var id = document.getElementById("busqueda").value
		//CHECAR QUE NO ESTE VACÍO EL TF
		if(id == ""){
			alert("Llenar el campo de busqueda");
		}else{
			//PREPARAR URL STRING
			var urlString =  "../Controller/evento.jsp?bConsultarId=consultar&id="+id+"&idAlumno=<%= usuario%>";
			xhr = new XMLHttpRequest();
			//ESTABLECER CONEXIÓN CON EL SERVER
			establecerConexion(urlString);
		}			
	}
	
	function establecerConexion(urlString){
		xhr.open("GET", urlString, true);
		xhr.onreadystatechange = obtenerDatos;
		xhr.send(null);
	}
	
	function obtenerDatos(){	
		if(xhr.readyState == 4){
			
			//var libros = eval("(" + xhr.responseText + ")");
			var eventos = JSON.parse(xhr.responseText);
			if(eventos[0].resultado == null){
				var htmlText = "<h2>EVENTOS DISPONIBLES</h2><table border = 1><tr><td>ID</td><td>Nombre</td><td>Departamento</td><td>Fecha</td><td>Status</td><td>Accion</td></tr>";
				
				for(i = 0; i<eventos.length; i++){
					htmlText = htmlText + "<tr>";
					htmlText = htmlText + "<td><a href='infoEvento.jsp?eventoId="+eventos[i].idevent+"'>"+eventos[i].idevent+"</a></td><td>"+eventos[i].nombre+"</td><td>"+eventos[i].nombreDepto+"</td><td>"+eventos[i].dateini+"</td><td>"+eventos[i].status+"</td>";
					if(eventos[i].status == 'ABIERTO')
						htmlText = htmlText + "<td><a href='registroEvento.jsp?idEvento="+eventos[i].idevent+"'><button>Registrarme</button></a></td>";
					else
						htmlText = htmlText +"<td>No disponible</td>";
					htmlText = htmlText + "</tr>";
				}
				//alert();
				htmlText = htmlText + "</table>";
				document.getElementById("resultado").innerHTML= htmlText;
			}else{
				var htmlText = "<h2>"+eventos[0].resultado+"</h2>";
				document.getElementById("resultado").innerHTML= htmlText;
			}
		}
	}
	
	function establecerConexion2(urlString){
		xhr.open("GET", urlString, true);
		xhr.onreadystatechange = obtenerDatos2;
		xhr.send(null);
	}
	
	function obtenerDatos2(){	
		if(xhr.readyState == 4){
			
			//var libros = eval("(" + xhr.responseText + ")");
			var eventos = JSON.parse(xhr.responseText);
			if(eventos[0].resultado == null){
				var htmlText = htmlText + "<h2>EVENTOS A LOS QUE TE REGISTRASTE</h2><table border = 1><tr><td>ID Registro</td><td>ID Evento</td><td>Nombre Evento</td><td>fecha Evento</td><td>Fecha registro</td><td>Accion</td></tr>";
				
				
				for(i = 0; i<eventos.length; i++){
					htmlText = htmlText + "<tr>";
					htmlText = htmlText + "<td>"+eventos[i].idregistro+"</td><td>"+eventos[i].idevento+"</td><td>"+eventos[i].nombrevento+"</td><td></td><td>"+eventos[i].fecharegistro+"</td><td><a href='../Controller/borrarRegistro.jsp?bCancelar=cancelar&idEvento="+eventos[i].idevento+"&idRegistro="+eventos[i].idregistro+"'><button>Cancelar</button></a></td>";
					htmlText = htmlText + "</tr>";
				}
				//alert();
				htmlText = htmlText + "</table>";
				document.getElementById("resultado").innerHTML= htmlText;
			}else{
				var htmlText = "<h2>"+eventos[0].resultado+"</h2>";
				document.getElementById("resultado").innerHTML= htmlText;
			}
		}
	}
</script>
<html>
   <head>
   	  <title>RegistraTEC</title>
   	  <link href="rT.css" rel="stylesheet" type="text/css">
   </head>
   <body>
   <center>
		<h1>RegistraTEC</h1>
		<h2>Bienvenido <%= nombreUsuario%>!</h2>
		<form method = "post" action = "../Controller/registraTec.jsp">
			<input type="submit" name="bCerrarSesion"   id="bCerrarSesion"    value="Salir" class="boton">
		</form>
		<form method="get">
			<h3>Busca eventos disponibles: </h3>
			<input type="text" name="busqueda"  id = "busqueda" value=""><br/>	<br/>					
   	   		<input type="button" name="bConsultarTodo"   id="bConsultarTodo"    value="Buscar todos" onclick="consultarTodo()" class="boton"> <br/><br/>
			<input type="button" name="bConsultarNombre"   id="bConsultarNombre"    value="Buscar por Nombre" onclick = "consultarNombre()" class="boton"><br/><br/>
			<input type="button" name="bConsultarId"   id="bConsultarId"    value="Buscar por Id" onclick = "consultarId()" class="boton">
			
			<h3>Busca en tus eventos: </h3>
			<input type="text" name="busqueda"  id = "busqueda" value=""><br/><br/>
			<input type="button" name="bRegistrarme"   id="bRegistrarme"    value="Buscar mis eventos" onclick = "consultarMisEventos()" class="boton"><br/>
		</form>
		<div id="resultado"></div>
		</center>
	</body>
</html>
<%
}
%> 