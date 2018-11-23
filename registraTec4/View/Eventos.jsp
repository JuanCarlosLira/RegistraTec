<%@ page import = "java.io.PrintWriter"%>

<%
if(session.getAttribute("user") == null){
	response.sendRedirect("../registraTec.html");
}else{
	String usuario = (String)session.getAttribute("user");
	String nombreUsuario = (String)session.getAttribute("userName");
%>
<script language = javascript>
	function consultar(){
		//OBTENER VALOR SELECT
		var e = document.getElementById("tipo");
		var strUser = e.options[e.selectedIndex].text;

		if(strUser == "Buscar Todos")
			consultarTodo();
		else if(strUser == "Buscar ID")
			consultarId();
		else if(strUser == "Buscar Nombre")
			consultarNombre();
	}

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
				var htmlText = "<table class='striped' border = 1><thead><tr><th>ID</th><th>Nombre</th><th>Departamento</th><th>Fecha</th><th>Status</th><th>Accion</th></tr><thead><tbody>";

				for(i = 0; i<eventos.length; i++){
					htmlText = htmlText + "<tr>";
					htmlText = htmlText + "<td><a href='infoEvento.jsp?eventoId="+eventos[i].idevent+"'>"+eventos[i].idevent+"</a></td><td>"+eventos[i].nombre+"</td><td>"+eventos[i].nombreDepto+"</td><td>"+eventos[i].dateini+"</td><td>"+eventos[i].status+"</td>";
					if(eventos[i].status == 'ABIERTO')
						htmlText = htmlText + "<td><a href='registroEvento.jsp?idEvento="+eventos[i].idevent+"'><button class='waves-effect blue darken-2 btn'>Registrarme</button></a></td>";
					else
						htmlText = htmlText +"<td>No disponible</td>";
					htmlText = htmlText + "</tr>";
				}
				//alert();
				htmlText = htmlText + "</tbody></table>";
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
   <body>
    <script>
            $(document).ready(function(){
                $('select').material_select();
                $(".button-collapse").sideNav();
                $('.collapsible').collapsible();
                mostrarEventos();
            })
        </script>


		<aside class="left-sidebar-nav">
            <ul id="slide-out" class="side-nav fixed leftside-navigations">
                <li class="user-details blue darken-2"><div>
                    <div class="">
                        <img src="../images/tec.jpg" class="ico">
                    </div>
                    <a href="infoAlumno.jsp"><span class="blue darken-2"><h4><%= nombreUsuario%></h4></span></a>
                </div></li>
                <li><a href="menuAlumno2.jsp">Inicio</a></li>

                <li class="bold"><a  href="Eventos.jsp" class="">Eventos Disponibles</a></li>
                <li class="bold"><a href="misEventos.jsp" class="">Mis Eventos</a></li>
								<li class="bold"><a href="Tutor.jsp" class="">Tutores</a></li>
                <li class="bold"><a href="../Controller/registraTec.jsp?bCerrarSesion=salir" class="">Cerrar Sesion</a></li>
            </ul>
            <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
        </aside>

		<div class="row">
			<div class="container">
				<div class="col s6 push-s4">
					<h2 class="center-align">RegistraTEC</h2>
					<h5 class="center-align">Busca eventos disponibles:</h5>

					<input placeholder="Busqueda..." name = "busqueda" id="busqueda" type="text">
					<select id = "tipo">
						<option value="1" >Buscar Todos</option>
						<option value="2">Buscar ID</option>
						<option value="3">Buscar Nombre</option>
					</select>
					<center>
						<button class="waves-effect blue darken-2 btn" name="bConsultarId"   id="bConsultarId" onclick = "consultar()">Buscar</button>
					</center>

					</br>
					</br>
					<div id="resultado"></div>

				</div>
			</div>
		</div>
	</body>
</html>
<%
}
%>
