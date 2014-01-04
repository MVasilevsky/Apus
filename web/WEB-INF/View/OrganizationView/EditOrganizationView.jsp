<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <title><fmt:message key="APUS"/> :: <fmt:message key="edit.organization"/></title>
    </head>

    <body>

        <apus:top/>
        <apus:navtabs number="2"/>

        <div class="span7 offset2 well">

            <div style="margin-bottom: 20px" class="center_title">
                <h2><fmt:message key="edit.organization"/></h2>
            </div>

            <div>            
                <form class="form-horizontal" method="POST" action="main?action=organization.save"
                      onsubmit="return organizationVal(this);">
                    <fieldset>

                        <input type="hidden" name="id" value="${organization.id}">

                        <div id="nameDiv" class="control-group">
                            <label class="control-label" for="name"><fmt:message key="org.name"/></label>
                            <div class="controls">
                                <input class="span4" type="text" id="name" name="name" 
                                       value="${organization.name}" oninput="fieldIsFilling(this)">
                                <span class="help-inline" ></span>
                            </div>
                        </div>

                        <div id="bankDiv" class="control-group">
                            <label class="control-label" for="bank"><fmt:message key="banking.details"/></label>
                            <div class="controls">
                                <input class="span4" type="text" id="bank" name="bank"
                                       value="${organization.bankingDetails}" oninput="fieldIsFilling(this)">
                                <span class="help-inline" ></span>
                            </div>
                        </div>

                        <div id="addressDiv" class="control-group">
                            <label class="control-label" for="address"><fmt:message key="address"/></label>
                            <div class="controls">
                                <input class="span4" type="text" id="address" name="address" 
                                       value="${organization.address}" oninput="fieldIsFilling(this)">
                                <span class="help-inline" ></span>
                            </div>
                        </div>
                    </fieldset>
                    <div class="center_title">
                        <input class="btn btn-primary" type="submit" name="saveButton" value="<fmt:message key="save"/>">
                        <a href="main?action=organization.list" class="btn" name="cancelButton"><fmt:message key="cancel"/></a>
                    </div>


                </form>

            </div>          
        </div>                    
    </body>
</html>
