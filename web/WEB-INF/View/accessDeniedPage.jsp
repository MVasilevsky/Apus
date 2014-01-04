<%-- 
    Document   : accessDeniedPage
    Created on : 15.08.2012, 15:05:25
    Author     : Vasileusky
--%>
<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>
<html>
    <head>
        <apus:bootstrap/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="APUS"/> :: <fmt:message key="access.denied"/></title>
    </head>
    <body>
        <apus:top/>
        <h1 style="margin-bottom: 30px"><fmt:message key="access.denied"/></h1>
        <a href="javascript:history.go(-1)" class="btn btn-primary"><fmt:message key="back"/></a>
    </body>
</html>
