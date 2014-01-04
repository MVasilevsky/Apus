<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="number" required="true" type="java.lang.Integer"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}"/>
<ul class="nav nav-tabs">

    <li class="<c:if test="${number==1}"><c:out value="active"/></c:if>">
        <a href="main?action=person.list"><fmt:message key="persons"/></a>
    </li>

    <li class="<c:if test="${number==2}"><c:out value="active"/></c:if>">
        <a href="main?action=organization.list"><fmt:message key="organizations"/></a>
    </li>
    
    <li class="<c:if test="${number==3}"><c:out value="active"/></c:if>">
        <a href="main?action=account.list"><fmt:message key="accounts"/></a>
    </li>
    
    <li class="<c:if test="${number==4}"><c:out value="active"/></c:if>">
        <a href="main?action=phoneNumber.list"><fmt:message key="phone.numbers"/></a>
    </li>  
    
    <li class="<c:if test="${number==5}"><c:out value="active"/></c:if>">
        <a href="main?action=call.list"><fmt:message key="calls"/></a>
    </li>
    
    <li class="pull-right <c:if test="${number==6}"><c:out value='active'/></c:if>">
        <a href="main?action=system.settings"><fmt:message key="sys.settings"/></a>
    </li>
    
    <li class="pull-right <c:if test="${number==7}"><c:out value='active'/></c:if>">
        <a href="main?action=employee"><fmt:message key="employee"/></a>
    </li>
</ul>                 