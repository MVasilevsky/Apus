<%-- 
    Document   : accountSearch
    Created on : 18.08.2012, 18:43:00
    Author     : Vasileusky
--%>
<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}"/>

<c:if test="${(accountList!=null)&&(!accountList.isEmpty())}">
        <table class="table table-striped table-condensed">
            <thead>
                <tr>
                    <th><fmt:message key="start.date"/></th>
                    <th><fmt:message key="end.date"/></th>
                    <th><fmt:message key="owner"/></th>
                    <th><fmt:message key="price"/></th>
                    <th><fmt:message key="is.paid"/></th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="account" items="${accountList}">
                    <tr>
                        <c:url var="subscrURL" value="main">
                            <c:param name="action" value="${account.subscriber.subscriberType}.view"/>
                            <c:param name="id" value="${account.subscriber.id}"/>
                        </c:url>

                        <c:if test="${account.subscriber.subscriberType == 'person'}">

                        </c:if>

                        <c:url var="accDelURL" value="main">
                            <c:param name="action" value="account.delete"/>
                            <c:param name="id" value="${account.id}"/>
                        </c:url>

                        <td>
                <fmt:formatDate value="${account.period.startDate}" type="date" dateStyle="medium"/>
                </td>
                <td>
                <fmt:formatDate value="${account.period.endDate}" type="date" dateStyle="medium"/>
                </td>
                <td>
                    <a href="${subscrURL}">
                        <c:out value="${account.subscriber.name}"/>
                    </a>
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
                    <a href="${accDelURL}">
                        <i class="icon-remove"></i>
                    </a>
                </td>                          
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${(accountList==null)||(accountList.isEmpty())}">
    <div class="alert"><fmt:message key="no.accounts"/></div>
</c:if>