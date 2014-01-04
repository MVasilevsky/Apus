<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}"/>

<c:if test="${(accountList!=null)&&(!accountList.isEmpty())}">

    <table class="table table-striped table-condensed">
        <thead>
            <tr>
                <th><fmt:message key="owner"/></th>
                <th><fmt:message key="start.date"/></th>
                <th><fmt:message key="end.date"/></th>
                <th><fmt:message key="price"/></th>
                <th><fmt:message key="is.paid"/></th>
                <th>&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="account" items="${accountList}">
                <tr class="rowSearch">
                    
                    <td>
                        <input class="acId" type="hidden" value="${account.id}">
                        <input class="stDate" type="hidden" value="<fmt:formatDate value="${account.period.startDate}" type="date" dateStyle="medium"/>">
                        <input class="enDate" type="hidden" value="<fmt:formatDate value="${account.period.endDate}" type="date" dateStyle="medium"/>">
                        <input class="summa" type="hidden" value="${account.price}">
                            <c:out value="${account.subscriber.name}"/>                      
                    </td>

                    <td>
                        <fmt:formatDate value="${account.period.startDate}" type="date" dateStyle="medium"/>
                    </td>

                    <td>
                        <fmt:formatDate value="${account.period.endDate}" type="date" dateStyle="medium"/>
                    </td> 

                    

                    <td>
                        <c:out value="${account.price}"/>
                    </td>
                    
                    <td>
                        <c:if test="${account.isPaid()}">
                            <i class="icon-ok"></i>
                        </c:if>
                    </td>
                    
                    <td>
                        <c:if test="${!account.isPaid()}">
                            <a class="btn btn-small chsBtn" onclick="chooseAcc(this);">
                                        <i class="icon-check"></i> <fmt:message key="choose"/>
                            </a>
                        </c:if> 
                    </td>                          
                </tr>
           <%--     --%>
            </c:forEach>

        </tbody>
    </table>
</c:if>
<c:if test="${(accountList==null)||(accountList.isEmpty())}">
    <div class="alert"><fmt:message key="no.accounts"/></div>
</c:if>