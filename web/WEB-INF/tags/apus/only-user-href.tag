<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@tag description="only for users href" pageEncoding="UTF-8"%>
<%@attribute name="role"%>
<%@attribute name="href"%>
<%@attribute name="title"%>
<fmt:setLocale value="${lang}"/>
<c:if test="${userAuth.role eq role}">
    <a title="${title}" href="${href}"><jsp:doBody/></a>
</c:if>
<c:if test="${userAuth.role ne role}">
    <jsp:doBody/>
</c:if>