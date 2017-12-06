<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset='utf-8'>
    <title> Вход </title>
    <script type="text/javascript" src="${rc.getContextPath()}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${rc.getContextPath()}/resources/js/validation.js"></script>
</head>
<body>

<#if logout_msg??>
    <h2 style="color: green" id="logout_msg">${logout_msg}</h2>
</#if>
<#if error_msg??>
    <h2 style="color: red" id="error_msg">${error_msg}</h2>
</#if>
<h2 id="fill_msg" style="color: orange"></h2>

<form onsubmit="return checkLogForm();" action="sign-in" method="post" id="login_form" name="login_form">
    <fieldset>
        <legend>Вход</legend>
        <label for="username">Имя пользователя</label>
        <#if cookie_username??>
            <input type="text" name="log_username" id="log_username" value="${cookie_username}"/>
        <#else>
            <input type="text" name="log_username" id="log_username"/>
        </#if>
        <br/>
        <label for="password">Пароль</label>
        <input type="password" name="log_password" id="log_password">
        <br/>
        <input type="submit" value="Войти">
    </fieldset>
</form>
<a href="sign-up">Зарегистрироваться</a>
</body>