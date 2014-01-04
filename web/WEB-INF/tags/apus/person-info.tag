<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag description="view of person" pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}"/>
<div class="well">
    <div>

        <h2 class="pull-left"><c:out value="${person.name}"/></h2>
        <apus:only-user role="ADMIN"><a class="btn btn-small pull-right" href="main?action=person.list"> <i class="icon-arrow-left"></i> <fmt:message key="to.persons.list"/> </a></apus:only-user>

    </div>

    <table class="table table-condensed table-striped table-bordered">

        <tr>
            <th><fmt:message key="per.name"/></th>
            <th><c:out value="${person.name}"/></th>
        </tr>
        <tr>
            <th><fmt:message key="passport"/></th>
            <th><c:out value="${person.passportNumber}"/></th>
        </tr>
        <tr>
            <th><fmt:message key="address"/></th>
            <th><c:out value="${person.address}"/></th>
        </tr>
        <tr>
            <th><fmt:message key="phone.numbers"/></th>
            <th>
                <c:if test="${!person.phoneNumbers.isEmpty()}">
                    <c:forEach var="phoneNumber" items="${person.phoneNumbers}">
                        <c:url var="phNURL" value="main">
                            <c:param name="action" value="phoneNumber.view"/>
                            <c:param name="id" value="${phoneNumber.id}"/>
                        </c:url>
                        <a href="${phNURL}"><c:out value="${settings.numberPrefix}"/><c:out value="${phoneNumber}"/></a><br>
                    </c:forEach>
                </c:if>
                <c:if test="${person.phoneNumbers.isEmpty()}">
                    -
                </c:if>
            </th>
        </tr>
    </table>

    <div style="margin-bottom: 40px">
        <c:url var="phNCreateURL" value="main">
            <c:param name="action" value="phoneNumber.create"/>
            <c:param name="sub_type" value="0"/>
            <c:param name="sub_id" value="${person.id}"/>
        </c:url>
        <c:url var="accViewURL" value="main">
            <c:param name="action" value="account.list"/>
            <c:param name="sub_id" value="${person.id}"/>
        </c:url>
        <c:url var="perEditURL" value="main">
            <c:param name="action" value="person.edit"/>
            <c:param name="id" value="${person.id}"/>
        </c:url>
        <apus:only-user role="ADMIN"><a style="margin-right: 20px" class="btn btn-small pull-left" href="${phNCreateURL}"> <i class="icon-plus-sign icon-black"></i><fmt:message key="add.number"/> </a></apus:only-user>
        <a style="margin-right: 20px" class="btn btn-small pull-left" href="${accViewURL}"> <i class="icon-align-justify icon-black"></i> <fmt:message key="view.accounts"/> </a> 
        <apus:only-user role="ADMIN"><a style="margin-left: 20px" class="btn btn-primary btn-small pull-right" href="${perEditURL}"> <i class="icon-edit icon-white"></i> <fmt:message key="edit"/> </a></apus:only-user>
    </div>
</div>