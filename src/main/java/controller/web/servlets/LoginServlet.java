package controller.web.servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import javax.servlet.http.*;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.api.IJsonWriter;
import view.api.IAuthService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Class which implements login functionality
 *
 * @author Vadim Rataiko
 * @version 1.1.1
 */
@Controller
@RequestMapping(value = "/signIn")
public class LoginServlet {

    /** Constant attribute name for current user storage */
    private final String CURRENT_USER = "currentUser";

    /** Constant attribute name for "SIGN_IN_FAIL" flag */
    private final String SIGN_IN_FAIL = "fail_sign_in";

    /** Instance of class that implements IAuthService interface */
    private final IAuthService authService;
    private final IJsonWriter jsonWriter;

    /**
     * Default constructor which connect IAuthService interface to servlet
     *
     * @param authService instance of IAuthService interface
     * @param jsonWriter
     */
    public LoginServlet(IAuthService authService, IJsonWriter jsonWriter) {
        this.authService = authService;
        this.jsonWriter = jsonWriter;
    }

    /**
     * GET request processing method. Redirects to signIn page
     *
     * @return URL of login page
     */
    @GetMapping
    public String doGet() {
        return "signIn";
    }

    /**
     * POST request processing method. Redirects to profile page if user with entered username and password exists.
     * Redirects to sign in page with error message if login process fails.
     *
     * @param request current HTTP request
     * @return destination URL
     */
    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();
        HashMap<String, String> receivedData;
        HashMap<String, String> result = new HashMap<>();
        String data;
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();

        while( (data = br.readLine()) != null ){
            sb.append(data);
        }

        receivedData = mapper.readValue(sb.toString(), new TypeReference<>() {});

        User user = authService.authentication(receivedData.get("username"), receivedData.get("password"));

        if (user == null) {
            session.setAttribute(SIGN_IN_FAIL, "Incorrect login or password");
            result.put("redirect", contextPath + "/signIn");
        } else {
            session.setAttribute(CURRENT_USER, user);
            result.put("redirect", contextPath + "/home");
        }

        jsonWriter.write(result, response);
    }
}
