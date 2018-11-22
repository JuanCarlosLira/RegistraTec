<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registratec - Los eventos de tu campus en un solo lugar</title>

        <!--Styles section-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
        <link rel="stylesheet" type="text/css" href="css/styles.css">

        <!--Scripts imported to manipulate the styles-->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
        <script type="text/javascript" src="js/functions.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <form class="col s12 pushdown" method="POST" action="MainController.jsp">
                    <h4>Iniciar sesión</h4><br>
                    <label for="email">Correo electrónico</label>
                    <input type="text" name="email" id="email" value="">
                    <label for="email">Contraseña</label>
                    <input type="password" name="contrasena" id="contrasena" value="">
                    <center>
                        <input type="submit" name="action" id="action" value="Iniciar sesión" class="btn waves-effect waves-light">
                    </center>
                    <br>
                    <a href="loginprofessor.jsp?logged=no">¿Olvidó su contraseña?</a><br>
                    <div class="card-panel red darken-3 hide" id="errorlogin">
                        <span class="white-text">Revise su usuario o contraseña</span>
                    </div>
                </form>
            </div>
        </div>    
    </body>
</html>
