package controller.web.servlets;

import config.SecurityConfig;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(value = "/oauth2/callback/google")
public class GithubRegistrationJSP {

    private final Environment env;

    public GithubRegistrationJSP(Environment env) {
        this.env = env;
    }

    @GetMapping
    public void callback(HttpServletRequest request){
        String code = request.getParameter("code");

        System.out.println(code);
    }
}
