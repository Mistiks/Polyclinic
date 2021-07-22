package controller.web.servlets;

import config.SecurityConfig;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class GoogleRegistrationJSP {

    private final Environment env;

    public GoogleRegistrationJSP(Environment env) {
        this.env = env;
    }

    @GetMapping("/signIn/google/callback")
    public String getLoginPage(HttpServletRequest request) {
        String code = request.getParameter("code");


        return "signIn/google/callback";
        }
    }
