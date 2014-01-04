/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
        
    $('.dLink').click(function(e){
        alert("afasf");
        $('#myModal').find('#confirm').attr("href", $(this).attr("href"));
        $('#subject').html($(this).parent().find('#employeeName').val());
        $('#myModal').modal('show');
           
        e.preventDefault(); 
    });
    
    $('#cancel').click(function(){
        $('#myModal').modal('hide');
    });
});


