<%@page import="java.util.Map"%>
<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <apus:bootstrap /> 
        <title><fmt:message key="APUS"/></title>
    </head>
    <body>
        <apus:top/>
        <apus:navtabs number="0"/>

    <div class="pull-left">
        <apus:winModal/>
        <apus:failModal/>
    </div>

    <c:if test="${statistics!=null}">

        <div class="well">

            <div style="margin-bottom: 40px">
                <h1 class="pull-left"> <fmt:message key="statistics"/> </h1>
            </div>

            <table style="margin-top: 50px" class="table table-condensed">
                <tbody>
                    <tr>
                        <td> <fmt:message key="persons"/> </td>
                        <td> ${statistics.get("Persons")} </td>
                    </tr>
                    <tr>
                        <td> <fmt:message key="organizations"/> </td>
                        <td> ${statistics.get("Organizations")} </td>
                    </tr>
                    <tr>
                        <td> <fmt:message key="accounts"/> </td>
                        <td> ${statistics.get("Accounts")} </td>
                    </tr>
                    <tr>
                        <td> <fmt:message key="phone.numbers"/> </td>
                        <td> ${statistics.get("Phone numbers")} </td>
                    </tr>
                    <tr>
                        <td> <fmt:message key="calls"/> </td>
                        <td> ${statistics.get("Calls")} </td>
                    </tr>
                </tbody>
            </table>

        </div>

    </c:if>
</body>
</html>
