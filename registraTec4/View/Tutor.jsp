<%@ page import = "java.io.PrintWriter"%>

<%
if(session.getAttribute("user") == null){
	response.sendRedirect("../registraTec.html");
}else{
	String usuario = (String)session.getAttribute("user");
	String nombreUsuario = (String)session.getAttribute("userName");
%>

<script language = javascript>

	var eventos;

	function checkRegistration() {

	var form = document.getElementById("formAcompanante");
	var inputs = form.getElementsByTagName('input');
		for (var i = 0; i < inputs.length; i++) {
			if(inputs[i].value == ""){
				return false;
			}
		}
		return true;
	}

	function agregarAcompanante(idRegistro, idEvento){

		if(checkRegistration()){
			alert("id Registro: "+idRegistro+"\nid Evento: "+idEvento+"\nid Alumno: <%= usuario%>");

			//PREPARAR URL STRING
			//var urlString =  "../Controller/evento.jsp?bRegistrarAcompanante=agregar&idAlumno=<%= usuario%>&idEvento="+idEvento+"&idRegistro="+idRegistro+"";
			//xhr = new XMLHttpRequest();
			//ESTABLECER CONEXIÓN CON EL SERVER
			//establecerConexion(urlString);

		}else{
			alert("Favor de llenar todos los datos...");
		}
	}

	function establecerConexion(urlString){
		xhr.open("GET", urlString, true);
		xhr.onreadystatechange = obtenerDatos3;
		xhr.send(null);
	}

	function obtenerDatos3(){
		if(xhr.readyState == 4){

			//var libros = eval("(" + xhr.responseText + ")");

		}
	}

	function consultarMisEventos(){
		//PREPARAR URL STRING
		var urlString =  "../Controller/tutor.jsp?bConsultarMisTutores=consultar&idAlumno=<%= usuario%>";
		xhr = new XMLHttpRequest();
		//ESTABLECER CONEXIÓN CON EL SERVER
		establecerConexion2(urlString);
	}

	function establecerConexion2(urlString){
		xhr.open("GET", urlString, true);
		xhr.onreadystatechange = obtenerDatos2;
		xhr.send(null);
	}

	function obtenerDatos2(){
		if(xhr.readyState == 4){

			var tutores = JSON.parse(xhr.responseText);
			if(tutores.length > 0){
				var htmlText = htmlText + "<table class='striped' border = 1><thead><tr><th>ID</th><th>Nombre</th><th>Accion</th></tr><thead><tbody>";

				for(i = 0; i<tutores.length; i++){
					console.log(tutores);
					htmlText += "<tr>";
					htmlText += "<td>"+tutores[i].idtutor+"</td><td>"+tutores[i].nombre+"</td>";
          htmlText += "<td><a href='../Controller/tutor.jsp?bBorrarTutor=borrar&idTutor="+tutores[i].idtutor+"'><button class='waves-effect  red darken-3 btn' >Borrar</button></a></td>";
          //htmlText += "<td><a href='../Controller/evento.jsp?bAñadirAcompañante=Añadir&idEvento="+tutores[i].idevento+"&idRegistro="+tutores[i].idregistro+"'><button class='waves-effect  blue darken-3 btn' >Agregar_Acompanante</button></a></td>";
					htmlText += "<td><button onclick='modifyButton("+i+")' data-toggle='modal' data-target='#exampleModal' id = 'add-acompanante' class='waves-effect  blue darken-3 btn'>Eitar</button></td>";
					htmlText += "</tr>";
				}
				//alert();
				htmlText += "</tbody></table>";
				document.getElementById("resultado").innerHTML= htmlText;
				//var htmlText2 = '<button type="button" onclick="agregarAcompanante('+tutores[i].idregistro+','+tutores[i].idevento+')" class="btn btn-primary" data-dismiss="modal">Agregar</button>';
				//document.getElementById("botonAgregarAcompanante").innerHTML= htmlText2;
			}else{
				var htmlText = "<h5 class='center-align'>"+"..."+"</h5>";
				document.getElementById("resultado").innerHTML= htmlText;
			}
		}
	}

	function modifyButton(i){
		//var htmlText2 = '<button type="button" onclick="agregarAcompanante('+eventos[i].idregistro+','+eventos[i].idevento+')" class="btn btn-primary" data-dismiss="modal">Agregar</button>';
		//document.getElementById("botonAgregarAcompanante").innerHTML= htmlText2;
		var htmlText2 = '<input type="hidden" id="idregistro" name="idregistro" type="text" class="validate" value="'+eventos[i].idregistro+'">';
		htmlText2 += '<input type="hidden" id="idevento" name="idevento" type="text" class="validate" value="'+eventos[i].idevento+'">';
		document.getElementById("hiddenFields").innerHTML= htmlText2
	}
</script>

<html>
   <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>RegistraTEC</title>
		<!--<link href="rT.css" rel="stylesheet" type="text/css">-->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">

        <!--Scripts imported to manipulate the styles-->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
   </head>
   <body>
     <strong></strong>
     <script>
             $(document).ready(function(){
                 consultarMisEventos();
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

                <li class="bold"><a href="Eventos.jsp" class="">Eventos Disponibles</a></li>
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
					<h5 class="center-align">Tutores:</h5>
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
