<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags/apus" prefix="apus" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/HTML4/strict.dtd">
<fmt:setLocale value="${lang}"/>

<html>
    <head>
        <apus:bootstrap/>
        <script type="text/javascript" src="js/modals.js">  </script>
        <script type="text/javascript" src="js/bootstrap-alert.js">  </script>
        <script type="text/javascript" src="js/jquery-1.7.2.js">  </script>
        <script type="text/javascript" src="js/bootstrap-modal.js">  </script>
        <script type="text/javascript" src="js/bootstrap-alert.js">  </script>
        <script type="text/javascript" src="js/bootstrap-tooltip.js">  </script>
        <script type="text/javascript" src="js/tooltips.js"> </script>
        <script type="text/javascript">
            $(document).ready(function(){
        
                $("a").tooltip();
                $("span").tooltip();
        
                $('.dLink').click(function(e){
                    $('#myModal').find('#confirm').attr("href", $(this).attr("href"));
                    $('#subject').html($(this).parent().find('#accName').val());
                    $('#myModal').modal('show');
           
                    e.preventDefault(); 
                });
    
                $('#cancel').click(function(){
                    $('#myModal').modal('hide');
                });
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf8">
        <title><fmt:message key="APUS"/> :: <fmt:message key="account.list"/></title>
    </head>
    <body  onload="javascript:$('.scrtable').height($(window).height()-160);  setTimeout(function () { $('#winModal').fadeOut('slow', function() {}); }, 3000);">
        <apus:top/>
        <apus:deleteModal/>
        <apus:only-user role="ADMIN"><apus:navtabs number="3"/></apus:only-user>
        <apus:account-list/>
    </body>
</html>
