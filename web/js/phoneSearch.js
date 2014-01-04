/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    
    if ($('#isFilter').val()) {
        $('#searchSwitch').addClass('active');
        $('#searchDiv').fadeIn(0);
    }
    
    $('#searchSwitch').click(function() {
        if($('#searchDiv').is(':visible')) {
            $('#searchSwitch').removeClass('active');
            $('#searchDiv').fadeOut('fast');
        } else  {
            $('#searchSwitch').addClass('active');
            $('#searchDiv').fadeIn('fast');
        }
    })
//    $('#searchSwitch').click(function(){
//        $('#searchDiv').slideToggle(250);
//        return false;
//    });
});

