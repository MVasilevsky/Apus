<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}"/>

<script type="text/javascript">
    $(document).ready(function(){
        
        $("a").tooltip();
        $("span").tooltip();
        
        $('.dLink').click(function(e){
            $('#myModal').find('#confirm').attr("href", $(this).attr("href")+"&search="+$('#srchInpt').val());
            $('#subject').html($(this).parent().parent().find('#nameLink').html());
            $('#myModal').modal('show');
           
            e.preventDefault(); 
        });
    
        $('#cancel').click(function(){
            $('#myModal').modal('hide');
        });
    });
</script>

<c:if test="${(organizationList!=null)&&(!organizationList.isEmpty())}">

    <div>
        <table class="table table-striped table-condensed">
            <thead>
                <tr>
                    <th><fmt:message key="org.name"/></th>
                    <th><fmt:message key="banking.details"/></th>
                    <th><fmt:message key="address"/></th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="organization" items="${organizationList}">
                    <tr id="org">
                        <c:url var="orgURL" value="main">
                            <c:param name="action" value="organization.view"/>
                            <c:param name="id" value="${organization.id}"/>
                        </c:url>

                        <c:url var="orgEditURL" value="main">
                            <c:param name="action" value="organization.edit"/>
                            <c:param name="id" value="${organization.id}"/>
                        </c:url>

                        <c:url var="orgDelURL" value="main">
                            <c:param name="action" value="organization.delete"/>
                            <c:param name="id" value="${organization.id}"/>
                            <c:param name="name" value="${organization.name}"/>
                        </c:url>

                        <td id="tdName">
                            <a id="nameLink" href="${orgURL}">
                                <c:out value="${organization.name}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${organization.bankingDetails}"/>
                        </td>
                        <td>
                            <c:out value="${organization.address}"/>
                        </td>
                        <td>
                            <a title="<fmt:message key="edit"/> <c:out value="${organization.name}"/>" href="${orgEditURL}">
                                <i class="icon-edit"></i>
                            </a>
                        </td> 
                        <td>
                            <a class="dLink" title="<fmt:message key="remove"/> <c:out value="${organization.name}"/>" href="${orgDelURL}">
                                <i class="icon-remove"></i>
                            </a>
                        </td>                          
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${(organizationList==null)||(organizationList.isEmpty())}">
    <div class="alert"><fmt:message key="no.organizations"/></div>
</c:if>