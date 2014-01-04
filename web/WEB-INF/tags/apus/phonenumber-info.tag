<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag description="phone number info" pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}"/>


<div class="well">
    <div>
        <h2 class="pull-left">
            <c:url var="subURL" value="main">
                <c:param name="action" value="${phoneNumber.getOwner().getSubscriberType()}.view"/>
                <c:param name="id" value="${phoneNumber.getOwner().id}"/>
            </c:url>
            <apus:only-user role="ADMIN"><a href="${subURL}"><c:out value="${phoneNumber.getOwner().name}"/></a>
                &nbsp;</apus:only-user>

            <c:out value="${settings.numberPrefix}"/><c:out value="${phoneNumber.number}"/></h2>
        <apus:only-user role="USER"><a class="btn btn-small pull-right" href="main?action=main.view"> <i style="margin-right: 3px" class="icon-arrow-left"></i> <fmt:message key="back"/> </a></apus:only-user>
        <apus:only-user role="ADMIN"><a class="btn btn-small pull-right" href="main?action=phoneNumber.list"> <i style="margin-right: 3px" class="icon-arrow-left"></i><fmt:message key="to.numbers.list"/> </a></apus:only-user>
        <a id="searchSwitch" style="margin-right: 10px;" class="btn btn-small pull-right" href="#"><fmt:message key="choose.period"/> </a>
        <a style="margin-right: 10px" class="btn btn-small pull-right" href="main?action=phoneNumber.view&id=<c:out value="${phoneNumber.id}"/>"> <i style="margin-right: 3px" class="icon-list"></i><fmt:message key="all.calls"/> </a>
    </div>



    <div style="clear: both;"></div>

    <div id="searchDiv" style=" display:none;">
        <form name="ololo" class="form-inline pull-right" method="POST" action="main?action=call.list.period" >
            <input type="hidden" id="isFilter" value="<c:out value="${filter}"/>">
            <input type="hidden" id="isFilter" value="<c:out value="${filter}"/>">
            <input type="hidden" name="id" value="<c:out value="${phoneNumber.id}"/>">
            <select id="month" name="month" style="width: 100px">
                <option value="0" <c:if test="${month==0}"><c:out value="selected=\"true\""/></c:if>> <fmt:message key="january"/> </option>
                <option value="1" <c:if test="${month==1}"><c:out value="selected=\"true\""/></c:if>> <fmt:message key="february"/> </option>
                <option value="2" <c:if test="${month==2}"><c:out value="selected=\"true\""/></c:if>> <fmt:message key="march"/> </option>
                <option value="3" <c:if test="${month==3}"><c:out value="selected=\"true\""/></c:if>> <fmt:message key="april"/> </option>
                <option value="4" <c:if test="${month==4}"><c:out value="selected=\"true\""/></c:if>> <fmt:message key="may"/> </option>
                <option value="5" <c:if test="${month==5}"><c:out value="selected=\"true\""/></c:if>> <fmt:message key="june"/> </option>
                <option value="6" <c:if test="${month==6}"><c:out value="selected=\"true\""/></c:if>> <fmt:message key="jule"/> </option>
                <option value="7" <c:if test="${month==7}"><c:out value="selected=\"true\""/></c:if>> <fmt:message key="august"/> </option>
                <option value="8" <c:if test="${month==8}"><c:out value="selected=\"true\""/></c:if>> <fmt:message key="september"/> </option>
                <option value="9" <c:if test="${month==9}"><c:out value="selected=\"true\""/></c:if>> <fmt:message key="october"/> </option>
                <option value="10" <c:if test="${month==10}"><c:out value="selected=\"true\""/></c:if>> <fmt:message key="november"/> </option>
                <option value="11" <c:if test="${month==11}"><c:out value="selected=\"true\""/></c:if>> <fmt:message key="december"/> </option>
            </select>
            <select id="year" name="year" style="width: 70px">
                <option value="2012" <c:if test="${year==2012}"><c:out value="selected=\"true\""/></c:if>> 2012 </option>
                <option value="2013" <c:if test="${year==2013}"><c:out value="selected=\"true\""/></c:if>> 2013 </option>
                <option value="2014" <c:if test="${year==2014}"><c:out value="selected=\"true\""/></c:if>> 2014 </option>
                <option value="2015" <c:if test="${year==2015}"><c:out value="selected=\"true\""/></c:if>> 2015 </option>
                <option value="2016" <c:if test="${year==2016}"><c:out value="selected=\"true\""/></c:if>> 2016 </option>
            </select>
                <button class="btn btn-small btn-info" type="submit" name="submitButton"> <i style="margin-right: 3px" class="icon-search"></i><fmt:message key="search.calls"/> </button> 
        </form>
    </div>





    <div style="margin-top: 20px" style="clear: both;"></div>

    <h3 class="pull-left"><fmt:message key="outgoing.calls"/></h3>
    <table class="table table-condensed table-striped table-bordered">
        <thead>
            <tr>
                <th><fmt:message key="in.number"/></th>
                <th><fmt:message key="date"/></th>
                <th><fmt:message key="time"/></th>
                <th><fmt:message key="duration"/></th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${!outCalls.isEmpty()}">
                <c:forEach var="call" items="${outCalls}">
                    <tr>
                        <c:url var="callURL" value="main">
                            <c:param name="action" value="phoneNumber.view"/>
                            <c:param name="id" value="${call.numberTo.id}"/>
                        </c:url>
                        <td>
                            <apus:only-user-href title="${call.numberTo.owner}" href="${callURL}" role="ADMIN"><c:out value="${settings.numberPrefix}"/><c:out value="${call.numberTo}"/></apus:only-user-href>
                            </td>
                            <td>
                            <fmt:formatDate value="${call.dateOfCall}" type="date" dateStyle="medium"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${call.dateOfCall}" type="time" timeStyle="medium"/>
                        </td>
                        <td>
                            <c:out value="${call.duration}"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>

    </table>

    <h3 class="pull-left"><fmt:message key="incoming.calls"/></h3>
    <table class="table table-condensed table-striped table-bordered">
        <thead>
            <tr>
                <th><fmt:message key="out.number"/></th>
                <th><fmt:message key="date"/></th>
                <th><fmt:message key="time"/></th>
                <th><fmt:message key="duration"/></th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${!inCalls.isEmpty()}">
                <c:forEach var="call" items="${inCalls}">
                    <tr>
                        <c:url var="callURL" value="main">
                            <c:param name="action" value="phoneNumber.view"/>
                            <c:param name="id" value="${call.numberFrom.id}"/>
                        </c:url>
                        <td>
                            <apus:only-user-href title="${call.numberFrom.owner}" href="${callURL}" role="ADMIN">
                                <span title="<c:out value="${call.numberFrom.owner}"/>"> 
                                    <c:out value="${settings.numberPrefix}"/><c:out value="${call.numberFrom}"/>
                                </span>
                            </apus:only-user-href>
                        </td>
                        <td>
                            <fmt:formatDate value="${call.dateOfCall}" type="date" dateStyle="medium"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${call.dateOfCall}" type="time" timeStyle="medium"/>
                        </td>
                        <td>
                            <c:out value="${call.duration}"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>

    </table>
</div>