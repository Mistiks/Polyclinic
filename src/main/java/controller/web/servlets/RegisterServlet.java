package controller.web.servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.api.IJsonWriter;
import view.api.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/signUp")
public class RegisterServlet {

    /** Constant attribute name for "SIGN_UP_FAIL" flag */
    private static final String SIGN_UP_FAIL = "fail_sign_up";

    /** Instance of class that implements IUserService interface */
    private final IUserService userService;
    private final IJsonWriter jsonWriter;

    /** Default constructor which connect IUserService interface to servlet
     *
     * @param userService instance of IUserService interface
     * @param jsonWriter
     */
    public RegisterServlet(IUserService userService, IJsonWriter jsonWriter) {
        this.userService = userService;
        this.jsonWriter = jsonWriter;
    }

    /**
     * GET request processing method. Redirects to signUp page
     *
     * @return URL of registration page
     */
    @GetMapping
    public String doGet() {
        return "signUp";
    }

    /**
     * POST request processing method. Register new user if all field are filled with data (username must be unique).
     * Redirects to sign up page with error message if register process fails.
     *
     * @param request request information for HTTP servlets
     * @return destination URL
     */
    @PostMapping
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

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

        try {
            User user = new User(receivedData.get("username"));
            this.userService.signUp(user, receivedData.get("password"));
            result.put("redirect", contextPath + "/home");
        } catch (IllegalArgumentException e){
            request.getSession().setAttribute(SIGN_UP_FAIL, e.getMessage());
            result.put("redirect", contextPath + "/signUp");
        } finally {
            jsonWriter.write(result, response);
        }
    }
}
