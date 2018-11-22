<%@ page import = "java.io.PrintWriter"%>
<%@ page import="java.util.StringTokenizer" %>


<%
	if(session.getAttribute("user") == null){
		response.sendRedirect("../registraTec.html");
		
	}else{
		String usuario = (String)session.getAttribute("user");
		String nombreUsuario = (String)session.getAttribute("userName");
		String idEvento = request.getParameter("eventoId");
		System.out.println(usuario+" - "+nombreUsuario+" - "+idEvento);
%>
<script language = javascript>
	function getError(){
		let params = (new URL(location)).searchParams;
		var error = params.get('error');
		
	}
	
	function consultarEvento(idEvento){
		//PREPARAR URL STRING
		var urlString =  "../Controller/evento.jsp?bConsultarIdFull=consulta&id="+idEvento;
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
				var htmlText = 'Nombre Evento:<input type="text" name="password" id = "password" value="'+eventos[0].nombre+'" readonly><br/>';
				htmlText += 'Nombre Admin:<input type="text" name="name"  id = "name" value="'+eventos[0].nombreadmin+'" readonly><br/>';
				htmlText += 'Nombre Departamento:<input type="text" name="phone"  id = "phone" value="'+eventos[0].nombreDepto+'" readonly><br/>';
				htmlText += 'Fecha Inicio:<input type="text" name="address"  id = "address" value="'+eventos[0].dateini+'" readonly><br/>';
				
				if(eventos[0].datefin != "null")
					htmlText += 'Fecha Termino:<input type="text" name="fecha"  id = "fecha" value="'+eventos[0].datefin+'" readonly><br/>';
				else
					htmlText += 'Fecha Termino:<input type="text" name="fecha"  id = "fecha" value="No se especifico fecha de termino" readonly><br/>';
				
				htmlText += 'Status:<input type="text" name="programa"  id = "programa" value="'+eventos[0].status+'" readonly><br/>';
				htmlText += 'Cap Max:<input type="text" name="semestre"  id = "semestre" value="'+eventos[0].capmax+'" readonly><br/>';
				htmlText += 'Cap Actual:<input type="text" name="escuela"  id = "escuela" value="'+eventos[0].capact+'" readonly><br/>';
				
				if(eventos[0].carta != "null")
					htmlText += 'Carta:<input type="text" name="escuela"  id = "escuela" value="Se enviara a tu correo cuando te registres" readonly><br/>';
				else
					htmlText += 'Carta:<input type="text" name="escuela"  id = "escuela" value="Este evento no requiere carta responsiva" readonly><br/>';
				
				if(eventos[0].requisitos == "null")
					htmlText += 'Requisitos:<input type="text" name="escuela"  id = "escuela" value="Este evento no tiene requisitos especiales" readonly><br/>';
				else
					htmlText += 'Requisitos:<input type="text" name="escuela"  id = "escuela" value="'+eventos[0].requisitos+'" readonly><br/>';
				
				htmlText += 'Id Lugar:<input type="text" name="escuela"  id = "escuela" value="'+eventos[0].idlugar+'" readonly></br>';
				htmlText += 'Acompanantes<input type="text" name="acomp" id="acomp" value = "'+eventos[0].acomp+'"readonly><br/>';
				if(eventos[0].status == 'ABIERTO')
					htmlText = htmlText +'<a href="../View/registroEvento.jsp?idEvento='+eventos[0].idevent+'"><button class="waves-effect blue darken-2 btn" >Registrarme</button></a>';
				
				htmlText = htmlText +'<a href = "Eventos.jsp"><button class="waves-effect  red darken-3 btn" >Regresar</button></a>';
				//alert();
				document.getElementById("resultado").innerHTML= htmlText;
			
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
					<h5 class="center-align"><%= nombreUsuario%>, esta es la informacion del evento <%= idEvento%>:</h5>
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