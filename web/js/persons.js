/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    
    
    
    $('#srchFrm').submit(function(){
        $.ajax({
            type: 'GET',
            url: "./main?action=person.search&name="+$('#srchInpt').val(),
            cache: false,
            success: function(html){
                $("#searchResults").html(html);
            }
        });
        return false;
    });
		
    $('#srchInpt').keyup(function(){
//        alert(('#searchResults').html());
        if ($('#isquick').val()=="true") {
            $.ajax({
            type: 'GET',
            url: "./main?action=person.search&name="+$('#srchInpt').val(),
            cache: false,
            success: function(html){
                $("#searchResults").html(html);
            }
        });
        }        
    }); 
    
    $('.dLink').click(function(e){
        $('#myModal').find('#confirm').attr("href", $(this).attr("href")+"&search="+$('#srchInpt').val());
        $('#subject').html($(this).parent().parent().find('#nameLink').html());
        $('#myModal').modal('show');
           
        e.preventDefault(); 
    });

    
    $('.addLink').click(function(e){
        
        //$('#myModal').find('#confirm').attr("href", $(this).attr("href")+"&search="+$('#srchInpt').val());
        $('#createNumberModal').find('#sub_id').val($('#subIdField').val());
        //$('#subject').html($(this).parent().parent().find('#nameLink').html());
        $('#createNumberModal').modal('show');
           
        e.preventDefault(); 
    });
    
//    $('#srchInpt').focus(function(){
//        $(this).animate({width:"300px"},300);
//        $(this).animate({width:"295px"},200);
//    });
//    
//    $('#srchInpt').focusout(function(){
//        $(this).animate({width:"150px"},300);
//    });
    
});



