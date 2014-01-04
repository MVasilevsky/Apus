<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/HTML4/strict.dtd">
<fmt:setLocale value="${lang}"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf8">
        <title><fmt:message key="APUS"/> :: <fmt:message key="new.account"/></title>
    </head>
    <body>

        <h1><fmt:message key="new.account"/></h1>
        <h2><fmt:message key="for.subscriber"/> 
        <c:out value="${subscriber.name}"/>
        </h2>
        <form method="POST" action="./main?action=account.save">
            
            <input type="hidden" name="id" value="0">
            <input type="hidden" name="sub_id" value="<c:out value="${subscriber.id}">">
                <input type="hidden" name="sub_type" value="<c:if test="${subscriber.subscriberType eq 'person'}"><c:out value="0"/></c:if><c:if test="${subscriber.subscriberType eq 'organization'}"><c:out value="1"/></c:if>">
            
            <label for="startDate"><fmt:message key="start.date"/></label>
            <input type="text" id="startDate" name="startDate" value="01.01.2011"><br>
            
            <label for="endDate"><fmt:message key="end.date"/></label>
            <input type="text" id="endDate" name="endDate" value="31.01.2011"><br>
            
            <input type="submit" name="saveButton" value="<fmt:message key="new.account"/>">
            <input type="submit" name="cancelButton" value="<fmt:message key="cancel"/>">
        </form>
    </body>
</html>
