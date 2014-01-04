<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/HTML4/strict.dtd">
<fmt:setLocale value="${lang}"/>

<html>
    <head>
        <apus:bootstrap/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf8">
        <script type="text/javascript" src="js/jquery1-1.7.2.js"></script>
        <script type="text/javascript" src="js/bootstrap-alert.js" ></script> 
        <script type="text/javascript" src="js/jquery.validation.js" ></script> 
        <script type="text/javascript" src="js/form_validate.js">  </script>
        <title><fmt:message key="APUS"/> :: <fmt:message key="add.number"/></title>
    </head>
    <body onload="javascript: setTimeout(function () { $('#winModal').fadeOut('slow', function() {}); }, 3000);">

        <apus:top/>
        <apus:navtabs number="4"/>

        <div class="span7 offset2 well">
            <div class="pull-left">
                <apus:winModal/>
                <apus:failModal/>
            </div>
            <div style="margin-bottom: 20px" class="offset2">
                <h2><fmt:message key="add.number"/></h2>
                <h4>for: <c:out value="${subscriber.name}"/></h4>
            </div>

            <div id="mainDiv">

                <!--hidden form-->
                <form id="helpForm">
                    <input type="hidden" id="numberLength" value="<c:out value="${settings.numberSize}"/>">
                </form>

                <form class="form-horizontal" method="POST" action="main?action=phoneNumber.save"
                      onsubmit="return pnVal(this);">
                    <fieldset>
                        <input type="hidden" name="id" value="0">
                        <input type="hidden" name="sub_id" value="${subscriber.id}">
                        <c:set var="s_type" value="1" scope="page"/>

                        <div id="numDiv" class="control-group">

                            <div class="controls input-prepend">
                                <span class="add-on"><c:out value="${settings.numberPrefix}"/></span><input class="span3" type="text" id="number" name="number" onchange="fieldIsFilling(this)">
                                <span class="help-inline" ></span>
                            </div>
                        </div>
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
