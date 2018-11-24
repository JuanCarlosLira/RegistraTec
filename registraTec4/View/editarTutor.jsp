<%@ page import = "java.io.PrintWriter"%>

<%
if(session.getAttribute("user") == null){
	response.sendRedirect("../registraTec.html");
}else{
	String usuario = (String)session.getAttribute("user");
	String nombreUsuario = (String)session.getAttribute("userName");
%>

<script type="text/javascript">
  function consultarTutor(){
    //PREPARAR URL STRING
    var urlString =  "../Controller/tutor.jsp?bConsultarTutor=consultar&idTutor=<%= request.getParameter("idTutor") %>";
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
        document.getElementById("nombre").value = tutores[0].nombre;
        document.getElementById("direccion").value = tutores[0].direccion;
        document.getElementById("telefono").value = tutores[0].telefono;
        document.getElementById("mail").value = tutores[0].email;
        document.getElementById("parentezco").value = tutores[0].parentezco;
        document.getElementById("idescuela").value = tutores[0].idescuela;
				/*var htmlText = htmlText + "<table class='striped' border = 1><thead><tr><th>ID</th><th>Nombre</th><th>Accion</th></tr><thead><tbody>";

				for(i = 0; i<tutores.length; i++){
					console.log(tutores);
					htmlText += "<tr>";
					htmlText += "<td>"+tutores[i].idtutor+"</td><td>"+tutores[i].nombre+"</td>";
          htmlText += "<td><a href='../Controller/tutor.jsp?bBorrarTutor=borrar&idTutor="+tutores[i].idtutor+"'><button class='waves-effect  red darken-3 btn' >Borrar</button></a></td>";
					htmlText += "<td><a href='../View/editarTutor.jsp?idTutor="+tutores[i].idtutor+"'><button class='waves-effect  blue darken-3 btn' >Editar</button></a></td>";
					htmlText += "</tr>";
				}
				//alert();
				htmlText += "</tbody></table>";
				document.getElementById("resultado").innerHTML= htmlText;
				//var htmlText2 = '<button type="button" onclick="agregarAcompanante('+tutores[i].idregistro+','+tutores[i].idevento+')" class="btn btn-primary" data-dismiss="modal">Agregar</button>';
				//document.getElementById("botonAgregarAcompanante").innerHTML= htmlText2;*/
			}else{
				var htmlText = "<h5 class='center-align'>"+"..."+"</h5>";
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

        <!--Scripts imported to manipulate the styles-->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
   </head>
   <body>
     <script>
             $(document).ready(function(){
                 consultarTutor();
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

          <form action="../Controller/tutor.jsp" method="get">
            <input placeholder="ID Tutor" name = "idtutor" id="idtutor" type="text" value="<%= request.getParameter("idTutor") %>" readonly>
            <input placeholder="ID Alumno" name = "idalumno" id="idalumno" type="text" value="<%=usuario%>" readonly>
            <input placeholder="Nombre" name = "nombre" id="nombre" type="text">
            <input placeholder="Direccion" name = "direccion" id="direccion" type="text">
            <input placeholder="Telefono" name = "telefono" id="telefono" type="text">
            <input placeholder="E-Mail" name = "mail" id="mail" type="text">
            <input placeholder="Parentezco" name = "parentezco" id="parentezco" type="text">
            <input placeholder="ID Escuela" name = "idescuela" id="idescuela" type="text">

            <input type="hidden" value="update" name="update" />

            <center>
              <input class='btn waves-effect  blue darken-4' type='submit' name='submit'   id='submit'    value='Actualizar'>
              <!--<input class="waves-effect blue darken-2 btn" id='submit' type='submit' name = 'submit'    value = 'Actualizar'>-->
  					</center>
          </form>

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
