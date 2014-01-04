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

<c:if test="${(phoneNumberList!=null)&&(!phoneNumberList.isEmpty())}">
    <div class="scrtable">
        <table class="table table-striped table-condensed">
            <thead>
                <tr>
                    <th><fmt:message key="number"/></th>
                    <th><fmt:message key="owner"/></th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="phoneNumber" items="${phoneNumberList}">
                    <tr id="trNumber">
                        <c:url var="phoneURL" value="main">
                            <c:param name="action" value="phoneNumber.view"/>
                            <c:param name="id" value="${phoneNumber.id}"/>
                        </c:url>

                        <c:url var="ownerURL" value="main">
                            <c:param name="action" value="${phoneNumber.owner.subscriberType}.view"/>
                            <c:param name="id" value="${phoneNumber.owner.id}"/>
                        </c:url>

                        <c:url var="phNEditURL" value="main">
                            <c:param name="action" value="phoneNumber.edit"/>
                            <c:param name="id" value="${phoneNumber.id}"/>
                        </c:url>

                        <c:url var="phNDelURL" value="main">
                            <c:param name="action" value="phoneNumber.delete"/>
                            <c:param name="id" value="${phoneNumber.id}"/>
                            <c:param name="number" value="${phoneNumber.number}"/>
                        </c:url>

                        <td id="tdNum">
                            <a id="nameLink" href="${phoneURL}">
                                <c:out value="${settings.numberPrefix}"/><c:out value="${phoneNumber.number}"/>
                            </a>
                        </td>
                        <td>
                            <a href="${ownerURL}">
                                <c:out value="${phoneNumber.owner.name}"/>
                            </a>
                        </td>
                        <td>
                            <a title="<fmt:message key="edit.number"/>" href="${phNEditURL}">
                                <i class="icon-edit"></i>
                            </a>
                        </td> 
                        <td>
                            <a class="dLink" href="${phNDelURL}" title="<fmt:message key="remove.number"/>">
                                <i class="icon-remove"></i>
                            </a>
                        </td>                          
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${(phoneNumberList==null)||(phoneNumberList.isEmpty())}">
    <div class="alert"><fmt:message key="no.numbers"/></div>
</c:if>