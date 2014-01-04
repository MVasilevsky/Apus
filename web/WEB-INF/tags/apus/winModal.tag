<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@tag description="success modal window" pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}"/>
<%-- The list of normal or fragment attributes can be specified here: --%>

<c:if test="${win!=null}" >
    <div style="height: 15px" class="alert alert-success span4 fade in" id="winModal">
        <button id="closeBtn" class="close" data-dismiss="alert">&times;</button><p><c:out value="${win}"/></p>            
    </div>
    
    <c:remove scope="session" var="win"/>
</c:if>
