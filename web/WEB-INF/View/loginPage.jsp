<%-- 
    Document   : loginPage
    Created on : 14.08.2012, 14:03:10
    Author     : Vasileusky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <apus:bootstrap /> 
        <script type="text/javascript" src="js/bootstrap-alert.js" ></script> 
        <script type="text/javascript" src="js/jquery.validation.js" ></script> 
        <script type="text/javascript" src="js/form_validate.js">  </script>
        <script type="text/javascript" src="js/jquery-1.7.2.js">  </script>
        <title><fmt:message key="APUS"/> :: <fmt:message key="welcome"/></title>
    </head>
    <body>
        <apus:top/>
        <div class="well">
            <h1 style="margin-bottom: 20px" class="center_title"><fmt:message key="APUS.IS"/></h1>
            <div>
                <apus:loginFail/>  
            <form class="form-horizontal offset2" method="POST" action="session?action=login"
                  onsubmit="return loginFormVal(this);">
                <fieldset>
                    <div id="loginDiv" class="control-group">
                        <label class="control-label" for="login"><fmt:message key="login"/></label>
                        <div class="controls">
                            <input name="login" id="login" type="text" oninput="fieldIsFilling(this)">
                            <span class="help-inline" ></span>
                        </div>
                    </div>
                    <div id="passDiv" class="control-group">
                        <label class="control-label" for="password"><fmt:message key="password"/></label> 
                        <div class="controls">
                            <input name="password" id="password" type="password" oninput="fieldIsFilling(this)">
                            <span class="help-inline" ></span>
                        </div>
                    </div>
                    <div class="offset2">
                        <input class="btn btn-primary span2" type="submit" id="loginButton" name="loginButton" value="<fmt:message key="log.in"/>">
                    </div>
                </fieldset>
            </form>
        </div>
            </div>
    </body>
</html>
