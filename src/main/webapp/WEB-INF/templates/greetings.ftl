<#ftl encoding="utf-8"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset='UTF-8'>
    <title> Страница приветствия </title>
    <script type="text/javascript" src="${rc.getContextPath()}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${rc.getContextPath()}/resources/js/getDate.js"></script>
</head>
<body>

<h1 id="greeting">${user.username}!</h1>

<#if userLastVisitTime??>
<p id="info_about_user">В последний раз Вы заходили с адреса ${user.lastVisitIp}
    в ${userLastVisitTime.hour} часов ${userLastVisitTime.minute} мин
    ${userLastVisitTime.dayOfMonth} ${monthName} ${userLastVisitTime.year} года.</p>
<#else>
<p>Вы на этой странице впервые.</p>
</#if>
<p id="total_visit_amount">Общее количество просмотров страницы: ${count}</p>
<form action="logout" method="get">
    <input type="submit" value="Выйти">
</form>

</body>