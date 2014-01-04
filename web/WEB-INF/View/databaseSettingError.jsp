<%-- 
    Document   : error
    Created on : 13.08.2012, 0:37:26
    Author     : Vasileusky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>
<html>
    <head>
        <apus:bootstrap/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="APUS"/> :: <fmt:message key="error"/></title>
    </head>
    <body>
        <apus:top/>
        <div class="well">
            <h5><fmt:message key="db.error.occured"/></h5>
        </div>
    </body>
</html>
