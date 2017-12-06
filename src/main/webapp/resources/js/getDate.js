$(document).ready(function () {
    var date = new Date();
    var hours = date.getHours();
    if (hours >= 6 && hours < 10) {
        $("#greeting").prepend("Доброе утро, ");
    } else if (hours >= 10 && hours < 18) {
        $("#greeting").prepend("Добрый день, ");
    } else if (hours >= 18 && hours < 22) {
        $("#greeting").prepend("Добрый вечер, ");
    } else {
        $("#greeting").prepend("Доброй ночи, ");
    }
});