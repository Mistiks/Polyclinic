package controller.web.servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.TokenRequest;
import model.User;
import model.dto.UserSession;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import view.api.IAuthService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
@PropertySource("file:D:/Programs/Java_course/Polyclinic/src/main/resources/application.properties")
public class GoogleLoginServlet {

    /** Constant attribute name for "SIGN_IN_FAIL" flag */
    private final String SIGN_IN_FAIL = "fail_sign_in";

    /** Constant attribute name for current user storage */
    private final String CURRENT_USER = "currentUser";

    public static final String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";
    private final IAuthService authService;
    private final Environment env;
    private ObjectMapper mapper;
    private final CommonOAuth2Provider client;
    private Map <String, String> accessInfo;
    private Map <String, String> userInfo;

    public GoogleLoginServlet(IAuthService authService, Environment env) {
        this.authService = authService;
        this.env = env;
        client = CommonOAuth2Provider.GOOGLE;
        mapper = new ObjectMapper();
    }

    @GetMapping("/signIn/google/callback")
    public String getAccessToken(HttpServletRequest request)
            throws IOException {
        String code = request.getParameter("code");
        URL url = new URL("https://oauth2.googleapis.com/token");
        String clientName = client.name().toLowerCase();
        String clientId = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".client-id");
        String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".client-secret");
        String callbackUrl = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".callback-url");
        TokenRequest tokenRequest = new TokenRequest(clientId, clientSecret, code, callbackUrl);
        String jsonResult = mapper.writeValueAsString(tokenRequest);

        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()){
            byte[] input = jsonResult.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        accessInfo = getInfoFromSite(con);

        return "redirect:/signIn/google/callback/login";
    }

    @GetMapping("/signIn/google/callback/login")
    public String getUserMail(HttpServletRequest request, Model model) throws IOException {

        URL url = new URL("https://www.googleapis.com/oauth2/v2/userinfo");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + accessInfo.get("access_token"));
        con.setDoOutput(true);

        userInfo = getInfoFromSite(con);

        try {
            User user = authService.googleAuthentication(userInfo.get("email"), userInfo.get("id"));
            UserSession userSession = new UserSession(user.getLogin(), user.getId(), user.getDoctorId(), user.getRole());
            request.getSession().setAttribute(CURRENT_USER, userSession);
        } catch (IllegalArgumentException e) {
            model.addAttribute(SIGN_IN_FAIL, e.getMessage());
            return "signIn";
        }

        return "redirect:/home";
    }

    private HashMap<String, String> getInfoFromSite(HttpURLConnection con) {
        HashMap<String, String> result = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;

            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            result = mapper.readValue(response.toString(), new TypeReference<>() {});

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}