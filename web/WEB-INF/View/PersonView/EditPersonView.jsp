<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <title><fmt:message key="APUS"/> :: <fmt:message key="edit.person"/></title>
    </head>

    <body>

        <apus:top/>
        <apus:navtabs number="1"/>

        <div class="span7 offset2 well">

            <div style="margin-bottom: 20px" class="center_title">
                <h2><fmt:message key="edit.person"/></h2>
            </div>
            <div>

                <form class="form-horizontal" method="POST" action="main?action=person.save"
                      onsubmit="return personVal(this);">
                    <fieldset>

                        <input type="hidden" name="id" value="${person.id}">

                        <div id="nameDiv" class="control-group">
                            <label class="control-label" for="name"><fmt:message key="per.name"/></label>
                            <div class="controls">
                                <input class="span4" type="text" id="name" name="name" 
                                       value="${person.name}"  oninput="fieldIsFilling(this)">
                                <span class="help-inline" ></span>
                            </div>
                        </div>

                        <div id="passportDiv" class="control-group">
                            <label class="control-label" for="passport"><fmt:message key="passport"/></label>
                            <div class="controls">
                                <input class="span4" type="text" id="passport" name="passport" 
                                       value="${person.passportNumber}"  oninput="fieldIsFilling(this)">
                                <span class="help-inline" ></span>
                            </div>
                        </div>

                        <div id="addressDiv" class="control-group">
                            <label class="control-label" for="address"><fmt:message key="address"/></label>
                            <div class="controls">
                                <input class="span4" type="text" id="address" name="address" 
                                       value="${person.address}" oninput="fieldIsFilling(this)">
                                <span class="help-inline" ></span>
                            </div>
                        </div>
</fieldset>
                        <div class="center_title">
                            <input class="btn btn-primary" type="submit" name="saveButton" value="<fmt:message key="save"/>">
                            <a href="main?action=person.list" class="btn" name="cancelButton"> <fmt:message key="cancel"/> </a>                            
                        </div>

                    
                </form>

            </div>

        </div> 
    </body>
</html>
