package com.inovus.controllers;

import com.inovus.models.User;
import com.inovus.services.interfaces.PagesStatisticService;
import com.inovus.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.regex.Pattern;


@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    PagesStatisticService pagesStatisticService;

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9.]{3,20}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,20}$");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirectToGreetingsPage(ModelMap model) throws IOException {

        return "redirect:greetings";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String returnSignInPage(ModelMap model,
                                   HttpSession session,
                                   @RequestParam(value = "logout_msg", required = false) String logout_msg,
                                   @RequestParam(value = "error_msg", required = false) String error_msg,
                                   HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return "redirect:greetings";
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    model.addAttribute("cookie_username", username);
                }
            }
        }
        if (logout_msg != null) {
            model.addAttribute("logout_msg", "Вы вышли из приложения");
        }
        if (error_msg != null) {
            model.addAttribute("error_msg", "Неверные имя пользователя и пароль");
        }
        return "signin";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public String signIn(ModelMap model,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         HttpSession session,
                         @RequestParam(value = "log_username") String username,
                         @RequestParam(value = "log_password") String password) throws IOException {

        String ip = InetAddress.getLocalHost().getHostAddress();
        boolean isCorrect = userService.isCorrectUser(username.toLowerCase(), password);
        if (!isCorrect) {
            model.addAttribute("error_msg", true);
            return "redirect:sign-in";
        }
        Cookie newCookie = new Cookie("username", username);
        response.addCookie(newCookie);
        userService.updateLastVisit(username, ip);

        session.setAttribute("username", username);
        return "redirect:greetings";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String returnSignUpPage(ModelMap model,
                                   HttpSession session,
                                   @RequestParam(value = "server_msg", required = false) String server_msg) throws IOException {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return "redirect:greetings";
        }
        model.addAttribute("server_msg", server_msg);
        return "signup";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String signUp(ModelMap model,
                         HttpServletResponse response,
                         HttpSession session,
                         @RequestParam(value = "reg_username") String username,
                         @RequestParam(value = "reg_password") String password,
                         @RequestParam(value = "reg_password_repeat") String password_repeat) throws IOException {

        String ifCorrectCredentials = validateCredentials(username.trim().toLowerCase(), password, password_repeat);
        model.addAttribute("server_msg", ifCorrectCredentials);
        if (ifCorrectCredentials != null) {
            return "redirect:sign-up";
        }
        userService.saveUser(username, password);
        userService.updateLastVisit(username, InetAddress.getLocalHost().getHostAddress());
        session.setAttribute("username", username);

        return "redirect:greetings";
    }

    @RequestMapping(value = "/greetings", method = RequestMethod.GET)
    public String returnUsersPage(ModelMap model,
                                  HttpSession session,
                                  HttpServletRequest request) throws IOException {

        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:sign-in";
        }

        User user = userService.getUserByUsername(username);
        if (user.getLastVisitTime() != null) {
            model.addAttribute("userLastVisitTime", user.getLastVisitTime().toLocalDateTime());
            model.addAttribute("monthName", getMonthName(user.getLastVisitTime().toLocalDateTime().getMonthValue()));
        } else {
            model.addAttribute("userLastVisitTime", null);
        }
        model.addAttribute("user", user);
        String servletPath = request.getServletPath().substring(1, request.getServletPath().length());
        pagesStatisticService.updateCountByPage(servletPath);
        System.out.println(pagesStatisticService.getCountByPage(servletPath));
        model.addAttribute("count", pagesStatisticService.getCountByPage(servletPath));
        return "greetings";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOutGet(ModelMap model,
                            HttpSession session,
                            HttpServletResponse response) throws IOException {
        session.invalidate();
        model.addAttribute("logout_msg", true);
        return "redirect:sign-in";
    }

    @RequestMapping(value = "/ajax/checkUsername", method = RequestMethod.POST)
    public @ResponseBody
    String returnIfUsernameExists(ModelMap model,
                               @RequestParam(value = "username") String username) {
        System.out.println(username);
        if (userService.getUserByUsername(username) != null) {
            return "true";
        }
        return "false";
    }

    private String validateCredentials(String username, String password, String password_repeat) {
        if (userService.getUserByUsername(username) != null) {
            return "Такое имя пользователя уже занято";
        } else if (!USERNAME_PATTERN.matcher(username).matches()) {
            return "Имя пользователя должно быть длиннее 4 символов и состоять из цифр, букв английского алфавита и точек. Первый символ - обяательно буква.";
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            return "Пароль недостаточно сложен: должны быть цифры, заглавные и строчные буквы и длина минимум 8 символов";
        } else if (!password.equals(password_repeat)) {
            return "Пароль и повтор пароля не совпадают";
        }
        return null;
    }

    private String getMonthName(int month) {
        switch (month) {
            case 1:  return "января";
            case 2:  return "февраля";
            case 3:  return "марта";
            case 4:  return "апреля";
            case 5:  return "мая";
            case 6:  return "июня";
            case 7:  return "июля";
            case 8:  return "августа";
            case 9:  return "сентября";
            case 10: return "октября";
            case 11: return "ноября";
            case 12: return "декабря";
            default: return "невозможно";
        }
    }
}
