<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import = "javax.servlet.http.*" %>
<%@ page import = "org.apache.commons.fileupload.*" %>
<%@ page import = "org.apache.commons.fileupload.disk.*" %>
<%@ page import = "org.apache.commons.fileupload.servlet.*" %>
<%@ page import = "org.apache.commons.io.output.*" %>

<%
    String data = "[";
    ServletContext context = pageContext.getServletContext();


    File[] files = new File(context.getInitParameter("file-upload")).listFiles(); 

    if(files.length > 0){
        for(File file : files){
            if(file.isFile()){
                data = data + "{file:'"+file.getName()+"'},";
            }
        }
    		
        data=data.substring(0,data.length()-1)+"]";
    }else{
        data = "{msg:'No file selected'}";
    }

    out.println(data);
%>

