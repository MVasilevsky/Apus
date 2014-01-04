<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="apus.persistence.DaoFactory"%>
<%@page import="apus.persistence.jdbc.JdbcDAOFactory"%>
<%@page import="apus.application.APUS_1"%>

<%
    {
        APUS_1 myApp = new APUS_1();
        myApp.setFactory(new JdbcDAOFactory());
        myApp.init();
    }
%>
