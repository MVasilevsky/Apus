<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@tag description="error div from the login page" pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}"/>

<c:if test="${errorMessage!=null}" >
    <div class="span4 offset3 alert">
        <strong><fmt:message key="login.fail"/></strong>
    </div>
        <c:remove var="errorMessage"/>
</c:if>