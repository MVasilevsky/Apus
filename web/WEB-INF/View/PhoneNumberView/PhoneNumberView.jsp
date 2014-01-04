<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>

<html>
    <head>
        <apus:bootstrap/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="APUS"/> :: <fmt:message key="view.number"/></title>
        <script type="text/javascript" src="js/jquery-1.7.2.js">  </script>
        <script type="text/javascript" src="js/bootstrap-tooltip.js">  </script>
        <script type="text/javascript" src="js/tooltips.js"> </script>
        <script type="text/javascript" src="js/phoneSearch.js"> </script>
    </head>
    <body>

        <apus:top/>
        <apus:only-user role="ADMIN"><apus:navtabs number="4"/></apus:only-user>
        <apus:phonenumber-info/>
    </body>
</html>
