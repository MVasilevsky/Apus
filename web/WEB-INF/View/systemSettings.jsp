<%-- 
    Document   : systemSettings
    Created on : 21.08.2012, 17:17:44
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
        <script type="text/javascript" src="js/form_validate.js">  </script>
        <script type="text/javascript" src="js/jquery-1.7.2.js">  </script>
        <script type="text/javascript" src="js/ajax.js">  </script>
        <script type="text/javascript" src="js/form_validate.js">  </script>
        <script type="text/javascript" src="js/bootstrap-alert.js">  </script>
        <script type="text/javascript" src="js/bootstrap-modal.js">  </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="APUS"/> :: <fmt:message key="sys.settings"/></title>
    </head>
    <body onload="javascript: setTimeout(function () { $('#winModal').fadeOut('slow', function() {}); }, 3000);">
        <apus:top/>
        <apus:navtabs number="6"/>
        <div class="span7">       
            <h2 class="pull-left" style="margin-left: 50px"><fmt:message key="sys.settings"/></h2>

            <!--            <div class="pull-right">
                            <a href="main?action=main.view" style="margin-top: 8px; margin-right: 40px" class="btn"> <i class="icon-arrow-left"></i> <fmt:message key="main.page"/> </a>
                        </div> -->



            <div style="margin-top: 15px" class="offset1 pull-left">
                <apus:winModal/>
                <apus:failModal/>
            </div>

            <div style="clear:both;"></div>
            <div class="span6 well" style="margin-top: 20px">


                <form style="margin-top: 10px" class="form-horizontal" method="POST" action="main?action=save.settings"
                      onsubmit="return sysSettingsVal(this);">
                    <fieldset>

                        <div id="prefixDiv" class="control-group">
                            <label class="control-label" for="prefix"> <fmt:message key="prefix"/> </label>
                            <div class="controls">
                                <input class="input-small" name="prefix" id="prefix" type="text" oninput="fieldIsFilling(this)"
                                       value="<c:out value="${apusSettings.numberPrefix}"/>">
                                <span class="help-inline" ></span>
                            </div>
                        </div>

                        <div id="lengthDiv" class="control-group">
                            <label class="control-label" for="length"> <fmt:message key="number.length"/></label> 
                            <div class="controls">
                                <input class="input-small" name="length" id="length" type="text" oninput="fieldIsFilling(this)"
                                       value="<c:out value="${apusSettings.numberSize}"/>">
                                <span class="help-inline" ></span>
                            </div>
                        </div>

                        <div id="tariffDiv" class="control-group">
                            <label class="control-label" for="tariff"> <fmt:message key="tariff"/></label> 
                            <div class="controls">
                                <input class="input-small" name="tariff" id="tariff" type="text" oninput="fieldIsFilling(this)"
                                       value="<c:out value="${apusSettings.tariff}"/>">
                                <span class="help-inline" ></span>
                            </div>
                        </div>

                        <div id="langDiv" class="control-group">
                            <label class="control-label" for="lang"> <fmt:message key="default.language"/></label> 
                            <div class="controls">
                                <select id="lang" name="lang">
                                    <option value="be" <c:if test="${apusSettings.defaultLanguage eq 'be'}"><c:out value="selected=\"true\""/></c:if>> Беларуская </option> 
                                    <option value="en" <c:if test="${apusSettings.defaultLanguage eq 'en'}"><c:out value="selected=\"true\""/></c:if>> English </option> 
                                    <option value="ru" <c:if test="${apusSettings.defaultLanguage eq 'ru'}"><c:out value="selected=\"true\""/></c:if>> Русский </option> 
                                </select>
                            </div>
                        </div>

                                
                        <div id="qSearchDiv" class="control-group">
                            <label class="control-label" for="qsearch"> <fmt:message key="quick.search"/></label> 
                            <div class="controls">
                                <input class="input-small" name="qsearch" id="qsearch" type="checkbox"
                                       value="true" <c:if test="${apusSettings.quickSearch}"><c:out value="checked"/></c:if> > 
                            </div>
                        </div>

                                
                        <div class="offset2">
                            <input class="btn btn-primary" type="submit" id="saveButton" name="saveButton" value="<fmt:message key="save.settings"/>">
                        </div>

                    </fieldset>
                </form>
            </div>
        </div>
    </body>
</html>