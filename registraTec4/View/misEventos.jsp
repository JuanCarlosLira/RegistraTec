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
		var urlString =  "../Controller/evento.jsp?bConsultarMisEventos=consultar&idAlumno=<%= usuario%>";
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
			
			//var libros = eval("(" + xhr.responseText + ")");
			eventos = JSON.parse(xhr.responseText);
			if(eventos[0].resultado == null){
				var htmlText = htmlText + "<table class='striped' border = 1><thead><tr><th>ID</th><th>Nombre</th><th>Fecha</th><th>Accion</th></tr><thead><tbody>";
				
				for(i = 0; i<eventos.length; i++){
					console.log(eventos);
					htmlText += "<tr>";
					htmlText += "<td>"+eventos[i].idregistro+"</td><td>"+eventos[i].nombrevento+"</td><td>"+eventos[i].fecharegistro+"</td><td><a href='../Controller/borrarRegistro.jsp?bCancelar=cancelar&idEvento="+eventos[i].idevento+"&idRegistro="+eventos[i].idregistro+"'><button class='waves-effect  red darken-3 btn' >Cancelar</button></a></td>";
					//htmlText += "<td><a href='../Controller/evento.jsp?bAñadirAcompañante=Añadir&idEvento="+eventos[i].idevento+"&idRegistro="+eventos[i].idregistro+"'><button class='waves-effect  blue darken-3 btn' >Agregar_Acompanante</button></a></td>";
					htmlText += "<td><button onclick='modifyButton("+i+")' data-toggle='modal' data-target='#exampleModal' id = 'add-acompanante' class='waves-effect  blue darken-3 btn'>Agregar_Acompanante</button></td>";
					htmlText += "</tr>";
				}
				//alert();
				htmlText += "</tbody></table>";
				document.getElementById("resultado").innerHTML= htmlText;
				//var htmlText2 = '<button type="button" onclick="agregarAcompanante('+eventos[i].idregistro+','+eventos[i].idevento+')" class="btn btn-primary" data-dismiss="modal">Agregar</button>';
				//document.getElementById("botonAgregarAcompanante").innerHTML= htmlText2;
			}else{
				var htmlText = "<h5 class='center-align'>"+eventos[0].resultado+"</h5>";
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
        <!--<link rel="stylesheet" type="text/css" href="css/styles.css">-->

        <!--Scripts imported to manipulate the styles-->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
		<!--<script type="text/javascript" src="js/functions.js"></script>-->
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>


   </head>
   <body onload = "consultarMisEventos()">
    <script>
            $(document).ready(function(){
               // $('select').material_select();     
               // $(".button-collapse").sideNav();
               // $('.collapsible').collapsible();
                consultarMisEventos();
            })
        </script>
		
		
		<aside class="left-sidebar-nav">
            <ul id="slide-out" class="side-nav fixed leftside-navigations">
                <li class="user-details blue darken-2"><div>
                    <div class="">
                       <!-- <img src="images/tec.jpg" class="ico">-->
                    </div>
                    <a href="infoAlumno.jsp"><span class="blue darken-2"><h4><%= nombreUsuario%></h4></span></a>
                </div></li>
                <li><a href="menuAlumno2.jsp">Inicio</a></li>
               
                <li class="bold"><a  href="Eventos.jsp" class="">Eventos Disponibles</a></li>   
                <li class="bold"><a href="misEventos.jsp" class="">Mis Eventos</a></li>   
                <li class="bold"><a href="../Controller/registraTec.jsp?bCerrarSesion=salir" class="">Cerrar Sesion</a></li>     
            </ul>
            <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
        </aside>
		
		<div class="row">
			<div class="container">
				<div class="col s6 push-s4">
					<h2 class="center-align">RegistraTEC</h2>
					<h5 class="center-align">Eventos a los que te has registrado:</h5>
					</br>
					</br>
					<div id="resultado"></div>
					
				</div>
			</div>
		</div>
		<!--<form method = "post" action = "../Controller/registraTec.jsp">
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
		<div id="resultado"></div>-->

		

		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Agregar Acompanante</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form id="formAcompanante" onsubmit="return checkRegistration()" action="../Controller/evento.jsp?idAlumno=<%= usuario%>" method="post">
						<div class="modal-body">
							<div class="input-field col s6" id="hiddenFields">
								<!--<input type="hidden" id="nombre" type="text" class="validate" >-->
							</div>
							<div class="input-field col s6">
								<input name="nombre" id="nombre" type="text" class="validate">
								<label for="nombre">Nombre</label>
							</div>
							<div class="input-field col s6">
								<input name="direccion" id="direccion" type="text" class="validate">
								<label for="direccion">Direccion</label>
							</div>
							<div class="input-field col s6">
								<input name="telefono" id="telefono" type="text" class="validate">
								<label for="telefono">Telefono</label>
							</div>
							<div class="input-field col s6">
								<input name="email" id="email" type="text" class="validate">
								<label for="correo">E-mail</label>
							</div>
							<div class="input-field col s6">
								<input name="comentario" id="comentario" type="text" class="validate">
								<label for="comentario">Comentarios</label>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
							<div id="botonAgregarAcompanante">
								<!--<button type="button" onclick="agregarAcompanante()" class="btn btn-primary" data-dismiss="modal">Agregar</button>-->
							</div>
							<input type="submit" name="bRegistrarAcompanante" id="bRegistrarAcompanante" value="submit" class="btn btn-primary" values="Agregar acompanante">
						</div>
						
					</form>
				</div>
			</div>
		</div>


	</body>
</html>
<%
}
%> 