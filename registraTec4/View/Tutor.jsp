<%@ page import = "java.io.PrintWriter"%>

<%
if(session.getAttribute("user") == null){
	response.sendRedirect("../registraTec.html");
}else{
	String usuario = (String)session.getAttribute("user");
	String nombreUsuario = (String)session.getAttribute("userName");
%>

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
					<h1 class="center-align">RegistraTEC</h1>
					<h3 class="center-align">Bienvenido <%= nombreUsuario%>!</h3>
					</br>
					<h4  class="center-align">Seccion de Noticias</h4>
					<h5  class="center-align">En construccion...</h5>
				</div>

				<div class="col s6 push-s4">

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
	</body>
</html>
<%
}
%>
