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
        <div class="wall">
            <h1><fmt:message key="error.occured"/></h1>
            <p>
                <a href="./main?action=main.view" class="btn btn-primary btn-large">
                    <fmt:message key="main.page"/>
                </a>
            </p>
        </div>
    </body>
</html>
