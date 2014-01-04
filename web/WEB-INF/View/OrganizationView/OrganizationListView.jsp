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
        <script type="text/javascript" src="js/organizations.js">  </script>
        <script type="text/javascript" src="js/bootstrap-modal.js">  </script>
        <script type="text/javascript" src="js/bootstrap-alert.js">  </script>
        <script type="text/javascript" src="js/bootstrap-tooltip.js">  </script>
        <script type="text/javascript" src="js/tooltips.js"> </script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="APUS"/> :: <fmt:message key="organization.list"/></title>
    </head>
    <body onload="javascript:$('.scrtable').height($(window).height()-180);">

        <apus:top/>
        <apus:navtabs number="2"/>

        <div id="searchDiv">
            
            <input name="isquick" id="isquick" type="hidden" value="<c:if test="${sets.quickSearch eq true}">true</c:if>">

            <form id="srchFrm" style="margin-bottom: 20px;" class="pull-left"  method="POST">
                <div class="input-append">
                    <input id="srchInpt" style="width: 250px" type="text" name="name" value="<c:if test="${search!=null}"><c:out value="${search}"/></c:if>"><button id="srchBtn" class="btn" type="submit"><i class="icon-search"></i> </button>
                </div>
            </form>

            <c:remove var="search" scope="session"/>
            
            <a class="btn btn-primary pull-right" href="main?action=organization.create"> <fmt:message key="new.organization"/> </a>

        </div>

        <div class="pull-right editSpaces">
            <apus:deleteModal/>
            <apus:winModal/>
            <apus:failModal/> 
        </div>

        <div style="clear:both;"></div>
        <div class="scrtable" id="searchResults">
            <c:if test="${(organizationList!=null)&&(!organizationList.isEmpty())}">

                <div class="scrtable">
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
                                        <a title="<fmt:message key="remove"/> <c:out value="${organization.name}"/>" class="dLink" href="${orgDelURL}">
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
        </div>
    </body>
</html>
