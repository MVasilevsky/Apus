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



<c:if test="${(personList!=null)&&(!personList.isEmpty())}">

    <div>
        <table class="table table-striped table-condensed">
            <thead>
                <tr>
                    <th><fmt:message key="per.name"/></th>
                    <th><fmt:message key="passport"/></th>
                    <th><fmt:message key="address"/></th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="person" items="${personList}">
                    <tr id="person">
                        <c:url var="perURL" value="main">
                            <c:param name="action" value="person.view"/>
                            <c:param name="id" value="${person.id}"/>
                        </c:url>

                        <c:url var="perEditURL" value="main">
                            <c:param name="action" value="person.edit"/>
                            <c:param name="id" value="${person.id}"/>
                        </c:url>

                        <c:url var="perDelURL" value="main">
                            <c:param name="action" value="person.delete"/>
                            <c:param name="id" value="${person.id}"/>
                            <c:param name="name" value="${person.name}"/>
                        </c:url>

                        <td id="tdName">
                            <a id="nameLink" href="${perURL}">
                                <c:out value="${person.name}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${person.passportNumber}"/>
                        </td>
                        <td>
                            <c:out value="${person.address}"/>
                        </td>
                        <td>
                            <a title="<fmt:message key="edit"/> <c:out value="${person.name}"/>" href="${perEditURL}">
                                <i class="icon-edit"></i>
                            </a>
                        </td> 
                        <td>
                            <a class="dLink" title="<fmt:message key="remove"/> <c:out value="${person.name}"/>" href="${perDelURL}">
                                <i class="icon-remove"></i>
                            </a>
                        </td>                          
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${(personList==null)||(personList.isEmpty())}">
    <div class="alert"><fmt:message key="no.persons"/></div>
</c:if>