<%-- 
    Document   : deleteModal
    Created on : 23.08.2012, 23:51:54
    Author     : 21vek
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>


<%-- any content can be specified here e.g.: --%>
<div class="modal hide fade" id="myModal">
  <div class="modal-header">
    <button class="close" data-dismiss="modal">×</button>
    <h3><fmt:message key="warning"/></h3>
  </div>
  <div class="modal-body">
    <p><fmt:message key="are.you.sure"/> <span id="subject"></span>?</p>
  </div>
  <div class="modal-footer">
    <a id="confirm" href="#" class="btn btn-danger"><fmt:message key="remove"/></a>
    <a id="cancel" href="#" data-dismiss="modal" class="btn"><fmt:message key="cancel"/></a>
  </div>
</div>