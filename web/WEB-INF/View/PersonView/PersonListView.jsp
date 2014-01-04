<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>

<html>
    <head>
        <apus:bootstrap/>
        <script type="text/javascript" src="js/jquery-1.7.2.js">  </script>
        <script type="text/javascript" src="js/persons.js">  </script>
        <script type="text/javascript" src="js/modals.js">  </script>
        <script type="text/javascript" src="js/bootstrap-alert.js">  </script>
        <script type="text/javascript" src="js/bootstrap-modal.js">  </script>
        <script type="text/javascript" src="js/bootstrap-tooltip.js">  </script>
        <script type="text/javascript" src="js/tooltips.js"> </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="APUS"/> :: <fmt:message key="person.list"/></title>
    </head>
    <body onload="javascript:$('.scrtable').height($(window).height()-180);  setTimeout(function () { $('#winModal').fadeOut('slow', function() {}); }, 3000);">

        <apus:top/>
        <apus:navtabs number="1"/>
        <input name="isquick" id="isquick" type="hidden" value="<c:if test="${sets.quickSearch eq true}">true</c:if>">
            <div id="searchDiv">

                <form id="srchFrm" style="margin-bottom: 20px;" class="pull-left"  method="POST" action="./main?action=person.search">
                    <div class="input-append">
                        <input id="srchInpt" style="width: 250px" type="text" name="name" value="<c:if test="${search!=null}"><c:out value="${search}"/></c:if>"><button id="srchBtn" class="btn" type="submit"><i class="icon-search"></i> </button>
                    </div>
                </form>

            <c:remove var="search" scope="session"/>

            <a class="btn btn-primary pull-right" href="./main?action=person.create"><fmt:message key="new.person"/></a>

        </div>

        <div class="pull-right editSpaces">
            <apus:deleteModal/>
            <apus:winModal/>
            <apus:failModal/> 
        </div>

        <div style="clear:both;"></div>
        <div class="scrtable" id="searchResults">
            <c:if test="${(personList!=null)&&(!personList.isEmpty())}">

                <div class="scrtable">
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
        </div>

    </body>
</html>
