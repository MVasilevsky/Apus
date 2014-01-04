<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>

<html>
    <head>
        <apus:bootstrap/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="APUS"/> :: <fmt:message key="call.list"/></title>
        <script type="text/javascript" src="js/jquery-1.7.2.js">  </script>
        <script type="text/javascript" src="js/bootstrap-tooltip.js">  </script>
        <script type="text/javascript" src="js/tooltips.js"> </script>
    </head>
    <body onload="javascript:$('.scrtable').height($(window).height()-140); setTimeout(function () { $('#winModal').fadeOut('slow', function() {}); }, 3000);">
        <apus:top/>
        <apus:navtabs number="5"/>
        <div class="pull-right editSpaces">
            <apus:winModal/>
            <apus:failModal/> 
        </div>
        <div style="clear:both;"></div>
        <c:if test="${(callList!=null)&&(!callList.isEmpty())}">
            <div class="scrtable">
                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th><fmt:message key="out.number"/></th>
                            <th><fmt:message key="in.number"/></th>
                            <th><fmt:message key="duration"/></th>
                            <th><fmt:message key="date"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="call" items="${callList}">
                            <tr>
                                <c:url var="phNumFr" value="main">
                                    <c:param name="action" value="phoneNumber.view"/>
                                    <c:param name="id" value="${call.numberFrom.id}"/>
                                </c:url>

                                <c:url var="phNumTo" value="main">
                                    <c:param name="action" value="phoneNumber.view"/>
                                    <c:param name="id" value="${call.numberTo.id}"/>
                                </c:url>
                                <td>
                                    <c:out value="${call.id}"/>
                                </td>
                                <td>
                                    <a title="${call.numberFrom.owner}" href="${phNumFr}">
                                        <c:out value="${settings.numberPrefix}"/><c:out value="${call.numberFrom.number}"/>
                                    </a>
                                </td>
                                <td>
                                    <a title="${call.numberTo.owner}" href="${phNumTo}">
                                        <c:out value="${settings.numberPrefix}"/><c:out value="${call.numberTo.number}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${call.duration}"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${call.dateOfCall}" type="both" dateStyle="medium" timeStyle="medium"/>
                                </td>                         
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
            </div>
        </c:if>
        <c:if test="${(callList==null)||(callList.isEmpty())}">
            <div class="alert"><fmt:message key="no.calls"/></div>
        </c:if>
    </body>
</html>
