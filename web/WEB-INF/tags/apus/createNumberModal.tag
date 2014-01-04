<%-- 
    Document   : createNumberModal
    Created on : 26.08.2012, 0:24:27
    Author     : 21vek
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>


<%-- any content can be specified here e.g.: --%>
<div class="modal hide fade" id="createNumberModal">
    <div class="modal-header">
        <button class="close" data-dismiss="modal">×</button>
        <h3><fmt:message key="add.edit.number"/></h3>
    </div>


    <!--hidden form-->
    <form id="helpForm">
        <input type="hidden" id="numberLength" value="<c:out value="${settings.numberSize}"/>">
    </form>

    <form style="margin-bottom: 0px" class="form-horizontal" method="POST" action="main?action=phoneNumber.save"
          onsubmit="return pnVal(this);">
        <fieldset>
            <input type="hidden" name="id" id="id" value="0">
            <input type="hidden" name="sub_id" id="sub_id" value="">
            <c:set var="s_type" value="1" scope="page"/>

            <div id="numDiv" class="control-group modal-body">

                <div class="controls input-prepend">
                    <span class="add-on"><c:out value="${settings.numberPrefix}"/></span><input class="span3" type="text" id="number" name="number" onchange="fieldIsFilling(this)">
                    <span class="help-inline" ></span>
                </div>
            </div>
            <div class="modal-footer">
                <input class="btn btn-primary" type="submit" name="saveButton" value="<fmt:message key="save"/>">
                <button id="cancel" data-dismiss="modal" class="btn"><fmt:message key="cancel"/></button>
            </div>
        </fieldset>
    </form> 


</div>