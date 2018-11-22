<%
    if(session == null || session.getAttribute("login") == null){
        response.sendRedirect("loginprofessor.jsp");
    }
%>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>RegistraTec - Los eventos de tu campus en un solo lugar</title>

        <!--Styles section, we use frameworks in this part to save time-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
        <link rel="stylesheet" type="text/css" href="css/styles.css">

        <!--Scripts imported to manipulate the styles-->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
    </head>
    <body onload="menuLugares()">
        <script>
            $(document).ready(function(){
                $('select').material_select();     
                $(".button-collapse").sideNav();
                $('.collapsible').collapsible();
            })
        </script>
        <!--Navbar side-->
        
        <aside class="left-sidebar-nav">
            <ul id="slide-out" class="side-nav fixed leftside-navigations">
                <li class="user-details cyan darken-2"><div>
                    <div class="">
                        <img src="images/tec.jpg" class="ico">
                    </div>
                    <a href="#!name"><span class="">Bienvenido <%=session.getAttribute("username")%></span></a>
                </div></li>
                <li><a href="inicio.jsp"><i class="material-icons">home</i>Inicio</a></li>
                <li class="no-padding">
                    <ul class="collapsible collapsible-accordion">
                        <li id="" class="bold"><a class="collapsible-header"><i class=""></i>Eventos</a>
                            <div class="collapsible-body">
                                <ul>
                                    <li class="bold "><a href="crearevento.jsp" class="">Crear evento</a></li>
                                    <li class="bold"><a href="ligareventoponente.jsp">Ponentes de evento</a></li>
                                    <li class="bold"><a href="subircarta.jsp">Subir carta</a></li><li class="bold"><a href="cartaparaevento.jsp">Añadir carta</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </li>
                <li>
                    <ul class="collapsible collapsible-accordion">
                        <li id="" class="bold"><a class="collapsible-header"><i class=""></i>Lugares</a>
                            <div class="collapsible-body">
                                <ul>
                                    <li class="bold "><a href="crearlugar.jsp" class="">Crear lugar</a></li>
                                    <li class="bold"><a href="verlugares.jsp">Consultar Lugares</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </li>
                <li>
                    <ul class="collapsible collapsible-accordion">
                        <li id="" class="bold"><a class="collapsible-header"><i class=""></i>Ponentes</a>
                            <div class="collapsible-body">
                                <ul>
                                    <li class="bold "><a href="crearponente.jsp" class="">Crear ponente</a></li>
                                    <li class="bold "><a href="verponentes.jsp" class="">Consultar ponentes</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </li>
                <li>
                    <ul class="collapsible collapsible-accordion">
                        <li id="" class="bold"><a class="collapsible-header"><i class=""></i>Alumnos</a>
                            <div class="collapsible-body">
                                <ul>
                                    <li class="bold "><a href="crearalumno.jsp" class="">Registrar alumno</a></li>
                                    <li class="bold "><a href="veralumnos.jsp" class="">Ver alumnos</a></li>
                                    <li class="bold"><a href="crearescuela.jsp" class="">Registrar escuela</a></li>
                                    <li class="bold"><a href="verescuelas.jsp" class="">Consultar escuelas</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </li>
                <li>
                    <ul class="collapsible collapsible-accordion">
                        <li id="" class="bold"><a class="collapsible-header"><i class=""></i>Administración</a>
                            <div class="collapsible-body">
                                <ul>
                                    <li class="bold"><a href="creardepto.jsp" class="">Registrar depto</a></li>
                                    <li class="bold"><a href="verdeptos.jsp" class="">Consultar depto</a></li>
                                    <li class="bold"><a href="crearadmin.jsp" class="">Crear admin</a></li>
                                    <li class="bold"><a href="veradmins.jsp" class="">Ver admins</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
            <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
        </aside>
        
        <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
        <div class="container">
            <div class="container">
                <h2>Creación de eventos</h2>
                <form id="eventForm">
                    <label>Nombre del evento</label>
                    <input type="text" maxlenght="15" name="nombre" required>
                    <br>
                    <label>Fecha de inicio del evento</label>
                    <input type="date" name="FInE" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}">
                    <label>Fecha de término del evento</label>
                    <input type="date" name="FTerE" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}">
                    <label>Estado del evento</label>
                    <input type="text" value="ABIERTO" name="status" readonly>
                    <label>Máximo de alumnos</label>
                    <input type="number" min="3" max="100" name="cMax" required>
                    <label>Fecha de inicio de registro</label>
                    <input type="date" name="FInR" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}">
                    <label>Fecha de termino de registro</label>
                    <input type="date" name="FInTer" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}">
                    <div class="input-field">
                        <select name="idLug" id="idLug">
                            <option value="" disabled>Seleccione un lugar</option>           
                        </select>
                        <label>Lugar</label>
                    </div>
                    <br>
                    <label>Numero de acompañantes</label>
                    <input type="number" name="acomp" required min="0" max="10" value="0">
                    <input type="button" name="action" value="Crear evento" class="btn waves-effect waves-light"  onclick="crearEvento();" id="ev">
                </form>
                <br>
                <br>
            </div>
        </div>                   
    </body>
</html>