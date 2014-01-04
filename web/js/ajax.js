$(document).ready(function() {

    $('#srchFrm').submit(function() {
        $.ajax({
            type: 'GET',
            url: "./main?action=cashier.search&name=" + $('#srchInpt').val(),
            cache: false,
            success: function(html) {
                $("#searchResults").html(html);
            }
        });
        return false;
    });

    $('#srchInpt').keyup(function() {
        if ($('#isquick').val() == "true") {
            $.ajax({
                type: 'GET',
                url: "./main?action=cashier.search&name=" + $('#srchInpt').val(),
                cache: false,
                success: function(html) {
                    $("#searchResults").html(html);
                }
            });
        }
    });

});
