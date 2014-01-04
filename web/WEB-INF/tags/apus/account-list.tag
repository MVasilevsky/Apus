<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}"/>

<div>
    <form id="srchFrm" class="form-search pull-left" method="POST" action="main?action=account.search">
        <div class="input-append">
            <%--<input type="text" name="name" class="input-medium search-query"
                   value="<%= (request.getAttribute("searchQuery") != null) ? request.getAttribute("searchQuery") : ""%>"><button class="btn" type="submit"> Search by subscriber </button>
            --%> </div>
    </form>
    <apus:only-user role="ADMIN"><a class="btn btn-primary pull-right" href="main?action=account.monthly"> <fmt:message key="monthly.accounts"/> </a></apus:only-user>
    <apus:only-user role="USER"><a class="btn btn-small pull-right" href="main?action=main.view"> <i class="icon-arrow-left"></i><fmt:message key="back"/> </a></apus:only-user>
</div>
<div class="pull-right editSpaces">

    <apus:winModal/>
    <apus:failModal/> 
</div>
<div style="clear:both;"></div>

<div class="scrtable" id="searchResults">
    <c:if test="${(accountList!=null)&&(!accountList.isEmpty())}">
        <div class="scrtable">
            <table class="table table-striped table-condensed">
                <thead>
                    <tr>
                        <th><fmt:message key="start.date"/></th>
                        <th><fmt:message key="end.date"/></th>
                        <apus:only-user role="ADMIN"><th><fmt:message key="owner"/></th></apus:only-user>
                        <th><fmt:message key="price"/></th>
                        <th><fmt:message key="is.paid"/></th>
                        <apus:only-user role="ADMIN"><th>&nbsp;</th></apus:only-user>
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
                            <apus:only-user role="ADMIN"><td>
                                    <a href="${subscrURL}">
                                        <c:out value="${account.subscriber.name}"/>
                                    </a>
                                </td></apus:only-user>

                                <td>
                                <c:out value="${account.price}"/>
                            </td>
                            <td>
                                <c:if test="${account.isPaid()}">
                                    <i class="icon-ok"></i>
                                </c:if>
                            </td>
                            <apus:only-user role="ADMIN">
                                <td id="tdDel">
                                    <input id="accName" type="hidden" value="<fmt:message key="little.account"/>">
                                    <a class="dLink" title="<fmt:message key="remove"/>" href="${accDelURL}">
                                        <i class="icon-remove"></i>
                                    </a>
                                </td>
                            </apus:only-user>                          
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </c:if>
</div>
<c:if test="${(accountList==null)||(accountList.isEmpty())}">
    <h2 class="alert"><fmt:message key="no.accounts"/></h2>
</c:if>