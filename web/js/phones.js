/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    
    $('#srchFrm').submit(function(){
        $.ajax({
            type: 'GET',
            url: "./main?action=phoneNumber.search&name="+$('#srchInpt').val(),
            cache: false,
            success: function(html){
                $("#searchResults").html(html);
            }
        });
        return false;
    });
		
    $('#srchInpt').keyup(function(){
        if ($('#isquick').val()=="true") {
            $.ajax({
                type: 'GET',
                url: "./main?action=phoneNumber.search&name="+$('#srchInpt').val(),
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
    
    $('.editLink').click(function(e){
        //$('#myModal').find('#confirm').attr("href", $(this).attr("href")+"&search="+$('#srchInpt').val());
        $('#createNumberModal').find('#sub_id').val( $(this).parent().parent().find('#subIdField').val());
        $('#createNumberModal').find('#id').val($(this).parent().parent().find('#numIdField').val());
        $('#createNumberModal').find('#number').val($(this).parent().parent().find('#numberField').val());
        
        $('#createNumberModal').modal('show');
           
        e.preventDefault(); 
    });
    
//    $('#srchInpt').focus(function(){
//        $(this).animate({
//            width:"250px"
//        },300);
//        $(this).animate({
//            width:"247px"
//        },200);
//    });
//    
//    $('#srchInpt').focusout(function(){
//        $(this).animate({
//            width:"150px"
//        },300);
//    });
    
});