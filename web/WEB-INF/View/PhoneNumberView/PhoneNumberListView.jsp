<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/HTML4/strict.dtd">
<fmt:setLocale value="${lang}"/>

<html>
    <head>
        <apus:bootstrap/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf8">
        <script type="text/javascript" src="js/modals.js">  </script>
        <script type="text/javascript" src="js/jquery-1.7.2.js">  </script>
        <script type="text/javascript" src="js/phones.js">  </script>
        <script type="text/javascript" src="js/bootstrap-alert.js">  </script>
        <script type="text/javascript" src="js/bootstrap-modal.js">  </script>
        <script type="text/javascript" src="js/bootstrap-tooltip.js">  </script>
        <script type="text/javascript" src="js/tooltips.js"> </script>
        <script type="text/javascript" src="js/form_validate.js"> </script>
        <title><fmt:message key="APUS"/> :: <fmt:message key="number.list"/></title>
    </head>
    <body onload="javascript: setTimeout(function () { $('#winModal').fadeOut('slow', function() {}); }, 3000);">

        <apus:top/>
        <apus:navtabs number="4"/>

        <apus:createNumberModal/>

        <div>
            <input name="isquick" id="isquick" type="hidden" value="<c:if test="${settings.quickSearch eq true}">true</c:if>">

            <form id="srchFrm" style="margin-bottom: 20px;" class="pull-left"  method="POST" action="./main?action=phoneNumber.search">
                <div class="input-append">
                    <input id="srchInpt" style="width: 250px" type="text" name="name" value="<c:if test="${search!=null}"><c:out value="${search}"/></c:if>"><button id="srchBtn" class="btn" type="submit"><i class="icon-search"></i> </button>
                </div>
            </form>
        </div>

        <c:remove var="search" scope="session"/>

        <div class="pull-right editSpaces">
            <apus:deleteModal/>
            <apus:winModal/>
            <apus:failModal/> 
            <apus:createNumberModal/>
        </div>

        <div style="clear:both;"></div>

        <div class="scrtable" id="searchResults">
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
                                    <td id="editTD">

                                        <input type="hidden" id="subIdField" value="<c:out value="${phoneNumber.owner.id}"/>" >
                                        <input type="hidden" id="numIdField" value="<c:out value="${phoneNumber.id}"/>" >
                                        <input type="hidden" id="numberField" value="<c:out value="${phoneNumber}"/>" >
                                        <a id="editLink" class="editLink" title="<fmt:message key="edit.number"/>" href="${phNEditURL}">
                                            <i class="icon-edit"></i>
                                        </a>
                                    </td> 
                                    <td>
                                        <a class="dLink" title="<fmt:message key="remove.number"/>" href="${phNDelURL}">
                                            <i class="icon-remove"></i>
                                        </a>
                                    </td>                          
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
        <c:if test="${(phoneNumberList==null)||(phoneNumberList.isEmpty())}">
            <div class="alert"><fmt:message key="no.numbers"/></div>
        </c:if>
    </body>
</html>
