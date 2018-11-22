<jsp:useBean id="administradordp" class="profesorModel.AdministradorDP"/>
<jsp:useBean id="profesorad" class="profesorModel.ApplicationLogic"/>
<jsp:useBean id="eventodp" class="profesorModel.EventoDP"/>
<jsp:useBean id="lugardp" class="profesorModel.LugarDP"/>
<jsp:useBean id="ponentedp" class="profesorModel.PonenteDP"/>
<jsp:useBean id="escueladp" class="profesorModel.EscuelaDP"/>
<jsp:useBean id="alumnodp" class="profesorModel.AlumnoDP"/>
<jsp:useBean id="listaponentesdp" class="profesorModel.ListaPonentesDP"/>
<jsp:setProperty name="administradordp" property="*"/>
<jsp:setProperty name="profesorad" property="*"/>
<jsp:setProperty name="eventodp" property="*"/>
<jsp:setProperty name="lugardp" property="*"/>
<jsp:setProperty name="ponentedp" property="*"/>
<jsp:setProperty name="listaponentesdp" property="*"/>
<jsp:setProperty name="escueladp" property="*"/>
<jsp:setProperty name="alumnodp" property="*"/>

<%@ page import = "java.io.PrintWriter"%>

<%
if(request.getParameter("action") != null){
    switch(request.getParameter("action")){
        case "Iniciar sesión":
            String passed = profesorad.logInProfe(request.getParameter("email"),request.getParameter("contrasena"));
            if(passed.equals("NO_LOCALIZADO") || passed.equals("ERROR")){

                response.sendRedirect("loginprofessor.jsp");
            }else{
                String [] tokens = passed.split("_");
                session.setAttribute("login",true);
                session.setAttribute("user","adm");
                session.setAttribute("userid",tokens[0]);
                session.setAttribute("department",tokens[1]);
                session.setAttribute("username",tokens[2]);
                response.sendRedirect("inicio.jsp");
            }
            break;

        case "crearevento":
            eventodp.setIdAdmin((String)session.getAttribute("userid"));
            eventodp.setIdDep((String)session.getAttribute("department"));
            eventodp.setCarta("files/carta.docx");
            eventodp.setReq("reqs");

            String crEvent = profesorad.crearEvento(eventodp);

            PrintWriter eventop = response.getWriter();
            response.setContentType("text/plain\n\n");
            eventop.println(crEvent);
            eventop.close();
            break;
            
        case "crearlugar":
            String insertLugar = profesorad.creaLugar(lugardp);

            PrintWriter printer = response.getWriter();
            response.setContentType("text/plain\n\n");
            printer.println(insertLugar);
            printer.close();
            break;
        
        case "crearponente":

            String insertPo = profesorad.creaPonente(ponentedp);

            PrintWriter printerPonente = response.getWriter();
            response.setContentType("text/plain\n\n");
            printerPonente.println(insertPo);
            printerPonente.close();
            break;
        
        case "crearescuelas":
            String inserEscuela = profesorad.insertEscuela(escueladp);

            PrintWriter printerEscuela= response.getWriter();
            response.setContentType("text/plain\n\n");
            printerEscuela.println(inserEscuela);
            printerEscuela.close();
            break;

        case "crearalumno":
            String insertAlumno = profesorad.insertEstudiante(alumnodp);

            PrintWriter printerAlumno= response.getWriter();
            response.setContentType("text/plain\n\n");
            printerAlumno.println(insertAlumno);
            printerAlumno.close();
            break;

        case "creardpto":
            String insertDpto = profesorad.crearDepto(request.getParameter("depto"));

            PrintWriter printerDeptoWrite = response.getWriter();
            response.setContentType("text/plain\n\n");
            printerDeptoWrite.println(insertDpto);
            printerDeptoWrite.close();
            break;

        case "crearAdmin":
            String inAdm = profesorad.crearAdmin(administradordp);

            PrintWriter printerAdminWriuter = response.getWriter();
            response.setContentType("text/plain\n\n");
            printerAdminWriuter.println(inAdm);
            printerAdminWriuter.close();
            break;

        case "linkpoe":
            String res = profesorad.linkPonenteEvento(listaponentesdp);
            PrintWriter pw = response.getWriter();
            response.setContentType("text/plain\n\n");
            pw.println(res);
            pw.close();
            break;

        case "linkf":
            String resultsss = profesorad.agregarCarta(eventodp,"AAAA");
            PrintWriter pwm = response.getWriter();
            response.setContentType("text/plain\n\n");
            pwm.println(resultsss);
            pwm.close();
            break;
    
        case "soloeventos":
            String eventos = profesorad.consultaEventos();
            PrintWriter serap = response.getWriter();
            response.setContentType("text/plain\n\n");
            serap.println(eventos);
            serap.close();
            break;

        case "obtenerlugares":
            String lugares = profesorad.obtenerLugares();
            PrintWriter writerr = response.getWriter();
            response.setContentType("text/plain\n\n");
            writerr.println(lugares);
            writerr.close();
            break;
        
        case "obtenerponentes":
            String ponentes = profesorad.obtenerPonentes();
            PrintWriter placesWriterShow = response.getWriter();
            response.setContentType("text/plain\n\n");
            placesWriterShow.println(ponentes);
            placesWriterShow.close();
            break;
        
        case "obtenerescuelas":
            String escuelas = profesorad.obtenerEscuelas();
            
            PrintWriter schoolsWriterShow = response.getWriter();
            response.setContentType("text/plain\n\n");
            schoolsWriterShow.println(escuelas);
            schoolsWriterShow.close();
            break;

        case "obteneralumnos":
            String alumnos = profesorad.obtenerEstudiantes();
            
            PrintWriter alWrSh = response.getWriter();
            response.setContentType("text/plain\n\n");
            alWrSh.println(alumnos);
            alWrSh.close();
            break;
        
        case "obtenerdeptos":
            String depas = profesorad.getDeptos();

            PrintWriter pwDep = response.getWriter();
            response.setContentType("text/plain\n\n");
            pwDep.println(depas);
            pwDep.close();            
            break;

        case "obteneradmins":
            String admins = profesorad.getAdmins();

            PrintWriter pwAdmin = response.getWriter();
            response.setContentType("text/plain\n\n");
            pwAdmin.println(admins);
            pwAdmin.close();            
            break;
        
        case "eventoponentelugar":
            String recevents = profesorad.eventosRecientes();
            
            PrintWriter evWr = response.getWriter();
            response.setContentType("text/plain\n\n");
            evWr.println(recevents);
            evWr.close();
            break;

        case "eventlugar":
            String eventossss =  profesorad.getEventos();
            PrintWriter impresora = response.getWriter();
            response.setContentType("text/plain\n\n");
            impresora.println(eventossss);
            impresora.close();        

    }
}
%>