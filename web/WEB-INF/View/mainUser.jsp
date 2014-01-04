<%-- 
    Document   : mainUser
    Created on : 14.08.2012, 23:29:12
    Author     : Vasileusky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <apus:bootstrap /> 
        <title><fmt:message key="APUS"/> :: <c:out value="${userAuth.username}"/></title>
    </head>

    <body>
        <apus:top/>
        <c:if test="${all_paid==false}">            
            <div class="alert alert-error">
                <h3><fmt:message key="not.paid.account.exists"/></h3>
            </div></c:if>
        <c:if test="${all_paid==true}">            
            <div class="alert alert-success">
                <h3><fmt:message key="thanks.for.paying"/></h3>
            </div></c:if>
        <c:if test="${person!=null}">
            <apus:person-info/>
        </c:if>
        <c:if test="${organization!=null}">
            <apus:organization-info/>
        </c:if>
    </body>

</html>
