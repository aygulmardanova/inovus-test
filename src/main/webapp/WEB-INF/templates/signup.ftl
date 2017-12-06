<#ftl encoding="utf-8"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Регистрация </title>
    <script type="text/javascript" src="${rc.getContextPath()}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${rc.getContextPath()}/resources/js/validation.js"></script>
</head>
<body>

<form id="reg_form" action="sign-up" method="post">
    <fieldset>
        <legend>Регистрация</legend>

        <#if server_msg??>
        <p id="server_msg" style="color: orange">${server_msg}</p>
        </#if>

        <p id="reg_msg" style="color: red"></p>
        <label for="reg_username">Имя пользователя</label>
        <input type="text" name="reg_username" id="reg_username" oninput="correctUsername()" required/>

        <p id="reg_psw_msg" style="color: red"></p>
        <label for="reg_password">Пароль</label>
        <input type="password" name="reg_password" id="reg_password" oninput="correctPassword()" required>

        <p id="reg_rpt_psw_msg" style="color: red"></p>
        <label for="reg_password_repeat">Повтор пароля</label>
        <input type="password" name="reg_password_repeat" id="reg_password_repeat" oninput="correctPasswordRepeat()"
               required>
        <br/>

        <input type="submit" value="Зарегистрироваться">
    </fieldset>
</form>
<a href="sign-in">Войти</a>

</body>
