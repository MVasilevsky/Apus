<%-- 
    Document   : mainCashier
    Created on : 14.08.2012, 23:29:32
    Author     : Vasilevsky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.7.2.js">  </script>
        <script type="text/javascript" src="js/ajax.js">  </script>
        <script type="text/javascript" src="js/cashier.js">  </script>
        <script type="text/javascript" src="js/modals.js">  </script>
        <script type="text/javascript" src="js/bootstrap-alert.js">  </script>
        <script type="text/javascript" src="js/bootstrap-modal.js">  </script>
        <apus:bootstrap />
        <title><fmt:message key="APUS"/> :: <c:out value="${userAuth.username}"/></title>
    </head>

    <body id="body" onload="javascript: setTimeout(function () { $('#winModal').fadeOut('slow', function() {}); }, 3000);">
        <apus:top/>
        
        <div id="isquickDiv">
            <input name="isquick" id="isquick" type="hidden" value="<c:if test="${settings.quickSearch eq true}">true</c:if>">
        </div>
        
        <form id="srchFrm" style="margin-bottom: 20px;" class="pull-left">
                <div class="input-append">
                    <input id="srchInpt" type="text" name="name" class="input-medium span4" value=""><button id="srchBtn" class="btn" type="submit"><i class="icon-search"></i> </button>
                </div>
            </form>

        <div class="pull-right editSpaces">
            <apus:winModal/>
            <apus:failModal/> 
        </div>

        <div style="clear:both;"></div>

        <div class="shortTable well" id="searchResults">
            <c:if test="${phoneNumberList!=null}">
                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th><fmt:message key="number"/></th>
                            <th><fmt:message key="owner"/></th>
                            <th>&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="phoneNumber" items="${phoneNumberList}">
                            <tr class="rowSearch">                        
                                <td>
                                    <input class="subId" type="hidden" value="${phoneNumber.owner.id}"/>
                                    <input class="subName" type="hidden" value="${phoneNumber.owner.name}"/>
                                    <c:out value="${settings.numberPrefix}"/><c:out value="${phoneNumber.number}"/>
                                </td>
                                <td>
                                    <c:out value="${phoneNumber.owner.name}"/>
                                </td>
                                <td>
                                    <button class="btn btn-small chsBtn" onclick="choose(this);">
                                        <fmt:message key="search.accounts"/>
                                    </button>
                                </td> 
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>

        <div style="margin-top: 20px" class="veryShortTable well" id="accSearchResults">           

        </div>


        <div style="margin-top: 20px" class="well strangePanel" id="strangePanel">
            <form class="form-horizontal" method="POST" action="main?action=paid" onsubmit="ccc(this);">
                <div class="control-group">
                    <input id="accountId" type="hidden" name="accountId">

                    <label class="pull-left" for="name"> <fmt:message key="subscriber"/> </label>
                    <input id="subChkd" type="text" name="name" class="input-medium pull-left editSpaces" disabled value="">

                    <label class="pull-left" for="acc1"><fmt:message key="account"/> <fmt:message key="from"/> </label>
                    <input id="accChkd1" type="text" name="acc1" class="input-small pull-left editSpaces" disabled value="">

                    <label class="pull-left" for="acc2"> <fmt:message key="to"/> </label>
                    <input id="accChkd2" type="text" name="acc2" class="input-small pull-left editSpaces" disabled value="">

                    <label class="pull-left" for="sum"> <fmt:message key="price"/>: </label>
                    <input id="sum" type="text" name="sum" class="input-small pull-left editSpaces" disabled value="">

                    <button id="payBtn" disabled="disabled" class="btn btn-primary" type="submit"> <fmt:message key="perform.payment"/> </button>
                </div>
            </form>
        </div>

    </body>

</html>
