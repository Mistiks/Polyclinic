package controller.web.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/logout")
public class LogoutServlet {

    private final String CURRENT_USER = "currentUser";

    @GetMapping
    public String doGet(HttpServletRequest request) {
        request.getSession().removeAttribute(CURRENT_USER);
        return "redirect:/home";
    }
}
