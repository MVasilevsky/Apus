<%-- 
    Document   : changePassword
    Created on : 18.08.2012, 2:28:07
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
        <script type="text/javascript" src="js/jquery.validation.js" ></script> 
        <script type="text/javascript" src="js/form_validate.js">  </script>
        <script type="text/javascript" src="js/jquery-1.7.2.js">  </script>
        <script type="text/javascript" src="js/ajax.js">  </script>
        <script type="text/javascript" src="js/form_validate.js">  </script>
        <script type="text/javascript" src="js/modals.js">  </script>
        <script type="text/javascript" src="js/bootstrap-alert.js">  </script>
        <script type="text/javascript" src="js/bootstrap-modal.js">  </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="APUS"/> :: <fmt:message key="settings"/></title>
    </head>
    <body onload="javascript: setTimeout(function () { $('#winModal').fadeOut('slow', function() {}); }, 3000);">
        <apus:top/>

        <div class="pull-left span7">       
            <h2 class="pull-left" style="margin-left: 50px"><fmt:message key="settings"/> </h2>

            <div class="pull-right">
                <a href="main?action=main.view" style="margin-top: 8px; margin-right: 40px" class="btn"> <i class="icon-arrow-left"></i> <fmt:message key="main.page"/> </a>
            </div> 

            <div style="clear:both;"></div>
            <div class="span6 well" style="margin-top: 20px">
                <h3><fmt:message key="change.password"/> </h3>
                <div style="margin-top: 10px" class="span2 pull-left">
                    <apus:winModal/>
                    <apus:failModal/>
                </div>
                <div style="clear:both;"></div>
                <form style="margin-top: 10px" class="form-horizontal" method="POST" action="main?action=password.save"
                      onsubmit="return passChangeFormVal(this);">
                    <fieldset>
                        <div id="oldPassDiv" class="control-group">
                            <label class="control-label" for="login"> <fmt:message key="password"/> </label>
                            <div class="controls">
                                <input name="oldPass" id="oldPass" type="password" oninput="fieldIsFilling(this)">
                                <span class="help-inline" ></span>
                            </div>
                        </div>
                        <div id="newPassDiv" class="control-group">
                            <label class="control-label" for="password"> <fmt:message key="new.password"/></label> 
                            <div class="controls">
                                <input name="newPass" id="newPass" type="password" oninput="fieldIsFilling(this)">
                                <span class="help-inline" ></span>
                            </div>
                        </div>
                        <div class="offset2">
                            <input class="btn btn-primary span2" type="submit" id="loginButton" name="loginButton" value="<fmt:message key="change.password"/>">
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="well span6 pull-left">       
                <h3> <fmt:message key="change.language"/> </h3>
                <form class="form-inline" style="margin-top: 10px" method="POST" action="main?action=lang.change">
                    <select name="lang">
                        <option value="be" <c:if test="${lang eq 'be'}"><c:out value="selected=\"true\""/></c:if>> Беларуская </option> 
                        <option value="en" <c:if test="${lang eq 'en'}"><c:out value="selected=\"true\""/></c:if>> English </option> 
                        <option value="ru" <c:if test="${lang eq 'ru'}"><c:out value="selected=\"true\""/></c:if>> Русский </option> 
                    </select>
                    <button class="btn btn-primary" type="submit"> <fmt:message key="save"/> </button>
                </form>
            </div>

        </div>

    </body>
</html>
