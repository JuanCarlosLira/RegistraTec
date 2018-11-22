/**
 * File to control all the interactions with the pages 
 */

function crearEvento(){
    var urlData = $("#eventForm").serialize()+"&action=crearevento";
    console.log("datos"+urlData);
    
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: urlData,
        method: 'GET',
        dataType:'text',
        success: function (result) {
            alert(result);
        },
        error: function (response) {
            alert("Su petición no pudo procesarse");
        }
    });
}

function crearLugar(){
    var urlData = $("#placeForm").serialize()+"&action=crearlugar";
    console.log("datos"+urlData);
    
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: urlData,
        method: 'GET',
        dataType:'text',
        success: function (result) {
            alert(result);
        },
        error: function (response) {
            alert("Su petición no pudo procesarse");
        }
    });
}

function crearPonente(){
    var urlData = $("#ponentForm").serialize()+"&action=crearponente";
    console.log("datos"+urlData);
    
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: urlData,
        method: 'GET',
        dataType:'text',
        success: function (result) {
            alert(result);
        },
        error: function (response) {
            alert("Su petición no pudo procesarse");
        }
    });
}

function crearAlumno(){
    var urlData = $("#alumnoForm").serialize()+"&action=crearalumno";
    console.log("datos"+urlData);
    
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: urlData,
        method: 'GET',
        dataType:'text',
        success: function (result) {
            alert(result);
        },
        error: function (response) {
            alert("Su petición no pudo procesarse");
        }
    });
}

function crearEscuela() {
    var urlData = $("#schoolForm").serialize()+"&action=crearescuelas";
    console.log("datos"+urlData);
    
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: urlData,
        method: 'GET',
        dataType:'text',
        success: function (result) {
            alert(result);
        },
        error: function (response) {
            alert("Su petición no pudo procesarse");
        }
    });
}

function crearDepto(){
    var urlData = $("#deptForm").serialize()+"&action=creardpto";
    console.log("datos"+urlData);
    
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: urlData,
        method: 'GET',
        dataType:'text',
        success: function (result) {
            alert(result);
        },
        error: function (response) {
            alert("Su petición no pudo procesarse");
        }
    });
}

function crearAdmin() {
    var urlData = $("#adminForm").serialize()+"&action=crearAdmin";
    console.log("datos"+urlData);
    
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: urlData,
        method: 'GET',
        dataType:'text',
        success: function (result) {
            alert(result);
        },
        error: function (response) {
            alert("Su petición no pudo procesarse");
        }
    });
}

function linkPonent(){
    var formSerialize = $("#ponenteRegistro").serialize();
    console.log("Datos: "+formSerialize);

    $.ajax({
        url: "../registratec/MainController.jsp",
        data: formSerialize+"&action=linkpoe",
        method: 'GET',
        dataType: "text",
        success: function(response){
            alert(response);
        },
        error: function(){
            alert("Su petición no pudo procesarse");
        }
    });
}

function linkFiles(){
    var formSerialize = $("#cartas").serialize();
    console.log("Datos: "+formSerialize);

    $.ajax({
        url: "../registratec/MainController.jsp",
        data: formSerialize+"&action=linkf",
        method: 'GET',
        dataType: "text",
        success: function(response){
            alert(response);
        },
        error: function(){
            alert("Su petición no pudo procesarse");
        }
    });
}

function eventPonente(){
    var eventid = $("#idEve").val();

    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=eventoponente&idEve="+eventid,
        method: 'GET',
        dataType: "text",
        success: function(textJson){
            var html = "<tr><th>ID de ponente</th><th>Nombre de ponente</th><th>Id de evento</th><th>Nombre de evento</th><th>Fecha de inicio</th></tr>";
            var response = eval(textJson);

            for(i = 0; i<response.length; i++){
                html+="<tr><td>"+response[i].id_ponente+"</td><td>"+response[i].nombre+"</td><td>"+response[i].id_evento+"</td><td>"+response[i].nombreEvento+"</td><td>"+response[i].fechaInicio+"</td></tr>";
            }

            $("#results").html(html);
            //$("#results").html(textJson);
 
        },
        error: function(){
            alert("Su petición no pudo procesarse");
        }
    });
}

function lugarEvento(){
    var lugar = $("#lugar").val();

    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=lugarevento&lugar="+lugar,
        method: 'GET',
        dataType: "text",
        success: function(textJson){
            var html = "<tr><th>Nombre de lugar</th><th>Direccion</th><th>Teléfono</th><th>Nombre de evento</th><th>Fecha de inicio</th></tr>";
            var response = eval(textJson);

            for(i = 0; i<response.length; i++){
                html+="<tr><td>"+response[i].nombrelugar+"</td><td>"+response[i].direccion+"</td><td>"+response[i].telefono+"</td><td>"+response[i].nombre+"</td><td>"+response[i].fechainicio+"</td></tr>";
            }

            $("#results").html(html);
        },
        error: function(){
            alert("Su petición no pudo procesarse");
        }
    });
}

function mostrarPonentes(){
    //alert("carga");
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=obtenerponentes",
        method: 'GET',
        dataType: "text",
        success: function(textJson){
            console.log(textJson);
            var response = eval(textJson);

            if(response.msg){
                alert("Error al procesar la peticion");
            }

            response.forEach(function (item) {
                $("#ponentes").append(
                    "<tr>"+
                        "<td>"+item.nombre+"</td>"+
                        "<td>"+item.direccion+"</td>"+
                        "<td>"+item.telefono+"</td>"+
                        "<td>"+item.email+"</td>"+
                    "</tr>"
                );
            })
        },
        error: function(){
            alert("Su petición no pudo procesarse");
        }
    });
}

function mostrarLugares(){
    //alert("carga");
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=obtenerlugares",
        method: 'GET',
        dataType: "text",
        success: function(textJson){
            console.log(textJson);
            var response = eval(textJson);

            if(response.msg){
                alert("Error al procesar la peticion");
            }

            response.forEach(function (item) {
                $("#lugares").append(
                    "<tr>"+
                        "<td>"+item.nombre+"</td>"+
                        "<td>"+item.direccion+"</td>"+
                        "<td>"+item.telefono+"</td>"+
                    "</tr>"
                );
            })
        },
        error: function(){
            alert("Su petición no pudo procesarse");
        }
    });
}

function mostrarAlumnos(){
    //alert("carga");
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=obteneralumnos",
        method: 'GET',
        dataType: "text",
        success: function(textJson){
            console.log(textJson);
            var response = eval(textJson);

            if(response.msg){
                alert("Error al procesar la peticion");
            }

            response.forEach(function (item) {
                $("#alumnos").append(
                    "<tr>"+
                        "<td>"+item.idAlumno+"</td>"+
                        "<td>"+item.nombre+"</td>"+ 
                        "<td>"+item.email+"</td>"+
                        "<td>"+item.fechaNacimiento+"</td>"+
                        "<td>"+item.programa+"</td>"+
                        "<td>"+item.semestre+"</td>"+
                    "</tr>"
                );
            })
        },
        error: function(obj){
            console.log(obj);
            alert("Su petición no pudo procesarse");
        }
    });
}

function mostrarEscuelas(){
    //alert("carga");
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=obtenerescuelas",
        method: 'GET',
        dataType: "text",
        success: function(textJson){
            console.log(textJson);
            var response = eval(textJson);

            if(response.msg){
                alert("Error al procesar la peticion");
            }

            response.forEach(function (item) {
                $("#escuelas").append(
                    "<tr>"+
                        "<td>"+item.nombre+"</td>"+
                        "<td>"+item.direccion+"</td>"+
                        "<td>"+item.telefono+"</td>"+
                    "</tr>"
                );
            })
        },
        error: function(){
            alert("Su petición no pudo procesarse");
        }
    });
}

function mostrarDeptos() {
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=obtenerdeptos",
        method: 'GET',
        dataType: "text",
        success: function(textJson){
            console.log(textJson);
            var response = eval(textJson);

            if(response.msg){
                alert("Error al procesar la peticion");
            }

            response.forEach(function (item) {
                $("#deptos").append(
                    "<tr>"+
                        "<td>"+item.idDpto+"</td>"+
                        "<td>"+item.nombreDpto+"</td>"+
                    "</tr>"
                );
            })
        },
        error: function(){
            alert("Su petición no pudo procesarse");
        }
    });
}

function mostrarAdmins() {
    //alert("carga");
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=obteneradmins",
        method: 'GET',
        dataType: "text",
        success: function(textJson){
            console.log(textJson);
            var response = eval(textJson);

            if(response.msg){
                alert("Error al procesar la peticion");
            }

            response.forEach(function (item) {
                $("#admins").append(
                    "<tr>"+
                        "<td>"+item.id_administrador+"</td>"+
                        "<td>"+item.nombre+"</td>"+ 
                        "<td>"+item.telefono+"</td>"+
                        "<td>"+item.posicion+"</td>"+
                        "<td>"+item.fecha_nacimiento+"</td>"+
                        "<td>"+item.email+"</td>"+
                    "</tr>"
                );
            })
        },
        error: function(obj){
            console.log(obj);
            alert("Su petición no pudo procesarse");
        }
    });
}

function menuEscuelas() {
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=obtenerescuelas",
        method: 'GET',
        dataType: 'text',
        success: function(jsonText){
            console.log(jsonText);
            
            if(jsonText === "ERROR" || jsonText === "["){
                alert("Error al recuperar escuelas");
                $("#ev").props('disabled',true);
            }else{
                var response = eval(jsonText);
                response.forEach(function (item) {
                    $("#idEscuela").append("<option value='"+item.idEscuela+"'>"+item.nombre+"</option>");
                   // console.log("Resultado: "+item);       
                    $('select').material_select();     
                });
            }
        },
        error: function (error) {
            alert("No se pudieron cargar todos los elementos de esta pagina");
        }
    });
}

function menuLugares(){
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=obtenerlugares",
        method: 'GET',
        dataType: 'text',
        success: function(jsonText){
            console.log(jsonText);
            var response = eval(jsonText);
            if(response.msg){
                alert("Error al recuperar eventos");
                $("#ev").props('disabled',true);
            }else{
                response.forEach(function (item) {
                    $("#idLug").append("<option value='"+item.id_lugar+"'>"+item.nombre+"</option>");
                    console.log("Resultado: "+item);       
                    $('select').material_select();     
                });
            }
        },
        error: function (error) {
            alert("No se pudieron cargar todos los elementos de esta pagina");
        }
    });
}

function menuEventos(){
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=soloeventos",
        method: 'GET',
        dataType: 'text',
        success: function(jsonText){
            console.log(jsonText);
            var response = eval(jsonText);
            if(response.msg){
                alert("Error al recuperar eventos");
                $("#ev").props('disabled',true);
            }else{
                response.forEach(function (item) {
                    $("#idEve").append("<option value='"+item.id_evento+"'>"+item.nombre+"</option>");
                  //  console.log("Resultado: "+item);       
                    $('select').material_select();     
                });

                menuPonentes();
            }
        },
        error: function (error) {
            alert("No se pudieron cargar todos los elementos de esta pagina");
        }
    });
}

function menuFiles(){
    $.ajax({
        url: "../registratec/FileGetExtension.jsp",
        method: 'GET',
        dataType: 'text',
        success: function(jsonText){
            console.log(jsonText);
            var response = eval(jsonText);
            if(response.msg){
                alert("No se han subido archivos al sistema");
                $("#ev").props('disabled',true);
            }else{
                response.forEach(function (item) {
                    $("#carta").append("<option value='"+item.file+"'>"+item.file+"</option>");
                  //  console.log("Resultado: "+item);       
                    $('select').material_select();     
                });
            }
        },
        error: function (error) {
            alert("No se pudieron cargar todos los elementos de esta pagina");
        }
    });
}

function menuPonentes(){
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=obtenerponentes",
        method: 'GET',
        dataType: 'text',
        success: function(jsonText){
            console.log(jsonText);
            var response = eval(jsonText);
            if(response.msg){
                alert("Error al recuperar ponentes");
                $("#ev").props('disabled',true);
            }else{
                response.forEach(function (item) {
                    $("#idPon").append("<option value='"+item.id_ponente+"'>"+item.nombre+"</option>");
                    ///console.log("Resultado: "+item);       
                    $('select').material_select();     
                });
            }
        },
        error: function (error) {
            alert("No se pudieron cargar todos los elementos de esta pagina");
        }
    });
}

function menuDeptos() {
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=obtenerdeptos",
        method: 'GET',
        dataType: 'text',
        success: function(jsonText){
            console.log(jsonText);
            var response = eval(jsonText);
            if(response.msg){
                alert("Error al recuperar ponentes");
                $("#ev").props('disabled',true);
            }else{
                response.forEach(function (item) {
                    $("#idDepto").append("<option value='"+item.idDpto+"'>"+item.nombreDpto+"</option>");
                    ///console.log("Resultado: "+item);       
                    $('select').material_select();     
                });
            }
        },
        error: function (error) {
            alert("No se pudieron cargar todos los elementos de esta pagina");
        }
    });
}

function mostrarEventos(){
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=eventoponentelugar",
        method: 'GET',
        dataType: 'text',
        success: function(jsonText){
            console.log(jsonText);
            var response = eval(jsonText);
            if(response.msg){
               // alert("No hay eventos recientes que mostrar");
            }else{
                $("#header2").html("");
                $("#header2").html("<tr>"+
                    "<th>Nombre del evento</th>"+
                    "<th>Fecha de inicio</th>"+
                    "<th>Fecha de cierre</th>"+
                    "<th>Lugar</th>"+
                    "<th>Ponente(s)</th>"+
                "</tr>");
                response.forEach(function (item) {
                    $("#ponentbody").append(
                        "<tr>"+
                            "<td>"+item.nombreEvento+"</td>"+
                            "<td>"+item.fechaInicio+"</td>"+
                            "<td>"+item.fechaTermino+"</td>"+
                            "<td>"+item.nombreLugar+"</td>"+
                            "<td>"+item.nombrePonente+"</td>"+                            
                        "</tr>"
                    );
                });
            }
        },
        error: function (error) {
            alert("No se pudieron cargar todos los elementos de esta pagina");
        }
    });
}

function mostrarEventosSinPonente(){
    $.ajax({
        url: "../registratec/MainController.jsp",
        data: "action=eventlugar",
        method: 'GET',
        dataType: 'text',
        success: function(jsonText){
            console.log(jsonText);
            var response = eval(jsonText);
            if(response.msg){
               // alert("No hay eventos recientes que mostrar");
            }else{
                $("#header1").html("");
                $("#header1").html("<tr>"+
                    "<th>Nombre del evento</th>"+
                    "<th>Fecha de inicio</th>"+
                    "<th>Fecha de cierre</th>"+
                    "<th>Lugar</th>"+
                "</tr>");
                response.forEach(function (item) {
                    $("#eventbody").append(
                        "<tr>"+
                            "<td>"+item.nombreEvento+"</td>"+
                            "<td>"+item.fechaInicio+"</td>"+
                            "<td>"+item.fechaTermino+"</td>"+
                            "<td>"+item.nombreLugar+"</td>"+
                        "</tr>"
                    );
                });
            }
        },
        error: function (error) {
            alert("No se pudieron cargar todos los elementos de esta pagina");
        }
    });
}