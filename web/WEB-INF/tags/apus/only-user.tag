<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@tag description="only for users" pageEncoding="UTF-8"%> 
<%@attribute name="role"%>
<fmt:setLocale value="${lang}"/>
<c:if test="${userAuth.role eq role}">
<jsp:doBody/>
</c:if>