$(document).ready(function(){
    $('#body').ready(function(){
        $('#searchResults').height(Math.round(($(window).height()-140)*0.40));
        $('#accSearchResults').height(Math.round(($(window).height()-140)*0.20));
    });
});

function choose (obj) {
    $('#subChkd').val($(obj).parent().parent('.rowSearch').find('.subName').val());
    $('#subChkd').html($(obj).parent().parent('.rowSearch').find('.subName').val());
        
    $('#accChkd1').val("");
    $('#accChkd2').val("");
        
    $('#payBtn').attr('disabled', true);
        
    $.ajax({
        type: 'GET',
        url: "./main?action=cashier.account.search&sub_id="+$(obj).parent().parent('.rowSearch').find('.subId').val(),
        cache: false,
        success: function(html){
            $("#accSearchResults").html(html);
        }
    });      
}
   
function chooseAcc(obj) {   
    $('#accChkd1').val($(obj).parent().parent('.rowSearch').find('.stDate').val());
    $('#accChkd2').val($(obj).parent().parent('.rowSearch').find('.enDate').val()); 
    $('#accountId').val($(obj).parent().parent('.rowSearch').find('.acId').val());
    $('#sum').val($(obj).parent().parent('.rowSearch').find('.summa').val());
        
    $('#payBtn').attr('disabled', false);
}

