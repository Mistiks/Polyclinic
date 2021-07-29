package controller.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import model.dto.TalonRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import storage.api.IUserRepository;
import utils.api.IJsonWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/talon")
public class TalonServlet {

    private final IJsonWriter jsonWriter;
    private final IUserRepository repository;
    private final String POSITION_LIST = "positionsList";
    private final ObjectMapper mapper;

    public TalonServlet(IJsonWriter jsonWriter, IUserRepository repository, ObjectMapper mapper) {
        this.jsonWriter = jsonWriter;
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping
    public String doGet(Model model) {
        model.addAttribute(POSITION_LIST, repository.findAllPositions());
        return "talon";
    }

    @PostMapping(value = "/specialization")
    @ResponseBody
    public ResponseEntity<?> getNames(@RequestBody TalonRequest talonRequest) {
        List<User> users = repository.findAllByPosition(talonRequest.getSpecialization());
        List<String> fio = new ArrayList<>();

        for (User user : users) {
            fio.add(user.getSurname().concat(" ").concat(user.getFirstName()).
                    concat(" ").concat(user.getPatronymic()));
        }

        return new ResponseEntity<>(fio, HttpStatus.OK);
    }
}
