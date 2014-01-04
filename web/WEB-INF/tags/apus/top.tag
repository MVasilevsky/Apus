<%@tag description="Top of the site" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<div class="navbar" >
    <div class="navbar-inner">

        <div class="container">
            <a class="brand" href="main?action=main.view">
                <img src="img/phone_little_grey_final.png"> <fmt:message key="APUS"/>
            </a>

            
            <c:if test="${userAuth!=null}">
                <ul class="nav pull-right">

                    <li> 
                        <p style="margin-right: 10px" class="navbar-text" id="user">
                            <span class="label label-info">
                                <a style="color: white" href="main?action=settings">
                                    <i style="margin-right: 5px" class="icon-wrench"></i><c:out value="${userAuth.username}" />
                                </a>
                            </span>
                        </p>
                    </li>
                    

                    <li>
                        <p class="navbar-text" id="user">
                            <a class="btn btn-mini" href="./session?action=logout"><fmt:message key="log.out"/></a>
                        </p>
                    </li>
                </ul>

            </c:if>


        </div>

    </div>
</div>
