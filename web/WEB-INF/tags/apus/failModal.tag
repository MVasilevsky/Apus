<%-- 
    Document   : failModal
    Created on : 18.08.2012, 14:16:39
    Author     : 21vek
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag description="fail modal window" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<c:if test="${fail!=null}" >
    <div style="height: 15px" class="alert alert-error span4" id="failModal">
            <button id="closeBtn" class="close" data-dismiss="alert">&times;</button><p><c:out value="${fail}"/></p>
    </div>
    <c:remove scope="session" var="fail"/>
</c:if>