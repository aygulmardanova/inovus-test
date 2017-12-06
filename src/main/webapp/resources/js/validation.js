correctUsername = function () {
    var username = $.trim($("#reg_username").val()).toLowerCase();
    if ($("#server_msg") != null) {
        $("#server_msg").css({'display': 'none'});
    }
    if (username != '') {
        var pattern = /^[a-zA-Z][a-zA-Z0-9.]{3,20}$/i;
        $.ajax({
            type: 'POST',
            url: "ajax/checkUsername",
            data: {"username": username},
            dataType: "text",
            async: false,
            success: function (response_data) {
                if (response_data == 'true') {
                    $("#reg_msg").html("Такое имя пользователя уже занято");
                } else {
                    if (pattern.test(username)) {
                        $("#reg_msg").html("");
                        return true;
                    } else if (username.length < 4) {
                        $("#reg_msg").html("Имя пользователя дб длиннее 4");
                    } else {
                        $("#reg_msg").text("Имя пользователя должно быть длиннее 4 символов " +
                            "и состоять из цифр, букв английского алфавита и точек.  Первый символ - обяательно буква.");
                    }
                }
                return false;
            }
        });
    } else {
        $('#reg_msg').text('Имя пользователя не мб пустым');
        return false;
    }
}

correctPassword = function () {
    var pass = $("#reg_password").val();
    var pattern = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,20}$/g;
    if ($("#server_msg") != null) {
        $("#server_msg").css({'display': 'none'});
    }
    if (pass != '') {
        if (pattern.test(pass)) {
            $('#reg_psw_msg').text('');
            return true;
        } else {
            $('#reg_psw_msg').text('Пароль недостаточно сложен: должны быть ' +
                'цифры, заглавные и строчные буквы и длина минимум 8 символов');
        }
        return false;
    } else {
        $('#reg_psw_msg').text('Пароль не может быть пустым');
        return false;
    }
}

correctPasswordRepeat = function () {
    var pass1 = $("#reg_password").val();
    var pass2 = $("#reg_password_repeat").val();
    if ($("#server_msg") != null) {
        $("#server_msg").css({'display': 'none'});
    }
    if (pass2 != '') {
        if (pass1 != pass2) {
            $('#reg_rpt_psw_msg').text('Пароль и повтор пароля не совпадают');
            return false;
        } else {
            $('#reg_rpt_psw_msg').text('');
            return true;
        }
    } else {
        $('#reg_rpt_psw_msg').text('Повторите пароль');
        return false;
    }
}

checkLogForm = function () {
    var username = $("#log_username").val();
    var password = $("#log_password").val();
    if (username == '' ||
        password == '') {
        $("#fill_msg").text('Необходимо ввести учетные данные');
        if ($("#logout_msg") != null) {
            $("#logout_msg").css({'display': 'none'});
        }
        if ($("#error_msg") != null) {
            $("#error_msg").css({'display': 'none'});
        }
        return false;
    } else {
        return true;
    }
}
