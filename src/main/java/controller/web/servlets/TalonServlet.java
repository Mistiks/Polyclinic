package controller.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Talon;
import model.User;
import model.dto.TalonRequest;
import model.dto.TalonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import storage.api.ITalonRepository;
import storage.api.IUserRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/talon")
public class TalonServlet {

    private final IUserRepository repository;
    private final ITalonRepository talonRepository;
    private final String POSITION_LIST = "positionsList";
    private final String CURRENT_USER = "currentUser";
    private final ObjectMapper mapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public TalonServlet(IUserRepository repository, ITalonRepository talonRepository, ObjectMapper mapper) {
        this.repository = repository;
        this.talonRepository = talonRepository;
        this.mapper = mapper;
    }

    @GetMapping
    public String doGet(Model model) {
        model.addAttribute(POSITION_LIST, repository.findAllPositions());
        return "talon";
    }

    @PostMapping(value = "/specialization")
    @ResponseBody
    public ResponseEntity<?> getDoctor(@RequestBody TalonRequest talonRequest) {
        List<User> users = repository.findAllByPosition(talonRequest.getSpecialization());
        List<TalonResponse> fio = new ArrayList<>();

        for (User user : users) {
            fio.add(new TalonResponse(user.getDoctorId(), user.getSurname().concat(" ").concat(user.getFirstName()).
                    concat(" ").concat(user.getPatronymic())));
        }

        return new ResponseEntity<>(fio, HttpStatus.OK);
    }

    @PostMapping(value = "/doctor")
    @ResponseBody
    public ResponseEntity<?> getDate(@RequestBody TalonRequest talonRequest) {
        List<Talon> talons = talonRepository.findAllByFio(talonRequest.getDoctorId());
        List<String> result = new ArrayList<>();

        for (Talon talon : talons) {
            LocalDateTime dateTime = talon.getVisitTime();
            result.add(dateTime.format(formatter));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/date")
    @ResponseBody
    public ResponseEntity<?> getTime(@RequestBody TalonRequest talonRequest) {
        LocalDate requestDate = LocalDate.parse(talonRequest.getDate(), formatter);
        List<Talon> talons = talonRepository.findAllByTalonDate(talonRequest.getDoctorId(), requestDate);
        List<String> result = new ArrayList<>();

        talons.forEach((record) -> {
            LocalDateTime localDateTime = record.getVisitTime();
            result.add(localDateTime.format(timeFormatter));
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/time")
    @ResponseBody
    public ResponseEntity<?> saveTalon(@RequestBody TalonRequest talonRequest, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        LocalDate requestDate = LocalDate.parse(talonRequest.getDate(), formatter);
        LocalTime requestTime = LocalTime.parse(talonRequest.getTime(), timeFormatter);
        LocalDateTime localDateTime = requestDate.atTime(requestTime);
        Talon talon = talonRepository.getTalon(talonRequest.getDoctorId(), localDateTime);
        talon.setUserId(user.getId());
        talonRepository.save(talon);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
