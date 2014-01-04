<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>

<html>
    <head>
        <apus:bootstrap/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery1-1.7.2.js"></script>
        <script type="text/javascript" src="js/bootstrap-alert.js" ></script> 
        <script type="text/javascript" src="js/jquery.validation.js" ></script> 
        <script type="text/javascript" src="js/form_validate.js">  </script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js" type="text/javascript"></script>
        <title><fmt:message key="APUS"/> :: <fmt:message key="edit.number"/></title>
    </head>
    <body>

        <apus:top/>
        <apus:navtabs number="4"/>

        <div class="span7 offset2 well">

            <div style="margin-bottom: 20px" class="offset2">
                <h2><fmt:message key="edit.number"/></h2>
            </div>

            <div id="mainDiv">

                <!--hidden form-->
                <form id="helpForm">
                    <input type="hidden" id="numberLength" value="<c:out value="${settings.numberSize}"/>">
                </form>

                <!--main form-->
                <form id="mainForm" class="form-horizontal" method="POST" action="main?action=phoneNumber.save"
                      onsubmit="return pnVal(this);">
                    <fieldset>
                        <input type="hidden" name="id" value="${phoneNumber.id}">
                        <input type="hidden" name="sub_id" value="${phoneNumber.owner.id}">

                        <div id="numDiv" class="control-group">
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><c:out value="${settings.numberPrefix}"/></span><input class="span2" type="text" id="number" name="number" 
                                       value="${phoneNumber.number}"  oninput="fieldIsFilling(this)">
                                    <span class="help-inline"></span>
                                </div>
                            </div>
                        </div>

<%--                        <div class="control-group">
                            <label class="control-label" for="sub_id"><fmt:message key="owner"/></label>
                            <div class="controls">
                                <select class="span3" name="sub_id">
                                    <option value="${phoneNumber.getOwner().id}"><c:out value="${phoneNumber.getOwner().name}"/></option>
                                    <c:forEach var="sub" items="${perList}">
                                        <c:if test="${sub.id!=phoneNumber.getOwner().id}">
                                            <option value="${sub.id}"><c:out value="${sub.name}"/></option>
                                        </c:if>
                                    </c:forEach>
                                    <c:forEach var="sub" items="${orgList}">
                                        <c:if test="${sub.id!=phoneNumber.getOwner().id}">
                                            <option value="${sub.id}"><c:out value="${sub.name}"/></option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>--%>
                        

                        <div class="span4 offset2">
                            <input class="btn btn-primary" type="submit" name="saveButton" value="<fmt:message key="save"/>">
                            <a href="main?action=phoneNumber.list" class="btn" name="cancelButton"><fmt:message key="cancel"/></a>
                        </div>

                    </fieldset>
                </form>

            </div>

        </div>
    </body>
</html>
