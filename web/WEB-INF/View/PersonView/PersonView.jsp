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
        <script type="text/javascript" src="js/jquery-1.7.2.js">  </script>
        <script type="text/javascript" src="js/persons.js"> </script>
        <script type="text/javascript" src="js/bootstrap-alert.js">  </script>
        <script type="text/javascript" src="js/bootstrap-modal.js">  </script>
        <script type="text/javascript" src="js/form_validate.js">  </script>

        <title><fmt:message key="APUS"/> :: <c:out value="${person.name}"/></title>
    </head>
    <body onload="javascript: setTimeout(function () { $('#winModal').fadeOut('slow', function() {}); }, 3000);">

        <apus:top/>
        <apus:navtabs number="1"/>
        <apus:createNumberModal/>

        <div class="well">

            <div>
                <h2 class="pull-left"><c:out value="${person.name}"/></h2>
                <a class="btn btn-small pull-right" href="main?action=person.list"> <i style="margin-right: 3px" class="icon-arrow-left"></i> <fmt:message key="to.persons.list"/> </a>
                
                <div class="pull-right" style="margin-right: 10px">
                    <apus:winModal/>
                    <apus:failModal/>
                </div>
                
            </div>

            <input type="hidden" id="subIdField" value="<c:out value="${person.id}"/>" >

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

                <a style="margin-right: 20px" class="btn btn-small pull-left addLink" href="#"> <i style="margin-right: 3px" class="icon-plus-sign icon-black"></i><fmt:message key="add.number"/> </a>
                <a style="margin-right: 20px" class="btn btn-small pull-left" href="${accViewURL}"> <i style="margin-right: 3px" class="icon-align-justify icon-black"></i><fmt:message key="view.accounts"/> </a> 
                <a style="margin-left: 20px" class="btn btn-primary btn-small pull-right" href="${perEditURL}"> <i class="icon-edit icon-white"></i> <fmt:message key="edit"/> </a>
            </div>
        </div>
    </body>
</html>
