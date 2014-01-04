<%-- 
    Document   : CreateEmployeeView
    Created on : 18.08.2012, 0:47:15
    Author     : Vasileusky
--%>
<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}"/>

<html>
    <head>
        <apus:bootstrap/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
        <script type="text/javascript" src="js/bootstrap-alert.js" ></script> 
        <script type="text/javascript" src="js/bootstrap-modal.js">  </script>
        <script type="text/javascript" src="js/jquery.validation.js" ></script> 
        <script type="text/javascript" src="js/form_validate.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {

                $("a").tooltip();
                $("span").tooltip();

                $('.dLink').click(function(e) {
                    $('#myModal').find('#confirm').attr("href", $(this).attr("href"));
                    $('#subject').html($(this).parent().find('#employeeName').val());
                    $('#myModal').modal('show');

                    e.preventDefault();
                });

                $('#cancel').click(function() {
                    $('#myModal').modal('hide');
                });
            });
        </script>
        <title><fmt:message key="APUS"/> :: <fmt:message key="new.employee"/></title>
    </head>
    <body>
        <apus:top/>
        <apus:navtabs number="7"/>

        <div style="clear:both;"></div>

        <div class="span7 offset2 well">

            <div style="margin-bottom: 20px" class="center_title">
                <h2><fmt:message key="new.employee"/></h2>
            </div>
            <div style="margin-left: 90px"><apus:failModal/></div>
            <form class="form-horizontal" method="POST" action="./main?action=user.save"
                  onsubmit="return newEmplVal(this)">
                <fieldset>

                    <div id="loginDiv" class="control-group">
                        <label class="control-label" for="name"><fmt:message key="login"/></label>
                        <div class="controls">
                            <input class="span3" type="text" id="login" name="login" oninput="fieldIsFilling(this)">
                            <span class="help-inline" ></span>
                        </div>
                    </div>

                    <div id="passDiv" class="control-group">
                        <label class="control-label" for="bank"><fmt:message key="password"/></label>
                        <div class="controls">
                            <input class="span3" type="password" id="pass" name="pass" oninput="fieldIsFilling(this)">
                            <span class="help-inline" ></span>
                        </div>
                    </div>

                    <div id="confirmPassDiv" class="control-group">
                        <label class="control-label" for="bank"><fmt:message key="repeat.password"/></label>
                        <div class="controls">
                            <input class="span3" type="password" id="confPass" name="confPass" oninput="fieldIsFilling(this)">
                            <span class="help-inline" ></span>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <select name="role">
                                <option value="1"><fmt:message key="administrator"/></option>
                                <option value="2"><fmt:message key="cashier"/></option>
                            </select>
                        </div>
                    </div>

                </fieldset>
                <div class="center_title">
                    <input class="btn btn-primary" type="submit" name="saveButton" value="<fmt:message key="new.employee"/>">
                    <a href="./main?action=employee" class="btn" name="cancelButton"><fmt:message key="cancel"/></a>
                </div>



            </form>

        </div>

    </body>
</html>
