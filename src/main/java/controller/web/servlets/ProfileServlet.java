package controller.web.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.api.IJsonWriter;
import view.api.IAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/profile")
public class ProfileServlet {

    private final IJsonWriter jsonWriter;
    private final IAccountService accountService;

    public ProfileServlet(IJsonWriter jsonWriter, IAccountService accountService) {
        this.jsonWriter = jsonWriter;
        this.accountService = accountService;
    }

    @GetMapping
    public String doGet() {
        return "profile";
    }

    @PostMapping(value = "/account-general")
    public void updateGeneral(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, String> receivedData = jsonWriter.read(request);
        HashMap<String, String> result = new HashMap<>();

        try {
            accountService.updateGeneralInfo(receivedData, request);
            result.put("success", "Information saved!");
        } catch (IllegalArgumentException e) {
            result.put("error", e.getMessage());
        }

        jsonWriter.write(result, response);
    }

    @PostMapping(value = "/account-password")
    public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, String> receivedData = jsonWriter.read(request);
        HashMap<String, String> result = new HashMap<>();

        try {
            accountService.updatePassword(receivedData, request);
            result.put("success", "Information saved!");
        } catch (IllegalArgumentException e) {
            result.put("error", e.getMessage());
        }

        jsonWriter.write(result, response);
    }
}
