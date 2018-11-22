
<%
if(session.getAttribute("user") == null){
	response.sendRedirect("../registraTec.html");		
}else{
	String usuario = (String)session.getAttribute("user");
	String nombreUsuario = (String)session.getAttribute("userName");
	String idEvento = request.getParameter("idEvento");
%>
<script language = javascript>
	var counterAcomp = 0;
	
	function checkRegistration() {
	var inputs = form.getElementsByTagName('input');
	var valid = true;
		for (var i = 0; i < inputs.length; i++) {
			if(inputs[i].value == ""){
				alert("Favor de llenar todos los datos...");
				valid = false
				break;
			}       
		}		
		return valid; 		
	}

	function agregarCamposAcompanante(noAcomp){
		
		counterAcomp++;
		if(counterAcomp == 1){
			var divAc = document.getElementById("acompanantes");
			divAc.insertAdjacentHTML( "afterend", '<div id= "button"><input class="waves-effect red lighten-2 btn" type="button" onclick = "quitarCamposAcompanante()" id = "bQuitarAcompanante" value="Quitar Acompanante" class="boton"></input></	div>');
		}
		if(noAcomp >= counterAcomp){
			var divAc = document.getElementById("acompanantes");
			divAc.insertAdjacentHTML( "beforeend", "<div id='acompanante"+counterAcomp+"'><h5>Acompanante "+counterAcomp+":</h5> </br>Nombre:<input type='text' name='nombre"+counterAcomp+"'  id = 'nombre"+counterAcomp+"' value=''></br><br/>Direccion:<input type='text' name='direccion"+counterAcomp+"'  id = 'direccion"+counterAcomp+"' value=''></br><br/>Telefono:	<input type='text' name='telefono"+counterAcomp+"' id = 'telefono"+counterAcomp+"' value=''></br><br/>Email:<input type='text' name='email"+counterAcomp+"'  id = 'email"+counterAcomp+"' value=''></br></br>Comentarios(Opcionales): <textarea form = 'form' id = 'comentario"+counterAcomp+"' name = 'comentario"+counterAcomp+"' maxlength = '50'></textarea></br></div>");
			if(noAcomp == counterAcomp){
				document.getElementById("bAgregarAcompanante").style.display = 'none';
			}
		}
	}
	
	function quitarCamposAcompanante(){
		var div = document.getElementById("acompanantes");
		var div2 = "acompanante"+counterAcomp;
		document.getElementById("bAgregarAcompanante").style.display = 'initial';
		var acompanante = document.getElementById(div2);
		div.removeChild(acompanante);
		counterAcomp--;
		if(counterAcomp == 0){
			var button = document.getElementById("button");
			button.removeChild(document.getElementById("bQuitarAcompanante"));
			
		}
	}
	
	function consultarEvento(idEvento){
		//PREPARAR URL STRING
		var urlString =  "../Controller/evento.jsp?bConsultarIdFull=consultar&id="+idEvento;
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
			var eventos = JSON.parse(xhr.responseText);
			if(eventos[0].resultado == null){
				var htmlText = '<h4><%= nombreUsuario%>, confirma tu registro para: '+eventos[0].nombre+'</h4><form id = "form" onsubmit="return checkRegistration()" action="../Controller/evento.jsp?idAlumno=<%= usuario%>&idEvento='+eventos[0].idevent+'"  method="post"></br>';

				htmlText = htmlText + "Id Evento: "+eventos[0].idevent+"</br>Fecha Inicio: "+eventos[0].dateini+"</br>Departamento Organizador: "+eventos[0].nombreDepto+"</br>Comentarios (Opcional): <textarea form = 'form' id = 'comentario' name = 'comentario' maxlength = '50'></textarea>";
				if(eventos[0].acomp > 0)
					htmlText = htmlText+'</br>Puedes llevar hasta '+eventos[0].acomp+' acompanantes</br><div id = "acompanantes">';
					//htmlText += '</div><input class="waves-effect blue lighten-3 btn"  type="button" onclick = "agregarCamposAcompanante('+eventos[0].acomp+')" id = "bAgregarAcompanante" value="Agregar Acompanante" class="boton"></input>';
				if(eventos[0].status == 'ABIERTO')
					htmlText = htmlText + '<input class="waves-effect blue darken-2 btn" type="submit" name="bRegistrarAlumnoEvento"   id="bRegistrarAlumnoEvento"    value="Registrarme!" class="boton">';
				else
					htmlText = htmlText +"Upss, parece que las inscripciones a este evento no estan disponibles...";
				htmlText = htmlText + "</form>";
				htmlText = htmlText + '<a href= "menuAlumno2.jsp"><button class="waves-effect  red darken-3 btn" >Cancelar</button></a>';
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
   <body onload = "consultarEvento('<%= idEvento%>')">
   
   <aside class="left-sidebar-nav">
        <ul id="slide-out" class="side-nav fixed leftside-navigations">
            <li class="user-details blue darken-2"><div>
                <div class="">
                    <img src="images/tec.jpg" class="ico">
                </div>
                <a href="infoAlumno.jsp"><span class="blue darken-2"><h4><%= nombreUsuario%></h4></span></a>
            </div></li>
            <li><a href="menuAlumno2.jsp">home</i>Inicio</a></li>
           
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
					</br>
					</br>
					<div id="resultado"></div>
					
				</div>
			</div>
		</div>
	</body>
</html>
<%
		if(request.getParameter("bRegistrarAlumnoSistema") != null){
			//String respuesta = alumnoad.registrarAlumnoEvento(registrodp);
	

		}
	}
%>