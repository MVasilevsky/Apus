<%-- 
    Document   : administrationMainView
    Created on : 18.08.2012, 0:24:29
    Author     : Vasileusky
--%>
<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>

<html>
    <head>
        <apus:bootstrap/>
        <script type="text/javascript" src="js/modals.js">  </script>
        
        <script type="text/javascript" src="js/jquery-1.7.2.js">  </script>
        <script type="text/javascript" src="js/bootstrap-alert.js">  </script>
        <script type="text/javascript" src="js/bootstrap-modal.js">  </script>
        <script type="text/javascript" src="js/bootstrap-tooltip.js">  </script>
        <script type="text/javascript" src="js/tooltips.js"> </script>
        <script type="text/javascript">
            $(document).ready(function(){
        
                $("a").tooltip();
                $("span").tooltip();
        
                $('.dLink').click(function(e){
                    $('#myModal').find('#confirm').attr("href", $(this).attr("href"));
                    $('#subject').html($(this).parent().find('#employeeName').val());
                    $('#myModal').modal('show');
           
                    e.preventDefault(); 
                });
    
                $('#cancel').click(function(){
                    $('#myModal').modal('hide');
                });
            });
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="APUS"/> :: <fmt:message key="administration"/></title>
    </head>
    <body  onload="javascript:$('.scrtable').height($(window).height()-160);  setTimeout(function () { $('#winModal').fadeOut('slow', function() {}); }, 3000);">

        <apus:top/>
        <apus:navtabs number="7"/>

        <div>
            <a class="btn btn-primary pull-right" href="./main?action=user.create"><fmt:message key="new.employee"/></a>
        </div>

        <div class="pull-right editSpaces">
            <apus:deleteModal/>
            <apus:winModal/>
            <apus:failModal/> 
        </div>

        <div style="clear:both;"></div>

        <div class="scrtable">
            <table class="table table-striped table-condensed">
                <thead>
                    <tr>
                        <th>&nbsp;</th>
                        <th><fmt:message key="login"/></th>
                        <th><fmt:message key="position"/></th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="employee" items="${employeeList}">
                        <tr>

                            <c:url var="emplDelURL" value="main">
                                <c:param name="action" value="user.delete"/>
                                <c:param name="id" value="${employee.id}"/>
                                <c:param name="login" value="${employee.login}"/>
                            </c:url>

                            <td>
                            </td>

                            <td>
                                <c:out value="${employee.login}"/>
                            </td>

                            <td>
                                <c:if test="${employee.role==1}">
                                    <span class="label label-important"><fmt:message key="administrator"/></span>
                                </c:if>

                                <c:if test="${employee.role==2}">
                                    <span class="label label-success"><fmt:message key="cashier"/></span>
                                </c:if>
                            </td>

                            <td id="tdDel">                               
                                <c:if test="${userAuth.login ne employee.login}">
                                    <input id="employeeName" type="hidden" value="<fmt:message key="little.employee"/>">
                                    <a class="dLink" title="<fmt:message key="remove.employee"/>" href="${emplDelURL}">
                                        <i class="icon-remove"></i>
                                    </a>
                                </c:if>
                            </td> 

                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>


    </body>
</html>
