<%-- 
    Document   : EditAccountView
    Created on : Aug 1, 2012, 8:24:24 AM
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
        <title><fmt:message key="APUS"/> :: <fmt:message key="edit.account"/></title>
    </head>
    <body>
        <apus:top/>
        <apus:navtabs number="3"/>
        
        <div class="span7 offset2 well">
            <div>
            <% Account account = (Account) request.getAttribute("account");%>
            <h2><fmt:message key="edit.account"/> <fmt:message key="for.subscriber"/> <%= account.getSubscriber().getName()%></h2>
            <form class="form-horizontal" method="POST" action="./main?action=account.save">
                <fieldset>

                    <input type="hidden" name="id" value="<%= account.getId()%>">
                    <%--  <input type="hidden" name="sub_id" value="<%= account.getSubscriber().getId()%>"> --%>

                    <%
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        String stdate = sdf.format(account.getPeriod().getStartDate());
                        String enddate = sdf.format(account.getPeriod().getStartDate());
                    %>
                    <div class="control-group">
                        <label class="control-label" for="startDate"><fmt:message key="start.date"/></label>
                        <div class="controls">
                            <input class="input-small" type="text" id="startDate" name="startDate" 
                                   value="<%= stdate%>"  oninput="fieldIsFilling(this)">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="endDate"><fmt:message key="end.date"/></label>
                        <div class="controls">
                            <input class="input-small" type="text" id="endDate" name="endDate" value="<%= enddate%>">
                        </div>
                    </div>
                    <div class="span4 offset1">
                        <input class="btn btn-primary" type="submit" name="saveButton" value="<fmt:message key="save"/>">
                        <input class="btn" type="submit" name="cancelButton" value="<fmt:message key="cancel"/>">
                    </div>
                </fieldset>
            </form>
            </div>
        </div>
                        
                        
    </body>
</html>
