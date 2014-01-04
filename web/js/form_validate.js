//------------------------------fields-------------------------------------

// checks logins in all forms 
function loginVal(obj)
{
    var return_value = true; 
    var loginRegex = /^[a-zA-Z0-9_-]{4,16}$/;
    if (!$(obj).find('input').val().match(loginRegex)) {
        $(obj).addClass('error');
        $(obj).find('.help-inline').text("Incorrect login");
        if ($(obj).find('input').val().length<4) $(obj).find('.help-inline').text("Too short");
        if ($(obj).find('input').val().length>16) $(obj).find('.help-inline').text("Too long");
        return_value = false;
    } else {
        $(obj).removeClass('error');
        $(obj).find('.help-inline').text("");
    }
    return return_value;    
}

// checks passwords in all forms 
function passVal(obj)
{
    var return_value = true; 
    var passRegex = /^[a-zA-Z0-9_-]{5,18}$/;
    if (!$(obj).find('input').val().match(passRegex)) {
        $(obj).addClass('error');
        $(obj).find('.help-inline').text("Incorrect password");
        if ($(obj).find('input').val().length<5) $(obj).find('.help-inline').text("Too short");
        if ($(obj).find('input').val().length>18) $(obj).find('.help-inline').text("Too long");
        return_value = false;
    } else {
        $(obj).removeClass('error');
        $(obj).find('.help-inline').text("");
    }
    return return_value;    
}

// is passwords equals?
function confirmPassVal(obj1, obj2)
{
    var return_value = true; 
    if ($(obj1).find('input').val() != $(obj2).find('input').val()) {
        $(obj2).addClass('error');
        $(obj2).find('.help-inline').text("Different passwords");
        return_value = false;
    } else {
        $(obj2).removeClass('error');
        $(obj2).find('.help-inline').text("");
    }
    return return_value;    
}

// checks subscriber's name 
function subNameVal(obj)
{
    var return_value = true; 
    var regexp = /^[а-яА-Яa-zA-Z0-9 ,.'"_-]{4,60}$/;
    if (!$(obj).find('input').val().match(regexp)) {
        $(obj).addClass('error');
        $(obj).find('.help-inline').text("Incorrect name");

        if (jQuery.trim($(obj).find('input').val()).length < 4){
            $(obj).find('.help-inline').text("Too short name");
        }
        if ($(obj).find('input').val().length > 60){
            $(obj).find('.help-inline').text("Too long name");
        }
        return_value = false;
    } else {
        $(obj).removeClass('error');
        $(obj).find('.help-inline').text("");
    }
    return return_value;    
}

// checks subscriber's address 
function subAddressVal(obj)
{
    var return_value = true; 
    var regexp = /^[а-яА-Яa-zA-Z0-9 ,.'"_-]{6,100}$/;
    if (!$(obj).find('input').val().match(regexp)) {
        $(obj).addClass('error');
        $(obj).find('.help-inline').text("Incorrect address");
        if (jQuery.trim($(obj).find('input').val()).length<6) $(obj).find('.help-inline').text("Too short address");
        if ($(obj).find('input').val().length > 100) $(obj).find('.help-inline').text("Too long address");
        return_value = false;
    } else {
        $(obj).removeClass('error');
        $(obj).find('.help-inline').text("");
    }
    return return_value;    
}

// checks subscriber's passport number 
function subPassportVal(obj)
{
    var return_value = true; 
    var regexp = /^[a-zA-Z]{2}[0-9]{7}$/;
    if (!$(obj).find('input').val().match(regexp)) {
        $(obj).addClass('error');
        $(obj).find('.help-inline').text("AA0000000");
        return_value = false;
    } else {
        $(obj).removeClass('error');
        $(obj).find('.help-inline').text("");
    }
    return return_value;    
}

// checks subscriber's banking details 
function subBankVal(obj)
{
    var return_value = true; 
    if ( ( jQuery.trim($(obj).find('input').val()).length < 8) || ($(obj).find('input').val().length>100)) {
        $(obj).addClass('error');
        if (jQuery.trim($(obj).find('input').val()).length < 8) $(obj).find('.help-inline').text("Check this field");
        if ($(obj).find('input').val().length > 100) $(obj).find('.help-inline').text("Too long");
        return_value = false;
    } else {
        $(obj).removeClass('error');
        $(obj).find('.help-inline').text("");
    }
    return return_value;    
}

// checks phone number 
function phoneNumberVal(obj, digits)
{
    var return_value = true;
    var numRegexp = "^[0-9]{"+ digits +"}$";
    
    if (!$(obj).find('input').val().match(numRegexp)) {
        $(obj).addClass('error');
        $(obj).find('.help-inline').text(digits+" digits");
        return_value = false;
    
        if(!$(obj).find('input').val()){                                      
            $(obj).find('.help-inline').text("Fill this field");
        } 
    } else {
        $(obj).removeClass('error');
        $(obj).find('.help-inline').text("");
    }
    return return_value;  
}

// checks number prefix field 
function prefixVal(obj)
{
    var return_value = true; 
    var regexp = /^[+]{0,1}[0-9]{0,9}$/;
    if (!$(obj).find('input').val().match(regexp)) {
        $(obj).addClass('error');
        $(obj).find('.help-inline').text("Wrong format");
        //        if ($(obj).find('input').val().length>10){
        //            $(obj).find('.help-inline').text("Too long prefix");
        //        }
        return_value = false;
    } else {
        $(obj).removeClass('error');
        $(obj).find('.help-inline').text("");
    }
    return return_value;    
}

// checks number length field 
function numLengthVal(obj)
{
    var return_value = true; 
    var regexp = /^[1-9]{1}[0-9]{0,1}$/;
    if ((!$(obj).find('input').val().match(regexp))||($(obj).find('input').val()>20)) {
        $(obj).addClass('error');
        $(obj).find('.help-inline').text("Wrong format");
        if ($(obj).find('input').val()>20){
            $(obj).find('.help-inline').text("Too big value");
        }
        if (!$(obj).find('input').val()){
            $(obj).find('.help-inline').text("Fill this field");
        }
        return_value = false;
    } else {
        $(obj).removeClass('error');
        $(obj).find('.help-inline').text("");
    }
    return return_value;    
}


// checks tariff field 
function tariffVal(obj)
{
    var return_value = true; 
    var regexp = /^[0-9]{0,6}$/;
    if (!$(obj).find('input').val().match(regexp)) {
        $(obj).addClass('error');
        $(obj).find('.help-inline').text("Wrong format");
        if ($(obj).find('input').val().length>6){
            $(obj).find('.help-inline').text("Too big value");
        }
        if (!$(obj).find('input').val()) {
            $(obj).find('.help-inline').text("Fill this field");
        }
        return_value = false;
    } else {
        $(obj).removeClass('error');
        $(obj).find('.help-inline').text("");
    }
    return return_value;    
}

// --------------------------------- forms -------------------------------------


// checks login form on the start page 
function loginFormVal(obj) {
    var return_value = true;
    if(!loginVal($(obj).find('#loginDiv'))){
        return_value=false;
    }           
    if(!passVal($(obj).find('#passDiv'))) {  
        return_value=false;
    }
    return return_value;
}


// checks password change form 
function passChangeFormVal(obj) {
    var return_value = true;
    if(!passVal($(obj).find('#newPassDiv'))){
        return_value=false;
    }           
    if(!passVal($(obj).find('#oldPassDiv'))) {  
        return_value=false;
    }
    return return_value;
}


// checks adding new employee form
function newEmplVal(obj)
{
    var return_value = true;
    if(!loginVal($(obj).find('#loginDiv'))){
        return_value=false;
    }           
    if(!passVal($(obj).find('#passDiv'))) {  
        return_value=false;
    }
    if( !confirmPassVal($(obj).find('#passDiv'), $(obj).find('#confirmPassDiv')) ) {
        return_value=false;
    }
    return return_value;
}

// checks add and edit person forms
function personVal(obj){
    var return_value = true;    
    if (!subNameVal($(obj).find('#nameDiv'))){
        return_value = false;
    }
    if (!subAddressVal($(obj).find('#addressDiv'))){
        return_value = false;
    }
    if (!subPassportVal($(obj).find('#passportDiv'))){
        return_value = false;
    }
    return return_value;
}

// checks add and edit organization forms
function organizationVal(obj){
    var return_value = true;    
    if (!subNameVal($(obj).find('#nameDiv'))){
        return_value = false;
    }
    if (!subAddressVal($(obj).find('#addressDiv'))){
        return_value = false;
    }
    if (!subBankVal($(obj).find('#bankDiv'))){
        return_value = false;
    }
    return return_value;
}

// checks add and edit phone number forms
function pnVal(obj){
    var return_value = true;
    if (!phoneNumberVal($(obj).find('#numDiv'), $(obj).parent().find('#numberLength').val())) {
        return_value = false;
    }
    return return_value;
}

// checks system setings form
function sysSettingsVal(obj){
    var return_value = true;    
    if (!prefixVal($(obj).find('#prefixDiv'))){
        return_value = false;
    }
    if (!numLengthVal($(obj).find('#lengthDiv'))){
        return_value = false;
    }
    if (!tariffVal($(obj).find('#tariffDiv'))){
        return_value = false;
    }
    return return_value;
}





// remove red frame from field, when user starts input
function fieldIsFilling(obj){
    $(obj).parent('.controls').parent('.control-group').removeClass('error');
    $(obj).parent('.controls').children('.help-inline').text("");
}