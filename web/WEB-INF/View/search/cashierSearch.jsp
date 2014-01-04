<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}"/>

<c:if test="${(phoneNumberList!=null)&&(!phoneNumberList.isEmpty())}">
    
    <table class="table table-striped table-condensed">
        <thead>
            <tr>
                <th><fmt:message key="number"/></th>
                <th><fmt:message key="owner"/></th>
                <th>&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="phoneNumber" items="${phoneNumberList}">
                <tr class="rowSearch">                        
                    <td>
                        <input class="subId" type="hidden" value="${phoneNumber.owner.id}"/>
                        <input class="subName" type="hidden" value="${phoneNumber.owner.name}"/>
                        <c:out value="${settings.numberPrefix}"/><c:out value="${phoneNumber.number}"/>
                    </td>
                    <td>
                        <c:out value="${phoneNumber.owner.name}"/>
                    </td>
                    <td>
                        <a class="btn btn-small chsBtn" onclick="choose(this);">
                            <fmt:message key="search.accounts"/>
                        </a>
                    </td> 
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${(phoneNumberList==null)||(phoneNumberList.isEmpty())}">
    <div class="alert"><fmt:message key="not.found"/></div>
</c:if>