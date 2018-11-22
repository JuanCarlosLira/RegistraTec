<%@ page import = "java.io.PrintWriter"%>

<!--<jsp:useBean id="alumnoad" class="modelRegistraTec.AlumnoAD"/>-->

<%
if(session.getAttribute("user") == null){
	response.sendRedirect("../registraTec.html");
}else{
	String usuario = (String)session.getAttribute("user");
	String nombreUsuario = (String)session.getAttribute("userName");
%>
<script language = javascript>	
	function checkRegistration() {
		var inputs = form.getElementsByTagName('input');
		var valid = true;
		for (var i = 0; i < inputs.length; i++) {
			if(inputs[i].value == ""){
				alert("Favor de llenar todos los campos...");
				valid = false
				break;
			}       
		}		
		return valid; 		
	}

	function consultarMiInfo(){
		//PREPARAR URL STRING
		var urlString =  "../Controller/registraTec.jsp?bConsultarMiInfo=consultarInfo&idAlumno=<%= usuario%>";
		xhr = new XMLHttpRequest();
		//ESTABLECER CONEXIÓN CON EL SERVER
		establecerConexion(urlString);
	}
	
	function establecerConexion(urlString){
		xhr.open("GET", urlString, true);
		xhr.onreadystatechange = obtenerDatos;
		xhr.send(null);
	}
	
	function obtenerDatos(){	
		if(xhr.readyState == 4){
			
			//var libros = eval("(" + xhr.responseText + ")");
			var alumno = JSON.parse(xhr.responseText);
			if(alumno[0].resultado == null){
				i=0;
				var htmlText = htmlText + "<table class='striped' border = 1>";
				
				htmlText += "<tr>";
				htmlText += "<td>NOMBRE: </td><td><input type='text' name='nombre' value='"+alumno[i].nombre+"' readonly></input></td>";
				htmlText += "</tr>";
				htmlText += "<tr>";
				htmlText += "<td>CONTRASENA: </td><td><input type='password'  name='contrasena' value='"+alumno[i].contrasena+"'></input></td>";
				htmlText += "</tr>";
				htmlText += "<tr>";
				htmlText += "<td>DIRECCION: </td><td><input type='text'  name='direccion' value='"+alumno[i].direccion+"'></input></td>";
				htmlText += "</tr>";
				htmlText += "<tr>";
				htmlText += "<td>TELEFONO: </td><td><input type='text'  name='telefono' value='"+alumno[i].telefono+"'></input></td>";
				htmlText += "</tr>";
				htmlText += "<tr>";
				htmlText += "<td>EMAIL: </td><td><input type='text'  name='email' value='"+alumno[i].email+"'></input></td>";
				htmlText += "</tr>";
				htmlText += "<tr>";
				htmlText += "<td>FECHA NAC: </td><td><input type='text'  value='"+alumno[i].fechanacimiento+"'readonly></input></td>";
				htmlText += "</tr>";
				htmlText += "<tr>";
				htmlText += "<td>PROGRAMA: </td><td><input type='text'  value='"+alumno[i].programa+"' readonly></input></td>";
				htmlText += "</tr>";
				htmlText += "<tr>";
				htmlText += "<td>SEMESTRE: </td><td><input type='number'  value='"+alumno[i].semestre+"'readonly></input></td>";
				htmlText += "</tr>";
				htmlText += "<tr>";
				htmlText += "<td>ESCUELA: </td><td><input type='text'  value='"+alumno[i].idescuela+"' readonly></input></td>";
				htmlText += "</tr>";
				htmlText += "<tr>";
				htmlText += "<td><input class='btn waves-effect  blue darken-4' type='submit' name='bActualizarDatos'   id='bActualizarDatos'    value='Actualizar datos' colspan = '2'></td>";
				htmlText += "</tr>";
				//alert();
				htmlText = htmlText + "</table>";
				document.getElementById("resultado").innerHTML= htmlText;
			}else{
				var htmlText = "<h5 class='center-align'>"+eventos[0].resultado+"</h5>";
				document.getElementById("resultado").innerHTML= htmlText;
			}
		}
	}
</script>
		
<html>
   <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>RegistraTEC</title>
		<!--<link href="rT.css" rel="stylesheet" type="text/css">-->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
        <link rel="stylesheet" type="text/css" href="css/styles.css">

        <!--Scripts imported to manipulate the styles-->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
   </head>
   <body onload = "consultarMiInfo()">
    <script>
            $(document).ready(function(){
                $('select').material_select();     
                $(".button-collapse").sideNav();
                $('.collapsible').collapsible();
            })
	</script>
	
		
		<aside class="left-sidebar-nav">
            <ul id="slide-out" class="side-nav fixed leftside-navigations">
                <li class="user-details blue darken-2"><div>
                    <div class="">
                        <img src="images/tec.jpg" class="ico">
                    </div>
                    <a href="infoAlumno.jsp"><span class="blue darken-2"><h4><%= nombreUsuario%></h4></span></a>
                </div></li>
                <li><a href="menuAlumno2.jsp">Inicio</a></li>
               
                <li class="bold"><a href="Eventos.jsp" class="">Eventos Disponibles</a></li>   
                <li class="bold"><a href="misEventos.jsp" class="">Mis Eventos</a></li>   
                <li class="bold"><a href="../Controller/registraTec.jsp?bCerrarSesion=salir" class="">Cerrar Sesion</a></li>     
            </ul>
            <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
        </aside>
		
		
		<div class="row">
			<div class="container">
				<div class="col s6 push-s4">
					<h1 class="center-align">RegistraTEC</h1>
					<h5 class="center-align"><%= nombreUsuario%>, esta es tu informacion: </h5>
					<h6 class="center-align">Haz click sobre un campo para editarlo</h6>
					</br>
					</br>
					<form id = "form" method = "POST" action="../Controller/registraTec.jsp?bModificarMiInfo=modificar&idAlumno=<%= usuario%>" onsubmit="return checkRegistration()">
						<div id="resultado"></div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
<%
}
%> 