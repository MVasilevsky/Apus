<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/HTML4/strict.dtd">
<fmt:setLocale value="${lang}"/>

<html>
    <head>
        <apus:bootstrap/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf8">
        <script type="text/javascript" src="js/bootstrap-alert.js" ></script> 
        <script type="text/javascript" src="js/jquery.validation.js" ></script> 
        <script type="text/javascript" src="js/form_validate.js">  </script>
        <script type="text/javascript" src="js/jquery-1.7.2.js">  </script>
        <title><fmt:message key="APUS"/> :: <fmt:message key="new.organization"/></title>
    </head>
    <body>

        <apus:top/>
        <apus:navtabs number="2"/>

        <div class="span7 offset2 well">

            <div style="margin-bottom: 20px" class="center_title">
                <h2><fmt:message key="new.organization"/></h2>
            </div>

            <form class="form-horizontal" method="POST" action="main?action=organization.save"
                  onsubmit="return organizationVal(this);">
                <fieldset>

                    <input type="hidden" name="id" value="0">

                    <div id="nameDiv" class="control-group">
                        <label class="control-label" for="name"><fmt:message key="org.name"/></label>
                        <div class="controls">
                            <input class="span4" type="text" id="name" name="name" oninput="fieldIsFilling(this)">
                            <span class="help-inline" ></span>
                        </div>
                    </div>

                    <div id="bankDiv" class="control-group">
                        <label class="control-label" for="bank"><fmt:message key="banking.details"/></label>
                        <div class="controls">
                            <input class="span4" type="text" id="bank" name="bank" oninput="fieldIsFilling(this)">
                            <span class="help-inline" ></span>
                        </div>
                    </div>

                    <div id="addressDiv" class="control-group">
                        <label class="control-label" for="address"><fmt:message key="address"/></label>
                        <div class="controls">
                            <input class="span4" type="text" id="address" name="address" oninput="fieldIsFilling(this)">
                            <span class="help-inline" ></span>
                        </div>
                    </div>

                </fieldset>
                <div class="center_title">
                    <input class="btn btn-primary" type="submit" name="saveButton" value="<fmt:message key="new.organization"/>">
                    <a href="main?action=organization.list" class="btn" name="cancelButton"><fmt:message key="cancel"/></a>
                </div>



            </form>

        </div>
    </body>
</html>
